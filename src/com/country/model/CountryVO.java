package com.country.model;

import java.io.Serializable;

public class CountryVO implements Serializable {
	private String countryNo;
	private String countryName;
	public CountryVO() {
		super();
	}
	public CountryVO(String countryNo, String countryName) {
		super();
		this.countryNo = countryNo;
		this.countryName = countryName;
	}
	public String getCountryNo() {
		return countryNo;
	}
	public void setCountryNo(String countryNo) {
		this.countryNo = countryNo;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
