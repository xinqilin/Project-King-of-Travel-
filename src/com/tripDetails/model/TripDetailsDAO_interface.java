package com.tripDetails.model;

import java.util.*;

import com.tripDetails.model.TripDetailsVO;

public interface TripDetailsDAO_interface {
          public void insert(TripDetailsVO tripDetailsVO);
          public void update(TripDetailsVO tripDetailsVO);
          public void delete(String tripno,String spotno);
          public TripDetailsVO findByPrimaryKey(String tripno,String spotno);
          public List<TripDetailsVO> getAll();
          public List<TripDetailsVO> findByTripno(String tripno);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<TripVO> getAll(Map<String, String[]> map);  
//          String tripno,String spotno
}
