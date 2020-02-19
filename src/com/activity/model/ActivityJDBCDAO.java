package com.activity.model;

import java.util.*;
import java.sql.*;

public class ActivityJDBCDAO implements ActivityDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "YUGI";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO Activity (ActivityNo, ActivityName, ActRule, DateStart, DateEnd, BetRate, ChatContent, StreamContent, ActivityContent) VALUES "
					+ "('ACT'||LPAD(to_char(PointGoodsNo_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT ActivityNo, ActivityName, ActRule, DateStart, DateEnd, BetRate, ChatContent, StreamContent, ActivityContent FROM Activity ORDER BY ActivityNo";
	private static final String GET_ONE_STMT = 
			"SELECT ActivityNo, ActivityName, ActRule, DateStart, DateEnd, BetRate, ChatContent, StreamContent, ActivityContent FROM Activity WHERE ActivityNo=?";
	private static final String DELETE_STMT = 
			"DELETE FROM Activity WHERE ActivityNo=?";
	private static final String UPDATE_STMT =
			"UPDATE Activity set ActivityName=?, ActRule=?, DateStart=?, DateEnd=?, BetRate=?, ChatContent=?, StreamContent=?, ActivityContent=? WHERE ActivityNo=?";
	@Override
	public void insert(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, activityVO.getActivityname());
			pstmt.setString(2, activityVO.getActrule());
			pstmt.setTimestamp(3, activityVO.getDatestart());
			pstmt.setTimestamp(4, activityVO.getDateend());
			pstmt.setDouble(5, activityVO.getBetrate());
			pstmt.setString(6, activityVO.getChatcontent());
			pstmt.setString(7, activityVO.getStreamcontent());
			pstmt.setString(8, activityVO.getActivitycontent());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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
	}
	@Override
	public void update(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, activityVO.getActivityname());
			pstmt.setString(2, activityVO.getActrule());
			pstmt.setTimestamp(3, activityVO.getDatestart());
			pstmt.setTimestamp(4, activityVO.getDateend());
			pstmt.setDouble(5, activityVO.getBetrate());
			pstmt.setString(6, activityVO.getChatcontent());
			pstmt.setString(7, activityVO.getStreamcontent());
			pstmt.setString(8, activityVO.getActivitycontent());
			pstmt.setString(9, activityVO.getActivityno());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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
	}
	@Override
	public void delete(String activityno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url ,userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, activityno);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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
	}
	@Override
	public ActivityVO findByPrimaryKey(String activityno) {
		ActivityVO activityVO = null; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, activityno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivityno(rs.getString("activityno"));
				activityVO.setActivityname(rs.getString("activityname"));
				activityVO.setActrule(rs.getString("actrule"));
				activityVO.setDatestart(rs.getTimestamp("datestart"));
				activityVO.setDateend(rs.getTimestamp("dateend"));
				activityVO.setBetrate(rs.getDouble("betrate"));
				activityVO.setChatcontent(rs.getString("chatcontent"));
				activityVO.setStreamcontent(rs.getString("streamcontent"));
				activityVO.setActivitycontent(rs.getString("activitycontent"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+ se.getMessage());
		} finally {
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
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return activityVO;
	}
	@Override
	public List<ActivityVO> getAll() {
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		ActivityVO activityVO = null; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivityno(rs.getString("activityno"));
				activityVO.setActivityname(rs.getString("activityname"));
				activityVO.setActrule(rs.getString("actrule"));
				activityVO.setDatestart(rs.getTimestamp("datestart"));
				activityVO.setDateend(rs.getTimestamp("dateend"));
				activityVO.setBetrate(rs.getDouble("betrate"));
				activityVO.setChatcontent(rs.getString("chatcontent"));
				activityVO.setStreamcontent(rs.getString("streamcontent"));
				activityVO.setActivitycontent(rs.getString("activitycontent"));
				list.add(activityVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+ se.getMessage());
		} finally {
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
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		ActivityJDBCDAO dao = new ActivityJDBCDAO();
		//新增
//		ActivityVO activityVO1 = new ActivityVO();
//		activityVO1.setActivityname("活動七");
//		activityVO1.setActrule("這是規則 " + "\n" + "這也是規則" + "\n" + "這也是規則唷");
//		activityVO1.setDatestart(new Timestamp(2019, 12, 24, 10, 10, 10, 1000));
//		activityVO1.setDateend(new Timestamp(2019, 12, 24, 10, 10, 10, 1000));
//		activityVO1.setBetrate(new Double(6.666));
//		activityVO1.setChatcontent("這是聊天內容");
//		activityVO1.setStreamcontent("這是直播內容");
//		activityVO1.setActivitycontent("這是活動內容");
//		dao.insert(activityVO1);
		//修改
//		ActivityVO activityVO2 = new ActivityVO();
//		activityVO2.setActivityname("活動八");
//		activityVO2.setActrule("這是規則 " + "\n" + "這也是規則" + "\n" + "這也是規則唷");
//		activityVO2.setDatestart(new Timestamp(2019, 12, 24, 10, 10, 10, 1000));
//		activityVO2.setDateend(new Timestamp(2019, 12, 24, 10, 10, 10, 1000));
//		activityVO2.setBetrate(new Double(7.777));
//		activityVO2.setChatcontent("這是聊天內容");
//		activityVO2.setStreamcontent("這是直播內容");
//		activityVO2.setActivitycontent("這是活動內容");
//		activityVO2.setActivityno("ACT0009");
//		dao.update(activityVO2);
		//刪除
//		dao.delete("ACT0009");
		//查詢一個
		ActivityVO activityVO3 = dao.findByPrimaryKey("ACT0001");
		System.out.println(activityVO3.getActivityno() + ", ");
		System.out.println(activityVO3.getActivityname() + ", ");
		System.out.println(activityVO3.getActrule() + ", ");
		System.out.println(activityVO3.getDatestart() + ", ");
		System.out.println(activityVO3.getDateend() + ", ");
		System.out.println(activityVO3.getBetrate() + ", ");
		System.out.println(activityVO3.getChatcontent() + ", ");
		System.out.println(activityVO3.getStreamcontent() + ", ");
		System.out.println(activityVO3.getActivitycontent() + ", ");
		//查詢全部
		List<ActivityVO> list = dao.getAll();
		for (ActivityVO activityVO : list) {
			System.out.println(activityVO3.getActivityno() + ", ");
			System.out.println(activityVO3.getActivityname() + ", ");
			System.out.println(activityVO3.getActrule() + ", ");
			System.out.println(activityVO3.getDatestart() + ", ");
			System.out.println(activityVO3.getDateend() + ", ");
			System.out.println(activityVO3.getBetrate() + ", ");
			System.out.println(activityVO3.getChatcontent() + ", ");
			System.out.println(activityVO3.getStreamcontent() + ", ");
			System.out.println(activityVO3.getActivitycontent() + ", ");
		}
	}
}
