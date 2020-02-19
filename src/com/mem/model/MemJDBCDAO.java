//package com.mem.model;
//
//import java.sql.*;
//
//import java.util.*;
//
//
//public class MemJDBCDAO implements MemDAO_interface  {
//	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G3";
//	String passwd = "123456";
//	
//	private static final String INSERT_STMT = 
//			"INSERT INTO memberlist (memno,memname,e_mail,mempasswd,memphoto,nickname,idno,birday,address,phone) VALUES ('MEM'||LPAD(to_char(memberlist_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = 
//			"SELECT memno,memname,e_mail,mempasswd,memphoto,nickname,idno,to_char(birday,'yyyy-mm-dd')birday,address,phone FROM memberlist order by memno";
//	private static final String GET_ONE_STMT =
//			"SELECT memno,memname,e_mail,mempasswd,memphoto,nickname,idno,to_char(birday,'yyyy-mm-dd')birday,address,phone FROM memberlist where memno = ?";
//	private static final String DELETE = 
//			"DELETE FROM memberlist where memno = ?";
//	private static final String UPDATE = 
//			"UPDATE memberlist set memname=?, e_mail=?, mempasswd=?, memphoto=?, nickname=?, idno=?, birday=?, address=?, phone=?, status=? where memno=?";
//	private static final String GET_Email="select * from memberlist where e_mail=?";		
//	@Override
//	public void insert(MemVO memVO) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			
//			pstmt.setString(1, memVO.getMemName());
//			pstmt.setString(2, memVO.getE_mail());
//			pstmt.setString(3, memVO.getMemPassWd());
//			pstmt.setBytes(4, memVO.getMemPhoto());
//			pstmt.setString(5, memVO.getNickName());
//			pstmt.setString(6, memVO.getIdNo());
//			pstmt.setDate(7, memVO.getBirDay());
//			pstmt.setString(8, memVO.getAddress());
//			pstmt.setString(9, memVO.getPhone());
////			pstmt.setInt(10, memVO.getStatus());
////			System.out.println(memVO.getMemName()+memVO.getE_mail());
//			pstmt.executeUpdate();
//			
//		} catch(ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//		} catch(SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//		} finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void update(MemVO memVO) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			
//			
//			pstmt.setString(1, memVO.getMemName());
//			pstmt.setString(2, memVO.getE_mail());
//			pstmt.setString(3, memVO.getMemPassWd());
//			pstmt.setBytes(4, memVO.getMemPhoto());
//			pstmt.setString(5, memVO.getNickName());
//			pstmt.setString(6, memVO.getIdNo());
//			pstmt.setDate(7, memVO.getBirDay());
//			pstmt.setString(8, memVO.getAddress());
//			pstmt.setString(9, memVO.getPhone());
////			pstmt.setInt(10, memVO.getStatus());
//			pstmt.setString(10, memVO.getMemNo());
//			
//			pstmt.executeUpdate();
//			
//		} catch(ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//		} catch(SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//		} finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				} catch(Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		
//	}
//	
//
//	@Override
//	public void delete(String memNo) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//			
//			pstmt.setString(1, memNo);
//			
//			pstmt.executeUpdate();
//			
//		} catch(ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//		} catch(SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage()); 
//		} finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				} catch(Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//	
//
//	@Override
//	public MemVO findByPrimaryKey(String memNo) {
//		
//		MemVO memVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			
//			pstmt.setString(1, memNo);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				memVO = new MemVO();
//				memVO.setMemNo(rs.getString("memno"));
//				memVO.setMemName(rs.getString("memname"));
//				memVO.setE_mail(rs.getString("e_mail"));
//				memVO.setMemPassWd(rs.getString("mempasswd"));
//				memVO.setMemPhoto(rs.getBytes("memphoto"));
//				memVO.setNickName(rs.getString("nickname"));
//				memVO.setIdNo(rs.getString("idno"));
//				memVO.setBirDay(rs.getDate("birday"));
//				memVO.setAddress(rs.getString("address"));
//				memVO.setPhone(rs.getString("phone"));
////				memVO.setStatus(rs.getInt("status"));
//			}
//			
//		} catch(ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//		} catch(SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//		} finally {
//			if(rs != null) {
//				try {
//					rs.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				} catch(Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		
//		return memVO;
//	}
//
//	@Override
//	public List<MemVO> getAll() {
//		List<MemVO> list = new ArrayList<MemVO>();
//		MemVO memVO = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				memVO = new MemVO();
//				memVO.setMemNo(rs.getString("memno"));
//				memVO.setMemName(rs.getString("memname"));
//				memVO.setE_mail(rs.getString("e_mail"));
//				memVO.setMemPassWd(rs.getString("mempasswd"));
//				memVO.setMemPhoto(rs.getBytes("memphoto"));
//				memVO.setNickName(rs.getString("nickname"));
//				memVO.setIdNo(rs.getString("idno"));
//				memVO.setBirDay(rs.getDate("birday"));
//				memVO.setAddress(rs.getString("address"));
//				memVO.setPhone(rs.getString("phone"));
////				memVO.setStatus(rs.getInt("status"));
//				list.add(memVO);
//			}
//			
//		} catch(ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//		} catch(SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//		} finally {
//			if(rs != null) {
//				try {
//					rs.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null){
//				try {
//					con.close();
//				} catch(Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//	
//	public static void main(String[] args) {
//		MemJDBCDAO dao = new MemJDBCDAO();
//		
//		//新增
//		MemVO memVO1 = new MemVO();
//		memVO1.setMemName("喜憨兒");
//		memVO1.setE_mail("co123n@gmail.com");
//		memVO1.setMemPassWd("987654321");
//	
//		memVO1.setNickName("睿智軒");
//		memVO1.setIdNo("A31321121");
//		memVO1.setBirDay(java.sql.Date.valueOf("1992-4-24"));
//		memVO1.setAddress("桃園市中壢區中央路216巷8號");
//		memVO1.setPhone("0987-087587");
////		memVO1.setStatus(2);
//		dao.insert(memVO1);
//		System.out.println("insert done!");
//		
//		//修改
////		MemVO memVO2 = new MemVO();
////		memVO2.setMemNo("MEM0013");
////		memVO2.setMemName("XXX");
////		memVO2.setE_mail("co12hjhjadn@gmail.com");
////		memVO2.setMemPassWd("987654321");
////		memVO2.setNickName("OOO");
////		memVO2.setIdNo("A311321121");
////		memVO2.setBirDay(java.sql.Date.valueOf("1992-4-24"));
////		memVO2.setAddress("桃園市中壢區中央路216巷8號");
////		memVO2.setPhone("0987-087587");
////		memVO2.setStatus(2);
////		dao.update(memVO2);
//		
//		//查詢
////		MemVO memVO2 = dao.findByPrimaryKey("MEM0014");
////		System.out.print(memVO2.getMemNo() + ",");
////		System.out.print(memVO2.getMemName() + ",");
//	
//		
//		//查詢全部
//		List<MemVO> list = dao.getAll();
//		for(MemVO memVO : list){
//			System.out.print(memVO.getMemNo() + ",");
//			System.out.print(memVO.getMemName() + ",");
//			System.out.print(memVO.getIdNo() + ",");
//			System.out.println();
//		}
//	}
//
//	@Override
//	public MemVO getEmail(String e_mail) {
//		MemVO memVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_Email);
//			rs = pstmt.executeQuery();
//			
//			pstmt.setString(1, e_mail);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				memVO = new MemVO();
//				memVO.setMemNo(rs.getString("memno"));
//				memVO.setMemName(rs.getString("memname"));
//				memVO.setE_mail(rs.getString("e_mail"));
//				memVO.setMemPassWd(rs.getString("mempasswd"));
//				memVO.setMemPhoto(rs.getBytes("memphoto"));
//				memVO.setNickName(rs.getString("nickname"));
//				memVO.setIdNo(rs.getString("idno"));
//				memVO.setBirDay(rs.getDate("birday"));
//				memVO.setAddress(rs.getString("address"));
//				memVO.setPhone(rs.getString("phone"));
////				memVO.setStatus(rs.getInt("status"));
//			}
//			
//		} catch(ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//		} catch(SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//		} finally {
//			if(rs != null) {
//				try {
//					rs.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null){
//				try {
//					con.close();
//				} catch(Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		
//		return memVO;
//	}
//
//	@Override
//	public void updatepoints(MemVO memVO) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
