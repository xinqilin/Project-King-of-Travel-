package com.Android.city.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CityJNDIDAO implements CityDAO_Interface {
	private final String INSERT_STMT = 
			"INSERT INTO City (CityNo, CountryNo, CityName) VALUES ('CIT'||LPAD(to_char(City_seq.NEXTVAL), 4, '0'), ?, ?)";
	private final String UPDATE_STMT = 
			"UPDATE City SET CountryNo = ?, CityName = ? WHERE CityNo = ?";
	private final String FIND_BY_PK = 
			"SELECT CityNo, CountryNo, CityName FROM City WHERE CityNo= ?";
	private final String GET_ALL = 
			"SELECT * FROM City";
	private static final String GET_SELECTED_CITIES = 
			"Select * from city where countryno = (select countryNo from city where cityNo = ?) order by cityNo";


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
	public void insert(CityVO city) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, city.getCountryNo());
			pstmt.setString(2, city.getCityName());
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
	public void delete(String cityNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt.setString(1, cityNo);
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
	public void update(CityVO city) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, city.getCountryNo());
			pstmt.setString(2, city.getCityName());
			pstmt.setString(3, city.getCityNo());
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
	public CityVO findByPrimaryKey(String cityNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		CityVO city = new CityVO();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, cityNo);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				city.setCityNo(rs.getString("CITYNO"));
				city.setCountryNo(rs.getString("countryNo"));
				city.setCityName(rs.getString("CITYNAME"));
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
		return city;
	}

	@Override
	public List<CityVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;

		List<CityVO> cityList = new ArrayList<CityVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				cityList.add(new CityVO(rs.getString("CityNO"), rs.getString("CityNAME"), rs.getString("CountryNO")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return cityList;
	}

	@Override
	public List<CityVO> getCities(String cityNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<CityVO> cityList = new ArrayList<CityVO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SELECTED_CITIES);
			pstmt.setString(1, cityNo);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				cityList.add(new CityVO(rs.getString("CityNO"), rs.getString("CityNAME"), rs.getString("CountryNO")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
				
		return cityList;
	}
}
