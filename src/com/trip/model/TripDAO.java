package com.trip.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TripDAO implements TripDAO_interface {

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
			"Insert into TRIPLIST(TripNo,MemNo,CityNo,TripName,TripStartDay,TripEndDay,TripDays,TripEstDate,BeTheBuyer,TripStatus,TimeOfViews,KindOfTrip,trippic) values ( 'TLI'||LPAD(to_char(TRIPLIST_SEQ.nextval),4,'0'),?,?,?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
				"SELECT TripNo,MemNo,CityNo,TripName,to_char(TripStartDay,'yyyy-mm-dd') TripStartDay,to_char(TripEndDay,'yyyy-mm-dd') TripEndDay,TripDays,to_char(TripEstDate,'yyyy-mm-dd') TripEstDate,BeTheBuyer,TripStatus,TimeOfViews,KindOfTrip,trippic FROM TRIPLIST order by TripNo";
		private static final String GET_ONE_STMT = 
				"SELECT TripNo,MemNo,CityNo,TripName,to_char(TripStartDay,'yyyy-mm-dd')  TripStartDay,to_char(TripEndDay,'yyyy-mm-dd') TripEndDay,TripDays,to_char(TripEstDate,'yyyy-mm-dd') TripEstDate,BeTheBuyer,TripStatus,TimeOfViews,KindOfTrip,trippic FROM TRIPLIST where TRIPNO = ?";
		private static final String UPDATE = 
				"UPDATE TRIPLIST set MemNo=?,CityNo=?,TripName=?,TripStartDay=?,TripEndDay=?,TripDays=?,TripEstDate=?,BeTheBuyer=?,TripStatus=?,TimeOfViews=?,KindOfTrip=?,trippic=? where TRIPNO = ?";
		private static final String DELETE = "UPDATE TRIPLIST SET TRIPSTATUS=2 WHERE TRIPNO=?";
		private static final String orderByDate="select * from triplist order by tripestdate desc";
		private static final String orderByViews="select * from triplist order by timeofviews desc";
		private static final String orderByCity="select * from triplist order by cityno";
		private static final String orderByDays="select * from triplist order by tripdays desc";
		private static final String ACCEPT = "UPDATE TRIPLIST SET TRIPSTATUS=1 WHERE TRIPNO=?";
		private static final String lastOne="select * FROM (select * FROM triplist  ORDER BY tripno DESC) where  rownum <=1";
		
	@Override
	public void insert(TripVO tripVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tripVO.getMemno());
			pstmt.setString(2, tripVO.getCityno());
			pstmt.setString(3, tripVO.getTripname());
			pstmt.setDate(4, tripVO.getTripstartday());
			pstmt.setDate(5, tripVO.getTripendday());
			pstmt.setInt(6, tripVO.getTripdays());
			pstmt.setDate(7, tripVO.getTripestdate());
			pstmt.setInt(8,tripVO.getBethebuyer());
			pstmt.setInt(9, tripVO.getTripstatus());
			pstmt.setInt(10, tripVO.getTimeofviews());
			pstmt.setInt(11, tripVO.getKindoftrip());
			pstmt.setBytes(12, tripVO.getTrippic());
			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(TripVO tripVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, tripVO.getMemno());
			pstmt.setString(2, tripVO.getCityno());
			pstmt.setString(3, tripVO.getTripname());
			pstmt.setDate(4, tripVO.getTripstartday());
			pstmt.setDate(5, tripVO.getTripendday());
			pstmt.setInt(6, tripVO.getTripdays());
			pstmt.setDate(7, tripVO.getTripestdate());
			pstmt.setInt(8,tripVO.getBethebuyer());
			pstmt.setInt(9, tripVO.getTripstatus());
			pstmt.setInt(10, tripVO.getTimeofviews());
			pstmt.setInt(11, tripVO.getKindoftrip());
			pstmt.setBytes(12, tripVO.getTrippic());
			pstmt.setString(13, tripVO.getTripno());
			
			
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
	public void delete(String tripno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, tripno);

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
	public void accept(String tripno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ACCEPT);

			pstmt.setString(1, tripno);

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
	public TripVO findByPrimaryKey(String tripno) {

		TripVO tripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, tripno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				//tripVo 也稱為 Domain objects
				tripVO = new TripVO();
				tripVO.setTripno(rs.getString("tripno"));
				tripVO.setTripname(rs.getString("tripname"));
				tripVO.setTripstartday(rs.getDate("tripstartday"));
				tripVO.setTripendday(rs.getDate("tripendday"));
				tripVO.setTripestdate(rs.getDate("tripestdate"));
				tripVO.setTripdays(rs.getInt("tripdays"));
				tripVO.setBethebuyer(rs.getInt("bethebuyer"));
				tripVO.setMemno(rs.getString("memno"));
				tripVO.setCityno(rs.getString("cityno"));
				tripVO.setTripstatus(rs.getInt("tripstatus"));
				tripVO.setTimeofviews(rs.getInt("timeofviews"));
				tripVO.setKindoftrip(rs.getInt("kindoftrip"));
				tripVO.setTrippic(rs.getBytes("trippic"));
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
		return tripVO;
	}

	@Override
	public List<TripVO> getAll() {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//tripVo 也稱為 Domain objects
				tripVO = new TripVO();
				tripVO.setTripno(rs.getString("tripno"));
				tripVO.setTripname(rs.getString("tripname"));
				tripVO.setTripstartday(rs.getDate("tripstartday"));
				tripVO.setTripendday(rs.getDate("tripendday"));
				tripVO.setTripestdate(rs.getDate("tripestdate"));
				tripVO.setTripdays(rs.getInt("tripdays"));
				tripVO.setBethebuyer(rs.getInt("bethebuyer"));
				tripVO.setMemno(rs.getString("memno"));
				tripVO.setCityno(rs.getString("cityno"));
				tripVO.setTripstatus(rs.getInt("tripstatus"));
				tripVO.setTimeofviews(rs.getInt("timeofviews"));
				tripVO.setKindoftrip(rs.getInt("kindoftrip"));
				tripVO.setTrippic(rs.getBytes("trippic"));
				list.add(tripVO); // Store the row in the list
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
	public List<TripVO> orderByDate() {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(orderByDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripno(rs.getString("tripno"));
				tripVO.setTripname(rs.getString("tripname"));
				tripVO.setTripstartday(rs.getDate("tripstartday"));
				tripVO.setTripendday(rs.getDate("tripendday"));
				tripVO.setTripestdate(rs.getDate("tripestdate"));
				tripVO.setTripdays(rs.getInt("tripdays"));
				tripVO.setBethebuyer(rs.getInt("bethebuyer"));
				tripVO.setMemno(rs.getString("memno"));
				tripVO.setCityno(rs.getString("cityno"));
				tripVO.setTripstatus(rs.getInt("tripstatus"));
				tripVO.setTimeofviews(rs.getInt("timeofviews"));
				tripVO.setKindoftrip(rs.getInt("kindoftrip"));
				tripVO.setTrippic(rs.getBytes("trippic"));
				list.add(tripVO); // Store the row in the list
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
	public List<TripVO> orderByViews() {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(orderByViews);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripno(rs.getString("tripno"));
				tripVO.setTripname(rs.getString("tripname"));
				tripVO.setTripstartday(rs.getDate("tripstartday"));
				tripVO.setTripendday(rs.getDate("tripendday"));
				tripVO.setTripestdate(rs.getDate("tripestdate"));
				tripVO.setTripdays(rs.getInt("tripdays"));
				tripVO.setBethebuyer(rs.getInt("bethebuyer"));
				tripVO.setMemno(rs.getString("memno"));
				tripVO.setCityno(rs.getString("cityno"));
				tripVO.setTripstatus(rs.getInt("tripstatus"));
				tripVO.setTimeofviews(rs.getInt("timeofviews"));
				tripVO.setKindoftrip(rs.getInt("kindoftrip"));
				tripVO.setTrippic(rs.getBytes("trippic"));
				list.add(tripVO); // Store the row in the list
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
	public List<TripVO> orderByCity() {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(orderByCity);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripno(rs.getString("tripno"));
				tripVO.setTripname(rs.getString("tripname"));
				tripVO.setTripstartday(rs.getDate("tripstartday"));
				tripVO.setTripendday(rs.getDate("tripendday"));
				tripVO.setTripestdate(rs.getDate("tripestdate"));
				tripVO.setTripdays(rs.getInt("tripdays"));
				tripVO.setBethebuyer(rs.getInt("bethebuyer"));
				tripVO.setMemno(rs.getString("memno"));
				tripVO.setCityno(rs.getString("cityno"));
				tripVO.setTripstatus(rs.getInt("tripstatus"));
				tripVO.setTimeofviews(rs.getInt("timeofviews"));
				tripVO.setKindoftrip(rs.getInt("kindoftrip"));
				tripVO.setTrippic(rs.getBytes("trippic"));
				list.add(tripVO); // Store the row in the list
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
	public List<TripVO> orderByDays() {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(orderByDays);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripno(rs.getString("tripno"));
				tripVO.setTripname(rs.getString("tripname"));
				tripVO.setTripstartday(rs.getDate("tripstartday"));
				tripVO.setTripendday(rs.getDate("tripendday"));
				tripVO.setTripestdate(rs.getDate("tripestdate"));
				tripVO.setTripdays(rs.getInt("tripdays"));
				tripVO.setBethebuyer(rs.getInt("bethebuyer"));
				tripVO.setMemno(rs.getString("memno"));
				tripVO.setCityno(rs.getString("cityno"));
				tripVO.setTripstatus(rs.getInt("tripstatus"));
				tripVO.setTimeofviews(rs.getInt("timeofviews"));
				tripVO.setKindoftrip(rs.getInt("kindoftrip"));
				tripVO.setTrippic(rs.getBytes("trippic"));
				list.add(tripVO); // Store the row in the list
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
	public TripVO lastTrip() {
		TripVO tripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(lastOne);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				//tripVo 也稱為 Domain objects
				tripVO = new TripVO();
				tripVO.setTripno(rs.getString("tripno"));
				tripVO.setTripname(rs.getString("tripname"));
				tripVO.setTripstartday(rs.getDate("tripstartday"));
				tripVO.setTripendday(rs.getDate("tripendday"));
				tripVO.setTripestdate(rs.getDate("tripestdate"));
				tripVO.setTripdays(rs.getInt("tripdays"));
				tripVO.setBethebuyer(rs.getInt("bethebuyer"));
				tripVO.setMemno(rs.getString("memno"));
				tripVO.setCityno(rs.getString("cityno"));
				tripVO.setTripstatus(rs.getInt("tripstatus"));
				tripVO.setTimeofviews(rs.getInt("timeofviews"));
				tripVO.setKindoftrip(rs.getInt("kindoftrip"));
				tripVO.setTrippic(rs.getBytes("trippic"));
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
		return tripVO;
	}
}