import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Post {
    private static java.sql.Connection con;
    private static final Scanner scanner = new Scanner(System.in);

    public static void crearPost() throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Introduce los datos de la publicacion a crear separados por comas");
        System.out.println("(id,texto,likes,fecha,id_usuario)");
        String[] datos = scanner.nextLine().split(",");
        crearPostSQL(datos);
    }

    public static void crearPostSQL(String[] datos) throws SQLException {
        PreparedStatement st;
        String sql = "INSERT INTO posts (id,texto,likes,fecha,id_usuario) VALUES (?,?,?,?,?)";
        st = con.prepareStatement(sql);
        st.setInt(1, Integer.parseInt(datos[0]));
        st.setString(2, datos[1]);
        st.setInt(3, Integer.parseInt(datos[2]));
        st.setString(4, datos[3]);
        st.setInt(5, Integer.parseInt(datos[4]));
        st.executeUpdate();
        System.out.println("Publicacion creada correctamente");
        System.out.println();
    }

    public static void verPosts() throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Lista de publicaciones: ");
        verPostsSQL();
    }

    public static void verPostsSQL() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM posts");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) + "\t" +
                    "TEXTO: " + rs.getString(2) + "\t" +
                    "LIKES: " + rs.getInt(3) + "\t" +
                    "FECHA: " +  rs.getString(4) + "\t" +
                    "ID_USUARIO: " + rs.getInt(5) + "\t");
        }
        System.out.println();
    }
    public static void eliminarPosts() throws SQLException{
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Introduzca el ID de la publicacion a eliminar: ");
        int id = scanner.nextInt();
        eliminarPostsSQL(id);
    }
    public static void eliminarPostsSQL(int id) throws SQLException{
        PreparedStatement st;
        String sql = "DELETE FROM posts WHERE id = ?";
        st = con.prepareStatement(sql);
        st.setInt(1,id);
        st.executeUpdate();
        System.out.println("Publicacion elimnada correctamente");
    }
    public static void modificarPost() throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Introduce el ID de la publicacion a modificar y sus nuevos datos");
        System.out.println("(id,texto,likes,fecha,id_usuario)");
        String[] datos = scanner.nextLine().split(",");
        modificarPostSQL(datos);
    }

    public static void modificarPostSQL(String[] datos) throws SQLException {
        PreparedStatement st;
        String sql = "UPDATE posts SET texto = ?, likes = ?, fecha = ?, id_usuario = ? where id = ?";
        st = con.prepareStatement(sql);
        st.setString(1, datos[1]);
        st.setInt(2, Integer.parseInt(datos[2]));
        st.setString(3, datos[3]);
        st.setInt(4, Integer.parseInt(datos[4]));
        st.setInt(5, Integer.parseInt(datos[0]));
        st.executeUpdate();
        System.out.println("Publicacion actualizada correctamente");
        System.out.println();
    }
}
