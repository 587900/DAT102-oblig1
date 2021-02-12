package no.hvl.dat102.sirkulaer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import no.hvl.dat102.adt.KoeADT;
import no.hvl.dat102.adt.KoeADTTest;

public class SirkulaerKoeTest extends KoeADTTest {

	@Override
	protected KoeADT<Integer> reset() {
		return new SirkulaerKoe<>();
	}

	private KoeADT<Integer> reset(int length) {
		return new SirkulaerKoe<>(length);
	}
	
	/**
	 * Test utvid
	 */
	@Test
	public void testUtvid() {
		koe = reset(100);	//lengde = 100
		for (int i = 0; i < 100; ++i) koe.innKoe(e0);
		koe.innKoe(e1);
		koe.innKoe(e2);
		koe.innKoe(e3);
		assertEquals("utvid feil størrelse innKoe", 103, koe.antall());
		
		for (int i = 0; i < 100; ++i) assertEquals("loop utvid fail utKoe != e0", e0, koe.utKoe());
		assertEquals("utvid fail utKoe != e1", e1, koe.utKoe());
		assertEquals("utvid fail utKoe != e2", e2, koe.utKoe());
		assertEquals("utvid fail utKoe != e3", e3, koe.utKoe());
		assertEquals("utvid feil størrelse utKoe", 0, koe.antall());
		assertTrue("utvid feil erTom", koe.erTom());
	}
	
}
