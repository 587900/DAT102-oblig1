package no.hvl.dat102.klient;

import java.util.Scanner;

import no.hvl.dat102.Fil;
import no.hvl.dat102.Filmarkiv;
import no.hvl.dat102.adt.FilmarkivADT;

public class Meny {
	
	private static final String FILEEXTENSION = ".kll";	//Kjetil, Lars, Lima :)
	
	private Tekstgrensesnitt tekstgr;
	private FilmarkivADT filma;
	private Scanner tastatur;
	private String filnamn;
	
	public Meny(FilmarkivADT filma) {
		tekstgr = new Tekstgrensesnitt();
		this.filma = filma;
	}
	
	public void setFilmarkiv (FilmarkivADT filma) {
		this.filma = filma;
	}
	
	public void start() {
		try {
			String input;
			tastatur = new Scanner(System.in);
			while (true) {
				System.out.println("Vil du opprette et nytt filmarkiv (n), eller jobbe med eksisterende (e)?");
				input = tastatur.nextLine().toLowerCase();
				if (input.equals("n") || input.equals("e")) break;
			}
			if (input.equals("n")) nyttArkiv();
			else eksisterendeArkiv();
			
			loop();
			tastatur.close();
			save();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CRASH. Lagrer BACKUP-crash-fil");
			crashSave();
		}
	}
	
	private void loop() {
		while (true) {
			System.out.println("Skriv 'lagre' viss du vil lagre filen.");
			System.out.println("Skriv 'sokFilm <søk>' viss du vil søke etter en film");
			System.out.println("Skriv 'sokProd <søk>' viss du vil søke etter en produsent");
			System.out.println("Skriv 'prtStat' viss du vil få ut statistikk om filmer");
			System.out.println("Skriv 'nyFilm' viss du vil legge til en ny film");
			System.out.println("Skriv 'delFilm <nr> viss du vil slette en film");
			System.out.println("Skriv 'exit' viss du vil lukke programmet");
			
			String input = tastatur.nextLine();	//input = "sokFilm HARRY" sokFilmmmmmm f
			String[] split = input.split(" ", 2);
			
			String query = split[0];
			String params = "";
			if (split.length > 1) params = split[1];
			
			switch (query) {
				case "lagre":
					save();
					break;
				case "sokFilm":
					tekstgr.printByTitle(filma, params);
					break;
				case "sokProd":
					tekstgr.printByProd(filma, params);
					break;
				case "prtStat":
					tekstgr.printStats(filma);
					break;
				case "nyFilm":
					filma.addFilm(tekstgr.lesFilm(tastatur, filma.count()));
					save();
					break;
				case "delFilm":
					if (!validInteger(params)) { System.out.println("Ugyldig filmnr: " + params); break; }
					int filmnr = Integer.parseInt(params);
					filma.deleteFilm(filmnr);
					break;
				case "exit":
					return;
				default:
					System.out.println("Ukjent kommando: " + query);
					break;
			}
			System.out.println("Utført kommando: " + query + "\n");
		}
	}
	
	private void nyttArkiv() {
		String input;
		
		while (true) {
			System.out.println("Skriv inn et filnavn: ");
			System.out.println("Desse filene finnes allerede: " + Fil.listFilesFilteredPretty(".", FILEEXTENSION));
			input = tastatur.nextLine();
			
			if (!input.endsWith(FILEEXTENSION)) input += FILEEXTENSION;
			if (!Fil.exists(input)) break;
			
			System.out.println("Filen: " + input + " finnes allerede. Overskrive? (y/n):");
			if (tastatur.nextLine().toLowerCase().equals("y")) break;
		}
		
		filnamn = input;
		filma = new Filmarkiv();	//brukar default størrelse
		Fil.writeFile(filma, input);
		System.out.println("Opprettet filen: " + input);
	}
	
	private void eksisterendeArkiv() {
		String input;
		
		while (true) {
			System.out.println("Skriv inn et filnavn: ");
			System.out.println("Du kan velge mellom blant anna: " + Fil.listFilesFilteredPretty(".", FILEEXTENSION));
			input = tastatur.nextLine();
			
			//her har vi ingen FILEEXTENSION-sjekking
			
			if (Fil.exists(input)) break;
			System.out.println("Den filen (" + input + ") finnes ikke!");
		}
		
		filnamn = input;		
		filma = Fil.readFile(input);
		System.out.println("Lastet inn filen: " + input);
	}
	
	private void save() {
		if (filnamn == null) throw new IllegalStateException("filnamn not set");//{ System.out.println("Kan ikke lagre siden filnamn ikke er satt. Lagring feilet."); return; }		
		Fil.writeFile(filma, filnamn);
	}
	
	private void crashSave() {
		if (filnamn == null) { System.out.println("Unable to crash-save: no filnamn"); return; }
		Fil.writeFile(filma, filnamn + "-BACKUP-crash"); //vurder legg til dato på slutten
	}
	
	private boolean validInteger(String s) {
		try { Integer.parseInt(s); return true; } catch(NumberFormatException e) { return false; }
	}

}
