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
	/**
	 * Construye un tablero de un arreglo de bloques N*N
	 * @param tiles
	 */
	public Board(int[][] tiles)
	{
		this.tiles = tiles;
		int num = 1;
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
		}
	}
	
	/**
	 * Retrona el numero de bloques fuera de lugar.
	 * @return
	 */
	public void hamming()
	{
		System.out.print("Demo del metodo Hamming:\n\n");
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
					retorno+="\n";
				}else{
					retorno+= meta[i][j]+" | ";
				}
				
			}
		}//Fin ciclo;
		
		retorno+="Tablero a resolver: \n\n";
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				if(j+1 == size){
					retorno+="\n";
				}else{
					retorno+= tiles[i][j]+" | ";
				}
				
			}
		}//fin ciclo
		
		return retorno;
		
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		int[][] board = new int[size][size];
		
		//Construye la matriz del tablero
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				board[i][j] = sc.nextInt();
			}
		}
		
		Board bd = new Board(board);
		bd.hamming();
		//bd.toString();
		
		
		
	}
	

}