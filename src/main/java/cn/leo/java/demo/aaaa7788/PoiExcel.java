package cn.leo.java.demo.aaaa7788;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PoiExcel {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\youliang.chen\\Desktop\\新建文件夹\\营业执照解押版本统计-更新至100本后.xlsx");

        String fileName = file.getName();
        ExcelFormat excelFormat = getExcelFormat(fileName);
        InputStream inputStream = new FileInputStream(file);

        Workbook excelWorkbook = getExcelWorkbook(inputStream, excelFormat);
        Sheet sheet = excelWorkbook.getSheetAt(0);
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                continue;
            }
            String agent_name = excelCellTransform(row.getCell(0)).toString().trim();
            String province = excelCellTransform(row.getCell(3)).toString().trim();
            String city = excelCellTransform(row.getCell(4)).toString().trim();
            String type = excelCellTransform(row.getCell(7)).toString().trim();
            String bl_code = null;
            if ("新证".equals(type)) {
                String oV = excelCellTransform(row.getCell(8)).toString().trim();
                String[] split = oV.split("/");
                bl_code = "zhang-" + split[0] + "-" + split[1];
            } else {
                bl_code = excelCellTransform(row.getCell(8)).toString().trim();
            }
            String sql = "insert into orders.order_settle_agent_bl(agent_name, agent_city, agent_province, bl_code) values('"+agent_name+"', '"+city+"', '"+province+"', '"+bl_code+"');";
            System.out.println(sql);
        }
//        blUrl();

    }

    public static void blUrl(){
        String urlPre = "https://prod-zhyq-oss.oss-cn-hangzhou.aliyuncs.com/order_settle_agent_bl/20200721/";
        String foldStr = "C:\\Users\\youliang.chen\\Desktop\\新建文件夹\\all";
        File fold = new File(foldStr);
        for (String name : fold.list()) {
            int i = name.indexOf(".");
            String nn = name.substring(0, i);
            String url = urlPre + name;
            String sql = "insert into orders.order_settle_bl(bl_code, bl, bl_url) values('"+ nn + "', '" + nn + "', '" + url + "');";
            System.out.println(sql);

        }

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
