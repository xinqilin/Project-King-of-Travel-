package com.pgorddetails.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PGOrdDetailsDAO implements PGOrdDetailsDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"Insert into PgOrdDetails (PointGoodsOrdNo, PointGoodsNo, PointGoodsQuantity, GoodsPoint) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT PointGoodsOrdNo, PointGoodsNo, PointGoodsQuantity, GoodsPoint FROM PgOrdDetails ORDER BY PointGoodsOrdNo=?";
	private static final String GET_ONE_STMT =
			"SELECT PointGoodsOrdNo, PointGoodsNo, PointGoodsQuantity, GoodsPoint FROM PgOrdDetails WHERE PointGoodsOrdNo=?, PointGoodsNo=?"; 
	private static final String DELETE_STMT =
			"DELETE FROM PgOrdDetails where PointGoodsOrdNo=? AND PointGoodsNo=?";
	private static final String UPDATE_STMT =
			"UPDATE PgOrdDetails set PointGoodsQuantity=?, GoodsPoint=? WHERE PointGoodsOrdNo=? AND PointGoodsNo=?";
	
	@Override
	public void insert(PGOrdDetailsVO pgorddetailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, pgorddetailsVO.getPointgoodsordno());
			pstmt.setString(2, pgorddetailsVO.getPointgoodsno());
			pstmt.setInt(3, pgorddetailsVO.getPointgoodsquantity());
			pstmt.setInt(4, pgorddetailsVO.getGoodspoint());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+se.getMessage());
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
	public void update(PGOrdDetailsVO pgorddetailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, pgorddetailsVO.getPointgoodsquantity());
			pstmt.setInt(2, pgorddetailsVO.getGoodspoint());
			pstmt.setString(3, pgorddetailsVO.getPointgoodsordno());
			pstmt.setString(4, pgorddetailsVO.getPointgoodsno());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+se.getMessage());
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
	public void delete(String pointgoodsordno, String pointgoodsno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, pointgoodsordno);
			pstmt.setString(2, pointgoodsno);
			
			pstmt.executeUpdate();
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, pointgoodsordno);
			pstmt.setString(2, pointgoodsno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pgorddetailsVO = new PGOrdDetailsVO();
				pgorddetailsVO.setPointgoodsordno(rs.getString("pointgoodsordno"));
				pgorddetailsVO.setPointgoodsno(rs.getString("pointgoodsno"));
				pgorddetailsVO.setPointgoodsquantity(rs.getInt("pointgoodsquantity"));
				pgorddetailsVO.setGoodspoint(rs.getInt("goodspoint"));
			} 
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pgorddetailsVO = new PGOrdDetailsVO();
				pgorddetailsVO.setPointgoodsordno(rs.getString("pointgoodordno"));
				pgorddetailsVO.setPointgoodsno(rs.getString("pointgoodsno"));
				pgorddetailsVO.setPointgoodsquantity(rs.getInt("pointgoodsquantity"));
				pgorddetailsVO.setGoodspoint(rs.getInt("goodspoint"));
				list.add(pgorddetailsVO);
			} 
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void insert2(PGOrdDetailsVO pgorddetailsVO, Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pgorddetailsVO.getPointgoodsordno());
			pstmt.setString(2, pgorddetailsVO.getPointgoodsno());
			pstmt.setInt(3, pgorddetailsVO.getPointgoodsquantity());
			pstmt.setInt(4, pgorddetailsVO.getGoodspoint());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
}
