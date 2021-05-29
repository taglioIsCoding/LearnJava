package oroscopo.model;

public class OroscopoMensile implements Oroscopo, Comparable<Oroscopo> {
	
	private Previsione amore;
	private Previsione lavoro;
	private Previsione salute;
	private SegnoZodiacale segnoZodiacale;
	
	public OroscopoMensile(SegnoZodiacale segnozod, Previsione amore, Previsione lavoro, Previsione salute) {
		if(segnozod == null || amore == null || lavoro == null || salute == null) {
			throw new IllegalArgumentException();
		} else {
			this.segnoZodiacale = segnozod;
			this.amore = amore;
			this.salute = salute;
			this.lavoro = lavoro;
			if(!amore.validaPerSegno(segnozod) || !lavoro.validaPerSegno(segnozod) || !salute.validaPerSegno(segnozod)) {
				throw new IllegalArgumentException();
			}
		}
		
	}
	
	public OroscopoMensile(String segnozod, Previsione amore, Previsione lavoro, Previsione salute) {
		if(segnozod != null || amore != null || lavoro != null || salute != null) {
		SegnoZodiacale toAssign = getSegnoZodiacaleFrom(segnozod);
			if(toAssign == null) {
				throw new IllegalArgumentException("QUESTO_SEGNO_NON_ESISTE");
			}else if(!amore.validaPerSegno(toAssign) || !lavoro.validaPerSegno(toAssign) || !salute.validaPerSegno(toAssign)) {
				throw new IllegalArgumentException();
			}
		this.segnoZodiacale = toAssign;
		this.amore = amore;
		this.salute = salute;
		this.lavoro = lavoro;
			
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private SegnoZodiacale getSegnoZodiacaleFrom(String sz) {
		return SegnoZodiacale.valueOf(sz);
	}

	@Override
	public SegnoZodiacale getSegnoZodiacale() {
		return this.segnoZodiacale;
	}

	@Override
	public Previsione getPrevisioneAmore() {
		return this.amore;
	}

	@Override
	public Previsione getPrevisioneSalute() {
		
		return this.salute;
	}

	@Override
	public Previsione getPrevisioneLavoro() {
		return this.lavoro;
	}

	@Override
	public int getFortuna() {
		//System.out.println(((this.amore.getValore() + this.lavoro.getValore() + this.salute.getValore())/3));
		return Math.round(((this.amore.getValore() + this.lavoro.getValore() + this.salute.getValore())/3f));
	}

	@Override
	public int compareTo(Oroscopo o) {
		return this.getSegnoZodiacale().compareTo(o.getSegnoZodiacale());
	}

	@Override
	public String toString() {
		return  amore.getPrevisione()+ lavoro.getPrevisione()+ salute.getPrevisione();
	}
	
	

}
