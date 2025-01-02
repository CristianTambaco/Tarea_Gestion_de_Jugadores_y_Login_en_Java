import javax.swing.*; // Importa las clases Swing para la interfaz gráfica
import java.awt.event.*; // Importa las clases necesarias para manejar eventos (ActionListener)
import java.sql.*; // Importa las clases necesarias para la base de datos


public class AgregarJugadorFrame extends JFrame { // Define la clase AgregarJugadorFrame que extiende JFrame
    // Declaración de campos de texto para ingresar datos del jugador
    private JTextField txtNombre, txtPosicion, txtEquipo, txtEdad;
    // Declaración de botón para agregar jugador
    private JButton btnAgregar;

    public AgregarJugadorFrame() { // Constructor de la clase AgregarJugadorFrame
        setTitle("Agregar Jugador"); // Título de la ventana
        setSize(250, 300); // Tamaño de la ventana (250x300 píxeles)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Campo de entrada
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblPosicion = new JLabel("Posición:");
        JLabel lblEquipo = new JLabel("Equipo:");
        JLabel lblEdad = new JLabel("Edad:");

        // Campos de texto para ingresar los datos del jugador
        txtNombre = new JTextField(15);
        txtPosicion = new JTextField(15);
        txtEquipo = new JTextField(15);
        txtEdad = new JTextField(5);
        // Botón para agregar jugador
        btnAgregar = new JButton("Agregar");

        // Agregar una accion al botón
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarJugador(); // Llama al metodo agregarJugador() para guardar el jugador en la base de datos
            }
        });

        // Crear un panel para organizar las etiquetas, campos de texto y botón)
        JPanel panel = new JPanel();
        panel.add(lblNombre); // Añadir la etiqueta Nombre
        panel.add(txtNombre); // Añadir el campo de texto Nombre
        panel.add(lblPosicion); // Añadir la etiqueta Posición
        panel.add(txtPosicion); // Añadir el campo de texto Posición
        panel.add(lblEquipo); // Añadir la etiqueta Equipo
        panel.add(txtEquipo); // Añadir el campo de texto Equipo
        panel.add(lblEdad); // Añadir la etiqueta Edad
        panel.add(txtEdad); // Añadir el campo de texto Edad
        panel.add(btnAgregar); // Añadir el botón para agregar jugador

        add(panel); // Añadir el panel con todos los componentes a la ventana
    }

    // Metodo para agregar un jugador a la base de datos
    private void agregarJugador() {
        // Obtener los datos de los campos de texto
        String nombre = txtNombre.getText();
        String posicion = txtPosicion.getText();
        String equipo = txtEquipo.getText();
        int edad = Integer.parseInt(txtEdad.getText()); // Convertir la edad de String a int

        // Intentar agregar el jugador a la base de datos
        try (Connection conn = ConexionDB.conectar()) { // Establecer conexión con la base de datos
            // Consulta SQL para insertar un nuevo jugador
            String sql = "INSERT INTO Jugadores (nombre, posicion, equipo, edad) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql); // Preparar la consulta SQL
            // Establecer los valores de la consulta usando los datos ingresados por el usuario
            ps.setString(1, nombre);
            ps.setString(2, posicion);
            ps.setString(3, equipo);
            ps.setInt(4, edad);

            // Ejecutar la consulta para insertar en la base de datos
            int rows = ps.executeUpdate();
            if (rows > 0) {
                // Mostrar un mensaje
                JOptionPane.showMessageDialog(this, "Jugador agregado con éxito");
            }
        } catch (SQLException e) { // Captura cualquier excepción de SQL
            e.printStackTrace(); // Imprime la excepción en la consola
        }
    }

    // Metodo principal para iniciar
    public static void main(String[] args) {
        //Visibilidad de la ventana
        SwingUtilities.invokeLater(() -> new AgregarJugadorFrame().setVisible(true));
    }
}