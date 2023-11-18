package br.com.hiago640.splitdumb.model.converter;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.hiago640.splitdumb.model.Grupo;
import br.com.hiago640.splitdumb.repository.GrupoRepository;

@Component
public class StringToGrupoConverter implements Converter<String, Grupo> {

	private static final Logger logger = LoggerFactory.getLogger(StringToGrupoConverter.class);

	@Autowired
	private GrupoRepository grupoRepository;

	public StringToGrupoConverter() {
		logger.info(">>> Criando um StringToGrupoConverter");
	}

	@Override
	public Grupo convert(String from) {
		Long codigo = Long.parseLong(from);
		logger.info(">>> Convertendo o Long: {} em um Grupo", codigo);
		Optional<Grupo> optGrupo = grupoRepository.findById(codigo);
		if (optGrupo.orElse(null) != null)
			logger.info("Achei o cinema {}", optGrupo.orElse(null).getId());
		return optGrupo.orElse(null);
	}
}