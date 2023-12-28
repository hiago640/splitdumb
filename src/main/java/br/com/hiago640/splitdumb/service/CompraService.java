package br.com.hiago640.splitdumb.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hiago640.splitdumb.model.Compra;
import br.com.hiago640.splitdumb.model.Pessoa;
import br.com.hiago640.splitdumb.model.TipoOperacaoEnum;
import br.com.hiago640.splitdumb.model.Transferencia;
import br.com.hiago640.splitdumb.repository.CompraRepository;

@Service
public class CompraService {

	private static final Logger logger = LoggerFactory.getLogger(CompraService.class);

	@Autowired
	private CompraRepository compraRepository;

	@Transactional
	public void salvar(Compra compra) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método salvar");
		logger.trace(">>>>>>>>>>>>>>>> salvando compra: {}", compra);

		compraRepository.save(compra);

		Pessoa comprador = compra.getComprador();
		BigDecimal valorCompra = compra.getValorCompra();
		BigDecimal qtdDivisores = new BigDecimal(compra.getEnvolvidos().size());
		List<Pessoa> envolvidos = compra.getEnvolvidos();

		Transferencia transferencia;
		BigDecimal valorATransferir;

		logger.info("Criando transferências");
		for (Pessoa envolvido : envolvidos) {
			
			if (!envolvido.equals(comprador)) {
				logger.info("\n");

				valorATransferir = valorCompra.divide(qtdDivisores, 4, RoundingMode.HALF_UP);

				transferencia = new Transferencia(envolvido);
				transferencia.setTipoOperacao(TipoOperacaoEnum.DESPESA);
				transferencia.setRecebedor(comprador);
				transferencia.setValor(valorATransferir);
				envolvido.getTransferencias().add(transferencia);

				logger.info("Pagador: {} -> Recebedor: {}", envolvido.getNome(), comprador.getNome());
				logger.info("Saldo do pagador: {}", transferencia.getValorCalculado());

				logger.info("Sentido Contrário agora");
				logger.info("Pagador: {} -> Recebedor: {}", envolvido.getNome(), comprador.getNome());
				transferencia.setTipoOperacao(TipoOperacaoEnum.RECEITA);

				logger.info("Saldo do comprador: {}", transferencia.getValorCalculado());
				comprador.getTransferencias().add(transferencia);

			}

		}

		logger.trace(">>>>>>>>>>>>>>>> Compra salva!");

	}

	@Transactional
	public void alterar(Compra compra) {
		logger.trace(">>>>>>>>>>>>>>>> Entrou no método alterar");
		logger.trace(">>>>>>>>>>>>>>>> salvando compra: {}", compra);

		compraRepository.save(compra);

		logger.trace(">>>>>>>>>>>>>>>> Compra alterada!");

	}
//
//	@Transactional
//	public void remover(Long id) {
//		logger.trace(">>>>>>>>>>>>>>>> Entrou no método remover");
//		logger.trace(">>>>>>>>>>>>>>>> removendo a compra com o id: {}", id);
//		
//		compraRepository.deleteById(id);
//		
//		logger.trace(">>>>>>>>>>>>>>>> Compra removida");
//	}
}
