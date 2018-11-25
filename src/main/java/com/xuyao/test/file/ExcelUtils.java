package com.xuyao.test.file;

import java.io.File;
import java.util.List;

public class ExcelUtils {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static List<List<String>> readContent(File file){
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(suffix);
        return null;
    }

    public static List<List<String>> readContent(String filePath){
        return readContent(new File(filePath));
    }

}
