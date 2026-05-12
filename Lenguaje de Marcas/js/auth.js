
/* #region LOGIN */

class Usuario {
    constructor(id, nombre, email, password, rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
}

const usuariosDemo = [
    new Usuario(1, "Admin Principal", "admin@driveflow.com", "admin123", "Administrador"),
    new Usuario(2, "Pedro López", "pedro@driveflow.com", "comercial123", "Comercial"),
    new Usuario(3, "Marta Fernández", "marta@driveflow.com", "gestor123", "Gestor")
];

function obtenerUsuarioActivo() {
    const usuarioGuardado = sessionStorage.getItem("usuarioActivo");

    if (!usuarioGuardado) {
        return null;
    }

    return JSON.parse(usuarioGuardado);
}

function guardarUsuarioActivo(usuario) {
    sessionStorage.setItem("usuarioActivo", JSON.stringify({
        id: usuario.id,
        nombre: usuario.nombre,
        email: usuario.email,
        rol: usuario.rol
    }));
}

function cerrarSesion() {
    sessionStorage.removeItem("usuarioActivo");
    window.location.href = "../index.html";
}

function protegerPagina() {
    const usuarioActivo = obtenerUsuarioActivo();

    if (!usuarioActivo) {
        window.location.href = "../index.html";
    }
}

function validarLogin(email, password) {
    return usuariosDemo.find(function (usuario) {
        return usuario.email === email && usuario.password === password;
    });
}

/* #endregion */