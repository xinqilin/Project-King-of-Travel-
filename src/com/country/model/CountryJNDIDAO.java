package com.country.model;

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

public class CountryJNDIDAO implements CountryDAO_Interface {

	private final String INSERT_STMT = "INSERT INTO COUNTRY (CountryNo, CountryName) VALUES ('CRY'||LPAD(to_char(Country_seq.NEXTVAL), 4, '0'), ?)";
	private final String DELETE_STMT = "DELETE FROM COUNTRY WHERE CountryNo = ?";
	private final String UPDATE_STMT = "UPDATE Country SET CountryName = ? WHERE CountryNo = ?";
	private final String FIND_BY_PK = "SELECT CountryNo, CountryName FROM Country WHERE CountryNo= ?";
	private final String GET_ALL = "SELECT * FROM COUNTRY";

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(CountryVO country) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String[] cols = { "COUNTRYNO" };
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, country.getCountryName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void delete(String countryNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, countryNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void update(CountryVO country) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, country.getCountryName());
			pstmt.setString(2, country.getCountryNo());

			pstmt.executeUpdate();
			System.out.println("test");
		} catch (SQLException e) {
			e.printStackTrace();
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
	public CountryVO findByPrimaryKey(String countryNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		CountryVO country = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, countryNo);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			while (rs.next()) {
				country.setCountryNo(rs.getString("COUNTRYNO"));
				country.setCountryName(rs.getString("COUNTRYNAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
		return country;
	}

	@Override
	public List<CountryVO> getAll() {

		Connection con = null;
		PreparedStatement pstmt = null;
		List<CountryVO> countryList = new ArrayList<CountryVO>();
		;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				countryList.add(new CountryVO(rs.getString("COUNTRYNO"), rs.getString("COUNTRYNAME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		return countryList;
	}
}
