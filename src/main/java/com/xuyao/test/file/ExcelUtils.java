package com.xuyao.test.file;

import java.io.File;
import java.util.List;

public class ExcelUtils {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static void main(String[] args) {
        System.out.println(readContent(new File("C:\\Users\\xuyao\\Desktop\\pom.xml")));

    }
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
