package com.pgorddetails.model;

import java.sql.*;
import java.util.*;

public class PGOrdDetailsJDBCDAO implements PGOrdDetailsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "YUGI";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"Insert into PgOrdDetails (PointGoodsOrdNo, PointGoodsNo, PointGoodsQuantity, GoodsPoint) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT PointGoodsOrdNo, PointGoodsNo, PointGoodsQuantity, GoodsPoint FROM PgOrdDetails ORDER BY PointGoodsOrdNo";
	private static final String GET_ONE_STMT =
			"SELECT PointGoodsOrdNo, PointGoodsNo, PointGoodsQuantity, GoodsPoint FROM PgOrdDetails WHERE PointGoodsOrdNo=? AND PointGoodsNo=?"; 
	private static final String DELETE_STMT =
			"DELETE FROM PgOrdDetails where PointGoodsOrdNo=? AND PointGoodsNo=?";
	private static final String UPDATE_STMT =
			"UPDATE PgOrdDetails set PointGoodsQuantity=?, GoodsPoint=? WHERE PointGoodsOrdNo=? AND PointGoodsNo=?";
	
	@Override
	public void insert(PGOrdDetailsVO pgorddetailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, pgorddetailsVO.getPointgoodsordno());
			pstmt.setString(2, pgorddetailsVO.getPointgoodsno());
			pstmt.setInt(3, pgorddetailsVO.getPointgoodsquantity());
			pstmt.setInt(4, pgorddetailsVO.getGoodspoint());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+ se.getMessage());
		}  finally {
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
	public void update(PGOrdDetailsVO pgorddetailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, pgorddetailsVO.getPointgoodsquantity());
			pstmt.setInt(2, pgorddetailsVO.getGoodspoint());
			pstmt.setString(3, pgorddetailsVO.getPointgoodsordno());
			pstmt.setString(4, pgorddetailsVO.getPointgoodsno());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database serve. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+ se.getMessage());
		}  finally {
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
	public void delete(String pointgoodsordno, String pointgoodsno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url ,userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, pointgoodsordno);
			pstmt.setString(2, pointgoodsno);
			
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
	public PGOrdDetailsVO findByPrimaryKey(String pointgoodsordno, String pointgoodsno) {
			PGOrdDetailsVO pgorddetailsVO = null; 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, pointgoodsordno);
				pstmt.setString(2, pointgoodsno);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					pgorddetailsVO = new PGOrdDetailsVO();
					pgorddetailsVO.setPointgoodsordno(rs.getString("pointgoodsordno"));
					pgorddetailsVO.setPointgoodsno(rs.getString("pointgoodsno"));
					pgorddetailsVO.setPointgoodsquantity(rs.getInt("pointgoodsquantity"));
					pgorddetailsVO.setGoodspoint(rs.getInt("goodspoint"));
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
			return pgorddetailsVO;
	}
	@Override
	public List<PGOrdDetailsVO> getAll() {
		List<PGOrdDetailsVO> list = new ArrayList<PGOrdDetailsVO>();
		PGOrdDetailsVO pgorddetailsVO = null; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				pgorddetailsVO = new PGOrdDetailsVO();
				pgorddetailsVO.setPointgoodsordno(rs.getString("pointgoodsordno"));
				pgorddetailsVO.setPointgoodsno(rs.getString("pointgoodsno"));
				pgorddetailsVO.setPointgoodsquantity(rs.getInt("pointgoodsquantity"));
				pgorddetailsVO.setGoodspoint(rs.getInt("goodspoint"));
				list.add(pgorddetailsVO);
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
	
	public static void main(String[] args) {
		
		PGOrdDetailsJDBCDAO dao = new PGOrdDetailsJDBCDAO();
		//新增
//		PGOrdDetailsVO pgorddetailsVO1 = new PGOrdDetailsVO();
//		pgorddetailsVO1.setPointgoodordno("PGO0001");
//		pgorddetailsVO1.setPointgoodsno("PG00002");
//		pgorddetailsVO1.setPointgoodquantity(new Integer(7));
//		pgorddetailsVO1.setGoodspoint(new Integer(9999));
//		dao.insert(pgorddetailsVO1);
		//修改
//		PGOrdDetailsVO pgorddetailsVO2 = new PGOrdDetailsVO();
//		pgorddetailsVO2.setPointgoodordno("PGO0001");
//		pgorddetailsVO2.setPointgoodsno("PG00002");
//		pgorddetailsVO2.setPointgoodquantity(new Integer(70));
//		pgorddetailsVO2.setGoodspoint(new Integer(999999));
//		dao.update(pgorddetailsVO2);
		//刪除
//		dao.delete("PGO0001", "PG00002");
		//查詢一個
//		PGOrdDetailsVO pgorddetailsVO4 = dao.findByPrimaryKey("PGO0001", "PG00001");
//		System.out.println(pgorddetailsVO4.getPointgoodordno() + ", ");
//		System.out.println(pgorddetailsVO4.getPointgoodsno() + ", ");
//		System.out.println(pgorddetailsVO4.getPointgoodsquantity() + ", ");
//		System.out.println(pgorddetailsVO4.getGoodspoint() + ", ");
		//查詢全部
		List<PGOrdDetailsVO> list = dao.getAll();
		for (PGOrdDetailsVO pgorddetailsVO : list) {
			System.out.println(pgorddetailsVO.getPointgoodsordno() + ", ");
			System.out.println(pgorddetailsVO.getPointgoodsno() + ", ");
			System.out.println(pgorddetailsVO.getPointgoodsquantity() + ", ");
			System.out.println(pgorddetailsVO.getGoodspoint() + ", ");
		}
		
	}
	@Override
	public void insert2(PGOrdDetailsVO pgorddetailsVO, Connection con) {
		// TODO Auto-generated method stub
		
	}
	
}
