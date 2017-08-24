package GUI;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class HeaderFooterPageEvent extends PdfPageEventHelper {
	private PdfTemplate t;
	private Image total;
	
	public void onOpenDocument(PdfWriter writer, Document document) {
		t = writer.getDirectContent().createTemplate(30, 16);
		try {
			total = Image.getInstance(t);
			total.setRole(PdfName.ARTIFACT);
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		addHeader(writer);
	}

	private void addHeader(PdfWriter writer){
		PdfPTable header = new PdfPTable(2);
		try {
			// layout de la page
			header.setWidths(new int[]{2, 24});
			header.setTotalWidth(527);
			header.setLockedWidth(true);
			header.getDefaultCell().setFixedHeight(40);
			header.getDefaultCell().setBorder(Rectangle.BOTTOM);
			header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
			Image logo = Image.getInstance(HeaderFooterPageEvent.class.getResource("/IMAGES/icons8-leaf.png"));
			header.addCell(logo);

			// add text
			PdfPCell text = new PdfPCell();
			text.setPaddingBottom(15);
			text.setPaddingLeft(10);
			text.setBorder(Rectangle.BOTTOM);
			text.setBorderColor(BaseColor.LIGHT_GRAY);
			text.addElement(new Phrase("PDF Creator by GreenLeaf inc", new Font(Font.FontFamily.HELVETICA, 12)));
			text.addElement(new Phrase("http://GreenLeaf.com", new Font(Font.FontFamily.HELVETICA, 8)));
			header.addCell(text);

			// write content
			header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
		} catch(DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (MalformedURLException e) {
			throw new ExceptionConverter(e);
		} catch (IOException e) {
			throw new ExceptionConverter(e);
		}
	}
}
