package com.funcList.model;

import java.util.*;

public interface FuncListDAO_interface {
	public void insert(FuncListVO funcListVO);
	public void update(FuncListVO funcListVO);
	public void delete(String funcNo);
	public FuncListVO findByPrimaryKey(String funcNo);
	public List<FuncListVO> getAll();
	
}
