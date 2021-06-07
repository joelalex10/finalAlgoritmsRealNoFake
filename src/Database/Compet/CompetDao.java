package Database.Compet;

import Database.Conexion;
import Database.Sorts.Model.SortModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompetDao {

    static ResultSet resultado;
    static Conexion conexion;
    static PreparedStatement ps;

    public static int insertCompet(CompetModel compet) {
        Conexion conexion=new Conexion();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into compet (compet_text,nombre) VALUES (?,?)");
            ps.setString(1, compet.getCadena());
            ps.setString(2, compet.getNombre());
            res = ps.executeUpdate();
            System.out.println("EL ID DEL SORT ES: "+ps.getLastInsertID());
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static List<CompetModel> getListCompet(){

        List<CompetModel> lista = new ArrayList<CompetModel>();


        ResultSet resultado;
        Conexion conexion=new Conexion();
        resultado=conexion.getQuery("SELECT * FROM compet");

        try {
            while(resultado.next()) {

                int idSort = resultado.getInt("id");
                String sortContent = resultado.getString("compet_text");
                String name = resultado.getString("nombre");

                CompetModel compet = new CompetModel();
                compet.setIdCompet(idSort);
                compet.setCadena(sortContent);
                compet.setNombre(name);
                lista.add(compet);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
