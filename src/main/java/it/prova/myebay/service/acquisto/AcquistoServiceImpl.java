package it.prova.myebay.service.acquisto;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.myebay.dao.acquisto.AcquistoDAO;
import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class AcquistoServiceImpl implements AcquistoService {
	private AcquistoDAO acquistoDAO;

	@Override
	public void setAcquistoDAO(AcquistoDAO acquistoDAO) {
		this.acquistoDAO = acquistoDAO;
	}

	@Override
	public List<Acquisto> listAllElements() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Acquisto caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.findOne(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Acquisto caricaSingoloElementoEager(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.findOneEager(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Acquisto acquistoInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			acquistoDAO.setEntityManager(entityManager);

			acquistoDAO.update(acquistoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Acquisto acquistoInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			// grazie al fatto che il regista ha un id viene eseguito il merge
			// automaticamente
			// se quell'id non ha un corrispettivo in tabella viene lanciata una eccezione
			acquistoDAO.insert(acquistoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Long idAcquistoToRemove) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			acquistoDAO.setEntityManager(entityManager);
			Acquisto acquistoToRemove = acquistoDAO.findOne(idAcquistoToRemove).orElse(null);
			if (acquistoToRemove == null)
				throw new ElementNotFoundException("Acquisto con id: " + idAcquistoToRemove + " non trovato.");

			acquistoDAO.delete(acquistoToRemove);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public List<Acquisto> findByExample(Acquisto example) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			acquistoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return acquistoDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
