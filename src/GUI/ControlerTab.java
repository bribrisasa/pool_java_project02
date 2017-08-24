package GUI;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.DocumentException;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ControlerTab {
	ListCollab list = new ListCollab("src/GUI/pers.csv");
	ListCollab listAdded = new ListCollab();
ArrayList<String> actions = new ArrayList<String>();


protected ListProperty<String> listColl = new SimpleListProperty<String>();
protected ListProperty<String> listCollS = new SimpleListProperty<String>();
protected ListProperty<String> listAct = new SimpleListProperty<String>();


@FXML ListView<String> listCollab;
@FXML ListView<String> listAddedCollab;
@FXML ListView<String> listAction;
@FXML TextField action;
@FXML TextField titleReport;
@FXML TextField locationReport;
@FXML DatePicker dateMeeting;
@FXML TextField durationReport;
@FXML TextArea summaryReport;

@FXML
public void chargeCollab() {
	listColl.set(FXCollections.observableArrayList(list.getCollab()));
	listCollab.itemsProperty().bind(listColl);
}


@FXML
public void addCollab() {
	listAdded.add((listCollab.getSelectionModel().getSelectedItem()));
	listCollS.set(FXCollections.observableArrayList(listAdded.getCollab()));
	listAddedCollab.itemsProperty().bind(listCollS);
	
	list.remove(listCollab.getSelectionModel().getSelectedItem());
	listColl.set(FXCollections.observableArrayList(list.getCollab()));
	listCollab.itemsProperty().bind(listColl);

}
@FXML
public void removeCollab() {
	list.add(listAddedCollab.getSelectionModel().getSelectedItem());
	listColl.set(FXCollections.observableArrayList(list.getCollab()));
	listCollab.itemsProperty().bind(listColl);
	
	listAdded.remove((listAddedCollab.getSelectionModel().getSelectedItem()));
	listCollS.set(FXCollections.observableArrayList(listAdded.getCollab()));
	listAddedCollab.itemsProperty().bind(listCollS);
}

@FXML
public void addAction() {
	actions.add(action.getText());
	
	listAct.set(FXCollections.observableArrayList(actions));
	listAction.itemsProperty().bind(listAct);
	action.setText("");
	
}

@FXML
public void removeAction() {
	actions.remove(listAction.getSelectionModel().getSelectedItem());
	
	listAct.set(FXCollections.observableArrayList(actions));
	listAction.itemsProperty().bind(listAct);
	
}


@FXML
public void generatePDF() {
	FilePdf file;
	try {
		file = new FilePdf(
				titleReport.getText(),
				locationReport.getText(),
				dateMeeting.getValue().toString(),
				durationReport.getText(),
				listAdded.getCollab(),
				actions,
				summaryReport.getText()	
				);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

@FXML
public void quitAppli() {
	System.exit(0);
}
}