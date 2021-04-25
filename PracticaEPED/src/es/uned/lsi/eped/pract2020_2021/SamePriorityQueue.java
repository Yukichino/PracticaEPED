package es.uned.lsi.eped.pract2020_2021;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.QueueIF;

/* Representa una cola con un nivel de prioridad asignado determinado*/
// El funcionamiento de esta estructura es exactamente el mismo que el de una cola normal, de modo que implementa la interfaz QueueIF
public class SamePriorityQueue<E> implements QueueIF<E>, Comparable<SamePriorityQueue<E>> {

	// LA DEFINICIÓN DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE

	private int p;
	private QueueIF<E> colaI;
	// private Queue<E> colaI;
	/* OPERACIONES PROPIAS DE ESTA CLASE */

	/*
	 * Constructor por defecto: crea cola vacía con la prioridad dada por parámetro.
	 * 
	 * @param p: nivel de prioridad asociado a la cola
	 */
	public SamePriorityQueue(int p) {
		this.p = p;
		colaI = new Queue<E>();
	}

	/*
	 * Devuelve la prioridad de la cola
	 * 
	 * @return prioridad de la cola
	 */
	public int getPriority() {
		return this.p;
	}

	/* OPERACIONES PROPIAS DE QUEUEIF */

	/*
	 * Devuelve el primer elemento de la cola
	 * 
	 * @Pre !isEmpty()
	 */
	public E getFirst() {
		return colaI.getFirst();
	}

	/* Añade un elemento a la cola de acuerdo al orden de llegada */
	public void enqueue(E elem) {
		colaI.enqueue(elem);
	}

	/*
	 * Elimina un elemento a la cola de acuerdo al orden de llegada
	 * 
	 * @Pre !isEmpty()
	 */
	public void dequeue() {
		colaI.dequeue();
	}

	/* OPERACIONES PROPIAS DEL INTERFAZ SEQUENCEIF */

	/* Devuelve un iterador para la cola */
	public IteratorIF<E> iterator() {
		return colaI.iterator();
	}

	/* OPERACIONES PROPIAS DEL INTERFAZ COLLECTIONIF */

	/* Devuelve el número de elementos de la cola */
	public int size() {
		return colaI.size();
	}

	/* Decide si la cola está vacía */
	public boolean isEmpty() {
		return colaI.isEmpty();
	}

	/* Decide si la cola contiene el elemento dado por parámetro */
	public boolean contains(E e) {
		return colaI.contains(e);
	}

	/* Elimina todos los elementos de la cola */
	public void clear() {
		colaI.clear();
	}

	/* OPERACIONES PROPIAS DEL INTERFAZ COMPARABLE */

	/*
	 * Comparación entre colas según su prioridad 
	 * Salida:
		- Valor > 0 si la cola tiene mayor prioridad que la cola dada por parámetro
	 *  - Valor 0 si ambas colas tienen la misma prioridad
	 *  - Valor < 0 si la cola tiene menor prioridad que la	cola dada por parámetro
	 */
	public int compareTo(SamePriorityQueue<E> o) {
		// return p - o.p; Devuelve la diferencia entre las colas
		if (this.p == o.p)
			return 0;
		else if (this.p > o.p)
			return 1;
		else
			return -1;
	}

	/*
	 * Por otro lado, estas estructuras pueden compararse entre sí de acuerdo con su
	 * nivel de prioridad, de modo que implementa la interfaz Comparable. Esto
	 * último implica que esta clase debe implementar un método denominado compareTo
	 * que compara un objeto SamePriorityQueue con otro del mismo tipo. La
	 * descripción de las operaciones se encuentra en el esqueleto de esta clase,
	 * adjunto a este enunciado
	 */
}
