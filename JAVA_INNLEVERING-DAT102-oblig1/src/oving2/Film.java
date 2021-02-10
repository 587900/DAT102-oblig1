package oving2;

public class Film {
	
	private int filmNr;
	private String produsent;
	private String tittel;
	private int year;
	public enum Sjanger {ACTION, KRIM, KOMEDIE};
	private Sjanger sjanger;
	private String filmSelskap;
	
	public Film () {
		
	}
	
	public Film (int filmNr, String tittel, String produsent, int year, Sjanger sjanger, String filmSelskap) {
		this.filmNr = filmNr;
		this.tittel = tittel;
		this.produsent = produsent;
		this.year = year;
		this.sjanger = sjanger;
		this.filmSelskap = filmSelskap;
	}
	
	public int getFilmNr() {
		return filmNr;
	}
	
	public void setFilmNr(int filmNr) {
		this.filmNr = filmNr;
	}
	
	public String getProdusent() {
		return produsent;
	}
	
	public void setProdusent(String produsent) {
		this.produsent = produsent;
	}
	
	public String getTittel() {
		return tittel;
	}
	
	public void setTittel(String tittel) {
		this.tittel = tittel;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public Sjanger getSjanger () {
		return sjanger;
	}
	
	public void setSjanger (Sjanger sjanger) {
		this.sjanger = sjanger;
	}
	
	public String getFilmSelskap() {
		return filmSelskap;
	}
	
	public void setFilmSelskap(String filmSelskap) {
		this.filmSelskap = filmSelskap;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj == this) return true;
		if (obj == null || !(obj instanceof Film)) return false;
		
		return filmNr == ((Film)obj).getFilmNr();
	}
	
	@Override
	public String toString () {
		return "Filmnummer: " + getFilmNr() + "\nTittel: " + getTittel() + "\nProdusent: " + getProdusent()
			   + "\nUtgivelsesår: " + getYear() + "\nSjanger: " + getSjanger()
			   + "\nFilmselskap: " + getFilmSelskap();
	}
	
	public String serialize () {
		
//		String separator = "#";
//		String replacement = "\\" + separator;
		
//		String fTittel = tittel.replace(separator, replacement);
//		String fProdusent = produsent.replace(separator, replacement);
//		String fSjanger = sjanger.toString().replace(separator, replacement);
//		String fFilmSelskap = filmSelskap.replace(separator, replacement);
		
//		return filmNr + separator + fTittel + separator + fProdusent + separator + år + separator + fSjanger
//			   + separator + fFilmSelskap;
		
		String separator = "\u2654";
		
		return filmNr + separator + tittel + separator + produsent + separator + year + separator + sjanger
				   + separator + filmSelskap;
	}
	
	public static Film deserialize (String data) {
		
		String[] info = data.split("\u2654");
		
		return new Film (Integer.parseInt(info[0]), info[1], info[2],
				Integer.parseInt(info[3]), Sjanger.valueOf(info[4]), info[5]);
	}
}




