
package windows;

  /**
 *
 * @author ploks
 */

import grafoArboles.Arbol;
import grafoArboles.NodoArbol;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class ArbolExpresionGrafico extends JPanel 
{
    private Arbol miArbol;
    private HashMap posicionNodos = null;
    private HashMap subtreeSizes = null;
    private boolean dirty = true;
    private int parent2child = 20, child2child = 30;
    private Dimension empty = new Dimension(0,0);
    private FontMetrics fm = null;
    
    
    /**
     * Constructor de la clase ArbolExpresionGrafico.
     * El constructor permite inicializar los atributos de la clase ArbolExpresionGrafico
     * y llama al método repaint(), que es el encargado de pintar el Arbol.
     * @parammiExpresion: dato de tipo ArbolExpresion que contiene el Arbol a
     * dibujar.
     */
    public ArbolExpresionGrafico(Arbol miArbol)
    {
          this.miArbol = miArbol;
          this.setBackground(new Color(66,68,65));
          posicionNodos = new HashMap();
          subtreeSizes = new HashMap();
          dirty = true;
          repaint();      
    }


    /**
     * Calcula las posiciones de los respectivos subárboles y de cada nodo que 
     * forma parte de ese subárbol, para conocer en que posición van a ir dibujados
     * los rectángulos representativos del árbol de la expresión.
     */
    private void calcularPosiciones() 
    {
         posicionNodos.clear();
         subtreeSizes.clear();
         NodoArbol root = this.miArbol.getRaiz();
         if (root != null) 
         {
             calcularTamanoSubarbol(root);
             calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    
    /**
     * Calcula el tama�o de cada subárbol y lo agrega al objeto subtreeSizes de la clase
     * de tipo HashMap que va a contener la coleccion de todos los 
     * subárboles que contiene un arbol.
     * @param n:Objeto de la clase NodoB <T> que se utiliza como
     * referencia calcular el tama�o de cada subárbol.
     * @return Dimension con el tama�o de cada subárbol.
     */
    private Dimension calcularTamanoSubarbol(NodoArbol n)
    {
          if (n == null) 
              return new Dimension(0,0);
 
          Dimension ld = calcularTamanoSubarbol(n.getIzq());
          Dimension rd = calcularTamanoSubarbol(n.getDer());
          
          int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
          int w = ld.width + child2child + rd.width;
          
          Dimension d = new Dimension(w, h);
          subtreeSizes.put(n, d);
          
          return d;
    }
    
    
    /**
     * Calcula la ubicación de cada nodo de cada subárbol y agrega cada nodo con 
     * un objeto de tipo Rectangule que tiene la ubicación y la información específica de dónde 
     * va a ser dibujado.
     * @param n: Objeto de tipo NodoB <T> que se utiliza como
     * referencia para calcular la ubicación de cada nodo.
     * @param left: int con alineación y orientación a la izquierda.
     * @param right: int con alineación y orientación a la derecha.
     * @param top: int con el tope.
     */
    private void calcularPosicion(NodoArbol n, int left, int right, int top)
    {
      if (n == null) 
          return;
      
      Dimension ld = (Dimension) subtreeSizes.get(n.getIzq());
      if (ld == null) 
          ld = empty;
      
      Dimension rd = (Dimension) subtreeSizes.get(n.getDer());
      if (rd == null) 
          rd = empty;
      
      int center = 0;
      
      if (right != Integer.MAX_VALUE)
          center = right - rd.width - child2child/2;
      else if (left != Integer.MAX_VALUE)
          center = left + ld.width + child2child/2;
      int width = fm.stringWidth(n.getDato()+"");
 
      posicionNodos.put(n,new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight()));
      
      calcularPosicion(n.getIzq(), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
      calcularPosicion(n.getDer(), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
    }
    
    /**
     * Dibuja el árbol teniendo en cuenta las ubicaciones de los nodos y los 
     * subárboles calculadas anteriormente.
     * @param g: Objeto de la clase Graphics2D que permite realizar el dibujo de las líneas, rectangulos y del String de la información que contiene el Nodo.
     * @param n: Objeto de la clase NodoB <T> que se utiliza como referencia para dibujar el árbol.
     * @param puntox: int con la posición en x desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param puntoy: int con la posición en y desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param yoffs: int con la altura del FontMetrics.
     */
    private void dibujarArbol(Graphics2D g, NodoArbol n, int puntox, int puntoy, int yoffs)
    {
     if (n == null)
         return;

     int d = 20;
     int widht = 13;
     int height = 16;
     Rectangle r = (Rectangle) posicionNodos.get(n);

        //g.draw(r);
     g.setColor(new Color(9,11,48));
     g.fillOval(r.x, r.y, d*(2), d);
     g.setColor(Color.white);
     g.drawString(n.getDato()+"", r.x+widht, r.y + yoffs);
     n.setX(r.x);
     n.setY(r.x);
     if (puntox != Integer.MAX_VALUE)

     g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y);

     dibujarArbol(g, n.getIzq(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     dibujarArbol(g, n.getDer(), (int)(r.x + r.width/2), r.y + r.height, yoffs);

     for(int i=0;i<posicionNodos.size();i++){
         System.out.println(posicionNodos.get(n));
     }
     
   }
    

   /**
     * Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
     * @param g: Objeto de la clase Graphics.
     */
   public void paint(Graphics g) 
   {
         super.paint(g);
         fm = g.getFontMetrics();

         if (dirty) 
         {
           calcularPosiciones();
           dirty = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(getWidth() / 2, parent2child);
         dibujarArbol(g2d, this.miArbol.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, 
                  fm.getLeading() + fm.getAscent());
         fm = null;
   }
   
 }




