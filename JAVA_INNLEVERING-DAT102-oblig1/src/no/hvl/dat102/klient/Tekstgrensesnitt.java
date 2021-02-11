package no.hvl.dat102.klient;

import java.util.Scanner;

import no.hvl.dat102.Film;
import no.hvl.dat102.Film.Sjanger;
import no.hvl.dat102.adt.FilmarkivADT;

import static java.lang.Integer.*;

public class Tekstgrensesnitt {
	
	public Film lesFilm(Scanner scan, int filmNr) {

		Film inputFilm = new Film();
		String input;

//		while (true) {
//			System.out.println("Skriv filmnummer og trykk Enter:");
//			input = scan.nextLine();
//			if (isNumeric(input)) {
//				inputFilm.setFilmNr(parseInt(input));
//				break;
//			}
//			System.out.println("Feil. Pr�v igjen.");
//		}
		inputFilm.setFilmNr(filmNr);	//for � unng� krasjar i id-systemet

		System.out.println("Skriv tittel og trykk Enter:");
		inputFilm.setTittel(scan.nextLine());

		System.out.println("Skriv produsent og trykk Enter:");
		inputFilm.setProdusent(scan.nextLine());
		
		while (true) {
			System.out.println("Skriv utgivelses�r og trykk Enter:");
			input = scan.nextLine();
			if (isNumeric(input)) {
				inputFilm.setYear(parseInt(input));
				break;
			}
			System.out.println("Feil. Pr�v igjen.");
		}

		while (true) {
			System.out.println("Skriv sjanger og trykk Enter:");
			System.out.println("Du kan velge mellom: " + stringifyArr(Sjanger.values()));
			input = scan.nextLine().toUpperCase();
			try {
				inputFilm.setSjanger(Sjanger.valueOf(input));
				break;
			} catch (Exception e) {
				System.out.println("Feil. Pr�v igjen.");
			}

		}

		System.out.println("Skriv filmselskap og trykk Enter:");
		inputFilm.setFilmSelskap(scan.nextLine());

		return inputFilm;
	}
	
	//Veit ikkje om Arrays.toString(...) er lov, s� vi lagde v�r egen.
	private <T> String stringifyArr(T[] arr) {
		String s = "";
		for (T t : arr) s += ", " + t;
		
		s = s.substring(Math.min(s.length(), 2));
		return "[" + s + "]";
	}

	private boolean isNumeric(String tallTxt) {
		if (tallTxt == null)
			return false;
		try {
			parseInt(tallTxt);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void visFilm(Film film) {
			System.out.println(film.toString() + "\n");
	}
	
	public void printByTitle(FilmarkivADT arkiv, String sokTitle) {
		for (Film film : arkiv.searchFilm(sokTitle)) {
			visFilm(film);
		}
	}

	public void printByProd(FilmarkivADT arkiv, String sokProd) {
		for (Film film : arkiv.searchProd(sokProd)) {
			visFilm(film);
		}
	}

	public void printStats(FilmarkivADT arkiv) {
		System.out.println("Totalt antall: " + arkiv.count());

		for (Sjanger sjanger : Sjanger.values()) {
			int antall = arkiv.countByGenre(sjanger);
			System.out.println(sjanger.toString() + ": " + antall);
		}
	}

}
