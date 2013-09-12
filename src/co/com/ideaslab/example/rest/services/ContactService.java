package co.com.ideaslab.example.rest.services;

import co.com.ideaslab.example.rest.model.Contact;

public interface ContactService {
	
	void addContact(String name, String email, String address, String gender) throws Exception;
}
