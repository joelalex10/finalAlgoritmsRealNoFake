package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DataHabitacion {
	static Conexion conexion=new Conexion();
	static ResultSet resultado;
	static Statement estado;
	static PreparedStatement ps;
	int totalRegistros;
	public static void main(String args[]) throws SQLException {
		
		String titulos[]= {"Numero","Piso","Precio","estado","Tipo Habitacion"};
		
		String registro[]=new String[8];
		String nombre;
		int idGrafo;
		/*
		resultado=conexion.getQuery("select a.id_grafo as id, a.nombre\r\n"
				+ "from grafo a ");
		try {
			while(resultado.next()) {
				nombre=resultado.getString("nombre");
				idGrafo=resultado.getInt("id");
				System.out.println("numero: "+nombre);
				System.out.println("piso: "+idGrafo);
				System.out.println("---------------------------------------------");
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}*/
		GrafoBDD obj= new GrafoBDD();
		obj.setIdGrafo(3);
		obj.selectGrafobyId();
		
		
		GrafoBDD grafo = new GrafoBDD();
		grafo.setNombre("ASDGGTRGHBRTF");
		int iddGrafo = grafo.insertGrafo();
		System.out.println("El ultimo Id es: "+iddGrafo);
		
		
		
		/*Connection con = null;
		try {
			con = conexion.getConexionMYSQL();
			ps = (PreparedStatement) con.prepareStatement("insert into grafo (nombre) VALUES (?)");
			ps.setString(1,null);
			int res = ps.executeUpdate();
			System.out.println("EL VALOR DE RES ES: "+ps.getLastInsertID());
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}*/
		
	}
}
