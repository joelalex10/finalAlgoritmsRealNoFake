package grafos;
import java.util.ArrayList;
import java.util.HashMap;
public class Grafos {
	
	HashMap<String,HashMap<String, Integer>> grafo;
	
	public  Grafos() {
		grafo = new HashMap<String,HashMap<String, Integer>>();	
	}
	
	public void crearVertice(String vertice, HashMap<String,Integer>relacion) {
		grafo.put(vertice,relacion);
	}
	
	public HashMap<String,Integer> getVertice(String vertice){
		return grafo.get(vertice);
	}
	
	public void elimiarEnlace(String inicio, String fin) {
		getVertice(inicio).remove(fin);
	}
	
	
	
	public void editarEnlace(String inicio, String fin, int newValue, HashMap<String,Integer> aux, ArrayList<String>vertices) {
		boolean isInListInicio = false;
		boolean isInListFin = false;
		
		
		for(int i=0;i<vertices.size();i++) {
			if(inicio.equals(vertices.get(i))) {
				isInListInicio = true;
			}
			if(fin.equals(vertices.get(i))) {
				isInListFin = true;
			}
		}
		if(isInListInicio && isInListFin) {
			getVertice(inicio).remove(fin);
			getVertice(inicio).put(fin, newValue);
		}else {
			System.out.println("LOS ELEMENTOS DEBEN ESTAR EN LA LISTA");
			
		}
		
		
		
		
		
	}
	
	public void eliminarGrafo(ArrayList<String>vertices) {
		grafo.clear();
		vertices.clear();
		
	}
	
	public void cargarNuevoGrafo(ArrayList<String>vertices, int [][]matriz, HashMap<String,Integer> aux) {
		for(int i=0;i<matriz.length;i++) {
			aux = new HashMap<String,Integer>();
			this.crearVertice(vertices.get(i), aux);
			
			for(int j=0;j<matriz.length;j++) {
				if(matriz[i][j]!=0) {
					aux.put(vertices.get(j), matriz[i][j]);
				}
			}
		}
	}
	
	public void eliminarVertice(String vertice, ArrayList<String>vertices, HashMap<String,Integer> aux) {
		vertices.remove(vertice);
		for(int i=0;i<vertices.size();i++) {
			aux = getVertice(vertices.get(i));
			aux.remove(vertice);
			}
	}
	
	
	public void editarVertice(String oldVertice, String newVertice, HashMap<String,Integer> aux,ArrayList<String>vertices) {
		System.out.println(vertices);
		aux = getVertice(oldVertice);
		grafo.remove(oldVertice);
		crearVertice(newVertice, aux);
		vertices.set(vertices.indexOf(oldVertice), newVertice);
		
		System.out.println(vertices);
		for(int i=0;i<vertices.size();i++) {
			try {
				HashMap<String,Integer>auxGrafo = getVertice(vertices.get(i));
				int obj = auxGrafo.remove(oldVertice);
				auxGrafo.put(newVertice, obj);
				//System.out.println("EXITO");
			}catch(NullPointerException e) {

				//System.out.println("PROBLEMAS");
				continue;
				
			}
		}
		System.out.println(vertices);
		
	}
	
	public boolean isConnected(String attributeA, String attributeB) {
		int a;
		
		try {
			a = this.getVertice(attributeA).get(attributeB);
			return true;
		}catch(NullPointerException e) {
			return false;
		}
		//System.out.println(a);
		//return false;
	}
}
