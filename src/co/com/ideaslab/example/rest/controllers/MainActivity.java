package co.com.ideaslab.example.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import co.com.ideaslab.example.rest.model.Contact;
import co.com.ideaslab.example.rest.services.rest.RestClient;

public class MainActivity extends ListActivity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private TextView label;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_contacts);
		label = (TextView)findViewById(R.id.label);
		
		try {
			JSONObject jsonObject = RestClient.read(getString(R.string.url));
			ArrayList<HashMap<String, String>> contactList = Contact.jsonToObject(jsonObject);
			ListAdapter adapter = new SimpleAdapter(this, contactList,
					R.layout.list_contactitem,
					new String[] { Contact.TAG_NAME, Contact.TAG_EMAIL, Contact.TAG_PHONE_MOBILE }, 
					new int[] {R.id.name, R.id.email, R.id.mobile });
			setListAdapter(adapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
