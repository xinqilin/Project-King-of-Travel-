package com.storeOrd.model;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.item.model.ItemDAO;
import com.item.model.ItemVO;
import com.storeOrdDetail.model.StoreOrdDetailDAO;
import com.storeOrdDetail.model.StoreOrdDetailVO;
import java.sql.*;

public class StoreOrdDAO implements StoreOrdDAO_interface {
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
	private static final String INSERT_STMT = "Insert into storeOrd(ordNo,MemNo,price,address) values ('ORD'||to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(storeOrd_seq.NEXTVAL),4,'0'),?,?,?)";
	private static final String INSERT_STMT_FE = "Insert into storeOrd(ordNo,MemNo,price,address,paymentMethod) values ('ORD'||to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(storeOrd_seq.NEXTVAL),4,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT*FROM storeOrd order by ordNo desc";
	private static final String GET_ONE_STMT = "SELECT ordNo,MemNo,price,address,status,orderedTime,paymentTime,paymentMethod FROM storeOrd where ordNo = ?";
	
	// 前台會員查詢
	private static final String GET_ALL_STMT_MEMNO = "SELECT*FROM storeOrd where memNo = ? order by orderedTime desc";

//	private static final String DELETE = "DELETE FROM storeOrd where ordNo = ?";

	// 修改訂單
	private static final String UPDATE = "UPDATE storeOrd set address=?, status=? where ordNo = ?";
	//修改訂單狀態
	private static final String CHAANGE_ORD_STATUS = "UPDATE storeOrd set status=3 where ordNo = ?";
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
			System.out.println("sodao 80");
			pstmt.setString(1, storeOrdVO.getAddress());
			pstmt.setInt(2, storeOrdVO.getStatus());
			pstmt.setString(3, storeOrdVO.getOrdNo());
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
			pstmt.setString(1, ordNo);
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
			System.out.println("DAO ordNo=" + storeOrdVO.getOrdNo());
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
	
	//從前台購物車新增訂單
	@Override
	public void insert_with_storeDetail(StoreOrdVO storeOrdVO, LinkedHashMap<ItemVO, Integer> cart) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先新增訂單
			String cols[] = { "ordNo" };
			pstmt = con.prepareStatement(INSERT_STMT_FE, cols);
			pstmt.setString(1, storeOrdVO.getMemNo());
			System.out.println(storeOrdVO.getMemNo());
			pstmt.setInt(2, storeOrdVO.getPrice());
			System.out.println(storeOrdVO.getPrice());
			pstmt.setString(3, storeOrdVO.getAddress());
			System.out.println(storeOrdVO.getAddress());
			pstmt.setInt(4, storeOrdVO.getPaymentMethod());
			System.out.println("付款方式=" + storeOrdVO.getAddress());
			pstmt.executeUpdate();
			System.out.println("1.訂單INSERT_STMT已傳送至資料庫");
			//取得「自增主鍵值」
			String nextOrdNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextOrdNo = rs.getString(1);
				System.out.println("自增主鍵值= " + nextOrdNo + "(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			System.out.println("list.size()-A=" + cart.size());
//			
//			//庫存系統先不用做
//			ItemDAO itemDAO = new ItemDAO(); 
//			for (ItemVO itemVo : cart.keySet()) {
//				ItemVO itemVO =new ItemVO();
//				itemDAO.Check_out(itemVO.getItemNo(),cart.get(itemVO), con);
//			}
			
			
			
			
			
			
			//新增訂單明細
			StoreOrdDetailDAO storeOrdDetailDAO = new StoreOrdDetailDAO();
			for (ItemVO itemVo : cart.keySet()) {
				StoreOrdDetailVO storeOrdDetailVO = new StoreOrdDetailVO();
				storeOrdDetailVO.setOrdNo(nextOrdNo);
				storeOrdDetailVO.setItemNo(itemVo.getItemNo());
				storeOrdDetailVO.setQuantity(cart.get(itemVo));
				storeOrdDetailVO.setPrice(itemVo.getPrice());
				storeOrdDetailDAO.check_out(storeOrdDetailVO, con);
			}
			// 2●設定於 pstm.executeUpdate()之後

			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + cart.size());
			System.out.println("新增訂單編號" + nextOrdNo + "時,共有訂單明細" + cart.size() + "筆同時被新增");
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
	public List<StoreOrdVO> getAllByMemno(String memNo) {
		List<StoreOrdVO> list = new ArrayList<StoreOrdVO>();
		StoreOrdVO storeOrdVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_MEMNO);
			pstmt.setString(1, memNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				storeOrdVO = new StoreOrdVO();
				storeOrdVO.setOrdNo(rs.getString("ordNo"));
				System.out.println(rs.getString("ordNo"));
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

	@Override
	public void change_ord_status(StoreOrdVO storeOrdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHAANGE_ORD_STATUS);
			pstmt.setString(1, storeOrdVO.getOrdNo());
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

}
