package com.cusc.cuscai.util.sensitivewd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.NavigableSet;

/**
 * 敏感词过滤器，以过滤速度优化为主。<br/>
 * * 增加一个敏感词：{@link #put(String)} <br/>
 * * 过滤一个句子：{@link #filter(String)} <br/>
 */
public class SensitiveFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 为2的n次方，考虑到敏感词大概在10k左右，
     * 这个数量应为词数的数倍，使得桶很稀疏
     * 提高不命中时hash指向null的概率，
     * 加快访问速度。
     */
    static final int DEFAULT_INITIAL_CAPACITY = 131072;

    /**
     * 类似HashMap的桶，比较稀疏。
     * 使用2个字符的hash定位。
     */
    public SensitiveNode[] nodes = new SensitiveNode[DEFAULT_INITIAL_CAPACITY];

    /**
     * 构建一个空的filter
     */
    public SensitiveFilter() {

    }

    /**
     * 加载一个文件中的词典，并构建filter<br/>
     * 文件中，每行一个敏感词条<br/>
     * <b>注意：</b>读取完成后会调用{@link BufferedReader#close()}方法。<br/>
     * <b>注意：</b>读取中的{@link IOException}不会抛出
     *
     * @param reader
     */
    public SensitiveFilter(BufferedReader reader) {
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                put(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个敏感词
     *
     * @param word
     */
    public boolean put(String word) {
        // 长度小于2的不加入
        if (word == null || word.trim().length() < 2) {
            return false;
        }
        // 两个字符的不考虑
        if (word.length() == 2 && word.matches("\\w\\w")) {
            return false;
        }
        StringPointer sp = new StringPointer(word.trim());
        // 计算头两个字符的hash
        int hash = sp.nextTwoCharHash(0);
        // 计算头两个字符的mix表示（mix相同，两个字符相同）
        int mix = sp.nextTwoCharMix(0);
        // 转为在hash桶中的位置
        int index = hash & (nodes.length - 1);

        // 从桶里拿第一个节点
        SensitiveNode node = nodes[index];
        if (node == null) {
            // 如果没有节点，则放进去一个
            node = new SensitiveNode(mix);
            // 并添加词
            node.words.add(sp);
            // 放入桶里
            nodes[index] = node;
        } else {
            // 如果已经有节点（1个或多个），找到正确的节点
            for (; node != null; node = node.next) {
                // 匹配节点
                if (node.headTwoCharMix == mix) {
                    node.words.add(sp);
                    return true;
                }
                // 如果匹配到最后仍然不成功，则追加一个节点
                if (node.next == null) {
                    new SensitiveNode(mix, node).words.add(sp);
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * 判断句子是否包含敏感词
     *
     * @param sentence 句子
     * @return 是否有敏感词
     */
    public boolean filter(String sentence) {
        // 先转换为StringPointer
        StringPointer sp = new StringPointer(sentence);

        // 标示是否有敏感词
        boolean flag = false;

        // 匹配的起始位置
        int i = 0;
        while (i < sp.length - 1) {
            if (flag) {
                break; //找到敏感词，退出
            }
            /*
             * 移动到下一个匹配位置的步进：
             * 如果未匹配为1，如果匹配是匹配的词长度
             */
            int step = 1;
            // 计算此位置开始2个字符的hash
            int hash = sp.nextTwoCharHash(i);
            /*
             * 根据hash获取第一个节点，
             * 真正匹配的节点可能不是第一个，
             * 所以有后面的for循环。
             */
            SensitiveNode node = nodes[hash & (nodes.length - 1)];
            /*
             * 如果非敏感词，node基本为null。
             * 这一步大幅提升效率
             */
            if (node != null) {
                /*
                 * 如果能拿到第一个节点，
                 * 才计算mix（mix相同表示2个字符相同）。
                 * mix的意义和HashMap先hash再equals的equals部分类似。
                 */
                int mix = sp.nextTwoCharMix(i);
                /*
                 * 循环所有的节点，如果非敏感词，
                 * mix相同的概率非常低，提高效率
                 */
                outer:
                for (; node != null; node = node.next) {
                    /*
                     * 对于一个节点，先根据头2个字符判断是否属于这个节点。
                     * 如果属于这个节点，看这个节点的词库是否命中。
                     * 此代码块中访问次数已经很少，不是优化重点
                     */
                    if (node.headTwoCharMix == mix) {
                        /*
                         * 查出比剩余sentence小的最大的词。
                         */
                        NavigableSet<StringPointer> desSet = node.words.headSet(sp.substring(i), true);
                        for (StringPointer word : desSet.descendingSet()) {
                            /*
                             * 仍然需要再判断一次
                             */
                            if (sp.nextStartsWith(i, word)) {
                                // 匹配成功
                                // 标示有敏感词
                                flag = true;
                                // 跳出循环（然后是while循环的下一个位置）
                                break outer;
                            }
                        }
                    }
                }
            }

            // 移动到下一个匹配位置
            i += step;
        }

        return flag;
    }

}
