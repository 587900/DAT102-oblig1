package no.hvl.dat102;

import no.hvl.dat102.Film.Sjanger;
import no.hvl.dat102.adt.FilmarkivADT;

public class Filmarkiv2 implements FilmarkivADT {
	private int antall;
	private LinearNode<Film> start;

	public Filmarkiv2 () {
		antall = 0;
		start = null;
	}
	
	@Override
	public Film[] getFilmCollection() {
		
		Film[] collection = new Film[antall];
		int index = 0;
		LinearNode<Film> current = start;
		while (current != null) {
			collection[index++] = current.getElement();
			current = current.getNext();
		}
		
		return collection;
	}

	@Override
	public void addFilm(Film film) {
		if (start == null) {
			start = new LinearNode<Film>(film);
			return;
		}
		LinearNode<Film> current = start;
		while (current.getNext() != null) current = current.getNext();
		current.setNext(new LinearNode<Film>(film));
		antall++;
	}

	@Override
	public void deleteFilm(Film film) {
		if (start.getElement().equals(film)) {
			start = start.getNext();
			return;
		}
		LinearNode<Film> current = start;
		while (current.getNext() != null) {
			LinearNode<Film> next = current.getNext();
			if (next.getElement().equals(film)) {
				current.setNext(next.getNext());
				return;
			}
			current = next;
		}	
	}

	@Override
	public Film[] searchFilm(String title) {
		title = title.toUpperCase();
		LinearNode<Film> current = start;
		int count = 0;
		while (current != null) {
			Film film = current.getElement();
			if (film.getTittel().toUpperCase().contains(title)) {
				count++;
			}
			current = current.getNext();
		}
		Film[] ret = new Film[count];
		int index = 0;
		while (current != null) {
			Film film = current.getElement();
			if (film.getTittel().toUpperCase().contains(title)) {
				ret[index++] = film;
			}
			current = current.getNext();
		}
		return ret;
	}

	@Override
	public Film[] searchProd(String prod) {
		prod = prod.toUpperCase();
		LinearNode<Film> current = start;
		int count = 0;
		while (current != null) {
			Film film = current.getElement();
			if (film.getProdusent().toUpperCase().contains(prod)) {
				count++;
			}
			current = current.getNext();
		}
		Film[] ret = new Film[count];
		int index = 0;
		while (current != null) {
			Film film = current.getElement();
			if (film.getProdusent().toUpperCase().contains(prod)) {
				ret[index++] = film;
			}
			current = current.getNext();
		}
		return ret;
	}

	@Override
	public int count() {
		
		return antall;
	}

	@Override
	public int countByGenre(Sjanger sjanger) {
		LinearNode<Film> current = start;
		int count = 0;
		while (current != null) {
			if (current.getElement().getSjanger() == sjanger) {
				count++;
			}
			current = current.getNext();
		}
		return count;
	}

}