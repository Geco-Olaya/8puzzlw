package v0;

/*************************************************************************
 *  Compilacion:  javac Stack.java
 *  Ejecucion:    java Stack < input.txt
 *
 *  Una pila generica, implementada usando Lista Enlazadas. Cada elemento
 *  de la pila es del tipo Item.
 *  
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *  La clase <tt>Stack</tt> representa una pila last-in-first-out (LIFO) de elementos genericos. 
 *  Soporta las operaciones normales <em>push</em> y <em>pop</em>, junto con metodos
 *  para revisar el elemento superior, probar si la pila esta vacia e iterar a traves
 *  de sus elementos en orden LIFO.
 *  <p>
 *  Todas las operaciones de la pila excepto iteraciones son de tiempo constante.
 */
public class Stack<Item> implements Iterable<Item> 
{
	private int N;          // tamaño de la pila
	private Node first;     // superior en la pila

	// clase de ayuda de listas enlazadas
	private class Node 
	{
		private Item item;
		private Node next;
	}

	/**
	 * Crea una lista vacia
	 */
	public Stack() 
	{
		first = null;
		N = 0;
		assert check();
	}

	/**
	 * esta vacia la pila?
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Retorna el numero de items es la pila
	 */
	public int size() {
		return N;
	}

	/**
	 * Agreaga el item a la pila
	 */
	public void push(Item item) 
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
		assert check();
	}

	/**
	 * Borra y retorna el elemento mas recientemente adicionado a la pila.
	 * Lanza una excepcion si no existe elementos porque la pila esta vacia.
	 */
	public Item pop() 
	{
		if (isEmpty()) throw new RuntimeException("Desbordamiento de Pila");
		Item item = first.item;        // graba elemento a retornar
		first = first.next;            // borra el primer nodo
		N--;
		assert check();
		return item;                   // retorna elemento grabado
    }

	/**
	 * Retorna el elemento mas recientemente adicionado a la pila.
	 * Lanza una excepcion si no existe elementos porque la pila esta vacia.
	 */
	public Item peek() 
	{
		if (isEmpty()) throw new RuntimeException("Desbordamiento de Pila");
		return first.item;
	}

	/**
	 * Retorna representacion de cadena
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	// revisa invarianza interna
	private boolean check() 
	{
		if (N == 0) {
			if (first != null) return false;
		}
		else if (N == 1) {
			if (first == null)      return false;
			if (first.next != null) return false;
		}
		else {
			if (first.next == null) return false;
		}

		// revisa consistencia interna de variable de instancia N
		int numberOfNodes = 0;
		for (Node x = first; x != null; x = x.next) {
			numberOfNodes++;
		}
		if (numberOfNodes != N) return false;

		return true;
	} 

	/**
	 * Retorna un iterador a la pila que itera a traves de los elementos en orden LIFO.
	 */
	public Iterator<Item> iterator()  { return new ListIterator();  }

	// un iterador, no se implementa remove() porque es opcional 
	private class ListIterator implements Iterator<Item> 
	{
		private Node current = first;
		public boolean hasNext()  { return current != null;                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public Item next() 
		{
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next; 
			return item;
		}
	}
}