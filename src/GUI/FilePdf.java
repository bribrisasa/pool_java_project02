package GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FilePdf {
	String titre;
	String location;
	String date;
	String duration;
	public static final String DEST="C:/Users/Laurent/Documents/GitHub/pool_java_project02/srcMeeting.pdf";
	ArrayList<String> attendance;
	ArrayList<String> actions;
	BaseColor darkblue = new BaseColor(77, 182, 172);
	BaseColor lightblue = new BaseColor(0, 150, 136);

	String summary;

	public FilePdf(String titre, String location, String date, String duration, ArrayList<String> attendance,
			ArrayList<String> actions, String summary) throws FileNotFoundException, DocumentException {
		this.titre = titre;
		this.location = location;
		this.date = date;
		this.duration = duration;
		this.attendance = attendance;
		this.actions = actions;
		this.summary = summary;
		//creation de document
		Document document = new Document(PageSize.A4, 36, 36, 90, 36);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
		//ajout du header et du footer
		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		writer.setPageEvent(event);
		//Document commence ici
		document.open();
		document.addTitle("Meeting report");
		document.add(addTitle(titre));
		document.add(addStart(location,date,duration));
		document.add(addListMembers(attendance));
		document.add(addBreacklines());
		document.add(addListSujets(actions));
		document.add(addBreacklines());
		document.add(addReport(summary));
		document.close();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("PDF Creation");
		alert.setContentText("Your PDF files as been created!");

		alert.showAndWait();
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File("C:/Users/Laurent/Documents/GitHub/pool_java_project02/srcMeeting.pdf");
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
	}

	//methode de creation de resumé de réunion
	public Paragraph addReport(String para) {
		Chunk underline = new Chunk("Meeting report: \n");
		underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
		Paragraph p = new Paragraph();
		p.add("\n");
		p.add(underline);
		p.add("\n"+para+"\n");
		return p;
	}
	//methode de creation de lignes de séparations
	public DottedLineSeparator addBreacklines() {
		DottedLineSeparator line = new DottedLineSeparator();
		return line;
	}

	public void affichePdf() {
		System.out.println("Titre : "+this.titre);
		System.out.println("Location : "+this.location);
		System.out.println("Date : "+this.date);
		System.out.println("Duration : "+this.duration);
		System.out.println("Participants : ");
		this.afficheList(attendance);
		System.out.println("Actions : ");
		this.afficheList(actions);
		System.out.println("Summary : ");
		System.out.println(this.summary);
	}

	public void afficheList(ArrayList<String> list) {
		for (String string : list) {
			System.out.println(string);
		}
	}

	public String getTitre() {
		return titre;
	}

	public String getLocation() {
		return location;
	}

	public String getDate() {
		return date;
	}

	public String getDuration() {
		return duration;
	}

	public ArrayList<String> getAttendance() {
		return attendance;
	}

	public ArrayList<String> getActions() {
		return actions;
	}

	public String getSummary() {
		return summary;
	}

	//methode de creation du titre
	public Paragraph addTitle(String title){
		Font fontbold = FontFactory.getFont("Greek-Regular", 40, Font.BOLD);
		Paragraph p = new Paragraph(title, fontbold);
		p.setSpacingAfter(20);
		p.setAlignment(1); // Center
		p.add("\n");
		return p;
	}

	//methode de creation de listes des membres
	public Paragraph addListMembers(ArrayList<String> attendance2) {
		Font zapfdingbats = new Font(FontFamily.ZAPFDINGBATS, 8);
		Font font = new Font();
		Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);
		Chunk underline = new Chunk("Attendance: \n");
		underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
		Paragraph p = new Paragraph();
		p.add(underline);
		p.add("\n");
		for (String item: attendance2) {
			p.add(bullet);
			p.add(new Phrase(" " + item + "\n", font));
		}
		p.add("\n");
		return p;
	}
	//methode de creation de creation d'entête
	public Paragraph addStart(String loc, String date,String time) {
		LineSeparator line = new LineSeparator();
		line.setLineColor(darkblue);
		Paragraph p = new Paragraph();
		p.add(line);  
		p.add("\n");
		p.add("Location :               ");
		p.add(loc+" \n");
		p.add("\n");
		p.add("Date :                     ");
		p.add(date+" \n");
		p.add("\n");
		p.add("Time :                     ");
		p.add(time+" \n");
		p.add(line);  
		p.add("\n");
		p.add("\n");
		return p;
	}

	//methode de creation de listes des sujets
	public Paragraph addListSujets(ArrayList<String> actions2) {
		int cont=1;
		Font font = new Font();
		Chunk underline = new Chunk("Action List : \n");
		underline.setUnderline(0.1f, -2f); //0.1 thick, -2 y-location
		Paragraph p = new Paragraph();
		p.add("\n");
		p.add(underline);
		p.add("\n");
		for (String item: actions2) {
			p.add(Integer.toString(cont)+". ");
			p.add(new Phrase(" " + item + "\n", font));
			cont++;
		}
		p.add("\n");
		return p;
	}
}