package it.prova.myebay.dao.annuncio;

import java.util.List;
import java.util.Optional;

import it.prova.myebay.dao.IBaseDAO;
import it.prova.myebay.model.Annuncio;

public interface AnnuncioDAO extends IBaseDAO<Annuncio> {
	public Optional<Annuncio> findOneEager(Long id) throws Exception;

	public List<Annuncio> findByExample(Annuncio example) throws Exception;
	
	public List<Annuncio> findAllOpened() throws Exception;
}
