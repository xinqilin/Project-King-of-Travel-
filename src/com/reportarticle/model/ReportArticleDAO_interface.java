package com.reportarticle.model;

import java.util.List;

import com.article.model.ArticleVO;

public interface ReportArticleDAO_interface {
	public void insert(ReportArticleVO reportarticleVO);
	public void update(ReportArticleVO reportarticleVO);
	public ArticleVO findByPrimaryKey(String articleno);
	public List<ArticleVO> getAll();
}
