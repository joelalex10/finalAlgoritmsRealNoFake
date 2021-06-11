package Database.Compet.Dao;

import Database.Compet.ConexionCompet;
import Database.Compet.Model.CompetEnlaceModel;
import Database.Compet.Model.CompetNodoModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<CompetEnlaceModel> getListEnlaceModelByIdGrafo(int pIdGrafo){
        List<CompetEnlaceModel> lista = new ArrayList<CompetEnlaceModel>();
        ResultSet resultado;
        conexion=new ConexionCompet();
        resultado=conexion.getQuery("SELECT * \n" +
                "FROM enlace_compet a\n" +
                "WHERE a.id_grafo ="+pIdGrafo);
        try {
            while(resultado.next()) {

                int idEnlace = resultado.getInt("id_enlace");
                int red = resultado.getInt("red");
                int green = resultado.getInt("green");
                int blue = resultado.getInt("blue");
                int idNodo1 = resultado.getInt("id_nodo_1");
                int idNodo2 = resultado.getInt("id_nodo_2");
                int idGrafo = resultado.getInt("id_grafo");
                CompetEnlaceModel enlaceModel = new CompetEnlaceModel(idEnlace,red,green,
                        blue,idNodo1,idNodo2,idGrafo);
                lista.add(enlaceModel);
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
