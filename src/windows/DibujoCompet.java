package windows;

import GrafoCompet.EnlaceCompet;
import GrafoCompet.NodoCompet;
import GrafoCompet.PosicionCompet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;


public class DibujoCompet extends JPanel implements MouseListener, MouseMotionListener{

	static int nodo=0;
	private int ne,nc;
	HashMap<String,HashMap<String, Integer>> grafo;
	static boolean flagPro=false;
	
	private NodoCompet nv=null;
	NodoCompet ni=null,nf=null;
	static double mul=1;
	static ArrayList<NodoCompet> lista=new ArrayList<>();
	static ArrayList<EnlaceCompet> listae=null;
	private ArrayList<String> help=null;
	private ArrayList<EnlaceCompet> enlacesEliminar = null;
	public ArrayList<PosicionCompet> listp=new ArrayList<>();
	static ArrayList<EnlaceCompet> listaEnlace=new ArrayList<>();
	static ArrayList<EnlaceCompet> listapintar=new ArrayList<>();
	private NodoCompet puntoMedio=new NodoCompet(0,0,"0");
	private Point p1,p2;
	private NodoCompet nodomover;
	private int inNodo;
	private int opEnlace=0;
	String nuevo = "", nuevofin="";
	static boolean sw=false;	
	
	
	public DibujoCompet() {
		setBackground(new Color(66,68,65));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLista(new ArrayList<>());
		setListae (new ArrayList<>());
		setEnlacesEliminar (new ArrayList<>());
		setPreferredSize(new Dimension (2000,2000));
		setHelp (new ArrayList<>());
		addMouseListener(this);
		addMouseMotionListener(this);
		grafo = new HashMap<String,HashMap<String, Integer>>();	
	}
	public boolean isFlagPro() {
		return flagPro;
	}
	public void setFlagPro(boolean flagPro) {
		this.flagPro = flagPro;
	}
	public ArrayList<EnlaceCompet> getEnlacesEliminar() {
		return enlacesEliminar;
	}



	public void setEnlacesEliminar(ArrayList<EnlaceCompet> enlacesEliminar) {
		this.enlacesEliminar = enlacesEliminar;
	}

	public void crearVertice(String vertice, HashMap<String,Integer>relacion) {
		grafo.put(vertice,relacion);
	}
	
	public ArrayList<String> getHelp() {
		return help;
	}



	public void setHelp(ArrayList<String> help) {
		this.help = help;
	}



	public ArrayList<EnlaceCompet> getListae() {
		return listae;
	}



	public void setPuntoMedio(NodoCompet puntoMedio) {
		this.puntoMedio = puntoMedio;
	}
	public NodoCompet getPuntoMedio() {
		return puntoMedio;
	}


	public boolean isSw() {
		return sw;
	}
	public void setSw(boolean sw) {
		this.sw = sw;
	}
	public void setListae(ArrayList<EnlaceCompet> listae) {
		this.listae = listae;
	}



	public ArrayList<NodoCompet> getLista() {
		return lista;
	}


	public void setLista(ArrayList<NodoCompet> lista) {
		this.lista = lista;
	}

	public static void limpiar() {
		
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		if(nodo==1) {
			getLista().clear();
			getListae().clear();
			getHelp().clear();
			nodo=0;
		}else {
		for (EnlaceCompet enlace : listae) {
			if(enlace.isDup()) {
				enlace.dibujarEnlaceDup(g);
			}else if(enlace.isMismo()){
				enlace.dibujarEnlaceMismo(g);
			}else {
				enlace.dibujarEnlaceNoDir(g);
			}
		}
		for(int i=0; i<getLista().size(); i++) {
			lista.get(i).dibujarNd(g);
			g.setColor(Color.BLUE);
			int antx=lista.get(i).getX();
			int anty= lista.get(i).getY();
			g.setColor(new Color(255,255,255));
			g.drawString(lista.get(i).getName(),  antx,  anty);
			}
			
		g.setColor(Color.RED);
		if(sw)
		{
			g.setColor(Color.red);
			int aux=(int)Math.round(puntoMedio.getXx()*mul);
			int auy=(int)Math.round(puntoMedio.getYy()*mul);
			g.fillOval(aux-8, auy-8, 16, 16);
		}
		if(flagPro)
		{
			g.setColor(Color.black);
			for(EnlaceCompet enlace:listapintar)
			{
				enlace.dibujarEnlaceMedio(g,mul);
			}
			
		}
		}
	}


	public ArrayList<EnlaceCompet> getListapintar() {
		return listapintar;
	}
	public void setListapintar(ArrayList<EnlaceCompet> listapintar) {
		this.listapintar = listapintar;
	}
	public double getMul() {
		return mul;
	}
	public void setMul(double mul) {
		this.mul = mul;
	}
	public void mouseClicked(MouseEvent e){
		boolean flag=false;
		boolean enlace=false;
		int contador=0;
		EnlaceCompet ed=null;
		String newName=null;
		// TODO Auto-generated method stub
		if(e.getButton()==e.BUTTON1) {
			
			for(NodoCompet nodo : lista) {
				if(new Rectangle (nodo.getX()-30, nodo.getY()-30, nodo.getB(), nodo.getA()).contains(e.getPoint())){
					if(p1==null) {
						p1=new Point(nodo.getX() + nodo.getB()/2, nodo.getY() + nodo.getA()/2);
						ni=nodo;
						flag=true;
						
					}else {
						p2=new Point(nodo.getX()+ nodo.getB()/2,nodo.getY()+ nodo.getA()/2);
						nf=nodo;
						boolean doble = verificarDobleEnlace(ni,nf);
						boolean mismo = verificarMismoEnlace(ni,nf);
						opEnlace=verificarEnlace(ni,nf);
						switch (opEnlace) {
						case 0:
						String name ="0";
						if(!mismo)
						{
							getListae().add(new EnlaceCompet (ni,nf,name,Color.white,doble,mismo));
						
						}
							
						p1=null;
						p2=null;
						flag=true;
						repaint();
						break;
						
						case 1:
							p1=null;
							p2=null;
							flag=true;
							repaint();
							break;
						case 2:
							newName=JOptionPane.showInputDialog("Ingrese el nuevo valor");
							for(EnlaceCompet enla : listae) {
								if((enla.getNi()==ni)&&(enla.getNf()==nf)) {
									enla.setName(newName);
								}
							}
							p1=null;
							p2=null;
							flag=true;
							repaint();
							break;
						case 3:
							EnlaceCompet el=null;
							for(EnlaceCompet enla : listae) {
								if((enla.getNi()==ni)&&(enla.getNf()==nf)) {
									el=enla;
								}
							}
							getEnlacesEliminar().add(el);
							for(EnlaceCompet eliminar : enlacesEliminar) {
								getListae().remove(eliminar);
							}
							repaint();
							p1=null;
							p2=null;
							flag=true;
							repaint();
							break;
							
						}
						
					}
				  }
				}
			
			if(flag==false) {

				/**
				String nombre;
				int c=getLista().size()+1;
				nombre=JOptionPane.showInputDialog("Ingrese el nombre del nodo "+c);
				VentanaCompet comet=new VentanaCompet();
				comet.getLista().add(new NodoCompet (e.getX(), e.getY(),e.getX(), e.getY(),60, 60, nombre,new Color(0, 102, 102)));
				puntoMedio.add(new NodoCompet(e.getX(),e.getY(),nombre));
				//double x=e.getX();
			//	double y=e.getY();
				getLista().add(new NodoCompet (e.getX(), e.getY(),e.getX(), e.getY(),60, 60, nombre,new Color(0, 102, 102)));
				help.add(nombre);
				repaint();*/
			}
	}
		
		else {//Click derecho para cambiar nombre del nodo
			
			int op;
			op=Integer.parseInt(JOptionPane.showInputDialog("1. Cambiar nombre a Nodo\n2. Eliminar nodo"));
			switch(op) {
			case 1:
				cambiarNombreNodo();
				break;
			case 2:
				eliminarNodo();
				break;
		}
	}
		
	}


	private void eliminarNodo() {
		// TODO Auto-generated method stub
		boolean existeNodo=false;
		listapintar.clear();
		String no=JOptionPane.showInputDialog("Ingrese el nombre");
		//validar(nc);
		//validarEntrada(nc);
		for(int i=0; i<getLista().size(); i++) {
		if(lista.get(i).getName().equals(no)){
			lista.remove(i);
			existeNodo=true;
		for(int j=0;j<listae.size();j++) {
			if(listae.get(j).getNi().getName().equals(no) || listae.get(j).getNf().getName().equals(no))
			{
				getListae().remove(j);
				j--;
			}
				
		}
		repaint();
		}
		}

		if(existeNodo==false) {
			JOptionPane.showMessageDialog(null, "El nodo no existe");
		}
		
	}



	private void cambiarNombreNodo() {
		// TODO Auto-generated method stub
		boolean existeNodo=false;
		ne=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero del nodo del que desea cambiar el nombre"));
		for(int i=0; i<getLista().size(); i++) {
			if((i+1)==ne) {
				String nuevonombre;
				nuevonombre=JOptionPane.showInputDialog("Ingrese el nuevo nombre para "+(i+1));
				lista.get(i).setName(nuevonombre);
				getHelp().set(i, nuevonombre);
				existeNodo=true;
				repaint();
			}
		}
		if(existeNodo==false) {
			JOptionPane.showMessageDialog(null, "El numero de nodo "+ne+" no existe");
		}
		
	}



	private int verificarEnlace(NodoCompet ni, NodoCompet nf) {
		// TODO Auto-generated method stub
		boolean rep=false;
		EnlaceCompet en=null;
		int op=0;
		for(EnlaceCompet e : listae) {
			if((e.getNi()==ni)&&(e.getNf()==nf)) {
				rep=true;
				en=e;
			}
		}
		if(rep==true) {
			op=Integer.parseInt(JOptionPane.showInputDialog("Ya existe un enlace entre los nodos: "+en.getNi().getName()+" y "+en.getNf().getName()
					+ "\n1. Cancelar accion\n2. Cambiar valor del enlace\n3. Eliminar enlace"));
		}
		return op;
	}



	private boolean verificarMismoEnlace(NodoCompet ni, NodoCompet nf) {
		// TODO Auto-generated method stub
		boolean flag=false;
		for(NodoCompet nodo : lista) {
			if((nodo==nf) && (nodo==ni)){
				flag=true;
			}
		}
		
		return flag;
	}



	private boolean verificarDobleEnlace(NodoCompet ni, NodoCompet nf) {
		// TODO Auto-generated method stub
		boolean flag=false;
		for(EnlaceCompet enlace : listae) {
			if((enlace.getNf()==ni) && (enlace.getNi()==nf)){
				System.out.println("Existe dup");
				flag=true;
			}
		}
		
		return flag;
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int in=0;
		for(NodoCompet nodo : lista) {
			if(new Rectangle (nodo.getX(), nodo.getY(), nodo.getB(), nodo.getA()).contains(e.getPoint())) {
				nodomover = nodo;
				inNodo = in;
				break;
			}
			
			in++;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stu
		nodomover=null;
		inNodo=-1;
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void validar (int nc) {
		for(int i=0; i<getLista().size(); i++) {
			if(nc==(i+1)) {
					
					for(EnlaceCompet enlace : getListae()) {
						if(enlace.getNf()==getLista().get(nc-1)) {
							getEnlacesEliminar().add(enlace);
						}
						
						if(enlace.getNi()==getLista().get(nc-1)) {
							getEnlacesEliminar().add(enlace);
						}
					}
		  }
			
	    }
	}



	private void validarEntrada(int nc) {
		// TODO Auto-generated method stub
		for(int i=0; i<getLista().size(); i++) {
			if(nc==(i+1)) {
				
				for(EnlaceCompet enlace : listae) {
					if(enlace.getNf()==lista.get(nc-1)) {
						getEnlacesEliminar().add(enlace);
					}
				}
		}
	 }
	}


	



	}