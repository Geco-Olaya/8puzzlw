package v0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings("unused")
public class Board implements Comparable<Board>{

	/************************************************************************
	 * ATRIBUTOS DEL TABLERO
	 ************************************************************************/
	Cuadro[][] tiles;
	// int[][] meta;//Cada objeto tablero contara con un tablero solucionado
	// para realizar operaciones
	int size;
	int num_movimientos;
	int hamming = 0;// Solo usaremos para esta version la prioridad Manhattan;
	int manhattan;
	Board ant;

	/************************************************************************
	 * ATRIBUTOS DEL TABLERO
	 ************************************************************************/

	/***************************************************************************
	 * Este es el constructor del objeto board.
	 * 
	 * @param tiles
	 * @param num_movimiento
	 * @param tablero_ant
	 ****************************************************************************/
	public Board(Cuadro[][] tab, int num_movimiento, Board tablero_ant) {
		this.tiles = tab;
		this.size = tiles.length;
		this.num_movimientos = num_movimiento;
		manhattan = manhattan(tab);
		if (num_movimientos != 0) {
			ant = tablero_ant;
		} else {
			ant = null;
		}
		
		
		

	}

	/**************************************************************************
	 * FIN DEL CONSTRUCTOR
	 *************************************************************************/
	/**
	 * Retrona el numero de bloques fuera de lugar.
	 * 
	 * @return
	 * 
	 *         public int hamming() { int prioridadH=0; for(int i = 0; i<size ;
	 *         i++){ for(int j = 0; j<size;j++){ if(!(meta[i][j] == tiles[i][j])
	 *         && tiles[i][j] != 0){ prioridadH++; } } }//Fin recorrido de la
	 *         matriz return prioridadH + num_movimientos; }
	 */

	/**
	 * Retorna la suma de distancia Manhattan entre bloques
	 * @param tab 
	 * 
	 * @return
	 */
	public int manhattan(Cuadro[][] tab) {
		int prioridadM = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				prioridadM += tab[i][j].prioridadM;
			}
		}

		return prioridadM + num_movimientos;
	}

	/**
	 * Observa si el tablero es igual
	 */
	public boolean equals(Object y) {
		int control = 0;
		// Recorro la matriz temporal y la actual para verificar si se trata del
		// mismo tablero
		Board temp = (Board) y;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (temp.tiles[i][j].num == tiles[i][j].num)
					control++;
			}
		}
		// Inicio analisis de la variable control que se modifico mediante el
		// recorrido de las dos matrices
		if (control == size) {
			return true;
		} // Ambos tableros son iguales.
		else {
			return false;
		}// Ambos tableros son diferentes. */
	}

	/**
	 * Retorna un Iterador de todos los vecinos de las posiciones
	 * 
	 * @return
	 */
	public Iterable<Board> neighbors() {
		return new Board_Nei_Iteratorclass();
	}

	private class Board_Nei_Iteratorclass implements Iterator<Board>,
			Iterable<Board> {
		
		LinkedList<Board> vecinos = vecinos(); 
		@Override
		public boolean hasNext() {
			return !(vecinos.isEmpty());
		}

		@Override
		public Board next() {
			return vecinos.pollFirst();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Iterator<Board> iterator() {
			return new Board_Nei_Iteratorclass();
		}

	}

	public String toString() {
		String retorno = null;
		retorno = "Tablero a resolver: \n\n";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j + 1 == size) {
					if(tiles[i][j].num != 0){
						retorno += tiles[i][j] + "\n";
					}else{
						retorno += "   \n";
					}
				} else {
					if(tiles[i][j].num != 0){
					retorno += tiles[i][j] + " | ";
					}
				}

			}
		}// fin ciclo
		retorno+= "Prioridad Manhattan: "+manhattan+"\n\n";
		return retorno;
	}

	/****************************************************************************************
	 * OJO SE DEBE CAMBIAR PARA QUE SE COMPARE POR PRIORIDAD, DE ESTA MANERA
	 * FUNCIONARA LA COLA DE PRIORIDAD.
	 */

	@Override
	public int compareTo(Board temp) {
		if (temp.manhattan > manhattan) {
			return 1;
		}
		if (temp.manhattan < manhattan) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Retorna la posicion de un numero dado dentro del objeto Board
	 * 
	 * @param number
	 * @param board
	 * @return
	 */
	private int[] find(int number) {
		int[] poss = new int[2];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((number == tiles[i][j].num)) {
					poss[0] = i;
					poss[1] = j;
					return poss;
				}
			}
		}// Fin recorrido de la matriz
		poss = null;
		return poss;// Se retorna null en caso de estar buscando un elemento que
					// no esta en la matriz
	}

	/**
	 * Intercambia el valor que hay en la casilla con un cero, por uno de los
	 * vecinos del mismo. El valor manhattan del cuadro que se cambia, se
	 * modifica mediante la funcion swap que tiene el objeto Cuadro.
	 * 
	 * @param newpos-> Debe ser una posicion valida del cero.
	 * @param cambio
	 */
	public Cuadro[][] swap(int[] newpos, Cuadro cambioSinClon, Cuadro[][] vecino) {
		int[] fixpos = new int[2];
		Cuadro cambio =  (Cuadro)cambioSinClon.clone();
		fixpos = find(cambio.num);
		Cuadro aux = (Cuadro)vecino[newpos[0]][newpos[1]].clone();
		vecino[newpos[0]][newpos[1]] = cambio;
		cambio.swap(newpos);
		vecino[fixpos[0]][fixpos[1]] = aux;
		aux.swap(fixpos);
		return vecino;
	}

	/**
	 * Genera una lista enlazada con todos los "tiles" vecinos que puede tener
	 * un objeto del tipo Board.
	 * 
	 * En un principio se inserta el tablero anterior con el objetivo de ir comparando
	 * si la solucion que se obtiene es unica y no se esta regresando a un estado
	 * anterior.
	 * 
	 * @return
	 */
	public LinkedList<Board> vecinos() {
		LinkedList<Cuadro[][]> neig = new LinkedList<Cuadro[][]>();
		LinkedList<Board> Board_neig = new LinkedList<Board>();
		int[] pos0 = new int[2];
		pos0 = find(0);
		if(ant != null){neig.add(ant.tiles);}
		/*
		 * Si la fila se puede incrementar, se mueve el numero debajo del cero
		 * 
		 * 
		 * 
		 * ERROR FATAL
		 * 
		 * INVESTIGAR POR ESTE LADO; LA VARIABLE TILES SIGUE CAMBIANDO A RAIZ
		 * DEL SWAP REALIZADO POR LA CLASE CUADRO
		 */
		if (!(pos0[0] + 1 > (size - 1))) {
			//Con este metodo y la modificacion del metetodo clone
			//se soluciono el problema de clonacion de la revision anterior
			Cuadro[][] temp = (Cuadro[][])tiles.clone();
			for(int i=0;i<tiles.length;i++){
				temp[i]=(Cuadro[])tiles[i].clone();
			}
			Cuadro[][] temp2;
			temp2 = swap(pos0, temp[pos0[0] + 1][pos0[1]], temp);
			if (!(neig.contains(temp2))) {
				neig.addFirst(temp2.clone());
			}
		}

		/*
		 * Si la fila se puede reducir, se mueve el numero encima del cero
		 */
		if (!(pos0[0] - 1 < 0)) {
			Cuadro[][] temp = (Cuadro[][])tiles.clone();
			for(int i=0;i<tiles.length;i++){
				temp[i]=(Cuadro[])tiles[i].clone();
			}
			Cuadro[][] temp2;
			temp2 = swap(pos0, temp[pos0[0] - 1][pos0[1]], temp);
			if (!(neig.contains(temp2))) {
				neig.addFirst((temp2.clone()));
			}
		}

		/*
		 * Si la columna se puede incrementar, se mueve el numero a la derecha
		 * del cero
		 */
		if (!(pos0[1] + 1 > size - 2)) {
			Cuadro[][] temp = (Cuadro[][])tiles.clone();
			for(int i=0;i<tiles.length;i++){
				temp[i]=(Cuadro[])tiles[i].clone();
			}
			Cuadro[][] temp2;
			temp2 = swap(pos0,temp[pos0[0]][pos0[1]+1],temp);
			if (!(neig.contains(temp2))) {
				neig.addFirst(temp2.clone());
			}
		}
		
		/*
		 * Si la columna se puede reducir, se mueve el numero a la izquierda
		 * del cero
		 */
		if(!(pos0[1]-1 < 0)){
			Cuadro[][] temp = (Cuadro[][])tiles.clone();
			for(int i=0;i<tiles.length;i++){
				temp[i]=(Cuadro[])tiles[i].clone();
			}
			Cuadro[][] temp2;
			temp2 = swap(pos0,temp[pos0[0]][pos0[1]-1],temp);
			if (!(neig.contains(temp2))) {
				neig.addFirst(temp2.clone());
			}
		}
		if(!(neig.isEmpty()) && (ant != null)){neig.removeLast();}
		//num_movimientos++;
		while(!(neig.isEmpty())){
			Board Bvecino = new Board(neig.pollFirst(),0,this);//num_movimientos
			Board_neig.add(Bvecino);
		}
		return Board_neig;
	}

}