package ghigliottina.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ghigliottina implements Iterator<Terna>{
	
	private Iterator<Terna> iteretor;
	private String rispostaEsatta;
	private List<Terna> terne;
	
	public Ghigliottina(List<Terna> terne, String rispostaEsatta) {
		if(terne == null | rispostaEsatta == null || rispostaEsatta.isBlank()) {
			throw new IllegalArgumentException("campi nulli per ghigliottina");
		}
		
		this.terne = new ArrayList<>(terne);
		this.iteretor = terne.iterator();
		this.rispostaEsatta = rispostaEsatta;
	}
	

	@Override
	public String toString() {
		return "Ghigliottina [rispostaEsatta=" + rispostaEsatta + ", terne=" + terne + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rispostaEsatta == null) ? 0 : rispostaEsatta.hashCode());
		result = prime * result + ((terne == null) ? 0 : terne.hashCode());
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
		Ghigliottina other = (Ghigliottina) obj;
		if (rispostaEsatta == null) {
			if (other.rispostaEsatta != null)
				return false;
		} else if (!rispostaEsatta.equals(other.rispostaEsatta))
			return false;
		if (terne == null) {
			if (other.terne != null)
				return false;
		} else if (!terne.equals(other.terne))
			return false;
		return true;
	}
	
	

	public String getRispostaEsatta() {
		return rispostaEsatta;
	}



	public List<Terna> getTerne() {
		return terne;
	}


	@Override
	public boolean hasNext() {
		return this.iteretor.hasNext();
	}

	@Override
	public Terna next() {
		return this.iteretor.next();
	}

}
