package it.prova.myebay.dao.categoria;

import java.util.Optional;

import it.prova.myebay.dao.IBaseDAO;
import it.prova.myebay.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria> {
	public Optional<Categoria> findOneEager(Long id) throws Exception;
}
