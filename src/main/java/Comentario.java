import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Comentario {
    private static java.sql.Connection con;
    private static final Scanner scanner = new Scanner(System.in);

    public static void crearComentario() throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Introduce los datos del comentario a crear separados por comas");
        System.out.println("(id,texto,fecha,id_usuario,id_post)");
        String[] datos = scanner.nextLine().split(",");
        crearComentarioSQL(datos);
    }

    public static void crearComentarioSQL(String[] datos) throws SQLException {
        PreparedStatement st;
        String sql = "INSERT INTO comentarios (id,texto,fecha,id_usuario,id_post) VALUES (?,?,?,?,?)";
        st = con.prepareStatement(sql);
        st.setInt(1, Integer.parseInt(datos[0]));
        st.setString(2, datos[1]);
        st.setString(3, datos[2]);
        st.setInt(4, Integer.parseInt(datos[3]));
        st.setInt(5, Integer.parseInt(datos[4]));
        st.executeUpdate();
        System.out.println("Comentario creado correctamente");
        System.out.println();
    }
    public static void verComentarios() throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Lista de comentarios: ");
        verComentariosSQL();
    }

    public static void verComentariosSQL() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM comentarios");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) + "\t" +
                    "TEXTO: " + rs.getString(2) + "\t" +
                    "FECHA: " +  rs.getString(3) + "\t" +
                    "ID_USUARIO: " + rs.getInt(4) + "\t" +
                    "ID_POST: " + rs.getInt(5) + "\t");

        }
        System.out.println();
    }
    public static void eliminarComentarios() throws SQLException{
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Introduzca el ID del comentario a eliminar: ");
        int id = scanner.nextInt();
        eliminarComentariosSQL(id);
    }
    public static void eliminarComentariosSQL(int id) throws SQLException{
        PreparedStatement st;
        String sql = "DELETE FROM comentarios WHERE id = ?";
        st = con.prepareStatement(sql);
        st.setInt(1,id);
        st.executeUpdate();
        System.out.println("Comentario elimnado correctamente");
    }
    public static void modificarComentario() throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Introduce el ID del comentario a modificar y sus nuevos datos");
        System.out.println("(id,texto,fecha,id_usuario,id_post)");
        String[] datos = scanner.nextLine().split(",");
        modificarComentarioSQL(datos);
    }

    public static void modificarComentarioSQL(String[] datos) throws SQLException {
        PreparedStatement st;
        String sql = "UPDATE comentarios SET texto = ?, fecha = ?, id_usuario = ?, id_post = ? where id = ?";
        st = con.prepareStatement(sql);
        st.setString(1, datos[1]);
        st.setString(2, datos[2]);
        st.setInt(3, Integer.parseInt(datos[3]));
        st.setInt(4, Integer.parseInt(datos[4]));
        st.setInt(5, Integer.parseInt(datos[0]));
        st.executeUpdate();
        System.out.println("Comentario actualizado correctamente");
        System.out.println();
    }
}
