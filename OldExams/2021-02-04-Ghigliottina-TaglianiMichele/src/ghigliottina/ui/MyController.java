package ghigliottina.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ghigliottina.model.Ghigliottina;

public class MyController implements Controller{
	
	private List<Ghigliottina> ghigliottine;
	
	public MyController(List<Ghigliottina> ghigliottine) {
		this.ghigliottine = new ArrayList<>(ghigliottine);
	}
	
	@Override
	public Ghigliottina sorteggiaGhigliottina() {
		Random random = new Random();
		return ghigliottine.get(random.nextInt(ghigliottine.size()));
	}

	@Override
	public List<Ghigliottina> listaGhigliottine() {
		return this.listaGhigliottine();
	}

}
