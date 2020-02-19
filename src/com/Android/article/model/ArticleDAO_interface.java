package com.Android.article.model;

import java.util.List;

public interface ArticleDAO_interface {
	public void insertArticle(ArticleVO articleVO);
	public void deleteArticle(String articleNo);
	public void updateArticle(ArticleVO articleVO);
	public List<ArticleVO> getMemArticles(String email);
	public List<ArticleVO> getHotArticles();
}
