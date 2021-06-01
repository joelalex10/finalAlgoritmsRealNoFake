package Algoritmos;

import java.util.ArrayList;
import java.util.Collections;

public class Maximo {

	public static void main(String[] args) {
		ArrayList<Integer> lista= new ArrayList<Integer>();
		lista.add(2);
		lista.add(5);
		lista.add(3);
		lista.add(1);
		lista.add(2);
		
		System.out.println(lista);
		Collections.sort(lista);
		System.out.println(lista);
		System.out.println(lista.get(lista.size()-1));
		
		

	}

}
