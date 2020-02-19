package com.tripReport.model;

import java.util.*;

public interface TripReportDAO_interface {
          public void insert(TripReportVO tripReportVO);
          public void update(TripReportVO tripReportVO);
          public void delete(TripReportVO tripReportVO);
//          public TripReportVO findByPrimaryKey(String tripReportVO);
          public TripReportVO findByPrimaryKey(String tripno ,String memno);
          public List<TripReportVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<TripReportVO> getAll(Map<String, String[]> map);  
}
