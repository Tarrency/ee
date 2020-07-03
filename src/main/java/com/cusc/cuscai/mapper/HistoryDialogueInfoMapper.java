package com.cusc.cuscai.mapper;

import com.cusc.cuscai.entity.bo.HistoryDialogueInfoBO;
import com.cusc.cuscai.entity.model.HistoryDialogueInfo;
import com.cusc.cuscai.entity.model.HistoryDialogueInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HistoryDialogueInfoMapper {
    int countByExample(HistoryDialogueInfoExample example);

    int deleteByExample(HistoryDialogueInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HistoryDialogueInfo record);

    int insertSelective(HistoryDialogueInfo record);

    List<HistoryDialogueInfoBO> selectByExample(HistoryDialogueInfoExample example);

    HistoryDialogueInfoBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HistoryDialogueInfo record, @Param("example") HistoryDialogueInfoExample example);

    int updateByExample(@Param("record") HistoryDialogueInfo record, @Param("example") HistoryDialogueInfoExample example);

    int updateByPrimaryKeySelective(HistoryDialogueInfo record);

    int updateByPrimaryKey(HistoryDialogueInfo record);
}