package com.funcList.model;

import java.util.List;

public class FuncListService {
	
	private FuncListDAO_interface dao;
	
	public FuncListService() {
		dao = new FuncListDAO();
	}
	
	public FuncListVO addFunc(String funcName) {
		
		FuncListVO funcListVO = new FuncListVO();
		
		funcListVO.setFuncName(funcName);
		dao.insert(funcListVO);
		
		return funcListVO;
	}
	
	public FuncListVO updateFunc(String funcNo, String funcName) {
		
		FuncListVO funcListVO = new FuncListVO();
		
		funcListVO.setFuncNo(funcNo);
		funcListVO.setFuncName(funcName);
		dao.update(funcListVO);
		
		return funcListVO;
	}
	
	public void deleteFunc(String funcNo) {
		dao.delete(funcNo);
	}
	
	public FuncListVO getOneFunc(String funcNo){
		return dao.findByPrimaryKey(funcNo);
	}
	
	public List<FuncListVO> getAll(){
		return dao.getAll();
	}
	
}
