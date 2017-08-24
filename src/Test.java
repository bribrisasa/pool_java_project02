
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Test {
//tamereenslip
	public static final String DEST = "C:/Users/Laurent/Documents/GitHub/pool_java_project02/srcMeeting.pdf";

	public static void main(String[] args) throws IOException,
	DocumentException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new Test().createPdf(DEST);
	}


	public void createPdf(String dest) throws IOException, DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(dest));
		document.open();
		PdfPTable table = new PdfPTable(4);
		table.addCell("hi");
		table.addCell("ho");
		table.addCell("ha");
		table.addCell("hi");
		table.addCell("hi");
		table.addCell("hi");
		table.addCell("hi");
		table.addCell("hi");
		document.add(table);
		PdfPTable table2 = new PdfPTable(1);
		Paragraph wrong = new Paragraph("This is wrong, because an object that was originally a paragraph is reduced to a phrase due to the fact that it's put into a cell that uses text mode.");
		wrong.setIndentationLeft(20);
        document.add(wrong);
		document.close();
	}
}