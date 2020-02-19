package com.Android.spot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Android.tripDetail.model.TripDetailVO;


public class SpotListJNDIDAO implements SpotListDAO_interface {
	private static final String GET_ONE_STMT = "SELECT * FROM spotlist where spotno = ? ";
	private static final String GET_ALL_BY_CITY = "SELECT * FROM spotlist where cityNo = ? ";
	private static final String GET_ALL_SPOT = "SELECT * FROM spotlist";
	private static final String FIND_IMG_BY_SPOTNO = "SELECT SPOTPHOTO from SPOTLIST WHERE SPOTNO = ?";
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	List<SpotListVO> list = new ArrayList<>();
	SpotListVO spotListVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public List<SpotListVO> getSpotFromTrip(List<TripDetailVO> TripDetailList) {

		List<SpotListVO> spots = new ArrayList<SpotListVO>();

		for (TripDetailVO tripDayList : TripDetailList) {
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setString(1, tripDayList.getSpotno());
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				SpotListVO spotListVO = new SpotListVO();
				spotListVO.setSpotNo(tripDayList.getSpotno());
				spotListVO.setSpotName(rs.getString("SPOTNAME"));
				spotListVO.setSpotLati(rs.getDouble("SPOTLATI"));
				spotListVO.setSpotLong(rs.getDouble("SPOTLONG"));
				spotListVO.setSpotDetail(rs.getString("spotdetail"));
				spots.add(spotListVO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		}
		return spots;
	}

	@Override
	public SpotListVO findByPrimaryKey(String spotNo) {
		SpotListVO spotListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, spotNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				spotListVO = new SpotListVO();
				spotListVO.setSpotNo(rs.getString("spotno"));
				spotListVO.setSpotName(rs.getString("spotname"));
				spotListVO.setCityNo(rs.getString("cityno"));
				spotListVO.setLocation(rs.getString("location"));
				spotListVO.setSpotType(rs.getInt("spottype"));
				spotListVO.setSpotStatus(rs.getInt("spotstatus"));
				spotListVO.setTel(rs.getString("tel"));
				spotListVO.setSpotLati(rs.getDouble("spotlati"));
				spotListVO.setSpotLong(rs.getDouble("spotLong"));
				spotListVO.setSpotDetail(rs.getString("spotdetail"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		return spotListVO;
	}

	@Override
	public List<SpotListVO> getSpotByCityNo(String cityNo) {
		List<SpotListVO> spotList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_CITY);
			pstmt.setString(1, cityNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				spotListVO = new SpotListVO();
				spotListVO.setSpotNo(rs.getString("spotno"));
				spotListVO.setSpotName(rs.getString("spotname"));
				spotListVO.setCityNo(rs.getString("cityno"));
				spotListVO.setLocation(rs.getString("location"));
				spotListVO.setSpotType(rs.getInt("spottype"));
				spotListVO.setSpotStatus(rs.getInt("spotstatus"));
				spotListVO.setTel(rs.getString("tel"));
				spotListVO.setSpotLati(rs.getDouble("spotlati"));
				spotListVO.setSpotLong(rs.getDouble("spotLong"));
				spotListVO.setSpotDetail(rs.getString("spotdetail"));
				spotList.add(spotListVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		return spotList;
	}

	@Override
	public List<SpotListVO> getAllSpot() {
		List<SpotListVO> spotList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SPOT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				spotListVO = new SpotListVO();
				spotListVO.setSpotNo(rs.getString("spotno"));
				spotListVO.setSpotName(rs.getString("spotname"));
				spotListVO.setCityNo(rs.getString("cityno"));
				spotListVO.setLocation(rs.getString("location"));
				spotListVO.setSpotType(rs.getInt("spottype"));
				spotListVO.setSpotStatus(rs.getInt("spotstatus"));
				spotListVO.setTel(rs.getString("tel"));
				spotListVO.setSpotLati(rs.getDouble("spotlati"));
				spotListVO.setSpotLong(rs.getDouble("spotLong"));
				spotListVO.setSpotDetail(rs.getString("spotdetail"));
				spotList.add(spotListVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		return spotList;
	}
	
	public byte[] getImage(String spotNo) {
		byte[] picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_IMG_BY_SPOTNO);
			pstmt.setString(1, spotNo);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				picture = rs.getBytes(1);
			}
			// Handle any driver errors
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
		return picture;
	}
	
	
}
