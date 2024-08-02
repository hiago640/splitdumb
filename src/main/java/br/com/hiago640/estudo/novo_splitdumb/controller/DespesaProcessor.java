package br.com.hiago640.estudo.novo_splitdumb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.hiago640.estudo.novo_splitdumb.model.DespesaInfo;
import br.com.hiago640.estudo.novo_splitdumb.model.Transacao;
import br.com.hiago640.estudo.novo_splitdumb.model.Usuario;
import br.com.hiago640.estudo.novo_splitdumb.model.abstracts.Despesa;
import br.com.hiago640.estudo.novo_splitdumb.model.abstracts.Split;

public class DespesaProcessor {

	List<Despesa> despesas;
	List<Usuario> usuarios;
	Map<Usuario, Map<Usuario, Double>> balancoPatrimonial;

	public DespesaProcessor() {
		despesas = new ArrayList<>();
		usuarios = new ArrayList<>();
		balancoPatrimonial = new HashMap<>();
	}

	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
		balancoPatrimonial.put(usuario, new HashMap<>());
	}

	public void addDespesa(Usuario comprador, double vlrCompra, List<Split> splits, DespesaInfo info) {

		Despesa despesa = DespesaService.criarDespesa(comprador, vlrCompra, splits, info);
//		System.out.println(despesa);
		despesas.add(despesa);

		// pra cada envolvido na compra
		for (Split split : despesa.getDivisoes()) {
//			System.out.println(split);
			Usuario devedor = split.getUsuario();
			Map<Usuario, Double> saldoComprador = balancoPatrimonial.get(comprador);

			if (!saldoComprador.containsKey(devedor))
				saldoComprador.put(devedor, 0.0D);

			// adiciona o devedor pra pagar ao comprador
			// credita o valor da quantia no saldo do comprador
			saldoComprador.put(devedor, (saldoComprador.get(devedor) + split.getQuantia()));

			Map<Usuario, Double> saldoDevedor = balancoPatrimonial.get(devedor);
			if (!saldoDevedor.containsKey(comprador))
				saldoDevedor.put(comprador, 0.0);

			// adiciona o comprador pra receber do devedor
			// debita o valor da quantia no saldo do devedor
			saldoDevedor.put(comprador, (saldoDevedor.get(comprador) - split.getQuantia()));

		}

	}

	public void otimizarSaldos() {

		// Calcule o saldo líquido de cada usuário
		Map<Usuario, Double> saldosLiquidos = new HashMap<>();
		for (Usuario usuario : balancoPatrimonial.keySet()) {
			double saldoLiquido = 0;
			for (Double valor : balancoPatrimonial.get(usuario).values()) {
				saldoLiquido += valor;
			}
			saldosLiquidos.put(usuario, saldoLiquido);
		}

		// Lista para armazenar as transações otimizadas
		List<Transacao> transacoes = new ArrayList<>();

		// Liste os credores e devedores
		List<Usuario> credores = new ArrayList<>();
		List<Usuario> devedores = new ArrayList<>();

		for (Map.Entry<Usuario, Double> entry : saldosLiquidos.entrySet()) {
			Usuario usuario = entry.getKey();
			double saldo = entry.getValue();
			if (saldo > 0) {
				credores.add(usuario);
			} else if (saldo < 0) {
				devedores.add(usuario);
			}
		}

		consolidarDividasECreditos(saldosLiquidos, transacoes, credores, devedores);

		// Exibe as transações consolidadas
		transacoes.forEach(System.out::println);
	}

	private void consolidarDividasECreditos(Map<Usuario, Double> saldosLiquidos, List<Transacao> transacoes,
			List<Usuario> credores, List<Usuario> devedores) {

		for (Usuario devedor : devedores) {
			for (Iterator<Usuario> credorIterator = credores.iterator(); credorIterator.hasNext();) {
				Usuario credor = credorIterator.next();
				double valor = Math.min(-saldosLiquidos.get(devedor), saldosLiquidos.get(credor));

				// Adiciona a transação
				if (valor > 0) {
					transacoes.add(new Transacao(devedor, credor, valor));

					// Atualiza os saldos líquidos
					saldosLiquidos.put(devedor, Math.round((saldosLiquidos.get(devedor) + valor) * 100.0) / 100.0);
					saldosLiquidos.put(credor, Math.round((saldosLiquidos.get(credor) - valor) * 100.0) / 100.0);

					// Remove o credor se o saldo for 0
					if (saldosLiquidos.get(credor) == 0) {
						credorIterator.remove();
					}

					// Interrompe o loop se o devedor já pagou toda a dívida
					if (saldosLiquidos.get(devedor) == 0) {
						break;
					}
				}
			}
		}
	}

}
