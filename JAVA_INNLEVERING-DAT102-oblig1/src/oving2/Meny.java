package oving2;

import java.util.Scanner;

public class Meny {
	
	private Tekstgrensesnitt tekstgr;
	private FilmarkivADT filma;
	private Scanner tastatur;
	
	public Meny(FilmarkivADT filma) {
		tekstgr = new Tekstgrensesnitt();
		this.filma = filma;
	}
	
	public void setFilmarkiv (FilmarkivADT filma) {
		this.filma = filma;
	}
	
	public void start() {
		String input;
		tastatur = new Scanner(System.in);
		while (true) {
			System.out.println("Vil du opprette et nytt filmarkiv (n), eller jobbe med eksisterende (e)?");
			input = tastatur.nextLine().toLowerCase();
			if (input == "n" || input == "e") break;
		}
		if (input == "n") nyttArkiv();
		else eksisterendeArkiv();
	}
	
	private void nyttArkiv() {
		System.out.println("Skriv inn et filnavn: ");
		
	}

}
