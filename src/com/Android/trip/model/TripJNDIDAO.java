package com.Android.trip.model;

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

import com.Android.trip.model.TripVO;

public class TripJNDIDAO  implements TripDAO_interface {
	private static final String getMemAllTrip  = 
			"Select * from TripList where memNo = (Select memNo from MemberList where e_mail = ?) and tripStatus = 1 order by TripEstDate desc";
	private static final String orderByViews = 
			"select * from Triplist where tripStatus = 1 order by timeofviews desc";
	private static final String GET_ONE_STMT = 
			"SELECT * from TripList where TRIPNO = ?";
	private static final String INSERT = 
			"Insert into TripList (tripNo, memNo, CITYNO, TRIPNAME, TRIPSTARTDAY, TRIPENDDAY, TRIPDAYS, KINDOFTRIP, TRIPESTDATE, BETHEBUYER, TRIPSTATUS, TIMEOFVIEWS) values ('TLI'||LPAD(to_char(TripList_SEQ.nextval),4,'0'), ?, ?, ?, ?, ?, ?, ?, sysdate, 0, 1, 0)";
	private static final String DELETE = 
			"Delete from TripList where tripNo = ?";
	private static final String UPDATE = 
			"UPDATE tripList SET TRIPNAME = ?, TRIPSTARTDAY =?, TRIPENDDAY =?, TRIPDAYS =?, KINDOFTRIP = ?, TRIPSTATUS = ? WHERE tripNo = ? ";
	
	
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
	public TripVO insertTrip(TripVO tripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] generatedKey = {"TRIPNO"};
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT, generatedKey);
			pstmt.setString(1, tripVO.getMemNo());
			pstmt.setString(2, tripVO.getCityNo());
			pstmt.setString(3, tripVO.getTripName());
			pstmt.setDate(4, tripVO.getTripStartDay());
			pstmt.setDate(5, tripVO.getTripEndDay());
			pstmt.setInt(6, tripVO.getTripDays());
			pstmt.setInt(7, tripVO.getKindOfTrip());	
			pstmt.executeUpdate(); 
			
			rs = pstmt.getGeneratedKeys();

			if(rs.next()) {
				String tripNo = rs.getString(1);
				tripVO.setTripNo(tripNo);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {con.close();
				} catch (Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return tripVO;
	}

	@Override
	public Boolean updateTrip(TripVO tripVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result =false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, tripVO.getTripName());
			pstmt.setDate(2, tripVO.getTripStartDay());
			pstmt.setDate(3, tripVO.getTripEndDay());
			pstmt.setInt(4, tripVO.getTripDays());
			pstmt.setInt(5, tripVO.getKindOfTrip());
			pstmt.setInt(6, tripVO.getTripStatus());
			pstmt.setString(7, tripVO.getTripNo());
			
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {con.close();
				} catch (Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return result;
	}

	@Override
	public boolean deleteTrip(String tripNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, tripNo);	
			result= pstmt.execute();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {con.close();
				} catch (Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return result;
	}

	@Override
	public List<TripVO> getMemAllTrips(String email) {
		List<TripVO> list= null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TripVO tripVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getMemAllTrip);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery(); 
			list = new ArrayList<TripVO>();
			
			while(rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripNo(rs.getString("tripno"));
				tripVO.setTripName(rs.getString("tripname"));
				tripVO.setTripStartDay(rs.getDate("tripstartday"));
				tripVO.setTripEndDay(rs.getDate("tripendday"));
				tripVO.setTripEstDate(rs.getDate("tripestdate"));
				tripVO.setTripDays(rs.getInt("tripdays"));
				tripVO.setBeTheBuyer(rs.getInt("bethebuyer"));
				tripVO.setMemNo(rs.getString("memno"));
				tripVO.setCityNo(rs.getString("cityno"));
				tripVO.setTripStatus(rs.getInt("tripstatus"));
				tripVO.setTimeOfViews(rs.getInt("timeofviews"));
				tripVO.setKindOfTrip(rs.getInt("kindoftrip"));
				list.add(tripVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (con != null) {
				try {con.close();
				} catch (Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<TripVO> getHotTrips() {
		List<TripVO> list= null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TripVO tripVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(orderByViews);
			rs = pstmt.executeQuery(); 
			list = new ArrayList<TripVO>();
			
			while(rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripNo(rs.getString("tripno"));
				tripVO.setTripName(rs.getString("tripname"));
				tripVO.setTripStartDay(rs.getDate("tripstartday"));
				tripVO.setTripEndDay(rs.getDate("tripendday"));
				tripVO.setTripEstDate(rs.getDate("tripestdate"));
				tripVO.setTripDays(rs.getInt("tripdays"));
				tripVO.setBeTheBuyer(rs.getInt("bethebuyer"));
				tripVO.setMemNo(rs.getString("memno"));
				tripVO.setCityNo(rs.getString("cityno"));
				tripVO.setTripStatus(rs.getInt("tripstatus"));
				tripVO.setTimeOfViews(rs.getInt("timeofviews"));
				tripVO.setKindOfTrip(rs.getInt("kindoftrip"));
				list.add(tripVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (con != null) {
				try {con.close();
				} catch (Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}


	@Override
	public TripVO getOneTrip(String tripNo) {
		TripVO tripVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con   = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, tripNo);
			rs    = pstmt.executeQuery();
			
			while(rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripNo(rs.getString("tripno"));
				tripVO.setTripName(rs.getString("tripname"));
				tripVO.setTripStartDay(rs.getDate("tripstartday"));
				tripVO.setTripEndDay(rs.getDate("tripendday"));
				tripVO.setTripEstDate(rs.getDate("tripestdate"));
				tripVO.setTripDays(rs.getInt("tripdays"));
				tripVO.setBeTheBuyer(rs.getInt("bethebuyer"));
				tripVO.setMemNo(rs.getString("memno"));
				tripVO.setCityNo(rs.getString("cityno"));
				tripVO.setTripStatus(rs.getInt("tripstatus"));
				tripVO.setTimeOfViews(rs.getInt("timeofviews"));
				tripVO.setKindOfTrip(rs.getInt("kindoftrip"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (con != null) {
				try {con.close();
				} catch (Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		
		return tripVO;
	}
}
