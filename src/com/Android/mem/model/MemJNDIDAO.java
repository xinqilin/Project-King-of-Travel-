package com.Android.mem.model;


import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemJNDIDAO implements MemDAO_interface {
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
			"INSERT INTO memberList (memno, memname, e_mail, mempasswd, nickname, idno, birday, address, phone) VALUES ('MEM'||LPAD(to_char(memberlist_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE memberList set nickName = ?, introduction = ? where e_mail = ?";
	private static final String GET_ONE_ByEmail_STMT =
			"SELECT memno, memname, e_mail, mempasswd, nickname, idno, to_char(birday,'yyyy-mm-dd')birday, address, phone, status, introduction, points FROM memberlist where e_mail = ?";
	private static final String SEARCH_ID   =  
			"Select Count(IDNO) result from memberlist where idno = ?";
	private static final String COUNT_TRIPS =
			"Select Count(tripNO) counts from TripList where memNo = ? and TRIPSTATUS = 1";
	private static final String COUNT_ARTICLES =
			"Select Count(tripNO) counts from Article where memNo = ? and ARTICLESTATUS = 1";
	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memVO.getMemName());
			pstmt.setString(2, memVO.getE_mail());
			pstmt.setString(3, memVO.getMemPassWd());
			pstmt.setString(4, memVO.getNickName());
			pstmt.setString(5, memVO.getIdNo());
			pstmt.setDate(6, memVO.getBirDay());
			pstmt.setString(7, memVO.getAddr());
			pstmt.setString(8, memVO.getPhone());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
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

			pstmt.setString(1, memVO.getNickName());
			pstmt.setString(2, memVO.getIntroduction());
			pstmt.setString(3, memVO.getE_mail());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (con != null) {
				try {con.close();} 
				catch (Exception e) {e.printStackTrace(System.err);}
			}
		}

	}

	@Override
	public Boolean isIdRegistered(String idNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_ID);

			pstmt.setString(1, idNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.getInt("result")!=0) {result = true;}
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (con != null) {
				try {con.close();
				} catch (Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return result;
	}


	@Override
	public MemVO findByEmail(String email) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection(); 
			pstmt = con.prepareStatement(GET_ONE_ByEmail_STMT);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMemNo(rs.getString("memno"));
				memVO.setMemName(rs.getString("memname"));
				memVO.setE_mail(rs.getString("e_mail"));
				memVO.setMemPassWd(rs.getString("mempasswd"));
				memVO.setNickName(rs.getString("nickname"));
				memVO.setIdNo(rs.getString("idno"));
				memVO.setBirDay(rs.getDate("birday"));
				memVO.setIntroduction(rs.getString("introduction"));
				memVO.setPoints(rs.getString("points"));
			}
			
		}  catch(SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if(con != null) {
				try {con.close();
				} catch(Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}

	@Override
	public String findTripCountByMemNo(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String counts ="";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT_TRIPS);

			pstmt.setString(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				counts += rs.getInt("counts");
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (rs != null) {
				try {rs.close();
				}catch (SQLException se) {se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {pstmt.close();
				} catch (SQLException se) {se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {con.close();
				} catch (Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return counts;
	}

	@Override
	public String findArticleCountByMemNo(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String counts ="";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT_ARTICLES);

			pstmt.setString(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				counts += rs.getInt("counts");
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (con != null) {
				try {con.close();
				} catch (Exception e) {e.printStackTrace(System.err);
				}
			}
		}
		return counts;
	}
}
