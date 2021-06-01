package grafos;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		
		Grafos g = new Grafos();
		ArrayList<String>vertices=new ArrayList<String>();
		
		vertices.add("a");
		vertices.add("b");
		vertices.add("c");
		vertices.add("d");
		vertices.add("e");
		vertices.add("f");
		
		//char[]vertices = {'a','b','c','d','e','f'};
		
		HashMap<String,Integer> aux;
		
		aux = new HashMap<String,Integer>();
		g.crearVertice(vertices.get(0), aux);
		aux.put(vertices.get(1),4);
		aux.put(vertices.get(2),2);
		
		
		aux = new HashMap<String,Integer>();
		g.crearVertice(vertices.get(1), aux);
		
		aux.put(vertices.get(5),9);
		aux.put(vertices.get(4),3);
		
		
		aux = new HashMap<String,Integer>();
		g.crearVertice(vertices.get(2), aux);
		
		aux.put(vertices.get(5),9);
		aux.put(vertices.get(0),2);
		
		
		aux = new HashMap<String,Integer>();
		g.crearVertice(vertices.get(3), aux);
		
		aux.put(vertices.get(0),6);
		aux.put(vertices.get(4),1);
		aux.put(vertices.get(5),2);
		
		
		aux = new HashMap<String,Integer>();
		g.crearVertice(vertices.get(4), aux);
		
		aux.put(vertices.get(1),8);
		aux.put(vertices.get(0),2);
		aux.put(vertices.get(2),3);
		
		
		aux = new HashMap<String,Integer>();
		g.crearVertice(vertices.get(5), aux);
		
		aux.put(vertices.get(2),1);
		aux.put(vertices.get(5),4);
		
		
		aux = g.getVertice(vertices.get(0));
		aux.put(vertices.get(3),9);
		aux.put(vertices.get(4),6);
		aux.put(vertices.get(0),6);
		
		aux =g.getVertice(vertices.get(1));
		aux.put(vertices.get(0),3);
		
		
		/*
		for(int i=0;i<vertices.size();i++) {
			System.out.println("");
			for(int j=0;j<vertices.size();j++) {
				if(g.getVertice(vertices.get(i)).get(vertices.get(j))==null){
					System.out.print(0+"\t");
				}else {
					System.out.print(g.getVertice(vertices.get(i)).get(vertices.get(j))+"\t");
					}
				}
		}*/
		
		
		System.out.println("");
		
		for(int i=0;i<vertices.size();i++) {
				System.out.println(vertices.get(i)+" -> "+g.getVertice(vertices.get(i))+"\n");
		}
		
		System.out.println(g.isConnected("a", "b"));
		/*
		//*******************************************************************************
		//ELIMINACION NODO
		vertices.remove("b");
		for(int i=0;i<vertices.size();i++) {
			aux = g.getVertice(vertices.get(i));
			aux.remove("b");
			}
		//*******************************************************************************
		*/
		g.eliminarVertice("b", vertices, aux);
		System.out.println("ANALISIS");
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i)+" -> "+g.getVertice(vertices.get(i))+"\n");
		}
		g.editarVertice("a", "H", aux, vertices);
		System.out.println("ANALISIS");
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i)+" -> "+g.getVertice(vertices.get(i))+"\n");
		}
		
		g.elimiarEnlace("f","f");
		System.out.println("ANALISIS");
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i)+" -> "+g.getVertice(vertices.get(i))+"\n");
		}
		/*
		//****************************************************************************
		//EDICION DE UN NODO
		System.out.println("ANALISIS");
		aux = g.getVertice(vertices.get(0));
		g.grafo.remove("a");
		g.crearVertice("h", aux);
		vertices.set(0, "h");//final
		
		for(int i=0;i<vertices.size();i++) {
			try {
				HashMap<String,Integer>auxGrafo = g.getVertice(vertices.get(i));
				int obj = auxGrafo.remove("a");
				auxGrafo.put("h", obj);
			}catch(NullPointerException e) {
				
			}
		}
		
		//****************************************************************************
		*/
		
		
		System.out.println("ANALISIS");
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i)+" -> "+g.getVertice(vertices.get(i))+"\n");
		}
		
		g.editarEnlace("e", "H", 12, aux,vertices);
		
		System.out.println("ANALISIS");
		
		int tam2=vertices.size();
		Object mat2[][]=new Object[tam2][tam2];
		/*
		for(int i=0;i<vertices.size();i++) {
			System.out.println("");
			for(int j=0;j<vertices.size();j++) {
				
				if(g.getVertice(vertices.get(i)).get(vertices.get(j))==null){
					System.out.print(0+"\t");
					mat[i][j] = 0;
				}else {
					System.out.print(g.getVertice(vertices.get(i)).get(vertices.get(j))+"\t");
					mat[i][j] = g.getVertice(vertices.get(i)).get(vertices.get(j));
					}
				}
		}*/
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i)+" -> "+g.getVertice(vertices.get(i))+"\n");
		}
		
		g.eliminarGrafo(vertices);
		System.out.println("ANALISIS");
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i)+" -> "+g.getVertice(vertices.get(i))+"\n");
		}
		
		vertices.add("u");
		vertices.add("v");
		vertices.add("w");
		vertices.add("x");
		vertices.add("f");
		vertices.add("z");
		
		int[][] matriz = new int[6][6];
		
		matriz[0][1]=7;
		matriz[1][0]=21;
		matriz[0][3]=32;
		matriz[0][0]=4;
		matriz[1][1]=6;
		matriz[2][0]=11;
		matriz[5][5]=9;
		
		g.cargarNuevoGrafo(vertices, matriz, aux);
		
		System.out.println("ANALISIS");
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i)+" -> "+g.getVertice(vertices.get(i))+"\n");
		}
		
		g.editarEnlace("u", "u", 100, aux,vertices);
		System.out.println("ANALISIS");
		for(int i=0;i<vertices.size();i++) {
			System.out.println(vertices.get(i)+" -> "+g.getVertice(vertices.get(i))+"\n");
		}
		
		
		
		
		
		
		
		
		
	
		
		
	}

}
