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
		System.out.print("Demo del metodo Hamming:\n\n");
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				if(meta[i][j] == tiles[i][j]){
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
	public void manhattan()
	{
		System.out.print("Demo del metodo Manhattan:\n\n");
		int freq=0;
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				if(meta[i][j] == tiles[i][j]){
					System.out.print(meta[i][j]+"::: 1\n");
				}else{
					System.out.print(meta[i][j]+"::: 0\n");
				}
			}
		}
		
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