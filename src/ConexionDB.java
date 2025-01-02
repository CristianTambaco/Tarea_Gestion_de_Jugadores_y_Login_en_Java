import java.sql.Connection; // Importa la clase Connection de JDBC, que permite conectar a la base de datos.
import java.sql.DriverManager; // Importa DriverManager, que maneja la conexión a la base de datos.
import java.sql.SQLException; // Importa SQLException para manejar excepciones relacionadas con la base de datos.

public class ConexionDB { // Clase que gestiona la conexión a la base de datos MySQL.
    // URL de la base de datos, con la dirección IP, el puerto y el nombre de la base de datos.
    private static final String URL = "jdbc:mysql://127.0.0.1:3307/futbol_db";

    // Usuario de MySQL que se utilizará para la conexión
    private static final String USER = "root";  // Usuario de MySQL

    // Contraseña del usuario de MySQL que se utilizará para la conexión
    private static final String PASSWORD = "123456";  // Contraseña de MySQL

    // Metodo que establece la conexión con la base de datos.
    public static Connection conectar() {
        try {
            // Intenta establecer la conexión con la base de datos.
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn; // Si la conexión es exitosa, devuelve conn.
        } catch (SQLException e) { // Si ocurre una excepción como una error de conexión
            e.printStackTrace(); // Imprime el error en la consola.
            return null; // Devuelve null si la conexión falla.
        }
    }
}
