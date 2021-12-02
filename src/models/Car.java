package models;

import java.util.Date;

public class Car {
	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the marketPrice
	 */
	public double getMarketPrice() {
		return marketPrice;
	}

	/**
	 * @param marketPrice the marketPrice to set
	 */
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	/**
	 * @return the modelYear
	 */
	public int getModelYear() {
		return modelYear;
	}

	/**
	 * @param modelYear the modelYear to set
	 */
	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	/**
	 * @return the annualInsurancePercent
	 */
	public double getAnnualInsurancePercent() {
		return annualInsurancePercent;
	}

	/**
	 * @param annualInsurancePercent the annualInsurancePercent to set
	 */
	public void setAnnualInsurancePercent(double annualInsurancePercent) {
		this.annualInsurancePercent = annualInsurancePercent;
	}

	/**
	 * @return the milage
	 */
	public int getMilage() {
		return milage;
	}

	/**
	 * @param milage the milage to set
	 */
	public void setMilage(int milage) {
		this.milage = milage;
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

	private String modelName = "";
	private String brand = "";
	private double marketPrice = 0.0;
	private int modelYear = 0;
	private double annualInsurancePercent = 0.0;
	private int milage = 0;
	private Date dateCreated;

}
