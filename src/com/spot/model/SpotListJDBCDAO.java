package com.spot.model;

import java.sql.*;
import java.util.*;

public class SpotListJDBCDAO implements SpotListDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G3";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO spotlist (spotno, spotname, cityno, location, spottype, spotphoto, spotstatus, tel, spotlati, spotlong, spotdetail) VALUES ('SPT'||LPAD(to_char(SpotList_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ? , ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT spotno, spotname, cityno, location, spottype, spotphoto, spotstatus, tel, spotlati, spotlong, spotdetail FROM spotlist order by spotno";
	private static final String GET_ONE_STMT =
			"SELECT spotno, spotname, cityno, location, spottype, spotphoto, spotstatus, tel, spotlati, spotlong, spotdetail FROM spotlist where spotno = ? ";
	private static final String DELETE =
			"DELETE FROM spotlist where spotno = ? ";
	private static final String UPDATE =
			"UPDATE spotlist set spotname=?, cityno=?, location=?, spottype=?, spotphoto=?, spotstatus=?, tel=?, spotlati=?, spotlong=?, spotdetail=? where spotno=? ";
	private static final String GET_ALL_NoPic = "SELECT spotno, spotname, cityno, location, spottype, spotstatus, tel, spotlati, spotlong, spotdetail FROM spotlist order by spotno";	
	@Override
	public void insert(SpotListVO spotListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, spotListVO.getSpotName());
			pstmt.setString(2, spotListVO.getCityNo());
			pstmt.setString(3, spotListVO.getLocation());
			pstmt.setInt(4, spotListVO.getSpotType());
			pstmt.setBytes(5, spotListVO.getSpotPhoto());
			pstmt.setInt(6, spotListVO.getSpotStatus());
			pstmt.setString(7, spotListVO.getTel());
			pstmt.setDouble(8, spotListVO.getSpotLati());
			pstmt.setDouble(9, spotListVO.getSpotLong());
			pstmt.setString(10, spotListVO.getSpotDetail());
			
			pstmt.executeUpdate();
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(SpotListVO spotListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, spotListVO.getSpotName());
			pstmt.setString(2, spotListVO.getCityNo());
			pstmt.setString(3, spotListVO.getLocation());
			pstmt.setInt(4, spotListVO.getSpotType());
			pstmt.setBytes(5, spotListVO.getSpotPhoto());
			pstmt.setInt(6, spotListVO.getSpotStatus());
			pstmt.setString(7, spotListVO.getTel());
			pstmt.setDouble(8, spotListVO.getSpotLati());
			pstmt.setDouble(9, spotListVO.getSpotLong());
			pstmt.setString(10, spotListVO.getSpotDetail());
			pstmt.setString(11, spotListVO.getSpotNo());
			
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String spotNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, spotNo);
			
			pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public SpotListVO findByPrimaryKey(String spotNo) {
		
		SpotListVO spotListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				spotListVO.setSpotPhoto(rs.getBytes("spotphoto"));
				spotListVO.setSpotStatus(rs.getInt("spotstatus"));
				spotListVO.setTel(rs.getString("tel"));
				spotListVO.setSpotLati(rs.getDouble("spotlati"));
				spotListVO.setSpotLong(rs.getDouble("spotLong"));
				spotListVO.setSpotDetail(rs.getString("spotdetail"));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return spotListVO;
	}

	@Override
	public List<SpotListVO> getAll() {
		List<SpotListVO> list = new ArrayList<>();
		SpotListVO spotListVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				spotListVO = new SpotListVO();
				spotListVO.setSpotNo(rs.getString("spotno"));
				spotListVO.setSpotName(rs.getString("spotname"));
				spotListVO.setCityNo(rs.getString("cityno"));
				spotListVO.setLocation(rs.getString("location"));
				spotListVO.setSpotType(rs.getInt("spottype"));
				spotListVO.setSpotPhoto(rs.getBytes("spotphoto"));
				spotListVO.setSpotStatus(rs.getInt("spotstatus"));
				spotListVO.setTel(rs.getString("tel"));
				spotListVO.setSpotLati(rs.getDouble("spotlati"));
				spotListVO.setSpotLong(rs.getDouble("spotlong"));
				spotListVO.setSpotDetail(rs.getString("spotdetail"));
				list.add(spotListVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
	
		SpotListJDBCDAO  dao = new SpotListJDBCDAO();
		
		//新增
//		SpotListVO spotListVO1 = new SpotListVO();
//		spotListVO1.setSpotName("復興SOGO");
//		spotListVO1.setCityNo("CIT0002");
//		spotListVO1.setLocation("台北市大安區忠孝東路三段300號");
//		spotListVO1.setSpotType(1);
//		spotListVO1.setSpotStatus(1);
//		spotListVO1.setTel("02-2776-5555");
//		spotListVO1.setSpotLati(new Double(25.041407));
//		spotListVO1.setSpotLong(new Double(121.542942));
//		spotListVO1.setSpotDetail("SOGO百貨為具有領導流行、高級形象、機能豐富、高品味、高格調之全客層百貨公司");
//		dao.insert(spotListVO1);
		
		//修改
		SpotListVO spotListVO2 = new SpotListVO();
		spotListVO2.setSpotNo("SPT000017");
		spotListVO2.setSpotName("忠孝SOGO");
		spotListVO2.setTel("02-2500-5600");
		spotListVO2.setSpotLati(23.76344);
		spotListVO2.setSpotLong(121.5694);
		spotListVO2.setSpotType(2);
		spotListVO2.setSpotStatus(0);
		spotListVO2.setSpotDetail("123131321");
		spotListVO2.setCityNo("CIT0002");
		spotListVO2.setLocation("xxxxx");
		dao.update(spotListVO2);
		
		//刪除
//		dao.delete("SPT000016");
		
//		//查詢
//		SpotListVO spotListVO3 = dao.findByPrimaryKey("SPT000003");
//		System.out.print(spotListVO3.getSpotNo() + ",");
//		System.out.print(spotListVO3.getSpotName() + ",");
//		System.out.print(spotListVO3.getSpotType() + ",");
//		System.out.print(spotListVO3.getSpotStatus() + ",");
//		System.out.print(spotListVO3.getLocation() + ",");
//		System.out.print(spotListVO3.getSpotDetail() + ",");
		
//		//查詢
//		List<SpotListVO> list = dao.getAll();
//		for(SpotListVO spl : list) {
//			System.out.print(spl.getSpotNo() + ",");
//			System.out.print(spl.getSpotName() + ",");
//			System.out.print(spl.getSpotDetail() + ",");
//			System.out.print(spl.getLocation() + ",");
//			System.out.println();
//			
//		}
		
	}

	@Override
	public List<SpotListVO> getAllNoPic() {
		// TODO Auto-generated method stub
		return null;
	}
}
