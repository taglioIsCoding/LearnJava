package agenda.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;


public class Agenda {

	private Set<Contact> contactSet = new TreeSet<Contact>();
	
	public Agenda() {
		
	}
	
	public Agenda(Collection<Contact> contactSet) {
		this.contactSet = new TreeSet<Contact>(contactSet);
	}
	
	public void addContact(Contact c) {
		this.contactSet.add(c);
	}
	
	public void removeContact(Contact c) {
		this.contactSet.remove(c);
	}
	
	public Optional<Contact> getContact(String name, String surname) {
		for( Contact c: contactSet) {
			if(c.equals(new Contact(name, surname))) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}
	
	public Set<Contact> searchContacts(String surname) {
		Set<Contact> returnSet = new TreeSet<Contact>();
		for( Contact c: contactSet) {
			if(c.getSurname().equals(surname)) {
				returnSet.add(c);
			}
		}
		
		return returnSet;
	}
	
	public Optional<Contact> getContact(int index) {
		int i = 0;
		for( Contact c: contactSet) {
			if(i == index) {
				return Optional.of(c);
			}
			i++;
		}
		return Optional.empty();
	}

	public Set<Contact> getContacts() {
		return contactSet;
	}
	
	
	
}
