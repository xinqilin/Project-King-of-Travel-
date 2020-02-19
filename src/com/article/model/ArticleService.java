package com.article.model;

import java.util.ArrayList;
import java.util.List;

import com.articleDetails.model.ArticleDetailsVO;
import com.tripDetails.model.TripDetailsService;
import com.tripDetails.model.TripDetailsVO;

public class ArticleService {
	
	private ArticleDAO_interface dao;
	
	public ArticleService() {
		dao = new ArticleDAO();
	}
	
	public List<ArticleDetailsVO> addArticle(String memno, String tripno, String articletitle, Integer daysofstaying, java.sql.Date dayofstart, java.sql.Date dayofend, Integer articlestatus, java.sql.Date dayoflastedit, Integer timeofviews, Integer kindoftrip, byte[] articlepic, java.sql.Date dayofcreate) {
		
		ArticleVO articleVO = new ArticleVO();
		List<TripDetailsVO> allDetails =new ArrayList<TripDetailsVO>();
		
		TripDetailsService tripDetailsSvc = new TripDetailsService();
		allDetails =tripDetailsSvc.getfindByTripno(tripno);		
	
		articleVO.setMemno(memno);
		articleVO.setTripno(tripno);
		articleVO.setArticletitle(articletitle);
		articleVO.setDaysofstaying(daysofstaying);
		articleVO.setDayofstart(dayofstart);
		articleVO.setDayofend(dayofend);
		articleVO.setArticlestatus(articlestatus);
		articleVO.setDayoflastedit(dayoflastedit);
		articleVO.setTimeofviews(timeofviews);
		articleVO.setKindoftrip(kindoftrip);
		articleVO.setArticlepic(articlepic);
		articleVO.setDayofcreate(dayofcreate);
System.out.println("ARTSVC 38 : 裝到VO裡" + articleVO);	
		return dao.insert(articleVO, allDetails);
	}
	
	public ArticleVO updateArticle(String articleno, String memno, String tripno, String articletitle, Integer daysofstaying, java.sql.Date dayofstart, java.sql.Date dayofend, Integer articlestatus, java.sql.Date dayoflastedit, Integer timeofviews, Integer kindoftrip, byte[] articlepic, java.sql.Date dayofcreate) {
		
		ArticleVO articleVO = new ArticleVO();
		
		articleVO.setArticleno(articleno);
		articleVO.setMemno(memno);
		articleVO.setTripno(tripno);
		articleVO.setArticletitle(articletitle);
		articleVO.setDaysofstaying(daysofstaying);
		articleVO.setDayofstart(dayofstart);
		articleVO.setDayofend(dayofend);
		articleVO.setArticlestatus(articlestatus);
		articleVO.setDayoflastedit(dayoflastedit);
		articleVO.setTimeofviews(timeofviews);
		articleVO.setKindoftrip(kindoftrip);
		articleVO.setArticlepic(articlepic);
		articleVO.setDayofcreate(dayofcreate);
		dao.update(articleVO);
		
		return articleVO;
	}
	
	public void deleteArticle(String articleno) {
		dao.delete(articleno);
	}
	
	public ArticleVO getOneArticle(String articleno) {
		return dao.findByPrimaryKey(articleno);
	}
	
	public List<ArticleVO> getAll(){
		return dao.getAll();
	}
	
	public List<ArticleVO> getAllOrderByTime(){
		return dao.getAllOrderByTime();
	}
	
	public List<ArticleVO> getAllOrderByViews(){
		return dao.getAllOrderByViews();
	}
	
	public List<ArticleVO> getAllByMemno(String memno){
		return dao.getAllByMemno(memno);
	}
}
