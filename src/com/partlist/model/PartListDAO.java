package com.partlist.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PartListDAO implements PartListDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO PartList (PartNo, MemNo, ActivityNo, OddTeam, Putpoints, GetPoints, OddRate) VALUES ('PAT'||LPAD(to_char(PartNo_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT MemNo, ActivityNo, Putpoints, GetPoints, OddRate FROM PartList ORDER BY MemNo";
	private static final String GET_ONE_STMT = 
			"SELECT MemNo, ActivityNo, Putpoints, GetPoints, OddRate FROM PartList WHERE MemNo=? AND ActivityNo=?";
	private static final String DELETE_STMT = 
			"DELETE FROM PartList WHERE MemNo=? AND ActivityNo=?";
	private static final String UPDATE_STMT =
			"UPDATE PartList set Putpoints=?, GetPoints=?, OddRate=? WHERE MemNo=? AND ActivityNo=?";
	
	@Override
	public void insert(PartListVO partlistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, partlistVO.getMemno());
			pstmt.setString(2, partlistVO.getActivityno());
			pstmt.setString(3, partlistVO.getOddteam());
			pstmt.setInt(4, partlistVO.getPutpoints());
			pstmt.setInt(5, partlistVO.getGetpoints());
			pstmt.setDouble(6, partlistVO.getOddrate());
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
	public void update(PartListVO partlistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, partlistVO.getPutpoints());
			pstmt.setInt(2, partlistVO.getGetpoints());
			pstmt.setDouble(3, partlistVO.getOddrate());
			pstmt.setString(4, partlistVO.getMemno());
			pstmt.setString(5, partlistVO.getActivityno());
			
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
	public void delete(String memno, String activityno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, memno);
			pstmt.setString(2, activityno);
					
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
	public PartListVO findByPrimaryKey(String memno, String activityno) {
		PartListVO partlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, memno);
			pstmt.setString(2, activityno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				partlistVO = new PartListVO();
				partlistVO.setMemno(rs.getString("memno"));
				partlistVO.setActivityno(rs.getString("activityno"));
				partlistVO.setPutpoints(rs.getInt("putpoints"));
				partlistVO.setGetpoints(rs.getInt("getpoints"));
				partlistVO.setOddrate(rs.getDouble("oddrate"));
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
		return partlistVO;
	}
	@Override
	public List<PartListVO> getAll() {
		List<PartListVO> list = new ArrayList<PartListVO>();
		PartListVO partlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				partlistVO = new PartListVO();
				partlistVO.setMemno(rs.getString("memno"));
				partlistVO.setActivityno(rs.getString("activityno"));
				partlistVO.setPutpoints(rs.getInt("putpoints"));
				partlistVO.setGetpoints(rs.getInt("getpoints"));
				partlistVO.setOddrate(rs.getDouble("oddrate"));
				list.add(partlistVO);
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
