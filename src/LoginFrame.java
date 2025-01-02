import javax.swing.*; // Importación de las clases Swing para la interfaz gráfica
import java.awt.event.*; // Importación de eventos, como ActionEvent
import java.sql.*; // Importación de clases para trabajar con bases de datos

public class LoginFrame extends JFrame {
    // Definición de componentes gráficos
    private JTextField txtUsuario; // Campo de texto para ingresar usuario
    private JPasswordField txtContrasenia; // Campo de texto para ingresar contraseña
    private JButton btnLogin; // Botón para iniciar sesión

    public LoginFrame() {
        // Configuración de la ventana principal
        setTitle("Pantalla de Login"); // Título de la ventana
        setSize(300, 200); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Configuración para cerrar la aplicación
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Creación de etiquetas label para campos de usuario y contraseña
        JLabel lblUsuario = new JLabel("Usuario:");
        JLabel lblContrasenia = new JLabel("Contraseña:");

        // Creación de los campos de texto y del botón
        txtUsuario = new JTextField(15); // Campo de texto para el usuario
        txtContrasenia = new JPasswordField(15); // Campo de texto para la contraseña
        btnLogin = new JButton("Iniciar Sesión"); // Botón para iniciar sesión

        // Acción que se ejecuta cuando se hace clic en el botón
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login(); // Llama al metodo login
            }
        });

        // Panel para organizar los componentes en la interfaz gráfica
        JPanel panel = new JPanel();
        // Se añaden los componentes al panel
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContrasenia);
        panel.add(txtContrasenia);
        panel.add(btnLogin);

        // Se añade el panel a la ventana principal (frame)
        add(panel);
    }

    // Metodo para gestiona el proceso de login
    private void login() {
        // Obtiene los valores ingresados por el usuario
        String usuario = txtUsuario.getText();
        String contrasenia = new String(txtContrasenia.getPassword());

        try (Connection conn = ConexionDB.conectar()) { // Conexión a la base de datos utilizando la clase ConexionDB
            // Consulta SQL para verificar si el usuario y la contraseña son correctos
            String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND contrasenia = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            // Se sustituyen los parámetros ? en la consulta por los valores proporcionados
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);

            // Ejecuta la consulta
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Si la consulta devuelve un resultado, el usuario y contraseña son correctos
                // Abre la ventana de Dashboard y cierra la ventana de login
                new DashboardFrame().setVisible(true);
                dispose(); // Cierra la ventana de login
            } else {
                // Si los datos son incorrectos, muestra un mensaje de error
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            // Si ocurre un error en la consulta o conexión con la base de datos, se muestra el error
            e.printStackTrace();
        }
    }

    // Metodo principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true)); // Ejecuta la ventana de login
    }
}
