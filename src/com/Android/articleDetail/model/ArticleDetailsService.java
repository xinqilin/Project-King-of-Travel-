package com.Android.articleDetail.model;


import java.util.List;
import java.util.Map;

import com.Android.tripDetail.model.TripDetailVO;



public class ArticleDetailsService {
	
	
	private ArticleDetailsDAO_interface dao;
	
	public ArticleDetailsService() {
		dao = new ArticleDetailsDAO();
	}
	
	public Map<Integer, List<ArticleDetailsVO>> getArticleDetails(String articleNo, int tripDays){
		Map<Integer, List<ArticleDetailsVO>> list = dao.getArticleDetails(articleNo, tripDays);
		return list;
	}
	
}

