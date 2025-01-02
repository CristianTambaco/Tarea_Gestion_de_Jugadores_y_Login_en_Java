import javax.swing.*; // Importa las clases necesarias Swing para la interfaz gráfica
import java.awt.event.*; // Importa las clases necesarias para manejar eventos (ActionListener)
import java.sql.*; // Importa las clases necesarias para la base de datos

public class EliminarJugadorFrame extends JFrame { // Define la clase EliminarJugadorFrame que extiende JFrame (ventana principal)
    // Declaración del campo de texto para el nombre, botón eliminar y una etiqueta para mostrar mensajes
    private JTextField txtNombre;
    private JButton btnEliminar;
    private JLabel lblMensaje;

    public EliminarJugadorFrame() { // Constructor de la clase EliminarJugadorFrame
        setTitle("Eliminar Jugador"); // Título de la ventana
        setSize(300, 200); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Etiqueta que pide ingresar el Nombre del jugador
        JLabel lblNombre = new JLabel("Nombre del Jugador:");

        // Crear el campo de texto, botón y etiqueta para mostrar mensajes
        txtNombre = new JTextField(15);
        btnEliminar = new JButton("Eliminar");
        lblMensaje = new JLabel(""); // Etiqueta de mensaje inicial vacía

        // Agregar un ActionListener al botón eliminar
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarJugador(); // Llama al metodo eliminarJugador()
            }
        });


        // Panel para organizar las etiquetas, campos de texto y botón
        JPanel panel = new JPanel();
        panel.add(lblNombre); // Añadir la etiqueta Nombre del Jugador
        panel.add(txtNombre); // Añadir el campo de texto Nombre del jugador
        panel.add(btnEliminar); // Añadir el botón eliminar
        panel.add(lblMensaje); // Añadir etiqueta para mostrar mensajes

        add(panel); // Añadir el panel con todos los componentes
    }

    // Metodo para eliminar un jugador de la base de datos
    private void eliminarJugador() {
        String nombre = txtNombre.getText(); // Obtener el nombre del jugador ingresado

        // Verificar que el campo de texto no esté vacío
        if (nombre.isEmpty()) {
            lblMensaje.setText("Por favor ingrese un nombre."); // Mostrar un mensaje si no hay un nombre ingresado
            return;
        }

        try (Connection conn = ConexionDB.conectar()) { // Establecer la conexión con la base de datos
            // Verificar si el jugador existe en la base de datos
            String sqlCheck = "SELECT * FROM Jugadores WHERE nombre = ?";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck); // Consulta SQL para buscar jugador
            psCheck.setString(1, nombre); // Establece parámetro del nombre
            ResultSet rs = psCheck.executeQuery(); // Ejecutar la consulta

            if (rs.next()) {
                // Si el jugador existe, eliminar de la base de datos
                String sqlDelete = "DELETE FROM Jugadores WHERE nombre = ?";
                PreparedStatement psDelete = conn.prepareStatement(sqlDelete); // Prepara la consulta SQL para eliminar jugador
                psDelete.setString(1, nombre); // Establece el parámetro nombre
                int rowsAffected = psDelete.executeUpdate(); // Ejecuta la eliminación

                if (rowsAffected > 0) {
                    lblMensaje.setText("Jugador eliminado con éxito."); // Mostrar un mensaje de éxito
                } else {
                    lblMensaje.setText("Error al eliminar jugador."); // Mostrar un mensaje de error si no se pudo eliminar
                }
            } else {
                lblMensaje.setText("No se encontró un jugador con ese nombre."); // Mostrar mensaje si el jugador no existe
            }

        } catch (SQLException e) { // Captura cualquier excepción de SQL
            e.printStackTrace(); // Imprime la excepción en la consola
            lblMensaje.setText("Error al eliminar jugador."); // Mostrar un mensaje de error en la interfaz
        }
    }

    // Metodo principal para iniciar la aplicación
    public static void main(String[] args) {
        // Visibilidad de la ventana
        SwingUtilities.invokeLater(() -> new EliminarJugadorFrame().setVisible(true));
    }
}