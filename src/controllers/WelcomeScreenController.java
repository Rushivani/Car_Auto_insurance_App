package controllers;

import java.net.URL;
import java.util.ResourceBundle;


//import controller.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.*;
import java.io.*;

public class WelcomeScreenController implements Initializable {
	
	@FXML
	private Button btn_getstared;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void getstarted(ActionEvent event) throws IOException{
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/views/FirstScreen.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Car Insurance Application !!");
		primaryStage.show();
		
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
		
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Information Dialog");
//		alert.setHeaderText("Look, an Information Dialog");
//		alert.setContentText("I have a great message for you!");
//
//		alert.showAndWait();
	}
}

