package com.item.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ItemDAO implements ItemDAO_interface {
//連線方式
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//=====
	private static final String INSERT_STMT = "INSERT INTO item (itemNo,itemName,price,amount,itemDetail,itemClass,picture) VALUES ('I'||LPAD(to_char(itemNo_seq.NEXTVAL),4,'0'), ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = "SELECT itemNo,itemName,price,amount,status,itemDetail,itemClass,picture FROM item order by itemNo desc";
	private static final String GET_ONE_STMT = "SELECT itemNo,itemName,price,amount,status,itemDetail,itemClass,picture FROM item where itemNo = ?";
	private static final String CHECK_OUT = "UPDATE item set amount=?, where itemNo = ?";
	private static final String UPDATE = "UPDATE item set itemName=?, price=?, amount=?, status=?, itemDetail=?, itemClass=?, picture=? where itemNo = ?";
//	private static final String UPDATE_NO_PIC= "UPDATE item set itemName=?, price=?, amount=?, status=?, itemDetail=?, itemClass=? where itemNo = ?";
	@Override
	public void insert(ItemVO itemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, itemVO.getItemName());
			pstmt.setInt(2, itemVO.getPrice());
			pstmt.setInt(3, itemVO.getAmount());
			pstmt.setString(4, itemVO.getItemDetail());
			pstmt.setInt(5, itemVO.getItemClass());
			pstmt.setBytes(6, itemVO.getPicture());
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
	public void update(ItemVO itemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setString(1, itemVO.getItemName());
				pstmt.setInt(2, itemVO.getPrice());
				pstmt.setInt(3, itemVO.getAmount());
				pstmt.setInt(4, itemVO.getStatus());
				pstmt.setString(5, itemVO.getItemDetail());
				pstmt.setInt(6, itemVO.getItemClass());
				pstmt.setBytes(7, itemVO.getPicture());
				pstmt.setString(8, itemVO.getItemNo());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ItemVO findByPrimaryKey(String itemNo) {
		// TODO Auto-generated method stub
		ItemVO itemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, itemNo);

			rs = pstmt.executeQuery();
 
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				itemVO = new ItemVO();
				itemVO.setItemNo(rs.getString("itemNo"));
				itemVO.setItemName(rs.getString("itemName"));
				itemVO.setPrice(rs.getInt("price"));
				itemVO.setAmount(rs.getInt("amount"));
				itemVO.setStatus(rs.getInt("status"));
				itemVO.setItemDetail(rs.getString("itemDetail"));
				itemVO.setItemClass(rs.getInt("itemClass"));
				itemVO.setPicture(rs.getBytes("picture"));
				
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
		return itemVO;
	}

	@Override
	public List<ItemVO> getAll() {
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// itemVO 也稱為 Domain objects
				itemVO = new ItemVO();
				itemVO.setItemNo(rs.getString("itemNo"));
				itemVO.setItemName(rs.getString("itemName"));
				itemVO.setPrice(rs.getInt("price"));
				itemVO.setAmount(rs.getInt("amount"));
				itemVO.setStatus(rs.getInt("status"));
				itemVO.setItemDetail(rs.getString("itemDetail"));
				itemVO.setItemClass(rs.getInt("itemClass"));
				itemVO.setPicture(rs.getBytes("picture"));
				list.add(itemVO); // Store the row in the list
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
	public void Check_out(String itemNo,int cart_amount, Connection con) {
		PreparedStatement pstmt = null;
		ItemVO itemVO = null;
		ResultSet rs = null;
		try {
			//取得產品庫存數量
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, itemNo);
			rs = pstmt.executeQuery();
			int Sql_amount =rs.getInt("amount");
			//判斷商品庫存數量是否比客戶要買的數量還要多
			if(Sql_amount>=cart_amount) {
				System.out.println("ItemDAO庫存數量="+Sql_amount+";客戶欲買數量="+cart_amount+"，庫存數量充足!");
			int new_amount=(Sql_amount-cart_amount);
			pstmt = con.prepareStatement(CHECK_OUT);
			
			
			}else if(Sql_amount<cart_amount) {
				System.out.println("ItemDAO庫存數量不足");
				con.rollback();
			}
			
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
	
	//分隔線
}
