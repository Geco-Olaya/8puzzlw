package v0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;


public class Board implements Comparable
{
	
	/************************************************************************
	 * 							ATRIBUTOS DEL TABLERO
	 ************************************************************************/
	int id;//Cada posible solucion se le dara un ID unico con el objetivo de evitar estados repetidos.
	int[][] tiles;
	int[][] meta;//Cada objeto tablero contara con un tablero solucionado para realizar operaciones
	int size;
	int num_movimientos;
	int hamming=0;//Solo usaremos para esta version la prioridad Manhattan;
	int manhattan;
	Board ant;
	/************************************************************************
	 * 							ATRIBUTOS DEL TABLERO
	 ************************************************************************/	
	
	
	
	/***************************************************************************
	 * Este es el constructor del objeto board.
	 * Para esta version se tomo la decicion de que cada objeto board
	 * tuviera el atributo meta, que es una matriz con el resultado a encontrar
	 * 
	 * @param tiles
	 * @param num_movimiento
	 * @param tiles_ant
	 ****************************************************************************/
	public Board(int[][] tiles, int num_movimiento, Board tiles_ant,int id)
	{	
		this.id = id;
		this.tiles = tiles;
		this.size = tiles.length;
		  ////////////////////////////////////////////////////////////
		 ////////Inicia construccion del tablero meta////////////////
		////////////////////////////////////////////////////////////
		int num = 1;//Variable usada para construir tablero meta
		meta = new int[size][size];
		
		for(int i = 0; i<size;i++){
			for(int j = 0; j<size;j++){
				if(num != size*size){
					meta[i][j] = num++;
				}else{
					meta[i][j] = 0;
				}
				
			}
			
		}//Fin de la construccion del tablero meta.
		this.num_movimientos = num_movimiento;
		if(num_movimientos != 0) {ant = tiles_ant;}
		else{ant = null;}
		manhattan = manhattan();
	}
	/**************************************************************************
	 * 							FIN DEL CONSTRUCTOR
	 *************************************************************************/
	/**
	 * Retrona el numero de bloques fuera de lugar.
	 * @return
	 */
	public int hamming()
	{
		int prioridadH=0;
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				if(!(meta[i][j] == tiles[i][j]) && tiles[i][j] != 0){
					prioridadH++;
				}
			}
		}//Fin recorrido de la matriz
		return prioridadH + num_movimientos;		
	}
	
	/**
	 * Retorna la suma de distancia Manhattan entre bloques
	 * @return
	 */
	public int manhattan()
	{
		int[] indices = new int[2];//Esta variable almacena fila y columna retornados por find()
		int prioridadM=0;
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				if(!(meta[i][j] == tiles[i][j]) && tiles[i][j] != 0){
					indices = find(tiles[i][j]);
					prioridadM += Math.abs(indices[0] - i) + Math.abs(indices[1] - j);
				}
			}
		}//Fin recorrido de la matriz
		return prioridadM + num_movimientos;		
	}
	
	/**
	 * Metodo privado que sirve a auxiliar para el metodo Mahatan.
	 * 
	 * Recibe el numero a buscar y retorna un arreglo de dimension dos
	 * con la fila y la columna en donde se encuentra dicho numero dentro de la matriz
	 * que se esta analizando. 
	 * @param nummber
	 * @return
	 */
	
	private int[] find(int number)
	{ 
		int[] poss = new int[2];
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				if((number == meta[i][j])){					
					poss[0] = i;
					poss[1] = j;
					return poss;
				}
			}
		}//Fin recorrido de la matriz
		poss = null;
		return poss;//Se retorna null en caso de estar buscando un elemento que no esta en la matriz
	}
	
	/**
	 * Hace  que el tablero sea iual
	 */
	public boolean equals(Object y)
	{
		return false;
	}
	
	/**
	 * Retorna un Iterador de todos los vecinos de las posiciones
	 * @return
	 
	public Iterable<Board> neighbors()
	{
		
	}*/
	
	public String toString()
	{
		String retorno ="Tablero objetivo: \n\n";
		
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				if(j+1 == size){
					retorno+= meta[i][j]+"\n";
				}else{
					retorno+= meta[i][j]+" | ";
				}
				
			}
		}//Fin ciclo;
		
		retorno+="Tablero a resolver: \n\n";
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				if(j+1 == size){
					retorno+=tiles[i][j]+"\n";
				}else{
					retorno+= tiles[i][j]+" | ";
				}
				
			}
		}//fin ciclo
		
		retorno+= "\n\n Valor Hamming del tablero: "+hamming();
		
		return retorno;
		
	}

	/****************************************************************************************
	 * Comparare la matriz tiles con una dada para identificar si un tablero es igual al otro
	 */
	@Override
	public int compareTo(Object ob) {
		int control=0;
		//Recorro la matriz temporal y la actual para verificar si se trata del mismo tablero
		Board temp = (Board) ob;
		for(int i = 0; i<size;i++){
			for(int j = 0; j<size;j++){
				if(temp.tiles[i][j] == tiles[i][j]) control++;
			}
		}
		//Inicio analisis de la variable control que se modifico mediante el recorrido de las dos matrices
		if(control==size) {return 0;} //Ambos tableros son iguales.
		else{return 1;}//Ambos tableros son diferentes.		
	}
	

}