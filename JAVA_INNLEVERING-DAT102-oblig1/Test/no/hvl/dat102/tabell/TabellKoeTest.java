package no.hvl.dat102.tabell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import no.dat102.tabell.TabellKoe;
import no.hvl.dat102.adt.KoeADT;
import no.hvl.dat102.adt.KoeADTTest;

public class TabellKoeTest extends KoeADTTest {
	
	@Override
	protected KoeADT<Integer> reset() {
		return new TabellKoe<>();
	}
	
	private KoeADT<Integer> reset(int length) {
		return new TabellKoe<>(length);
	}
	
	/**
	 * Test toString
	 */
	@Test
	public void testToString() {
		koe = reset();	//@BeforeEach fungerer ikke?
		koe.innKoe(e0);
		koe.innKoe(e1);
		assertEquals("feil string match e0,e1", e0 + "\n" + e1 + "\n", koe.toString());
		koe.innKoe(e2);
		assertEquals("feil string match e0,e1,e2", e0 + "\n" + e1 + "\n" + e2 + "\n", koe.toString());
		koe.utKoe();
		assertEquals("feil string match e1,e2", e1 + "\n" + e2 + "\n", koe.toString());
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
		
		for (int i = 0; i < 100; ++i) assertEquals("loop utvid fail utKoe != e0", e0, koe.utKoe());
		assertEquals("utvid fail utKoe != e1", e1, koe.utKoe());
		assertEquals("utvid fail utKoe != e2", e2, koe.utKoe());
		assertEquals("utvid fail utKoe != e3", e3, koe.utKoe());
	}

}
