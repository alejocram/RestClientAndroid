package co.com.ideaslab.example.rest.services.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import co.com.ideaslab.example.rest.controllers.R;
import co.com.ideaslab.example.rest.model.Contact;
import co.com.ideaslab.example.rest.services.ContactService;

public class ContactServiceImpl implements ContactService {
	public static final String TAG = ContactServiceImpl.class.getSimpleName();
	
	private static final String SHOW_CONTACTS_ACTION = "showContacts";
	private static final String ADD_CONTACT_ACTION = "addContact";
	private Context context;

	public ContactServiceImpl(Context context) {
		this.context = context;
	}
	
	@Override
	public ArrayList<HashMap<String, String>> showContact() throws ClientProtocolException, IOException, JSONException  {
		StringBuffer buildURL = new StringBuffer();
		buildURL.append(this.context.getResources().getString(R.string.url));
		buildURL.append("?action="+SHOW_CONTACTS_ACTION);
		JSONArray jsonArray = RestClient.read(buildURL.toString());
		return Contact.jsonToObject(jsonArray);
	}
	
	@Override
	public Contact addContact(String name, String email, String address, String gender) throws JSONException, ClientProtocolException, IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", 0);
		jsonObject.put("name", name);
		jsonObject.put("email", email);
		jsonObject.put("address", address);
		jsonObject.put("gender", gender);

        LinkedHashMap<String, Object> mapJson=new LinkedHashMap<String, Object>();
        mapJson.put("action",ADD_CONTACT_ACTION);
        mapJson.put("Contact", jsonObject.toString());

        StringBuffer buildURL = new StringBuffer();
		buildURL.append(this.context.getResources().getString(R.string.url));
        
		JSONObject jsonRes = RestClient.post(buildURL.toString(),mapJson);
		return Contact.jsonToObject(jsonRes);
	}
}
