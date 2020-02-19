package com.wishord.model;

import java.util.*;


public interface WishOrdDAO_interface {
	public void insert(WishOrdVO wishOrdVO);
	public void update(WishOrdVO wishOrdVO);
	public void delete(String wishOrdNo);
	public WishOrdVO findByPrimaryKey(String wishOrdNo);
	public List<WishOrdVO> getAll();
}