package com.articleDetails.model;

import java.sql.Connection;
import java.util.*;

public interface ArticleDetailsDAO_interface {
	public void insert(ArticleDetailsVO articledeailsVO);
	public void update(ArticleDetailsVO articledeailsVO);
	public void delete(ArticleDetailsVO articledeailsVO);
	public ArticleDetailsVO findByPrimaryKey(String articleno, String spotno);
	public List<ArticleDetailsVO> getAll();
	public List<ArticleDetailsVO> findByArticleno(String articleno);
	public void insert_with_Article(ArticleDetailsVO articleDetailsVO, Connection con);
	public void update_pic(byte[] articledetailspic, String articleno, String spotno);
}
