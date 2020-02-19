package com.activity.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActivityDAO implements ActivityDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			con = ds.getConnection();
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
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null) {
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
			con = ds.getConnection();
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
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null) {
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, activityno);
			
			pstmt.executeUpdate();
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, activityno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivityno(rs.getString("activityno"));
				activityVO.setActivityname(rs.getString("activityname"));
				activityVO.setActrule(rs.getString("actrule"));
				activityVO.setDatestart(rs.getTimestamp("datestart"));
				activityVO.setDateend(rs.getTimestamp("dateend"));
				activityVO.setChatcontent(rs.getString("chatcontent"));
				activityVO.setStreamcontent(rs.getString("streamcontent"));
				activityVO.setActivitycontent(rs.getString("activitycontent"));
			} 
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivityno(rs.getString("activityno"));
				activityVO.setActivityname(rs.getString("activityname"));
				activityVO.setActrule(rs.getString("actrule"));
				activityVO.setDatestart(rs.getTimestamp("datestart"));
				activityVO.setDateend(rs.getTimestamp("dateend"));
				activityVO.setChatcontent(rs.getString("chatcontent"));
				activityVO.setStreamcontent(rs.getString("streamcontent"));
				activityVO.setActivitycontent(rs.getString("activitycontent"));
				list.add(activityVO);
			} 
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
