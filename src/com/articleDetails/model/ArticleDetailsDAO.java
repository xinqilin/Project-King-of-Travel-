package com.articleDetails.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tripDetails.model.TripDetailsVO;

public class ArticleDetailsDAO implements ArticleDetailsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO articledetails (articleno, spotno, articlenotes, articledetailspic, articletriporderby, picnote, tripdayorder) values (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT articleno, spotno, articlenotes, articledetailspic, articletriporderby, picnote, tripdayorder FROM articledetails ORDER BY articleno";
	private static final String GET_ONE_STMT = "SELECT articleno, spotno, articlenotes, articledetailspic, articletriporderby, picnote, tripdayorder FROM articledetails WHERE articleno = ? and spotno = ?";
	private static final String DELETE = "DELETE FROM articledetails WHERE articleno = ? and spotno = ?";
	private static final String UPDATE = "UPDATE articledetails SET articlenotes = ?, articledetailspic = ?, articletriporderby = ?, picnote = ?, tripdayorder = ? WHERE articleno = ? and spotno = ?";
	private static final String GET_ONE_ARTICLE = "SELECT articleno, spotno, articlenotes, articledetailspic, articletriporderby, picnote, tripdayorder FROM articledetails WHERE articleno = ? ORDER BY tripdayorder";
	//負責傳照片
	private static final String UPDATE_PIC_BY_ARTICLENO = "UPDATE articledetails SET articledetailspic = ? WHERE articleno = ? and spotno = ?";
	
	@Override
	public void insert(ArticleDetailsVO articleDetailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, articleDetailsVO.getArticleno());
			pstmt.setString(2, articleDetailsVO.getSpotno());
			pstmt.setString(3, articleDetailsVO.getArticlenotes());
			pstmt.setBytes(4, articleDetailsVO.getArticledetailspic());
			pstmt.setInt(5, articleDetailsVO.getArticletriporderby());
			pstmt.setString(6, articleDetailsVO.getPicnote());
			pstmt.setInt(7, articleDetailsVO.getTripdayorder());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("" + se.getMessage());
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
	public void update(ArticleDetailsVO articleDetailsVO) {
System.out.println("DAO : 76 進來");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, articleDetailsVO.getArticlenotes());
			pstmt.setBytes(2, articleDetailsVO.getArticledetailspic());
			pstmt.setInt(3, articleDetailsVO.getArticletriporderby());
			pstmt.setString(4, articleDetailsVO.getPicnote());
			pstmt.setInt(5, articleDetailsVO.getTripdayorder());
			pstmt.setString(6, articleDetailsVO.getArticleno());
			pstmt.setString(7, articleDetailsVO.getSpotno());
System.out.println("DAO 91 : 更新一次 = " + articleDetailsVO);			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("" + se.getMessage());
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
	public void delete(ArticleDetailsVO articleDetailsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, articleDetailsVO.getArticleno());
			pstmt.setString(1, articleDetailsVO.getSpotno());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("" + se.getMessage());
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
	public ArticleDetailsVO findByPrimaryKey(String articleno, String spotno) {

		ArticleDetailsVO articleDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, articleno);
			pstmt.setString(2, spotno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleDetailsVO = new ArticleDetailsVO();
				articleDetailsVO.setArticleno(rs.getString("articleno"));
				articleDetailsVO.setSpotno(rs.getString("spotno"));
				articleDetailsVO.setArticlenotes(rs.getString("articlenotes"));
				articleDetailsVO.setArticledetailspic(rs.getBytes("articledetailspic"));
				articleDetailsVO.setArticletriporderby(rs.getInt("articletriporderby"));
				articleDetailsVO.setPicnote(rs.getString("picnote"));
				articleDetailsVO.setTripdayorder(rs.getInt("tripdayorder"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("" + se.getMessage());
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
		return articleDetailsVO;
	}

	@Override
	public List<ArticleDetailsVO> getAll() {
		List<ArticleDetailsVO> list = new ArrayList<ArticleDetailsVO>();
		ArticleDetailsVO articleDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleDetailsVO = new ArticleDetailsVO();
				articleDetailsVO.setArticleno(rs.getString("articleno"));
				articleDetailsVO.setSpotno(rs.getString("spotno"));
				articleDetailsVO.setArticlenotes(rs.getString("articlenotes"));
				articleDetailsVO.setArticledetailspic(rs.getBytes("articledetailspic"));
				articleDetailsVO.setArticletriporderby(rs.getInt("articletriporderby"));
				articleDetailsVO.setPicnote(rs.getString("picnote"));
				articleDetailsVO.setTripdayorder(rs.getInt("tripdayorder"));
				list.add(articleDetailsVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("" + se.getMessage());
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
	public List<ArticleDetailsVO> findByArticleno(String articleno) {

		List<ArticleDetailsVO> list = new ArrayList<ArticleDetailsVO>();
		ArticleDetailsVO articleDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ARTICLE);
			pstmt.setString(1, articleno);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleDetailsVO = new ArticleDetailsVO();
				articleDetailsVO.setArticleno(rs.getString("articleno"));
				articleDetailsVO.setSpotno(rs.getString("spotno"));
				articleDetailsVO.setArticlenotes(rs.getString("articlenotes"));
				articleDetailsVO.setArticledetailspic(rs.getBytes("articledetailspic"));
				articleDetailsVO.setArticletriporderby(rs.getInt("articletriporderby"));
				articleDetailsVO.setPicnote(rs.getString("picnote"));
				articleDetailsVO.setTripdayorder(rs.getInt("tripdayorder"));
				list.add(articleDetailsVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("" + se.getMessage());
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
	public void insert_with_Article (ArticleDetailsVO articleDetailsVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, articleDetailsVO.getArticleno());
			pstmt.setString(2, articleDetailsVO.getSpotno());
			pstmt.setString(3, articleDetailsVO.getArticlenotes());
			pstmt.setBytes(4, articleDetailsVO.getArticledetailspic());
			pstmt.setInt(5, articleDetailsVO.getArticletriporderby());
			pstmt.setString(6, articleDetailsVO.getPicnote());
			pstmt.setInt(7, articleDetailsVO.getTripdayorder());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-articledetails");
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
	
	//只負責傳照片
	@Override
	public void update_pic(byte[] articledetailspic, String articleno, String spotno) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ArticleDetailsVO articleDetailsVO = new ArticleDetailsVO();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PIC_BY_ARTICLENO);
			
			pstmt.setBytes(1, articleDetailsVO.getArticledetailspic());
			pstmt.setString(2, articleDetailsVO.getArticleno());
			pstmt.setString(3, articleDetailsVO.getSpotno());
System.out.print("DAO 372 : articleDetailsVO = " + articleDetailsVO);			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("" + se.getMessage());
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
