package com.Android.article.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ArticleJNDIDAO implements ArticleDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	private static final String INSERT_STMT  = 
//			"INSERT INTO article (articleNo,memNo,tripNo,articleTitle,daysOfStaying,dayOfstart,dayOfend,articleStatus,dayOfLastEdit,timeOfViews,kindOfTrip) values ('ANI'||LPAD(to_char(article_seq.NEXTVAL), 4, '0'), ?, ?, ? ,?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT =
//			"SELECT articleNo,memNo,tripNo,articleTitle,daysOfStaying,to_char(dayOfstart,'yyyy-mm-dd') dayOfstart,to_char(dayOfend,'yyyy-mm-dd') dayofend,articleStatus,dayOfLastEdit,timeOfViews,kindOfTrip FROM article ORDER BY articleno";
//	private static final String GET_ONE_STMT =
//			"SELECT articleNo,memNo,tripNo,articleTitle,daysOfStaying,to_char(dayOfstart,'yyyy-mm-dd') dayOfstart,to_char(dayOfend,'yyyy-mm-dd') dayofend,articleStatus,dayOfLastEdit,timeofviews,kindOfTrip FROM article WHERE articleno = ?";
//	private static final String DELETE = 
//			"DELETE FROM ARTICLE where articleNo = ?";
//	private static final String UPDATE = 
//			"UPDATE article SET memNo=?, tripNo=?, articleTitle=?, daysOfStaying=?, dayOfstart=?, dayOfend=?, articleStatus=?, dayOfLastEdit=?, timeOfViews=?, kindOfTrip=? WHERE articleNo = ?";
//	private static final String GET_ALL_ORDERBY_TIME =
//			"SELECT articleNo, memNo, tripNo,articleTitle,daysOfStaying,to_char(dayOfstart,'yyyy-mm-dd') dayOfstart,to_char(dayOfend,'yyyy-mm-dd') dayofend,articleStatus,dayOfLastEdit,timeOfViews,kindOfTrip FROM article ORDER BY dayOfLastEdit";
//	private static final String GET_ALL_ORDERBY_VIEWS = 
//			"SELECT articleNo, memNo, tripNo,articleTitle,daysOfStaying,to_char(dayOfstart,'yyyy-mm-dd') dayOfstart,to_char(dayOfend,'yyyy-mm-dd') dayofend,articleStatus,dayOfLastEdit,timeOfViews,kindOfTrip FROM article ORDER BY timeOfViews";
	private static final String getMemAllArticle = 
			"SELECT * FROM Article where memNo = (Select memNo from MemberList where e_mail = ?) order by dayOfLastEdit desc";
	private static final String getHotArticle = 
			"SELECT * FROM Article order by timeOfViews desc";
	
	@Override
	public void insertArticle(ArticleVO articleVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticle(String articleNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateArticle(ArticleVO articleVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArticleVO> getMemArticles(String email) {
		List<ArticleVO> list= null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVO articleVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getMemAllArticle);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery(); 
			list = new ArrayList<ArticleVO>();
			
			while(rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticleno(rs.getString("articleno"));
				articleVO.setMemno(rs.getString("memno"));
				articleVO.setTripno(rs.getString("tripno"));
				articleVO.setArticletitle(rs.getString("articletitle"));
				articleVO.setDaysofstaying(rs.getInt("daysofstaying"));
				articleVO.setDayofstart(rs.getDate("dayofstart"));
				articleVO.setDayofend(rs.getDate("dayofend"));
				articleVO.setArticlestatus(rs.getInt("articlestatus"));
				articleVO.setDayoflastedit(rs.getDate("dayoflastedit"));
				articleVO.setTimeofviews(rs.getInt("timeofviews"));
				articleVO.setKindoftrip(rs.getInt("kindoftrip"));
				list.add(articleVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<ArticleVO> getHotArticles() {
		List<ArticleVO> list= null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVO articleVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getHotArticle);
			rs = pstmt.executeQuery(); 
			list = new ArrayList<ArticleVO>();
			
			while(rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticleno(rs.getString("articleno"));
				articleVO.setMemno(rs.getString("memno"));
				articleVO.setTripno(rs.getString("tripno"));
				articleVO.setArticletitle(rs.getString("articletitle"));
				articleVO.setDaysofstaying(rs.getInt("daysofstaying"));
				articleVO.setDayofstart(rs.getDate("dayofstart"));
				articleVO.setDayofend(rs.getDate("dayofend"));
				articleVO.setArticlestatus(rs.getInt("articlestatus"));
				articleVO.setDayoflastedit(rs.getDate("dayoflastedit"));
				articleVO.setTimeofviews(rs.getInt("timeofviews"));
				articleVO.setKindoftrip(rs.getInt("kindoftrip"));
				list.add(articleVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
