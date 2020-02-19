package com.Android.article.model;

import java.util.List;

public class ArticleService {
	ArticleDAO_interface dao = null;
	public ArticleService() {
		dao = new ArticleJNDIDAO();
	}
	
	public List<ArticleVO> getMemAllArticles(String email){
		List<ArticleVO> list = null;
		list = dao.getMemArticles(email);
		return list;
	}
	
	
	public List<ArticleVO> getHotArticle(){
		List<ArticleVO> list = null;
		list = dao.getHotArticles();
		return list;
	}
}
