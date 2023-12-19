package br.com.hiago640.splitdumb.report;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class JasperSoftUtil {

	private static final Logger logger = LoggerFactory.getLogger(JasperSoftUtil.class);
	
	@Autowired
	private DataSource dataSource;
	
	public byte[] gerarRelatorio(String nomeRelatorio) {
		logger.trace("Entrou em gerarRelatorio");
		return gerarRelatorio(nomeRelatorio, null);
	}
	
	public byte[] gerarRelatorio(String nomeRelatorio, Map<String, Object> parametros) {
		logger.trace("Entrou em gerarRelatorio");
		InputStream arquivoJasper = getClass().getResourceAsStream("/relatorios/" + nomeRelatorio);
		try (Connection conexao = dataSource.getConnection()) {
			try {
				JasperPrint jasperPrint = JasperFillManager.fillReport(arquivoJasper, parametros, conexao);
				return JasperExportManager.exportReportToPdf(jasperPrint);
			} catch (JRException e) {
				logger.error("Problemas na geracao do PDF do relatório: " + e);
			}
		} catch (SQLException e) {
			logger.error("Problemas na obtenção de uma conexão com o BD na geração de relatório: " + e);
		}
		return null;
	}
	
	public byte[] gerarRelatorioColecao(String nomeRelatorio, List<?> colecao) {
		logger.trace("Entrou em gerarRelatorioColecao");
		return gerarRelatorioColecao(nomeRelatorio, colecao, null);
	}
	
	public byte[] gerarRelatorioColecao(String nomeRelatorio, List<?> colecao, Map<String, Object> parametros) {
		logger.trace("Entrou em gerarRelatorioColecao");

		try {
			ClassPathResource cpr = new ClassPathResource("relatorios/" + nomeRelatorio);
			InputStream arquivoJasper = cpr.getInputStream();
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(colecao);
			JasperPrint jasperPrint = JasperFillManager.fillReport(arquivoJasper, parametros, ds);
			logger.trace("Retornando o relatório gerado");
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			logger.error("Problemas na geracao do PDF do relatório: " + e);
		} catch (IOException e) {
			logger.error("Problema obtendo o diretório dos relatórios: " + e);
		}
		return null;
	}
	
	
}
