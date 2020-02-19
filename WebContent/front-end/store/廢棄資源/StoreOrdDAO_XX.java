package com.storeOrd.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.item.model.ItemVO;
import com.storeOrdDetail.model.StoreOrdDetailDAO_XX;
import com.storeOrdDetail.model.StoreOrdDetailVO;

import java.sql.*;

public class StoreOrdDAO_XX implements StoreOrdDAO_interface {

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

	private static final String INSERT_STMT = "Insert into storeOrd(ordNo,MemNo,price,address) values ('Ord'||to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(storeOrd_seq.NEXTVAL),4,'0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT*FROM storeOrd order by ordNo";
	private static final String GET_ONE_STMT = "SELECT ordNo,MemNo,price,address,status,orderedTime,paymentTime,paymentMethod FROM storeOrd where ordNo = ?";
	private static final String DELETE = "DELETE FROM storeOrd where ordNo = ?";
	private static final String UPDATE = "UPDATE storeOrd set memNo=?, price=?, address=?, status=?, paymentMethod=? where ordNo = ?";

	@Override
	public void insert(StoreOrdVO storeOrdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, storeOrdVO.getMemNo());
			pstmt.setInt(2, storeOrdVO.getPrice());
			pstmt.setString(3, storeOrdVO.getAddress());
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
	public void update(StoreOrdVO storeOrdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, storeOrdVO.getMemNo());
			pstmt.setInt(2, storeOrdVO.getPrice());
			pstmt.setString(3, storeOrdVO.getAddress());
			pstmt.setInt(4, storeOrdVO.getStatus());
			pstmt.setInt(5, storeOrdVO.getPaymentMethod());
			pstmt.setString(6, storeOrdVO.getOrdNo());
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
	public void delete(String ordNo) {
		System.out.println("OrdDao:ordNo="+ordNo);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ordNo);
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
	public StoreOrdVO findByPrimaryKey(String ordNo) {
		StoreOrdVO storeOrdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1,ordNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				storeOrdVO = new StoreOrdVO();
				storeOrdVO.setOrdNo(rs.getString("ordNo"));
				storeOrdVO.setMemNo(rs.getString("memNo"));
				storeOrdVO.setPrice(rs.getInt("price"));
				storeOrdVO.setAddress(rs.getString("address"));
				storeOrdVO.setStatus(rs.getInt("status"));
				storeOrdVO.setOrderedTime(rs.getTimestamp("orderedTime"));
				storeOrdVO.setPaymentTime(rs.getTimestamp("paymentTime"));
				storeOrdVO.setPaymentMethod(rs.getInt("paymentMethod"));
			}
			System.out.println("DAO ordNo="+storeOrdVO.getOrdNo());
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
		System.out.println("storeOrdDao  findByPrimaryKey 查詢成功 vo="+storeOrdVO);
		System.out.println("storeOrdDao  findByPrimaryKey 查詢成功 address="+storeOrdVO.getAddress());
		return storeOrdVO;
	}

	@Override
	public List<StoreOrdVO> getAll() {
		List<StoreOrdVO> list = new ArrayList<StoreOrdVO>();
		StoreOrdVO storeOrdVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeOrdVO = new StoreOrdVO();
				storeOrdVO.setOrdNo(rs.getString("ordNo"));
				storeOrdVO.setMemNo(rs.getString("memNo"));
				storeOrdVO.setPrice(rs.getInt("price"));
				storeOrdVO.setAddress(rs.getString("address"));
				storeOrdVO.setStatus(rs.getInt("status"));
				storeOrdVO.setOrderedTime(rs.getTimestamp("orderedTime"));
				storeOrdVO.setPaymentTime(rs.getTimestamp("paymentTime"));
				storeOrdVO.setPaymentMethod(rs.getInt("paymentMethod"));
				list.add(storeOrdVO);
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


	public void insert_with_storeDetail(StoreOrdVO storeOrdVO, List<StoreOrdDetailVO> StoreOrdDetailList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"ordNo"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, storeOrdVO.getMemNo());
			pstmt.setInt(2, storeOrdVO.getPrice());
			pstmt.setString(3, storeOrdVO.getAddress());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String nextOrdNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextOrdNo = rs.getString(1);
				System.out.println("自增主鍵值= " + nextOrdNo +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增訂單明細
			StoreOrdDetailDAO_XX storeOrdDetailDAO = new StoreOrdDetailDAO_XX();
			System.out.println("list.size()-A="+StoreOrdDetailList.size());
			for (StoreOrdDetailVO aStoreOrdDetail : StoreOrdDetailList) {
				aStoreOrdDetail.setOrdNo(nextOrdNo);
				storeOrdDetailDAO.insert2(aStoreOrdDetail,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+StoreOrdDetailList.size());
			System.out.println("新增部門編號" + nextOrdNo + "時,共有員工" + StoreOrdDetailList.size()
					+ "人同時被新增");
			
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
			// Clean up JDBC resources
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
	public void insert_with_storeDetail(StoreOrdVO storeOrdVO, LinkedHashMap<ItemVO, Integer> cart) {
		// TODO Auto-generated method stub
		
	}
}
