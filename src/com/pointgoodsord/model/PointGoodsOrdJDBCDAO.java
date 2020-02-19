package com.pointgoodsord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pgorddetails.model.PGOrdDetailsVO;

public class PointGoodsOrdJDBCDAO implements PointGoodsOrdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "YUGI";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"Insert into PointGoodsOrd (PointGoodsOrdNo, MemNo, Address, OrdStatus, OrderPoint) values ('PGO'||LPAD(to_char(PointGoodsOrdNo_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT PointGoodsOrdNo, MemNo, Address, OrdStatus, OrderPoint FROM PointGoodsOrd order by PointGoodsOrdNo";
	private static final String GET_ONE_STMT =
			"SELECT PointGoodsOrdNo, MemNo, Address, OrdStatus, OrderPoint FROM PointGoodsOrd where PointGoodsOrdNo=?";
	private static final String DELETE_STMT = 
			"DELETE FROM PointGoodsOrd where PointGoodsOrdNo=?";
	private static final String UPDATE_STMT =
			"UPDATE PointGoodsOrd set MemNo=?, Address=?, OrdStatus=?, OrderPoint=? where PointGoodsOrdNo=?";
	@Override
	public void insert(PointGoodsOrdVO pointgoodsordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, pointgoodsordVO.getMemno());
			pstmt.setString(2, pointgoodsordVO.getAddress());
			pstmt.setInt(3, pointgoodsordVO.getOrderstatus());
			pstmt.setInt(4, pointgoodsordVO.getOrderpoint());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(PointGoodsOrdVO pointgoodsordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, pointgoodsordVO.getMemno());
			pstmt.setString(2, pointgoodsordVO.getAddress());
			pstmt.setInt(3, pointgoodsordVO.getOrderstatus());
			pstmt.setInt(4, pointgoodsordVO.getOrderpoint());
			pstmt.setString(5, pointgoodsordVO.getPointgoodsordno());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String pointgoodsordno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url ,userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, pointgoodsordno);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public PointGoodsOrdVO findByPrimaryKey(String pointgoodsordno) {
		PointGoodsOrdVO pointgoodsordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, pointgoodsordno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pointgoodsordVO = new PointGoodsOrdVO();
				pointgoodsordVO.setPointgoodsordno(rs.getString("pointgoodsordno"));
				pointgoodsordVO.setMemno(rs.getString("memno"));
				pointgoodsordVO.setAddress(rs.getNString("address"));
				pointgoodsordVO.setOrderstatus(rs.getInt("ordstatus"));
				pointgoodsordVO.setOrderpoint(rs.getInt("orderpoint"));
			} 
		} catch (ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+ se.getMessage());
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
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return pointgoodsordVO;
	}
	@Override
	public List<PointGoodsOrdVO> getAll() {
		List<PointGoodsOrdVO> list = new ArrayList<PointGoodsOrdVO>();
		PointGoodsOrdVO pointgoodsordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pointgoodsordVO = new PointGoodsOrdVO();
				pointgoodsordVO.setPointgoodsordno(rs.getString("pointgoodsordno"));
				pointgoodsordVO.setMemno(rs.getString("memno"));
				pointgoodsordVO.setAddress(rs.getNString("address"));
				pointgoodsordVO.setOrderstatus(rs.getInt("orderstatus"));
				pointgoodsordVO.setOrderpoint(rs.getInt("orderpoint"));
				list.add(pointgoodsordVO);
			} 
		} catch (ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+ se.getMessage());
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
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public void insertWithDetails(PointGoodsOrdVO pointgoodsordVO, List<PGOrdDetailsVO> list) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main (String[] args) {
		PointGoodsOrdJDBCDAO dao = new PointGoodsOrdJDBCDAO();
		//新增
//		PointGoodsOrdVO pointgoodsordVO1 = new PointGoodsOrdVO();
//		pointgoodsordVO1.setMemno("MEM0001");
//		pointgoodsordVO1.setAddress("桃園市平鎮區民族路二段175號");
//		pointgoodsordVO1.setOrdstatus(new Integer(1));
//		pointgoodsordVO1.setOrderpoint(new Integer(777));
//		dao.insert(pointgoodsordVO1);
		//修改
//		PointGoodsOrdVO pointgoodsordVO2 = new PointGoodsOrdVO();
//		pointgoodsordVO2.setMemno("MEM0002");
//		pointgoodsordVO2.setAddress("桃園市中央大學");
//		pointgoodsordVO2.setOrdstatus(new Integer(2));
//		pointgoodsordVO2.setOrderpoint(new Integer(8888));
//		pointgoodsordVO2.setPointgoodsordno("PGO0009");
//		dao.update(pointgoodsordVO2);
		//刪除
//		dao.delete("PGO0009");
		//查詢一個
		PointGoodsOrdVO pointgoodsordVO3 = dao.findByPrimaryKey("PGO0001");
		System.out.println(pointgoodsordVO3.getPointgoodsordno() + ", ");
		System.out.println(pointgoodsordVO3.getMemno() + ", ");
		System.out.println(pointgoodsordVO3.getAddress() + ", ");
		System.out.println(pointgoodsordVO3.getOrderstatus() + ", ");
		System.out.println(pointgoodsordVO3.getOrderpoint() + ", ");
		//查詢全部
//		List<PointGoodsOrdVO> list = dao.getAll();
//		for (PointGoodsOrdVO pointgoodsordVO4 : list) {
//			System.out.println(pointgoodsordVO4.getPointgoodsordno() + ", ");
//			System.out.println(pointgoodsordVO4.getMemno() + ", ");
//			System.out.println(pointgoodsordVO4.getAddress() + ", ");
//			System.out.println(pointgoodsordVO4.getOrdstatus() + ", ");
//			System.out.println(pointgoodsordVO4.getOrderpoint() + ", ");
//		}
		
	}


}
