package com.tripReport.model;

import java.util.*;
import java.sql.*;

public class TripReportJDBCDAO implements TripReportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into reporttrip (TripNo,MemNo ,REASON,TRIPSTATUS) values (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT TripNo,MemNo ,REASON,TRIPSTATUS FROM reporttrip order by TripNo";
	private static final String GET_ONE_STMT = "SELECT TripNo,MemNo ,REASON,TRIPSTATUS FROM reporttrip where TRIPNO = ? and memno=?";
	private static final String DELETE = "DELETE FROM reporttrip where TRIPNO = ? and MEMNO=?";
	private static final String UPDATE = "UPDATE reporttrip set REASON=?,TRIPSTATUS=? where TripNo=? and MemNo=?";

	@Override
	public void insert(TripReportVO tripreportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tripreportVO.getTripno());
			pstmt.setString(2, tripreportVO.getMemno());
			pstmt.setString(3, tripreportVO.getReason());
			pstmt.setInt(4, tripreportVO.getTripstatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(TripReportVO tripreportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, tripreportVO.getReason());
			pstmt.setInt(2, tripreportVO.getTripstatus());
			pstmt.setString(3, tripreportVO.getTripno());
			pstmt.setString(4, tripreportVO.getMemno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(TripReportVO tripreportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, tripreportVO.getTripno());
			pstmt.setString(2, tripreportVO.getMemno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public TripReportVO findByPrimaryKey(String tripno,String memno) {

		TripReportVO tripreportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, tripno);
			pstmt.setString(2, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripreportVO = new TripReportVO();
				tripreportVO.setTripno(rs.getString("tripno"));
				tripreportVO.setMemno(rs.getString("memno"));
				tripreportVO.setReason(rs.getString("reason"));
				tripreportVO.setTripstatus(rs.getInt("tripstatus"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return tripreportVO;
	}

	@Override
	public List<TripReportVO> getAll() {
		List<TripReportVO> list = new ArrayList<TripReportVO>();
		TripReportVO tripreportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tripreportVO 也稱為 Domain objects
				tripreportVO = new TripReportVO();
				tripreportVO.setTripno(rs.getString("tripno"));
				tripreportVO.setMemno(rs.getString("memno"));
				tripreportVO.setReason(rs.getString("reason"));
				tripreportVO.setTripstatus(rs.getInt("tripstatus"));

				list.add(tripreportVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

		TripReportJDBCDAO dao = new TripReportJDBCDAO();

		// 新增
//		TripReportVO tripreportVO1 = new TripReportVO();
//		tripreportVO1.setTripno("TLI0010");
//		tripreportVO1.setMemno("MEM0010");
//		tripreportVO1.setReason("可能用字不雅喔");
//		tripreportVO1.setTripstatus(2);
//		dao.insert(tripreportVO1);
//		System.out.println("已新增!");

		// 修改
//		TripReportVO tripreportVO2 = new TripReportVO();
//		tripreportVO2.setReason("可能用字不雅喔!!!");
//		tripreportVO2.setTripstatus(1);
//		tripreportVO2.setTripno("TLI0009");
//		tripreportVO2.setMemno("MEM0001");
//		dao.update(tripreportVO2);
//		System.out.println("已修改");

		// 刪除
//		TripReportVO tripreportVO4 = new TripReportVO();
//		tripreportVO4.setTripno("TLI0010");
//		tripreportVO4.setMemno("MEM0010");
//		dao.delete(tripreportVO4);
//		System.out.println("已刪除!");

//		// 查詢
		TripReportVO tripreportVO3 = dao.findByPrimaryKey("TLI0001","MEM0002");
		System.out.print(tripreportVO3.getTripno() + ",");
		System.out.print(tripreportVO3.getMemno() + ",");
		System.out.print(tripreportVO3.getReason() + ",");
		System.out.print(tripreportVO3.getTripstatus() + ",");
		System.out.println();
		System.out.println("---------------------");

		// 查詢
		List<TripReportVO> list = dao.getAll();
		for (TripReportVO atripreport : list) {
			System.out.print(atripreport.getTripno() + ",");
			System.out.print(atripreport.getMemno() + ",");
			System.out.print(atripreport.getReason() + ",");
			System.out.print(atripreport.getTripstatus() + ",");

			System.out.println();
		}
	}


}