package dentinia.governor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.stream.Collectors;

public class LeggeElettoraleDHondt implements LeggeElettorale{

	@Override
	public RisultatoElezioni apply(Elezioni t) {
		SortedSet<Partito> pariti = t.getPartiti();
		ArrayList<Quoziente> quozientiArrayList = new ArrayList<>();
		
		
		for(Partito p: pariti) {
			for(int i=1; i < t.getSeggiDaAssegnare(); i++) {
				quozientiArrayList.add(new Quoziente(p, t.getVoti(p)/i));
			}
		}
		
		Collections.sort(quozientiArrayList);
		List<Quoziente> firstNElementsList = quozientiArrayList.stream().limit(t.getSeggiDaAssegnare()).collect(Collectors.toList());;
		
		System.out.println(t.getSeggiDaAssegnare());
		RisultatoElezioni rlElezioni = new RisultatoElezioni(pariti);
		for(Quoziente q: firstNElementsList) {
			rlElezioni.setSeggi(q.getPartito(), rlElezioni.getSeggi(q.getPartito())+1);
		}
		
		return rlElezioni;
	}

}
