Gestión de Jugadores y Login en Java TAREA

Tarea: Sistema de Gestión de Jugadores y Login en Java -Código bien estructurado y comentado

Objetivo:

Crear una aplicación en Java que permita:

1.	Iniciar sesión con un nombre de usuario y contraseña.

2.	Ver la lista de jugadores de fútbol almacenados en una base de datos.

3.	Agregar nuevos jugadores.

4.	Buscar jugadores por nombre.

5.	Eliminar jugadores de la base de datos.

Pasos a seguir:

1.	Base de datos:

o	Crea una base de datos con dos tablas:

	Usuarios: id, usuario, contrasena.

	Jugadores: id, nombre, posicion, equipo, edad.

o	Utiliza MySQL, SQL Server, PostgreSQL, o PHPMyAdmin (puede ser local o en la nube).

2.	Pantalla de Login:

o	Crea una ventana en Java con dos campos: usuario y contraseña.

o	Al hacer login, verifica si el usuario existe en la base de datos.

	Si el usuario es válido, accede a la siguiente pantalla.

	Si no, muestra un mensaje de error.

3.	Pantalla Principal (Dashboard):

o	Una vez logueado, muestra una pantalla con un menú de 4 pestañas:

	Ver Jugadores: Muestra los jugadores en una tabla.

	Agregar Jugadores: Permite ingresar un nuevo jugador en la base de datos.

	Buscar Jugadores: Permite buscar jugadores por nombre.

	Eliminar Jugadores: Permite eliminar un jugador de la base de datos.

4.	Conexión a la Base de Datos (JDBC):

o	Utiliza JDBC para conectar Java con la base de datos y realizar operaciones de consulta, inserción, búsqueda y eliminación de datos.

5.	PRESENTACIÓN:

o	El código debe estar bien comentado.

o	La aplicación debe ser funcional, con una interfaz gráfica sencilla y fluida.

o	Enlace en git hub.
