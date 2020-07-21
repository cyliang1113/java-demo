package cn.leo.java.demo.aaaa7788;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class OfficeUtils {
    /**
     * 根据word模板生成新的word
     * @param templateFileDir
     * @param params
     * @return
     */
    public static XWPFDocument genWordWithParams(String templateFileDir, Map<String, String> params){
        FileInputStream fileInputStream = null;
        XWPFDocument document = null;
        try {
            fileInputStream = new FileInputStream(templateFileDir);
            document = new XWPFDocument(fileInputStream);
            if (params == null || params.isEmpty()) {
                return document;
            }
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    run.setText(changeValue(run.toString(), params),0);
                }
            }
            return document;
        } catch (IOException e) {

            if (document != null) {
                try {
                    document.close();
                } catch (IOException ee) {

                }
            }
            throw new RuntimeException("XWPFDocument异常");
        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {

                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String changeValue(String value, Map<String, String> textMap){
        Set<Map.Entry<String, String>> textSets = textMap.entrySet();
        for (Map.Entry<String, String> textSet : textSets) {
            String key = textSet.getKey();
            if(value.indexOf(key)!= -1){
                value = value.replace(key, textSet.getValue());
            }
        }
        return value;
    }

    /**
     * 合并word 把第二个合并到第一个
     * @param document1
     * @param document2
     * @throws Exception
     */
    public static void mergeWord(XWPFDocument document1, XWPFDocument document2) throws Exception {
        CTBody src1Body = document1.getDocument().getBody();
        CTBody src2Body = document2.getDocument().getBody();
        XmlOptions optionsOuter = new XmlOptions();
        optionsOuter.setSaveOuter();
        String appendString = src2Body.xmlText(optionsOuter);
        String srcString = src1Body.xmlText();
        String prefix = srcString.substring(0,srcString.indexOf(">")+1);
        String mainPart = srcString.substring(srcString.indexOf(">")+1,srcString.lastIndexOf("<"));
        String suffix = srcString.substring( srcString.lastIndexOf("<") );
        String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
        CTBody makeBody = null;
        makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + suffix);
        src1Body.set(makeBody);
        return;
    }

    public static void wordSaveToDisk(XWPFDocument xwpfDocument, File docFile) {
        OutputStream docFileOut = null;
        try {
            docFileOut = new FileOutputStream(docFile);
            xwpfDocument.write(docFileOut);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (xwpfDocument != null) {
                try {
                    xwpfDocument.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (docFileOut != null) {
                try {
                    docFileOut.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
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
