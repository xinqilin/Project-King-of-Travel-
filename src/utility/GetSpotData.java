package utility;

import java.io.*;
import java.sql.*;

import org.json.*;

import com.Android.spot.model.SpotListVO;

import java.net.*;




public class GetSpotData{
	private static final String MY_URL = "https://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G3";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO spotlist (spotno, spotname, cityno, location, spottype, spotstatus, tel, spotlati, spotlong, spotdetail) VALUES ('SPT'||LPAD(to_char(SpotList_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();

		URLConnection urlConn = HttpsUtil.getURLConnection(MY_URL);
		urlConn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
		urlConn.connect();
		
		InputStream ins = urlConn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
		
		String str;
		while ((str = br.readLine()) != null)
			sb.append(str);
		
		br.close();
		ins.close();
		
		System.out.println("===================================");
		
		int j = sb.indexOf("{");
		String sc = sb.substring(j);
		JSONObject jObj = new JSONObject(sc.trim());
		JSONObject jObj2=jObj.getJSONObject("XML_Head");
		JSONObject jObj3=jObj2.getJSONObject("Infos");
		JSONArray jArray = jObj3.getJSONArray("Info");
		int k=0;

		for (int i = 801; i < 869; i++) {
			
			JSONObject data = jArray.getJSONObject(i);
			String name = data.getString("Name");
			String address=data.getString("Add");
			String tel=data.getString("Tel");
			double px=data.getDouble("Px");
			double py=data.getDouble("Py");
			String tip=data.getString("Description");
			
			System.out.println("�暺� : " + name);
			System.out.println("���"+address);
			System.out.println("�閰�"+tel);
			System.out.println("蝬漲"+px);
			System.out.println("蝺臬漲"+py);
			System.out.println("��膩"+tip);
			k++;
			System.out.println(k);
			//5132
			System.out.println("============================");
			GetSpotData  dao = new GetSpotData();
			
			SpotListVO spotListVO1 = new SpotListVO();
			spotListVO1.setSpotName(name);
			spotListVO1.setCityNo("CIT0003");
			spotListVO1.setLocation(address);
			spotListVO1.setSpotType(1);
			spotListVO1.setSpotStatus(1);
			spotListVO1.setTel(tel);
			spotListVO1.setSpotLati(new Double(py));
			spotListVO1.setSpotLong(new Double(px));
			spotListVO1.setSpotDetail(tip);
			dao.insert(spotListVO1);
		}	
	}

	public void insert(SpotListVO spotListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, spotListVO.getSpotName());
			pstmt.setString(2, spotListVO.getCityNo());
			pstmt.setString(3, spotListVO.getLocation());
			pstmt.setInt(4, spotListVO.getSpotType());
			pstmt.setInt(5, spotListVO.getSpotStatus());
			pstmt.setString(6, spotListVO.getTel());
			pstmt.setDouble(7, spotListVO.getSpotLati());
			pstmt.setDouble(8, spotListVO.getSpotLong());
			pstmt.setString(9, spotListVO.getSpotDetail());
			
			pstmt.executeUpdate();
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
}
