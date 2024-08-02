package br.com.hiago640.estudo.novo_splitdumb.controller;

import java.util.List;

import br.com.hiago640.estudo.novo_splitdumb.model.DespesaInfo;
import br.com.hiago640.estudo.novo_splitdumb.model.Usuario;
import br.com.hiago640.estudo.novo_splitdumb.model.abstracts.Despesa;
import br.com.hiago640.estudo.novo_splitdumb.model.abstracts.Split;

public class DespesaService {

	public static Despesa criarDespesa(Usuario comprador, double vlrCompra, List<Split> splits, DespesaInfo info) {
		
		int qtdParticipantesDaCompra = splits.size();
		double quantiaDaDivisao = (Math.round(vlrCompra * 100 / qtdParticipantesDaCompra)) / 100.0;
		
		splits.forEach(s -> s.setQuantia(quantiaDaDivisao));
				
		/*
		 * primeiro devedor = quantidade + (valor total - quantidade * quantidade de
		 * devedores) Essa expressão calcula a diferença entre o valor total da despesa
		 * (amount) e o valor que foi dividido igualmente (splitAmount * totalSplits).
		 * 
		 * Se o valor total da despesa não for exatamente divisível pelo número de
		 * pessoas, essa diferença é o "restante" que deve ser ajustado para garantir
		 * que a soma das contribuições iguale o total da despesa.
		 */
		splits.get(0).setQuantia(quantiaDaDivisao + (vlrCompra - quantiaDaDivisao * qtdParticipantesDaCompra));
		return new Despesa(comprador, vlrCompra, splits, info);
	}
	
}
