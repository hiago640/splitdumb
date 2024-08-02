package br.com.hiago640.estudo.novo_splitdumb;

import java.util.ArrayList;
import java.util.List;

import br.com.hiago640.estudo.novo_splitdumb.controller.DespesaProcessor;
import br.com.hiago640.estudo.novo_splitdumb.model.Usuario;
import br.com.hiago640.estudo.novo_splitdumb.model.abstracts.Split;

public class ClassTest {

	public static void main(String[] args) {
		
		DespesaProcessor manager = new DespesaProcessor();
		
		Usuario hiago = new Usuario("hia", "Hiago", "gaurav@workat.tech");
		Usuario leandro = new Usuario("lea", "Leandro", "gaurav@workat.tech");
		Usuario tayna = new Usuario("tay", "Tayna", "gaurav@workat.tech");
		Usuario otoniel = new Usuario("oto", "Otoniel", "gaurav@workat.tech");
		
		manager.addUsuario(hiago);
		manager.addUsuario(leandro);
		manager.addUsuario(tayna);
		manager.addUsuario(otoniel);

		
		//primeira compra
		//hia 1000 4 hia lea tay oto EQUAL
		List<Split> splits = new ArrayList<>();
		splits.add(new Split(hiago));
		splits.add(new Split(leandro));
		splits.add(new Split(tayna));
		splits.add(new Split(otoniel));
		
		manager.addDespesa(hiago, 1000D, splits, null);
		splits.clear();
		
		splits.add(new Split(hiago));
		splits.add(new Split(leandro));
		
		manager.addDespesa(leandro, 150D, splits, null);
		splits.clear();

		splits.add(new Split(hiago));
		splits.add(new Split(leandro));
		splits.add(new Split(tayna));
		
		manager.addDespesa(tayna, 800D, splits, null);
		splits.clear();
		
		manager.otimizarSaldos();
	}
			
}