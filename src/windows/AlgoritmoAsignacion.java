package windows;

import grafos.Enlace;
import grafos.Nodo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class AlgoritmoAsignacion {
	//private Vector<Nodo> nodos;

	private Vector<Nodo> list1;
	private Vector<Nodo> list2;

	private Vector<Enlace> aristas;
	private int[][] matrizAdyacente;
	private int[][] matrizAlgoritmo;
	private boolean opcion;//maximo = true o minimo = false
	
	private int[][] alfa;    
	private int[][] resta1;
	private int[][] gama;
	private int[][] resta2;

	private int[][] matrizOptimo;
	
	public AlgoritmoAsignacion(int[][] matrizAdyacente, boolean opcion, Vector<Nodo> list1, Vector<Nodo> list2, Vector<Enlace> aristas) {
		super();
		this.matrizAdyacente = matrizAdyacente;
		this.opcion = opcion;
		this.list1 = list1;
		this.list2 = list2;
		this.aristas = aristas;

		System.out.println("DATOS");

		for(int i=0;i<matrizAdyacente.length;i++){

			System.out.println("");
			for(int j=0;j<matrizAdyacente.length;j++){

				System.out.print(matrizAdyacente[i][j]+"\t");
			}
		}

		System.out.println(matrizAdyacente);

		System.out.println(opcion);
		System.out.println(list1);
		System.out.println(list2);
		System.out.println(aristas);


	}//end constructor class

	public int[][] getMatrizAdyacente() {return matrizAdyacente;}
	public void setMatrizAdyacente(int[][] matrizAdyacente) {this.matrizAdyacente = matrizAdyacente;}	
	public int[][] getMatrizOptimo() {return matrizOptimo;}
	public void setMatrizOptimo(int[][] matrizOptimo) {this.matrizOptimo = matrizOptimo;}

	public void efectuarAlgoritmo() {	
		mostrar(matrizAdyacente);
		gama = getMinOMax(matrizAdyacente);
		resta2 = restarMatriz(matrizAdyacente, gama);		
		alfa = getMinOMax2(resta2);
		resta1 = restarMatriz(resta2, alfa);
		mostrar(resta1);
		int [][] resultado = new int[resta1.length][resta1.length];
		for (int x=0; x < resultado.length; x++) {
			for (int y=0; y < resta1[x].length; y++) {
				resultado[x][y] = 100*resta1 [x][y];
			}
		}
		Sudoku a=new Sudoku(resultado);
		int[][] matrizasd = a.solve();
		int[][] hola=multiplicarMatrices(matrizAdyacente,cambiar(matrizasd));
		if (a.isLol()){
			mostrarString(orden(matrizAdyacente,cambiar(matrizasd)));
			JOptionPane.showMessageDialog (null, letra(matrizAdyacente,cambiar(matrizasd)));
			SumaAsignado(matrizAdyacente,cambiar(matrizasd));

		}



		//matrizAlgoritmo = escogerCeros(resta1);
		//mostrar(matrizAlgoritmo);
		
		
	}
	public static int[][] cambiar(int matriz[][]){
		int [][]m;
		for (int x=0; x < matriz.length; x++) {
			for (int y=0; y < matriz[x].length; y++)
				if (matriz[x][y]==1){
					matriz[x][y]=0;
				}else
					matriz[x][y]=1;
		}
		return matriz;
	}
	public static int[][] multiplicarMatrices(int [][] originalMat, int [][] newMat){

		int resultMat[][] = new int[originalMat.length][originalMat[0].length];

		for (int x=0; x < originalMat.length; x++) {
			for (int y=0; y < originalMat[x].length; y++)
				resultMat[x][y]=originalMat[x][y]*newMat[x][y];
		}

		return resultMat;
	}
	public String[][] orden(int[][] a, int[][] b){
		String s[][] = new String[b.length][b[0].length];
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				if(b[i][j]==1){
					s[i][j]=a[i][j]+"";

				}
				else{
					s[i][j]="("+a[i][j]+")";
				}
			}
		}
		return s;
	}
	public String[] letra(int[][] a, int[][] b){
		String al[]= new String[b.length];
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				if(b[i][j]==1){
					//al[i] = "Desde "+nodos.get(i).getNombre()+" Hasta "+nodos.get(j+b.length).getNombre();
				}
			}
		}
		return al;
	}
	public void SumaAsignado(int[][] A, int[][] B){
		int suma=0;
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B[0].length; j++) {
				if(B[i][j]==0) {
					System.out.println(suma);
					suma=suma+A[i][j];


				}else {
					suma=suma+0;
				}
			}
		}

		JOptionPane.showMessageDialog(null, "Total: "+suma);


	}
	private int[][] getMinOMax(int[][] m){
		int [][] matrizC = new int[m.length][m[0].length];
		for(int i = 0; i < matrizC[0].length; i++) {
			if(opcion) {
				int maximo = getMaximo(m, i, false);
				for(int j = 0 ;j < matrizC.length; j++) {
					matrizC[j][i] = maximo;
				}
			} else {
				int minimo = getMinimo(m, i, false);
				for(int j = 0 ;j < matrizC.length; j++) {
					matrizC[j][i] = minimo;
				}
			}			
		
		
		}
		return matrizC;		
	}
	private int[][] getMinOMax2(int[][] m){
		int [][] matrizC = new int[m.length][m[0].length];
		for(int i = 0; i < matrizC.length; i++) {
			if(opcion) {
				int maximo = getMaximo(m, i, true);
				for(int j = 0 ;j < matrizC[0].length; j++) {
					matrizC[i][j] = maximo;
				}
			} else {
				int minimo = getMinimo(m, i, true);
				for(int j = 0 ;j < matrizC[0].length; j++) {
					matrizC[i][j] = minimo;
				}
			}			
		}
		return matrizC;		
	}
	private int getMaximo(int[][] m, int indice, boolean estado) {
		int maximo;
		if(estado) {
			maximo = m[indice][0];
			for(int i = 0; i < m[0].length; i++)
				maximo = Math.max(m[indice][i], maximo);
		} else {
			maximo = m[0][indice];
			for(int i = 0; i < m.length; i++) 
				maximo = Math.max(m[i][indice], maximo);
		}
		
		return maximo;	
	}
	private int getMinimo(int[][] m, int indice, boolean estado) {
		int minimo;
		if(estado) {
			minimo = m[indice][0];
			for(int i = 0; i < m[0].length; i++) 
				minimo = Math.min(m[indice][i], minimo);
		} else {
			minimo = m[0][indice];
			for(int i = 0; i < m.length; i++) 
				minimo = Math.min(m[i][indice], minimo);
		}
		return minimo;	
	}
	private int[][] restarMatriz(int[][] matrizA, int[][] matrizB) {
		int[][] matrizRes = new int[matrizA.length][matrizA[0].length];
		for(int i=0;i<matrizA.length;i++){
	        for(int j=0;j<matrizA[0].length;j++){
	                matrizRes[i][j]=matrizA[i][j]-matrizB[i][j]; 
	        }
		}
		return matrizRes;
	}
	
//	private int[][] escogerCeros(int[][] m) {
//		int aux[][];
//		int [][] h = new int[m.length][m[0].length];
//		boolean flag = true;
//		aux = m;
//		do {
//
//			//JOptionPane.showMessageDialog(null, "itacion joder");
//			aux = iterar(aux);
//			//mostrar(aux);
//			eliminaCeros(aux);
//			//mostrar(aux);
//
//		} while(!validar(aux));
//		matrizOptimo = aux;
//		//mostrar(matrizOptimo);
//
//		/*aux = m;
//		aux = iterar(aux);
//		JOptionPane.showMessageDialog(null, "itacion joder");
//		mostrar(aux);
//		eliminaCeros(aux);
//		mostrar(aux);
//		matrizOptimo = aux;*/
//
//		/*do {
//			aux = iterar(aux);
//			JOptionPane.showMessageDialog(null, "itacion joder");
//			mostrar(aux);
//			eliminaCeros(aux);
//			mostrar(aux);
//
//			/*if(existeCeros(aux)) {
//				JOptionPane.showMessageDialog(null, "LLEGUE aQUI "+existeCeros(aux));
//				flag = false;
//			} /*else {
//				JOptionPane.showMessageDialog(null, "LLEGUE aQUI "+existeCeros(aux));
//				mostrar(aux);
//				aux = restarMatriz(resta1, gama);
//				mostrar(aux);
//				JOptionPane.showMessageDialog(null, "LLEGUE aQUI ITERACION");
//				/*int[][] matriz = {
//						{80,  0, 30, 120, 0},
//						{80,  0, 30, 120, 0},
//						{60, 60,  1,  80, 0},
//						{60, 60,  1,  80, 0},
//						{ 0, 90,  0,   0, 5}};
//				/*int[][] matriz = {
//						{80,  0,  0, 120, 0},
//						{80,  0,  0, 120, 0},
//						{60, 60,  1,  80, 0},
//						{60, 60,  1,  80, 0},
//						{ 0, 90,  0,   0, 5}};*/
//				/*int[][] matriz = {
//						{3,  2,  0, 2},
//						{0,  5,  1, 1},
//						{0,  2,  4, 6},
//						{3,  0,  2, 0},};
//				//aux = iterar(matriz);
//
//			//}
//
//		} while(flag);*/
//
//		return h;
//
//	}
	/*private boolean ajustarCeros(int[][] jj) {
		int[][]aux = jj;
		boolean flag = true;
		for(int i = 0; i < aux.length; i++) {
			for(int j = 0; j < aux[0].length; j++) {					
				if(aux[i][j] == 0) {						
					eliminaCeros(aux);					
					if(existeCeros(aux)) {
						//mostrar(aux);	 
						flag = false;
					} else {								
						aux = restarMatriz(resta2, alfa);
						aux[i][j] = 4;
						mostrar(aux);
					}//fin del if else interno						
				}//fin del if 					
			}//for j	
		}//for i
		return flag;
	} */
//	private int[][] iterar(int [][] g) {
//		int[][] v = g;
//		int[][] lineasOficiales = getLineas(g);
//		int [] filas = lineasOficiales[0];
//		int [] columnas = lineasOficiales[1];
//		//mostrar(lineasOficiales);
//
//		int menor = getMenorOficial(filas, columnas, v);
//		System.out.println(menor);
//
//
//		for(int i1 : filas) {
//			for(int i2 : columnas) {
//				if(i1 != -1 && i2 != -1) {
//					v[i1][i2] += menor;
//				}
//			}
//		}
//
//		for(int i = 0; i < v.length; i++) {
//			for(int j = 0; j < v[0].length; j++) {
//				if(esDiferente(i, filas) && esDiferente(j, columnas)) {
//					v[i][j] -= menor;
//				}
//			}
//		}
//		return v;
//	}
	private int getMenorOficial(int[] j1, int[] j2, int[][] g) {
		int menor = 100;
		for(int i = 0; i < g.length; i++) {
			for(int j = 0; j < g[0].length; j++) {
				if(esDiferente(i, j1) && esDiferente(j, j2)) {
					if(g[i][j] < menor)
						menor = g[i][j];
				}
			}			
		}
		return menor;
	}
	private boolean esDiferente(int i, int[] c) {
		boolean flag = true;
		for(int x : c) {
			if(x == i)
				flag = false;
		}
		return flag;		
	}
//	private int[][] getLineas(int[][] f) {
//		int[] lineasColumnas = new int[f[0].length], lineasFilas= new int[f.length];
//		int[][] lineasTod = new int[2][f[0].length];
//		int cf, cc;
//
//		//para las filas
//		for(int i = 0; i < f.length; i++) {
//			cf = 0;
//			int vacio = -1;
//			for(int j = 0; j < f[0].length; j++) {
//				if(f[i][j] == 0) {
//					cf++;
//				}
//			}
//			if(cf > 1)
//				vacio = i;
//			lineasFilas[i] = vacio;
//		}
//
//		//para las columnas
//		for(int i = 0; i < f.length; i++) {
//			cc = 0;
//			int vacio = -1;
//			for(int j = 0; j < f[0].length; j++) {
//				if(f[j][i] == 0) {
//					cc++;
//				}
//			}
//			if(cc > 1)
//				vacio = i;
//			lineasColumnas[i] = vacio;
//		}
//
//		for(int i = 0; i < f.length; i++) {
//			lineasTod[0][i] = lineasFilas[i];
//			lineasTod[1][i] = lineasColumnas[i];
//		}
//
//		lineasTod = minimizarLineas(f, lineasTod);
//		mostrar(lineasTod);
//
//
//		/*int[][] matriz = new int[f.length][2];
//		int max= 0;
//		for(int i = 0; i < f.length; i++) {
//			cc = 0;
//			for(int j = 0; j < f[0].length; j++) {
//				if(f[j][i] == 0) {
//					cc++;
//				}
//			}
//			matriz[i][0] = cc;
//			matriz[i][1] = i;
//			if(cc >= max)
//				max = cc;
//		}
//
//		if(estaFilaOColumna0(1, lineasTod)) {//1 es fila
//			for(int i = 0; i < f.length; i++) {
//				if(matriz[i][0] == max) {
//					lineasTod[0][matriz[i][1]] = matriz[i][1];
//				}
//			}
//		}
//		if(estaFilaOColumna0(2, lineasTod)) {// 2 es columna
//			for(int i = 0; i < f.length; i++) {
//				if(matriz[i][0] == max) {
//					lineasTod[1][matriz[i][1]] = matriz[i][1];
//				}
//			}
//		}*/
//
//
//
//		return lineasTod;
//	}
	private boolean estaFilaOColumna0(int i, int[][] lineasTod) {
		// TODO Auto-generated method stub
		return false;
	}

	private int[][] minimizarLineas(int[][] f, int[][] lineasTod) {
		//int c;
		int  aux = 0;
		ArrayList<Point> pointsCeros = new ArrayList<>();
		//pointsCeros = contarCerosArray(f);
		//para las columnas
		for(int ii = 0; ii < lineasTod.length; ii++) {
			for(int jj = 0; jj < lineasTod[0].length; jj++) {
				//c = 0;				
				pointsCeros.clear();				
				aux = jj;
				lineasTod[ii][jj] = -1;
				//mostrar(lineasTod);
				
				//algoritmo de prueba
				for(int i = 0; i < lineasTod.length; i++) {
					for(int j = 0; j < lineasTod[0].length; j++) {
						if(lineasTod[i][j] != -1) {
							Point puntoBlanco;
							if(i == 0) {
								for(int k = 0; k < lineasTod[0].length; k++) {
									puntoBlanco = new Point(lineasTod[i][j], k);
									if(f[lineasTod[i][j]][k] == 0 && !existePunto(pointsCeros, puntoBlanco)) {
										//c++;
										pointsCeros.add(puntoBlanco);
									}
								}																
							} else {									
								for(int k = 0; k < lineasTod[0].length; k++) {
									puntoBlanco = new Point(k, lineasTod[i][j]);
									if(f[k][lineasTod[i][j]] == 0 && !existePunto(pointsCeros, puntoBlanco)) {
										//c++;
										pointsCeros.add(puntoBlanco);
									}
								}																
							}							
						}												
					}
				}
				
				//JOptionPane.showMessageDialog(null, "pointsCeros = "+(pointsCeros.size()));
				//JOptionPane.showMessageDialog(null, "contarCeros = "+(contarCeros(f)));
				if(pointsCeros.size() != contarCeros(f)) 
					lineasTod[ii][jj] = aux;			
			}
		}		
		return lineasTod;
	}
	private boolean existePunto(ArrayList<Point> pointsCeros, Point p) {
		boolean flag = false;
		for(Point point : pointsCeros) {
			if( point.getX() == p.getX() && point.getY() == p.getY()) {
				flag = true;
			}
		}
		return flag;
	}
	private int contarCeros(int[][] f) {
		int c = 0;
		for(int[] j : f) {
			for(int i : j) {
				if(i == 0)
					c++;
			}
		}
		return c;		
	}

	/*private boolean existeCeros(int[][] x) {
		boolean[] flag = new boolean[x.length];
		boolean flag1 = true;
		int c = 0;
		for(int i = 0; i < x.length; i++) {
			for(int j = 0; j < x[0].length; j++) {
				if(x[i][j] == 0 && c <= 1) {					
					c++;
					flag[i] = true;
				} else {
					flag[i] = false;
					c = 0;
					break;
				}
			}			
		}
		for(boolean i : flag) {
			if(!i)
				flag1 = false;
		}
		return flag1;
	}*/
	private void eliminaCeros(int[][] m) {
		int contador;
		//ArrayList<Integer> arrayNroCerosFila = new ArrayList<>();
		ArrayList<Point> arrayCeros = new ArrayList<>();
		//JOptionPane.showMessageDialog(null, !validar(m));
		//System.out.println(haySolucionUno(m));
		while(haySolucionUno(m) && !validar(m)) {	
			//System.out.println(haySolucionUno(m));
			for(int i = 0; i < m.length; i++) {
				contador = 0;
				for(int j = 0; j < m[0].length; j++) {				
					if(m[i][j] == 0) {
						arrayCeros.add(new Point(i, j));
						contador++;	
					}							 			
				}				
				if(contador == 1) {
					Point t = arrayCeros.get(0);
					eliminarCerosFilaColumna(m, t.x, t.y);
				} 
				//System.out.println("hola");
				//mostrar(m);
				arrayCeros.clear();
			}
		}
		if(!haySolucionUno(m)) {
			for(int i = 0; i < m.length; i++) {
				for(int j = 0; j < m[0].length; j++) {				
					columna2ceros(i, j, m);						 			
				}
			}
		}
		//mostrar(m);
		/*if(!validar(m))
			volverMatriz(m);*/
		
	}

	private boolean haySolucionUno(int[][] m) {
		int contador;
		boolean flag = false;
		ArrayList<Integer> arrayNroCerosFila = new ArrayList<Integer>();
		for(int i = 0; i < m.length; i++) {
			contador = 0;
			for(int j = 0; j < m[0].length; j++) {				
				if(m[i][j] == 0) {
					contador++;	
				}							 			
			}				
			if(contador == 1) {
				arrayNroCerosFila.add(i);
			}			
		}
		for(int i = 0; i < m.length; i++) {
			contador = 0;
			for(int o : arrayNroCerosFila) {
				if( i != o) {
					for(int j = 0; j < m[0].length; j++) {				
						if(m[i][j] == 0)
							contador++;
					}
					if(contador == 1) {
						flag = true;
					}				
				}			
			}			
		}	
		return flag;
	}

	private void volverMatriz(int[][] m) {
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[0].length; j++) {				
				if(m[i][j] == -1) {
					m[i][j] = 0;
				}
			}
		}
		
	}

	private boolean validarArrayDeEnteros(ArrayList<Integer> a) {
		boolean flag = true;
		boolean[] g = new boolean[a.size()];
		JOptionPane.showMessageDialog(null, a.toString());
		for(int y = 0; y < g.length; y++) {
			if(a.get(y) != 1) {
				g[y] = false;
			}else {
				g[y] = true;
			}
		}
		for(boolean t : g) {
			if(t == false)
				flag = false;
		}
		return flag;
	}

	private boolean validar(int m[][]) {
		boolean flag = true;
		boolean []f=new boolean [m.length];
		int k;
		for (int i=0; i < m.length; i++) {
			k =0;
			for (int j=0; j < m.length; j++) {
				
				if(m[i][j] == 0) {
					k++;
				}
			}
			if(k == 1)
				f[i] = true;
			else
				f[i] = false;
		}
		for(boolean t : f) {
			if(t == false)
				flag = false;
		}
		return flag;
	}
	
	private void eliminarCerosFilaColumna(int[][] h, int i, int j ) {
		for(int c = 0; c < h.length; c++) {
			if(c != j && h[i][c] == 0) {
				h[i][c] = -1;
			}			
		}
		for(int c = 0; c < h.length; c++) {
			if(c != i && h[c][j] == 0) {
				h[c][j] = -1;
			}			
		}
	}
	
	public void mostrar(int matrizAdy[][]) {
		int c =matrizAdy.length;
		String[] m= new String[c];
		for(int i = 0;i < c; i++) {
			String v ="";
			for(int j = 0; j < matrizAdy[0].length; j++) {	
				v+= matrizAdy[i][j]+"   ";
			}
			m[i]=v;
		}
		JOptionPane.showMessageDialog (null, m);

	}
	public void mostrarString(String matrizAdy[][]) {
		int c =matrizAdy.length;
		String[] m= new String[c];
		for(int i = 0;i < c; i++) {
			String v ="";
			for(int j = 0; j < matrizAdy[0].length; j++) {
				v+= matrizAdy[i][j]+"   ";
			}
			m[i]=v;
		}
		JOptionPane.showMessageDialog (null, m);

	}

	public static int[][]columna2ceros(int f,int c, int[][]mat) {
		int k=0;
		for(int i=0; i<mat.length;i++) {
			if(mat[i][c]==0)
				k++;
		}
		if(k!=1) {			
			if(ContarCerosFila(f,mat)!=1) {
				mat[f][c] = -1;
			}
		}
		return mat;			
	}
	
	private static int ContarCerosFila(int f, int[][]mat) {
		int c=0;
		for(int i=0; i<mat.length;i++) {
			if(mat[f][i] == 0)
				c++;
		}
		return c;		
	}

}
		
	
