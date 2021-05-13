package agenda.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Contact implements Comparable<Contact>{

	private List<Detail> detailList = new ArrayList<Detail>();
	private String name;
	private String surname;
	
	public Contact(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	
	public Contact(String name, String surname, List<Detail> datailList) {
		this.name = name;
		this.surname = surname;
		this.detailList = datailList;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Detail> getDetailList() {
		return detailList;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		 StringBuilder builder = new StringBuilder();
	        builder.append(name + " ");
	        builder.append(surname + "\n");
	        for (Detail detail : detailList) 
	            builder.append(detail.toString() + "\n");

	        return builder.toString();
	}
	

	
	public int compareTo(Contact o) {
		if(this.getSurname().compareToIgnoreCase(o.getSurname()) > 0) {
			return 1;
		}
		else if(this.getSurname().compareToIgnoreCase(o.getSurname()) < 0) {
			return -1;
		}
		else{
			if(this.getName().compareToIgnoreCase(o.getName()) > 0) {
				return 1;
			}
			if(this.getName().compareToIgnoreCase(o.getName()) < 0) {
				return -1;
			}
		}
		return 0;
	}

}
