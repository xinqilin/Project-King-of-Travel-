package com.activity.model;

import java.sql.Timestamp;
import java.util.List;

public class ActivityService {
	
	private ActivityDAO_interface dao;
	
	public ActivityService() {
		dao = new ActivityDAO();
	}

	public ActivityVO addActivity (String activityname, String actrule, Timestamp datestart, Timestamp dateend, Double betrate, String chatcontent,
			String streamcontent, String activitycontent) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActivityname(activityname);
		activityVO.setActrule(actrule);
		activityVO.setDatestart(datestart);
		activityVO.setDateend(dateend);
		activityVO.setBetrate(betrate);
		activityVO.setChatcontent(chatcontent);
		activityVO.setStreamcontent(streamcontent);
		activityVO.setActivitycontent(activitycontent);
		dao.insert(activityVO);
		
		return activityVO;
	}
	
	public ActivityVO updateActivity (String activityno, String activityname, String actrule, Timestamp datestart, Timestamp dateend, Double betrate, 
			String chatcontent, String streamcontent, String activitycontent) {
		ActivityVO activityVO = new ActivityVO();
		activityVO.setActivityno(activityno);
		activityVO.setActivityname(activityname);
		activityVO.setActrule(actrule);
		activityVO.setDatestart(datestart);
		activityVO.setDateend(dateend);
		activityVO.setBetrate(betrate);
		activityVO.setChatcontent(chatcontent);
		activityVO.setStreamcontent(streamcontent);
		activityVO.setActivitycontent(activitycontent);
		dao.update(activityVO);
		
		return activityVO;
	}
	public void deleteActivity(String activityno) {
		dao.delete(activityno);
	}
	
	public ActivityVO getOneActivity(String activityno) {
		return dao.findByPrimaryKey(activityno);
	}
	
	public List<ActivityVO> getAll() {
		return dao.getAll();
	}
	
			
}
