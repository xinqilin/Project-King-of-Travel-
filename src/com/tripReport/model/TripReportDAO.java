package com.tripReport.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TripReportDAO implements TripReportDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				

				pstmt.setString(1, tripreportVO.getTripno());
				pstmt.setString(2, tripreportVO.getMemno());
				pstmt.setString(3, tripreportVO.getReason());
				pstmt.setInt(4, tripreportVO.getTripstatus());	
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, tripreportVO.getReason());
				pstmt.setInt(2, tripreportVO.getTripstatus());
				pstmt.setString(3, tripreportVO.getTripno());
				pstmt.setString(4, tripreportVO.getMemno());				
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, tripreportVO.getTripno());
				pstmt.setString(2, tripreportVO.getMemno());
				

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

				con = ds.getConnection();
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
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// tripDetilsVO 也稱為 Domain objects
					tripreportVO = new TripReportVO();
					tripreportVO.setTripno(rs.getString("tripno"));
					tripreportVO.setMemno(rs.getString("memno"));
					tripreportVO.setReason(rs.getString("reason"));
					tripreportVO.setTripstatus(rs.getInt("tripstatus"));
					list.add(tripreportVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
}