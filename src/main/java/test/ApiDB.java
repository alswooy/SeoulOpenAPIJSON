package Api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApiDB {
	
	public static void apiInsert(WifiDto wifiDto) {
		Connection conn=null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			
			String file="D:\\SQLiteStudio\\zerbase.db";
			
			conn=DriverManager.getConnection("jdbc:sqlite:" + file);
			
			String sql = "INSERT INTO zerbase_wifi ('X_SWIFI_MGR_NO','X_SWIFI_WRDOFC','X_SWIFI_MAIN_NM',"
					+ "'X_SWIFI_ADRES1','X_SWIFI_ADRES2','X_SWIFI_INSTL_FLOOR','X_SWIFI_INSTL_TY','X_SWIFI_INSTL_MBY',"
					+ "'X_SWIFI_SVC_SE','X_SWIFI_CMCWR','X_SWIFI_CNSTC_YEAR','X_SWIFI_INOUT_DOOR','X_SWIFI_REMARS3','LNT','LAT','WORK_DTTM')"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement pst  = conn.prepareStatement(sql);
			pst.setString(1, wifiDto.getMGR_NO());
			pst.setString(2, wifiDto.getWRDOFC());
			pst.setString(3, wifiDto.getMAIN_NM());
			pst.setString(4, wifiDto.getADRES1());
			pst.setString(5, wifiDto.getADRES2());
			pst.setString(6, wifiDto.getINSTL_FLOOR());
			pst.setString(7, wifiDto.getINSTL_TY());
			pst.setString(8, wifiDto.getINSTL_MBY());
			pst.setString(9, wifiDto.getSVC_SE());
			pst.setString(10, wifiDto.getCMCWR());
			pst.setString(11, wifiDto.getCNSTC_YEAR());
			pst.setString(12, wifiDto.getINOUT_DOOR());
			pst.setString(13, wifiDto.getREMARS3());
			pst.setDouble(14, wifiDto.getLNT());
			pst.setDouble(15, wifiDto.getLAT());
			pst.setString(16, wifiDto.getWORK_DTTM());
			pst.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	

}
