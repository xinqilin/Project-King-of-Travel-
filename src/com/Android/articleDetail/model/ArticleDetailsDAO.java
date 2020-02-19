package com.Android.articleDetail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Android.spot.model.SpotListJNDIDAO;
import com.Android.spot.model.SpotListVO;
import com.Android.tripDetail.model.TripDetailVO;



public class ArticleDetailsDAO implements ArticleDetailsDAO_interface {
	private static final String getArticleDetailByArticleNo = "Select * from articledetails where (tripDayorder = ? and ARTICLENO = ?) order by articletriporderby asc";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Map<Integer, List<ArticleDetailsVO>> getArticleDetails(String articleNo, int tripDays) {
		Map<Integer,List<ArticleDetailsVO>> artrcleDetailList = new LinkedHashMap<>();  //總行程
		List<ArticleDetailsVO> articleDetail = new ArrayList<ArticleDetailsVO>();  //單日行程

		for (int i = 1; i<=tripDays ; i++) {
			articleDetail = getArticleDayDetail(i, articleNo);  //設置 i 為天數，使用 getTripDayDetail 可取得一天的景點行程		
			artrcleDetailList.put(i,articleDetail);  //將行程加入總行程中
		}
		return artrcleDetailList;
	}
	
	public List<ArticleDetailsVO> getArticleDayDetail(int day, String articleNo){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ArticleDetailsVO> articleDetail = new ArrayList<ArticleDetailsVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getArticleDetailByArticleNo);
			pstmt.setInt(1, day);
			pstmt.setString(2, articleNo);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ArticleDetailsVO articleDetailsVO = new ArticleDetailsVO();
				articleDetailsVO.setArticleno(rs.getString("articleNo"));
				articleDetailsVO.setSpotno(rs.getString("SpotNo"));
				articleDetailsVO.setTripdayorder(rs.getInt("TRIPDAYORDER"));
				articleDetailsVO.setArticletriporderby(rs.getInt("Articletriporderby"));
				articleDetailsVO.setArticlenotes(rs.getString("articlenotes"));
				
				SpotListJNDIDAO spotDAO = new SpotListJNDIDAO();
				SpotListVO spotListVO = spotDAO.findByPrimaryKey(articleDetailsVO.getSpotno());
				articleDetailsVO.setSpotListVO(spotListVO);
				
				articleDetail.add(articleDetailsVO);
			}				
			System.out.println(articleDetail.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {con.close();
				} catch(Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return articleDetail;
	}
	
}
