package no.hvl.dat102;

public class Film {
	
	private int filmNr;
	private String produsent;
	private String tittel;
	private int year;
	public enum Sjanger {ACTION, KRIM, KOMEDIE};
	private Sjanger sjanger;
	private String filmSelskap;
	
	private static final String SEPARATOR = "# #";
//	private static final String REPLACEMENT = SEPARATOR + SEPARATOR;	//vurderte regex-løysing for å escape i tilfelle tittel inneheld SEPARATOR-teikn, men det blei litt for overkill
//	private static final String REGEX_DESERIALIZE_SPLIT = "/(?<!" + SEPARATOR + ")" + SEPARATOR + "(?!" + SEPARATOR + ")/";
	
	public Film () {}
	
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
		
//		String fTittel = tittel.replace(SEPARATOR, REPLACEMENT);
//		String fProdusent = produsent.replace(SEPARATOR, REPLACEMENT);
//		String fSjanger = sjanger.toString().replace(SEPARATOR, REPLACEMENT);
//		String fFilmSelskap = filmSelskap.replace(SEPARATOR, REPLACEMENT);
//		
//		return filmNr + SEPARATOR + fTittel + SEPARATOR + fProdusent + SEPARATOR + year + SEPARATOR + fSjanger
//			   + SEPARATOR + fFilmSelskap;
		
		return filmNr + SEPARATOR + tittel + SEPARATOR + produsent + SEPARATOR + year + SEPARATOR + sjanger
				   + SEPARATOR + filmSelskap;
	}
	
	public static Film deserialize (String data) {
		
//		String[] info = data.split(REGEX_DESERIALIZE_SPLIT);
//		for (int i = 0; i < info.length; ++i) info[i] = info[i].replace(REPLACEMENT, SEPARATOR);
//		
//		return new Film (Integer.parseInt(info[0]), info[1], info[2],
//				Integer.parseInt(info[3]), Sjanger.valueOf(info[4]), info[5]);
//		
		String[] info = data.split(SEPARATOR);
		return new Film (Integer.parseInt(info[0]), info[1], info[2],
				Integer.parseInt(info[3]), Sjanger.valueOf(info[4]), info[5]);
	}
}




