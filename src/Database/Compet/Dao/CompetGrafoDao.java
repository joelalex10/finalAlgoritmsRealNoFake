package Database.Compet.Dao;

import Database.Asignacion.ConexionAsignacion;
import Database.Compet.ConexionCompet;
import Database.Compet.Model.CompetGrafoModel;
import Database.Conexion;
import Database.Sorts.Model.SortModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import grafos.Enlace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<CompetGrafoModel> listGrafoCompet(){
        List<CompetGrafoModel> lista = new ArrayList<>();

        ResultSet resultado;
        conexion=new ConexionCompet();
        resultado=conexion.getQuery("SELECT * FROM grafo_compet");

        try {
            while(resultado.next()) {

                int id = resultado.getInt("id");
                String nombre = resultado.getString("NOMBRE");
                lista.add(new CompetGrafoModel(id,nombre));
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }


}
