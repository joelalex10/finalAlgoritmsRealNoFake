package Database.Compet.Dao;

import Database.Asignacion.ConexionAsignacion;
import Database.Compet.ConexionCompet;
import Database.Compet.Model.CompetGrafoModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import grafos.Enlace;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompetGrafoDao {

    static ResultSet resultado;
    static ConexionCompet conexion;
    static PreparedStatement ps;
    public static int insertEnlace(CompetGrafoModel grafo) {
        conexion=new ConexionCompet();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into grafo_compet (NOMBRE) VALUES (?)");
            ps.setString(1, grafo.getNombre());
            ps.executeUpdate();
            res = (int) ps.getLastInsertID();
            System.out.println("EL VALOR DEL ID INSERTADO ES: "+ps.getLastInsertID());
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

}
