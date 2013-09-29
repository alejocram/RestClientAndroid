package co.com.ideaslab.example.rest.services.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RestClient {
	private static final String TAG = RestClient.class.getSimpleName();
	private static final String HEADER_CONTENT_TYPE_KEY = "content-type";
	private static final String HEADER_CONTENT_TYPE_VALUE = "application/json";

	public static JSONArray read(String url) throws ClientProtocolException, IOException, JSONException {
		//Se instancia el cliente de la conexion
		DefaultHttpClient httpClient= new DefaultHttpClient();
		//Sera de tipo GET
		HttpGet del =new HttpGet(url);
		//Se indica en el encabezado que la respuesta sera en JSON
		del.setHeader(HEADER_CONTENT_TYPE_KEY, HEADER_CONTENT_TYPE_VALUE);
		
		//Se ejecuta la peticion y se recibe la trama de datos
		HttpResponse resp = httpClient.execute(del);
		//Se recupera la trama de datos en String
		String json = EntityUtils.toString(resp.getEntity());
		//Se convierte la respuesta en un JSONArray
		JSONArray object = new JSONArray(json);
		return object;
	}
	
	public static JSONObject post(String url, LinkedHashMap<String,Object> mapJSON) throws ClientProtocolException, IOException, JSONException{
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
