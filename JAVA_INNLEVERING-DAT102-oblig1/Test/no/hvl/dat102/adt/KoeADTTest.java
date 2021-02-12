package no.hvl.dat102.adt;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.exception.EmptyCollectionException;

/**
 * Test for StabelADT.
 * 
 * @author Kjetil, Lars, Lima
 */
public abstract class KoeADTTest {

	// Referanse til koe
	protected KoeADT<Integer> koe;

	// Testdata
	protected final Integer e0 = 1;
	protected final Integer e1 = 2;
	protected final Integer e2 = 3;
	protected final Integer e3 = 4;
	protected final Integer e4 = 5;

	protected abstract KoeADT<Integer> reset();

	/**
	 * Hent en ny koe for hver test.
	 * 
	 * @return
	 */
	@BeforeEach
	public void setup() {
		koe = reset();
	}

	/**
	 * Test at en ny koe er tom.
	 */
	@Test
	public void nyKoeErTom() {
		assertTrue(koe.erTom());
	}
	
	/**
	 * Test at en fylt koe ikke er tom.
	 */
	@Test
	public void fyltKoeErIkkeTom() {
		koe.innKoe(e0);
		assertFalse(koe.erTom());
	}
	
	/**
	 * Test at en koe inn ut er tom.
	 */
	@Test
	public void innUtKoeErTom() {
		koe.innKoe(e0);
		koe.utKoe();
		assertTrue(koe.erTom());
	}
	
	/**
	 * Test innKoe og utKoe
	 */
	@Test
	public void testInnUt() {
		koe.innKoe(e0);
		koe.innKoe(e1);
		koe.innKoe(e2);
		koe.innKoe(e3);
		koe.innKoe(e4);
		assertEquals("inn/ut mismatch e0", e0, koe.utKoe());
		assertEquals("inn/ut mismatch e1", e1, koe.utKoe());
		assertEquals("inn/ut mismatch e2", e2, koe.utKoe());
		assertEquals("inn/ut mismatch e3", e3, koe.utKoe());
		assertEquals("inn/ut mismatch e4", e4, koe.utKoe());
	}
	
	/**
	 * Test innKoe og utKoe med duplikater
	 */
	@Test
	public void testInnUtMedDuplikater() {
		koe.innKoe(e0);
		koe.innKoe(e1);
		koe.innKoe(e2);
		koe.innKoe(e2);
		koe.innKoe(e2);
		assertEquals("inn/ut mismatch e0", e0, koe.utKoe());
		assertEquals("inn/ut mismatch e1", e1, koe.utKoe());
		assertEquals("inn/ut mismatch e2 (1)", e2, koe.utKoe());
		assertEquals("inn/ut mismatch e2 (2)", e2, koe.utKoe());
		assertEquals("inn/ut mismatch e2 (3)", e2, koe.utKoe());
	}
	
	/**
	 * Test foreste med innKoe og utKoe
	 */
	@Test
	public void testFoerste() {
		Assertions.assertThrows(EmptyCollectionException.class, () -> {
			koe.foerste();
		});
		
		koe.innKoe(e0);
		koe.innKoe(e0);
		assertEquals("inn/foerste mismatch e0", e0, koe.foerste());
		
		koe.utKoe();
		assertEquals("inn/foerste mismatch e0 (duplicate)", e0, koe.foerste());
		
		koe.innKoe(e1);
		koe.utKoe();
		assertEquals("inn/foerste mismatch e1", e1, koe.foerste());
		
		koe.utKoe();
		koe.innKoe(e2);
		assertEquals("inn/foerste mismatch e2", e2, koe.foerste());
	}
	
	/**
	 * Test at tom koe utKoe gir exception.
	 * 
	 * @throws EmptyCollectionException - forventet exception
	 */
	@Test
	public void testUtKoeUnderlow() {
		Assertions.assertThrows(EmptyCollectionException.class, () -> {
			koe.utKoe();
		});
	}
	
	/**
	 * Test antall med innKoe, utKoe og duplikater
	 */
	@Test
	public void testKoeAntall() {
		assertEquals("antall feil 0 element", 0, koe.antall());
		koe.innKoe(e0);
		assertEquals("antall feil 1 element", 1, koe.antall());
		koe.innKoe(e1);
		assertEquals("antall feil 2 element", 2, koe.antall());
		koe.innKoe(e2);
		assertEquals("antall feil 3 element", 3, koe.antall());
		koe.innKoe(e3);
		assertEquals("antall feil 4 element", 4, koe.antall());
		koe.innKoe(e3);
		assertEquals("antall feil 5 element (duplicate e3)", 5, koe.antall());
		koe.innKoe(e3);
		assertEquals("antall feil 6 element (triplicate e3)", 6, koe.antall());
		
		koe.utKoe();
		assertEquals("antall feil 5 element (utKoe)", 5, koe.antall());
		koe.utKoe();
		assertEquals("antall feil 4 element (utKoe)", 4, koe.antall());
		koe.utKoe();
		assertEquals("antall feil 3 element (utKoe)", 3, koe.antall());
		koe.utKoe();
		assertEquals("antall feil 2 element (utKoe)", 2, koe.antall());
		koe.utKoe();
		assertEquals("antall feil 1 element (utKoe)", 1, koe.antall());
		koe.utKoe();
		assertEquals("antall feil 0 element (utKoe)", 0, koe.antall());
	}

}
