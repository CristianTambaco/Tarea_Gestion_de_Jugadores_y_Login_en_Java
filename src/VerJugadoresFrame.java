import javax.swing.*; // Importa las clases Swing para la interfaz gráfica
import java.sql.*; // Importa las clases necesarias para la base de datos
import javax.swing.JTable; // Importa la clase JTable para mostrar datos en una tabla
import javax.swing.table.DefaultTableModel; // Importa el modelo de tabla para trabajar con tablas personalizadas


public class VerJugadoresFrame extends JFrame { // Define la clase VerJugadoresFrame que extiende JFrame o ventana principal
    private JTable tblJugadores; // Declaración de la tabla para mostrar los jugadores

    public VerJugadoresFrame() { // Constructor de la clase VerJugadoresFrame
        setTitle("Ver Jugadores"); // El título de la ventana
        setSize(500, 300); // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Se cierra solo la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla


        // Definición de los nombres de las columnas de la tabla
        String[] columnNames = {"ID", "Nombre", "Posición", "Equipo", "Edad"};
        // Crea un modelo de tabla con las columnas definidas y sin filas
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        // Crea una nueva tabla con el modelo de datos
        tblJugadores = new JTable(model);


        // Conexión a la base de datos para obtener datos de los jugadores
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "SELECT * FROM Jugadores"; // Consulta SQL para seleccionar todos los jugadores
            PreparedStatement ps = conn.prepareStatement(sql); // Prepara la consulta
            ResultSet rs = ps.executeQuery(); // Ejecuta la consulta y obtiene los resultados


            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"), // ID del jugador
                        rs.getString("nombre"), // Nombre del jugador
                        rs.getString("posicion"), // Posición del jugador
                        rs.getString("equipo"), // Equipo del jugador
                        rs.getInt("edad") // Edad del jugador
                });
            }
        } catch (SQLException e) { // Captura cualquier excepción de SQL que pueda ocurrir
            e.printStackTrace(); // Imprime el error en la consola
        }

        // Crea un JScrollPane para hacer la tabla desplazable (scrollable)
        JScrollPane scrollPane = new JScrollPane(tblJugadores);
        // Añade el JScrollPane a la ventana principal
        add(scrollPane);
    }

    // Metodo principal que inicia la aplicación
    public static void main(String[] args) {
        // Visualización de la ventana
        SwingUtilities.invokeLater(() -> new VerJugadoresFrame().setVisible(true));
    }
}