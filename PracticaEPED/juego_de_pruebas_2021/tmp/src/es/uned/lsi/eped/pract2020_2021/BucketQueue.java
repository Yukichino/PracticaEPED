package es.uned.lsi.eped.pract2020_2021;

import es.uned.lsi.eped.DataStructures.Collection;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.List;

/* Representa una cola con prioridad implementada mediante una de SamePriorityQueue */
public class BucketQueue<E> extends Collection<E> implements PriorityQueueIF<E> {

	// LA DEFINICI�N DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE
	List<SamePriorityQueue<E>> lista = new List<SamePriorityQueue<E>>();
	int posicion = 1;
	int tamano = 0;

	/*
	 * Clase privada que implementa un iterador para la cola con prioridad basada en
	 * secuencia.
	 */
	public class PriorityQueueIterator implements IteratorIF<E> {

		// LA DEFINICIÓN DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE
		IteratorIF<SamePriorityQueue<E>> iterLista;
		IteratorIF<E> iterCola;

		/* Constructor por defecto */
		protected PriorityQueueIterator() {
			// No inicializar iterCola en caso de que no haya ninguna cola

			this.iterLista = lista.iterator();
			if (iterLista.hasNext()) {
				iterCola = iterLista.getNext().iterator();
			} else {
				System.out.println("Lista vacía,  PriorityQueueIterator()");
			}

		}

		/* Devuelve el siguiente elemento de la iteración */
		public E getNext() {

			if (lista.isEmpty()) {
				System.out.println("Lista vacía.");
			} else {
				while (iterCola.hasNext()) {
					E paciente = iterCola.getNext();
					if (!iterCola.hasNext()) {
						if (iterLista.hasNext())
							iterCola = iterLista.getNext().iterator();
					}					
					return paciente;
				}
			}

			return null;
		}

		/* Comprueba si queda algún elemento por iterar */
		public boolean hasNext() {
			boolean comprobado = iterCola.hasNext();
			return comprobado;
		}

		/* Reinicia el iterador a la posición inicial */
		public void reset() {
			iterLista.reset();
		}
	}

	/* OPERACIONES PROPIAS DE ESTA CLASE */

	/* constructor por defecto: crea cola con prioridad vacía */
	BucketQueue() {

	}

	/* OPERACIONES PROPIAS DE LA INTERFAZ PRIORITYQUEUEIF */

	/*
	 * Eso podría funcionar pero es poco eficiente. Tienes que buscar la lista mas
	 * prioritaria cada vez. Lo bueno seria que la lista mas prioritaria siempre
	 * estuviese al principio y no tuvieses que buscarla. En ese caso, este metodo
	 * tendria seria una linea
	 */
	/*
	 * Devuelve el elemento más prioritario de la cola y que llegó en primer lugar
	 * 
	 * @Pre !isEmpty()
	 */
	public E getFirst() {

		IteratorIF<SamePriorityQueue<E>> iterador = lista.iterator();
		int prioridad = 0;

		// getFirst()
		while (iterador.hasNext()) {
			int contador = iterador.getNext().getPriority();
			if (prioridad < contador) {
				prioridad = contador;
			}
		}

		return lista.get(prioridad).getFirst();
	}

	/*
	 * Añade un elemento a la cola de acuerdo a su prioridad y su orden de llegada
	 * // Creo que está BIEN
	 */
	public void enqueue(E elem, int prior) {

		// Si no existe una cola con la prioridad que viene por parametro, entonces debo
		// crearla
		IteratorIF<SamePriorityQueue<E>> iterador = lista.iterator();

		// Si la lista esta vacia, crear la primera cola // Comprobar despues si existe
		// una cola de mi prioridad. Y sino crear la cola con esa prioridad

		if (lista.isEmpty()) {
			SamePriorityQueue<E> cola = new SamePriorityQueue<E>(prior);
			cola.enqueue(elem);
			lista.insert(1, cola);
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
				lista.insert(lista.size() + 1, cola2);
			}
		}

	}

	/*
	 * Elimina el elemento más prioritario y que llegó a la cola en primer lugar
	 * 
	 * @Pre !isEmpty()
	 */
	public void dequeue() {

		SamePriorityQueue<E> sacarDeCola;

		// Primero comprobar si la lista está vacía
		if (lista.isEmpty()) {
			System.out.println("Lista vacía");
		} else {
			int posCola = getColaDeMayorPrioridad();
			sacarDeCola = lista.get(posCola);
			sacarDeCola.dequeue();
			int tamCola = sacarDeCola.size();
			if (tamCola < 1) {
				System.out.println("Sacamos de la cola y se queda vacía, procedemos a eliminarla: " + posCola);
				lista.remove(posCola);
			}
		}
	}

	private int getColaDeMayorPrioridad() {

		IteratorIF<SamePriorityQueue<E>> iterador = lista.iterator();
		SamePriorityQueue<E> colaD = null;
		int prioridad = 0;
		int posicionP = 0;
		int posicionCola = 0;

		while (iterador.hasNext()) {
			colaD = iterador.getNext();
			int contador = colaD.getPriority();
			posicionP++;

			if (prioridad < contador) {
				prioridad = contador;
				posicionCola = posicionP;
			}
		}

		System.out.println("Prioridad: " + prioridad + " ,posicionCola: " + posicionCola);

		return posicionCola;
	}

	/* OPERACIONES PROPIAS DE LA INTERFAZ SEQUENCEIF */

	/* Devuelve un iterador para la cola */ // BIEN // llamamos a PriorityQueueIterator
	public IteratorIF<E> iterator() {
		return new PriorityQueueIterator();
	}

	/* OPERACIONES PROPIAS DE LA INTERFAZ COLLECTIONIF */

	/* Devuelve el número de elementos de la cola */ // BIEN (Incluyendo tamano++ en enqueue() y tamano-- en dequeue(),
														// nos evitariamos recorrer la lista con el iterador)
	public int size() {

		// System.out.println("Tamaño(lista): " + lista.size());

		IteratorIF<SamePriorityQueue<E>> iterador = lista.iterator();
		int contador = 0;

		while (iterador.hasNext()) {
			contador += iterador.getNext().size();
		}

		// System.out.println("Tamaño(nº pacientes): " + contador);
		return contador;

	}

	/* Decide si la cola está vacía */ // BIEN
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	/* Decide si la cola contiene el elemento dado por parámetro */ // BIEN
	public boolean contains(E e) {

		IteratorIF<SamePriorityQueue<E>> iterador = lista.iterator();

		while (iterador.hasNext()) {
			if (iterador.getNext().contains(e)) {
				return true;
			}
		}

		return false;

	}

	/* Elimina todos los elementos de la cola */ // BIEN
	public void clear() {
		lista.clear();
	}

}
