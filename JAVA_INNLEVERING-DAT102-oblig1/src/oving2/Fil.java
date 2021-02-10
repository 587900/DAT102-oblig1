package oving2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import oving2.Film.Sjanger;

public class Fil {
	
	// String = String2.replace (input, output)
	// String split regEx
	
	private static String LOCATION = "Filmarkiv.txt";
	
	public static void writeFile (Filmarkiv arkiv) { writeFile(arkiv, LOCATION); }
	
	public static void writeFile (Filmarkiv arkiv, String location) { 
		
		try (PrintWriter writer = new PrintWriter(location)){
			writer.println(arkiv.count());
			for(Film film : arkiv.getFilmCollection()) {
				writer.println(film.serialize());
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Filmarkiv readFile () { return readFile(LOCATION); }
	
	public static Filmarkiv readFile (String location) { 
		
		try (BufferedReader reader = new BufferedReader(new FileReader (location))){
			int antall = Integer.parseInt(reader.readLine());
			Filmarkiv arkiv = new Filmarkiv(antall);
			for(int i = 0; i < antall; i++) {
				arkiv.addFilm(Film.deserialize(reader.readLine()));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return null; }
	
	public static void main(String[] args) {
		Filmarkiv arkiv = new Filmarkiv(2);
		arkiv.addFilm(new Film(0, "Harry", "Potter", 1200, Sjanger.ACTION, "Berg Productions"));
		arkiv.addFilm(new Film(3, "Film2", "Se det, ja", 1349, Sjanger.KOMEDIE, "Birkeland Productions"));
		writeFile(arkiv); //ERROR
	}

	
}
