# Sugerencia 1::: 8 de junio de 2012 #

Dentro de las sugerencias para el diseño tenemos Crear un objeto de tipo casilla(Recuadro) que contenga la posición actual en la matriz, el numero y la posicion real para el control de las prioridades.

Ejemplo

public class Recuadro{

int row, col; int num;
public Cuadricula(int row, int col, int num)
}


Luego en Board se creara una matriz de "cuadros" la prioridad Manhattan se calculara sumando las prioridades de cada cuadro.

# **Detalles** #

La clase Recuadro con tiene los siguientes parámetros
  * int **row, col** que contendrán la posición actual en el tablero
  * int **row\_obj, col\_obj** contendrán la posición objetivo o meta.
  * int **num**, contendrá el valor que representa el **cuadrado**.
  * int **prioridadM** valor de la prioridad manhattan de ese cuadro especifico.
  * int **size** es el tamaño del tablero que se esta resolviendo; se usa para hallar la prioridad Manhattan.