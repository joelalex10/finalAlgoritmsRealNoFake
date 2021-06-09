package Database.Compet.Dao;

import Database.Compet.ConexionCompet;
import Database.Compet.Model.CompetEnlaceModel;
import Database.Compet.Model.CompetNodoModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompetEnlaceDao {

    static ResultSet resultado;
    static ConexionCompet conexion;
    static PreparedStatement ps;
    public static int insertEnlace(CompetEnlaceModel enlaceModel) {
        System.out.println(enlaceModel);
        conexion=new ConexionCompet();
        PreparedStatement ps;
        int res = 0;
        Connection con = null;
        try {
            con = conexion.getConexionMYSQL();
            ps = (PreparedStatement) con.prepareStatement("insert into enlace_compet (red,green,blue,id_nodo_1,id_nodo_2,id_grafo) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, enlaceModel.getRed());
            ps.setInt(2, enlaceModel.getGreen());
            ps.setInt(3, enlaceModel.getBlue());
            ps.setInt(4, enlaceModel.getIdNodo1());
            ps.setInt(5, enlaceModel.getIdNodo2());
            ps.setInt(6, enlaceModel.getIdGrafo());
            res = ps.executeUpdate();
            System.out.println("EL VALOR DEL ID INSERTADO ES: "+ps.getLastInsertID());
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
