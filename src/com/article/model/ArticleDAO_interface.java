package com.article.model;

import java.util.*;

import com.articleDetails.model.*;
import com.spot.model.SpotListVO;
import com.trip.model.TripVO;
import com.tripDetails.model.TripDetailsVO;

public interface ArticleDAO_interface {
	public List<ArticleDetailsVO> insert(ArticleVO articleVO, List<TripDetailsVO> allDetails);
	public void update(ArticleVO articleVO);
	public void delete(String articleno);
	public ArticleVO findByPrimaryKey(String articleno);
	public List<ArticleVO> getAll();
	public List<ArticleVO> getAllOrderByTime();
	public List<ArticleVO> getAllOrderByViews();
	public List<ArticleVO> getAllOrderByDays();
	public List<ArticleVO> getAllByMemno(String memno);
	
}
