package com.wishdetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.wishitem.model.WishItemVO;

public class WishDetailDAO implements WishDetailDAO_interface{

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
			"INSERT INTO WISHDETAILS(WISHORDNO,WISHITEMNO,NUMBERS,PRICE)VALUES(?,?,?,?)";
	
	@Override
	public void insert(WishItemVO wishItemVO,Connection con) {
		
	}
	@Override
	public void update(WishDetailVO wishDetailVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String wishItemNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WishDetailVO findByPrimaryKey(String wishItemNo,String wishOrdNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WishDetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void insert2(WishDetailVO WishDetailVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try {

     		pstmt = con.prepareStatement(INSERT_STMT);
     		pstmt.setString(1, WishDetailVO.getWishOrdNo());
     		pstmt.setString(2,WishDetailVO.getWishItemNo());
     		pstmt.setInt(3, WishDetailVO.getWishItemNumbers());
     		pstmt.setInt(4, WishDetailVO.getWishItemPrice());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
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
