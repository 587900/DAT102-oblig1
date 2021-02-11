package no.hvl.dat102;

//Representerer en node.
class LinearNode<T> {
	private LinearNode<T> next;
	private T element;

	/**
	 * Oppretter en tom node
	 */
	public LinearNode() {
		next = null;
		element = null;
	}

	/**
	 * Oppretter en node med et element.
	 * 
	 * @param elem.
	 */
	public LinearNode(T elem) {
		next = null;
		element = elem;
	}

	/**
	 * Returnerer etterfølger.
	 * 
	 * @return etterfølger.
	 */
	public LinearNode<T> getNext() {
		return next;
	}

	/**
	 * Setter neste til node.
	 * 
	 * @param node.
	 */
	public void setNext(LinearNode<T> node) {
		next = node;
	}

	/**
	 * Returnerer elementet til denne noden
	 * 
	 * @return element.
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Setter nytt element i denne noden.
	 * 
	 * @param elem.
	 */
	public void setElement(T elem) {
		element = elem;
	}

}
