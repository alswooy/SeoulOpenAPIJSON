package Api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApiDB {
	
	public static void apiInsert(WifiDto wifiDto) {
		Connection conn=null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			
			String file="D:\\SQLiteStudio\\zerbase.db";
			
			conn=DriverManager.getConnection("jdbc:sqlite:" + file);
			
			String sql = "INSERT INTO zerobase_wifi ('X_SWIFI_MGR_NO','X_SWIFI_WRDOFC','X_SWIFI_MAIN_NM',"
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
	public static List<WifiDto> getApiList() {
		Connection conn=null;
		
		
		List<WifiDto> list = new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");
			
			String file="D:\\SQLiteStudio\\zerbase.db";
			
			conn=DriverManager.getConnection("jdbc:sqlite:" + file);
			
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM zerobase_wifi,  "
					+ " (SELECT id, lat as LAT2, lnt as LNT2  FROM zerobase_history ORDER BY id DESC LIMIT 1);");
			
			while(rs.next()){
				String MGR_NO = rs.getString("X_SWIFI_MGR_NO");
				String WRDOFC = rs.getString("X_SWIFI_WRDOFC");
				String MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
				String ADRES1 = rs.getString("X_SWIFI_ADRES1");
				String ADRES2 = rs.getString("X_SWIFI_ADRES2");
				String INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
				String INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
				String INSTL_MBY = rs.getString("X_SWIFI_INSTL_MBY");
				String SVC_SE = rs.getString("X_SWIFI_SVC_SE");
				String CMCWR = rs.getString("X_SWIFI_CMCWR");
				String CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
				String INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
				String REMARS3 = rs.getString("X_SWIFI_REMARS3");
				Double LNT = rs.getDouble("LNT");
				Double LAT = rs.getDouble("LAT");
				String WORK_DTTM = rs.getString("WORK_DTTM");
				Double LNT2 = rs.getDouble("LNT2");
				Double LAT2 = rs.getDouble("LAT2");
				Double distance = Calculation.getDistance(LNT, LAT, LAT2, LNT2);
				System.out.println(LNT + " --  "+LAT+ " --  "+LAT2+" --  "+ LNT2);
				WifiDto dto = new WifiDto();
				
				dto.setMGR_NO(MGR_NO);
				dto.setWRDOFC(WRDOFC);
				dto.setMAIN_NM(MAIN_NM);
				dto.setADRES1(ADRES1);
				dto.setADRES2(ADRES2);
				dto.setINSTL_FLOOR(INSTL_FLOOR);
				dto.setINSTL_TY(INSTL_TY);
				dto.setINSTL_MBY(INSTL_MBY);
				dto.setSVC_SE(SVC_SE);
				dto.setCMCWR(CMCWR);
				dto.setCNSTC_YEAR(CNSTC_YEAR);
				dto.setINOUT_DOOR(INOUT_DOOR);
				dto.setREMARS3(REMARS3);
				dto.setLNT(LNT);
				dto.setLAT(LAT);
				dto.setWORK_DTTM(WORK_DTTM);
				dto.setDistance(distance);
				
				list.add(dto);
				
			}
			
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return list.subList(0, 20);
	}
	
	public static void HistoryInsert(HistoryDto historydto) {
		Connection conn=null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			String file="D:\\SQLiteStudio\\zerbase.db";
			conn=DriverManager.getConnection("jdbc:sqlite:" + file);
			String sql = " INSERT INTO zerobase_history (lat, lnt, date) "
					+ " VALUES (?, ?, ?); ";
			PreparedStatement pst  = conn.prepareStatement(sql);
			pst.setDouble(1, historydto.getLat());
			pst.setDouble(2, historydto.getLnt());
			pst.setString(3, historydto.getDate());
			pst.executeUpdate();
			
			
			
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}
	
	public static List<HistoryDto> getHistoryList(){
		Connection conn=null;
		List<HistoryDto> historyList = new ArrayList<>();
		
		try {
			Class.forName("org.sqlite.JDBC");
			
			String file="D:\\SQLiteStudio\\zerbase.db";
			conn=DriverManager.getConnection("jdbc:sqlite:" + file);
			
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * from zerobase_history where data_yn='1' order by id DESC ");
			while(rs.next()) {
				int id = rs.getInt("id");
				Double lat = rs.getDouble("lat");
				Double lnt = rs.getDouble("lnt");
				String date = rs.getString("date");
				String data_yn = rs.getString("data_yn");
				
				HistoryDto dto = new HistoryDto();
				dto.setId(id);
				dto.setLat(lat);
				dto.setLnt(lnt);
				dto.setDate(date);
				dto.setData_yn(data_yn);
				historyList.add(dto);
				
			}
			
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return historyList;
		
	}
	public static void HistoryDelete(HistoryDto historydto) {
		Connection conn=null;
		
		
		try {
			Class.forName("org.sqlite.JDBC");
			
			String file="D:\\SQLiteStudio\\zerbase.db";
			conn=DriverManager.getConnection("jdbc:sqlite:" + file);
			String sql = " UPDATE zerobase_history set data_yn='0' where id=?";
			PreparedStatement pst  = conn.prepareStatement(sql);
			pst.setInt(1, historydto.getId());
			pst.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}
}
