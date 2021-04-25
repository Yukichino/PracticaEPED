package es.uned.lsi.eped.pract2020_2021;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.QueueIF;

/* Representa una cola con un nivel de prioridad asignado determinado*/
// El funcionamiento de esta estructura es exactamente el mismo que el de una cola normal, de modo que implementa la interfaz QueueIF
public class SamePriorityQueue<E> implements QueueIF<E>, Comparable<SamePriorityQueue<E>> {

	// LA DEFINICI�N DE LOS ATRIBUTOS DE LA CLASE ES TAREA DE CADA ESTUDIANTE

	private int p;
	private QueueIF<E> colaI;
	// private Queue<E> colaI;
	/* OPERACIONES PROPIAS DE ESTA CLASE */

	/*
	 * Constructor por defecto: crea cola vac�a con la prioridad dada por par�metro.
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

	/* A�ade un elemento a la cola de acuerdo al orden de llegada */
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

	/* Devuelve el n�mero de elementos de la cola */
	public int size() {
		return colaI.size();
	}

	/* Decide si la cola est� vac�a */
	public boolean isEmpty() {
		return colaI.isEmpty();
	}

	/* Decide si la cola contiene el elemento dado por par�metro */
	public boolean contains(E e) {
		return colaI.contains(e);
	}

	/* Elimina todos los elementos de la cola */
	public void clear() {
		colaI.clear();
	}

	/* OPERACIONES PROPIAS DEL INTERFAZ COMPARABLE */

	/*
	 * Comparaci�n entre colas seg�n su prioridad 
	 * Salida:
		- Valor > 0 si la cola tiene mayor prioridad que la cola dada por par�metro
	 *  - Valor 0 si ambas colas tienen la misma prioridad
	 *  - Valor < 0 si la cola tiene menor prioridad que la	cola dada por par�metro
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
	 * Por otro lado, estas estructuras pueden compararse entre s� de acuerdo con su
	 * nivel de prioridad, de modo que implementa la interfaz Comparable. Esto
	 * �ltimo implica que esta clase debe implementar un m�todo denominado compareTo
	 * que compara un objeto SamePriorityQueue con otro del mismo tipo. La
	 * descripci�n de las operaciones se encuentra en el esqueleto de esta clase,
	 * adjunto a este enunciado
	 */
}
