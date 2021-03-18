package fractioncollection;

import java.util.StringJoiner;

import frazione.Frazione;

public class FractionCollection {
	
	private int DEFAULT_GROWTH_FACTOR = 2;
	private int DEFAULT_PHSICAL_SIZE = 10;
	
	private Frazione[] innerContainer;
	private int size;
	
	public FractionCollection() {
		this.innerContainer = new Frazione[DEFAULT_PHSICAL_SIZE];
		this.size = 0;
	}
	
	public FractionCollection(int physicalSize) {
		this.innerContainer = new Frazione[physicalSize];
		this.size = 0;
	}
	
	public FractionCollection(Frazione[] collection) {
		this.innerContainer = new Frazione[collection.length];
		for (int i = 0; i < collection.length; i++) {
			if(collection[i] != null) {
				this.innerContainer[i] = collection[i];
				this.size++;
			}
		}
            
	}
	
	public int size() {
		return this.size;
	}
	
	public Frazione get(int index) {
		return this.innerContainer[index];
	}
	
	public void put(Frazione f) {
		if(this.size + 1 > this.innerContainer.length ) {
			Frazione[] bigger = new Frazione[this.size * DEFAULT_GROWTH_FACTOR];
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
	
	public String toString() {
		StringJoiner joiner = new StringJoiner(",", "[", "]" );
		for(Frazione f: this.innerContainer)
			joiner.add(f.toString());
		
		return joiner.toString();
	}

	
	public FractionCollection sum(FractionCollection setB) { 
		if (this.size() != setB.size()) 
			return null; 
		FractionCollection result = new FractionCollection(setB.size()); 
		for (int k=0; k< setB.size(); k++){
			  result.put(this.get(k).sum(setB.get(k)));
		}
		return result;
	}
	
	public FractionCollection sub(FractionCollection setB) { 
		if (this.size() != setB.size()) 
			return null; 
		FractionCollection result = new FractionCollection(setB.size()); 
		for (int k=0; k< setB.size(); k++){
			  result.put(this.get(k).sub(setB.get(k)));
		}
		return result;
	}
	
	public FractionCollection mul(FractionCollection setB) { 
		if (this.size() != setB.size()) 
			return null; 
		FractionCollection result = new FractionCollection(setB.size());
		for (int k=0; k<setB.size(); k++){
			result.put(this.get(k).mul(setB.get(k)));
		}
		return result;
	}
	
	public FractionCollection div(FractionCollection setB) { 
		if (this.size() != setB.size()) 
			return null; 
		FractionCollection result = new FractionCollection(setB.size());
		for (int k=0; k<setB.size(); k++){
			result.put(this.get(k).mul(setB.get(k).reciprocal()));
		}
		return result;
	}
}










