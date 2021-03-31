package model;



public class AppointmentCollection {
	
	private int DEFAULT_GROWTH_FACTOR = 2;
	private int DEFAULT_PHSICAL_SIZE = 10;
	
	private Appointment[] innerContainer;
	private int size;
	
	public AppointmentCollection() {
		this.innerContainer = new Appointment[DEFAULT_PHSICAL_SIZE];
		this.size = 0;
	}
	
	public AppointmentCollection(int physicalSize) {
		this.innerContainer = new Appointment[physicalSize];
		this.size = 0;
	}
	
//	public AppointmentCollection(Appointment[] collection) {
//		this.innerContainer = new Appointment[collection.length];
//		for (int i = 0; i < collection.length; i++) {
//			if(collection[i] != null) {
//				this.innerContainer[i] = collection[i];
//				this.size++;
//			}
//		}
//            
//	}
	
	public int size() {
		return this.size;
	}
	
	public Appointment get(int index) {
		return this.innerContainer[index];
	}
	
	public void add(Appointment f) {
		if(this.size + 1 > this.innerContainer.length ) {
			Appointment[] bigger = new Appointment[this.size * DEFAULT_GROWTH_FACTOR];
			for (int i = 0; i < this.size; i++) {
				 bigger[i] = this.innerContainer[i];
			}
			this.size++;
			bigger[this.size()] = f;
			this.innerContainer = bigger;
		} else {
			this.innerContainer[this.size()] = f;
			this.size++;
		}
	}	
	
	public void remove(int index) {
		for(int i = index; i < this.size; i++) {
			this.innerContainer[i] = this.innerContainer[i+ 1];
		}
		this.size--;
	}	
	
	public int indexOf(Appointment app) {
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).equals(app)) {
				return i;
			}
		}
		return -1;
	}
	
}










