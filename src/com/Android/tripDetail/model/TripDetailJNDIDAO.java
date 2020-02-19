package com.Android.tripDetail.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Android.spot.model.*;


public class TripDetailJNDIDAO implements TripDetailDAO_interface {
	private static final String getTripDetailByTripNo = "Select * from tripDetails where (TripDayOrder = ? and tripNo = ?) order by tripOrderBy asc";
	private static final String getSpotByTripNo = "Select spotNo from tripDetails where tripNo = ? order by tripOrderBy asc";
	private static final String IS_SPOT_EXIST = "Select count(spotno) count from TripDetails where tripNo = ? and spotNo = ? ";
	private static final String INSERT = "Insert into TripDetails (TripNo, SpotNo, TripDayOrder, TripOrderBy, TimeOfArrive, TimeOfLeave, stayhours)  values (?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM TripDetails where TRIPNO = ? and TripDayOrder = ?";
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
	public void insert(TripDetailVO tripDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);		
			pstmt.setString(1, tripDetailVO.getTripno());
			pstmt.setString(2, tripDetailVO.getSpotno());
			pstmt.setInt(3, tripDetailVO.getTripdayorder());
			pstmt.setInt(4, tripDetailVO.getTriporderby());
			
			if( tripDetailVO.getTimeofarrive() == null) {
				java.sql.Time defaultTime = java.sql.Time.valueOf("00:00:00");
				pstmt.setTime(5, defaultTime);
				pstmt.setTime(6, defaultTime);
				pstmt.setInt(7, 0);
			}else{
				pstmt.setTime(5, tripDetailVO.getTimeofarrive());
				pstmt.setTime(6, tripDetailVO.getTimeofleave());
				pstmt.setInt(7, tripDetailVO.getStayhours());
			}
			
		
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(rs != null) {
				try {rs.close();} 
				catch(SQLException se){se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {pstmt.close();
				} catch(SQLException se) {se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {con.close();
				} catch(Exception e) {e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String  tripNo, Integer day) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);		
			pstmt.setString(1, tripNo);
			pstmt.setInt(2, day);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(rs != null) {
				try {rs.close();} 
				catch(SQLException se){se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {pstmt.close();
				} catch(SQLException se) {se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {con.close();
				} catch(Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	@Override
	public List<TripDetailVO> getTripDayDetail(int day, String tripNo){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TripDetailVO> tripDetail = new ArrayList<TripDetailVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getTripDetailByTripNo);
			pstmt.setInt(1, day);
			pstmt.setString(2, tripNo);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				TripDetailVO tripDetailVO = new TripDetailVO();
				tripDetailVO.setTripno(rs.getString("tripNo"));
				tripDetailVO.setSpotno(rs.getString("SpotNo"));
				tripDetailVO.setTripdayorder(rs.getInt("TRIPDAYORDER"));
				tripDetailVO.setTriporderby(rs.getInt("tripOrderBy"));
				tripDetailVO.setTriptips(rs.getString("TRIPTIPS"));
				tripDetailVO.setTimeofarrive(rs.getTime("timeofarrive"));
				tripDetailVO.setTimeofleave(rs.getTime("timeofleave"));
				tripDetailVO.setStayhours(rs.getInt("stayhours"));
				
				SpotListJNDIDAO spotDAO = new SpotListJNDIDAO();
				SpotListVO spotListVO = spotDAO.findByPrimaryKey(tripDetailVO.getSpotno());
				tripDetailVO.setSpotListVO(spotListVO);
				
				tripDetail.add(tripDetailVO);
			}				

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {con.close();
				} catch(Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return tripDetail;
	}
	
	
	@Override
	public Map<Integer, List<TripDetailVO>> getTripDetails(String tripNo, int tripDays) {
		Map<Integer,List<TripDetailVO>> tripDetailList = new LinkedHashMap<>();  //總行程
		List<TripDetailVO> tripDetail = new ArrayList<TripDetailVO>();  //單日行程

		for (int i = 1; i<=tripDays ; i++) {
			tripDetail = getTripDayDetail(i, tripNo);  //設置 i 為天數，使用 getTripDayDetail 可取得一天的景點行程		
			tripDetailList.put(i,tripDetail);  //將行程加入總行程中
		}
		return tripDetailList;
	}
	
	@Override
	public boolean isSpotExist(String tripNo, String spotNo) {
		boolean isExist = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(IS_SPOT_EXIST);
			
			pstmt.setString(1, tripNo);
			pstmt.setString(2, spotNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if(rs.getInt("count")==1) {
					isExist = true;
				}
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(rs != null) {
				try {rs.close();} 
				catch(SQLException se){se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {pstmt.close();
				} catch(SQLException se) {se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {con.close();
				} catch(Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		
		return isExist;
	}

	@Override
	public List<String> getSpotByTripNo(String tripNo) {
		List<String> spots = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getSpotByTripNo);		
			pstmt.setString(1, tripNo);
			rs    = pstmt.executeQuery();
					
			while(rs.next()) {
				spots.add(rs.getString(1));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(rs != null) {
				try {rs.close();} 
				catch(SQLException se){se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {pstmt.close();
				} catch(SQLException se) {se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {con.close();
				} catch(Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return spots;
	}
	
}
