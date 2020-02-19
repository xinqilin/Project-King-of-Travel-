package com.partlist.model;

import java.sql.*;
import java.util.*;

public class PartListJDBCDAO implements PartListDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "YUGI";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO PartList (MemNo, ActivityNo, Putpoints, GetPoints, OddRate) VALUES (?, ?, ?, ?, ?)";
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, partlistVO.getMemno());
			pstmt.setString(2, partlistVO.getActivityno());
			pstmt.setInt(3, partlistVO.getPutpoints());
			pstmt.setInt(4, partlistVO.getGetpoints());
			pstmt.setDouble(5, partlistVO.getOddrate());
			
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
	public void update(PartListVO partlistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, partlistVO.getPutpoints());
			pstmt.setInt(2, partlistVO.getGetpoints());
			pstmt.setDouble(3, partlistVO.getOddrate());
			pstmt.setString(4, partlistVO.getMemno());
			pstmt.setString(5, partlistVO.getActivityno());
			
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
	public void delete(String memno, String activityno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, memno);
			pstmt.setString(2, activityno);
					
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
	public PartListVO findByPrimaryKey(String memno, String activityno) {
		PartListVO partlistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
	public static void main(String[] args) {
		
		PartListJDBCDAO dao = new PartListJDBCDAO();
		//新增
//		PartListVO partlistVO1 = new PartListVO();
//		partlistVO1.setMemno("MEM0001");
//		partlistVO1.setActivityno("ACT0007");
//		partlistVO1.setPutpoints(new Integer(7777));
//		partlistVO1.setGetpoints(new Integer(77777));
//		partlistVO1.setOddrate(new Double(7));
//		dao.insert(partlistVO1);
		//修改
//		PartListVO partlistVO2 = new PartListVO();
//		partlistVO2.setMemno("MEM0001");
//		partlistVO2.setActivityno("ACT0007");
//		partlistVO2.setPutpoints(new Integer(8888));
//		partlistVO2.setGetpoints(new Integer(88888));
//		partlistVO2.setOddrate(new Double(8));
//		dao.update(partlistVO2);
		//刪除
//		dao.delete("MEM0001", "ACT0007");
		//查詢一個
//		PartListVO partlistVO3 = dao.findByPrimaryKey("MEM0001", "ACT0001");
//		System.out.print(partlistVO3.getActivityno() + ", ");
//		System.out.print(partlistVO3.getMemno() + ", ");
//		System.out.print(partlistVO3.getPutpoints() + ", ");
//		System.out.print(partlistVO3.getGetpoints() + ", ");
//		System.out.print(partlistVO3.getOddrate() + ", ");
		//查詢全部
		List<PartListVO> list = dao.getAll();
		for (PartListVO partlistVO : list) {
			System.out.print(partlistVO.getActivityno() + ", ");
			System.out.print(partlistVO.getMemno() + ", ");
			System.out.print(partlistVO.getPutpoints() + ", ");
			System.out.print(partlistVO.getGetpoints() + ", ");
			System.out.print(partlistVO.getOddrate() + ", ");
			System.out.println();
		}
	}
}
