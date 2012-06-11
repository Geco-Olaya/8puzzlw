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
	Cuadro[][] tiles;
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
	public Board(Cuadro[][] tiles, int num_movimiento, Board tiles_ant)
	{	
		this.tiles = tiles;
		this.size = tiles.length;
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
	} */
	
	/**
	 * Retorna la suma de distancia Manhattan entre bloques
	 * @return
	 */
	public int manhattan()
	{
		int prioridadM=0;
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
					prioridadM += tiles[i][j].prioridadM;
				}
			}
		
		return prioridadM + num_movimientos;		
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
		
		//retorno+= "\n\n Valor Hamming del tablero: "+hamming();
		
		return retorno;
		
	}

	/****************************************************************************************
	 * OJO SE DEBE CAMBIAR PARA QUE SE COMPARE POR PRIORIDAD, DE ESTA MANERA FUNCIONARA
	 * LA COLA DE PRIORIDAD.
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