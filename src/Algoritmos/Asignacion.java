import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Asignacion{

    private int[][] matrizCostes;

    /* ----------------- IMPLEMENTACIÓN DEL ALGORITMO ----------------- */
    public Asignacion(int n){ // n será el tamaño del problema

        Scanner sc = new Scanner(System.in);  //Se crea un objeto Scanner

        matrizCostes=new int[n][n];
        for (int i=0; i<n; i++){// Generamos un problema aleatorio. Si quieres introducir tus datos elimina esto
            for (int j=0; j<n; j++){
                //matrizCostes[i][j]=(int) (Math.random()*10+1);
                System.out.print("Introduzca un numero en la posicion: ");
                matrizCostes[i][j] = Integer.parseInt(sc.nextLine());
            }
        }



        for (int i=0; i<n; i++){
            System.out.println("");
            for (int j=0; j<n; j++){
                System.out.print(matrizCostes[i][j]+"\t");
            }
        }



    }

    public int[] asignaTareas(){
        int[] nodoSolucion=generaSolucion(); // Generamos una solución por defecto
        int[] solucion=new int[matrizCostes.length]; // Solución que vdevolvemos
        int cota=calculaCotaAsociada(nodoSolucion);
        ArrayList<Integer> agentesDisponibles=inicializaArrayList(); // Tenemos el conjunto de agentes en una estructura de datos
        int j=0; // Contador para guiarnos sobre el nodoSolucion y nuestra solucion
        while (!agentesDisponibles.isEmpty()){ // Mientras que la pila no esté vacía
            int[] vectorCotas=new int[agentesDisponibles.size()]; // Nos declaramos un array de cotas
            for (int i=0; i<vectorCotas.length; i++){ // Rellenamos el array de cotas
                nodoSolucion[j]=matrizCostes[agentesDisponibles.get(i)][j];
                vectorCotas[i]=calculaCotaAsociada(nodoSolucion);
            }
            int posicion=getPosicionMejorAgente(vectorCotas); // Nos quedamos con el mejor valor (el que hace que la cota sea menor)
            nodoSolucion[j]=matrizCostes[agentesDisponibles.get(posicion)][j];
            solucion[j]=agentesDisponibles.get(posicion); // Lo incluimos en nuestra solución
            agentesDisponibles.remove(posicion); // Lo eliminamos de la pila
            j++;
        }
        return solucion;
    }

    // Construimos un nodo solución para poder comprar los valores con él
    private int[] generaSolucion(){
        int[] solucion=new int[matrizCostes.length];
        int j=0;
        for (int i=0; i<solucion.length; i++){
            solucion[i]=matrizCostes[i][j];
            j++;
        }
        return solucion;
    }

    // Generamos la pila con la información sobre los agentes
    private ArrayList<Integer> inicializaArrayList(){
        ArrayList<Integer> solucion=new ArrayList<Integer>();
        for (int i=0; i<matrizCostes.length; i++)
            solucion.add(i);
        return solucion;
    }

    // Calculamos la cota asociada a un nodo
    private int calculaCotaAsociada(int[] nodo){
        int sol=0;
        for (int i=0; i<nodo.length; i++)
            sol+=nodo[i];
        return sol;
    }

    // Extraemos al mejor agente
    private int getPosicionMejorAgente(int[] vectorCotas){
        int posicion=0;
        int valor=vectorCotas[0];
        for (int i=1; i<vectorCotas.length; i++){
            if (valor>vectorCotas[i]){
                valor=vectorCotas[i];
                posicion=i;
            }
        }
        return posicion;
    }

    /* --------------------- PRUEBA DEL ALGORITMO --------------------- */
    public static void main(String[] args){
        Asignacion a=new Asignacion(3);
        System.out.println(Arrays.toString(a.asignaTareas()));
    }

}