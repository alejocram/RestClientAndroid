package co.com.ideaslab.example.rest.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
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
import android.widget.Toast;
import co.com.ideaslab.example.rest.model.Contact;
import co.com.ideaslab.example.rest.services.ContactService;
import co.com.ideaslab.example.rest.services.rest.ContactServiceImpl;
import co.com.ideaslab.example.rest.services.rest.RestClient;

/**
 * Esta es la clase principal
 * @author Alejocram
 */
public class MainActivity extends ListActivity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final int REQ_CODE_MAIN_TO_ADD = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Se indica el layout que se va utilizar para este ListActivity
		setContentView(R.layout.list_contacts);
		//Se crea la vista
		buildView(); 
	}

	private void buildView() {
		//Se instancia el ContactService 
		ContactService contactService = new ContactServiceImpl(this);
		ArrayList<HashMap<String, String>> contactList;
		try {
			//Se recupera el listado de contactos
			contactList = contactService.showContact();
			//Se crea un adaptador para llenar el ListView, cada valor queda relacionado
			ListAdapter adapter = new SimpleAdapter(this, contactList,
					R.layout.list_contactitem,
					new String[] { Contact.TAG_NAME, Contact.TAG_EMAIL, Contact.TAG_PHONE_MOBILE }, 
					new int[] {R.id.name, R.id.email, R.id.mobile });
			setListAdapter(adapter);
		} catch (Exception e) {
			//Se muestra el error en pantalla
			Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_LONG).show();
			e.printStackTrace();
			//Se muestra el error en el LogCat
			Log.e(ContactServiceImpl.TAG, e.getMessage());
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
		//Se llama otra ventana pero se espera un resultado
		startActivityForResult(i, REQ_CODE_MAIN_TO_ADD);
		
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Verifica el codigo del request code
		if(requestCode == REQ_CODE_MAIN_TO_ADD){
			//Se refresca la lista
			buildView();
			//Verifica que el codigo de respuesta este siendo controlado
			if(resultCode == RESULT_OK){
				//Se recupera la informacion que retorna la otra pantalla
				String id = data.getStringExtra("id");
				String name = data.getStringExtra("name");
				//Se expone un mensaje indicando que ya agrego
				Toast.makeText(this, name +" ha sido agregado exitosamente", Toast.LENGTH_SHORT).show();
			}
		}
	}

}
