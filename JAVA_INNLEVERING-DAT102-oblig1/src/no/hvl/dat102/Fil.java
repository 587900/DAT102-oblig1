package no.hvl.dat102;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import no.hvl.dat102.adt.FilmarkivADT;

public class Fil {
	
	private static final String LOCATION = "Filmarkiv.txt";
	
	public static void writeFile (FilmarkivADT arkiv) { writeFile(arkiv, LOCATION); }
	
	public static void writeFile (FilmarkivADT arkiv, String location) { 
		
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
	
	public static FilmarkivADT readFile () { return readFile(LOCATION); }
	
	public static FilmarkivADT readFile (String location) { 
		
		try (BufferedReader reader = new BufferedReader(new FileReader (location))){
			int antall = Integer.parseInt(reader.readLine());
			FilmarkivADT arkiv = new Filmarkiv(antall);
			//FilmarkivADT arkiv = new Filmarkiv2();
			for(int i = 0; i < antall; i++) {
				arkiv.addFilm(Film.deserialize(reader.readLine()));
			}
			return arkiv;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return null; }
	
	/**
	 * Returns null if directory parameter does not denote a directory
	 * Will filter based on file-extension
	 * */
	public static String[] listFiles(String directory) { return listFilesFiltered(directory, ""); }
	public static String[] listFilesFiltered(String directory, String extension) {
		return new File(directory).list((dir, name) -> name.toLowerCase().endsWith(extension));
	}
	
	public static String listFilesPretty(String directory) { return listFilesFilteredPretty(directory, ""); }
	public static String listFilesFilteredPretty(String directory, String extension) {
		String[] files = listFilesFiltered(directory,extension);
		String ret = "";
		for (String f : files) ret += ", " + f;
		ret = ret.substring(Math.min(ret.length(), 2));
		return ret;
	}
	
	public static boolean exists(String file) {
		return new File(file).exists();
	}
	
	//consider placing all files in a folder (ex. named 'database')
	
//	public static void main(String[] args) {
//		Filmarkiv arkiv = new Filmarkiv(2);
//		arkiv.addFilm(new Film(0, "Harry", "Potter", 1200, Sjanger.ACTION, "Berg Productions"));
//		arkiv.addFilm(new Film(3, "Film2", "Se det, ja", 1349, Sjanger.KOMEDIE, "Birkeland Productions"));
//		writeFile(arkiv); //ERROR
//		
//		Filmarkiv arkiv2 = readFile();
//		Tekstgrensesnitt tg = new Tekstgrensesnitt();
//		tg.printStats(arkiv2);
//		for (Film film : arkiv2.getFilmCollection()) System.out.println(film);
//		
//	}
	
//	public static void main(String[] args) {
//		System.out.println(Arrays.toString(listFiles(".")));
//	}

	
}
