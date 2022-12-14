package edu.java.spring.view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.java.spring.model.JavaClazz;
import edu.java.spring.model.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {

    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) {
        JavaClazz clazz = (JavaClazz) model.get("clazzObj");
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        try {
            table.setWidths(new float[]{2.0f, 3.0f, 1.5f});
            table.setSpacingBefore(10);
            Font font = FontFactory.getFont(FontFactory.HELVETICA);
            font.setColor(BaseColor.WHITE);
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.GREEN);
            cell.setPadding(5);
            cell.setPhrase(new Phrase("ID", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Name", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Age", font));
            table.addCell(cell);
            table.completeRow();
            for (Student student : clazz.getStudents()
            ) {
                table.addCell(String.valueOf(student.getId()));
                table.addCell(String.valueOf(student.getName()));
                table.addCell(String.valueOf(student.getAge()));
                table.completeRow();
            }
            document.add(table);
        } catch (DocumentException ex) {
            throw new RuntimeException(ex);
        }
    }
}
