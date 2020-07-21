package cn.leo.java.demo.aaaa7788;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SettleDoc {
    public static void main(String[] args) throws Exception {
        String dir = "D:\\settle";
        File file = new File(dir);
        String[] files = file.list();
        XWPFDocument xwpfDocument1 = OfficeUtils.genWordWithParams(dir + "\\" + files[0], null);
        for (int i = 1; i < files.length; i++) {
            XWPFDocument xwpfDocument2 = OfficeUtils.genWordWithParams(dir + "\\" + files[i], null);
            OfficeUtils.mergeWord(xwpfDocument1, xwpfDocument2);
        }
        OfficeUtils.wordSaveToDisk(xwpfDocument1, new File("C:\\Users\\youliang.chen\\Desktop\\12121.docx"));

//        String f1 = "C:\\Users\\youliang.chen\\Desktop\\周口地区委托书.docx";
//        String f2 = "C:\\Users\\youliang.chen\\Desktop\\东莞地区委托书.docx";
//        XWPFDocument xwpfDocument1 = OfficeUtils.genWordWithParams(f1, null);
//        XWPFDocument xwpfDocument2 = OfficeUtils.genWordWithParams(f2, null);
//        XWPFDocument xwpfDocument3 = OfficeUtils.genWordWithParams(f1, null);
//        OfficeUtils.mergeWord(xwpfDocument1, xwpfDocument2);
//        OfficeUtils.mergeWord(xwpfDocument1, xwpfDocument3);
//        OfficeUtils.wordSaveToDisk(xwpfDocument1, new File("C:\\Users\\youliang.chen\\Desktop\\12121.docx"));
    }

    public enum ExcelFormat{
        xls, xlsx;
    }

    public static ExcelFormat getExcelFormat(String fileName) {
        if (fileName.endsWith(ExcelFormat.xls.name())){
            return ExcelFormat.xls;
        }
        if (fileName.endsWith(ExcelFormat.xlsx.name())) {
            return ExcelFormat.xlsx;
        }
        throw new RuntimeException("不是excel文件");
    }

    public static Workbook getExcelWorkbook(InputStream in, ExcelFormat format) {
        Workbook workbook = null;
        try {
            if(ExcelFormat.xls.equals(format)){
                workbook = new HSSFWorkbook(in);
            }else{
                workbook = new XSSFWorkbook(in);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return workbook;
    }

    public static Object excelCellTransform(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                // 处理日期格式、时间格式
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                            .getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } if(cell.getCellStyle().getDataFormat() == HSSFDataFormat
                            .getBuiltinFormat("m/d/yy h:mm")){
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    return sdf.format(date);
                } else {
                    DecimalFormat df = new DecimalFormat("##.##");
                    return df.format(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getArrayFormulaRange();
            case ERROR:
                return cell.getErrorCellValue();
            default:
                return "";
        }
    }
}
