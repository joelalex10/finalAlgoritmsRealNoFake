package GrafoCompet;


import javax.swing.*;
import java.awt.*;

public class EnlaceCompet extends JPanel{
	private int x1,y1,x2,y2;
	private String name;
	private Color c;
	private NodoCompet ni,nf;
	private boolean dup;
	private boolean mismo;
	
	public EnlaceCompet(NodoCompet ni, NodoCompet nf, String name, Color c, boolean dup, boolean mismo) {
		super();
		this.ni=ni;
		this.nf=nf;
		this.name = name;
		this.c = c;
		this.dup = dup;
		this.mismo = mismo;
	}
	public EnlaceCompet(NodoCompet ni, NodoCompet nf, String name, Color c) {
		super();
		this.ni=ni;
		this.nf=nf;
		this.name = name;
		this.c = c;
	}

	public boolean isMismo() {
		return mismo;
	}

	public void setMismo(boolean mismo) {
		this.mismo = mismo;
	}

	public boolean isDup() {
		return dup;
	}

	public void setDup(boolean dup) {
		this.dup = dup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getC() {
		return c;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public void setC(Color c) {
		this.c = c;
	}

	



	public NodoCompet getNi() {
		return ni;
	}

	public void setNi(NodoCompet ni) {
		this.ni = ni;
	}

	public NodoCompet getNf() {
		return nf;
	}

	public void setNf(NodoCompet nf) {
		this.nf = nf;
	}


	public void dibujarEnlace (Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(getC());
		g.drawLine(getNi().getX()+20, getNi().getY()+20, getNf().getX()+22,getNf().getY()+22);
		
		double x1=getNi().getX()+20;
		double y1=getNi().getY()+20;

		double x2=getNf().getX()+20;
		double y2=getNf().getY()+20;
		double m=(y2-y1)/(x2-x1);
		double xxd=0;
	    double yyd=0;
	    double rad=38;
	    if(m<0){
	        if(m>=-1){
	          if(x1>x2){
	        xxd=x2+rad;
	        yyd=m*(xxd-x1)+y1;
	        
	      }else{
	        xxd=x2-rad;
	        yyd=m*(xxd-x1)+y1;
	      }
	        }
	        else{
	        	if(x1>x2){
	            yyd=y2-rad;
	          xxd=(yyd+m*x1-y1)/(m);
	      }else{
	        yyd=y2+rad;
	        xxd=(yyd+m*x1-y1)/(m);
	      }
	        }
	      }
	      else{
	          if(m<=1){
	          if(x1>x2){
	        xxd=x2+rad;
	        yyd=m*(xxd-x1)+y1;
	        
	      }else{
	        xxd=x2-rad;
	        yyd=m*(xxd-x1)+y1;
	        
	      }
	        }
	        else{
	if(x1>x2){
	            yyd=y2+rad;
	          xxd=(yyd+m*x1-y1)/(m);
	      }else{
	            yyd=y2-rad;
	          xxd=(yyd+m*x1-y1)/(m);
	          
	      }
	        }
	      }  
		g.fillOval((int) xxd-20, (int) yyd-10, 25, 25);
		g.setColor(Color.BLACK);
		g.drawString(getName(), (int) xxd-10, (int) yyd+10);
		
	}
	public void dibujarEnlaceNoDir (Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(getC());
		g.drawLine(getNi().getX(), getNi().getY(), getNf().getX(),getNf().getY());
	}
	public void dibujarEnlaceMedio (Graphics g, double mul) {
		
		super.paintComponent(g);
		g.setColor(getC());
		int nx=(int)Math.round(getNi().getXx()*mul);
		int ny=(int)Math.round(getNi().getYy()*mul);
		int fx=(int)Math.round(getNf().getXx()*mul);
		int fy=(int)Math.round(getNf().getYy()*mul);
		g.drawLine(nx, ny, fx,fy);
	}
	
	public void dibujarEnlaceDup (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		
		g.drawLine(getNi().getX()+20, getNi().getY()+20, getNf().getX()+20,getNf().getY()+20);
		g.fillOval(getNf().getX()+20, getNf().getY()+20, 25, 25);
		
		g.setColor(Color.yellow);
		g.drawString(getName(), getNf().getX()+29, getNf().getY()+37);
		
	}
	
	public void dibujarEnlaceMismo (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		
		g.drawOval(getNi().getX(), getNi().getY()-15, 30,30);
		g.fillOval(getNi().getX()+20, getNi().getY()+10, 25, 25);
		
		
		g.setColor(Color.yellow);
		g.drawString(getName(), getNi().getX()+28, getNi().getY()+28);
		
	}
	
}
