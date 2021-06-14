package happybank.model;

public class HappyBancomat extends AbstractBancomat{

	@Override
	public ImportoErogato erogaImporto(TesseraBancomat tessera, int importo, int taglio1, int taglio2)
			throws ImportoNonErogabileException {
		
		if (tessera.checkPrelevamentoDalConto(importo) 
				&&tessera.checkMaxPrelevabile(importo) 
				&& tessera.getContoCorrente().possoPrelevare(importo)) {	
				
				int n1=0, n2=0;
				n1 = importo / taglio1;
				n2 = (importo - (taglio1 * n1))/taglio2;
				
				while((n1*taglio1 + n2*taglio2) != importo && n1 != 0) {
					n1 --;
					n2 = (importo - (taglio1 * n1))/taglio2;
				}
				
//				System.out.println("importo " + importo+" n1 " + n1+" n2 " + n2);
				
				if(n1*taglio1 + n2*taglio2 == importo && n1 >= 0 && n2 >= 0) {
					
					
					if(taglio1 > taglio2) {
						if(getNumPiccoloTaglio() - n1 < 0 || getNumGrandeTaglio() - n2 < 0) {
							throw new ImportoNonErogabileException("Importo non erogabile");
						}else {
							this.setNumPiccoloTaglio(getNumPiccoloTaglio() - n2);
							this.setNumGrandeTaglio(getNumGrandeTaglio() - n1);
							tessera.prelievo(importo);
							return new ImportoErogato(getPiccoloTaglio(), n2, getGrandeTaglio(), n1);
						}	
					}else {
						if(getNumPiccoloTaglio() - n2 < 0 || getNumGrandeTaglio() - n1 < 0) {
							throw new ImportoNonErogabileException("Importo non erogabile");
						}else {
							this.setNumPiccoloTaglio(getNumPiccoloTaglio() - n1);
							this.setNumGrandeTaglio(getNumGrandeTaglio() - n2);
							tessera.prelievo(importo);
							return new ImportoErogato(getPiccoloTaglio(), n1, getGrandeTaglio(), n2);
						}	
					}
				}else {
					throw new ImportoNonErogabileException("Importo non erogabile");
				}
	
		}else {
			throw new ImportoNonErogabileException("Importo non erogabile");
		}
	}

}
