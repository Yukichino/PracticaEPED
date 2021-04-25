package es.uned.lsi.eped.pract2020_2021;

import es.uned.lsi.eped.DataStructures.BSTree;
import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.BSTreeIF.IteratorModes;
import es.uned.lsi.eped.pract2020_2021.BucketQueue.PriorityQueueIterator;

/*Representa una cola con prioridad implementada mediante un Árbol binario de búsqueda de SamePriorityQueue*/
public class BSTPriorityQueue<E> extends Collection<E> implements PriorityQueueIF<E> {

	// LA DEFINICIÃ“N DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE
	BSTree<SamePriorityQueue<E>> arbol = new BSTree<SamePriorityQueue<E>>();

	/*
	 * Clase privada que implementa un iterador para la cola con prioridad basada en
	 * secuencia.
	 */
	public class PriorityQueueIterator implements IteratorIF<E> {

		// LA DEFINICIÓN DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE
		IteratorIF<SamePriorityQueue<E>> iterArbol;
		/* Constructor por defecto */
		protected PriorityQueueIterator() {
		}

		/* Devuelve el siguiente elemento de la iteraciÃ³n */
		public E getNext() {
			return null;
		}

		/* Comprueba si queda algÃºn elemento por iterar */
		public boolean hasNext() {
			return false;
		}

		/* Reinicia el iterador a la posiciÃ³n inicial */
		public void reset() {
			iterArbol.reset();
		}

	}

	/* OPERACIONES PROPIAS DE ESTA CLASE */

	/*
	 * constructor por defecto: crea cola con prioridad vacía
	 */
	BSTPriorityQueue() {
	}

	/* OPERACIONES PROPIAS DE LA INTERFAZ PRIORITYQUEUEIF */

	/*
	 * Devuelve el elemento más prioritario de la cola y que llega en primer lugar
	 * 
	 * @Pre !isEmpty()
	 */
	public E getFirst() {

		if (arbol.isEmpty()) {
			System.out.println("Lista vacía");
		} else {
			SamePriorityQueue<E> sacarDeCola;
			sacarDeCola = getColaDeMayorPrioridad();
			return sacarDeCola.getFirst();
		}

		return null;
	}

	/*
	 * Añade un elemento a la cola de acuerdo a su prioridad y su orden de llegada
	 */
	public void enqueue(E elem, int prior) {

		IteratorIF<SamePriorityQueue<E>> iterador = arbol.iterator(IteratorModes.DIRECTORDER);

		if (arbol.isEmpty()) {
			SamePriorityQueue<E> cola = new SamePriorityQueue<E>(prior);
			cola.enqueue(elem);
			arbol.add(cola);
		} else {
			boolean existe = false;

			while (iterador.hasNext()) {
				SamePriorityQueue<E> colaMismaPrioridad = iterador.getNext();
				int prioridad = colaMismaPrioridad.getPriority();

				if (prioridad == prior) {
					colaMismaPrioridad.enqueue(elem);
					existe = true;
				}
			}

			if (!existe) {
				SamePriorityQueue<E> cola2 = new SamePriorityQueue<E>(prior);
				cola2.enqueue(elem);
				System.out.println("Paciente: " + elem);
				arbol.add(cola2);
			}
		}
	}

	/*
	 * Elimina el elemento más prioritario y que llega a la cola en primer lugar
	 * 
	 * @Pre !isEmpty()
	 */
	public void dequeue() {

		SamePriorityQueue<E> sacarDeCola;

		// Primero comprobar si el árbol está vacío
		if (arbol.isEmpty()) {
			System.out.println("Lista vacía");
		} else {
			sacarDeCola = getColaDeMayorPrioridad();
			System.out.println("Dequeue: " + sacarDeCola.getFirst().toString());
			sacarDeCola.dequeue();

			if (sacarDeCola.size() < 1)
				arbol.remove(sacarDeCola);
		}

	}
	
	/*
	 *  Conseguimos la cola con mayor prioridad
	 */

	// Conseguimos la cola con mayor prioridad del árbol
	private SamePriorityQueue<E> getColaDeMayorPrioridad() {

		IteratorIF<SamePriorityQueue<E>> iterador = arbol.iterator(IteratorModes.DIRECTORDER);
		SamePriorityQueue<E> colaD = null;
		int cont = 0;

		while (iterador.hasNext()) {
			SamePriorityQueue<E> colaNew = iterador.getNext();
			if (cont < 1) {
				cont = 1;
				colaD = colaNew;
			} else {
				if (colaNew.compareTo(colaD) > 0) {
					colaD = colaNew;
				}
			}

		}

		return colaD;
	}
	

	/* OPERACIONES PROPIAS DE LA INTERFAZ SEQUENCEIF */

	/* Devuelve un iterador para la cola */
	public IteratorIF<E> iterator() {
		return new PriorityQueueIterator();
	}
	

	/* OPERACIONES PROPIAS DE LA INTERFAZ COLLECTIONIF */

	/* Devuelve el número de elementos de la cola */
	public int size() {

		IteratorIF<SamePriorityQueue<E>> iterador = arbol.iterator(IteratorModes.DIRECTORDER);
		int contador = 0;

		while (iterador.hasNext()) {
			contador += iterador.getNext().size();
		}

		return contador;
	}

	/* Decide si la cola estÃ¡ vacÃ­a */
	public boolean isEmpty() {
		return arbol.isEmpty();
	}

	/* Decide si la cola contiene el elemento dado por parÃ¡metro */
	public boolean contains(E e) {

		IteratorIF<SamePriorityQueue<E>> iterador = arbol.iterator(IteratorModes.DIRECTORDER);

		while (iterador.hasNext()) {
			if (iterador.getNext().contains(e)) {
				return true;
			}
		}

		return false;

	}

	/* Elimina todos los elementos de la cola */
	public void clear() {
		arbol.clear();
	}

}
