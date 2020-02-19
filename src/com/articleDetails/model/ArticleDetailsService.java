package com.articleDetails.model;


import java.util.List;



public class ArticleDetailsService {
	
	
	private ArticleDetailsDAO_interface dao;
	
	public ArticleDetailsService() {
		dao = new ArticleDetailsDAO();
	}
	
	public ArticleDetailsVO addArticleDetails(String articleno, String spotno, String articlenotes, byte[] articledetailspic, Integer articletriporderby, String picnote, Integer tripdayorder) {
		
		ArticleDetailsVO articleDetailsVO1 = new ArticleDetailsVO();
		articleDetailsVO1.setArticleno(articleno);
		articleDetailsVO1.setSpotno(spotno);
		articleDetailsVO1.setArticlenotes(articlenotes);
		articleDetailsVO1.setArticledetailspic(articledetailspic);
		articleDetailsVO1.setArticletriporderby(articletriporderby);
		articleDetailsVO1.setPicnote(picnote);
		articleDetailsVO1.setTripdayorder(tripdayorder);
		
		return articleDetailsVO1;
	}
	
	public ArticleDetailsVO updateArticleDetails(String articleno, String spotno, String articlenotes, byte[] articledetailspic, Integer articletriporderby, String picnote, Integer tripdayorder) {
System.out.println("SVC 32 : 進來");
		ArticleDetailsVO articleDetailsVO = new ArticleDetailsVO();
		articleDetailsVO.setArticleno(articleno);
		articleDetailsVO.setSpotno(spotno);
		articleDetailsVO.setArticlenotes(articlenotes);
		articleDetailsVO.setArticledetailspic(articledetailspic);
		articleDetailsVO.setArticletriporderby(articletriporderby);
		articleDetailsVO.setPicnote(picnote);
		articleDetailsVO.setTripdayorder(tripdayorder);
		dao.update(articleDetailsVO);
System.out.println("SVC 42 : 更新一次" + articleDetailsVO);
		return articleDetailsVO;
	}
	
	public void deleteArticleDetails(ArticleDetailsVO articleDetailsno) {
		dao.delete(articleDetailsno);
	}
	
	public ArticleDetailsVO getOneArticleDetails(String articleno, String spotno) {
		return dao.findByPrimaryKey(articleno, spotno);
	}
	
	public List<ArticleDetailsVO> getAll(){
		return dao.getAll();
	}
	
	public List<ArticleDetailsVO> getfindByArticleno(String articleno){
		return dao.findByArticleno(articleno);
	}
	public ArticleDetailsVO addArticleDetailsWithArticle(String articleno, String spotno, String articlenotes, byte[] articledetailspic, Integer articletriporderby, String picnote, Integer tripdayorder) {
		ArticleDetailsVO articleDetailsVO = new ArticleDetailsVO();
		articleDetailsVO.setArticleno(articleno);
		articleDetailsVO.setSpotno(spotno);
		articleDetailsVO.setArticlenotes(articlenotes);
		articleDetailsVO.setArticledetailspic(articledetailspic);
		articleDetailsVO.setArticletriporderby(articletriporderby);
		articleDetailsVO.setPicnote(picnote);
		articleDetailsVO.setTripdayorder(tripdayorder);
		
		return articleDetailsVO;
	}
	//只負責傳照片
	public ArticleDetailsVO addArticleDetailsPic(byte[] articledetailspic, String articleno, String spotno) {
		ArticleDetailsVO articleDetailsVO = new ArticleDetailsVO();
		articleDetailsVO.setArticleno(articleno);
		articleDetailsVO.setSpotno(spotno);
		articleDetailsVO.setArticledetailspic(articledetailspic);
		dao.update_pic(articledetailspic, articleno, spotno);
		
		return articleDetailsVO;
	}
}

