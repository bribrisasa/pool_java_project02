package GUI;

import java.util.ArrayList;

public class FilePdf {
String titre;
String location;
String date;
String duration;

ArrayList<String> attendance;
ArrayList<String> actions;

String summary;

public FilePdf(String titre, String location, String date, String duration, ArrayList<String> attendance,
		ArrayList<String> actions, String summary) {
	this.titre = titre;
	this.location = location;
	this.date = date;
	this.duration = duration;
	this.attendance = attendance;
	this.actions = actions;
	this.summary = summary;
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


}
