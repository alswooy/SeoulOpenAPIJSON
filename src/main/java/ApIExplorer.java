
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApIExplorer {
	public static void main(String[] args) throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/7354734f416a6a613936514e477a6d/xml/TbPublicWifiInfo/1/5/"); 
		/*URL*/
		urlBuilder.append("/" + URLEncoder.encode("sample","UTF-8") ); /*����Ű
		(sample���ÿ��� ȣ��� ���ѵ˴ϴ�.)*/
		urlBuilder.append("/" + URLEncoder.encode("xml","UTF-8") ); /*��û����Ÿ��
		(xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew","UTF-8")); 
		/*���񽺸� (��ҹ��� ���� �ʼ��Դϴ�.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*��û������ġ
		(sample����Ű ���� 5�̳� ����)*/
		urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); 
		/*��û������ġ(sample����Ű ���� 5�̻� ���� ���� �� ��)*/
		// ���� 5���� �ʼ������� �����ٲ��� �ʰ� ȣ���ؾ� �մϴ�.
		// ���񽺺� �߰� ��û �����̸� �ڼ��� ������ �� ���񽺺� '��û����'�κп�
		urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* ���񽺺�
		�߰� ��û���ڵ�*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		System.out.println("Response code: " + conn.getResponseCode()); /* ����
		��ü�� ���� Ȯ���� �ʿ��ϹǷ� �߰��մϴ�.*/
		BufferedReader rd;
		// �����ڵ尡 �����̸� 200~300������ ���ڰ� ���ɴϴ�.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
		rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
		sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		
	}

}
