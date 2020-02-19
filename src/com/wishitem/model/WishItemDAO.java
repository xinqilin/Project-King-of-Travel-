package com.wishitem.model;


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


public class WishItemDAO implements WishItemDAO_interface{
	
	
		private static DataSource ds =null;
		static {
			try {
				Context ctx = new InitialContext();
				ds =(DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			}catch (NamingException e){
				e.printStackTrace();
			}
			
		}
		
		private static final String INSERT_STMT =
			"INSERT INTO WISHITEM(WISHITEMNO,MEMNO,AMOUNT,ITEMNAME,ITEMPRICE,ITEMSTORENAME,ITEMSTOREADDR,ITEMSTORELATI,ITEMSTORELONG,BUYORSELL,WISHITEMDETAIL,WISHITEMPICTURE,WISHNOTE,STATUS,ITEMTYPE) VALUES('WIN'||LPAD(to_char(WISHITEM_seq.NEXTVAL),4,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT =
			"SELECT WISHITEMNO,MEMNO,AMOUNT,ITEMNAME,ITEMPRICE,ITEMSTORENAME,ITEMSTOREADDR,ITEMSTORELATI,ITEMSTORELONG,BUYORSELL,WISHITEMDETAIL,WISHITEMPICTURE,WISHNOTE,STATUS,ITEMTYPE FROM WISHITEM ORDER BY WISHITEMNO";
		private static final String GET_ONE_STMT =
			"SELECT WISHITEMNO,MEMNO,AMOUNT,ITEMNAME,ITEMPRICE,ITEMSTORENAME,ITEMSTOREADDR,ITEMSTORELATI,ITEMSTORELONG,BUYORSELL,WISHITEMDETAIL,WISHITEMPICTURE,WISHNOTE,STATUS,ITEMTYPE FROM WISHITEM WHERE WISHITEMNO = ?";
		private static final String DELETE =
			"DELETE FROM WISHITEM WHERE WISHITEMNO =?";	
		private static final String UPDATE =
			"UPDATE WISHITEM SET MEMNO=?, AMOUNT=?, ITEMNAME =?, ITEMPRICE=?, ITEMSTORENAME=?, ITEMSTOREADDR=?, ITEMSTORELATI=?, ITEMSTORELONG=?, BUYORSELL=?, WISHITEMDETAIL=?, WISHITEMPICTURE=?, WISHNOTE=?, STATUS=?, ITEMTYPE=? WHERE WISHITEMNO = ?";
		
		@Override
		public void insert(WishItemVO wishItemVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, wishItemVO.getMemNo());
				pstmt.setInt(2, wishItemVO.getAmount());
				pstmt.setString(3, wishItemVO.getItemName());
				pstmt.setInt(4, wishItemVO.getItemPrice());
				pstmt.setString(5, wishItemVO.getItemStoreName());
				pstmt.setString(6, wishItemVO.getItemStoreAddr());
				pstmt.setString(7, wishItemVO.getItemStoreLati());
				pstmt.setString(8, wishItemVO.getItemStoreLong());
				pstmt.setInt(9, wishItemVO.getBuyOrSell());
				pstmt.setString(10, wishItemVO.getWishItemDetail());
				pstmt.setBytes(11, wishItemVO.getWishItemPicture());
				pstmt.setString(12, wishItemVO.getWishNote());
				pstmt.setInt(13, wishItemVO.getStatus());
				pstmt.setInt(14, wishItemVO.getItemType());
				
				pstmt.executeUpdate();

				// Handle any SQL errors
			}catch (SQLException se) {
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
		public void update(WishItemVO wishItemVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, wishItemVO.getMemNo());
				pstmt.setInt(2, wishItemVO.getAmount());
				pstmt.setString(3, wishItemVO.getItemName());
				pstmt.setInt(4, wishItemVO.getItemPrice());
				pstmt.setString(5, wishItemVO.getItemStoreName());
				pstmt.setString(6, wishItemVO.getItemStoreAddr());
				pstmt.setString(7, wishItemVO.getItemStoreLati());
				pstmt.setString(8, wishItemVO.getItemStoreLong());
				pstmt.setInt(9, wishItemVO.getBuyOrSell());
				pstmt.setString(10, wishItemVO.getWishItemDetail());
				pstmt.setBytes(11, wishItemVO.getWishItemPicture());
				pstmt.setString(12, wishItemVO.getWishNote());
				pstmt.setInt(13, wishItemVO.getStatus());
				pstmt.setInt(14, wishItemVO.getItemType());
				pstmt.setString(15, wishItemVO.getWishItemNo());
				
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
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
		public void delete(String wishItemNo) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, wishItemNo);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
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
		public WishItemVO findByPrimaryKey(String wishItemNo) {
			WishItemVO wishItemVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, wishItemNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					wishItemVO = new WishItemVO();
					wishItemVO.setWishItemNo(rs.getString("WISHITEMNO"));
					wishItemVO.setMemNo(rs.getString("MEMNO"));
					wishItemVO.setAmount(rs.getInt("AMOUNT"));
					wishItemVO.setItemName(rs.getString("ITEMNAME"));
					wishItemVO.setItemPrice(rs.getInt("ITEMPRICE"));
					wishItemVO.setItemStoreName(rs.getString("ITEMSTORENAME"));
					wishItemVO.setItemStoreAddr(rs.getString("ITEMSTOREADDR"));
					wishItemVO.setItemStoreLati(rs.getString("ITEMSTORELATI"));
					wishItemVO.setItemStoreLong(rs.getString("ITEMSTORELONG"));
					wishItemVO.setBuyOrSell(rs.getInt("BUYORSELL"));
					wishItemVO.setWishItemDetail(rs.getString("WISHITEMDETAIL"));
					wishItemVO.setWishItemPicture(rs.getBytes("WISHITEMPICTURE"));
					wishItemVO.setWishNote(rs.getString("WISHNOTE"));
					wishItemVO.setStatus(rs.getInt("STATUS"));
					wishItemVO.setItemType(rs.getInt("ITEMTYPE"));
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return wishItemVO;
		}
		@Override
		public List<WishItemVO> getAll() {
			List<WishItemVO> list = new ArrayList<WishItemVO>();
			WishItemVO wishItemVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					wishItemVO = new WishItemVO();
					wishItemVO.setWishItemNo(rs.getString("WISHITEMNO"));
					wishItemVO.setMemNo(rs.getString("MEMNO"));
					wishItemVO.setAmount(rs.getInt("AMOUNT"));
					wishItemVO.setItemName(rs.getString("ITEMNAME"));
					wishItemVO.setItemPrice(rs.getInt("ITEMPRICE"));
					wishItemVO.setItemStoreName(rs.getString("ITEMSTORENAME"));
					wishItemVO.setItemStoreAddr(rs.getString("ITEMSTOREADDR"));
					wishItemVO.setItemStoreLati(rs.getString("ITEMSTORELATI"));
					wishItemVO.setItemStoreLong(rs.getString("ITEMSTORELONG"));
					wishItemVO.setBuyOrSell(rs.getInt("BUYORSELL"));
					wishItemVO.setWishItemDetail(rs.getString("WISHITEMDETAIL"));
					wishItemVO.setWishItemPicture(rs.getBytes("WISHITEMPICTURE"));
					wishItemVO.setWishNote(rs.getString("WISHNOTE"));
					wishItemVO.setStatus(rs.getInt("STATUS"));
					wishItemVO.setItemType(rs.getInt("ITEMTYPE"));
					list.add(wishItemVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		
	}
