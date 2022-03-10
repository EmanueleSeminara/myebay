package it.prova.myebay.dao.categoria;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.myebay.model.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Categoria> list() throws Exception {
		return entityManager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Optional<Categoria> findOne(Long id) throws Exception {
		Categoria result = entityManager.find(Categoria.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		categoriaInstance = entityManager.merge(categoriaInstance);

	}

	@Override
	public void insert(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(categoriaInstance);

	}

	@Override
	public void delete(Categoria categoriaInstance) throws Exception {
		if (categoriaInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(categoriaInstance));

	}

	@Override
	public Optional<Categoria> findOneEager(Long id) throws Exception {
		return entityManager
				.createQuery(
						"from Categoria c left join fetch c.annunci where c.id=:idCategoria", Categoria.class)
				.setParameter("idCategoria", id).getResultList().stream().findFirst();
	}

}
