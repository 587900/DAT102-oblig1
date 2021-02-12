package no.hvl.dat102.kjedet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import no.hvl.dat102.adt.KoeADT;
import no.hvl.dat102.adt.KoeADTTest;

public class KjedetKoeTest extends KoeADTTest {
	
	@Override
	protected KoeADT<Integer> reset() {
		return new KjedetKoe<>();
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
	
}
