package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Dao.DbConnect;

public class Driver {

	private int driver_id = 0;

	/**
	 * @return the driver_id
	 */
	public int getDriver_id() {
		return driver_id;
	}

	/**
	 * @param driver_id the driver_id to set
	 */
	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the dOB
	 */
	public Date getDOB() {
		return DOB;
	}

	/**
	 * @param dOB the dOB to set
	 */
	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	/**
	 * @return the email_id
	 */
	public String getEmail_id() {
		return email_id;
	}

	/**
	 * @param email_id the email_id to set
	 */
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	/**
	 * @return the addr1
	 */
	public String getAddr1() {
		return addr1;
	}

	/**
	 * @param addr1 the addr1 to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	/**
	 * @return the addr2
	 */
	public String getAddr2() {
		return addr2;
	}

	/**
	 * @param addr2 the addr2 to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(int zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone_no
	 */
	public String getPhone_no() {
		return Phone_no;
	}

	/**
	 * @param phone_no the phone_no to set
	 */
	public void setPhone_no(String phone_no) {
		Phone_no = phone_no;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	private String first_name = "";
	private String last_name = "";
	private Date DOB = new Date();

	private String email_id = "";
	private String addr1 = "";
	private String addr2 = "";
	private String city = "";
	private String state = "";
	private int zip = 00000;
	private String Phone_no = "";

	private Car car;

	private int priceSixMonths = 0;

	private Date dateCreated = new Date();

	public Driver(String firstName, String LastName, String Email, Integer Zip) {
		this.first_name = firstName;
		this.last_name = LastName;
		this.email_id = Email;
		this.zip = Zip;
	}

	public Driver() {
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void saveDriverAndPolicyToDatabase() throws SQLException {
		String policySql = "INSERT INTO 510fp.shivani_shah_autopolicy (`CarBrand`,`CarModel`,`CarYear`,`CarMilage`,`PolicySixMonthPrice`) VALUES (?,?,?,?,?)";

		String driverSql = "INSERT INTO `510fp`.`shivani_shah_driverinfo` (`FirstName`,`LastName`,`DOB`,`Email`,`Addr1`,`Addr2`,`City`,`State`,`Zip`,`PhoneNumber`,`PolicyId`) VALUES(?,?,?,?,?,?,?,?,?,?,(SELECT PolicyId FROM `510fp`.`shivani_shah_autopolicy` ORDER BY PolicyId DESC LIMIT 1))";

		DbConnect conn = new DbConnect();

		try {
			// Save Policy
			PreparedStatement preparedStatement = conn.prepareStatement(policySql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, this.car.getBrand());
			preparedStatement.setString(2, this.car.getModelName());
			preparedStatement.setInt(3, this.car.getModelYear());
			preparedStatement.setInt(4, this.car.getMilage());
			preparedStatement.setInt(5, this.priceSixMonths);

			preparedStatement.executeUpdate();

			// Save Driver
			PreparedStatement selectPS = conn.prepareStatement(driverSql);

			selectPS.setString(1, this.getFirst_name());
			selectPS.setString(2, this.getLast_name());

			java.sql.Date sqlDate = new java.sql.Date(this.getDOB().getDate());

			selectPS.setDate(3, sqlDate);
			selectPS.setString(4, this.getEmail_id());
			selectPS.setString(5, this.getAddr1());
			selectPS.setString(6, this.getAddr2());
			selectPS.setString(7, this.getCity());
			selectPS.setString(8, this.getState());
			selectPS.setInt(9, this.getZip());
			selectPS.setString(10, this.getPhone_no());

			selectPS.executeUpdate();

			String a = "Test";
		} catch (SQLException ex) {

			throw ex;
		}

		// ((java.sql.Statement) stmt).executeUpdate(sql);

		// System.out.println(rowsAffected + "row added");
	}

	public int getPriceSixMonths() {
		return priceSixMonths;
	}

	public void setPriceSixMonths(int priceSixMonths) {
		this.priceSixMonths = priceSixMonths;
	}

}
