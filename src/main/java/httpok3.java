import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class httpok3 {
	
		
		public boolean getUserInfo(String key) {
			 
		    try {
		        String url = "http://127.0.0.1:8080/ZeroBase_Mission_01/get?key=" + key;
		 
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
