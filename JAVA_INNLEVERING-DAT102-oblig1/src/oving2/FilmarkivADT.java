package oving2;

import oving2.Film.Sjanger;

public interface FilmarkivADT {

	public Film[] getFilmCollection();
	
	public void addFilm(Film film);

	public void deleteFilm(Film film);

	public Film[] searchFilm(String title);

	public Film[] searchProd(String prod);

	public int count();
	
	public int countByGenre (Sjanger sjanger);

}
