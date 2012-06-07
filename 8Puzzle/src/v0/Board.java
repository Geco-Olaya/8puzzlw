package v0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;


public class Board
{
	int[][] tiles;
	int[][] meta;
	int size;
	int num_movimientos;
	Board ant;
	/**
	 * Construye un tablero de un arreglo de bloques N*N para solucionar.
	 * Tambien construye la matriz meta, que sera nuestra solucion a encontrar.
	 * 
	 * Este es el primer constructor y se utiliza solo al inicio del analisis.
	 * Para las demas de usara el consturctor siguiente.
	 * @param tiles
	 */
	public Board(int[][] tiles)
	{	
		this.tiles = tiles;
		ant = null;
		num_movimientos = 0;
		int num = 1;//Variable usada para construir tablero meta
		this.size = tiles.length;
		meta = new int[size][size];
		
		for(int i = 0; i<size;i++){
			for(int j = 0; j<size;j++){
				if(num != size*size){
					meta[i][j] = num++;
				}else{
					meta[i][j] = 0;
				}
				
			}
		}//Fin del for			
	}
	
	/**
	 * Este es el constructor por de defecto que se utiliza en el resto del juego.
	 * 
	 * @param tiles
	 * @param num_movimiento
	 * @param tiles_ant
	 */
	public Board(int[][] tiles, int num_movimiento, Board tiles_ant)
	{
		this.tiles = tiles;
		this.num_movimientos = num_movimiento;
		ant = tiles_ant;
	}
	
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
	

}