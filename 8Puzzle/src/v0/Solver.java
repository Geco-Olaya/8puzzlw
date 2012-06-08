package v0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Solver {

	public Solver(Board initial)
	{
		
	}
	
	public boolean isSolvable()
	{
		return false;
	}

	public int moves()
	{
		return 0;
	}
	
	public String toString()
	{
		return null;
	}

	private Board generarCandidatos(Board tablero)
	{
		return tablero;
	}
	
	
	/**
	 * Main del programa
	 * @param args
	 */
	public static void main(String[] args) {
		
		/************************************************************************
		 * INICIA CAPTURA DESDE LA ENTRADA ESTANDAR PARA CONSTRUIR EL TABLERO.
		 * 
		 ************************************************************************/
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		int[][] board = new int[size][size];
		
		//Construye la matriz del tablero
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				board[i][j] = sc.nextInt();
			}
		}
		/************************************************************************
		 * FIN CAPTURA DESDE LA ENTRADA ESTANDAR PARA CONSTRUIR EL TABLERO.
		 * 
		 ************************************************************************/
		
		
		
		
		
		
		Board bd = new Board(board,0,null,0);//Tablero inicial
		System.out.print("Valor Manhatan: "+bd.manhattan()+"\nValor Hamming: "+bd.hamming());
		Solver solve = new Solver(bd);
		//bd.hamming();
		//System.out.print(bd.toString());
		
	}

}
