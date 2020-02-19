package com.Android.articleDetail.model;


import java.util.*;

public interface ArticleDetailsDAO_interface {
	public Map<Integer, List<ArticleDetailsVO>> getArticleDetails(String articleNo, int tripDays);
}
