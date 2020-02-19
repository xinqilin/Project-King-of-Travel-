package com.trip.model;

import java.util.*;

public interface TripDAO_interface {
          public void insert(TripVO TripVO);
          public void update(TripVO TripVO);
          public void delete(String Tripno);
          public void accept(String Tripno);
          public TripVO findByPrimaryKey(String Tripno);
          public List<TripVO> getAll();
          public List<TripVO> orderByDate();
          public List<TripVO> orderByViews();
          public List<TripVO> orderByCity();
          public List<TripVO> orderByDays();
          public TripVO lastTrip();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map);  
}
