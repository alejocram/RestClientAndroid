package co.com.ideaslab.example.rest.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Contact {
	
	public static final String TAG_CONTACTS = "contacts";
	public static final String TAG_ID = "id";
	public static final String TAG_NAME = "name";
	public static final String TAG_EMAIL = "email";
	public static final String TAG_ADDRESS = "address";
	public static final String TAG_GENDER = "gender";
	public static final String TAG_PHONE = "phone";
	public static final String TAG_PHONE_MOBILE = "mobile";
	public static final String TAG_PHONE_HOME = "home";
	public static final String TAG_PHONE_OFFICE = "office";
		
	public static ArrayList<HashMap<String, String>> jsonToObject(JSONArray contacts) throws JSONException{
		//Hashmap
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
		// looping through All Contacts
		for(int i = 0; i < contacts.length(); i++){
			JSONObject c = contacts.getJSONObject(i);
			
			// Storing each json item in variable
			String id = c.getString(TAG_ID);
			String name = c.getString(TAG_NAME);
			String email = c.getString(TAG_EMAIL);
			String address = c.getString(TAG_ADDRESS);
			String gender = c.getString(TAG_GENDER);
			
			// Phone number is agin JSON Object
			JSONObject phone = c.getJSONObject(TAG_PHONE);
			String mobile = phone.getString(TAG_PHONE_MOBILE);
			String home = phone.getString(TAG_PHONE_HOME);
			String office = phone.getString(TAG_PHONE_OFFICE);
			
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			
			// adding each child node to HashMap key => value
			map.put(TAG_ID, id);
			map.put(TAG_NAME, name);
			map.put(TAG_EMAIL, email);
			map.put(TAG_PHONE_MOBILE, mobile);
	
			contactList.add(map);
		}
		return contactList;
	}
}
