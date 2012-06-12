package v0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Solver {

	public Solver(Board initial) {
		
	}

	public boolean isSolvable() {
		return false;
	}

	public int moves() {
		return 0;
	}

	public String toString() {
		return null;
	}

	private Board generarCandidatos(Board tablero) {
		return tablero;
	}

	
	/**
	 * Esta funcion se encarga de buscar los numeros en el tablera meta para
	 * crear los objetos "Cuadro" con las coordenadas reales que deberan de
	 * cada objeto.
	 * 
	 * @param number
	 * @param meta
	 * @return
	 */
	private static int[] find(int number,int[][] meta)
	{ 
		int size = meta.length;
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
	 * Main del programa
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/************************************************************************
		 * INICIA CAPTURA DESDE LA ENTRADA ESTANDAR PARA CONSTRUIR EL TABLERO.
		 * 
		 ************************************************************************/
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		Cuadro[][] board = new Cuadro[size][size];
		// ////////////////////Construyo tablero meta para analizarlo.
		int[][] meta;
		int num = 1;// Variable usada para construir tablero meta
		meta = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (num != size * size) {
					meta[i][j] = num++;
				} else {
					meta[i][j] = 0;
				}

			}
		}
		// //////////////////////////////////////////////////////////////////////////
		// ///////////////////////////////FIN CONSTRUCCION TABLERO
		// META/////////////

		// //////////////////////////////////////////////////////////////
		// //////////Construye la matriz del tablero////////////////////
		int[] pos = new int[2];// /Contendra los valores objetivo de cada
								// numero.
		int numero; // /Contendra el valor numero de cada objeto Cuadro.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				numero = sc.nextInt();
				pos = find(numero, meta);
				board[i][j] = new Cuadro(i, j, numero, pos[0], pos[1]);
			}
		}
		/************************************************************************
		 * FIN CAPTURA DESDE LA ENTRADA ESTANDAR PARA CONSTRUIR EL TABLERO.
		 * 
		 ************************************************************************/

		Board bd = new Board(board, 0, null);// Tablero inicial
		//System.out.print("Valor Manhatan: " + bd.manhattan()+ "\nValor Hamming: " + "SIN IMPLEMENTAR");
		

		

	}

}