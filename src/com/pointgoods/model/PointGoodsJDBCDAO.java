package com.pointgoods.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class PointGoodsJDBCDAO implements PointGoodsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "YUGI";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO pointgoods (pointgoodsno, pointgoodsname, pointgoodsquantity, needpoints, pointgoodsdesc, pointgoodspic, pointgoodsstatus) VALUES "
			+ "('PG'||LPAD(to_char(PointGoodsNo_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT pointgoodsno, pointgoodsname, pointgoodsquantity, needpoints, pointgoodsdesc, pointgoodspic, pointgoodsstatus FROM pointgoods order by pointgoodsno";
	private static final String GET_ONE_STMT = 
			"SELECT pointgoodsno, pointgoodsname, pointgoodsquantity, needpoints, pointgoodsdesc, pointgoodspic, pointgoodsstatus FROM pointgoods where pointgoodsno=?";
	//商品應該不能刪除
	private static final String DELETE_STMT = 
			"DELETE FROM pointgoods where pointgoodsno=?";
	private static final String UPDATE_STMT =
			"UPDATE pointgoods set pointgoodsname=?, pointgoodsquantity=?, needpoints=?, pointgoodsdesc=?, pointgoodspic=?, pointgoodsstatus=? where pointgoodsno=?";
	
	@Override
	public void insert(PointGoodsVO pointgoodsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			pstmt.setString(1, pointgoodsVO.getPointgoodsno()); 自增主鍵不用寫
			pstmt.setString(1, pointgoodsVO.getPointgoodsname());
			pstmt.setInt(2,pointgoodsVO.getPointgoodsquantity());
			pstmt.setInt(3, pointgoodsVO.getNeedpoints());
			pstmt.setString(4, pointgoodsVO.getPointgoodsdesc());
			pstmt.setBytes(5, pointgoodsVO.getPointgoodspic());
			pstmt.setInt(6, pointgoodsVO.getPointgoodsstatus());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(PointGoodsVO pointgoodsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, pointgoodsVO.getPointgoodsname());
			pstmt.setInt(2,pointgoodsVO.getPointgoodsquantity());
			pstmt.setInt(3, pointgoodsVO.getNeedpoints());
			pstmt.setString(4, pointgoodsVO.getPointgoodsdesc());
			pstmt.setBytes(5, pointgoodsVO.getPointgoodspic());
			pstmt.setInt(6, pointgoodsVO.getPointgoodsstatus());
			pstmt.setString(7, pointgoodsVO.getPointgoodsno());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " 
		+ se.getMessage());
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
	//商品理論上只能下架不能刪除
	public void delete(String pointgoodsno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url ,userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setString(1, pointgoodsno);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public PointGoodsVO findByPrimaryKey(String pointgoodsno) {
		PointGoodsVO pointgoodsVO = null; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, pointgoodsno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				pointgoodsVO = new PointGoodsVO();
				pointgoodsVO.setPointgoodsno(rs.getString("pointgoodsno"));
				pointgoodsVO.setPointgoodsname(rs.getString("pointgoodsname"));
				pointgoodsVO.setPointgoodsquantity(rs.getInt("pointgoodsquantity"));
				pointgoodsVO.setNeedpoints(rs.getInt("needpoints"));
				pointgoodsVO.setPointgoodsdesc(rs.getString("pointgoodsdesc"));
				pointgoodsVO.setPointgoodspic(rs.getBytes("pointgoodspic"));
				pointgoodsVO.setPointgoodsstatus(rs.getInt("pointgoodsstatus"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException ("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException ("A database error occured. "
					+ se.getMessage());
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
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return pointgoodsVO;
	}
	@Override
	public List<PointGoodsVO> getAll() {
			List<PointGoodsVO> list = new ArrayList<PointGoodsVO>();
			PointGoodsVO pointgoodsVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					pointgoodsVO = new PointGoodsVO();
					pointgoodsVO.setPointgoodsno(rs.getString("pointgoodsno"));
					pointgoodsVO.setPointgoodsname(rs.getString("pointgoodsname"));
					pointgoodsVO.setPointgoodsquantity(rs.getInt("pointgoodsquantity"));
					pointgoodsVO.setNeedpoints(rs.getInt("needpoints"));
					pointgoodsVO.setPointgoodsdesc(rs.getString("pointgoodsdesc"));
					pointgoodsVO.setPointgoodspic(rs.getBytes("pointgoodspic"));
					pointgoodsVO.setPointgoodsstatus(rs.getInt("pointgoodsstatus"));
					list.add(pointgoodsVO);
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException ("Couldn't load database driver. "
						+ e.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException ("A database error occured. "
						+ se.getMessage());
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
	
	public static void main(String[] args) {
		
		PointGoodsJDBCDAO dao = new PointGoodsJDBCDAO();
		//新增
//		PointGoodsVO pointgoodsVO1 = new PointGoodsVO();
//		pointgoodsVO1.setPointgoodsname("這是商品");
//		pointgoodsVO1.setPointgoodsquantity(new Integer(777));
//		pointgoodsVO1.setNeedpoints(new Integer(777));
//		pointgoodsVO1.setPointgoodsdesc("這是描述");
//		try {
//			pointgoodsVO1.setPointgoodspic(readPic());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		pointgoodsVO1.setPointgoodsstatus(new Integer(1));
//		dao.insert(pointgoodsVO1);
		//修改
		PointGoodsVO pointgoodsVO2 = new PointGoodsVO();
		pointgoodsVO2.setPointgoodsno("PG00001");
		pointgoodsVO2.setPointgoodsname("這是商品");
		pointgoodsVO2.setPointgoodsquantity(new Integer(777));
		pointgoodsVO2.setNeedpoints(new Integer(777));
		pointgoodsVO2.setPointgoodsdesc("這是描述");
		pointgoodsVO2.setPointgoodspic(null);
		pointgoodsVO2.setPointgoodsstatus(new Integer(1));
		dao.update(pointgoodsVO2);
		//刪除
//		dao.delete("PG00010");
		//查詢一個
//		PointGoodsVO pointgoodsVO3 = dao.findByPrimaryKey("PG00001");
//		System.out.println(pointgoodsVO3.getPointgoodsno());
//		System.out.println(pointgoodsVO3.getPointgoodsname());
//		System.out.println(pointgoodsVO3.getPointgoodsquantity());
//		System.out.println(pointgoodsVO3.getNeedpoints());
//		System.out.println(pointgoodsVO3.getPointgoodsdesc());
//		System.out.println(pointgoodsVO3.getPointgoodspic());
//		System.out.println(pointgoodsVO3.getPointgoodsstatus());
		//查詢全部
//		List<PointGoodsVO> list = dao.getAll();
//		for (PointGoodsVO pointgoodsVO : list) {
//			System.out.print(pointgoodsVO.getPointgoodsno() + ", ");
//			System.out.print(pointgoodsVO.getPointgoodsname() + ", ");
//			System.out.print(pointgoodsVO.getPointgoodsquantity() + ", ");
//			System.out.print(pointgoodsVO.getNeedpoints() + ", ");
//			System.out.print(pointgoodsVO.getPointgoodsdesc() + ", ");
//			System.out.print(pointgoodsVO.getPointgoodspic() + ", ");
//			System.out.println(pointgoodsVO.getPointgoodsstatus() + ", ");
//		}
	}
	
	public static byte[] readPic() throws IOException {

		FileInputStream fis = new FileInputStream("D:\\hellobear.png");
		int i;
		int j = 0;
		byte[] pic = new byte[fis.available()];
		while ((i = fis.read()) != -1) {
			pic[j++] = (byte) i;
		}
		return pic;
	}

}
