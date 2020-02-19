package com.article.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.articleDetails.model.ArticleDetailsDAO;
import com.articleDetails.model.ArticleDetailsService;
import com.articleDetails.model.ArticleDetailsVO;
import com.spot.model.SpotListVO;
import com.trip.model.TripVO;
import com.tripDetails.model.TripDetailsVO;

public class ArticleDAO implements ArticleDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT  = 
			"INSERT INTO article (articleno,memno,tripno,articletitle,daysofstaying,dayofstart,dayofend,articlestatus,dayoflastedit,timeofviews,kindoftrip,articlepic,dayofcreate) values ('ANI'||LPAD(to_char(article_seq.NEXTVAL), 4, '0'), ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT articleno,memno,tripno,articletitle,daysofstaying,to_char(dayofstart,'yyyy-mm-dd') dayofstart,to_char(dayofend,'yyyy-mm-dd') dayofend,articlestatus,dayoflastedit,timeofviews,kindoftrip,articlepic,dayofcreate FROM article ORDER BY articleno";
	private static final String GET_ONE_STMT =
			"SELECT articleno,memno,tripno,articletitle,daysofstaying,to_char(dayofstart,'yyyy-mm-dd') dayofstart,to_char(dayofend,'yyyy-mm-dd') dayofend,articlestatus,dayoflastedit,timeofviews,kindoftrip,articlepic,dayofcreate FROM article WHERE articleno = ?";
	//連細節一起刪除
	private static final String DELETE_ArticleDetails = 
			"DELETE FROM articledetails where articleno = ?";
	private static final String DELETE_Article = 
			"DELETE FROM article where articleno = ?";
	
	private static final String UPDATE = 
			"UPDATE article SET memno=?, tripno=?, articletitle=?, daysofstaying=?, dayofstart=?, dayofend=?, articlestatus=?, dayoflastedit=?, timeofviews=?, kindoftrip=? ,articlepic=? ,dayofcreate=? WHERE articleno = ?";
	//依排序查詢
	private static final String GET_ALL_ORDERBY_TIME =
			"SELECT articleno,memno,tripno,articletitle,daysofstaying,to_char(dayofstart,'yyyy-mm-dd') dayofstart,to_char(dayofend,'yyyy-mm-dd') dayofend,articlestatus,dayoflastedit,timeofviews,kindoftrip,articlepic,dayofcreate FROM article ORDER BY dayofcreate desc";
	private static final String GET_ALL_ORDERBY_VIEWS = 
			"SELECT articleno,memno,tripno,articletitle,daysofstaying,to_char(dayofstart,'yyyy-mm-dd') dayofstart,to_char(dayofend,'yyyy-mm-dd') dayofend,articlestatus,dayoflastedit,timeofviews,kindoftrip,articlepic,dayofcreate FROM article ORDER BY timeofviews desc";
	private static final String GET_ALL_ORDERBY_DAYS = 
			"SELECT articleno,memno,tripno,articletitle,daysofstaying,to_char(dayofstart,'yyyy-mm-dd') dayofstart,to_char(dayofend,'yyyy-mm-dd') dayofend,articlestatus,dayoflastedit,timeofviews,kindoftrip,articlepic,dayofcreate FROM article ORDER BY daysofstaying desc";
	//個人查詢
	private static final String GET_ALL_BY_MEMNO = "SELECT articleno,memno,tripno,articletitle,daysofstaying,to_char(dayofstart,'yyyy-mm-dd') dayofstart,to_char(dayofend,'yyyy-mm-dd') dayofend,articlestatus,dayoflastedit,timeofviews,kindoftrip,articlepic,dayofcreate FROM article WHERE memno = ?";

	
	
	@Override
	public List<ArticleDetailsVO> insert(ArticleVO articleVO, List<TripDetailsVO> allDetails) {
		ArticleDetailsVO articleDetailsVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ArticleDetailsVO> list = new ArrayList<ArticleDetailsVO>();
	
		try {
			
			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			//新增遊記
			String cols[] = {"articleno"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
					
			pstmt.setString(1, articleVO.getMemno());
			pstmt.setString(2, articleVO.getTripno());
			pstmt.setString(3, articleVO.getArticletitle());
			pstmt.setInt(4, articleVO.getDaysofstaying());
			pstmt.setDate(5, articleVO.getDayofstart());
			pstmt.setDate(6, articleVO.getDayofend());
			pstmt.setInt(7, articleVO.getArticlestatus());
			pstmt.setDate(8, articleVO.getDayoflastedit());
			pstmt.setInt(9, articleVO.getTimeofviews());
			pstmt.setInt(10, articleVO.getKindoftrip());
			pstmt.setBytes(11, articleVO.getArticlepic());
			pstmt.setDate(12, articleVO.getDayofcreate());
			pstmt.executeUpdate();
System.out.println("78 : 遊記已新增且傳送到資料庫" + articleVO);
			
			//擷取對應自增主鍵
			String nextArticleno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				nextArticleno = rs.getString(1);
System.out.println("86 : 自增主鍵=" + nextArticleno + "(剛才加入的遊記編號)");
			}else {
System.out.println("88 : 未取得新增的自增主鍵");
			}
			rs.close();
			
			//同時新增遊記內容
			//宣告articleDetailsVO的SERVICE跟articleDetailsVO的LIST
			
			byte[] articledetailspic = null;
			ArticleDetailsDAO articleDetailsDAO = new ArticleDetailsDAO();		
			
			
			for(TripDetailsVO tripDetailsVO : allDetails) {
				articleDetailsVO = new ArticleDetailsVO();
				articleDetailsVO.setArticleno(new String(nextArticleno));
				articleDetailsVO.setSpotno(tripDetailsVO.getSpotno());
				articleDetailsVO.setArticlenotes("");
				articleDetailsVO.setArticledetailspic(articledetailspic);
				articleDetailsVO.setArticletriporderby(tripDetailsVO.getTriporderby());
				articleDetailsVO.setPicnote("");
				articleDetailsVO.setTripdayorder(tripDetailsVO.getTripdayorder());
				//用SERVICE的list.add()方法存入資料庫
				articleDetailsDAO.insert_with_Article(articleDetailsVO, con);
				list.add(articleDetailsVO);
			}			
			// 2●設定於 pstm.executeUpdate()之後
System.out.println("120 : 新增遊記編號:" + nextArticleno + "裡面，共有" + list.size() + "個遊記內容被新增");
			con.commit();
			con.setAutoCommit(true);

		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occurd." + se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;//回傳list	
	}
	
	@Override
	public void update(ArticleVO articleVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, articleVO.getMemno());
			pstmt.setString(2, articleVO.getTripno());
			pstmt.setString(3, articleVO.getArticletitle());
			pstmt.setInt(4, articleVO.getDaysofstaying());
			pstmt.setDate(5, articleVO.getDayofstart());
			pstmt.setDate(6, articleVO.getDayofend());
			pstmt.setInt(7, articleVO.getArticlestatus());
			pstmt.setDate(8, articleVO.getDayoflastedit());
			pstmt.setInt(9, articleVO.getTimeofviews());
			pstmt.setInt(10, articleVO.getKindoftrip());
			
			pstmt.setBytes(11, articleVO.getArticlepic());
			pstmt.setDate(12, articleVO.getDayofcreate());
			pstmt.setString(13, articleVO.getArticleno());
			
			pstmt.executeUpdate();
		
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured" + se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(String articleno) {
		int updateCount_ArticleDetails = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			//設定於executeUpdate()之前
			con.setAutoCommit(false);
			
			//先刪除遊記細節
			pstmt = con.prepareStatement(DELETE_ArticleDetails);
			pstmt.setString(1, articleno);
			updateCount_ArticleDetails = pstmt.executeUpdate();
			
			//再刪除遊記
			pstmt = con.prepareStatement(DELETE_Article);
			pstmt.setString(1, articleno);
			pstmt.executeUpdate();
			
			//設定於executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
System.out.println("刪除遊記編號 = " + articleno + "，同時刪除遊記細節，共" + updateCount_ArticleDetails + "個遊記細節被刪除");
			
		}catch(SQLException se) {
			if(con != null) {
				try {
					//設定於有Exception發生時之catch區塊內
					con.rollback();
				}catch(SQLException sqlex) {
					throw new RuntimeException("A database error occured" + sqlex.getMessage());
				}
			}
			throw new RuntimeException("A database error occured" + se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
	}
	
	@Override
	public ArticleVO findByPrimaryKey(String articleno) {
		
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, articleno);
			
			rs = pstmt.executeQuery();
			
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
				articleVO.setArticlepic(rs.getBytes("articlepic"));
				articleVO.setDayofcreate(rs.getDate("dayofcreate"));
			}
		}catch(SQLException se) {
			throw new RuntimeException("" + se.getMessage());
			
		}finally{
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return articleVO;
	}
	
	@Override
	public List<ArticleVO> getAll(){
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
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
				articleVO.setArticlepic(rs.getBytes("articlepic"));
				articleVO.setDayofcreate(rs.getDate("dayofcreate"));
				list.add(articleVO);
			}
		}catch(SQLException se) {
			throw new RuntimeException("" + se.getMessage());
			
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<ArticleVO> getAllOrderByTime(){
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ORDERBY_TIME);
			rs = pstmt.executeQuery();
			
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
				articleVO.setArticlepic(rs.getBytes("articlepic"));
				articleVO.setDayofcreate(rs.getDate("dayofcreate"));
				list.add(articleVO);
			}
		}catch(SQLException se) {
			throw new RuntimeException("" + se.getMessage());
			
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<ArticleVO> getAllOrderByViews(){
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ORDERBY_VIEWS);
			rs = pstmt.executeQuery();
			
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
				articleVO.setArticlepic(rs.getBytes("articlepic"));
				articleVO.setDayofcreate(rs.getDate("dayofcreate"));
				list.add(articleVO);
			}
		}catch(SQLException se) {
			throw new RuntimeException("" + se.getMessage());
			
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<ArticleVO> getAllOrderByDays(){
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ORDERBY_DAYS);
			rs = pstmt.executeQuery();
			
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
				articleVO.setArticlepic(rs.getBytes("articlepic"));
				articleVO.setDayofcreate(rs.getDate("dayofcreate"));
				list.add(articleVO);
			}
		}catch(SQLException se) {
			throw new RuntimeException("" + se.getMessage());
			
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public List<ArticleVO> getAllByMemno(String memno){
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMNO);
			pstmt.setString(1, memno);
			rs = pstmt.executeQuery();
			
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
				articleVO.setArticlepic(rs.getBytes("articlepic"));
				articleVO.setDayofcreate(rs.getDate("dayofcreate"));
				list.add(articleVO);

			}
		}catch(SQLException se) {
			throw new RuntimeException("" + se.getMessage());
			
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}
