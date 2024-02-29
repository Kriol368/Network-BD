import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int opcion;
        menu:
        do {
            imprimirMenu();
            opcion = scanner.nextInt();
            switch (opcion){
                case 1:
                    Usuario.crearUsuario();
                    break;
                case 2:
                    Usuario.verUsuarios();
                    break;
                case 3:
                    Usuario.modificarUsuario();
                    break;
                case 4:
                    Usuario.eliminarUsuarios();
                    break;
                case 5:
                    Post.crearPost();
                    break;
                case 6:
                    Post.verPosts();
                    break;
                case 7:
                    Post.modificarPost();
                    break;
                case 8:
                    Post.eliminarPosts();
                    break;
                case 9:
                    Comentario.crearComentario();
                    break;
                case 10:
                    Comentario.verComentarios();
                    break;
                case 11:
                    Comentario.modificarComentario();
                    break;
                case 12:
                    Comentario.eliminarComentarios();
                    break;
                case 13:
                    break menu;
            }
        }while (true);
    }

    public static void imprimirMenu(){
        System.out.println("""
                Seleccione una opcion:\s
                1-Crear usuarios
                2-Ver usuarios
                3-Actualizar informacion de usuarios
                4-Borrar usuarios
                5-Crear publicacion
                6-Ver publicaciones
                7-Actualizar informacion de publicaciones
                8-Borrar publicaciones
                9-Crear comentarios
                10-Ver comentarios
                11-Actualizar informacion de comentarios
                12-Borrar comentarios
                13-Finalizar programa
                """);
    }
}
