package co.com.ideaslab.example.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import co.com.ideaslab.example.rest.model.Contact;
import co.com.ideaslab.example.rest.services.rest.ContactServiceImpl;
import co.com.ideaslab.example.rest.services.rest.RestClient;

public class MainActivity extends ListActivity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String SHOW_ACTION = "showContacts";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_contacts);
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(getString(R.string.url));
			stringBuffer.append("?action="+SHOW_ACTION);
			JSONArray jsonArray = RestClient.read(stringBuffer.toString());
			ArrayList<HashMap<String, String>> contactList = Contact.jsonToObject(jsonArray);
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
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent i = new Intent(getApplicationContext(), AddContactActivity.class);
		startActivity(i);
		
		return super.onMenuItemSelected(featureId, item);
	}

}
