package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EnlaceBDD {
	public int idEnlace;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int atributo;
	private int thickness;
	private int nodoInicio;
	private int nodoFin;
	private boolean ciclo;
	private int colorRed;
	private int colorGreen;
	private int colorBlue;
	private int grafoId;
	private int textDirection;
	private int nroActividadNodoInicio;
	private int nroActividadNodoFin;
	
	public EnlaceBDD() {
		
	}

	public EnlaceBDD(int idEnlace, int x1, int y1, int x2, int y2, int atributo, int thickness, int nodoInicio,
			int nodoFin, boolean ciclo, int colorRed,
			int colorGreen, int colorBlue, int grafoId, int textDirection,  int nroActividadNodoInicio, int nroActividadNodoFin) {
		super();
		this.idEnlace = idEnlace;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.atributo = atributo;
		this.thickness = thickness;
		this.nodoInicio = nodoInicio;
		this.nodoFin = nodoFin;
		this.ciclo = ciclo;
		this.colorRed = colorRed;
		this.colorGreen = colorGreen;
		this.colorBlue = colorBlue;
		this.grafoId = grafoId;
		this.textDirection = textDirection;
		this.nroActividadNodoInicio = nroActividadNodoInicio;
		this.nroActividadNodoFin = nroActividadNodoFin;
	}
	
	

	public int getIdEnlace() {
		return idEnlace;
	}

	public void setIdEnlace(int idEnlace) {
		this.idEnlace = idEnlace;
	}

	public int getTextDirection() {
		return textDirection;
	}

	public void setTextDirection(int textDirection) {
		this.textDirection = textDirection;
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
	public int getAtributo() {
		return atributo;
	}
	public void setAtributo(int atributo) {
		this.atributo = atributo;
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	public int getNodoInicio() {
		return nodoInicio;
	}
	public void setNodoInicio(int nodoInicio) {
		this.nodoInicio = nodoInicio;
	}
	public int getNodoFin() {
		return nodoFin;
	}
	public void setNodoFin(int nodoFin) {
		this.nodoFin = nodoFin;
	}
	public boolean isCiclo() {
		return ciclo;
	}
	public void setCiclo(boolean ciclo) {
		this.ciclo = ciclo;
	}
	public int getColorRed() {
		return colorRed;
	}
	public void setColorRed(int colorRed) {
		this.colorRed = colorRed;
	}
	public int getColorGreen() {
		return colorGreen;
	}
	public void setColorGreen(int colorGreen) {
		this.colorGreen = colorGreen;
	}
	public int getColorBlue() {
		return colorBlue;
	}
	public void setColorBlue(int colorBlue) {
		this.colorBlue = colorBlue;
	}

	public int getNroActividadNodoInicio() {
		return nroActividadNodoInicio;
	}

	public void setNroActividadNodoInicio(int nroActividadNodoInicio) {
		this.nroActividadNodoInicio = nroActividadNodoInicio;
	}

	public int getNroActividadNodoFin() {
		return nroActividadNodoFin;
	}

	public void setNroActividadNodoFin(int nroActividadNodoFin) {
		this.nroActividadNodoFin = nroActividadNodoFin;
	}

	public int getGrafoId() {
		return grafoId;
	}
	public void setGrafoId(int grafoId) {
		this.grafoId = grafoId;
	}
	public int insertEnlace() {
		Conexion conexion=new Conexion();
		PreparedStatement ps;
		int res = 0;
		Connection con = null;
		try {
			con = conexion.getConexionMYSQL();
			ps = (PreparedStatement) con.prepareStatement("insert into enlace (x1,y1,x2,y2,atributo,thickness,nodo_inicio,nodo_fin,ciclo,color_red,color_green,color_blue,grafo_id,text_direction,nroActividadNodoInicio,nroActividadNodoFin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, this.getX1());
			ps.setInt(2, this.getY1());
			ps.setInt(3, this.getX2());
			ps.setInt(4, this.getY2());
			ps.setInt(5, this.getAtributo());
			ps.setInt(6, this.getThickness());
			ps.setInt(7, this.getNodoInicio());
			ps.setInt(8, this.getNodoFin());
			ps.setBoolean(9, this.isCiclo());
			ps.setInt(10, this.getColorRed());
			ps.setInt(11, this.getColorGreen());
			ps.setInt(12, this.getColorBlue());
			ps.setInt(13, this.getGrafoId());
			ps.setInt(14, this.getTextDirection());
			ps.setInt(15, this.getNroActividadNodoInicio());
			ps.setInt(16,this.getNroActividadNodoFin());
			res = ps.executeUpdate();
			System.out.println("EL VALOR DE RES ES: "+ps.getLastInsertID());
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return res;
	}
	@Override
	public String toString() {
		return "EnlaceBDD [x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + ", atributo=" + atributo
				+ ", thickness=" + thickness + ", nodoInicio=" + nodoInicio + ", nodoFin=" + nodoFin + ", ciclo="
				+ ciclo + ", colorRed=" + colorRed + ", colorGreen=" + colorGreen + ", colorBlue=" + colorBlue + "]";
	}
	
	public ArrayList<EnlaceBDD> getEnlaceByGrafoId(){
		ArrayList<EnlaceBDD> newListEnlaceBDD= new ArrayList<EnlaceBDD>();
		newListEnlaceBDD.clear();
		Conexion conexion=new Conexion();
		ResultSet resultado = conexion.getQuery("select *\r\n"
				+ "from enlace a\r\n"
				+ "WHERE a.grafo_id = "+this.getGrafoId());
		
		try {
			while (resultado.next()) {
				
				int newIdEnlace = resultado.getInt("id_grafo");
				int newX1 = resultado.getInt("x1");
				int newX2 = resultado.getInt("x2");
				int newY1 = resultado.getInt("y1");
				int newY2 = resultado.getInt("y2");
				int newAtributo = resultado.getInt("atributo");
				int newThickness = resultado.getInt("thickness");
				int newNodo_inicio = resultado.getInt("nodo_inicio");
				int newNodo_fin = resultado.getInt("nodo_fin");
				boolean newCiclo = resultado.getBoolean("ciclo");
				int newColorRed = resultado.getInt("color_red");
				int newColorGreen = resultado.getInt("color_green");
				int newColorBlue = resultado.getInt("color_blue");
				int newGrafoId = resultado.getInt("grafo_id");
				int newTextDirection = resultado.getInt("text_direction");
				int newNroActividadNodoInicio = resultado.getInt("nroActividadNodoInicio");
				int newNroActividadNodoFin = resultado.getInt("nroActividadNodoFin");
				//System.out.println("LA VARIABLE ES: "+resultado.getInt("nroActividadNodoInicio"));
				newListEnlaceBDD.add(new EnlaceBDD(newIdEnlace, newX1, newY1, newX2,
						newY2, newAtributo, newThickness, newNodo_inicio, newNodo_fin,
						newCiclo, newColorRed, newColorGreen, newColorBlue, newGrafoId,
						newTextDirection, newNroActividadNodoInicio,newNroActividadNodoFin));
				
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return newListEnlaceBDD;
	}
	
	
	
	
	

}
