package it.prova.myebay.service.categoria;

import java.util.List;

import it.prova.myebay.dao.categoria.CategoriaDAO;
import it.prova.myebay.model.Categoria;

public interface CategoriaService {
	public List<Categoria> listAllElements() throws Exception;

	public Categoria caricaSingoloElemento(Long id) throws Exception;

	public Categoria caricaSingoloElementoEager(Long id) throws Exception;

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	// novità rispetto al passato: rimuoviamo per id così faccio tutto nel service e
	// risparimio
	public void rimuovi(Long idCategoriaToRemove) throws Exception;

	// per injection
	public void setCategoriaDAO(CategoriaDAO categoriaDAO);
}
