package com.mem.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import redis.clients.jedis.Jedis;

public class MemDAO implements MemDAO_interface {
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
			"INSERT INTO memberlist (memno,memname,e_mail,mempasswd,memphoto,nickname,idno,birday,address,phone,introduction, STATUS) VALUES ('MEM'||LPAD(to_char(memberlist_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
	private static final String GET_ALL_STMT = 
			"SELECT memno,memname,e_mail,mempasswd,memphoto,nickname,idno,to_char(birday,'yyyy-mm-dd')birday,address,phone,to_char(dateOfAccountEshablished,'yyyy-mm-dd')dateOfAccountEshablished,introduction,points,maxpoints,status FROM memberlist order by memno";
	private static final String GET_ONE_STMT =
			"SELECT memno,memname,e_mail,mempasswd,memphoto,nickname,idno,to_char(birday,'yyyy-mm-dd')birday,address,phone,to_char(dateOfAccountEshablished,'yyyy-mm-dd')dateOfAccountEshablished,introduction,points,maxpoints,status FROM memberlist where memno = ?";
	private static final String DELETE = 
			"DELETE FROM memberlist where memno = ?";
	private static final String UPDATE = 
			"UPDATE memberlist set mempasswd=?, nickname=?, address=?, phone=?, introduction=? where memno=?";
	private static final String GET_Email=
			"SELECT * FROM memberlist WHERE e_mail=?";
	
	private final String JEDISUSER = "localhost";
	private final int JEDISPORT= 6379;
	String JEDISPW = "123456";

	private static final String UPDATEPOINTS = 
			"UPDATE memberlist set points=? where memno=?";
	
	private static final String UPDATE_MEM_PHOTO = 
			"UPDATE memberlist set memphoto= ? where memno=?";
	
//	private static final String JUSTFORMEMPHOTO =
//			"UPDATE memberlist set memphoto=? where memno=?";

//	private static final String EMAIL_CONFIRM = 
//			"UPDATE memberlist set status WHERE e_mail=?";
	
	@Override
	public void insert(MemVO memVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String[] colName = {"MEMNO"};
			pstmt = con.prepareStatement(INSERT_STMT, colName);
			
			pstmt.setString(1, memVO.getMemName());
			pstmt.setString(2, memVO.getE_mail());
			pstmt.setString(3, memVO.getMemPassWd());
			pstmt.setBytes(4, memVO.getMemPhoto());
			pstmt.setString(5, memVO.getNickName());
			pstmt.setString(6, memVO.getIdNo());
			pstmt.setDate(7, memVO.getBirDay());
			pstmt.setString(8, memVO.getAddress());
			pstmt.setString(9, memVO.getPhone());
			pstmt.setString(10, memVO.getIntroduction());

			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			
			String memNo;
			while(rs.next()) {
				memNo = rs.getString(1);
				memVO.setMemNo(memNo);
			}
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(pstmt != null) {
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
	public void update(MemVO memVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, memVO.getMemPassWd());
			pstmt.setString(2, memVO.getNickName());
			pstmt.setString(3, memVO.getAddress());
			pstmt.setString(4, memVO.getPhone());
			pstmt.setString(5, memVO.getIntroduction());

			pstmt.setString(6, memVO.getMemNo());
						
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void delete(String memNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, memNo);
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage()); 
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
	public MemVO findByPrimaryKey(String memNo) {
		
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, memNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getString("memno"));
				memVO.setMemName(rs.getString("memname"));
				memVO.setE_mail(rs.getString("e_mail"));
				memVO.setMemPassWd(rs.getString("mempasswd"));
				memVO.setMemPhoto(rs.getBytes("memphoto"));
				memVO.setNickName(rs.getString("nickname"));
				memVO.setIdNo(rs.getString("idno"));
				memVO.setBirDay(rs.getDate("birday"));
				memVO.setAddress(rs.getString("address"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setDateOfAccountEshablished(rs.getDate("dateOfAccountEshablished"));
				memVO.setIntroduction(rs.getString("introduction"));
				memVO.setPoints(rs.getInt("points"));
				memVO.setMaxPoints(rs.getInt("maxpoints"));
				memVO.setStatus(rs.getInt("status"));
			}
			
		}  catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getString("memno"));
				memVO.setMemName(rs.getString("memname"));
				memVO.setE_mail(rs.getString("e_mail"));
				memVO.setMemPassWd(rs.getString("mempasswd"));
				memVO.setMemPhoto(rs.getBytes("memphoto"));
				memVO.setNickName(rs.getString("nickname"));
				memVO.setIdNo(rs.getString("idno"));
				memVO.setBirDay(rs.getDate("birday"));
				memVO.setAddress(rs.getString("address"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setDateOfAccountEshablished(rs.getDate("dateOfAccountEshablished"));
				memVO.setIntroduction(rs.getString("introduction"));
				memVO.setPoints(rs.getInt("points"));
				memVO.setMaxPoints(rs.getInt("maxpoints"));
				memVO.setStatus(rs.getInt("status"));
				list.add(memVO);
			}
			
		} catch(SQLException se) {
			throw new RuntimeException("A database erroe occured." + se.getMessage());
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
			if(con != null){
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
	public MemVO getEmail(String e_mail) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Email);
			
			pstmt.setString(1, e_mail);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getString("memno"));
				memVO.setMemName(rs.getString("memname"));
				memVO.setE_mail(rs.getString("e_mail"));
				memVO.setMemPassWd(rs.getString("mempasswd"));
				memVO.setMemPhoto(rs.getBytes("memphoto"));
				memVO.setNickName(rs.getString("nickname"));
				memVO.setIdNo(rs.getString("idno"));
				memVO.setBirDay(rs.getDate("birday"));
				memVO.setAddress(rs.getString("address"));
				memVO.setPhone(rs.getString("phone"));
				memVO.setIntroduction(rs.getString("introduction"));
				memVO.setPoints(rs.getInt("points"));
				memVO.setMaxPoints(rs.getInt("maxpoints"));
				memVO.setStatus(rs.getInt("status"));
			}
			
		}  catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
		
		return memVO;
	}

	@Override
	public String getUserCode(MemVO memVO) {
		String code = null;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		if(memVO != null && memVO.getMemNo()!= null) {
			code = returnAuthCode();
			
			Jedis jedis = new Jedis(JEDISUSER, JEDISPORT);
			jedis.auth(JEDISPW);
			
			jedis.set(code, gson.toJson(memVO));	
			jedis.expire(code, 60*60);
		}
		
		return code;
	}
	
	
	
	
	private static String returnAuthCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 8; i++) {
			int condition = (int) (Math.random() * 3) + 1;
			switch (condition) {
			case 1:
				char c1 = (char)((int)(Math.random() * 26) + 65);
				sb.append(c1);
				break;
			case 2:
				char c2 = (char)((int)(Math.random() * 26) + 97);
				sb.append(c2);
				break;
			case 3:
				sb.append((int)(Math.random() * 10));
			}
		}
		return sb.toString();
	}
	
	@Override
	public void updatepoints(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPOINTS);
			
			pstmt.setInt(1, memVO.getPoints());
			pstmt.setString(2, memVO.getMemNo());
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public MemVO confirmCode(String code) {
		MemVO memVO = null;
		Jedis jedis = new Jedis(JEDISUSER, JEDISPORT);
		jedis.auth(JEDISPW);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		if(jedis.ttl(code)>0) {
			String memVOJsonString = jedis.get(code);
			memVO = gson.fromJson(memVOJsonString, MemVO.class);
		}
		
		return memVO;
	}
	
	public void updateMemPhoto(MemVO memVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEM_PHOTO);
			
			
			pstmt.setBytes(1, memVO.getMemPhoto());
			pstmt.setString(2, memVO.getMemNo());
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	
//	public void justForMemPhoto(String memNo, byte[] memPhoto){
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(JUSTFORMEMPHOTO);
//			
//			MemVO memVO = new MemVO();
//			pstmt.setBytes(1, memVO.getMemPhoto());
//			pstmt.setString(2, memVO.getMemNo());
//			
//			pstmt.executeUpdate();
//			
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
	
	
//	public MemVO getEmailConfirm(String e_mail) {
//		MemVO memVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(EMAIL_CONFIRM);
//			rs = pstmt.executeQuery();
//			
//			
//		}
//		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.auth("123456");
//	}
}
