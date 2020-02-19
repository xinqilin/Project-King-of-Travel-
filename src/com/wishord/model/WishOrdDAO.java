package com.wishord.model;

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

import com.wishdetail.model.WishDetailDAO;
import com.wishdetail.model.WishDetailVO;
import com.wishitem.model.*;

public class WishOrdDAO implements WishOrdDAO_interface{
	
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
			"INSERT INTO WISHORD(WISHORDNO,BUYMEMNO,WISHMEMNO,DAYOFEST,STATUS,PRICE,WISHSENDDATE,BUYNOTE,LASTDATE,ADDR) VALUES('WON'||LPAD(to_char(WISHORD_seq.NEXTVAL),4,'0'),?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT =
			"SELECT WISHORDNO,BUYMEMNO,WISHMEMNO,DAYOFEST,STATUS,PRICE,WISHSENDDATE,BUYNOTE,LASTDATE,ADDR FROM WISHORD ORDER BY DAYOFEST desc";
		private static final String GET_ONE_STMT =
			"SELECT WISHORDNO,BUYMEMNO,WISHMEMNO,DAYOFEST,STATUS,PRICE,WISHSENDDATE,BUYNOTE,LASTDATE,ADDR FROM WISHORD WHERE WISHORDNO = ?";
		private static final String DELETE =
			"DELETE FROM WISHORD WHERE WISHORDNO =?";	
		private static final String UPDATE =
			"UPDATE WISHORD SET BUYMEMNO=?, WISHMEMNO=?, DAYOFEST =?, STATUS=?, PRICE=?, WISHSENDDATE=?, BUYNOTE=?, LASTDATE=?, ADDR=? WHERE WISHORDNO = ?";

		@Override
	public void insert(WishOrdVO wishOrdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, wishOrdVO.getBuyMemNo());
			pstmt.setString(2, wishOrdVO.getWishMemNo());
			pstmt.setInt(3, wishOrdVO.getWishOrdStatus());
			pstmt.setInt(4, wishOrdVO.getWishOrdTotalPrice());
			pstmt.setDate(5, wishOrdVO.getWishSendDate());
			pstmt.setString(6, wishOrdVO.getWishOrdBuyNote());
			pstmt.setDate(7, wishOrdVO.getLastDate());
			pstmt.setString(8, wishOrdVO.getWishOrdSendAddr());
			
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
	public void update(WishOrdVO wishOrdVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, wishOrdVO.getBuyMemNo());
				pstmt.setString(2, wishOrdVO.getWishMemNo());
				pstmt.setDate(3, wishOrdVO.getDayOfEst());
				pstmt.setInt(4, wishOrdVO.getWishOrdStatus());
				pstmt.setInt(5, wishOrdVO.getWishOrdTotalPrice());
				pstmt.setDate(6, wishOrdVO.getWishSendDate());
				pstmt.setString(7, wishOrdVO.getWishOrdBuyNote());
				pstmt.setDate(8, wishOrdVO.getLastDate());
				pstmt.setString(9, wishOrdVO.getWishOrdSendAddr());
				pstmt.setString(10, wishOrdVO.getWishOrdNo());
				
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
	public void delete(String wishOrdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, wishOrdNo);

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
	public WishOrdVO findByPrimaryKey(String wishOrdNo) {
		WishOrdVO wishOrdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, wishOrdNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				wishOrdVO = new WishOrdVO();
				wishOrdVO.setWishOrdNo(rs.getString("WISHORDNO"));
				wishOrdVO.setBuyMemNo(rs.getString("BUYMEMNO"));;
				wishOrdVO.setWishMemNo(rs.getString("WISHMEMNO"));
				wishOrdVO.setDayOfEst(rs.getDate("DAYOFEST"));
				wishOrdVO.setWishOrdStatus(rs.getInt("STATUS"));
				wishOrdVO.setWishOrdTotalPrice(rs.getInt("PRICE"));
				wishOrdVO.setWishSendDate(rs.getDate("WISHSENDDATE"));
				wishOrdVO.setWishOrdBuyNote(rs.getString("BUYNOTE"));
				wishOrdVO.setLastDate(rs.getDate("LASTDATE"));
				wishOrdVO.setWishOrdSendAddr(rs.getString("ADDR"));
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
		return wishOrdVO;
	}

	@Override
	public List<WishOrdVO> getAll() {
		List<WishOrdVO> list = new ArrayList<WishOrdVO>();
		WishOrdVO wishOrdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wishOrdVO = new WishOrdVO();
				wishOrdVO.setWishOrdNo(rs.getString("WISHORDNO"));
				wishOrdVO.setBuyMemNo(rs.getString("BUYMEMNO"));;
				wishOrdVO.setWishMemNo(rs.getString("WISHMEMNO"));
				wishOrdVO.setDayOfEst(rs.getDate("DAYOFEST"));
				wishOrdVO.setWishOrdStatus(rs.getInt("STATUS"));
				wishOrdVO.setWishOrdTotalPrice(rs.getInt("PRICE"));
				wishOrdVO.setWishSendDate(rs.getDate("WISHSENDDATE"));
				wishOrdVO.setWishOrdBuyNote(rs.getString("BUYNOTE"));
				wishOrdVO.setLastDate(rs.getDate("LASTDATE"));
				wishOrdVO.setWishOrdSendAddr(rs.getString("ADDR"));
				list.add(wishOrdVO);
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
		System.out.println(list.size());
		return list;
	}
	
	public void insertWithWishDetail(WishOrdVO wishOrdVO,List<WishItemVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			
			String cols[] = {"WISHORDNO"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, wishOrdVO.getBuyMemNo());
			pstmt.setString(2, wishOrdVO.getWishMemNo());
			pstmt.setDate(3, wishOrdVO.getDayOfEst());
			pstmt.setInt(4, wishOrdVO.getWishOrdStatus());
			pstmt.setInt(5, wishOrdVO.getWishOrdTotalPrice());
			pstmt.setDate(6, wishOrdVO.getWishSendDate());
			pstmt.setString(7, wishOrdVO.getWishOrdBuyNote());
			pstmt.setDate(8, wishOrdVO.getLastDate());
			pstmt.setString(9,wishOrdVO.getWishOrdSendAddr());
			pstmt.executeUpdate();

			
			String next_wishOrdNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_wishOrdNo = rs.getString(1);
				System.out.println("自增主鍵值= " + next_wishOrdNo +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			
			rs.close();
			WishDetailDAO dao =new WishDetailDAO();
			for(WishItemVO aWishItem : list) {
				WishDetailVO wishDetailVO =new WishDetailVO();
				wishDetailVO.setWishOrdNo(next_wishOrdNo);
				wishDetailVO.setWishItemNo(aWishItem.getWishItemNo());
				wishDetailVO.setWishItemNumbers(aWishItem.getAmount());
				wishDetailVO.setWishItemPrice((aWishItem.getAmount())*(aWishItem.getItemPrice()));
				dao.insert2(wishDetailVO, con);
			}
			
			
			con.commit();
			con.setAutoCommit(true);
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
}
