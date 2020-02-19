package com.funcList.model;

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

public class FuncListDAO implements FuncListDAO_interface{
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
			"INSERT INTO funclist (funcno, funcname) VALUES ('FUN'||LPAD(to_char(funclist_seq.NEXTVAL), 4, '0'), ?)";
	private static final String GET_ALL_STMT =
			"SELECT funcno, funcname from funclist order by funcno";
	private static final String GET_ONE_STMT = 
			"SELECT funcno, funcname from funclist where funcno = ?";
	private static final String DELETE = 
			"DELETE FROM funclist where funcno = ?";
	private static final String UPDATE =
			"UPDATE funclist set funcname=? where funcno = ?";
	
	@Override
	public void insert(FuncListVO funcListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, funcListVO.getFuncName());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
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
	public void update(FuncListVO funcListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, funcListVO.getFuncName());
			pstmt.setString(2, funcListVO.getFuncNo());
			
			pstmt.executeUpdate();
			
		} catch(SQLException se) {
			throw new RuntimeException("A database error occurred." + se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException se) {
					se.printStackTrace(System.err);
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String funcNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, funcNo);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
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
	public FuncListVO findByPrimaryKey(String funcNo) {
		FuncListVO funcListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, funcNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				funcListVO = new FuncListVO();
				funcListVO.setFuncNo(rs.getString("funcno"));
				funcListVO.setFuncName(rs.getString("funcname"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred." + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return funcListVO;
	}

	@Override
	public List<FuncListVO> getAll() {
		List<FuncListVO> list = new ArrayList<>();
		FuncListVO funcListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				funcListVO = new FuncListVO();
				funcListVO.setFuncNo(rs.getString("funcno"));
				funcListVO.setFuncName(rs.getString("funcname"));
				list.add(funcListVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occurred." + se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				} 
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		FuncListJDBCDAO dao = new FuncListJDBCDAO();
		
		//新增
//		FuncListVO funVO = new FuncListVO();
//		funVO.setFuncName("XXX");
//		dao.insert(funVO);
//		System.out.println("insert done!");
		
		//修改
//		FuncListVO funVO = new FuncListVO();
//		funVO.setFuncNo("FUN0001");
//		funVO.setFuncName("CCCCCCC");
//		dao.update(funVO);
		
		//刪除
//		dao.delete("FUN0005");
		
		//查詢
//		FuncListVO funVO = dao.findByPrimaryKey("FUN0002");
//		System.out.println(funVO.getFuncNo() + ",");
//		System.out.println(funVO.getFuncName() + ",");
//		System.out.println();
		
		//查詢
//		List<FuncListVO> list = dao.getAll();
//		for(FuncListVO funVO : list) {
//			System.out.print(funVO.getFuncNo() + ",");
//			System.out.print(funVO.getFuncName() + ",");
//		}
		
	}


}
