package co.com.ideaslab.example.rest.services.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

public class RestClient {
	private static final String TAG = RestClient.class.getSimpleName();
	private static final String HEADER_CONTENT_TYPE_KEY = "content-type";
	private static final String HEADER_CONTENT_TYPE_VALUE = "application/json";

	public static JSONObject read(String url) throws Exception{
		DefaultHttpClient httpClient= new DefaultHttpClient();		
		HttpGet del =new HttpGet(url);
		del.setHeader(HEADER_CONTENT_TYPE_KEY, HEADER_CONTENT_TYPE_VALUE);
		
		JSONObject object=null;
		HttpResponse resp;
		try {
			resp = httpClient.execute(del);
			String json=EntityUtils.toString(resp.getEntity());
			object=new JSONObject(json);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}		
		return object;
	}
}
