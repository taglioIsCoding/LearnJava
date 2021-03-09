package person;

public class Person {
	private String nameSurname;
	private int yearOfBirth;
	
	public Person(String nameSurname, int yearOfBirth) {
		this.nameSurname = nameSurname;
		this.yearOfBirth = yearOfBirth;
	}
	
	public Person(String nameSurname, String yearOfBirth) {
		this.nameSurname = nameSurname;
		this.yearOfBirth = Integer.parseInt(yearOfBirth);
	}
	
	public int getYearOfBirth() {
		return this.yearOfBirth;
	}
	
	public String getNameSurname() {
		return this.nameSurname;
	}
	
	public boolean omonimo(Person p1) {
		if(this.nameSurname.equalsIgnoreCase(p1.getNameSurname())) {
			return true;
		}else {
			return false;
		}
	}
	
	public int olderThan(Person other) {
		if(this.yearOfBirth > other.getYearOfBirth()) {
			return -1;
		}else if(this.yearOfBirth < other.getYearOfBirth()) {
			return 1;
		}else {
			return 0;
		}
	}

	
}
