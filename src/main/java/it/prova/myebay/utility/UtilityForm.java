package it.prova.myebay.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;

public class UtilityForm {

	public static Annuncio createAnnuncioFromParams(String testoAnnuncioInputParam, String prezzoInputParam,
			String[] categorieInputParam) {

		Annuncio result = new Annuncio();
//		result.setAperto(apertoStringParam == null ? null : Boolean.parseBoolean(apertoStringParam));
//		result.setData(parseDateFromString(dataInputParam));
		result.setTestoAnnuncio(StringUtils.isBlank(testoAnnuncioInputParam) ? null : testoAnnuncioInputParam);
		result.setPrezzo(NumberUtils.isCreatable(prezzoInputParam) ? Integer.parseInt(prezzoInputParam) : null);

		if (categorieInputParam != null) {
			for (String categoriaItem : categorieInputParam) {
				if (NumberUtils.isCreatable(categoriaItem)) {
					result.getCategorie().add(new Categoria(Long.parseLong(categoriaItem)));
				}
			}
		}
		return result;
	}

	public static boolean validateAnnuncioBean(Annuncio annuncioToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(annuncioToBeValidated.getTestoAnnuncio()) || annuncioToBeValidated.getAperto() == null
				|| annuncioToBeValidated.getPrezzo() == null || annuncioToBeValidated.getPrezzo() < 0
				|| annuncioToBeValidated.getData() == null) {

			return false;
		}
		return true;
	}

	public static Acquisto createAcquistoFromParams(String descrizioneInputParam, String prezzoInputParam,
			String dataInputParam) {

		Acquisto result = new Acquisto();
		result.setData(parseDateFromString(dataInputParam));
		result.setDescrizione(StringUtils.isBlank(descrizioneInputParam) ? null : descrizioneInputParam);
		result.setPrezzo(NumberUtils.isCreatable(prezzoInputParam) ? Integer.parseInt(prezzoInputParam) : null);
		return result;
	}

	public static boolean validateAcquistoBean(Acquisto acquistoToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(acquistoToBeValidated.getDescrizione()) || acquistoToBeValidated.getData() == null
				|| acquistoToBeValidated.getPrezzo() == null || acquistoToBeValidated.getPrezzo() < 0) {
			return false;
		}
		return true;
	}

	public static Date parseDateFromString(String dataStringParam) {
		if (StringUtils.isBlank(dataStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Utente createUtenteFromParams(String nomeInputParam, String cognomeInputParam,
			String usernameInputParam, String passwordInputParam) {

		Utente result = new Utente();
		result.setNome(StringUtils.isBlank(nomeInputParam) ? null : nomeInputParam);
		result.setCognome(StringUtils.isBlank(cognomeInputParam) ? null : cognomeInputParam);
		result.setUsername(StringUtils.isBlank(usernameInputParam) ? null : usernameInputParam);
		result.setPassword(StringUtils.isBlank(passwordInputParam) ? null : passwordInputParam);

		return result;
	}

	public static Utente createUtenteFromParamsForSearch(String nomeInputParam, String cognomeInputParam,
			String usernameInputParam, String passwordInputParam, String[] ruoliInputParam) {

		Utente result = new Utente();
		result.setNome(StringUtils.isBlank(nomeInputParam) ? null : nomeInputParam);
		result.setCognome(StringUtils.isBlank(cognomeInputParam) ? null : cognomeInputParam);
		result.setUsername(StringUtils.isBlank(usernameInputParam) ? null : usernameInputParam);
		result.setPassword(StringUtils.isBlank(passwordInputParam) ? null : passwordInputParam);

		if (ruoliInputParam != null) {
			for (String ruoloItem : ruoliInputParam) {
				if (NumberUtils.isCreatable(ruoloItem)) {
					result.getRuoli().add(new Ruolo(Long.parseLong(ruoloItem)));
				}
			}
		}

		return result;
	}

	public static boolean validateUtenteBean(Utente utenteToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(utenteToBeValidated.getNome()) || StringUtils.isBlank(utenteToBeValidated.getCognome())
				|| StringUtils.isBlank(utenteToBeValidated.getUsername())
				|| StringUtils.isBlank(utenteToBeValidated.getPassword())) {
			return false;
		}
		return true;
	}

	public static Map<Ruolo, Boolean> buildCheckedRolesFromRolesAlreadyInUtente(List<Ruolo> listaTotaleRuoli,
			Set<Ruolo> listaRuoliPossedutiDaUtente) {
		Map<Ruolo, Boolean> result = new TreeMap<>();

		// converto array di ruoli in List di Long
		List<Long> ruoliConvertitiInIds = new ArrayList<>();
		for (Ruolo ruoloDiUtenteItem : listaRuoliPossedutiDaUtente != null ? listaRuoliPossedutiDaUtente
				: new ArrayList<Ruolo>()) {
			ruoliConvertitiInIds.add(ruoloDiUtenteItem.getId());
		}
		for (Ruolo ruoloItem : listaTotaleRuoli) {
			result.put(ruoloItem, ruoliConvertitiInIds.contains(ruoloItem.getId()));
		}

		return result;
	}

}
