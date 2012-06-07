package v0;

/*************************************************************************
 *  Compilacion:  javac MinPQ.java
 *  Ejecucion:    java MinPQ < input.txt
 *
 *  Implementacion generica de una cola de prioridad min con un monticulo
 *  binario. Se puede usar un comparator en vez del orden natural.
 *
 *  % java MinPQ < tinyPQ.txt
 *  E A E (6 left on pq)
 *
 *  Se usa un arreglo para simplificar calculos de padre e hijos.
 *
 *************************************************************************/

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  La clase <tt>MinPQ</tt> representa una cola de prioridad de llaves genéricas.
 *  Soporta las operaciones usuales <em>insertar</em> y <em>borrar-el-mínimo</em>,
 *  junto con métodos para revisar la llave máxima, probar si la cola
 *  de prioridad está vacia e iterar a través de las llaves.
 *  <p>
 *  Las operaciones <em>insertar</em> y <em>borrar-el-mínimo</em> toman un
 *  tiempo de amortización logarítmica.
 *  Las operaciones <em>min</em>, <em>size</em>, y <em>is-empty</em> toman
 *  un tiempo constante.
 *  La construcción toma un tiempo proporcional a la capacidad específica o
 *  el número de elementos usados para inicializar la estructura de datos.
 *  <p>
 *  Esta implementación usa un montículo binario.
 *  <p>
 */
public class MinPQ<Key> implements Iterable<Key> 
{
	private Key[] pq;				// Guarda items en la posicion 1 a N
	private int N;					// Numero de items en la cola de prioridad
	@SuppressWarnings("rawtypes")
	private Comparator comparator;	// comparador opcional

	/**
	 * Crea una cola de prioridad vacia con la capacidad inicial dada.
	 */
	@SuppressWarnings("unchecked")
	public MinPQ(int initCapacity) 
	{
		pq = (Key[]) new Object[initCapacity + 1];
		N = 0;
	}

	/**
	 * Crea una cola de prioridad vacia.
	 */
	public MinPQ() { this(1); }

	/**
	 * Crea una cola de prioridad vacia con la capacidad inicial dada,
	 * usando el comparador dado.
	 */
	@SuppressWarnings("unchecked")
	public MinPQ(int initCapacity, Comparator<Key> comparator) 
	{
		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		N = 0;
	}

	/**
	 * Crea una cola de prioridad vacia con el comparador dado.
	 */
	public MinPQ(Comparator<Key> comparator) { this(1, comparator); }

	/**
	 * Crea una cola de prioridad con los elementos dados.
	 * Toma un tiempo proporcional al numero de elementos usando el constructor
	 * del montículo sink.
	 */
	@SuppressWarnings("unchecked")
	public MinPQ(Key[] keys) 
	{
		N = keys.length;
		pq = (Key[]) new Object[keys.length + 1];
		for (int i = 0; i < N; i++)
			pq[i+1] = keys[i];
		for (int k = N/2; k >= 1; k--)
			sink(k);
		assert isMinHeap();
	}

	/**
	 * Esta la cola de prioridad vacía?
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * Retorna el número de elementos de la cola de prioridad.
	 */
	public int size() {
		return N;
	}

	/**
	 * Retorna la llave mas pequeña en la cola de prioridad.
	 * Lanza una excepción si no existe tal llave porque la cola de prioridad esta vacia.
     */
	public Key min() 
	{
		if (isEmpty()) throw new RuntimeException("Desbordamiento Cola de priodad");
		return pq[1];
	}

	// funcion de ayuda para doblar el tamaño del arreglo del montículo
	@SuppressWarnings("unchecked")
	private void resize(int capacity) 
	{
		assert capacity > N;
		Key[] temp = (Key[]) new Object[capacity];
		for (int i = 1; i <= N; i++) temp[i] = pq[i];
		pq = temp;
	}

	/**
	 * Adiciona una nueva llave a la cola de prioridad.
	 */
	public void insert(Key x) 
	{
		// double size of array if necessary
		if (N == pq.length - 1) resize(2 * pq.length);

		// agrega x, y lo filtra hacia arriba para mantener la cola invariente 
		pq[++N] = x;
		swim(N);
		assert isMinHeap();
	}

	/**
	 * Borra y retorna la llave mas pequeña en la cola de prioridad.
	 * Lanza una excepción si tal llave no existe debido a que la cola esta vacia.
	 */
	public Key delMin() 
	{
		if (N == 0) throw new RuntimeException("Desbordamiento Cola de prioridad");
		exch(1, N);
		Key min = pq[N--];
		sink(1);
		pq[N+1] = null;			// evita merodeo y ayuda con la recoleccion de basura
		if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length  / 2);
		assert isMinHeap();
		return min;
	}	

	/***********************************************************************
	 * Funciones de ayuda para retaurar la invariancia del monticulo
	 **********************************************************************/
	private void swim(int k) 
	{
		while (k > 1 && greater(k/2, k)) {
			exch(k, k/2);
			k /= 2;
		}
	}

	private void sink(int k) 
	{
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && greater(j, j+1)) j++;
			if (!greater(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	/***********************************************************************
	 * Funciones de ayuda para conparar e intercambiar.
	 **********************************************************************/
	@SuppressWarnings("unchecked")
	private boolean greater(int i, int j) 
	{
		if (comparator == null) {
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) > 0;
		}
	}

	private void exch(int i, int j) 
	{
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	// es pq[1..N] un monticulo min?
	private boolean isMinHeap() {
		return isMinHeap(1);
	}

	// es el subarbol pq[1..N] enraizado a k un monticulo min?
	private boolean isMinHeap(int k) 
	{
		if (k > N) return true;
		int left = 2*k, right = 2*k + 1;
		if (left  <= N && greater(k, left))  return false;
		if (right <= N && greater(k, right)) return false;
		return isMinHeap(left) && isMinHeap(right);
	}

	/***********************************************************************
	 * Iteradores
	 **********************************************************************/

	/**
	 * Retorna un iterador que itera sobre todas las llaves de la cola de prioridad
	 * en orden ascendente.
	 * <p>
	 * El iterador no implementa <tt>remove()</tt> ya que es opcional.
	 */
    @Override
	public Iterator<Key> iterator() { return new HeapIterator(); }

	private class HeapIterator implements Iterator<Key> 
	{
		// crea una nueva pq
		private MinPQ<Key> copy;

		// adiciona todos los elementos como copia al monticulo
		// toma un tiempo lineal puesto que el monticulo esta en orden y no se mueven llaves
		@SuppressWarnings("unchecked")
		public HeapIterator() 
		{
			if (comparator == null) copy = new MinPQ<Key>(size());
			else                    copy = new MinPQ<Key>(size(), comparator);
			for (int i = 1; i <= N; i++)
				copy.insert(pq[i]);
		}

		public boolean hasNext()  { return !copy.isEmpty();                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public Key next() 
		{
			if (!hasNext()) throw new NoSuchElementException();
			return copy.delMin();
		}
	}	

	/**
	 * Un cliente de pruebas
	 
	public static void main(String[] args) 
	{
		MinPQ<String> pq = new MinPQ<String>();

	}*/
}