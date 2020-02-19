package com.Android.city.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements CityDAO_Interface {

	private static final String Driver="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="DA101G3"; 
	private static final String PASSWORD="123456";
	
	private final String INSERT_STMT="INSERT INTO City (CityNo, CountryNo, CityName) VALUES ('CIT'||LPAD(to_char(City_seq.NEXTVAL), 4, '0'), ?, ?)";
	private final String DELETE_STMT="DELETE FROM City WHERE CityNo = ?";
	private final String UPDATE_STMT="UPDATE City SET CountryNo = ?, CityName = ? WHERE CityNo = ?";
	private final String FIND_BY_PK="SELECT CityNo, CountryNo, CityName FROM City WHERE CityNo= ?";
	private final String GET_ALL="SELECT * FROM City";
	
	
	static {
		try {Class.forName(Driver);}
		catch(ClassNotFoundException ce) {ce.printStackTrace();}
	}	

	Connection          con = null;
	PreparedStatement pstmt = null;
	
	@Override
	public void insert(CityVO city) {
		try {
			con   = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, city.getCountryNo());
			pstmt.setString(2, city.getCityName());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if (pstmt != null) {
				try {pstmt.close();} catch (SQLException se) 
				{se.printStackTrace(System.err);}
			}
			if (con != null) {
				try {con.close();} catch (Exception e) 
				{e.printStackTrace(System.err);}
			}
		}
	}

	@Override
	public void delete(String cityNo) {
		try {
			con    = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt  = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, cityNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if (pstmt != null) {
				try {pstmt.close();} catch (SQLException se) 
				{se.printStackTrace(System.err);}
			}
			if (con != null) {
				try {con.close();} catch (Exception e) 
				{e.printStackTrace(System.err);}
			}
		}
		
	}

	@Override
	public void update(CityVO city) {
		try {
			con   = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, city.getCountryNo());
			pstmt.setString(2, city.getCityName());
			pstmt.setString(3, city.getCityNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if (pstmt != null) {
				try {pstmt.close();} catch (SQLException se) 
				{se.printStackTrace(System.err);}
			}
			if (con != null) {
				try {con.close();} catch (Exception e) 
				{e.printStackTrace(System.err);}
			}
		}
	}

	@Override
	public CityVO findByPrimaryKey(String cityNo) {
		CityVO city= new CityVO();
		try {
			con   = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, cityNo);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				city.setCityNo(rs.getString("CITYNO"));
				city.setCountryNo(rs.getString("countryNo"));
				city.setCityName(rs.getString("CITYNAME"));
			}
			
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if (pstmt != null) {
				try {pstmt.close();} catch (SQLException se) 
				{se.printStackTrace(System.err);}
			}
			if (con != null) {
				try {con.close();} catch (Exception e) 
				{e.printStackTrace(System.err);}
			}
		}
		return city;
	}

	@Override
	public List<CityVO> getAll() {
		List<CityVO> cityList = new ArrayList<CityVO>();;
		try {
			con   = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				cityList.add(new CityVO(rs.getString("CityNO"), rs.getString("CountryNO"),rs.getString("CityNAME")));
			}
		} catch (SQLException e) {e.printStackTrace();}
		finally {
			if (pstmt != null) {
				try {pstmt.close();} catch (SQLException se) 
				{se.printStackTrace(System.err);}
			}
			if (con != null) {
				try {con.close();} catch (Exception e) 
				{e.printStackTrace(System.err);}
			}
		}			
			return cityList;	
		}

	@Override
	public List<CityVO> getCities(String cityNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
