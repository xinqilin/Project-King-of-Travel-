package com.Android.mem.model;


public class MemService {
	
	private MemDAO_interface dao;
	
	public MemService() {
		dao = new MemJNDIDAO();
	}
	
	public MemVO addMem(String memName, String e_mail, String memPassWd, String nickName, String idNo, java.sql.Date birDay, String introduction, String address, String phone) {
		MemVO memVO = new MemVO();
		
		memVO.setMemName(memName);
		memVO.setE_mail(e_mail);
		memVO.setMemPassWd(memPassWd);
		memVO.setNickName(nickName);
		memVO.setIdNo(idNo);
		memVO.setBirDay(birDay);
		memVO.setIntroduction(introduction);
		memVO.setAddr(address);
		memVO.setPhone(phone);
		dao.insert(memVO);
		
		return memVO;
	}
	
	public MemVO updateMem(String e_mail, String nickName, String introduction) {
		
		MemVO memVO = new MemVO();
		memVO.setE_mail(e_mail);
		memVO.setNickName(nickName);
		memVO.setIntroduction(introduction);
		dao.update(memVO);
		return memVO;
	}
	
	public MemVO getMemByEmail(String email) {
		MemVO memVO = dao.findByEmail(email); //藉由 email 撈出使用者VO
		if(memVO != null) {
			String memNo = memVO.getMemNo();
			String tripCount = dao.findTripCountByMemNo(memNo);
			memVO.setTripCount(tripCount);
			String articleCount = dao.findArticleCountByMemNo(memNo);
			memVO.setArticleCount(articleCount);
		}
		return memVO;
	}
	
	
	public int isMember(String email, String password) {
		int i = 0;
		MemVO memVO = dao.findByEmail(email);
		if(memVO == null) {
			i = 1; // i = 1,  無此帳號
		}else if(!memVO.getMemPassWd().equals(password)) {
			i = 2; // i = 2,  密碼錯誤 
		}else {
			i = 3; // i = 3,  密碼正確
		}		
		return i;
	}
	
	
	public boolean isIdRegistered(String idNo) {
		
		boolean result = dao.isIdRegistered(idNo);

		return result;		
	}

}
