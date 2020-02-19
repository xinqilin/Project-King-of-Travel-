package com.Android.city.model;

import java.util.*;

public class CityService {
	CityDAO_Interface dao;
	public CityService() {
		dao = new CityJNDIDAO();
	}
	
	public CityVO insert(String CityName, String CountryNo) {
		CityVO city = new CityVO();	
		city.setCityNo(CityName);
		city.setCountryNo(CountryNo);
		dao.insert(city);
		return city;
	}
	
	public void delete(String CityNo) {dao.delete(CityNo);}
	
	public CityVO update(String CityNo, String CityName, String CountryNo) {
		CityVO city = new CityVO();
		city.setCityNo(CityNo);
		city.setCityNo(CityName);
		city.setCountryNo(CountryNo);
		dao.update(city);
		return city;
	}
	
	public CityVO getOne(String CityNo){
		CityVO city = dao.findByPrimaryKey(CityNo);
		return city;
	}
	
	public List<CityVO> getAll(){
		List<CityVO> cities=new ArrayList<CityVO>();
		cities = dao.getAll();
		return cities;
	}
	
	public List<CityVO> get_ClosedCities(String cityNo){
		List<CityVO> cities=new ArrayList<CityVO>();
		cities = dao.getCities(cityNo);
		return cities;
	}
	
}
