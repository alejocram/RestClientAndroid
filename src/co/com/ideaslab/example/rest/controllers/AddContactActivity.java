package co.com.ideaslab.example.rest.controllers;

import co.com.ideaslab.example.rest.services.rest.ContactServiceImpl;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends Activity implements OnClickListener{
	private static final String TAG = AddContactActivity.class.getSimpleName();
	private EditText nameEt;
	private EditText emailEt;
	private EditText addressEt;
	private EditText genderEt;
	private Button addContactBt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);
		nameEt = (EditText)findViewById(R.id.name_ev);
		emailEt = (EditText)findViewById(R.id.email_ev);
		addressEt = (EditText)findViewById(R.id.address_et);
		genderEt = (EditText)findViewById(R.id.gender_et);
		addContactBt = (Button)findViewById(R.id.add_contact_bt);
		addContactBt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == addContactBt){
			StringBuffer buildURL = new StringBuffer();
			buildURL.append(getString(R.string.url));
			try {
		        //Se instancia el servicio y se recupera los datos del formulario
				ContactServiceImpl contactServiceImpl = new ContactServiceImpl(this);
				contactServiceImpl.addContact(
						nameEt.getText().toString(), 
						emailEt.getText().toString(), 
						addressEt.getText().toString(), 
						genderEt.getText().toString());
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
