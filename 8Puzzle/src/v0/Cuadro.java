package v0;

/**********************************************************************************
 * 
 * Este objeto representa un cuadro de la matriz del juego.
 * 
 * Se construye con la posicion actual en la matriz y la 
 * posicion que deberia tener para realizar el calculo de la prioridad
 * Manhattan.
 * 
 * @author dsm
 *
 */

public class Cuadro implements Cloneable{
	int row,col,row_obj,col_obj;
	int num;
	int prioridadM;
	
	
	/**
	 * Constructor del objeto cuadro
	 * @param row -> Fila actual
	 * @param col -> Columna actual
	 * @param num -> numero del objeto
	 * @param row_obj -> Fila en la que deberia tener
	 * @param col_obj -> Columna en la que deberia estar el numero
	 */
	public Cuadro(int row,int col, int num, int row_obj, int col_obj)
	{
		this.row = row;
		this.col = col;
		this.num = num;
		this.row_obj = row_obj;
		this.col_obj = col_obj;
		prioridadM = manhattan();
		
	}
	
	/**
	 * Calculo simple de la prioridad Manhattan.
	 * @return
	 */
	private int manhattan()
	{
		int prioridadM=0;
		if(num != 0){
		prioridadM = Math.abs(row_obj - row) + Math.abs(col_obj - col);
		return prioridadM;	
		}
		else return 0;
			
	}
	
	public void swap(int[] newpos)
	{
		row = newpos[0];
		col = newpos[1];
		prioridadM = manhattan();
	}
	
	public String toString()
	{
		String retorno = null;
		retorno = " "+num+" ";
		return retorno;
	}
	/**
	 * Metodo clone que esta dando problemas por no comprender 
	 * bien su implementacion.
	 */
	public Object  clone()
	{
		Object obj = null;
		try{
			obj = super.clone();			
		}catch(CloneNotSupportedException ex){
			System.out.println("El objeto no se puede duplicar");
		}
		//obj = (Cuadro)obj.clone();
		/*
		for(int i = 0; i<obj.tiles.length; i++){
			obj.tiles[i] = (Cuadro[])obj.tiles[i].clone();
		}*/
		return obj;
	}
}