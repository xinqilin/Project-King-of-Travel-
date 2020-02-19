package com.country.model;

import java.util.*;

public interface CountryDAO_Interface {
	public void insert(CountryVO country);
	public void delete(String countryNo);
	public void update(CountryVO country);
	public CountryVO findByPrimaryKey(String countryNo);
	public List<CountryVO> getAll();
}
