
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class httpok3 {
	
		
		public boolean getUserInfo(String key) {
			 
		    try {
		        String url = "http://openapi.seoul.go.kr:8088/434c545a586a6a613636494f775847/json/TbPublicWifiInfo/1/100/";
		 
		        OkHttpClient client = new OkHttpClient();
		 
		        Request.Builder builder = new Request.Builder().url(url).get();
		        builder.addHeader("password", "BlahBlah");
		 
		        Request request = builder.build();
		 
		        Response response = client.newCall(request).execute();
		        if (response.isSuccessful()) {
		            ResponseBody body = response.body();
		            if (body != null) {
		                System.out.println("Response:" + body.string());
		            }
		        }
		        else
		            System.err.println("Error Occurred");
		 
		        return true;
		    } catch(Exception e) {
		        e.printStackTrace();
		    }
		    
		    return false;
		}
	
}
