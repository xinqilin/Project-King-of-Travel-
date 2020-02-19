package com.administrator.model;

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

public class AdministratorDAO implements AdministratorDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO administrator (adminno, adminname, e_mail, adminpasswd, adminstatus) VALUES ('ADS'||LPAD(to_char(administrator_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT adminno, adminname, e_mail, adminpasswd, adminstatus from administrator order by adminno";
	private static final String GET_ONE_STMT = 
			"SELECT adminno, adminname, e_mail, adminpasswd, adminstatus from administrator where adminno = ?";
	private static final String DELETE = 
			"DELETE FROM administrator where adminno = ?";
	private static final String UPDATE =
			"UPDATE administrator set adminname=?, e_mail=?, adminpasswd=?, adminstatus=? where adminno=?";
	private static final String GET_Email=
			"SELECT * FROM administrator where e_mail=?";
	
	@Override
	public void insert(AdministratorVO administratorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, administratorVO.getAdminName());
			pstmt.setString(2, administratorVO.getE_mail());
			pstmt.setString(3, administratorVO.getAdminPassWd());
			pstmt.setInt(4, administratorVO.getAdminStatus());
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error ocurred." + se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(AdministratorVO administratorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, administratorVO.getAdminName());
			pstmt.setString(2, administratorVO.getE_mail());
			pstmt.setString(3, administratorVO.getAdminPassWd());
			pstmt.setInt(4, administratorVO.getAdminStatus());
			pstmt.setString(5, administratorVO.getAdminNo());
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occurred." + se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String adminNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
				
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, adminNo);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred." +se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}


	@Override
	public AdministratorVO findByPrimaryKey(String adminNo) {
		
		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, adminNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdminNo(rs.getString("adminno"));
				administratorVO.setAdminName(rs.getString("adminname"));
				administratorVO.setE_mail(rs.getString("e_mail"));
				administratorVO.setAdminPassWd(rs.getString("adminpasswd"));
				administratorVO.setAdminStatus(rs.getInt("adminstatus"));
			}
			
		} catch(SQLException se) {
			throw new RuntimeException("A databse error occurred." + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return administratorVO;
	}

	@Override
	public List<AdministratorVO> getAll() {
		
		List<AdministratorVO> list = new ArrayList<>();
		AdministratorVO administratorVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdminNo(rs.getString("adminno"));
				administratorVO.setAdminName(rs.getString("adminname"));
				administratorVO.setE_mail(rs.getNString("e_mail"));
				administratorVO.setAdminPassWd(rs.getString("adminpasswd"));
				administratorVO.setAdminStatus(rs.getInt("adminstatus"));
				list.add(administratorVO);
			}
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occurred." + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public AdministratorVO getEmail(String e_mail) {
		
		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Email);
			
			pstmt.setString(1, e_mail);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdminNo(rs.getString("adminno"));
				administratorVO.setAdminName(rs.getString("adminname"));
				administratorVO.setE_mail(rs.getString("e_mail"));
				administratorVO.setAdminPassWd(rs.getString("adminpasswd"));
				administratorVO.setAdminStatus(rs.getInt("adminstatus"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(rs != null) {
				try{
					rs.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return administratorVO;
	}
	
	public static void main(String[] args) {
		AdministratorDAO dao = new AdministratorDAO();
		
		//新增
//		AdministratorVO adVO = new AdministratorVO();
//		adVO.setAdminName("水果奶奶");
//		adVO.setE_mail("corn@gmail.com");
//		adVO.setAdminPassWd("1324564");
//		adVO.setAdminStatus(1);
//		dao.insert(adVO);
//		System.out.println("insert done!");
		
		//修改
//		AdministratorVO adVO1 = new AdministratorVO();
//		adVO1.setAdminNo("ADS0005");
//		adVO1.setAdminName("木瓜奶奶");
//		adVO1.setE_mail("corn123@gmail.com");
//		adVO1.setAdminPassWd("13245645");
//		adVO1.setAdminStatus(0);
//		dao.update(adVO1);
		
		//刪除
//		dao.delete("ADS0005");
		
		//查詢
//		AdministratorVO adVO = dao.findByPrimaryKey("ADS0002");
//		System.out.print(adVO.getAdminNo() + ",");
//		System.out.print(adVO.getAdminName() + ",");
//		System.out.print(adVO.getE_mail() + ",");
//		System.out.print(adVO.getAdminPassWd() + ",");
//		System.out.print(adVO.getAdminStatus() + ",");
		
		//查詢
		List<AdministratorVO> list = dao.getAll();
		for(AdministratorVO adVO : list) {
			System.out.print(adVO.getAdminNo() + ",");
			System.out.print(adVO.getAdminName() + ",");
			System.out.print(adVO.getE_mail() + ",");
			System.out.print(adVO.getAdminPassWd() + ",");
			System.out.print(adVO.getAdminStatus() + ",");
			System.out.println();
		}
	}

}
