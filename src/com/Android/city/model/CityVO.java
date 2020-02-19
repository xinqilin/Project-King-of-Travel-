package com.Android.city.model;

import java.io.Serializable;

public class CityVO implements Serializable {
	private String CityNo;
	private String CityName;
	private String CountryNo;
	public CityVO() {
		super();
	}
	public CityVO(String cityNo, String cityName, String countryNo) {
		super();
		CityNo = cityNo;
		CityName = cityName;
		CountryNo = countryNo;
	}
	public String getCityNo() {
		return CityNo;
	}
	public void setCityNo(String cityNo) {
		CityNo = cityNo;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getCountryNo() {
		return CountryNo;
	}
	public void setCountryNo(String countryNo) {
		CountryNo = countryNo;
	}
	
}
