# Proyecto CRM Concesionario - Tercer Trimestre

## Introducción

Este repositorio contiene el proyecto intermodular desarrollado durante el tercer trimestre del ciclo de Desarrollo de Aplicaciones Multiplataforma. 
El proyecto consiste en un sistema CRM orientado a la gestión de un concesionario de coches, integrando contenidos de Programación, Base de Datos, Lenguaje de Marcas
y Entornos de Desarrollo.

El sistema permite trabajar con las entidades principales de un concesionario: clientes, usuarios, coches, ventas, detalles de venta e interacciones comerciales.
A partir de estas entidades se han desarrollado distintas partes del proyecto, como la aplicación Java, la base de datos relacional, la interfaz web y la gestión
del repositorio mediante Git y GitHub.

Este README funciona como guía general del proyecto. En él se describen las tecnologías utilizadas, la instalación básica del entorno, la estructura del repositorio
y la ejecución de las partes principales.

## Tecnologías utilizadas

### Programación / Back-end

- Java
- JDK 21
- Maven
- JDBC
- IntelliJ IDEA

### Base de Datos

- MySQL 8.4
- MySQL Workbench 8.0
- Oracle Database XE
- Oracle SQL Developer 24.3.1.347.1826
- SQL

### Lenguaje de Marcas / Front-end

- HTML
- CSS
- JavaScript
- Visual Studio Code

### Entornos de Desarrollo

- Git
- GitHub


## Estructura general del repositorio

El repositorio contiene todo el proyecto del tercer trimestre. La organización principal se divide por carpetas de asignatura y por ramas de trabajo en GitHub.

Estructura principal de carpetas:
main
│
├── base-de-datos
│   ├── feature/bd-sergio
│   └── feature/bd-alejandro
│
├── lenguaje
│   ├── feature/lenguaje-sergio
│   └── feature/lenguaje-alejandro
│
└── programacion
    ├── feature/programacion-sergio
    └── feature/programacion-alejandro

└── README.md

Cada uno ha trabajado en su rama personal. Después, los cambios se integran en la rama general de su asignatura y finalmente todo se une en main.

### BASE DE DATOS

1. DESCRIPCIÓN

En la asignatura de Base de Datos se ha diseñado la estructura necesaria para almacenar la información del CRM del concesionario.

El proyecto utiliza dos sistemas de base de datos:

- MySQL: base de datos principal conectada con la aplicación Java.
- Oracle Database: utilizada para trabajar y practicar scripts DDL y DML en Oracle SQL Developer.

La base de datos principal se llama crm_coches y contiene las tablas necesarias para gestionar clientes, usuarios, coches, ventas, detalles de venta e interacciones con clientes.


2. TECNOLOGÍAS UTILIZADAS

MySQL:
- MySQL 8.4
- MySQL Workbench 8.0

Oracle:
- Oracle Database XE
- Oracle SQL Developer 24.3.1.347.1826

Otros:
- SQL
- DDL
- DML


3. INSTALACIÓN DE MYSQL

Para trabajar con la base de datos principal del proyecto es necesario instalar MySQL Server y MySQL Workbench.

Pasos de instalación:

1. Descargar MySQL Installer desde la página oficial de MySQL.
2. Ejecutar el instalador.
3. Seleccionar la instalación de MySQL Server 8.4 y MySQL Workbench 8.0.
4. Continuar con la instalación siguiendo las opciones recomendadas.
5. Configurar MySQL Server como servidor local.
6. Definir una contraseña para el usuario root.
7. Finalizar la instalación.
8. Abrir MySQL Workbench.
9. Crear o utilizar una conexión local al servidor MySQL.
10. Comprobar que la conexión funciona correctamente.


4. PREPARACIÓN DE MYSQL WORKBENCH

Una vez instalado MySQL Workbench:

1. Abrir MySQL Workbench.
2. Seleccionar la conexión local.
3. Introducir la contraseña del usuario configurado.
4. Abrir el script DDL del proyecto.
5. Ejecutar el script completo.
6. Comprobar que se ha creado la base de datos crm_coches.
7. Abrir el script DML del proyecto.
8. Ejecutar el script DML para insertar datos iniciales o de prueba.
9. Revisar que las tablas contienen registros.


5. EJECUCIÓN DEL SCRIPT DDL EN MYSQL

El script DDL se encarga de crear la base de datos y todas sus tablas.

Pasos:

1. Abrir MySQL Workbench.
2. Conectarse al servidor local.
3. Abrir el archivo DDL de MySQL.
4. Ejecutar el script completo.
5. Verificar que aparece la base de datos crm_coches.
6. Comprobar que se han creado las tablas:
   - clientes
   - usuarios
   - coches
   - ventas
   - detalle_venta
   - interacciones_cliente

El script DDL incluye:
- Creación de la base de datos.
- Creación de tablas.
- Claves primarias.
- Claves foráneas.
- Restricciones NOT NULL.
- Restricciones UNIQUE.
- Valores DEFAULT.
- Restricciones CHECK.


6. EJECUCIÓN DEL SCRIPT DML EN MYSQL

El script DML se utiliza para insertar datos de prueba en la base de datos.

Pasos:

1. Abrir MySQL Workbench.
2. Asegurarse de que la base de datos crm_coches está creada.
3. Abrir el script DML de MySQL.
4. Ejecutar el script.
5. Comprobar que los datos se han insertado correctamente.
6. Revisar las tablas mediante consultas SELECT.

Ejemplo de comprobación:

SELECT * FROM clientes;
SELECT * FROM usuarios;
SELECT * FROM coches;
SELECT * FROM ventas;
SELECT * FROM detalle_venta;
SELECT * FROM interacciones_cliente;


7. INSTALACIÓN DE ORACLE DATABASE

Para trabajar con Oracle se ha utilizado Oracle Database XE junto con Oracle SQL Developer.

Pasos de instalación:

1. Descargar Oracle Database XE desde la página oficial de Oracle.
2. Ejecutar el instalador.
3. Seguir los pasos de instalación recomendados.
4. Definir la contraseña del usuario administrador.
5. Finalizar la instalación.
6. Comprobar que los servicios de Oracle se han instalado correctamente.
7. Descargar Oracle SQL Developer.
8. Descomprimir o instalar Oracle SQL Developer.
9. Abrir Oracle SQL Developer.
10. Crear una conexión con Oracle Database XE.


8. SERVICIOS DE ORACLE

Para que Oracle funcione correctamente, deben estar activos los servicios principales.

Servicios utilizados en el entorno:

- OracleJobSchedulerXE
- OracleOraDB21Home1MTSRecoveryService
- OracleOraDB21Home1TNSListener
- OracleServiceXE
- OracleVssWriterXE

Los servicios más importantes para poder trabajar con Oracle SQL Developer son:

- OracleServiceXE
- OracleOraDB21Home1TNSListener

Para comprobarlos:

1. Abrir Servicios de Windows.
2. Buscar los servicios de Oracle.
3. Comprobar que OracleServiceXE está iniciado.
4. Comprobar que OracleOraDB21Home1TNSListener está iniciado.
5. Si alguno está detenido, iniciarlo manualmente.


9. PREPARACIÓN DE ORACLE SQL DEVELOPER

Una vez instalado Oracle SQL Developer:

1. Abrir Oracle SQL Developer.
2. Crear una nueva conexión.
3. Introducir el nombre de la conexión.
4. Indicar el usuario correspondiente.
5. Introducir la contraseña.
6. Configurar el host como localhost.
7. Configurar el puerto correspondiente.
8. Probar la conexión.
9. Guardar la conexión.
10. Conectarse a la base de datos.


10. EJECUCIÓN DEL SCRIPT DDL EN ORACLE

El script DDL de Oracle contiene la estructura de tablas adaptada a Oracle Database.

Pasos:

1. Abrir Oracle SQL Developer.
2. Conectarse a Oracle Database XE.
3. Abrir el script DDL de Oracle.
4. Ejecutar el script.
5. Comprobar que las tablas se han creado correctamente.
6. Revisar las claves primarias y relaciones.


11. EJECUCIÓN DEL SCRIPT DML EN ORACLE

El script DML de Oracle contiene datos de prueba adaptados a Oracle.

Pasos:

1. Abrir Oracle SQL Developer.
2. Conectarse a la base de datos.
3. Abrir el script DML de Oracle.
4. Ejecutar el script.
5. Confirmar los cambios si es necesario.
6. Comprobar los registros insertados mediante consultas SELECT.


12. ESTRUCTURA DE LA BASE DE DATOS MYSQL

La base de datos principal se llama:

crm_coches

Está formada por las siguientes tablas principales:

1. clientes
2. usuarios
3. coches
4. ventas
5. detalle_venta
6. interacciones_cliente

Estas tablas permiten almacenar la información necesaria para gestionar el CRM del concesionario, relacionando clientes, usuarios, vehículos, ventas e interacciones comerciales.


13. TABLAS DE LA BASE DE DATOS

La base de datos contiene las siguientes tablas:

- clientes: almacena la información de los clientes del concesionario.
- usuarios: almacena los usuarios internos que gestionan ventas e interacciones.
- coches: almacena los vehículos registrados en el concesionario.
- ventas: almacena las ventas realizadas o registradas en el sistema.
- detalle_venta: relaciona las ventas con los coches incluidos en cada operación.
- interacciones_cliente: almacena las interacciones comerciales entre clientes y usuarios.

Cada tabla cumple una función concreta dentro del CRM y permite organizar la información de forma estructurada.


14. RELACIONES ENTRE TABLAS

La base de datos utiliza claves primarias y claves foráneas para relacionar las entidades y mantener la integridad de los datos.

Relaciones principales:

- Un cliente puede tener varias ventas.
- Una venta pertenece a un único cliente.
- Un usuario puede gestionar varias ventas.
- Una venta pertenece a un único usuario.
- Una venta puede incluir varios coches mediante la tabla detalle_venta.
- Un coche puede estar asociado a un detalle de venta.
- Un cliente puede tener varias interacciones comerciales.
- Un usuario puede registrar varias interacciones con clientes.

Estas relaciones permiten representar correctamente el funcionamiento básico de un concesionario, donde se gestionan clientes, empleados, vehículos, ventas y seguimiento comercial.

23. CONEXIÓN CON JAVA

La aplicación Java trabaja principalmente con la base de datos MySQL.

Para que la conexión funcione correctamente:

1. MySQL Server debe estar iniciado.
2. La base de datos crm_coches debe estar creada.
3. Las tablas deben existir.
4. El script DML puede ejecutarse para disponer de datos iniciales.
5. El proyecto Java debe tener configurada correctamente la conexión JDBC.
6. El usuario y contraseña de conexión deben coincidir con los definidos en MySQL.


### PROGRAMACIÓN

1. DESCRIPCIÓN

En la asignatura de Programación se ha desarrollado la parte principal de la lógica del CRM del concesionario.

La aplicación está creada en Java y permite gestionar las entidades principales del sistema:

- Clientes
- Usuarios
- Coches
- Ventas
- Detalles de venta
- Interacciones con clientes

Para cada una de estas entidades se ha implementado un CRUD completo, permitiendo crear, listar, buscar, modificar y eliminar registros.

La aplicación trabaja conectada a la base de datos MySQL crm_coches mediante JDBC.


2. TECNOLOGÍAS UTILIZADAS

Para la parte de Programación se han utilizado las siguientes tecnologías y herramientas:

- Java
- JDK 21
- Maven
- JDBC
- MySQL Connector/J
- IntelliJ IDEA
- Git
- GitHub


3. INSTALACIÓN DE JAVA

Para ejecutar el proyecto es necesario tener instalado Java mediante JDK 21.

Pasos de instalación:

1. Descargar e instalar JDK 21 desde una fuente oficial.
2. Durante la instalación, aceptar las opciones recomendadas.
3. Comprobar que Java se ha instalado correctamente.
4. Abrir una terminal o consola.
5. Ejecutar el siguiente comando:

java -version

Si Java está correctamente instalado, se mostrará la versión instalada del JDK.

También se puede comprobar el compilador con:

javac -version

El resultado debería mostrar una versión correspondiente a Java 21.


4. INSTALACIÓN DE INTELLIJ IDEA

Para desarrollar y ejecutar el proyecto se ha utilizado IntelliJ IDEA.

Pasos de instalación:

1. Entrar en la página oficial de JetBrains.
2. Descargar IntelliJ IDEA Community Edition.
3. Ejecutar el instalador.
4. Aceptar las opciones recomendadas.
5. Finalizar la instalación.
6. Abrir IntelliJ IDEA.
7. Seleccionar Open para abrir el proyecto Java.


5. INSTALACIÓN Y USO DE MAVEN

El proyecto utiliza Maven para gestionar la estructura y dependencias.

Maven permite:

- Organizar el proyecto Java.
- Gestionar dependencias externas.
- Añadir el conector JDBC de MySQL.
- Compilar el proyecto.
- Ejecutar tareas de construcción.

Normalmente IntelliJ IDEA detecta automáticamente que el proyecto usa Maven al abrir la carpeta donde se encuentra el archivo pom.xml.

Pasos:

1. Abrir IntelliJ IDEA.
2. Seleccionar Open.
3. Abrir la carpeta del proyecto Java.
4. Esperar a que IntelliJ cargue Maven.
5. Comprobar que no aparecen errores en el archivo pom.xml.
6. Si IntelliJ lo solicita, pulsar en Load Maven Project o Reload Maven.


6. DEPENDENCIA JDBC DE MYSQL

Para conectar Java con MySQL se utiliza MySQL Connector/J.

Esta dependencia debe estar incluida en el archivo pom.xml del proyecto.

Gracias a esta dependencia, Java puede conectarse a la base de datos crm_coches mediante JDBC.

La conexión permite realizar operaciones SQL desde Java, como INSERT, SELECT, UPDATE y DELETE.


7. APERTURA DEL PROYECTO EN INTELLIJ IDEA

Para abrir el proyecto:

1. Abrir IntelliJ IDEA.
2. Pulsar en Open.
3. Buscar la carpeta del proyecto.
4. Seleccionar la carpeta Programacion/ProyectoTercerTrimestre.
5. Esperar a que IntelliJ cargue el proyecto.
6. Comprobar que Maven reconoce correctamente el proyecto.
7. Revisar que no haya errores en las dependencias.


8. PREPARACIÓN DE LA BASE DE DATOS

Antes de ejecutar la aplicación Java, debe estar preparada la base de datos MySQL.

Pasos necesarios:

1. Tener MySQL Server iniciado.
2. Tener creada la base de datos crm_coches.
3. Haber ejecutado el script DDL para crear las tablas.
4. Opcionalmente, ejecutar el script DML para insertar datos de prueba.
5. Comprobar que las tablas existen en MySQL Workbench.

La aplicación Java necesita esta base de datos para poder guardar y consultar la información.


9. CONFIGURACIÓN DE LA CONEXIÓN CON MYSQL

La conexión con la base de datos se realiza desde la clase de conexión del proyecto, ubicada en el paquete database.

En esta clase se configura:

- URL de conexión
- Nombre de la base de datos
- Usuario
- Contraseña

La base de datos utilizada es:

crm_coches

Para que la conexión funcione correctamente:

1. MySQL debe estar iniciado.
2. La base de datos debe existir.
3. El usuario y contraseña deben coincidir con los configurados en MySQL.
4. El conector JDBC debe estar disponible mediante Maven.


10. ESTRUCTURA DEL PROYECTO JAVA

El proyecto está organizado por paquetes para separar responsabilidades y mantener el código ordenado.

Estructura principal:

- entities
- interfaz
- controller
- services
- repositories
- repositories.impl
- database
- utils


11. PAQUETE ENTITIES

El paquete entities contiene las clases que representan las entidades principales del CRM.

Clases principales:

- Cliente
- Usuario
- Coche
- Venta
- DetalleVenta
- InteraccionCliente

También contiene enumeraciones utilizadas por algunas entidades, como:

- RolUsuario
- EstadoCoche
- EstadoVenta
- Combustible
- TipoCambio
- TipoInteraccion

Estas clases representan los datos que se manejan en la aplicación.


12. PAQUETE INTERFAZ

El paquete interfaz contiene los menús de consola de la aplicación.

Cada entidad tiene su propio menú para interactuar con el usuario.

Menús principales:

- ClienteMenu
- UsuarioMenu
- CocheMenu
- VentaMenu
- DetalleVentaMenu
- InteraccionClienteMenu

Desde estos menús el usuario puede:

- Crear registros.
- Listar registros.
- Buscar registros.
- Modificar registros.
- Eliminar registros.
- Exportar información a archivos TXT.

La interfaz se encarga de pedir datos al usuario y enviarlos al controlador correspondiente.


13. PAQUETE CONTROLLER

El paquete controller actúa como intermediario entre la interfaz y la capa de servicios.

Controladores principales:

- ClienteController
- UsuarioController
- CocheController
- VentaController
- DetalleVentaController
- InteraccionClienteController

Su función es recibir los datos desde los menús, crear objetos si es necesario y llamar al servicio correspondiente.

Los controladores ayudan a mantener separada la interacción con el usuario de la lógica de negocio.


14. PAQUETE SERVICES

El paquete services contiene la lógica de negocio de la aplicación.

Servicios principales:

- ClienteService
- UsuarioService
- CocheService
- VentaService
- DetalleVentaService
- InteraccionClienteService

En esta capa se realizan las validaciones principales antes de acceder a la base de datos.

Ejemplos de validaciones:

- Comprobar que los campos obligatorios no estén vacíos.
- Validar que los IDs sean correctos.
- Comprobar que existan clientes, usuarios, coches o ventas relacionadas.
- Validar que los importes no sean negativos.
- Evitar que un coche se venda más de una vez.
- Comprobar que los datos son coherentes con la base de datos.


15. PAQUETE REPOSITORIES

El paquete repositories contiene las interfaces de acceso a datos.

Interfaces principales:

- ClienteRepository
- UsuarioRepository
- CocheRepository
- VentaRepository
- DetalleVentaRepository
- InteraccionClienteRepository

Estas interfaces definen los métodos que debe tener cada repositorio, como crear, listar, modificar, eliminar y buscar.

Este paquete permite aplicar el patrón Repository.


16. PAQUETE REPOSITORIES.IMPL

El paquete repositories.impl contiene la implementación real de los repositorios.

Clases principales:

- ClienteRepositoryImpl
- UsuarioRepositoryImpl
- CocheRepositoryImpl
- VentaRepositoryImpl
- DetalleVentaRepositoryImpl
- InteraccionClienteRepositoryImpl

Estas clases contienen las consultas SQL necesarias para trabajar con la base de datos.

Operaciones principales:

- INSERT
- SELECT
- UPDATE
- DELETE

También incluyen consultas para comprobar si existen registros relacionados, por ejemplo:

- Comprobar si existe un cliente.
- Comprobar si existe un usuario.
- Comprobar si existe un coche.
- Comprobar si un coche ya está asociado a un detalle de venta.


17. PAQUETE DATABASE

El paquete database contiene la clase encargada de gestionar la conexión con MySQL.

Esta clase permite obtener una conexión con la base de datos crm_coches.

Desde los repositorios se utiliza esta conexión para ejecutar las consultas SQL.


18. PAQUETE UTILS

El paquete utils contiene métodos reutilizables para leer y validar datos introducidos por el usuario.

Clase principal:

- ComprobacionOpcion

Esta clase incluye métodos para:

- Leer opciones dentro de un rango.
- Leer números enteros.
- Leer números decimales.
- Leer textos obligatorios.
- Leer emails válidos.
- Leer fechas.
- Leer números mínimos.
- Validar IDs existentes.

Esto evita repetir código en los menús y mejora la organización de la aplicación.


19. PATRÓN REPOSITORY

En el proyecto se ha aplicado el patrón Repository.

Este patrón permite separar la lógica de negocio del acceso a datos.

La capa services no trabaja directamente con SQL, sino que utiliza interfaces repository.

Ventajas:

- Código más organizado.
- Separación de responsabilidades.
- Mayor facilidad de mantenimiento.
- Posibilidad de cambiar la implementación del acceso a datos sin modificar la lógica de negocio.
- Mejor estructura general del proyecto.


20. CRUD IMPLEMENTADO

La aplicación incluye operaciones CRUD completas para las entidades principales.

CRUD significa:

- Create: crear registros.
- Read: leer, listar o buscar registros.
- Update: modificar registros.
- Delete: eliminar registros.

Entidades con CRUD:

- Clientes
- Usuarios
- Coches
- Ventas
- Detalles de venta
- Interacciones con clientes

Además, algunas entidades incluyen exportación a archivos TXT.

21. MENÚS DE CONSOLA

La aplicación funciona mediante menús de consola.

Cada menú permite gestionar una entidad concreta.

Opciones comunes:

1. Crear
2. Eliminar
3. Listar
4. Exportar a TXT
5. Modificar
6. Buscar
0. Volver

Esta estructura permite navegar por la aplicación de forma sencilla.


24. EXPORTACIÓN A TXT

La aplicación permite exportar información de algunas entidades a archivos TXT.

El objetivo es guardar listados de datos en archivos externos.

Ejemplos de archivos generados:

- clientes.txt
- usuarios.txt
- coches.txt
- ventas.txt
- detalle_venta.txt
- interacciones_cliente.txt

Cada archivo contiene un listado formateado con la información correspondiente.


25. EJECUCIÓN DE LA APLICACIÓN

Para ejecutar la aplicación Java:

1. Abrir IntelliJ IDEA.
2. Abrir la carpeta Programacion/ProyectoTercerTrimestre.
3. Comprobar que Maven carga correctamente el proyecto.
4. Asegurarse de que MySQL está iniciado.
5. Comprobar que la base de datos crm_coches existe.
6. Verificar que el archivo pom.xml contiene la dependencia de MySQL Connector/J.
7. Revisar la configuración de conexión en la clase de base de datos.
8. Ejecutar la clase principal del proyecto.
9. Utilizar los menús de consola para gestionar el CRM.


26. POSIBLES ERRORES Y SOLUCIONES

Error de conexión con MySQL:

- Comprobar que MySQL Server está iniciado.
- Revisar usuario y contraseña.
- Comprobar que la base de datos crm_coches existe.
- Verificar que el puerto de MySQL es correcto.

Error al cargar dependencias Maven:

- Recargar el proyecto Maven desde IntelliJ.
- Comprobar conexión a Internet.
- Revisar el archivo pom.xml.

Error al insertar datos:

- Comprobar que los campos obligatorios están rellenos.
- Verificar que los IDs relacionados existen.
- Revisar restricciones de la base de datos.

Error al cambiar de menú o introducir opciones:

- Introducir solo números cuando el menú lo solicita.
- Usar opciones dentro del rango indicado.

### LENGUAJE DE MARCAS / FRONT-END

1. DESCRIPCIÓN

En la asignatura de Lenguaje de Marcas se ha desarrollado la parte visual del proyecto CRM del concesionario.

Esta parte está formada por una interfaz web creada con HTML, CSS y JavaScript. Su objetivo es representar de forma visual las secciones principales del sistema, como el dashboard, clientes, usuarios, coches, ventas e interacciones.

La interfaz permite mostrar información de forma clara, organizar las secciones del CRM y aplicar funcionalidades dinámicas mediante JavaScript.

Aunque la parte visual no está conectada directamente con la aplicación Java, mantiene la misma temática y estructura general del proyecto.


2. TECNOLOGÍAS UTILIZADAS

Para la parte de Lenguaje de Marcas se han utilizado las siguientes tecnologías y herramientas:

- HTML
- CSS
- JavaScript
- Visual Studio Code
- Live Server
- Git
- GitHub


3. INSTALACIÓN DE VISUAL STUDIO CODE

Para desarrollar la parte front-end se ha utilizado Visual Studio Code.

Visual Studio Code es un editor de código ligero que permite trabajar cómodamente con HTML, CSS y JavaScript.

Pasos de instalación:

1. Entrar en la página oficial de Visual Studio Code.
2. Descargar la versión correspondiente al sistema operativo.
3. Ejecutar el instalador descargado.
4. Aceptar las opciones recomendadas durante la instalación.
5. Finalizar la instalación.
6. Abrir Visual Studio Code.
7. Seleccionar la carpeta del proyecto desde Archivo > Abrir carpeta.


4. EXTENSIONES RECOMENDADAS

Para trabajar mejor con el proyecto web se recomienda instalar algunas extensiones en Visual Studio Code.

Extensiones recomendadas:

- Live Server
- Prettier
- HTML CSS Support
- JavaScript ES6 snippets

Live Server permite ejecutar las páginas HTML en un servidor local y ver los cambios automáticamente en el navegador.

Prettier ayuda a mantener el código formateado.

HTML CSS Support facilita el trabajo con clases y estilos.

JavaScript ES6 snippets ayuda a escribir código JavaScript de forma más rápida.


5. INSTALACIÓN DE LIVE SERVER

Para instalar Live Server:

1. Abrir Visual Studio Code.
2. Ir al apartado de extensiones.
3. Buscar Live Server.
4. Seleccionar la extensión.
5. Pulsar en Instalar.
6. Esperar a que termine la instalación.

Una vez instalada, se puede abrir un archivo HTML y ejecutarlo con la opción Open with Live Server.


6. APERTURA DEL PROYECTO WEB

Para abrir la parte de Lenguaje de Marcas:

1. Abrir Visual Studio Code.
2. Pulsar en Archivo.
3. Seleccionar Abrir carpeta.
4. Buscar la carpeta del proyecto correspondiente a Lenguaje de Marcas.
5. Abrir la carpeta.
6. Comprobar que aparecen los archivos HTML, CSS y JavaScript.


7. EJECUCIÓN DEL FRONT-END

Para ejecutar la parte visual del proyecto:

1. Abrir Visual Studio Code.
2. Abrir la carpeta de Lenguaje de Marcas.
3. Localizar el archivo HTML principal.
4. Hacer clic derecho sobre el archivo.
5. Seleccionar Open with Live Server.
6. Se abrirá el navegador con la página del CRM.
7. Navegar por las distintas secciones de la interfaz.

También se puede abrir el archivo HTML directamente en el navegador, aunque se recomienda usar Live Server.


8. ESTRUCTURA GENERAL DEL FRONT-END

La parte visual del proyecto está organizada en archivos HTML, CSS y JavaScript.

La estructura general incluye:

- Archivos HTML para las diferentes páginas del CRM.
- Archivos CSS para los estilos y diseño visual.
- Archivos JavaScript para la lógica de la interfaz.
- Recursos visuales si son necesarios, como imágenes o iconos.

La organización permite separar el contenido, el diseño y el comportamiento de la web.

9. TABLAS Y LISTADOS

La interfaz utiliza tablas y listados para mostrar información del CRM de forma organizada.

Se han utilizado para representar datos como:

- Clientes.
- Usuarios.
- Coches.
- Ventas.
- Interacciones.

El objetivo es que la información sea clara y fácil de consultar.


10. ORGANIZACIÓN VISUAL

La interfaz se ha organizado en diferentes secciones para facilitar la navegación.

Secciones principales:

- Dashboard o página principal.
- Clientes.
- Usuarios.
- Coches.
- Ventas.
- Interacciones.

Cada sección mantiene una estructura visual similar para que la aplicación sea coherente.


10. POSIBLES ERRORES Y SOLUCIONES

Si la página no carga correctamente:

- Comprobar que el archivo HTML se ha abierto correctamente.
- Revisar que las rutas de CSS y JavaScript son correctas.
- Ejecutar con Live Server.
- Comprobar la consola del navegador.

Si los estilos no se aplican:

- Revisar que el archivo CSS está enlazado en el HTML.
- Comprobar que las clases coinciden.
- Revisar la ruta del archivo CSS.

Si JavaScript no funciona:

- Comprobar que el archivo JS está enlazado correctamente.
- Revisar errores en la consola del navegador.
- Comprobar que los IDs o clases utilizados existen en el HTML.

Si Live Server no aparece:

- Comprobar que la extensión está instalada.
- Reiniciar Visual Studio Code.
- Hacer clic derecho sobre el archivo HTML.

