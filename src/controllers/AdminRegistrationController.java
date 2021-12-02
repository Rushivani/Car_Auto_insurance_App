package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Dao.DbConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.HashPassword;

public class AdminRegistrationController implements Initializable {

    @FXML
    private Button btnsubmit;
    
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;
    
    
    
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
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

    @FXML
     public void btnsubmit_click(ActionEvent event) throws Exception {
    	
    	DbConnect conn = new DbConnect();
    	
    	String signUpAdminScript = "INSERT INTO 510fp.shivani_shah_adminlogin(`AdminId`,`username`,`password`,`Name`) VALUES(?,?,?,?)";
    	PreparedStatement preparedStatement = conn.prepareStatement(signUpAdminScript);

    	String hashedPassword = HashPassword.getHashedPasswordString(txtPassword.getText());   	
    	
    	preparedStatement.setInt(1, (int)(Math.random()*10000));
		preparedStatement.setString(2, txtUserName.getText());
		preparedStatement.setString(3, hashedPassword);
		preparedStatement.setString(4, txtName.getText());
		
		preparedStatement.executeUpdate();
    	
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
}
