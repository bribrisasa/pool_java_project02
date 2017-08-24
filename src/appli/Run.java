package appli;



import java.awt.TextField;
import java.io.*;
import java.time.LocalDate;

import javax.print.DocFlavor.URL;

import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Run extends Application {
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	static ListView<String> listCollab;
	@FXML TextField durationReport;
	protected static ListProperty<String> listColl = new SimpleListProperty<String>();
	
	  @Override
	  public void start(final Stage primaryStage) throws IOException {
		  Parent root;
			root = FXMLLoader.load(getClass().getResource("application.fxml"));
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY();
				}
			});
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					primaryStage.setX(event.getScreenX() - xOffset);
					primaryStage.setY(event.getScreenY() - yOffset);
				}
			});
			
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene(); 
			primaryStage.show();
			
	  }


	  public static void main(String[] args) {
	    launch(args);
	    
	  }
}
