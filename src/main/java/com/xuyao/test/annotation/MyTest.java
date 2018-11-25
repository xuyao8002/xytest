package com.xuyao.test.annotation;


import com.xuyao.test.Person;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyTest {

    public static void main(String[] args) throws Exception {
        Workbook workbook = new HSSFWorkbook(new FileInputStream("C:\\Users\\xuyao\\Desktop\\ksks.xls"));
        Sheet sheetAt = workbook.getSheetAt(0);
        Row row = sheetAt.getRow(0);
        int colNum = row.getLastCellNum();
        Map<String, Integer> map = new LinkedHashMap<>();

        for(int i = 0; i < colNum; i++){
            map.put(row.getCell(i).getStringCellValue(), i);
        }
        Field[] declaredFields = Person.class.getDeclaredFields();
        Map<Integer, Field> f = new HashMap<>();
        for (Field declaredField : declaredFields) {
            if(!declaredField.isAnnotationPresent(FieldAnno.class)) continue;
            FieldAnno annotation = declaredField.getAnnotation(FieldAnno.class);
            f.put(map.get(annotation.chn()), declaredField);

        }

        for(int i = 1; i <= sheetAt.getLastRowNum(); i++){
            row = sheetAt.getRow(i);
            Person p = new Person();
            for(int j = 0; j < colNum; j++){
                Field field = f.get(j);
                field.setAccessible(true);
                field.set(p, (j == 1) ? ((int) row.getCell(j).getNumericCellValue()): row.getCell(j).getStringCellValue());
            }
            System.out.println(p);
        }

    }


}

