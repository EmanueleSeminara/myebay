package it.prova.myebay.service.acquisto;

import java.util.List;

import it.prova.myebay.dao.acquisto.AcquistoDAO;
import it.prova.myebay.model.Acquisto;

public interface AcquistoService {
	public List<Acquisto> listAllElements() throws Exception;

	public Acquisto caricaSingoloElemento(Long id) throws Exception;

	public Acquisto caricaSingoloElementoEager(Long id) throws Exception;

	public void aggiorna(Acquisto acquistoInstance) throws Exception;

	public void inserisciNuovo(Acquisto acquistoInstance) throws Exception;

	// novità rispetto al passato: rimuoviamo per id così faccio tutto nel service e
	// risparimio
	public void rimuovi(Long idAcquistoToRemove) throws Exception;

	public List<Acquisto> findByExample(Acquisto example) throws Exception;

	public List<Acquisto> listaAcquistiPerIdUtente(Long id) throws Exception;

	// per injection
	public void setAcquistoDAO(AcquistoDAO acquistoDAO);
}
