package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Car;
import models.Driver;
import models.DriverModel;

public class GetAQuoteController implements Initializable {

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtAddress1;

	@FXML
	private TextField txtAddress2;

	@FXML
	private ChoiceBox<String> drpdwnModel;

	@FXML
	private ChoiceBox<String> drpdwnBrand;

	@FXML
	private Button btnGetAQuote;

	@FXML
	private DatePicker pickerDOB;

	@FXML
	private TextField txtCity;

	@FXML
	private TextField txtState;

	@FXML
	private TextField txtZip;

	@FXML
	private TextField txtPhone;

	@FXML
	private ChoiceBox<Integer> drpdwnYear;

	@FXML
	private TextField txtMilage;

	@FXML
	void btnback_click(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/views/FirstScreen.fxml"));
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
	public void btnGetAQuote_Click(ActionEvent event) throws IOException {

		DriverModel driver = new DriverModel();
		driver.setFirst_name(txtFirstName.getText());
		driver.setLast_name(txtLastName.getText());

		LocalDate pickerDt = pickerDOB.getValue();

		driver.setDOB(ToDate(pickerDt));
		driver.setEmail_id(txtEmail.getText());
		driver.setAddr1(txtAddress1.getText());
		driver.setAddr2(txtAddress2.getText());
		driver.setCity(txtCity.getText());
		driver.setState(txtState.getText());
		driver.setZip(Integer.parseInt(txtZip.getText()));
		driver.setPhone_no(txtPhone.getText());

		Car driverCar = new Car();
		driverCar.setBrand(drpdwnBrand.getValue());
		driverCar.setModelName(drpdwnModel.getValue());
		driverCar.setModelYear(drpdwnYear.getValue());
		driverCar.setMilage(Integer.parseInt(txtMilage.getText()));

		driver.setCar(driverCar);

		Stage primaryStage = new Stage();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/BuyQuote.fxml"));

		Parent root = (Parent) fxmlLoader.load();
		BuyQuoteController controller = fxmlLoader.<BuyQuoteController>getController();
		controller.setDriver(driver);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Car Insurance Application !!");

		primaryStage.show();

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

//		Parent root = FXMLLoader.load(getClass().getResource("/views/BuyQuote.fxml"));
//		BuyQuoteController bqController = new BuyQuoteController();
//		Scene scene = new Scene(root);
//		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setScene(scene);
//		primaryStage.show();

//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Information Dialog");
//		alert.setHeaderText("Look, an Information Dialog");
//		alert.setContentText("Here is the date YEAH : " + driver.getDOB() + " " + driver.getLast_name());
//
//		alert.showAndWait();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		drpdwnModel.getItems().removeAll(drpdwnModel.getItems());
		drpdwnModel.getItems().addAll("Prius", "Accord", "Pilot", "Sienna", "Camery");
		drpdwnModel.getSelectionModel().select("Accord");

		drpdwnBrand.getItems().removeAll(drpdwnBrand.getItems());
		drpdwnBrand.getItems().addAll("Toyota", "Honda");
		drpdwnBrand.getSelectionModel().select("Toyota");

		drpdwnYear.getItems().removeAll(drpdwnYear.getItems());
		for (Integer i = 2010; i < 2021; i++) {
			drpdwnYear.getItems().add(i);
		}
		drpdwnYear.getSelectionModel().select(0);

	}

	private Date ToDate(LocalDate date) {
		ZoneId defaultZoneId = ZoneId.systemDefault();

		return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
	}
}
