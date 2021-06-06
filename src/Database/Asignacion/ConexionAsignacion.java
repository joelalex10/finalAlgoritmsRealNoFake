package Database.Asignacion;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionAsignacion {
    private String usuario="root";
    private String pass="";
    private String host="localhost";
    private String nombre_BD="grafo_asignacion";
    private Connection con;
    private PreparedStatement ps;

    public Connection getConexionMYSQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String servidor="jdbc:mysql://"+host+"/"+nombre_BD;
            con=(Connection) DriverManager.getConnection(servidor,usuario,pass);
            return con;
        }catch(Exception e) {
            e.printStackTrace();
            return con;
        }
    }
    public ResultSet getQuery(String _query) {
        Statement state=null;
        ResultSet resultado=null;
        try {
            state=(Statement)getConexionMYSQL().createStatement();
            resultado=state.executeQuery(_query);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public void setQuery(String _query) {
        Statement state=null;
        try {
            state=(Statement)getConexionMYSQL().createStatement();
            state.execute(_query);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
