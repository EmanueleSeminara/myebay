package it.prova.myebay.service.annuncio;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.dao.annuncio.AnnuncioDAO;
import it.prova.myebay.exceptions.ElementNotFoundException;
import it.prova.myebay.exceptions.InvalidUserException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.web.listener.LocalEntityManagerFactoryListener;

public class AnnuncioServiceImpl implements AnnuncioService {
	private AnnuncioDAO annuncioDAO;

	@Override
	public void setAnnuncioDAO(AnnuncioDAO annuncioDAO) {
		this.annuncioDAO = annuncioDAO;
	}

	@Override
	public List<Annuncio> listAllElements() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Annuncio caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findOne(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Annuncio caricaSingoloElementoEager(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findOneEager(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Annuncio annuncioInstance, String[] categorie) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			annuncioDAO.setEntityManager(entityManager);
			Annuncio annuncioDaModificare = annuncioDAO.findOneEager(annuncioInstance.getId()).get();
			annuncioDaModificare.setTestoAnnuncio(annuncioInstance.getTestoAnnuncio());
			annuncioDaModificare.setPrezzo(annuncioInstance.getPrezzo());
			annuncioDaModificare.getCategorie().clear();

			entityManager.merge(annuncioDaModificare);

			if (categorie != null) {
				for (String categoriaItem : categorie) {
					if (NumberUtils.isCreatable(categoriaItem)) {
						annuncioDaModificare.getCategorie().add(new Categoria(Long.parseLong(categoriaItem)));
					}
				}
			}

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
	public void inserisciNuovo(Annuncio annuncioInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			// grazie al fatto che il regista ha un id viene eseguito il merge
			// automaticamente
			// se quell'id non ha un corrispettivo in tabella viene lanciata una eccezione
			annuncioDAO.insert(annuncioInstance);

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
	public void rimuovi(Long idAnnuncioToRemove) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			annuncioDAO.setEntityManager(entityManager);
			Annuncio annuncioToRemove = annuncioDAO.findOne(idAnnuncioToRemove).orElse(null);
			if (annuncioToRemove == null)
				throw new ElementNotFoundException("Annuncio con id: " + idAnnuncioToRemove + " non trovato.");

			annuncioDAO.delete(annuncioToRemove);
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
	public List<Annuncio> findByExample(Annuncio example) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Annuncio> cercaAnnunciAperti() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findAllOpened();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Annuncio> findByExampleForUser(Annuncio example) throws Exception {
		if (example.getUtenteInserimento() == null && example.getUtenteInserimento().getId() == null) {
			throw new InvalidUserException("L'utente a cui si fa riferimento non esiste");
		}
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findByExampleForUser(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Annuncio> listaAnnunciPerIdUtente(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			annuncioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return annuncioDAO.findAllByUtenteId(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
