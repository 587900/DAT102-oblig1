package oving2;

import java.util.Scanner;
import static java.lang.Integer.*;

import oving2.Film.Sjanger;

public class Tekstgrensesnitt {

	//Vurder scanner wrapper
	
	public Film lesFilm() {

		Film inputFilm = new Film();
		Scanner scan = new Scanner(System.in);
		String input;

		while (true) {
			System.out.println("Skriv filmnummer og trykk Enter:");
			input = scan.next();
			if (isNumeric(input)) {
				inputFilm.setFilmNr(parseInt(input));
				break;
			}
			System.out.println("Feil. Prøv igjen.");
		}

		System.out.println("Skriv tittel og trykk Enter:");
		inputFilm.setProdusent(scan.next());

		System.out.println("Skriv produsent og trykk Enter:");
		inputFilm.setProdusent(scan.next());
		
		while (true) {
			System.out.println("Skriv utgivelsesår og trykk Enter:");
			input = scan.next();
			if (isNumeric(input)) {
				inputFilm.setYear(parseInt(input));
				break;
			}
			System.out.println("Feil. Prøv igjen.");
		}

		while (true) {
			System.out.println("Skriv sjanger og trykk Enter:");
			try {
				inputFilm.setSjanger(Sjanger.valueOf(input));
				break;
			} catch (Exception e) {
				System.out.println("Feil. Prøv igjen.");
			}

		}

		System.out.println("Skriv filmselskap og trykk Enter:");
		inputFilm.setFilmSelskap(scan.next());

		scan.close();

		return inputFilm;
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
			System.out.println(film.toString());
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
