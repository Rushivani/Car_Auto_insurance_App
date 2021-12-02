package controllers;

import java.awt.List;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Pair;
import models.Driver;
import models.DriverModel;

public class BuyQuoteController implements Initializable {

	@FXML
	private Label lblUserName;

	@FXML
	private Label lblQuotePrice;

	@FXML
	private Label lblQuotePrice1;

	@FXML
	private Button btnBUYQUOTE;

	@FXML
	public void btnBUYQUOTE_click(ActionEvent event) throws SQLException {
		
		driverObj.saveDriverAndPolicyToDatabase();		

		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setTitle("Message"); //
		alert.setHeaderText("Thank you!!"); //
		alert.setContentText("You successfully bought Quote ");
		alert.showAndWait();
		//alert.close();
		
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();

	}

	private DriverModel driverObj;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// lblUserName.setText(driverObj.getFirst_name() + " " +
		// driverObj.getLast_name());

		CalculateQuote();

	}

	private void CalculateQuote() {
		int preferencePoints = 0;

		TreeMap<Integer, Integer> yearBasedPoints = new TreeMap<Integer, Integer>();

		int cnt = 1;
		for (Integer i = 2010; i < 2021; i++) {
			yearBasedPoints.put(i, cnt);
			cnt++;
		}

		preferencePoints = preferencePoints + yearBasedPoints.get(driverObj.getCar().getModelYear());

		if (driverObj.getCar().getMilage() <= 5000) {
			preferencePoints = preferencePoints + 10;
		}

		if (driverObj.getCar().getMilage() <= 10000 && driverObj.getCar().getMilage() > 5000) {
			preferencePoints = preferencePoints + 9;
		}

		if (driverObj.getCar().getMilage() <= 15000 && driverObj.getCar().getMilage() > 10000) {
			preferencePoints = preferencePoints + 8;
		}

		if (driverObj.getCar().getMilage() <= 35000 && driverObj.getCar().getMilage() > 15000) {
			preferencePoints = preferencePoints + 7;
		}

		if (driverObj.getCar().getMilage() <= 80000 && driverObj.getCar().getMilage() > 35000) {
			preferencePoints = preferencePoints + 6;
		}

		if (driverObj.getCar().getMilage() > 80000) {
			preferencePoints = preferencePoints + 5;
		}

		Integer sixMonthPrice = preferencePoints * 50;
		Integer monthlyPrice = sixMonthPrice / 6;
		
		driverObj.setPriceSixMonths(sixMonthPrice);

		lblQuotePrice.setText("$" + Integer.toString(sixMonthPrice));

		lblQuotePrice1.setText("$" + Integer.toString(monthlyPrice));

		lblUserName.setText(driverObj.getFirst_name() + " " + driverObj.getLast_name());
	}

	public void setDriver(DriverModel driver) {
		driverObj = driver;
		// lblUserName.setText(driver.getFirst_name() + " " + driver.getLast_name());
	}

}
