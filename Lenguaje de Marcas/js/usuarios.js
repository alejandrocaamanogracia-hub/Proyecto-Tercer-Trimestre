protegerPagina();

class UsuarioCRM {
    constructor(id, nombre, email, rol, password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.password = password;
    }
}

const usuarioForm = document.querySelector("#usuarioForm");
const usuarioIdInput = document.querySelector("#usuarioId");
const nombreUsuarioInput = document.querySelector("#nombreUsuario");
const emailUsuarioInput = document.querySelector("#emailUsuario");
const rolUsuarioInput = document.querySelector("#rolUsuario");
const passwordUsuarioInput = document.querySelector("#passwordUsuario");
const usuarioMensaje = document.querySelector("#usuarioMensaje");
const usuariosTableBody = document.querySelector("#usuariosTableBody");
const buscadorUsuarios = document.querySelector("#buscadorUsuarios");
const submitUserButton = document.querySelector("#submitUserButton");
const cancelUserButton = document.querySelector("#cancelUserButton");
const logoutButton = document.querySelector("#logoutButton");
const usuarioFormPanel = document.querySelector("#usuarioFormPanel");
const newUserButton = document.querySelector("#newUserButton");

let usuarios = [];

function cargarUsuarios() {
    const usuariosGuardados = sessionStorage.getItem("usuarios");

    if (usuariosGuardados) {
        usuarios = JSON.parse(usuariosGuardados);

        if (usuarios.length > 0) {
            return;
        }
    }

    usuarios = [
        new UsuarioCRM(1, "Admin Principal", "admin@driveflow.com", "Administrador", "admin123"),
        new UsuarioCRM(2, "Pedro López", "pedro@driveflow.com", "Comercial", "comercial123"),
        new UsuarioCRM(3, "Marta Fernández", "marta@driveflow.com", "Gestor", "gestor123")
    ];

    guardarUsuarios();
}

function guardarUsuarios() {
    sessionStorage.setItem("usuarios", JSON.stringify(usuarios));
}

function mostrarMensajeUsuario(mensaje, tipo) {
    usuarioMensaje.textContent = mensaje;
    usuarioMensaje.className = `usu-usuarios__message usu-usuarios__message--${tipo}`;
}

function limpiarMensajeUsuario() {
    usuarioMensaje.textContent = "";
    usuarioMensaje.className = "usu-usuarios__message";
}

function generarNuevoIdUsuario() {
    if (usuarios.length === 0) {
        return 1;
    }

    return Math.max(...usuarios.map(function (usuario) {
        return usuario.id;
    })) + 1;
}

function validarUsuario(nombre, email, rol, password) {
    if (nombre === "" || email === "" || rol === "" || password === "") {
        mostrarMensajeUsuario("Todos los campos son obligatorios.", "error");
        return false;
    }

    if (!email.includes("@")) {
        mostrarMensajeUsuario("El email debe tener un formato válido.", "error");
        return false;
    }

    return true;
}

function limpiarFormularioUsuario() {
    usuarioIdInput.value = "";
    nombreUsuarioInput.value = "";
    emailUsuarioInput.value = "";
    rolUsuarioInput.value = "";
    passwordUsuarioInput.value = "";
    submitUserButton.textContent = "Guardar usuario";
    limpiarMensajeUsuario();
}

function pintarUsuarios(listaUsuarios) {
    usuariosTableBody.innerHTML = "";

    if (listaUsuarios.length === 0) {
        usuariosTableBody.innerHTML = `
            <tr>
                <td colspan="6" class="usu-usuarios__empty">No hay usuarios para mostrar.</td>
            </tr>
        `;
        return;
    }

    listaUsuarios.forEach((usuario) => {
        const fila = document.createElement("tr");
        fila.classList.add("usu-usuarios__row");

        fila.innerHTML = `
            <td data-label="ID">${usuario.id}</td>
            <td data-label="Nombre">${usuario.nombre}</td>
            <td data-label="Email">${usuario.email}</td>
            <td data-label="Rol">${usuario.rol}</td>
            <td data-label="Contraseña">••••••••</td>
            <td data-label="Acciones">
                <div class="usu-usuarios__table-actions">
                    <button class="usu-usuarios__icon-button usu-usuarios__icon-button--edit" data-action="edit" data-id="${usuario.id}">
                        Editar
                    </button>
                    <button class="usu-usuarios__icon-button usu-usuarios__icon-button--delete" data-action="delete" data-id="${usuario.id}">
                        Eliminar
                    </button>
                </div>
            </td>
        `;

        usuariosTableBody.appendChild(fila);
    });
}

function crearUsuario(nombre, email, rol, password) {
    const nuevoUsuario = new UsuarioCRM(
        generarNuevoIdUsuario(),
        nombre,
        email,
        rol,
        password
    );

    usuarios.push(nuevoUsuario);
    guardarUsuarios();
    pintarUsuarios(usuarios);
    limpiarFormularioUsuario();
    ocultarFormularioUsuario();
    mostrarMensajeUsuario("Usuario creado correctamente.", "success");
}

function actualizarUsuario(id, nombre, email, rol, password) {
    usuarios = usuarios.map((usuario) => {
        if (usuario.id === id) {
            return new UsuarioCRM(id, nombre, email, rol, password);
        }

        return usuario;
    });

    guardarUsuarios();
    pintarUsuarios(usuarios);
    limpiarFormularioUsuario();
    ocultarFormularioUsuario();
    mostrarMensajeUsuario("Usuario actualizado correctamente.", "success");
}

function cargarUsuarioEnFormulario(id) {
    const usuarioEncontrado = usuarios.find((usuario) => usuario.id === id);

    if (!usuarioEncontrado) {
        mostrarMensajeUsuario("Usuario no encontrado.", "error");
        return;
    }

    mostrarFormularioUsuario();

    usuarioIdInput.value = usuarioEncontrado.id;
    nombreUsuarioInput.value = usuarioEncontrado.nombre;
    emailUsuarioInput.value = usuarioEncontrado.email;
    rolUsuarioInput.value = usuarioEncontrado.rol;
    passwordUsuarioInput.value = usuarioEncontrado.password;
    submitUserButton.textContent = "Actualizar usuario";
    mostrarMensajeUsuario("Editando usuario seleccionado.", "info");

    setTimeout(() => {
        irAlFormularioUsuario();
    }, 150);
}

function eliminarUsuario(id, callback) {
    usuarios = usuarios.filter((usuario) => usuario.id !== id);
    guardarUsuarios();
    callback(usuarios);
}

function filtrarUsuarios(textoBusqueda) {
    const texto = textoBusqueda.toLowerCase();

    return usuarios.filter((usuario) => {
        return usuario.nombre.toLowerCase().includes(texto) ||
            usuario.email.toLowerCase().includes(texto) ||
            usuario.rol.toLowerCase().includes(texto);
    });
}

function mostrarFormularioUsuario() {
    usuarioFormPanel.classList.remove("usu-usuarios__panel--hidden");
}

function ocultarFormularioUsuario() {
    usuarioFormPanel.classList.add("usu-usuarios__panel--hidden");
}

function irAlFormularioUsuario() {
    usuarioFormPanel.scrollIntoView({
        behavior: "smooth",
        block: "start"
    });
}

usuarioForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const id = usuarioIdInput.value;
    const nombre = nombreUsuarioInput.value.trim();
    const email = emailUsuarioInput.value.trim();
    const rol = rolUsuarioInput.value;
    const password = passwordUsuarioInput.value.trim();

    limpiarMensajeUsuario();

    if (!validarUsuario(nombre, email, rol, password)) {
        return;
    }

    if (id) {
        actualizarUsuario(Number(id), nombre, email, rol, password);
    } else {
        crearUsuario(nombre, email, rol, password);
    }
});

usuariosTableBody.addEventListener("click", (event) => {
    const boton = event.target.closest("button");

    if (!boton) {
        return;
    }

    const id = Number(boton.dataset.id);
    const action = boton.dataset.action;

    if (action === "edit") {
        cargarUsuarioEnFormulario(id);
    }

    if (action === "delete") {
        const confirmar = window.confirm("¿Seguro que quieres eliminar este usuario?");

        if (confirmar) {
            eliminarUsuario(id, (usuariosActualizados) => {
                pintarUsuarios(usuariosActualizados);
                mostrarMensajeUsuario("Usuario eliminado correctamente.", "success");
            });
        }
    }
});

buscadorUsuarios.addEventListener("input", () => {
    const usuariosFiltrados = filtrarUsuarios(buscadorUsuarios.value);
    pintarUsuarios(usuariosFiltrados);
});

cancelUserButton.addEventListener("click", () => {
    limpiarFormularioUsuario();
    ocultarFormularioUsuario();
});

logoutButton.addEventListener("click", () => {
    cerrarSesion();
});

newUserButton.addEventListener("click", () => {
    limpiarFormularioUsuario();
    mostrarFormularioUsuario();

    setTimeout(() => {
        irAlFormularioUsuario();
    }, 150);
});

window.addEventListener("load", () => {
    cargarUsuarios();
    pintarUsuarios(usuarios);
});