package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Film.Sjanger;

public interface FilmarkivADT {

	public Film[] getFilmCollection();
	
	public void addFilm(Film film);

	public void deleteFilm(Film film);

	public Film[] searchFilm(String title);

	public Film[] searchProd(String prod);

	public int count();
	
	public int countByGenre (Sjanger sjanger);

}
