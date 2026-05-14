protegerPagina();

class Cliente {
    constructor(id, nombre, email, telefono, direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}

const clienteForm = document.querySelector("#clienteForm");
const clienteIdInput = document.querySelector("#clienteId");
const nombreInput = document.querySelector("#nombre");
const emailInputCliente = document.querySelector("#email");
const telefonoInput = document.querySelector("#telefono");
const direccionInput = document.querySelector("#direccion");
const clienteMensaje = document.querySelector("#clienteMensaje");
const clientesTableBody = document.querySelector("#clientesTableBody");
const buscadorClientes = document.querySelector("#buscadorClientes");
const submitButton = document.querySelector("#submitButton");
const cancelButton = document.querySelector("#cancelButton");
const logoutButton = document.querySelector("#logoutButton");
const clienteFormPanel = document.querySelector("#clienteFormPanel");
const newClientButton = document.querySelector("#newClientButton");


let clientes = [];

function cargarClientes() {
    const clientesGuardados = localStorage.getItem("clientes");

    if (clientesGuardados) {
        clientes = JSON.parse(clientesGuardados);

        if (clientes.length > 0) {
            return;
        }
    }

    clientes = [
        new Cliente(1, "Carlos Martínez", "carlos.martinez@email.com", "600111222", "Calle Mayor 12, Madrid"),
        new Cliente(2, "Laura Sánchez", "laura.sanchez@email.com", "600333444", "Avenida Andalucía 45, Sevilla"),
        new Cliente(3, "Miguel Torres", "miguel.torres@email.com", "600555666", "Calle Valencia 8, Barcelona")
    ];

    guardarClientes();
}

function guardarClientes() {
    localStorage.setItem("clientes", JSON.stringify(clientes));
}

function mostrarMensaje(mensaje, tipo) {
    clienteMensaje.textContent = mensaje;
    clienteMensaje.className = `cli-clientes__message cli-clientes__message--${tipo}`;
}

function limpiarMensaje() {
    clienteMensaje.textContent = "";
    clienteMensaje.className = "cli-clientes__message";
}

function generarNuevoId() {
    if (clientes.length === 0) {
        return 1;
    }

    return Math.max(...clientes.map(function (cliente) {
        return cliente.id;
    })) + 1;
}

function validarCliente(nombre, email, telefono, direccion) {
    if (nombre === "" || email === "" || telefono === "" || direccion === "") {
        mostrarMensaje("Todos los campos son obligatorios.", "error");
        return false;
    }

    if (!email.includes("@")) {
        mostrarMensaje("El email debe tener un formato válido.", "error");
        return false;
    }

    return true;
}

function limpiarFormulario() {
    clienteIdInput.value = "";
    nombreInput.value = "";
    emailInputCliente.value = "";
    telefonoInput.value = "";
    direccionInput.value = "";
    submitButton.textContent = "Guardar cliente";
    limpiarMensaje();
}

function pintarClientes(listaClientes) {
    clientesTableBody.innerHTML = "";

    if (listaClientes.length === 0) {
        clientesTableBody.innerHTML = `
            <tr>
                <td colspan="6" class="cli-clientes__empty">No hay clientes para mostrar.</td>
            </tr>
        `;
        return;
    }

    listaClientes.forEach((cliente) => {
        const card = document.createElement("div");
        card.classList.add("table__dataItem");

        card.innerHTML = `
            <p class="table__attributesText"><strong>ID:</strong> ${cliente.id}</p>
            <p class="table__attributesText"><strong>Nombre:</strong> ${cliente.nombre}</p>
            <p class="table__attributesText"><strong>Email:</strong> ${cliente.email}</p>
            <p class="table__attributesText"><strong>Teléfono:</strong> ${cliente.telefono}</p>
            <p class="table__attributesText"><strong>Dirección:</strong> ${cliente.direccion}</p>
            <div class="table__actions">
                <button class="input" data-action="edit" data-id="${cliente.id}">Editar</button>
                <button class="button" data-action="delete" data-id="${cliente.id}">Eliminar</button>
            </div>
        `;

        clientesTableBody.appendChild(card);
    });
}

function crearCliente(nombre, email, telefono, direccion) {
    const nuevoCliente = new Cliente(
        generarNuevoId(),
        nombre,
        email,
        telefono,
        direccion
    );

    clientes.push(nuevoCliente);
    guardarClientes();
    pintarClientes(clientes);
    limpiarFormulario();
    ocultarFormulario();
    mostrarMensaje("Cliente creado correctamente.", "success");
}

function actualizarCliente(id, nombre, email, telefono, direccion) {
    clientes = clientes.map((cliente) => {
        if (cliente.id === id) {
            return new Cliente(id, nombre, email, telefono, direccion);
        }

        return cliente;
    });

    guardarClientes();
    pintarClientes(clientes);
    limpiarFormulario();
    ocultarFormulario();
    mostrarMensaje("Cliente actualizado correctamente.", "success");
}

function cargarClienteEnFormulario(id) {
    const clienteEncontrado = clientes.find((cliente) => cliente.id === id);

    if (!clienteEncontrado) {
        mostrarMensaje("Cliente no encontrado.", "error");
        return;
    }

    mostrarFormulario();

    clienteIdInput.value = clienteEncontrado.id;
    nombreInput.value = clienteEncontrado.nombre;
    emailInputCliente.value = clienteEncontrado.email;
    telefonoInput.value = clienteEncontrado.telefono;
    direccionInput.value = clienteEncontrado.direccion;
    submitButton.textContent = "Actualizar cliente";
    mostrarMensaje("Editando cliente seleccionado.", "info");

    setTimeout(() => {
        irAlFormulario();
    }, 150);
}

function eliminarCliente(id, callback) {
    clientes = clientes.filter((cliente) => cliente.id !== id);
    guardarClientes();
    callback(clientes);
}

function filtrarClientes(textoBusqueda) {
    const texto = textoBusqueda.toLowerCase();

    return clientes.filter((cliente) => {
        return cliente.nombre.toLowerCase().includes(texto) ||
            cliente.email.toLowerCase().includes(texto) ||
            cliente.telefono.toLowerCase().includes(texto) ||
            cliente.direccion.toLowerCase().includes(texto);
    });
}

function mostrarFormulario() {
    clienteFormPanel.classList.remove("cli-clientes__panel--hidden");
}

function ocultarFormulario() {
    clienteFormPanel.classList.add("cli-clientes__panel--hidden");
}

function irAlFormulario() {
    clienteFormPanel.scrollIntoView({
        behavior: "smooth",
        block: "start"
    });
}

clienteForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const id = clienteIdInput.value;
    const nombre = nombreInput.value.trim();
    const email = emailInputCliente.value.trim();
    const telefono = telefonoInput.value.trim();
    const direccion = direccionInput.value.trim();

    limpiarMensaje();

    if (!validarCliente(nombre, email, telefono, direccion)) {
        return;
    }

    if (id) {
        actualizarCliente(Number(id), nombre, email, telefono, direccion);
    } else {
        crearCliente(nombre, email, telefono, direccion);
    }
});

clientesTableBody.addEventListener("click", (event) => {
    const boton = event.target.closest("button");

    if (!boton) {
        return;
    }

    const id = Number(boton.dataset.id);
    const action = boton.dataset.action;

    if (action === "edit") {
        cargarClienteEnFormulario(id);
    }

    if (action === "delete") {
        const confirmar = window.confirm("¿Seguro que quieres eliminar este cliente?");

        if (confirmar) {
            eliminarCliente(id, (clientesActualizados) => {
                pintarClientes(clientesActualizados);
                mostrarMensaje("Cliente eliminado correctamente.", "success");
            });
        }
    }
});

buscadorClientes.addEventListener("input", () => {
    const clientesFiltrados = filtrarClientes(buscadorClientes.value);
    pintarClientes(clientesFiltrados);
});

cancelButton.addEventListener("click", () => {
    limpiarFormulario();
    ocultarFormulario();
});

logoutButton.addEventListener("click", () => {
    cerrarSesion();
});

newClientButton.addEventListener("click", () => {
    limpiarFormulario();
    mostrarFormulario();

    setTimeout(() => {
        irAlFormulario();
    }, 150);
});

window.addEventListener("load", () => {
    cargarClientes();
    pintarClientes(clientes);
});