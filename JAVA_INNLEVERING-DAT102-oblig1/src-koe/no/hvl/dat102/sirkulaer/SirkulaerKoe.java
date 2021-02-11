/**
 * 
 */
package no.hvl.dat102.sirkulaer;

import no.hvl.dat102.adt.KoeADT;
import no.hvl.dat102.exception.EmptyCollectionException;

/**
 * @author Ole Olsen
 * @param <T>
 * 
 */
public class SirkulaerKoe<T> implements KoeADT<T> {

	private final static int STDK = 100;
	private int foran, bak, antall;
	private T[] koe;

	public SirkulaerKoe() {
		this(STDK);
	}
	
	@SuppressWarnings("unchecked")
	public SirkulaerKoe(int startKapasitet) {
		foran = bak = antall = 0;
		koe = ((T[]) (new Object[startKapasitet]));
	}

	@Override
	public void innKoe(T element) {
		if (antall() == koe.length) {
			utvid();
		}

		koe[bak] = element;
		bak = (bak + 1) % koe.length;
		antall++;

	}

	@Override
	public T utKoe() {
		if (erTom())
			throw new EmptyCollectionException("Tom koe.");

		T ret = koe[foran];
		koe[foran] = null;
		foran = (foran + 1) % koe.length;
		antall--;

		return ret;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("Tom koe.");

		return koe[foran];
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public int antall() {
		return antall;
	}

	private void utvid() {
		@SuppressWarnings("unchecked")
		T[] hjelpetabell = (T[]) (new Object[koe.length * 2]);
		for (int i = 0; i < antall; i++) {
			hjelpetabell[i] = koe[foran];
			foran = (foran + 1)%koe.length;
		}
		foran = 0;
		bak = antall;
		koe = hjelpetabell;
	}
}
