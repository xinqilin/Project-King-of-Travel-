package com.wishitem.model;

import java.util.*;
public interface WishItemDAO_interface {
	public void insert(WishItemVO wishItemVO);
	public void update(WishItemVO wishItemVO);
	public void delete(String wishItemNo);
	public WishItemVO findByPrimaryKey(String wishItemNo);
	public List<WishItemVO> getAll();
}
