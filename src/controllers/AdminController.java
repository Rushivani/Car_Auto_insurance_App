package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Dao.DbConnect;

import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Car;
import models.Driver;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

public class AdminController implements Initializable {

	@FXML
	private Button btnLOGOUT;

	@FXML
	private TableView DriverTable;

	ObservableList<Driver> oblist = FXCollections.observableArrayList();

	@FXML
	public void btnLOGOUT_click(ActionEvent event) throws IOException {
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

	public void initialize(URL location, ResourceBundle resources) {
		DriverTable.setEditable(true);

		TableColumn firstNameCol = new TableColumn("FirstName");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("first_name"));

		TableColumn lastNameCol = new TableColumn("LastName");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("last_name"));

		TableColumn emailCol = new TableColumn("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("email_id"));

		TableColumn zipCol = new TableColumn("Zip");
		zipCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("zip"));

		TableColumn DOBCol = new TableColumn("DOB");
		DOBCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("DOB"));

		TableColumn addr1Col = new TableColumn("Addr1");
		addr1Col.setCellValueFactory(new PropertyValueFactory<Driver, String>("addr1"));

		TableColumn addr2Col = new TableColumn("Addr2");
		addr1Col.setCellValueFactory(new PropertyValueFactory<Driver, String>("addr2"));

		TableColumn stateCol = new TableColumn("State");
		stateCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("state"));

		TableColumn cityCol = new TableColumn("City");
		cityCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("city"));

		TableColumn PhonenoCol = new TableColumn("PhoneNumber");
		PhonenoCol.setCellValueFactory(new PropertyValueFactory<Driver, String>("Phone_no"));

		TableColumn<Driver, String> carbrandCol = new TableColumn("CarBrand");
		carbrandCol.setCellValueFactory(param -> {
			if (param.getValue() != null) {
				return new SimpleStringProperty(param.getValue().getCar().getBrand());
			} else {
				return new SimpleStringProperty("");
			}
		});

		TableColumn<Driver, String> carmodelCol = new TableColumn("CarModel");
		carmodelCol.setCellValueFactory(param -> {
			if (param.getValue() != null) {
				return new SimpleStringProperty(param.getValue().getCar().getModelName());
			} else {
				return new SimpleStringProperty("");
			}
		});

		TableColumn<Driver, String> caryearCol = new TableColumn("CarYear");
		caryearCol.setCellValueFactory(param -> {
			if (param.getValue() != null) {
				return new SimpleStringProperty(String.valueOf(param.getValue().getCar().getModelYear()));
			} else {
				return new SimpleStringProperty("");
			}
		});

		TableColumn<Driver, String> carmilageCol = new TableColumn("CarMilage");
		carmilageCol.setCellValueFactory(param -> {
			if (param.getValue() != null) {
				return new SimpleStringProperty(String.valueOf(param.getValue().getCar().getMilage()));
			} else {
				return new SimpleStringProperty("");
			}
		});

		TableColumn<Driver, String> pricesixmonthCol = new TableColumn("PriceSixMonthPrice");
		pricesixmonthCol.setCellValueFactory(param -> {
			if (param.getValue() != null) {
				return new SimpleStringProperty("$" + String.valueOf(param.getValue().getPriceSixMonths()));
			} else {
				return new SimpleStringProperty("");
			}
		});

		DbConnect conn = new DbConnect();
		ResultSet res = null;
		String selectDataQuery = "SELECT d.FirstName, d.LastName, d.DOB, d.Email, d.Addr1, d.Addr2, d.City, d.State, d.Zip, d.PhoneNumber, d.PolicyId, a.CarBrand, a.CarModel, a.CarYear, a.CarMilage, a.PolicySixMonthPrice FROM 510fp.shivani_shah_driverinfo AS d JOIN 510fp.shivani_shah_autopolicy AS a ON d.PolicyId = a.PolicyId";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.connect().prepareStatement(selectDataQuery);
			res = preparedStatement.executeQuery();

			while (res.next()) {

				Driver dr = new Driver();
				String FirstName = res.getString("FirstName");
				String LastName = res.getString("LastName");
				String Email = res.getString("Email");
				Integer zip = res.getInt("Zip");
				Date DOB = res.getDate("DOB");
				String addr1 = res.getString("Addr1");
				String addr2 = res.getString("Addr2");
				String state = res.getString("State");
				String city = res.getString("City");
				String Phoneno = res.getString("PhoneNumber");
				String carbrand = res.getString("CarBrand");
				String carmodel = res.getString("CarModel");
				Integer caryear = res.getInt("CarYear");
				Integer carmilage = res.getInt("CarMilage");
				Integer pricesixmonth = res.getInt("PolicySixMonthPrice");				

				dr.setFirst_name(FirstName);
				dr.setLast_name(LastName);
				dr.setEmail_id(Email);
				dr.setZip(zip);
				dr.setDOB(DOB);
				dr.setEmail_id(Email);
				dr.setAddr1(addr1);
				dr.setAddr2(addr2);
				dr.setCity(city);
				dr.setState(state);

				dr.setPhone_no(Phoneno);
				dr.setPriceSixMonths(pricesixmonth);

				Car cr = new Car();

				cr.setBrand(carbrand);
				cr.setModelName(carmodel);
				cr.setModelYear(caryear);
				cr.setMilage(carmilage);
				dr.setCar(cr);

				oblist.add(dr);

				// driverList.add(dr);

			}

// 			ArrayList<Driver> driverList1 = driverList;
// 			
// 			for(Integer i = 0; i <driverList.size(); i++) {
// 				System.out.println(driverList.get(i).getFirst_name());
// 			}
			DriverTable.getColumns().clear();
			DriverTable.getColumns().addAll(firstNameCol, lastNameCol, pricesixmonthCol, carmodelCol, caryearCol,
					emailCol, DOBCol, addr1Col, addr2Col, cityCol, stateCol, zipCol, PhonenoCol, carbrandCol,
					carmilageCol);
			DriverTable.setItems(oblist);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
