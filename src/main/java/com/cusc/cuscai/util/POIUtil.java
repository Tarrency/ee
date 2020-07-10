package com.cusc.cuscai.util;

import com.cusc.cuscai.entity.model.KnowledgeGet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POIUtil {
    //解析excel文件
    public static List<Map> parseExcel(MultipartFile file) throws Exception {
//        取后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
//        创建临时文件，返回值是路径
        File tmp = File.createTempFile("tmp", suffix);
//        将文件保存到临时文件中
        file.transferTo(tmp);

        List<Map> res = new ArrayList<>();

        //获取一张表
//        读取文件
        InputStream ins = new FileInputStream(tmp);
//        创建一个工作簿，将excel文件读进来
        XSSFWorkbook book = new XSSFWorkbook(ins);

//        获取第一个表格
        Sheet sheet = book.getSheetAt(0);
//        获取表格的第一行
        Row rh = sheet.getRow(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            Map<String, String> map = row2map(rh, row);
            res.add(map);
        }
        return res;
    }

    //excel列传换为map
    public static Map<String, String> row2map(Row head, Row row) {
        HashMap<String, String> map = new HashMap<String, String>();
        for (int i = head.getFirstCellNum(); i < head.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if(cell!=null){
                cell.setCellType(CellType.STRING); //这里不加这一句会报bugCannot get a STRING value from a NUMERIC cell
            }
             map.put(head.getCell(i).getStringCellValue(), cell == null ? "" : cell.getStringCellValue());
        }

        return map;
    }

    public static List<KnowledgeGet> parseKnowledgeExcel(MultipartFile file) throws Exception{
        //        取后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
//        创建临时文件，返回值是路径
        File tmp = File.createTempFile("tmp", suffix);
//        将文件保存到临时文件中
        file.transferTo(tmp);

        List<KnowledgeGet> res = new ArrayList<>();

        //获取一张表
//        读取文件
        InputStream ins = new FileInputStream(tmp);
//        创建一个工作簿，将excel文件读进来
        XSSFWorkbook book = new XSSFWorkbook(ins);

//        获取第一个表格
        Sheet sheet = book.getSheetAt(0);
//        获取光标当前行数，即第一行
        Row rh = sheet.getRow(0);
        for (Row r : sheet){
            KnowledgeGet k = new KnowledgeGet();
            k.setQuestion(r.getCell(0).getStringCellValue());
            k.setAnswer(r.getCell(1).getStringCellValue());
            k.setType(r.getCell(2).getStringCellValue());
            res.add(k);
        }
        return res;
    }

}
