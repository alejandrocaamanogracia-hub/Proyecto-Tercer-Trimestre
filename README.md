# 🚗 Proyecto CRM Concesionario - Tercer Trimestre

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

## 📝 Introducción

Este repositorio contiene el proyecto intermodular desarrollado durante el tercer trimestre del ciclo de **Desarrollo de Aplicaciones Multiplataforma (DAM)**.

El proyecto consiste en un sistema **CRM orientado a la gestión de un concesionario de coches**, integrando contenidos de las asignaturas de *Programación, Bases de Datos, Lenguajes de Marcas y Entornos de Desarrollo*.

El sistema permite trabajar de forma integral con las entidades principales de un concesionario: clientes, usuarios, coches, ventas, detalles de venta e interacciones comerciales. A partir de estas entidades se han desarrollado la aplicación Java (Back-end), la base de datos relacional, la interfaz web (Front-end) y la gestión del ciclo de vida mediante Git y GitHub.

---

## 🛠️ Tecnologías Utilizadas

### Programación / Back-end

| Herramienta | Detalle |
|---|---|
| **Lenguaje** | Java (JDK 21) |
| **Gestor de Dependencias** | Maven |
| **Persistencia** | JDBC (MySQL Connector/J) |
| **IDE** | IntelliJ IDEA |

### Base de Datos

| Herramienta | Detalle |
|---|---|
| **Motores** | MySQL 8.4 y Oracle Database XE |
| **Herramientas** | MySQL Workbench 8.0 y Oracle SQL Developer 24.3 |
| **Lenguaje** | SQL (Scripts DDL y DML adaptados) |

### Lenguaje de Marcas / Front-end

| Herramienta | Detalle |
|---|---|
| **Tecnologías** | HTML5, CSS3 y JavaScript (ES6) |
| **Editor** | Visual Studio Code |

### Entornos de Desarrollo

| Herramienta | Detalle |
|---|---|
| **Control de Versiones** | Git & GitHub |

---

## 🌿 Estructura General del Repositorio

La organización del proyecto se divide por carpetas de asignatura en la rama principal y se ha gestionado mediante un flujo de trabajo basado en ramas en GitHub.

### Organización de Ramas (Git Flow)

```text
main
├── base-de-datos
│   ├── feature/bd-sergio
│   └── feature/bd-alejandro
├── lenguaje
│   ├── feature/lenguaje-sergio
│   └── feature/lenguaje-alejandro
└── programacion
    ├── feature/programacion-sergio
    └── feature/programacion-alejandro
```

> 💡 Cada integrante ha trabajado en su rama personal `feature/*`. Posteriormente, los cambios se integraron en la rama general de su asignatura y finalmente todo se fusionó en la rama `main`.

### Directorio de Carpetas

```text
Proyecto-Tercer-Trimestre/
├── Base de datos/          # Scripts DDL/DML de MySQL y Oracle
├── Lenguaje de Marcas/     # Interfaz Web del CRM (HTML, CSS, JS)
├── Programacion/           # Proyecto Java Maven (Código Fuente)
└── README.md               # Guía general
```

---

## 🗄️ Base de Datos

En esta sección se ha diseñado la estructura necesaria para almacenar la información del CRM. El proyecto utiliza dos sistemas:

- **MySQL**: Base de datos principal conectada con la aplicación Java a través de JDBC.
- **Oracle Database**: Utilizada para trabajar y practicar scripts DDL y DML en Oracle SQL Developer.

### 📊 Modelo Entidad-Relación

La base de datos se llama `crm_coches` y su estructura principal está formada por:

| Tabla | Función |
|---|---|
| `clientes` | Información general y de contacto de clientes potenciales e históricos |
| `usuarios` | Usuarios internos del sistema (empleados, comerciales, administradores) |
| `coches` | Vehículos registrados en el inventario del concesionario |
| `ventas` | Operaciones de venta globales |
| `detalle_venta` | Líneas de venta con coche asociado, precio final y descuento aplicado |
| `interacciones_cliente` | Historial de interacciones comerciales entre clientes y usuarios |

---

## 🚀 Instalación y Despliegue

### Configuración de Credenciales por Defecto

Para que el proyecto funcione correctamente sin modificar el código, asegúrate de configurar tu entorno local con estos datos o adaptarlos en la clase de conexión:

- **Database:** `crm_coches`
- **MySQL User:** `root` | **Password:** *(Tu contraseña local)*
- **Oracle User:** `SYSTEM` o el usuario esquema configurado

### En MySQL (Workbench)

1. Descarga e instala MySQL Server 8.4 y MySQL Workbench 8.0.
- https://dev.mysql.com/downloads/mysql/8.4.html
- https://dev.mysql.com/downloads/workbench/
2. Abre MySQL Workbench y conéctate al servidor local.
3. Abre y ejecuta el script **DDL** (`Base de datos/..`) para crear la estructura, claves primarias, foráneas y restricciones (`CHECK`, `UNIQUE`, `DEFAULT`).
4. Abre y ejecuta el script **DML** para cargar los datos de prueba.
5. Verifica la correcta inserción ejecutando:

```sql
SELECT * FROM clientes;
```

### En Oracle Database (SQL Developer)

1. Instala Oracle Database XE y Oracle SQL Developer.
- https://www.oracle.com/es/database/technologies/xe-downloads.html
- https://www.oracle.com/database/sqldeveloper/technologies/download/
2. Asegúrate de que los servicios locales de Windows estén activos. Para ello abre Servicios de Windows y comprueba que los siguientes servicios están iniciados.
   - `OracleServiceXE`
   - `OracleOraDB21Home1TNSListener`

## Configuración de Oracle XE

Este proyecto usa **Oracle Database 21c XE**.

> MUY IMPORTANTE:
> En SQL Developer hay que usar como **Nombre del Servicio**:
>
> ```text
> XEPDB1
> ```
>
> No usar `XE`, porque `XE` conecta al contenedor principal y puede provocar errores como `ORA-65096`.

---

## 1. Conexión inicial como SYSTEM

Primero hay que crear una conexión en SQL Developer con el usuario administrador `SYSTEM`.

**Datos de conexión:**

```text
Nombre de conexión: system_xepdb1
Usuario: system
Contraseña: la contraseña indicada al instalar Oracle
Host: localhost
Puerto: 1521
Nombre del Servicio: XEPDB1
```

Una vez conectados como `SYSTEM`, ejecutar la primera parte del script DDL Oracle:

```sql
CREATE USER concesionario
    IDENTIFIED BY "1234"
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP
    QUOTA UNLIMITED ON USERS;

GRANT CONNECT, RESOURCE TO concesionario;
GRANT CREATE SESSION TO concesionario;
```

## 2. Crear conexión con el usuario del proyecto

Después de crear el usuario `concesionario`, hay que crear una segunda conexión en SQL Developer.

**Datos de conexión:**

```text
Nombre de conexión: concesionario_xepdb1
Usuario: concesionario
Contraseña: 1234
Host: localhost
Puerto: 1521
Nombre del Servicio: XEPDB1
```

Para comprobar que estamos conectados con el usuario correcto:

```sql
SELECT USER FROM dual;
```

Debe devolver:

```text
CONCESIONARIO
```

Una vez conectados como `concesionario`, ejecutar el resto del **script DDL Oracle**.

Posteriormente, ejecutar el **script DML Oracle**.



## 💻 Programación (Back-end Java)

La aplicación Java implementa la lógica de negocio del CRM utilizando una arquitectura limpia basada en menús de consola y persistencia mediante JDBC.

### 🔧 Herramientas necesarias

Para ejecutar la parte de Programación se recomienda utilizar las siguientes herramientas:

- **JDK 21**: necesario para compilar y ejecutar la aplicación Java.
- **IntelliJ IDEA**: IDE recomendado para abrir el proyecto, gestionar Maven y ejecutar la clase principal `Main.java`.
- **Maven**: utilizado para gestionar las dependencias del proyecto, especialmente el conector JDBC de MySQL.
- **MySQL Connector/J**: dependencia incluida en el archivo `pom.xml`, necesaria para conectar Java con la base de datos MySQL.

### Instalación de JDK 21 e IntelliJ IDEA

1. Descarga e instala **JDK 21**.
- https://www.oracle.com/java/technologies/downloads/#java21

2. Descarga e instala **IntelliJ IDEA Community Edition**.
- https://www.jetbrains.com/idea/download/

3. Comprueba que Java está instalado correctamente ejecutando en la terminal:

```bash
java -version

### 🏛️ Arquitectura y Patrón Repository

Para separar las responsabilidades y aislar la lógica de negocio de las consultas SQL, se ha implementado el **Patrón Repository**:

```text
src/java/
├── com/concesionario/proyectoTercerTrimestre/
│   ├── controllers/      # Controladores entre la interfaz y los servicios
│   ├── database/         # Conexión JDBC con MySQL
│   ├── entities/         # Clases entidad y enumeradores
│   ├── interfaz/         # Menús interactivos por consola
│   ├── repositories/     # Interfaces de acceso a datos
│   │   └── impl/         # Implementación de consultas SQL y operaciones CRUD
│   ├── services/         # Lógica de negocio y validaciones
│   └── utils/            # Métodos auxiliares y utilidades

### ⚙️ Características Principales

- **CRUD Completo**: Implementado para las 6 entidades del sistema (Crear, Leer, Actualizar y Eliminar).
- **Gestión con Maven**: Dependencia automatizada de `mysql-connector-j` en el archivo `pom.xml`.
- **Exportación de Datos**: Capacidad de generar reportes en archivos de texto planos (ej. `clientes.txt`, `ventas.txt`) con formatos limpios de los listados de la base de datos.

### 🏁 Ejecución de la Aplicación

1. Asegúrate de que el servidor de MySQL esté encendido y con la base de datos `crm_coches` cargada siguiendo los pasos descritos previamente en el apartado de Base de Datos.

2. Abre IntelliJ IDEA y selecciona *Open* apuntando a la carpeta `Programacion/ProyectoTercerTrimestre`.

3. Permite que Maven recargue y descargue las dependencias automáticas.

4. Comprueba que la carpeta `src/java` está marcada como **Sources Root**. Para ello, haz clic derecho sobre la carpeta `java` situada dentro de `src`, selecciona **Mark Directory as** y después **Sources Root**.

    La estructura esperada es:

    ```text
    src/
    └── java/
        └── com/
            └── concesionario/
                └── proyectoTercerTrimestre/
    ```

    No se debe marcar como **Sources Root** la carpeta `src` ni la carpeta del paquete `com/concesionario/proyectoTercerTrimestre`, únicamente `src/java`.

5. Para que la aplicación Java pueda conectarse correctamente con MySQL, los datos de conexión configurados en MySQL Workbench deben coincidir con los datos utilizados en la clase `DataBaseConnection`.

    Ejemplo:

    - Parámetros MySQL Workbench:

    ```SQL
    - Hostname: 127.0.0.1
    - Port: 3306
    - Username: root
    - Password: contraseña configurada durante la instalación de MySQL
    - Base de datos: crm_coches
    ```

    - En Java, la clase `DataBaseConnection` debe utilizar la misma configuración:

    ```java
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/crm_coches";
    private static final String USER = "root";
    private static final String PASSWORD = "tu_contraseña";
    ```

6. Localiza la clase principal (`Main.java`), haz clic derecho y selecciona *Run*.

## 🌐 Lenguaje de Marcas (Front-end Web)

Representación visual y dinámica del CRM utilizando tecnologías estándar de desarrollo web.

### 🎨 Características de la Interfaz

- **HTML5 Semántico**: Estructura limpia y accesible de los diferentes módulos (Dashboard, Clientes, Coches, Ventas).
- **CSS3 Personalizado**: Diseño de interfaces, tablas de datos y paneles de control homogéneos y modernos.
- **JavaScript (DOM & BOM)**: Manipulación dinámica de elementos de la interfaz, gestión de eventos de usuario y almacenamiento temporal utilizando `SessionStorage` y el objeto `Window`.

### 🖥️ Cómo Visualizar el Front-end

1. Abre Visual Studio Code y carga la carpeta `Lenguaje de Marcas/`.
2. Se recomienda tener instalada la extensión **Live Server**.
3. Haz clic derecho sobre el archivo HTML principal (ej: `index.html` o `dashboard.html`) y selecciona *Open with Live Server*.
4. La aplicación se desplegará de forma automática en tu navegador web predeterminado (`http://127.0.0.1:5500`).

### Instalación de Live Server

1. Abrir Visual Studio Code.
2. Ir al apartado de extensiones.
3. Buscar Live Server.
4. Seleccionar la extensión.
5. Pulsar en Instalar.
6. Esperar a que termine la instalación.

---

## ⚠️ Posibles Errores y Soluciones

| Error Común | Causa Probable | Solución |
|---|---|---|
| `java.sql.SQLException: Access denied...` | Credenciales de MySQL incorrectas en el código Java | Revisa el archivo de configuración en el paquete `database` y actualiza el usuario/password |
| El script de Oracle no conecta | Los servicios de Oracle están caídos o el puerto está ocupado | Abre Servicios de Windows, localiza `OracleServiceXE` y dale a *Iniciar*. Verifica el puerto `1521` |
| No se descargan las dependencias en IntelliJ | Bloqueo o falta de sincronización de Maven | Haz clic derecho sobre el proyecto → Maven → *Reload Project* |
| Los estilos CSS o scripts JS no cargan en la web | Rutas relativas mal especificadas en el archivo HTML | Revisa las etiquetas `<link>` y `<script>` en el HTML y asegúrate de usar rutas relativas correctas (ej: `./css/style.css`) |
