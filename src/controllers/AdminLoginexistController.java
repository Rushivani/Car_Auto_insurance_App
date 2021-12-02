package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminLoginexistController implements Initializable { 

    @FXML
    private Button btnhaveaccount;

    @FXML
    private Button btnsignup;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

    @FXML
    void btnback_click(ActionEvent event) throws IOException {
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

    }
    
    @FXML
    public void btnhaveaccount_click(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/views/Adminlogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Car Insurance Application !!");
		primaryStage.show();
		
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
    }

    @FXML
    public void btnsignup_click(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/views/AdminRegistration.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Car Insurance Application !!");
		primaryStage.show();
		
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
    }

}