package it.prova.myebay.service.annuncio;

import java.util.List;

import it.prova.myebay.dao.annuncio.AnnuncioDAO;
import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {
	public List<Annuncio> listAllElements() throws Exception;

	public Annuncio caricaSingoloElemento(Long id) throws Exception;

	public Annuncio caricaSingoloElementoEager(Long id) throws Exception;

	public void aggiorna(Annuncio annuncioInstance, String[] categorie) throws Exception;

	public void inserisciNuovo(Annuncio annuncioInstance) throws Exception;

	// novità rispetto al passato: rimuoviamo per id così faccio tutto nel service e
	// risparimio
	public void rimuovi(Long idAnnuncioToRemove) throws Exception;

	public List<Annuncio> findByExample(Annuncio example) throws Exception;

	public List<Annuncio> findByExampleForUser(Annuncio example) throws Exception;

	public List<Annuncio> cercaAnnunciAperti() throws Exception;

	public List<Annuncio> listaAnnunciPerIdUtente(Long id) throws Exception;

	// per injection
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO);
}
