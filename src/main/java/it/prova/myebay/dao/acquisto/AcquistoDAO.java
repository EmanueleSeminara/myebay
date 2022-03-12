package it.prova.myebay.dao.acquisto;

import java.util.List;
import java.util.Optional;

import it.prova.myebay.dao.IBaseDAO;
import it.prova.myebay.model.Acquisto;

public interface AcquistoDAO extends IBaseDAO<Acquisto> {
	public List<Acquisto> findByExample(Acquisto example) throws Exception;

	public Optional<Acquisto> findOneEager(Long id) throws Exception;

	public List<Acquisto> listByIdUtente(Long id) throws Exception;
}
