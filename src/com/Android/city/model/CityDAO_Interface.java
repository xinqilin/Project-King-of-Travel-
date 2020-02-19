package com.Android.city.model;

import java.util.*;

import com.Android.city.model.CityVO;

public interface CityDAO_Interface {
	public void insert(CityVO city);
	public void delete(String cityNo);
	public void update(CityVO city);
	public CityVO findByPrimaryKey(String cityNo);
	public List<CityVO> getAll();
	public List<CityVO> getCities(String cityNo);
}
