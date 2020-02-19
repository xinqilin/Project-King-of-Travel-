package com.spot.model;

import java.util.*;

public interface SpotListDAO_interface {
	 public void insert(SpotListVO spotListVO);
     public void update(SpotListVO spotListVO);
     public void delete(String spotNo);
     public SpotListVO findByPrimaryKey(String spotNo);
     public List<SpotListVO> getAll();
     public List<SpotListVO> getAllNoPic();
}
