package com.storeOrdDetail.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;


public class StoreOrdDetailDAO_XX implements StoreOrdDetailDAO_interface {
	// 連線方式
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// =====

	private static final String INSERT_STMT = "Insert into storeOrdDetails (ordNo,itemNo,quantity,price) values ('Ord'||to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(storeOrd_seq.CURRVAL),4,'0'),''I'||LPAD(to_char(itemNo_seq.CURRVAL)',?,?)";
//	private static final String INSERT_STMT = "Insert into storeOrdDetails (ordNo,itemNo,quantity,price) values ('Ord'||to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(storeOrd_seq.CURRVAL),4,'0'),'I0005',?,?)";
	private static final String GET_ALL_STMT = "SELECT*FROM storeOrdDetails order by ordNo";
//	private static final String GET_ONE_STMT = "SELECT ordNo,itemNo,quantity,price FROM storeOrdDetails where ordNo = ?";
//	private static final String DELETE = "DELETE FROM storeOrdDetails where ordNo = ? AND itemNo= ?";
//	private static final String UPDATE = "UPDATE emp2 set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=? where empno = ?";
	
	
	
	@Override//新增
	public void insert(StoreOrdDetailVO storeOrdDetailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, storeOrdDetailsVO.getOrdNo());
			pstmt.setString(2, storeOrdDetailsVO.getItemNo());
			pstmt.setInt(3, storeOrdDetailsVO.getQuantity());
			pstmt.setInt(4, storeOrdDetailsVO.getPrice());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	
	
	
	
	
	
	
	
	
	
	@Override//查全部
	public List<StoreOrdDetailVO> getAll() {
		List<StoreOrdDetailVO> list = new ArrayList<StoreOrdDetailVO>();
		StoreOrdDetailVO storeOrdDetailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeOrdDetailsVO = new StoreOrdDetailVO();
				storeOrdDetailsVO.setOrdNo(rs.getString("ordNo"));
				storeOrdDetailsVO.setItemNo(rs.getString("itemNo"));
				storeOrdDetailsVO.setQuantity(rs.getInt("quantity"));
				storeOrdDetailsVO.setPrice(rs.getInt("price"));
				list.add(storeOrdDetailsVO); // Store the row in the list

			}
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


	@Override//刪除
	public void delete(String ordNo, String itemNo) {
		// TODO Auto-generated method stub
		
	}











	@Override//查詢單筆
	public StoreOrdDetailVO findByPrimaryKey(String ordNo, String itemNo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	public void insert2(StoreOrdDetailVO storeOrdDetailsVO, Connection con) {
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, storeOrdDetailsVO.getOrdNo());
			pstmt.setString(2, storeOrdDetailsVO.getItemNo());
			pstmt.setInt(3, storeOrdDetailsVO.getQuantity());
			pstmt.setInt(4, storeOrdDetailsVO.getPrice());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-StoreOrdDetail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

		
	}


}
