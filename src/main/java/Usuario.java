import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Usuario {
    private static java.sql.Connection con;
    private static final Scanner scanner = new Scanner(System.in);

    public static void crearUsuario() throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Introduce los datos del usuario a crear separados por comas");
        System.out.println("(id,nombre,apellidos)");
        String[] datos = scanner.nextLine().split(",");
        crearUsuarioSQL(datos);
    }

    public static void crearUsuarioSQL(String[] datos) throws SQLException {
        PreparedStatement st;
        String sql = "INSERT INTO usuarios (id,nombre,apellidos) VALUES (?,?,?)";
        st = con.prepareStatement(sql);
        st.setInt(1, Integer.parseInt(datos[0]));
        st.setString(2, datos[1]);
        st.setString(3, datos[2]);
        st.executeUpdate();
        System.out.println("Usuario creado correctamente");
        System.out.println();
    }

    public static void verUsuarios() throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Lista de usuarios: ");
        verUsuariosSQL();
    }

    public static void verUsuariosSQL() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) + "\t" + "NAME: " + rs.getString(2) + "\t" + "LASTNAME: " + rs.getString(3));
        }
        System.out.println();
    }

    public static void eliminarUsuarios() throws SQLException{
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Introduzca el ID del usuario a eliminar: ");
        int id = scanner.nextInt();
        eliminarUsuariosSQL(id);
    }
    public static void eliminarUsuariosSQL(int id) throws SQLException{
        PreparedStatement st;
        String sql = "DELETE FROM usuarios WHERE id = ?";
        st = con.prepareStatement(sql);
        st.setInt(1,id);
        st.executeUpdate();
        System.out.println("Usuario elimnado correctamente");
        System.out.println();
    }
    public static void modificarUsuario() throws SQLException {
        String host = "jdbc:sqlite:src/main/resources/network.bd";
        con = java.sql.DriverManager.getConnection(host);
        System.out.println("Introduce el ID del usuario a modificar y sus nuevos datos");
        System.out.println("(id,nombre,apellidos)");
        String[] datos = scanner.nextLine().split(",");
        modificarUsuarioSQL(datos);
    }

    public static void modificarUsuarioSQL(String[] datos) throws SQLException {
        PreparedStatement st;
        String sql = "UPDATE usuarios SET nombre = ?, apellidos = ? where id = ?";
        st = con.prepareStatement(sql);
        st.setString(1, datos[1]);
        st.setString(2, datos[2]);
        st.setInt(3, Integer.parseInt(datos[0]));
        st.executeUpdate();
        System.out.println("Usuario actualizado correctamente");
        System.out.println();
    }
}
