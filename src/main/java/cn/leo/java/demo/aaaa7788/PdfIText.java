package cn.leo.java.demo.aaaa7788;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class PdfIText {
    public static void main(String[] args) throws Exception {
        String dir = "C:\\Users\\youliang.chen\\Desktop\\poa\\新建文件夹";
        File file = new File(dir);
        String[] files = file.list();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\66666.pdf");
        Document doc = new Document();
        PdfCopy copy = new PdfCopy(doc, fileOutputStream);
        doc.open();


        for (int i = 0; i < files.length; i++) {

            PdfReader reader = new PdfReader(dir + "\\" + files[i]);
            int numberOfPages = reader.getNumberOfPages();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, bos);
            AcroFields fields = stamper.getAcroFields();
            if (fields != null) {
                List<AcroFields.FieldPosition> vin = fields.getFieldPositions("vin");
                if(vin != null) {
                    for (AcroFields.FieldPosition fieldPosition : vin) {
                        fields.setField("vin", "1231231aaaaAABB");
                    }

                    stamper.setFormFlattening(true);
                    stamper.close();

                    for (int index = 1; index <= numberOfPages; index++) {
                        doc.newPage();
                        PdfImportedPage importedPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), index);
                        copy.addPage(importedPage);
                    }

                    continue;
                }
            }

            for (int index = 1; index <= numberOfPages; index++) {
                doc.newPage();
                PdfImportedPage importedPage = copy.getImportedPage(reader, index);
                copy.addPage(importedPage);
            }
        }
        copy.close();
        doc.close();
    }

}
