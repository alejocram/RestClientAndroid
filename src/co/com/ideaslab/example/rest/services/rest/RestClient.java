package co.com.ideaslab.example.rest.services.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class RestClient {
	private static final String TAG = RestClient.class.getSimpleName();
	private static final String HEADER_CONTENT_TYPE_KEY = "content-type";
	private static final String HEADER_CONTENT_TYPE_VALUE = "application/json";

	public static JSONArray read(String url) throws Exception{
		DefaultHttpClient httpClient= new DefaultHttpClient();		
		HttpGet del =new HttpGet(url);
		del.setHeader(HEADER_CONTENT_TYPE_KEY, HEADER_CONTENT_TYPE_VALUE);
		
		JSONArray object=null;
		HttpResponse resp;
		try {
			resp = httpClient.execute(del);
			String json=EntityUtils.toString(resp.getEntity());
			object=new JSONArray(json);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}		
		return object;
	}
	
	public static JSONObject post(String url, LinkedHashMap<String,Object> mapJSON) throws Exception{
		// Se crea la conexion tipo Post
		DefaultHttpClient httpClient= new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		
		// Creamos una lista
        List nameValuepairs = new ArrayList();
        
        // Añadimos los elementos a la lista
        for (Map.Entry<String,Object> entry : mapJSON.entrySet()) {
        	nameValuepairs.add(new BasicNameValuePair(entry.getKey(),(String) entry.getValue()));
        }
		
        // UrlEncodedFormEntity : Codificamos la lista para el envio por post
        post.setEntity(new UrlEncodedFormEntity(nameValuepairs));
        // Ejecutamos la solicitud y obtenemos una respuesta
        HttpResponse resp = httpClient.execute(post);

        //  Obtenemos el contenido de la respuesta
		String respStr = EntityUtils.toString(resp.getEntity());
		if(respStr!=null && !respStr.equals("")){
			JSONObject object=new JSONObject(respStr);
			return object;	
		}else{
			return null;
		}
	}
}
