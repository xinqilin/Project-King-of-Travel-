package com.trip.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class TripJDBCDAO implements TripDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G3";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			    "Insert into TRIPLIST(TripNo,MemNo,CityNo,TripName,TripStartDay,TripEndDay,TripDays,TripEstDate,BeTheBuyer,TripStatus,TimeOfViews,KindOfTrip,trippic) values ( 'TLI'||LPAD(to_char(TRIPLIST_SEQ.nextval),4,'0'),?,?,?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
				"SELECT TripNo,MemNo,CityNo,TripName,to_char(TripStartDay,'yyyy-mm-dd') TripStartDay,to_char(TripEndDay,'yyyy-mm-dd') TripEndDay,TripDays,to_char(TripEstDate,'yyyy-mm-dd') TripEstDate,BeTheBuyer,TripStatus,TimeOfViews,KindOfTrip,trippic FROM TRIPLIST order by TripNo";
		private static final String GET_ONE_STMT = 
				"SELECT TripNo,MemNo,CityNo,TripName,to_char(TripStartDay,'yyyy-mm-dd')  TripStartDay,to_char(TripEndDay,'yyyy-mm-dd') TripEndDay,TripDays,to_char(TripEstDate,'yyyy-mm-dd') TripEstDate,BeTheBuyer,TripStatus,TimeOfViews,KindOfTrip,trippic FROM TRIPLIST where TRIPNO = ?";
		private static final String UPDATE = 
				"UPDATE TRIPLIST set MemNo=?,CityNo=?,TripName=?,TripStartDay=?,TripEndDay=?,TripDays=?,TripEstDate=?,BeTheBuyer=?,TripStatus=?,TimeOfViews=?,KindOfTrip=?,trippic=? where TRIPNO = ?";
		private static final String DELETE = "UPDATE TRIPLIST SET TRIPSTATUS=2 WHERE TRIPNO=?";
		private static final String ACCEPT = "UPDATE TRIPLIST SET TRIPSTATUS=1 WHERE TRIPNO=?";
		private static final String orderByDate="select * from triplist order by tripestdate desc";
		private static final String orderByViews="select * from triplist order by timeofviews desc";
		private static final String orderByCity="select * from triplist order by cityno";
		private static final String orderByDays="select * from triplist order by tripdays desc";
		private static final String lastOne="select * FROM (select * FROM triplist  ORDER BY tripno DESC) where  rownum <=1";

	@Override
	public void insert(TripVO tripVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String tripno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, tripno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void accept(String tripno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(ACCEPT);

			pstmt.setString(1, tripno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public TripVO findByPrimaryKey(String tripno) {

		TripVO tripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, tripno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tripVo 也稱為 Domain objects
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tripVO 也稱為 Domain objects
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		TripJDBCDAO dao = new TripJDBCDAO();

//		// 新增
//		TripVO tripVO1 = new TripVO();
//		tripVO1.setTripname("吳永志1");
//		tripVO1.setMemno("MEM0012");
//		tripVO1.setCityno("CIT0005");
//		tripVO1.setTripstartday(java.sql.Date.valueOf("2005-01-01"));
//		tripVO1.setTripendday(java.sql.Date.valueOf("2005-01-02"));
//		tripVO1.setTripestdate(java.sql.Date.valueOf("2005-01-01"));
//		tripVO1.setBethebuyer(1);
//		tripVO1.setTripdays(2);
//		tripVO1.setTripstatus(1);
//		tripVO1.setTimeofviews(900);
//		tripVO1.setKindoftrip(6);
//		dao.insert(tripVO1);
//		System.out.println("新增!");


		
//		// 修改
//		TripVO 	tripVO2 = new TripVO();
//		tripVO2.setTripno("TLI0015");
//		tripVO2.setTripname("吳永志2");
//		tripVO2.setMemno("MEM0011");
//		tripVO2.setCityno("CIT0005");
//		tripVO2.setTripstartday(java.sql.Date.valueOf("2005-01-01"));
//		tripVO2.setTripendday(java.sql.Date.valueOf("2005-01-02"));
//		tripVO2.setTripestdate(java.sql.Date.valueOf("2005-01-01"));
//		tripVO2.setBethebuyer(1);
//		tripVO2.setTripdays(2);
//		tripVO2.setTripstatus(0);
//		tripVO2.setTimeofviews(800);
//		tripVO2.setKindoftrip(5);
//		dao.update(tripVO2);

		// 刪除
//		dao.delete("TLI0012");

//		// 查詢
//		TripVO tripVO3 = dao.findByPrimaryKey("TLI0012");
//		System.out.print(tripVO3.getTripno() + ",");
//		System.out.print(tripVO3.getMemno() + ",");
//		System.out.print(tripVO3.getCityno() + ",");
//		System.out.print(tripVO3.getTripname() + " ,");
//		System.out.print(tripVO3.getTripstartday() + " ,");
//		System.out.print(tripVO3.getTripendday() + " ,");
//		System.out.print(tripVO3.getTripdays() + " ,");
//		System.out.print(tripVO3.getTripestdate() + " ,");
//		System.out.print(tripVO3.getBethebuyer() + " ,");
//		System.out.print(tripVO3.getTripstatus() + " ,");
//		System.out.print(tripVO3.getTimeofviews() + " ,");
//		System.out.print(tripVO3.getKindoftrip() );
//		
//		System.out.println();
//		System.out.println("---------------------");

		//查詢
//		List<TripVO> list = dao.getAll();
//		for (TripVO aTrip : list) {
//			System.out.print(aTrip.getTripno() + ",");
//			System.out.print(aTrip.getMemno() + ",");
//			System.out.print(aTrip.getCityno() + ",");
//			System.out.print(aTrip.getTripname() + " ,");
//			System.out.print(aTrip.getTripstartday() + " ,");
//			System.out.print(aTrip.getTripendday() + " ,");
//			System.out.print(aTrip.getTripdays() + " ,");
//			System.out.print(aTrip.getTripestdate() + " ,");
//			System.out.print(aTrip.getBethebuyer() + " ,");
//			System.out.print(aTrip.getTripstatus() + " ,");
//			System.out.print(aTrip.getTimeofviews() + " ,");
//			System.out.print(aTrip.getKindoftrip());
//			
//			System.out.println();
//		}
		
		
		
		TripVO tripVO8 = dao.lastTrip();
		System.out.print(tripVO8.getTripno() + ",");
		System.out.print(tripVO8.getMemno() + ",");
		System.out.print(tripVO8.getCityno() + ",");
		System.out.print(tripVO8.getTripname() + " ,");
		System.out.print(tripVO8.getTripstartday() + " ,");
		System.out.print(tripVO8.getTripendday() + " ,");
		System.out.print(tripVO8.getTripdays() + " ,");
		System.out.print(tripVO8.getTripestdate() + " ,");
		System.out.print(tripVO8.getBethebuyer() + " ,");
		System.out.print(tripVO8.getTripstatus() + " ,");
		System.out.print(tripVO8.getTimeofviews() + " ,");
		System.out.print(tripVO8.getKindoftrip() );
		
		System.out.println();
		System.out.println("---------------------");
		
		
	}

	@Override
	public List<TripVO> orderByDate() {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(lastOne);


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
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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