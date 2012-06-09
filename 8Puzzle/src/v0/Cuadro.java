package v0;

/**********************************************************************************
 * 
 * 
 * 
 * @author dsm
 *
 */

public class Cuadro {
	int row,col,row_obj,col_obj;
	int num;
	int prioridadM;
	int size; //Tamaño del tablero que se obtiene de la matriz meta.
	
	/************************************************************************
	 *  Constructor del cuadrado
	 * @param row -> Filas
	 * @param col  -> Columnas
	 * @param num  -> Numero a contener
	 * @param meta	-> Matriz con la meta a alcanzar.
	 *************************************************************************/
	public Cuadro(int row,int col, int num, int[][] meta)
	{
		this.row = row;
		this.col = col;
		this.num = num;
		size = meta.length;
		prioridadM = manhattan(meta);
		
	}
	
	private int manhattan(int[][] meta)
	{
		int[] indices = new int[2];//Esta variable almacena fila y columna retornados por find()
		int prioridadM=0;
		indices = find (num,meta);
		prioridadM = Math.abs(indices[0] - row) + Math.abs(indices[1] - col);
		/*
		for(int i = 0; i<size ; i++){
			for(int j = 0; j<size;j++){
				if(!(meta[i][j] == num) && num != 0){
					indices = find(num,meta);
					prioridadM += Math.abs(indices[0] - i) + Math.abs(indices[1] - j);
				}
			}
		}//Fin recorrido de la matriz*/
		return prioridadM;		
	}
	
	private int[] find(int number,int[][] meta)
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
}
