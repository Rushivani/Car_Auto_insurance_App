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

public class DriverModel {
	
	

	private static int driver_id = 0;
	/**
	 * @return the driver_id
	 */
	public static int getDriver_id() {
		return driver_id;
	}

	/**
	 * @param driver_id the driver_id to set
	 */
	public static void setDriver_id(int driver_id) {
		DriverModel.driver_id = driver_id;
	}

	/**
	 * @return the first_name
	 */
	public static String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public static void setFirst_name(String first_name) {
		DriverModel.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public static String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public static void setLast_name(String last_name) {
		DriverModel.last_name = last_name;
	}

	/**
	 * @return the dOB
	 */
	public static Date getDOB() {
		return DOB;
	}

	/**
	 * @param dOB the dOB to set
	 */
	public static void setDOB(Date dOB) {
		DOB = dOB;
	}

	/**
	 * @return the email_id
	 */
	public static String getEmail_id() {
		return email_id;
	}

	/**
	 * @param email_id the email_id to set
	 */
	public static void setEmail_id(String email_id) {
		DriverModel.email_id = email_id;
	}

	/**
	 * @return the addr1
	 */
	public static String getAddr1() {
		return addr1;
	}

	/**
	 * @param addr1 the addr1 to set
	 */
	public static void setAddr1(String addr1) {
		DriverModel.addr1 = addr1;
	}

	/**
	 * @return the addr2
	 */
	public static String getAddr2() {
		return addr2;
	}

	/**
	 * @param addr2 the addr2 to set
	 */
	public static void setAddr2(String addr2) {
		DriverModel.addr2 = addr2;
	}

	/**
	 * @return the city
	 */
	public static String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public static void setCity(String city) {
		DriverModel.city = city;
	}

	/**
	 * @return the state
	 */
	public static String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public static void setState(String state) {
		DriverModel.state = state;
	}

	/**
	 * @return the zip
	 */
	public static int getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public static void setZip(int zip) {
		DriverModel.zip = zip;
	}

	/**
	 * @return the phone_no
	 */
	public static String getPhone_no() {
		return Phone_no;
	}

	/**
	 * @param phone_no the phone_no to set
	 */
	public static void setPhone_no(String phone_no) {
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

	private static String first_name = "";
	private static String last_name = "";
	private static Date DOB;	
	
	private static String email_id = "";
	private static String addr1 = "";
	private static String addr2 = "";
	private static String city = "";
	private static String state = "";
	private static int zip = 00000;
	private static String Phone_no = "";	
	
	private static Car car;
	
	private static int priceSixMonths = 0;
	
	
	private Date dateCreated = new Date();
	
	public DriverModel(String firstName, String LastName, String Email, Integer Zip) {
		this.first_name = firstName;
		this.last_name = LastName;
		this.email_id = Email;
		this.zip = Zip;
	}
	
	public DriverModel() {}

	public static Car getCar() {
		return car;
	}

	public static void setCar(Car car) {
		DriverModel.car = car;
	}
	
	
	public void saveDriverAndPolicyToDatabase() throws SQLException {
		String policySql = "INSERT INTO 510fp.shivani_shah_autopolicy(`PolicyId`,`CarBrand`,`CarModel`,`CarYear`,`CarMilage`,`PolicySixMonthPrice`) VALUES (?,?,?,?,?,?)";
				
		String driverSql = "INSERT INTO `510fp`.`shivani_shah_driverinfo`(`DriverId`,`FirstName`,`LastName`,`DOB`,`Email`,`Addr1`,`Addr2`,`City`,`State`,`Zip`,`PhoneNumber`,`PolicyId`) VALUES(?,?,?,?,?,?,?,?,?,?,?,(SELECT PolicyId FROM `510fp`.`shivani_shah_autopolicy` ORDER BY PolicyId DESC LIMIT 1))";

		DbConnect conn = new DbConnect();
		
		
		try {
			//Save Policy
			PreparedStatement preparedStatement = conn.prepareStatement(policySql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, (int) (Math.random() * 1000));
			preparedStatement.setString(2, this.car.getBrand());
			preparedStatement.setString(3, this.car.getModelName());
			preparedStatement.setInt(4, this.car.getModelYear());
			preparedStatement.setInt(5, this.car.getMilage());
			preparedStatement.setInt(6, this.priceSixMonths);
			
			preparedStatement.executeUpdate();
						
			//Save Driver
			PreparedStatement selectPS = conn.prepareStatement(driverSql);

			selectPS.setInt(1,(int)(1000*Math.random()));
			selectPS.setString(2, this.getFirst_name());
			selectPS.setString(3, this.getLast_name());
			
			java.sql.Date sqlDate = new java.sql.Date(this.getDOB().getTime());
			
			selectPS.setDate(4, sqlDate);
			selectPS.setString(5, this.getEmail_id());
			selectPS.setString(6, this.getAddr1());
			selectPS.setString(7, this.getAddr2());
			selectPS.setString(8, this.getCity());
			selectPS.setString(9, this.getState());
			selectPS.setInt(10, this.getZip());
			selectPS.setString(11, this.getPhone_no());
			
			selectPS.executeUpdate();	
			
			String a = "Test";
		}
		catch(SQLException ex){
			
			
			throw ex;
		}

		
		// ((java.sql.Statement) stmt).executeUpdate(sql);

		// System.out.println(rowsAffected + "row added");
	}

	public static int getPriceSixMonths() {
		return priceSixMonths;
	}

	public static void setPriceSixMonths(int priceSixMonths) {
		DriverModel.priceSixMonths = priceSixMonths;
	}
	

}