package com.tripDetails.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tripDetails.model.TripDetailsDAO_interface;
import com.tripDetails.model.TripDetailsVO;

public class TripDetailsDAO implements TripDetailsDAO_interface {

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

	private static final String INSERT_STMT = 
			"Insert into TripDetails (TripNo, SpotNo, TimeOfArrive, TimeOfLeave, StayHours, MilestoNextSpots, TripDayOrder, TripOrderBy, TripTips)  values ( ?,?,?,?,?,?,?,?,?)";	
		private static final String GET_ALL_STMT = 
			"SELECT TripNo, SpotNo,to_char(TimeOfArrive,'HH24:MI:SS') TimeOfArrive,to_char(TimeOfLeave,'HH24:MI:SS') TimeOfLeave, StayHours, MilestoNextSpots, TripDayOrder, TripOrderBy, TripTips FROM TripDetails order by TripNo";
		private static final String GET_ONE_STMT = 
			"SELECT TripNo, SpotNo,to_char(TimeOfArrive,'HH24:MI:SS') TimeOfArrive,to_char(TimeOfLeave,'HH24:MI:SS') TimeOfLeave, StayHours, MilestoNextSpots, TripDayOrder, TripOrderBy, TripTips FROM TripDetails where TRIPNO = ? and spotno=?";
		private static final String DELETE = 
			"DELETE FROM TripDetails where TRIPNO = ? and SPOTNO=?";
		private static final String UPDATE = 
			"UPDATE TripDetails set TimeOfArrive=?, TimeOfLeave=?, StayHours=?, MilestoNextSpots=?, TripDayOrder=?, TripOrderBy=?, TripTips=? where TRIPNO = ? and SpotNo=?";

		private static final String GET_ONE_TRIP="select * from tripdetails where tripno=? order by tripdayorder, triporderby";
		
		@Override
		public void insert(TripDetailsVO tripDetailsVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, tripDetailsVO.getTripno());
				pstmt.setString(2, tripDetailsVO.getSpotno());
				pstmt.setTime(3, tripDetailsVO.getTimeofarrive());
				pstmt.setTime(4, tripDetailsVO.getTimeofleave());
				pstmt.setInt(5, tripDetailsVO.getStayhours());
				pstmt.setDouble(6, tripDetailsVO.getMilestonextspots());
				pstmt.setInt(7, tripDetailsVO.getTripdayorder());
				pstmt.setInt(8, tripDetailsVO.getTriporderby());
				pstmt.setString(9,tripDetailsVO.getTriptips());		
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
		public void update(TripDetailsVO tripDetailsVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setTime(1, tripDetailsVO.getTimeofarrive());
				pstmt.setTime(2, tripDetailsVO.getTimeofleave());
				pstmt.setInt(3, tripDetailsVO.getStayhours());
				pstmt.setDouble(4, tripDetailsVO.getMilestonextspots());
				pstmt.setInt(5, tripDetailsVO.getTripdayorder());
				pstmt.setInt(6, tripDetailsVO.getTriporderby());
				pstmt.setString(7,tripDetailsVO.getTriptips());	
				pstmt.setString(8, tripDetailsVO.getTripno());
				pstmt.setString(9, tripDetailsVO.getSpotno());
				
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
		public void delete(String tripno,String spotno) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, tripno);
				pstmt.setString(2, spotno);
				

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
		public TripDetailsVO findByPrimaryKey(String tripno,String spotno) {

			TripDetailsVO tripDetailsVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, tripno);
				pstmt.setString(2, spotno);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					tripDetailsVO = new TripDetailsVO();
					tripDetailsVO.setTripno(rs.getString("tripno"));
					tripDetailsVO.setSpotno(rs.getString("spotno"));
					tripDetailsVO.setTimeofarrive(rs.getTime("timeofarrive"));
					tripDetailsVO.setTimeofleave(rs.getTime("timeofleave"));
					tripDetailsVO.setStayhours(rs.getInt("stayhours"));
					tripDetailsVO.setMilestonextspots(rs.getDouble("milestonextspots"));
					tripDetailsVO.setTripdayorder(rs.getInt("tripdayorder"));
					tripDetailsVO.setTriporderby(rs.getInt("triporderby"));
					tripDetailsVO.setTriptips(rs.getString("triptips"));
					
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
			return tripDetailsVO;
		}

		@Override
		public List<TripDetailsVO> getAll() {
			List<TripDetailsVO> list = new ArrayList<TripDetailsVO>();
			TripDetailsVO tripDetailsVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// tripDetilsVO 也稱為 Domain objects
					tripDetailsVO = new TripDetailsVO();
					tripDetailsVO.setTripno(rs.getString("tripno"));
					tripDetailsVO.setSpotno(rs.getString("spotno"));
					tripDetailsVO.setTimeofarrive(rs.getTime("timeofarrive"));
					tripDetailsVO.setTimeofleave(rs.getTime("timeofleave"));
					tripDetailsVO.setStayhours(rs.getInt("stayhours"));
					tripDetailsVO.setMilestonextspots(rs.getDouble("milestonextspots"));
					tripDetailsVO.setTripdayorder(rs.getInt("tripdayorder"));
					tripDetailsVO.setTriporderby(rs.getInt("triporderby"));
					tripDetailsVO.setTriptips(rs.getString("triptips"));
					list.add(tripDetailsVO); // Store the row in the list
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
		
		@Override
		public List<TripDetailsVO> findByTripno(String tripno) {

			List<TripDetailsVO> list = new ArrayList<TripDetailsVO>();
			TripDetailsVO tripDetilsVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			System.out.println("DAO拿到: " + tripno);
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_TRIP);
				pstmt.setString(1, tripno);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					tripDetilsVO = new TripDetailsVO();
					tripDetilsVO.setTripno(rs.getString("tripno"));
					tripDetilsVO.setSpotno(rs.getString("spotno"));
					tripDetilsVO.setTimeofarrive(rs.getTime("timeofarrive"));
					tripDetilsVO.setTimeofleave(rs.getTime("timeofleave"));
					tripDetilsVO.setStayhours(rs.getInt("stayhours"));
					tripDetilsVO.setMilestonextspots(rs.getDouble("milestonextspots"));
					tripDetilsVO.setTripdayorder(rs.getInt("tripdayorder"));
					tripDetilsVO.setTriporderby(rs.getInt("triporderby"));
					tripDetilsVO.setTriptips(rs.getString("triptips"));
					list.add(tripDetilsVO); // Store the row in the list
				}
				

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