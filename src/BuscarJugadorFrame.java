import javax.swing.*; // Importa las clases necesarias Swing para la interfaz gráfica
import java.awt.event.*; // Importa las clases necesarias para manejar eventos (ActionListener)
import java.sql.*; // Importa las clases necesarias para la base de datos

public class BuscarJugadorFrame extends JFrame { // Define la clase BuscarJugadorFrame que extiende JFrame (ventana principal)
    // Declaración de campos para el nombre del jugador, el botón búsqueda y el área de texto para mostrar los resultados
    private JTextField txtNombre;
    private JButton btnBuscar;
    private JTextArea txtAreaResultado;

    public BuscarJugadorFrame() { // Constructor de la clase BuscarJugadorFrame
        setTitle("Buscar Jugador"); // Título de la ventana
        setSize(500, 300); // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Etiqueta para el campo de texto Nombre del Jugador
        JLabel lblNombre = new JLabel("Nombre del Jugador:");

        // Campos de texto para ingresar el nombre del jugador y el área de texto para mostrar los resultados
        txtNombre = new JTextField(15);
        btnBuscar = new JButton("Buscar");
        txtAreaResultado = new JTextArea(10, 30); // Definir el tamaño del área de texto
        txtAreaResultado.setEditable(false); // Hacer que el área de texto no sea editable por el usuario

        // Agregar un ActionListener al botón de búsqueda
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarJugador(); // Llama al metodo buscarJugador() para realizar la búsqueda
            }
        });

        // Crear un panel para organizar las etiquetas, campos de texto y botón
        JPanel panel = new JPanel();
        panel.add(lblNombre); // Añadir etiqueta Nombre del Jugador
        panel.add(txtNombre); // Añadir campo de texto nombre del jugador
        panel.add(btnBuscar); // Añadir el botón de búsqueda
        panel.add(new JScrollPane(txtAreaResultado)); // Permitir desplazamiento en el area de texto

        add(panel); // Añadir panel con todos los componentes
    }

    // Metodo para buscar un jugador en la base de datos
    private void buscarJugador() {
        String nombre = txtNombre.getText(); // Obtener el nombre ingresado por el usuario

        try (Connection conn = ConexionDB.conectar()) { // Establecer conexión con la base de datos
            // Consulta SQL para buscar jugadores
            String sql = "SELECT * FROM Jugadores WHERE nombre LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql); // Preparar la consulta SQL
            ps.setString(1, "%" + nombre + "%"); // Usar LIKE para buscar nombres
            ResultSet rs = ps.executeQuery(); // Ejecuta la consulta y obtiene los resultados

            txtAreaResultado.setText(""); // Limpiar los resultados previos en el área de texto

            if (rs.next()) { // Si se encuentran resultados
                do {
                    // Información del jugador
                    String jugadorInfo = "ID: " + rs.getInt("id") + "\n" +
                            "Nombre: " + rs.getString("nombre") + "\n" +
                            "Posición: " + rs.getString("posicion") + "\n" +
                            "Equipo: " + rs.getString("equipo") + "\n" +
                            "Edad: " + rs.getInt("edad") + "\n\n";
                    txtAreaResultado.append(jugadorInfo); // Añadir la información del jugador al área de texto
                } while (rs.next());
            } else { // Enviar un mensaje que no se encuentran jugadores con ese nombre
                txtAreaResultado.setText("No se encontraron jugadores con ese nombre.");
            }

        } catch (SQLException e) { // Captura cualquier excepción de SQL
            e.printStackTrace(); // Imprime la excepción en la consola
            txtAreaResultado.setText("Error en la búsqueda."); // Mostrar un mensaje de error en el área de texto
        }
    }

    // Metodo principal para iniciar la aplicación
    public static void main(String[] args) {
        // Visibilidad de la ventana
        SwingUtilities.invokeLater(() -> new BuscarJugadorFrame().setVisible(true));
    }
}