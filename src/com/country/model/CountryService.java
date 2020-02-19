package com.country.model;

import java.util.ArrayList;
import java.util.List;

public class CountryService {
	private CountryDAO dao=null;
	public CountryService() {
		dao= new CountryDAO();
	}
	
	public CountryVO insertCountry(String CountryName) {
		CountryVO country=new CountryVO();
		country.setCountryName(CountryName);
		dao.insert(country);
		return country;
	}
	
	public void deleteCountry(String countryNo) {
		dao.delete(countryNo);
	}
	
	public CountryVO updateCountry(String countryNo, String countryName) {
		CountryVO country = new CountryVO();
		country.setCountryNo(countryNo);
		country.setCountryName(countryName);
		dao.update(country);
		return country;
	}
	
	public CountryVO getOne(String CountryNo) {
		CountryVO country = dao.findByPrimaryKey(CountryNo);
		return country; 		
	}
	
	public List<CountryVO> getAll(){
		List<CountryVO> countries=new ArrayList<CountryVO>();
		countries = dao.getAll();
		return countries;
	}
	
	
	
}
