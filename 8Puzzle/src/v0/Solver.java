package v0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Solver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		//bd.hamming();
		System.out.print(bd.toString());
		
	}

}
