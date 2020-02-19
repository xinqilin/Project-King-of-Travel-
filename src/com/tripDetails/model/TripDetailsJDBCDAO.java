package com.tripDetails.model;

import java.util.*;

import com.tripDetails.model.TripDetailsDAO_interface;
import com.tripDetails.model.TripDetailsJDBCDAO;
import com.tripDetails.model.TripDetailsVO;

import java.sql.*;

public class TripDetailsJDBCDAO implements TripDetailsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into TripDetails (TripNo, SpotNo, TimeOfArrive, TimeOfLeave, StayHours, MilestoNextSpots, TripDayOrder, TripOrderBy, TripTips)  values ( ?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT TripNo, SpotNo,to_char(TimeOfArrive,'HH24:MI:SS') TimeOfArrive,to_char(TimeOfLeave,'HH24:MI:SS') TimeOfLeave, StayHours, MilestoNextSpots, TripDayOrder, TripOrderBy, TripTips FROM TripDetails order by TripNo";
	private static final String GET_ONE_STMT = "SELECT TripNo, SpotNo,to_char(TimeOfArrive,'HH24:MI:SS') TimeOfArrive,to_char(TimeOfLeave,'HH24:MI:SS') TimeOfLeave, StayHours, MilestoNextSpots, TripDayOrder, TripOrderBy, TripTips FROM TripDetails where TRIPNO = ? and spotno=?";
	private static final String DELETE = "DELETE FROM TripDetails where TRIPNO = ? and SPOTNO=?";
	private static final String UPDATE = "UPDATE TripDetails set TimeOfArrive=?, TimeOfLeave=?, StayHours=?, MilestoNextSpots=?, TripDayOrder=?, TripOrderBy=?, TripTips=? where TRIPNO = ? and SpotNo=?";
	private static final String GET_ONE_TRIP = "select * from tripdetails where tripno=? order by tripdayorder, triporderby";
	
	
	
	@Override
	public void insert(TripDetailsVO tripDetailsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tripDetailsVO.getTripno());
			pstmt.setString(2, tripDetailsVO.getSpotno());
			pstmt.setTime(3, tripDetailsVO.getTimeofarrive());
			pstmt.setTime(4, tripDetailsVO.getTimeofleave());
			pstmt.setInt(5, tripDetailsVO.getStayhours());
			pstmt.setDouble(6, tripDetailsVO.getMilestonextspots());
			pstmt.setInt(7, tripDetailsVO.getTripdayorder());
			pstmt.setInt(8, tripDetailsVO.getTriporderby());
			pstmt.setString(9, tripDetailsVO.getTriptips());
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
	public void update(TripDetailsVO tripDetilsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTime(1, tripDetilsVO.getTimeofarrive());
			pstmt.setTime(2, tripDetilsVO.getTimeofleave());
			pstmt.setInt(3, tripDetilsVO.getStayhours());
			pstmt.setDouble(4, tripDetilsVO.getMilestonextspots());
			pstmt.setInt(5, tripDetilsVO.getTripdayorder());
			pstmt.setInt(6, tripDetilsVO.getTriporderby());
			pstmt.setString(7, tripDetilsVO.getTriptips());
			pstmt.setString(8, tripDetilsVO.getTripno());
			pstmt.setString(9, tripDetilsVO.getSpotno());

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
	public void delete(String tripno,String spotno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, tripno);
			pstmt.setString(2, spotno);

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
	public TripDetailsVO findByPrimaryKey(String tripno, String spotno) {

		TripDetailsVO tripDetilsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, tripno);
			pstmt.setString(2, spotno);

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
		return tripDetilsVO;
	}

	@Override
	public List<TripDetailsVO> getAll() {
		List<TripDetailsVO> list = new ArrayList<TripDetailsVO>();
		TripDetailsVO tripDetilsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tripDetilsVO 也稱為 Domain objects
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

		TripDetailsJDBCDAO dao = new TripDetailsJDBCDAO();

		// 新增
//		TripDetailsVO tripDetilsVO1 = new TripDetailsVO();
//		tripDetilsVO1.setTripno("TLI0014");
//		tripDetilsVO1.setSpotno("SPT000006");
//		tripDetilsVO1.setTimeofarrive(java.sql.Time.valueOf("15:51:37"));
//		tripDetilsVO1.setTimeofleave(java.sql.Time.valueOf("15:51:37"));
//		tripDetilsVO1.setStayhours(1);
//		tripDetilsVO1.setMilestonextspots(2.2);
//		tripDetilsVO1.setTripdayorder(1);
//		tripDetilsVO1.setTriporderby(3);
//		tripDetilsVO1.setTriptips("玩一下啊");
//		dao.insert(tripDetilsVO1);
//		System.out.println("已新增!");

//		// 修改
//		TripDetailsVO tripDetilsVO2 = new TripDetailsVO();
//		tripDetilsVO2.setTimeofarrive(java.sql.Time.valueOf("7:00:00"));
//		tripDetilsVO2.setTimeofleave(java.sql.Time.valueOf("13:00:00"));
//		tripDetilsVO2.setStayhours(2);
//		tripDetilsVO2.setMilestonextspots(2.2);
//		tripDetilsVO2.setTripdayorder(1);
//		tripDetilsVO2.setTriporderby(3);
//		tripDetilsVO2.setTriptips("玩一下啊!!!!!!!!!!!!!!!!!!!!!!");
//		tripDetilsVO2.setTripno("TLI0014");
//		tripDetilsVO2.setSpotno("SPT000006");
//		dao.update(tripDetilsVO2);
//		System.out.println("已修改");

		// 刪除 
//		dao.delete("TLI0002", "SPT000048");
//		System.out.println("已刪除!");

//		// 查詢
//		TripDetailsVO tripDetilsVO3 = dao.findByPrimaryKey("TLI0001","SPT000001");
//		System.out.print(tripDetilsVO3.getTripno() + ",");
//		System.out.print(tripDetilsVO3.getSpotno() + ",");
//		System.out.printf(tripDetilsVO3.getTimeofarrive().toString().substring(0,5)+ " ,");
//		System.out.printf(tripDetilsVO3.getTimeofleave().toString().substring(0,5)+ " ,");
//		System.out.print(tripDetilsVO3.getStayhours() + " ,");
//		System.out.print(tripDetilsVO3.getMilestonextspots() + " ,");
//		System.out.print(tripDetilsVO3.getTripdayorder() + " ,");
//		System.out.print(tripDetilsVO3.getTriporderby()+ " ,");
//		System.out.print(tripDetilsVO3.getTriptips() + " ,");
//		System.out.println();
//		System.out.println("---------------------");

//		List<TripDetailsVO> list1 = dao.findByTripno("TLI0002");
//		for (TripDetailsVO a : list1) {
//			System.out.print(a.getTripno() + ",");
//			System.out.print(a.getSpotno() + ",");
////			System.out.printf(aTripDetils.getTimeofarrive().getHours()+":"+aTripDetils.getTimeofarrive().getMinutes() + ",");
//			System.out.printf(a.getTimeofarrive().toString().substring(0, 5) + " ,");
//			System.out.printf(a.getTimeofleave().toString().substring(0, 5) + " ,");
//			System.out.print(a.getStayhours() + " ,");
//			System.out.print(a.getMilestonextspots() + " ,");
//			System.out.print(a.getTripdayorder() + " ,");
//			System.out.print(a.getTriporderby() + " ,");
//			System.out.print(a.getTriptips() + " ,");
//			System.out.println();
//			System.out.println("---------------------");
//		}
			// 查詢
			List<TripDetailsVO> list = dao.getAll();
			for (TripDetailsVO aTripDetils : list) {
				System.out.print(aTripDetils.getTripno() + ",");
				System.out.print(aTripDetils.getSpotno() + ",");
//			System.out.printf(aTripDetils.getTimeofarrive().getHours()+":"+aTripDetils.getTimeofarrive().getMinutes() + ",");
				System.out.printf(aTripDetils.getTimeofarrive().toString().substring(0, 5) + " ,");
				System.out.printf(aTripDetils.getTimeofleave().toString().substring(0, 5) + " ,");
				System.out.print(aTripDetils.getStayhours() + " ,");
				System.out.print(aTripDetils.getMilestonextspots() + " ,");
				System.out.print(aTripDetils.getTripdayorder() + " ,");
				System.out.print(aTripDetils.getTriporderby() + " ,");
				System.out.print(aTripDetils.getTriptips() + " ,");
				System.out.println();
		}
	
	
	
	
	}

	@Override
	public List<TripDetailsVO> findByTripno(String tripno) {

		List<TripDetailsVO> list = new ArrayList<TripDetailsVO>();
		TripDetailsVO tripDetilsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
}