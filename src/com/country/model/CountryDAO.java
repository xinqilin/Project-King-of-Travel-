package com.country.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements CountryDAO_Interface {
	private static final String Driver="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER="DA101G3"; 
	private static final String PASSWORD="123456";
	
	private final String INSERT_STMT="INSERT INTO COUNTRY (CountryNo, CountryName) VALUES ('CRY'||LPAD(to_char(Country_seq.NEXTVAL), 4, '0'), ?)";
	private final String DELETE_STMT="DELETE FROM COUNTRY WHERE CountryNo = ?";
	private final String UPDATE_STMT="UPDATE Country SET CountryName = ? WHERE CountryNo = ?";
	private final String FIND_BY_PK="SELECT CountryNo, CountryName FROM Country WHERE CountryNo= ?";
	private final String GET_ALL="SELECT * FROM COUNTRY";
	
	static {
		try {Class.forName(Driver);}
		catch(ClassNotFoundException ce) {ce.printStackTrace();}
	}	

	Connection con=null;
	PreparedStatement pstmt=null;
	
	@Override
	public void insert(CountryVO country) {
		String[] cols = {"COUNTRYNO"};
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt=con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, country.getCountryName());
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
	public void delete(String countryNo) {
		try {
			con=DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt=con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, countryNo);
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
	public void update(CountryVO country) {
		try {
			con=DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt=con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, country.getCountryName());
			pstmt.setString(2, country.getCountryNo());

			pstmt.executeUpdate();
			System.out.println("test");
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
	public CountryVO findByPrimaryKey(String countryNo) {
		CountryVO country=new CountryVO();
		try {
			con  = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt= con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, countryNo);
			pstmt.executeQuery();
			ResultSet rs=pstmt.getResultSet();
			while(rs.next()) {
				country.setCountryNo(rs.getString("COUNTRYNO"));
				country.setCountryName(rs.getString("COUNTRYNAME"));
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
		return country;
	}

	@Override
	public List<CountryVO> getAll() {
		List<CountryVO> countryList=new ArrayList<CountryVO>();;
		try {
			con   = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				countryList.add(new CountryVO(rs.getString("COUNTRYNO"),rs.getString("COUNTRYNAME")));
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
		return countryList;
	}

}
