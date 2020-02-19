package com.administrator.model;

import java.util.List;

public class AdministratorService {
	
	private AdministratorDAO_interface dao;
	
	public AdministratorService() {
		dao = new AdministratorDAO();
	}
	
	public AdministratorVO addAdmin(String adminName, String e_mail,
			String adminPassWd) {
		
		AdministratorVO administratorVO = new AdministratorVO();
		
		administratorVO.setAdminName(adminName);
		administratorVO.setE_mail(e_mail);
		administratorVO.setAdminPassWd(adminPassWd);
//		administratorVO.setAdminStatus(adminStatus);
		dao.insert(administratorVO);
		
		return administratorVO;
	}
	
	public AdministratorVO updateAdmin(String adminNo, String adminName, String e_mail,
			String adminPassWd) {
		
		AdministratorVO administratorVO = new AdministratorVO();
		
		administratorVO.setAdminNo(adminNo);
		administratorVO.setAdminName(adminName);
		administratorVO.setE_mail(e_mail);
		administratorVO.setAdminPassWd(adminPassWd);
//		administratorVO.setAdminStatus(adminStatus);
		dao.update(administratorVO);
		
		return administratorVO;
	}
	
	public void deleteAdmin(String adminNo) {
		dao.delete(adminNo);
	}
	
	public AdministratorVO getOneAdmin(String adminNo) {
		return dao.findByPrimaryKey(adminNo);
	}
	
	public AdministratorVO getEmail(String e_mail) {
		return dao.getEmail(e_mail);
	}
	
	public List<AdministratorVO> getAll(){
		return dao.getAll();
	}
}
