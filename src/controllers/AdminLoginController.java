package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.AdminLoginModel;

public class AdminLoginController implements Initializable {

	@FXML
	private TextField txtUserName;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button btnLogin;
	

    @FXML
    void btnback_click(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/views/AdminLoginexist.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Car Insurance Application !!");
		primaryStage.show();
		
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
    }

	@FXML
	public void btnLogin_click(ActionEvent event) throws IOException, SQLException {

		AdminLoginModel adminloginModel = new AdminLoginModel();
		System.out.println(txtUserName.getText() + txtPassword.getText());
		boolean isLogin = adminloginModel.isLogin(txtUserName.getText(), txtPassword.getText());

		if (isLogin) {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/views/Admin.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Car Insurance Application !!");
			primaryStage.show();
		} else {

			Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("Message"); //
			alert.setHeaderText("Sorry Credential Wrong!!"); //
			alert.setContentText("The Username and password you have provided is Wrong !");
			alert.showAndWait();
//    		Node  source = (Node)  event.getSource(); 
//    	    Stage stage  = (Stage) source.getScene().getWindow();
//    	    stage.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
