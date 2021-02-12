package no.hvl.dat102;

import no.hvl.dat102.Film.Sjanger;
import no.hvl.dat102.adt.FilmarkivADT;

public class Filmarkiv implements FilmarkivADT {

	private Film[] filmtabell;
	private int antall;

	public Filmarkiv(int antall) {
		this.antall = 0;
		filmtabell = new Film[antall];
	}

	public Filmarkiv() {
		this(25);
	}

	@Override
	public Film[] getFilmCollection() {
		Film [] retTab = new Film[antall];
		for(int i = 0; i<antall; i++) {
			retTab[i] = filmtabell[i];
		}
		
		return retTab;
	}
	
	@Override
	public void addFilm(Film film) {
		if (indexOf(film.getFilmNr()) >= 0) return; //Filmen finnes (vurder tillatelse av duplikater. Filmarkiv2 tillater det. Evt. forby det i Filmarkiv2).
	
		if (isFull()) expandBy((int)Math.max(1, filmtabell.length * 0.1));
		filmtabell[antall] = film;
		antall++;
	}

	@Override
	public void deleteFilm(int filmnr) {
		int index = indexOf(filmnr);
		if (index == -1) return;
		antall--;
		filmtabell[index] = filmtabell[antall];
		filmtabell[antall] = null;
	}
	
	@Override
	public Film[] searchFilm(String delTittel) {
		delTittel = delTittel.toUpperCase();
		
		Film[] filmMatches = new Film[antall];
		int filmMatchesCount = 0;
		
		for (int i = 0; i < antall; i++) {
			if (filmtabell[i].getTittel().toUpperCase().contains(delTittel)) filmMatches[filmMatchesCount++] = filmtabell[i];
		}
		
		Film[] trimmed = new Film[filmMatchesCount];
		for (int i = 0; i < trimmed.length; i++) trimmed[i] = filmMatches[i];
		
		return trimmed;
	}

	@Override
	public Film[] searchProd(String delProd) {
		delProd = delProd.toUpperCase();
		
		Film[] filmMatches = new Film[antall];
		int filmMatchesCount = 0;
		
		for (int i = 0; i < antall; i++) {
			if (filmtabell[i].getProdusent().toUpperCase().contains(delProd)) filmMatches[filmMatchesCount++] = filmtabell[i];
		}
		
		Film[] trimmed = new Film[filmMatchesCount];
		for (int i = 0; i < trimmed.length; i++) trimmed[i] = filmMatches[i];
		
		return trimmed;
		
	}

	@Override
	public int count() {
		
		return antall;
	}
	
	@Override
	public int countByGenre (Sjanger sjanger) {
		
		int antMatch = 0;
		
		for (int i = 0; i < antall; i++) {
			if (sjanger == filmtabell[i].getSjanger()) antMatch ++;
		}
		
		return antMatch;
	}

	public void expandTo(int target) {

		if (target <= filmtabell.length) return;

		Film[] hjelpetabell = new Film[target];

		for (int i = 0; i < filmtabell.length; i++) {
			hjelpetabell[i] = filmtabell[i];
		}
		filmtabell = hjelpetabell;

	}
	
	public void expandBy(int antall) {
		expandTo(filmtabell.length + antall);
	}
	
	public boolean isFull() {
		return (antall == filmtabell.length);
	}
	
	public int indexOf(int filmnr) {
		for(int i = 0; i < antall; i++) {
			if (filmtabell[i].getFilmNr() == filmnr) return i;
		}
		return -1;
	}

}