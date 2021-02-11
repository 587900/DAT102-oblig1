package no.hvl.dat102.sirkulaer;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import no.hvl.dat102.adt.KoeADT;

public class TestSirkulaerKoe {
	
	@Test
	public void bananas() {
		KoeADT<Integer> koe = new SirkulaerKoe<>();
		koe.innKoe(50);
		assertEquals("Bliasd", (Integer) 50, koe.utKoe());
	}

}
