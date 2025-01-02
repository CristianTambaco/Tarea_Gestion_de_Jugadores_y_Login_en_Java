import javax.swing.*; // Importa las clases Swing para la interfaz gráfica
import java.awt.*; // Importa las clases para el manejo de disposición de componentes
import java.awt.event.*; // Importa las clases para manejar eventos, como ActionListener

public class DashboardFrame extends JFrame { // Define la clase DashboardFrame, que extiende JFrame (ventana principal)
    // Declaración de botones que estarán en el dashboard
    private JButton btnVerJugadores, btnAgregarJugador, btnBuscarJugador, btnEliminarJugador;

    public DashboardFrame() { // Constructor de la clase DashboardFrame
        setTitle("Dashboard"); // Título de la ventana
        setSize(400, 300); // Establece el tamaño de la ventana (400x300 píxeles)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Al cerrar la ventana se termine la aplicación
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Creación de botones con textos
        btnVerJugadores = new JButton("Ver Jugadores");
        btnAgregarJugador = new JButton("Agregar Jugador");
        btnBuscarJugador = new JButton("Buscar Jugador");
        btnEliminarJugador = new JButton("Eliminar Jugador");

        // Acción para el botón 'Ver Jugadores'
        btnVerJugadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana para ver jugadores al hacer clic en el botón
                new VerJugadoresFrame().setVisible(true);
            }
        });

        // Acción para el botón 'Agregar Jugador'
        btnAgregarJugador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana para agregar un jugador al hacer clic en el botón
                new AgregarJugadorFrame().setVisible(true);
            }
        });

        // Acción para el botón 'Buscar Jugador'
        btnBuscarJugador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana para buscar un jugador al hacer clic en el botón
                new BuscarJugadorFrame().setVisible(true);
            }
        });

        // Acción para el botón 'Eliminar Jugador'
        btnEliminarJugador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana para eliminar un jugador al hacer clic en el botón
                new EliminarJugadorFrame().setVisible(true);
            }
        });

        // Creación de un panel con un diseño de cuadrícula (GridLayout) que tendrá 4 filas y 1 columna
        JPanel panel = new JPanel(new GridLayout(4, 1));

        // Añade los botones al panel uno por uno en cada fila
        panel.add(btnVerJugadores);
        panel.add(btnAgregarJugador);
        panel.add(btnBuscarJugador);
        panel.add(btnEliminarJugador);

        // Añade el panel a la ventana principal (JFrame)
        add(panel);
    }

    // Metodo principal que se ejecuta al iniciar la aplicación
    public static void main(String[] args) {
        // Visibilidad de la ventana
        SwingUtilities.invokeLater(() -> new DashboardFrame().setVisible(true));
    }
}
