package com.cusc.cuscai.util;

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
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
        File tmp = File.createTempFile("tmp", suffix);
        file.transferTo(tmp);

        List<Map> res = new ArrayList<>();

        //获取一张表
        InputStream ins = new FileInputStream(tmp);
        XSSFWorkbook book = new XSSFWorkbook(ins);
        Sheet sheet = book.getSheetAt(0);
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

}
