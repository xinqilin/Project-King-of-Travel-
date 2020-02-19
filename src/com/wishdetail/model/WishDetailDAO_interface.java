package com.wishdetail.model;

import java.sql.Connection;
import java.util.*;

import com.wishitem.model.*;
public interface WishDetailDAO_interface {
	public void insert(WishItemVO wishItemVO,Connection con);
	public void update(WishDetailVO wishDetailVO);
	public void delete(String wishItemNo);
	public WishDetailVO findByPrimaryKey(String wishItemNo,String wishOrdNo);
	public List<WishDetailVO> getAll();
}
