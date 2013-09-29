package co.com.ideaslab.example.rest.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import co.com.ideaslab.example.rest.model.Contact;

public interface ContactService {
	
	ArrayList<HashMap<String, String>> showContact()throws ClientProtocolException, IOException, JSONException;
	Contact addContact(String name, String email, String address, String gender) throws JSONException, ClientProtocolException, IOException;
}
