package com.mem.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class MemService {
	
	private MemDAO_interface dao;
	
	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO addMem(String memName, String e_mail, String memPassWd,
			byte[] memPhoto, String nickName, String idNo, java.sql.Date birDay,
			String address, String phone, String introduction) {
		
		MemVO memVO = new MemVO();
		
		memVO.setMemName(memName);
		memVO.setE_mail(e_mail);
		memVO.setMemPassWd(memPassWd);
		memVO.setMemPhoto(memPhoto);
		memVO.setNickName(nickName);
		memVO.setIdNo(idNo);
		memVO.setBirDay(birDay);
		memVO.setAddress(address);
		memVO.setPhone(phone);
		memVO.setIntroduction(introduction);
//		memVO.setStatus(status);
		dao.insert(memVO);
		
		return memVO;
	}
	
	public MemVO updateMem(String memNo, String memPassWd,
			String nickName, String address, String phone, String introduction) {
		
		MemVO memVO = new MemVO();
		
		memVO.setMemNo(memNo);
		memVO.setMemPassWd(memPassWd);
		memVO.setNickName(nickName);
		memVO.setAddress(address);
		memVO.setPhone(phone);
		memVO.setIntroduction(introduction);
		System.out.println("SERVICE的           memNO:"+memNo+"    memPassWd:"+memPassWd+
				"    nickName:"+nickName+"    addr:"+address+"    phone"+phone+"    introduction:"+introduction);

		dao.update(memVO);
		
		return memVO;
	}
	
	public String getCode(MemVO memVO) {
		String code = null;
		
		if(memVO != null) {
			code = dao.getUserCode(memVO);
		}
		
		return code;
	}
	
	
	public void deleteMem(String memNo) {
		dao.delete(memNo);
	}
	
	public MemVO getOneMem(String memNo) {
		return dao.findByPrimaryKey(memNo);
	}
	public MemVO getEmail(String e_mail) {
		return dao.getEmail(e_mail);
	}
	
	public List<MemVO> getAll(){
		return dao.getAll();
	}
	
	public MemVO getEmailConfirm(String e_mail) {
		return dao.getEmail(e_mail);
	}
	
	
	public boolean sendMailToMem(MemVO memVO, String code, HttpServletRequest req) {
		boolean result =false;
		StringBuffer sb = new StringBuffer();
		
		String to = memVO.getE_mail();
		String memName = memVO.getMemName();
		if(memName != null) {
			String subject = "您好，您已經完成註冊，請您進行會員驗證！";
			String text1 = memName  + "，您好！\n";
			String text2 = "感謝您使用本系統，請點選下方連結完成註冊\n";
//			String text3 ="http://10.120.39.21:8081/DA101G3/back-end/mem/mem.do?action=confirmStatus&code="+code;
			String text3 ="http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/back-end/mem/mem.do?action=confirmStatus&code="+code;
			
			String messageText = sb.append(text1).append(text2).append(text3).toString();
			
			MailService mailSvc = new MailService();
			mailSvc.sendMail(to, subject, messageText);
			result  = true;
		}
					
		return result;	
	}
	
	public boolean confirmCode(String code) {
		boolean result =false;
		
		MemVO memVO = dao.confirmCode(code);
		memVO.setStatus(1);
		dao.update(memVO);
		result = true;
		return result;
	}
	
	public MemVO updateMemPhoto(String memNo, byte[] memPhoto) {
		MemVO memVO = new MemVO();
		
		memVO.setMemNo(memNo);
		memVO.setMemPhoto(memPhoto);
		
		System.out.println("SERVICE的           memNO:"+memNo+"    memPhoto:"+memPhoto);

		dao.update(memVO);
		
		return memVO;
	}
}
