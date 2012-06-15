package v0;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/************************************************************************
		 * INICIA CAPTURA DESDE LA ENTRADA ESTANDAR PARA CONSTRUIR EL TABLERO.
		 * 
		 ************************************************************************/
		Scanner sc = new Scanner(System.in);
		Solver solv;//Instancia del objeto solver
		int size = Integer.parseInt(sc.nextLine());
		Cuadro[][] board = new Cuadro[size][size];
		Cuadro[][] solucion = new Cuadro[size][size];
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
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				solucion[i][j] = new Cuadro(i,j,meta[i][j],i,j);
			}
		}
		// //////////////////////////////////////////////////////////////////////////
		// ///////////////////////////////FIN CONSTRUCCION TABLERO META////////////
		// //////////////////////////////////////////////////////////////
		// //////////Construye la matriz del tablero////////////////////
		int[] pos = new int[2];// /Contendra los valores objetivo de cada
								// numero.
		solv= new Solver(meta);//Construyo el objeto solver y le paso una matriz meta para operaciones internas
		int numero; // /Contendra el valor numero de cada objeto Cuadro.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				numero = sc.nextInt();
				pos = solv.find(numero);
				board[i][j] = new Cuadro(i, j, numero, pos[0], pos[1]);
			}
		}
		/************************************************************************
		 * FIN CAPTURA DESDE LA ENTRADA ESTANDAR PARA CONSTRUIR EL TABLERO.
		 * 
		 ************************************************************************/

		Board bd = new Board(board, 0, null);// Tablero inicial
		Board metaBoard = new Board(solucion,0,null);//Tablero objetivo.
		solv.Solver_Method(bd,metaBoard);
		

		

	}

}
