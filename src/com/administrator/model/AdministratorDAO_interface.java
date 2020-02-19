package com.administrator.model;

import java.util.*;

public interface AdministratorDAO_interface {
	public void insert(AdministratorVO administratorVO);
	public void update(AdministratorVO administratorVO);
	public void delete(String admminNo);
	public AdministratorVO findByPrimaryKey(String admminNo);
	public List<AdministratorVO> getAll();
	public AdministratorVO getEmail(String e_mail);
}
