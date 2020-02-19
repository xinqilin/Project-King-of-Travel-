package com.pointgoodsord.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.pgorddetails.model.PGOrdDetailsDAO;
import com.pgorddetails.model.PGOrdDetailsVO;


public class PointGoodsOrdDAO implements PointGoodsOrdDAO_interface {
	
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
			"Insert into PointGoodsOrd (PointGoodsOrdNo, MemNo, Receiver, Phone, Address, OrderDate, OrderStatus, OrderPoint) values ('PGO'||LPAD(to_char(PointGoodsOrdNo_SEQ.NEXTVAL), 4, '0'),?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT PointGoodsOrdNo, MemNo, Receiver, Phone, Address, OrderDate, OrderStatus, OrderPoint FROM PointGoodsOrd order by PointGoodsOrdNo";
	private static final String GET_ONE_STMT =
			"SELECT PointGoodsOrdNo, MemNo, Receiver, Phone, Address, OrderDate, OrderStatus, OrderPoint FROM PointGoodsOrd where PointGoodsOrdNo=?";
	private static final String DELETE_STMT = 
			"DELETE FROM PointGoodsOrd where PointGoodsOrdNo no=?";
	private static final String UPDATE_STMT =
			"UPDATE PointGoodsOrd set Receiver=?, Phone=?, Address=?, OrdStatus=?, OrderPoint=? where PointGoodsOrdNo=?";
	@Override
	public void insert(PointGoodsOrdVO pointgoodsordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, pointgoodsordVO.getMemno());
			pstmt.setString(2, pointgoodsordVO.getReceiver());
			pstmt.setString(3, pointgoodsordVO.getPhone());
			pstmt.setString(4, pointgoodsordVO.getAddress());
			pstmt.setTimestamp(5, pointgoodsordVO.getOrderdate());
			pstmt.setInt(6, pointgoodsordVO.getOrderstatus());
			pstmt.setInt(7, pointgoodsordVO.getOrderpoint());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void update(PointGoodsOrdVO pointgoodsordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, pointgoodsordVO.getReceiver());
			pstmt.setString(2, pointgoodsordVO.getPhone());
			pstmt.setString(3, pointgoodsordVO.getAddress());
			pstmt.setInt(4, pointgoodsordVO.getOrderstatus());
			pstmt.setInt(5, pointgoodsordVO.getOrderpoint());
			pstmt.setString(6, pointgoodsordVO.getPointgoodsordno());
			
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
	public void delete(String pointgoodsordno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, pointgoodsordno);
			
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
	public PointGoodsOrdVO findByPrimaryKey(String pointgoodsordno) {
		PointGoodsOrdVO pointgoodsordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, pointgoodsordno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pointgoodsordVO = new PointGoodsOrdVO();
				pointgoodsordVO.setPointgoodsordno(rs.getString("pointgoodsordno"));
				pointgoodsordVO.setMemno(rs.getString("memno"));
				pointgoodsordVO.setReceiver(rs.getString("receiver"));
				pointgoodsordVO.setPhone(rs.getString("phone"));
				pointgoodsordVO.setAddress(rs.getString("address"));
				pointgoodsordVO.setOrderdate(rs.getTimestamp("orderdate"));
				pointgoodsordVO.setOrderstatus(rs.getInt("orderstatus"));
				pointgoodsordVO.setOrderpoint(rs.getInt("orderpoint"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pointgoodsordVO = new PointGoodsOrdVO();
				pointgoodsordVO.setPointgoodsordno(rs.getString("pointgoodsordno"));
				pointgoodsordVO.setMemno(rs.getString("memno"));
				pointgoodsordVO.setReceiver(rs.getString("receiver"));
				pointgoodsordVO.setPhone(rs.getString("phone"));
				pointgoodsordVO.setAddress(rs.getString("address"));
				pointgoodsordVO.setOrderdate(rs.getTimestamp("orderdate"));
				pointgoodsordVO.setOrderstatus(rs.getInt("orderstatus"));
				pointgoodsordVO.setOrderpoint(rs.getInt("orderpoint"));
				list.add(pointgoodsordVO);
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
	public void insertWithDetails(PointGoodsOrdVO pointgoodsordVO, List<PGOrdDetailsVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			String cols[] = {"POINTGOODSORDNO"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, pointgoodsordVO.getMemno());
			pstmt.setString(2, pointgoodsordVO.getReceiver());
			pstmt.setString(3, pointgoodsordVO.getPhone());
			pstmt.setString(4, pointgoodsordVO.getAddress());
			pstmt.setTimestamp(5, pointgoodsordVO.getOrderdate());
			pstmt.setInt(6, pointgoodsordVO.getOrderstatus());
			pstmt.setInt(7, pointgoodsordVO.getOrderpoint());
			pstmt.executeUpdate();
			
			String pointgoodsordno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				pointgoodsordno = rs.getString(1);
			}
			rs.close();
			pointgoodsordVO.setPointgoodsordno(pointgoodsordno);
			PGOrdDetailsDAO dao = new PGOrdDetailsDAO();
			for(PGOrdDetailsVO pgorddetailsVO : list) {
				pgorddetailsVO.setPointgoodsordno(pointgoodsordno);
				dao.insert2(pgorddetailsVO, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
