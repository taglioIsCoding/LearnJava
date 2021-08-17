package crosswords.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class MyGame extends Scheme implements Game{
	
	private Map<Integer,Optional<String>> charMap = new HashMap<>();
	
	public MyGame(int size) {
		super(size);
	}

	@Override
	public void setMapping(int num, String value) {
		if(value.isEmpty()) {
			this.charMap.put(num, Optional.empty());
			return;
		}
		this.charMap.put(num, Optional.of(value));
	}

	@Override
	public Optional<String> getMapping(int num) {
		if (this.charMap.get(num) == null) {
			return Optional.empty();
		}
		return this.charMap.get(num);
	}

	@Override
	public boolean isFull() {
		
		//System.out.println(super.toString());
		
		for (int row=0; row<this.getSize(); row++) {
			for (int col=0; col<this.getSize(); col++) {
				if(this.getMapping(this.getCell(row, col)).isEmpty() && this.getCell(row, col) != 0){
					this.setMapping(this.getCell(row, col), "");
				}
			}	
		}	
		
		//System.out.println(this.charMap);
		
		//System.out.println(this.toString());
		
		for(Integer i : this.charMap.keySet()) {
			if(this.getMapping(i).isEmpty()) {
				//System.out.println("Falso per"+ i);
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int k=0; k<this.getSize()*this.getSize(); k++) {
			int row = k/this.getSize();
			int col = k%this.getSize();
			if(this.getMapping(this.getCell(row, col)).isEmpty() && this.getCell(row, col) != 0) {
				sb.append(this.getCell(k/this.getSize(),k%this.getSize()));
				sb.append(k%this.getSize()==this.getSize()-1 ? System.lineSeparator() : '\t');
			} else if(this.getCell(row, col) == 0) {
				sb.append("#");
				sb.append(k%this.getSize()==this.getSize()-1 ? System.lineSeparator() : '\t');
			} else {
				sb.append(this.getMapping(this.getCell(row, col)).get());
				sb.append(k%this.getSize()==this.getSize()-1 ? System.lineSeparator() : '\t');
			}
			
		}
		
		return sb.toString();
	}
}
