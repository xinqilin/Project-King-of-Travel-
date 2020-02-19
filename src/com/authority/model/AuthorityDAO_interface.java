package com.authority.model;

import java.util.*;

public interface AuthorityDAO_interface {
	public void insert(AuthorityVO authorityVO);
	public void update(AuthorityVO authorityVO);
	public void delete(String adminNo, String funcNo);
	public AuthorityVO findByPrimaryKey(String adminNo, String funcNo);
	public List<AuthorityVO> getAll();
	
}
