package v0;

import java.util.LinkedList;


public class Solver {

	MinPQ<Board> quee;//Cola prioridad
	LinkedList<Board> pila;//Pila de resultados.
	int[][] meta;
	public Solver(int[][] meta)
	{
		pila= new LinkedList<Board>();
		quee = new MinPQ<Board>();
		this.meta = meta;
	}
	/**
	 *Decide si el tablero tiene solucion o no.
	 */
	public boolean isSolvable(Board tablero) {
		int[] poss = new int[2];
		poss = tablero.find(0);
		if(poss == null){return false;}
		else{return true;}
	}

	/**
	 * Retorna el numero de movimientos
	 * @return
	 */
	public int moves() 
	{
		return pila.peek().num_movimientos;
	}

	public String toString() {
		String retorno = "";
		int encolados = pila.size();
		int movimientos = moves();
		Board temp;
		LinkedList<Board> pila_print = new LinkedList<Board>();
		while(!(pila.isEmpty())){
			pila_print.push(pila.pop());
		}
	
		while(!(pila_print.isEmpty())){
			temp = pila_print.pop();
			if(!(pila_print.contains(temp))){retorno+= temp.toString();}
		}
		retorno+="\n\nEstados encolados: "+encolados;
		retorno+="\nNumero de movimientos: "+movimientos;
		return retorno;
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
	public int[] find(int number)
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
	

	/**
	 * Se realizara una primera implementacion del solver.
	 * En esta primera version solo se generaran los vecinos de un cuadro dado;
	 * se ingresara a la cola de prioridad, y luego se obtendra el de menor prioridad.
	 * @param initial
	 */
	@SuppressWarnings("unchecked")
	public void Solver_Method(Board initial,Board meta) {
		LinkedList<Board> vecinos = new LinkedList<Board>();//Lista enlazada con los vecinos de una Board dado
		//�El tablero tiene solucion?
		if(isSolvable(initial)){
			//�El tablero ya esta solucionado?
			if(initial.manhattan == 0){
				System.out.print("\n\nEl tablero ingresado ya esta solucionado\n\n");
				System.exit(0);
			}
			
			//Si llegamos hasta este bloque es porque el tablero es solucionable
			LinkedList<Board> clone = (LinkedList<Board>) initial.vecinos().clone();
			vecinos = clone;
			while(!(vecinos.isEmpty())){
				quee.insert(vecinos.poll());
			}
			pila.push(initial);
			pila.push(quee.delMin());
			/**
			 * Mientras lo que hay en la cima de la pila no sea igual
			 * al tablero meta, se generan vecinos y priorizan, para luego
			 * poner el de menor prioridad sobre la cima de la pila
			 */
			
			while(!(meta.equals(pila.peek()))){
				//quee = new MinPQ<Board>();
				vecinos = pila.peek().vecinos();//Genero los vecinos de la cima de la pila
				while(!(vecinos.isEmpty())){
					quee.insert(vecinos.poll()); //Ingreso los vecinos a la cola de prioridad.
				}
				pila.push(quee.delMin());
			}
			System.out.print(toString());
			
		}else{
			System.out.print("El tablero propuesto no tiene solucion");
		}
	}
}