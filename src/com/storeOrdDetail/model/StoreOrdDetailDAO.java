package com.storeOrdDetail.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.storeOrd.model.StoreOrdVO;

import java.sql.*;

public class StoreOrdDetailDAO implements StoreOrdDetailDAO_interface {
	// 連線方式
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {

		}
	}
	// =====
	private static final String INSERT_STMT = "Insert into storeOrdDetails (ordNo,itemNo,quantity,price) values (?,?,?,?)";
	private static final String GET_ONE_ORDER_DETAIL_STMT = "SELECT*FROM storeOrdDetails where ordNo = ? ";

	@Override // 新增
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

	@Override
	public void check_out(StoreOrdDetailVO storeOrdDetailVO, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, storeOrdDetailVO.getOrdNo());
			System.out.println("detaildao得到ordno=" + storeOrdDetailVO.getOrdNo());
			pstmt.setString(2, storeOrdDetailVO.getItemNo());
			System.out.println("detaildao得到itemno=" + storeOrdDetailVO.getItemNo());
			pstmt.setInt(3, storeOrdDetailVO.getQuantity());
			System.out.println("detaildao得到Quantity=" + storeOrdDetailVO.getQuantity());
			pstmt.setInt(4, storeOrdDetailVO.getPrice());
			System.out.println("detaildao得到Price=" + storeOrdDetailVO.getPrice());
			pstmt.executeUpdate();
			System.out.println("新增訂單明細１筆！");
		} catch (SQLException se) {
			System.out.println("3●設定於當有exception發生時之catch區塊內");
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.out.println("3●設定於當有exception發生時之catch區塊內");
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-StoreOrdDetail_JDBC");
					con.rollback();
				} catch (SQLException excep) {
					System.out.println("rollback error occured. ");
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

	@Override
	public List<StoreOrdDetailVO> findByPrimaryKey(String ordNo) {
		ArrayList<StoreOrdDetailVO> list = new ArrayList<StoreOrdDetailVO>();
		StoreOrdDetailVO storeOrdDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ORDER_DETAIL_STMT);
			pstmt.setString(1, ordNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				storeOrdDetailVO = new StoreOrdDetailVO();
				storeOrdDetailVO.setOrdNo(ordNo);
				storeOrdDetailVO.setItemNo(rs.getString("itemNo"));
				storeOrdDetailVO.setPrice(rs.getInt("price"));
				storeOrdDetailVO.setQuantity(rs.getInt("quantity"));
				list.add(storeOrdDetailVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
