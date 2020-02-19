
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class loadDatatoDB {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "DA101G3";
	private static final String PASSWORD = "123456";

	private static final String INSERT = "Insert into SpotList(SPOTNO, SPOTNAME, CITYNO, LOCATION, SPOTTYPE, SPOTPHOTO, SPOTSTATUS, SPOTLATI, SPOTLONG, SPOTDETAIL) VALUES"
			+ " ('SPT'||LPAD(to_char(SpotList_seq.NEXTVAL), 6, '0'), ? , ? , ?, ?, ?, ?, ?, ?, ?)";

	public static int setData(String input, int sum) throws IOException {
		File file = new File(input);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str;
		while ((str = br.readLine()) != null && str.length() != 0) {

			String[] strArray = str.split(",");

			String img = "Pictures/" + strArray[0] + ".png";
			File imgFile = new File(img);
			System.out.println(img + ", " + imgFile.exists());
			if (imgFile.exists()) {
				byte[] pic = getPictureByteArray(img);
				Connection con = null;
				PreparedStatement pstmt;
				try {

					con = DriverManager.getConnection(URL, USER, PASSWORD);
					pstmt = con.prepareStatement(INSERT);
					String spotName = strArray[1];
					pstmt.setString(1, spotName);
					String cityNo = strArray[7];
					pstmt.setString(2, cityNo);
					String addr = strArray[2];
					pstmt.setString(3, addr);
					int type = Integer.parseInt(strArray[6]);
					pstmt.setInt(4, type);
					pstmt.setBytes(5, pic);
					int status = 0;
					pstmt.setInt(6, status);
					double lat = Double.parseDouble(strArray[3]);
					pstmt.setDouble(7, lat);
					double lng = Double.parseDouble(strArray[4]);
					pstmt.setDouble(8, lng);
					String detail = strArray[5];
					pstmt.setString(9, detail);

					int result = pstmt.executeUpdate();
					System.out.println(result);
					sum++;
					try {
						Thread.sleep((long) 100.0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (!con.isClosed()) {
							con.close();
						}
					} catch (SQLException e) {e.printStackTrace();
					}
				}
			} else {
				continue;
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {

		String input = "spot.csv";
		int sum = 0;
		sum = setData(input, sum);
		System.out.println(sum);
		System.out.println("����");
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
}
