package Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class InterfaceConexion {
	public static void main(String args[]) {
		Conexion con=new Conexion();
		Connection conexion=con.getConexionMYSQL();
		if(conexion==null) {
			System.out.println("Error en la conexion a la BD!!!!");
		}else {
			try {
				System.out.println("Conexion a la BD correcta!!!!");
				conexion.close();
			}catch(SQLException ex) {
				Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
	}
}
