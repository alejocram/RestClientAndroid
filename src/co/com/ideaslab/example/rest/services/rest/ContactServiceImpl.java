package co.com.ideaslab.example.rest.services.rest;

import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.Resources;

import co.com.ideaslab.example.rest.controllers.R;
import co.com.ideaslab.example.rest.model.Contact;
import co.com.ideaslab.example.rest.services.ContactService;

public class ContactServiceImpl implements ContactService {
	private static final String TAG = ContactServiceImpl.class.getSimpleName();
	
	private static final String ADD_CONTACT_ACTION = "addContact";
	private Context context;

	public ContactServiceImpl(Context context) {
		this.context = context;
	}
	
	@Override
	public void addContact(String name, String email, String address, String gender) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", 0);
		jsonObject.put("name", name);
		jsonObject.put("email", email);
		jsonObject.put("address", address);
		jsonObject.put("gender", gender);

        LinkedHashMap<String, Object> mapJson=new LinkedHashMap<String, Object>();
        mapJson.put("action","addContact");
        mapJson.put("Contact", jsonObject.toString());
		
        
        
        StringBuffer buildURL = new StringBuffer();
		buildURL.append(this.context.getResources().getString(R.string.url));
//		buildURL.append("?action="+ADD_CONTACT_ACTION);
        
		RestClient.post(buildURL.toString(),mapJson);
	}

}
