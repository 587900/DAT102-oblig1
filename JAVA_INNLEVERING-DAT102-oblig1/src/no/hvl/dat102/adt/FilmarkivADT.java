package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Film.Sjanger;

/**
 * Et interface som definerer et filmarkiv.
 * 
 * Filmarkivet skal ha plass til uendelig mange filmer (virtuelt sett).
 *
 */

public interface FilmarkivADT {

	/**
	 * Henter en tabell over alle filmer som er i filmarkivet (ikke null).
	 * 
	 * @return Tabell av filmer i filmarkivet.
	 */
	public Film[] getFilmCollection();

	/**
	 * Legger til filmen du vil legge til i filmarkivet.
	 * 
	 * @param film - filmen du vil legge til.
	 */
	public void addFilm(Film film);

	/**
	 * Sletter en film du ønsker å fjerne fra filmarkivet.
	 * Ved duplikat / flere ved samme filmnummer, slett første førekomst.
	 * 
	 * @param filmnr - filmnummeret til filmen du vil slette.
	 */
	public void deleteFilm(int filmnr);

	/**
	 * SÃ¸ker etter en film med gitt delstreng, og returnerer en tabell med filmer
	 * som inneholder delstrengen i tittelen.
	 * 
	 * @param title - delstrengen man skal sÃ¸ke etter.
	 * @return tabell over filmer som inneholder delstrengen i tittelen.
	 */
	public Film[] searchFilm(String title);

	/**
	 * SÃ¸ker etter en film med gitt delstreng, og returnerer en tabell med filmer
	 * som inneholder delstrengen i produsentnavnet.
	 * 
	 * @param prod - delstrengen man skal sÃ¸ke etter.
	 * @return tabell over filmer som inneholder delstrengen i produsentnavnet.
	 */
	public Film[] searchProd(String prod);

	/**
	 * Henter antall filmer i filmarkivet.
	 * 
	 * @return heltall av antall filmer i filmarkivet.
	 */
	public int count();

	/**
	 * Henter antall filmer av en gitt sjanger i filmarkivet.
	 * 
	 * @param sjanger - sjanger man vil sÃ¸ke etter.
	 * @return heltall av antall filmer av oppgitt sjanger.
	 */
	public int countByGenre(Sjanger sjanger);

}
