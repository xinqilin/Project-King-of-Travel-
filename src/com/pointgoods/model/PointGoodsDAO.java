package com.pointgoods.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PointGoodsDAO implements PointGoodsDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
			"INSERT INTO pointgoods (pointgoodsno, pointgoodsname, pointgoodsquantity, needpoints, pointgoodsdesc, pointgoodspic, pointgoodsstatus) VALUES "
					+ "('PG'||LPAD(to_char(PointGoodsNo_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT pointgoodsno, pointgoodsname, pointgoodsquantity, needpoints, pointgoodsdesc, pointgoodspic, pointgoodsstatus FROM pointgoods ORDER BY pointgoodsno";
	private static final String GET_ONE_STMT = 
			"SELECT pointgoodsno, pointgoodsname, pointgoodsquantity, needpoints, pointgoodsdesc, pointgoodspic, pointgoodsstatus FROM pointgoods WHERE pointgoodsno=?";
	//商品應該不能刪除
	private static final String DELETE_STMT = 
			"DELETE FROM pointgoods WHERE pointgoodsno=?";
	private static final String UPDATE_STMT =
			"UPDATE pointgoods set pointgoodsname=?, pointgoodsquantity=?, needpoints=?, pointgoodsdesc=?, pointgoodspic=?, pointgoodsstatus=? WHERE pointgoodsno=?";
	
	@Override
	public void insert(PointGoodsVO pointgoodsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, pointgoodsVO.getPointgoodsname());
			pstmt.setInt(2,pointgoodsVO.getPointgoodsquantity());
			pstmt.setInt(3, pointgoodsVO.getNeedpoints());
			pstmt.setString(4, pointgoodsVO.getPointgoodsdesc());
			pstmt.setBytes(5, pointgoodsVO.getPointgoodspic());
			pstmt.setInt(6, pointgoodsVO.getPointgoodsstatus());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null) {
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
	public void update(PointGoodsVO pointgoodsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, pointgoodsVO.getPointgoodsname());
			pstmt.setInt(2,pointgoodsVO.getPointgoodsquantity());
			pstmt.setInt(3, pointgoodsVO.getNeedpoints());
			pstmt.setString(4, pointgoodsVO.getPointgoodsdesc());
			pstmt.setBytes(5, pointgoodsVO.getPointgoodspic());
			pstmt.setInt(6, pointgoodsVO.getPointgoodsstatus());
			pstmt.setString(7, pointgoodsVO.getPointgoodsno());
			
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
	public void delete(String pointgoodsno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, pointgoodsno);
			
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
	public PointGoodsVO findByPrimaryKey(String pointgoodsno) {
		PointGoodsVO pointgoodsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, pointgoodsno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pointgoodsVO = new PointGoodsVO();
				pointgoodsVO.setPointgoodsno(rs.getString("pointgoodsno"));
				pointgoodsVO.setPointgoodsname(rs.getString("pointgoodsname"));
				pointgoodsVO.setPointgoodsquantity(rs.getInt("pointgoodsquantity"));
				pointgoodsVO.setNeedpoints(rs.getInt("needpoints"));
				pointgoodsVO.setPointgoodsdesc(rs.getString("pointgoodsdesc"));
				pointgoodsVO.setPointgoodspic(rs.getBytes("pointgoodspic"));
				pointgoodsVO.setPointgoodsstatus(rs.getInt("pointgoodsstatus"));
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
		return pointgoodsVO;
	}
	@Override
	public List<PointGoodsVO> getAll() {
		List<PointGoodsVO> list = new ArrayList<PointGoodsVO>();
		PointGoodsVO pointgoodsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pointgoodsVO = new PointGoodsVO();
				pointgoodsVO.setPointgoodsno(rs.getString("pointgoodsno"));
				pointgoodsVO.setPointgoodsname(rs.getString("pointgoodsname"));
				pointgoodsVO.setPointgoodsquantity(rs.getInt("pointgoodsquantity"));
				pointgoodsVO.setNeedpoints(rs.getInt("needpoints"));
				pointgoodsVO.setPointgoodsdesc(rs.getString("pointgoodsdesc"));
				pointgoodsVO.setPointgoodspic(rs.getBytes("pointgoodspic"));
				pointgoodsVO.setPointgoodsstatus(rs.getInt("pointgoodsstatus"));
				list.add(pointgoodsVO);
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
}	

