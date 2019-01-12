package com.xuyao.test.file;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static void main(String[] args) {
        try {
            readContent("xxx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<List<String>> readContent(String filePath) throws Exception {

        InputStream is = new FileInputStream(filePath);
        String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
        System.out.println(suffix);

        Workbook workbook = null;
        if(XLS.equals(suffix)){
            workbook = new HSSFWorkbook(is);
        }else if(XLSX.equals(suffix)){
            workbook = new XSSFWorkbook(is);
        }

        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum(); //行数，从0开始
        int lastCellNum = sheet.getRow(0).getPhysicalNumberOfCells(); //列数，从1开始
        Row row;
        Cell cell;
        int cellNum = 0;
        for (int i = 0; i <= lastRowNum; i++) {
            row = sheet.getRow(i);
            for(int j = 0; j < lastCellNum; j++){
                cell = row.getCell(cellNum++);
                System.out.print(ExcelUtils.getCellValue(cell) + ", ");
            }
            System.out.println();
            cellNum = 0;
        }
        return null;
    }



    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 判断数据的类型
        switch (cell.getCellType()) {
            case NUMERIC: // 数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    // 验证short值
                    if (cell.getCellStyle().getDataFormat() == 14) {
                        sdf = new SimpleDateFormat("yyyy/MM/dd");
                    } else if (cell.getCellStyle().getDataFormat() == 21) {
                        sdf = new SimpleDateFormat("HH:mm:ss");
                    } else if (cell.getCellStyle().getDataFormat() == 22) {
                        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    } else {
                        throw new RuntimeException("日期格式错误!!!");
                    }
                    Date date = cell.getDateCellValue();
                    cellValue = sdf.format(date);
                }else{
                    cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: // Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: // 公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK: // 空值
                cellValue = null;
                break;
            case ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

}
