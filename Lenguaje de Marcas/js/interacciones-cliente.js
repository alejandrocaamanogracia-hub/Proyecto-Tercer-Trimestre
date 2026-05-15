protegerPagina();

class InteraccionCliente {
    constructor(id, clienteId, usuarioId, tipo, fecha, asunto, descripcion, resultado, proximaAccion, fechaProxima) {
        this.id = id;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.fecha = fecha;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.resultado = resultado;
        this.proximaAccion = proximaAccion;
        this.fechaProxima = fechaProxima;
    }
}

const interaccionForm = document.querySelector("#interaccionForm");
const interaccionIdInput = document.querySelector("#interaccionId");
const clienteIdInput = document.querySelector("#clienteId");
const usuarioIdInput = document.querySelector("#usuarioId");
const tipoInteraccionInput = document.querySelector("#tipoInteraccion");
const fechaInteraccionInput = document.querySelector("#fechaInteraccion");
const asuntoInteraccionInput = document.querySelector("#asuntoInteraccion");
const descripcionInteraccionInput = document.querySelector("#descripcionInteraccion");
const resultadoInteraccionInput = document.querySelector("#resultadoInteraccion");
const proximaAccionInteraccionInput = document.querySelector("#proximaAccionInteraccion");
const fechaProximaInteraccionInput = document.querySelector("#fechaProximaInteraccion");
const interaccionMensaje = document.querySelector("#interaccionMensaje");

const interaccionesTableBody = document.querySelector("#interaccionesTableBody");
const buscadorInteracciones = document.querySelector("#buscadorInteracciones");
const interaccionFormPanel = document.querySelector("#interaccionFormPanel");
const newInteractionButton = document.querySelector("#newInteractionButton");
const submitInteractionButton = document.querySelector("#submitInteractionButton");
const cancelInteractionButton = document.querySelector("#cancelInteractionButton");
const logoutButton = document.querySelector("#logoutButton");

const calendarGrid = document.querySelector("#calendarGrid");
const calendarTitle = document.querySelector("#calendarTitle");
const prevMonthButton = document.querySelector("#prevMonthButton");
const nextMonthButton = document.querySelector("#nextMonthButton");

let interacciones = [];
let clientes = [];
let usuarios = [];
let fechaCalendario = new Date();

function cargarClientesAuxiliares() {
    const clientesGuardados = localStorage.getItem("clientes");

    if (clientesGuardados) {
        clientes = JSON.parse(clientesGuardados);
    }

    if (clientes.length === 0) {
        clientes = [
            { id: 1, nombre: "Carlos Martínez" },
            { id: 2, nombre: "Laura Sánchez" },
            { id: 3, nombre: "Miguel Torres" }
        ];
    }
}

function cargarUsuariosAuxiliares() {
    const usuariosGuardados = localStorage.getItem("usuarios");

    if (usuariosGuardados) {
        usuarios = JSON.parse(usuariosGuardados);
    }

    if (usuarios.length === 0) {
        usuarios = [
            { id: 1, nombre: "Admin Principal" },
            { id: 2, nombre: "Pedro López" },
            { id: 3, nombre: "Marta Fernández" }
        ];
    }
}

function pintarSelects() {
    clienteIdInput.innerHTML = `<option value="">Selecciona un cliente</option>`;
    usuarioIdInput.innerHTML = `<option value="">Selecciona un usuario</option>`;

    clientes.forEach((cliente) => {
        clienteIdInput.innerHTML += `<option value="${cliente.id}">${cliente.nombre}</option>`;
    });

    usuarios.forEach((usuario) => {
        usuarioIdInput.innerHTML += `<option value="${usuario.id}">${usuario.nombre}</option>`;
    });
}

function obtenerNombreCliente(id) {
    const cliente = clientes.find((clienteItem) => Number(clienteItem.id) === Number(id));
    return cliente ? cliente.nombre : "Cliente no encontrado";
}

function obtenerNombreUsuario(id) {
    const usuario = usuarios.find((usuarioItem) => Number(usuarioItem.id) === Number(id));
    return usuario ? usuario.nombre : "Usuario no encontrado";
}

function cargarInteracciones() {
    const interaccionesGuardadas = localStorage.getItem("interaccionesCliente");

    if (interaccionesGuardadas) {
        interacciones = JSON.parse(interaccionesGuardadas);

        if (interacciones.length > 0) {
            return;
        }
    }

    interacciones = [
        new InteraccionCliente(
            1,
            1,
            2,
            "Llamada",
            "2026-04-09T10:30",
            "Interés en financiación",
            "El cliente pregunta por financiación y garantía.",
            "Interesado",
            "Enviar propuesta de financiación",
            "2026-04-11T12:00"
        ),
        new InteraccionCliente(
            2,
            2,
            3,
            "Email",
            "2026-04-10T16:15",
            "Consulta vehículo eléctrico",
            "La clienta solicita ficha técnica y disponibilidad.",
            "Respondido",
            "Agendar prueba de conducción",
            "2026-04-14T11:00"
        ),
        new InteraccionCliente(
            3,
            3,
            2,
            "WhatsApp",
            "2026-04-12T09:45",
            "Reserva de vehículo",
            "El cliente confirma interés y solicita reservar.",
            "Reservado",
            "Preparar documentación de reserva",
            "2026-04-16T10:00"
        )
    ];

    guardarInteracciones();
}

function guardarInteracciones() {
    localStorage.setItem("interaccionesCliente", JSON.stringify(interacciones));
    if (window.updateSidebarBadges) window.updateSidebarBadges();
}

function generarNuevoIdInteraccion() {
    if (interacciones.length === 0) {
        return 1;
    }

    return Math.max(...interacciones.map(function (interaccion) {
        return interaccion.id;
    })) + 1;
}

function formatearFecha(fecha) {
    if (!fecha) {
        return "-";
    }

    return fecha.replace("T", " ");
}

function obtenerFechaDia(fecha) {
    if (!fecha) {
        return "";
    }

    return fecha.split("T")[0];
}

function mostrarMensajeInteraccion(mensaje, tipo) {
    interaccionMensaje.textContent = mensaje;
    interaccionMensaje.className = `int-interacciones__message int-interacciones__message--${tipo}`;
}

function limpiarMensajeInteraccion() {
    interaccionMensaje.textContent = "";
    interaccionMensaje.className = "int-interacciones__message";
}

function validarInteraccion(clienteId, usuarioId, tipo, fecha, asunto, descripcion, resultado) {
    if (
        clienteId === "" ||
        usuarioId === "" ||
        tipo === "" ||
        fecha === "" ||
        asunto === "" ||
        descripcion === "" ||
        resultado === ""
    ) {
        mostrarMensajeInteraccion("Todos los campos principales son obligatorios.", "error");
        return false;
    }

    return true;
}

function limpiarFormularioInteraccion() {
    interaccionIdInput.value = "";
    clienteIdInput.value = "";
    usuarioIdInput.value = "";
    tipoInteraccionInput.value = "";
    fechaInteraccionInput.value = "";
    asuntoInteraccionInput.value = "";
    descripcionInteraccionInput.value = "";
    resultadoInteraccionInput.value = "";
    proximaAccionInteraccionInput.value = "";
    fechaProximaInteraccionInput.value = "";
    submitInteractionButton.textContent = "Guardar interacción";
    limpiarMensajeInteraccion();
}

function pintarInteracciones(listaInteracciones) {
    interaccionesTableBody.innerHTML = "";

    if (listaInteracciones.length === 0) {
        interaccionesTableBody.innerHTML = `
            <tr>
                <td colspan="9" class="int-interacciones__empty">No hay interacciones para mostrar.</td>
            </tr>
        `;
        return;
    }

    listaInteracciones.forEach((interaccion) => {
        const fila = document.createElement("tr");
        fila.classList.add("int-interacciones__row");

        fila.innerHTML = `
            <td data-label="ID">${interaccion.id}</td>
            <td data-label="Cliente">${obtenerNombreCliente(interaccion.clienteId)}</td>
            <td data-label="Usuario">${obtenerNombreUsuario(interaccion.usuarioId)}</td>
            <td data-label="Tipo">${interaccion.tipo}</td>
            <td data-label="Fecha">${formatearFecha(interaccion.fecha)}</td>
            <td data-label="Asunto">${interaccion.asunto}</td>
            <td data-label="Próxima acción">${interaccion.proximaAccion || "-"}</td>
            <td data-label="Fecha próxima">${formatearFecha(interaccion.fechaProxima)}</td>
            <td data-label="Acciones">
                <div class="int-interacciones__table-actions">
                    <button class="int-interacciones__icon-button int-interacciones__icon-button--edit" data-action="edit" data-id="${interaccion.id}">
                        Editar
                    </button>
                    <button class="int-interacciones__icon-button int-interacciones__icon-button--delete" data-action="delete" data-id="${interaccion.id}">
                        Eliminar
                    </button>
                </div>
            </td>
        `;

        interaccionesTableBody.appendChild(fila);
    });
}

function crearInteraccion(clienteId, usuarioId, tipo, fecha, asunto, descripcion, resultado, proximaAccion, fechaProxima) {
    const nuevaInteraccion = new InteraccionCliente(
        generarNuevoIdInteraccion(),
        Number(clienteId),
        Number(usuarioId),
        tipo,
        fecha,
        asunto,
        descripcion,
        resultado,
        proximaAccion,
        fechaProxima
    );

    interacciones.push(nuevaInteraccion);
    guardarInteracciones();
    pintarInteracciones(interacciones);
    pintarCalendario();
    limpiarFormularioInteraccion();
    ocultarFormularioInteraccion();
    mostrarMensajeInteraccion("Interacción creada correctamente.", "success");
}

function actualizarInteraccion(id, clienteId, usuarioId, tipo, fecha, asunto, descripcion, resultado, proximaAccion, fechaProxima) {
    interacciones = interacciones.map((interaccion) => {
        if (interaccion.id === id) {
            return new InteraccionCliente(
                id,
                Number(clienteId),
                Number(usuarioId),
                tipo,
                fecha,
                asunto,
                descripcion,
                resultado,
                proximaAccion,
                fechaProxima
            );
        }

        return interaccion;
    });

    guardarInteracciones();
    pintarInteracciones(interacciones);
    pintarCalendario();
    limpiarFormularioInteraccion();
    ocultarFormularioInteraccion();
    mostrarMensajeInteraccion("Interacción actualizada correctamente.", "success");
}

function cargarInteraccionEnFormulario(id) {
    const interaccionEncontrada = interacciones.find((interaccion) => interaccion.id === id);

    if (!interaccionEncontrada) {
        mostrarMensajeInteraccion("Interacción no encontrada.", "error");
        return;
    }

    mostrarFormularioInteraccion();

    interaccionIdInput.value = interaccionEncontrada.id;
    clienteIdInput.value = interaccionEncontrada.clienteId;
    usuarioIdInput.value = interaccionEncontrada.usuarioId;
    tipoInteraccionInput.value = interaccionEncontrada.tipo;
    fechaInteraccionInput.value = interaccionEncontrada.fecha;
    asuntoInteraccionInput.value = interaccionEncontrada.asunto;
    descripcionInteraccionInput.value = interaccionEncontrada.descripcion;
    resultadoInteraccionInput.value = interaccionEncontrada.resultado;
    proximaAccionInteraccionInput.value = interaccionEncontrada.proximaAccion;
    fechaProximaInteraccionInput.value = interaccionEncontrada.fechaProxima;
    submitInteractionButton.textContent = "Actualizar interacción";
    mostrarMensajeInteraccion("Editando interacción seleccionada.", "info");

    setTimeout(() => {
        irAlFormularioInteraccion();
    }, 150);
}

function eliminarInteraccion(id, callback) {
    interacciones = interacciones.filter((interaccion) => interaccion.id !== id);
    guardarInteracciones();
    callback(interacciones);
}

function filtrarInteracciones(textoBusqueda) {
    const texto = textoBusqueda.toLowerCase();

    return interacciones.filter((interaccion) => {
        return obtenerNombreCliente(interaccion.clienteId).toLowerCase().includes(texto) ||
            obtenerNombreUsuario(interaccion.usuarioId).toLowerCase().includes(texto) ||
            interaccion.tipo.toLowerCase().includes(texto) ||
            interaccion.asunto.toLowerCase().includes(texto) ||
            interaccion.resultado.toLowerCase().includes(texto) ||
            interaccion.proximaAccion.toLowerCase().includes(texto);
    });
}

function mostrarFormularioInteraccion() {
    interaccionFormPanel.classList.remove("int-interacciones__panel--hidden");
}

function ocultarFormularioInteraccion() {
    interaccionFormPanel.classList.add("int-interacciones__panel--hidden");
}

function irAlFormularioInteraccion() {
    interaccionFormPanel.scrollIntoView({
        behavior: "smooth",
        block: "start"
    });
}

function pintarCalendario() {
    calendarGrid.innerHTML = "";

    const year = fechaCalendario.getFullYear();
    const month = fechaCalendario.getMonth();

    const nombreMes = fechaCalendario.toLocaleDateString("es-ES", {
        month: "long",
        year: "numeric"
    });

    calendarTitle.textContent = nombreMes.charAt(0).toUpperCase() + nombreMes.slice(1);

    const primerDiaMes = new Date(year, month, 1);
    const ultimoDiaMes = new Date(year, month + 1, 0);
    const totalDias = ultimoDiaMes.getDate();

    let diaSemanaInicio = primerDiaMes.getDay();

    if (diaSemanaInicio === 0) {
        diaSemanaInicio = 7;
    }

    for (let i = 1; i < diaSemanaInicio; i++) {
        const celdaVacia = document.createElement("div");
        celdaVacia.classList.add("int-interacciones__calendar-day", "int-interacciones__calendar-day--empty");
        calendarGrid.appendChild(celdaVacia);
    }

    for (let dia = 1; dia <= totalDias; dia++) {
        const fechaDia = `${year}-${String(month + 1).padStart(2, "0")}-${String(dia).padStart(2, "0")}`;
        const interaccionesDia = interacciones.filter((interaccion) => {
            return obtenerFechaDia(interaccion.fechaProxima) === fechaDia;
        });

        const celda = document.createElement("div");
        celda.classList.add("int-interacciones__calendar-day");

        celda.innerHTML = `
            <span class="int-interacciones__calendar-number">${dia}</span>
            <div class="int-interacciones__calendar-events"></div>
        `;

        const eventosContainer = celda.querySelector(".int-interacciones__calendar-events");

        interaccionesDia.forEach((interaccion) => {
            const evento = document.createElement("button");
            evento.classList.add("int-interacciones__calendar-event");
            evento.type = "button";
            evento.dataset.id = interaccion.id;
            evento.textContent = interaccion.proximaAccion || interaccion.asunto;
            eventosContainer.appendChild(evento);
        });

        calendarGrid.appendChild(celda);
    }
}

interaccionForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const id = interaccionIdInput.value;
    const clienteId = clienteIdInput.value;
    const usuarioId = usuarioIdInput.value;
    const tipo = tipoInteraccionInput.value;
    const fecha = fechaInteraccionInput.value;
    const asunto = asuntoInteraccionInput.value.trim();
    const descripcion = descripcionInteraccionInput.value.trim();
    const resultado = resultadoInteraccionInput.value.trim();
    const proximaAccion = proximaAccionInteraccionInput.value.trim();
    const fechaProxima = fechaProximaInteraccionInput.value;

    limpiarMensajeInteraccion();

    if (!validarInteraccion(clienteId, usuarioId, tipo, fecha, asunto, descripcion, resultado)) {
        return;
    }

    if (id) {
        actualizarInteraccion(Number(id), clienteId, usuarioId, tipo, fecha, asunto, descripcion, resultado, proximaAccion, fechaProxima);
    } else {
        crearInteraccion(clienteId, usuarioId, tipo, fecha, asunto, descripcion, resultado, proximaAccion, fechaProxima);
    }
});

interaccionesTableBody.addEventListener("click", (event) => {
    const boton = event.target.closest("button");

    if (!boton) {
        return;
    }

    const id = Number(boton.dataset.id);
    const action = boton.dataset.action;

    if (action === "edit") {
        cargarInteraccionEnFormulario(id);
    }

    if (action === "delete") {
        const confirmar = window.confirm("¿Seguro que quieres eliminar esta interacción?");

        if (confirmar) {
            eliminarInteraccion(id, (interaccionesActualizadas) => {
                pintarInteracciones(interaccionesActualizadas);
                pintarCalendario();
                mostrarMensajeInteraccion("Interacción eliminada correctamente.", "success");
            });
        }
    }
});

calendarGrid.addEventListener("click", (event) => {
    const evento = event.target.closest(".int-interacciones__calendar-event");

    if (!evento) {
        return;
    }

    cargarInteraccionEnFormulario(Number(evento.dataset.id));
});

buscadorInteracciones.addEventListener("input", () => {
    const interaccionesFiltradas = filtrarInteracciones(buscadorInteracciones.value);
    pintarInteracciones(interaccionesFiltradas);
});

cancelInteractionButton.addEventListener("click", () => {
    limpiarFormularioInteraccion();
    ocultarFormularioInteraccion();
});

logoutButton.addEventListener("click", () => {
    cerrarSesion();
});

newInteractionButton.addEventListener("click", () => {
    limpiarFormularioInteraccion();
    mostrarFormularioInteraccion();

    setTimeout(() => {
        irAlFormularioInteraccion();
    }, 150);
});

prevMonthButton.addEventListener("click", () => {
    fechaCalendario.setMonth(fechaCalendario.getMonth() - 1);
    pintarCalendario();
});

nextMonthButton.addEventListener("click", () => {
    fechaCalendario.setMonth(fechaCalendario.getMonth() + 1);
    pintarCalendario();
});

window.addEventListener("load", () => {
    cargarClientesAuxiliares();
    cargarUsuariosAuxiliares();
    pintarSelects();
    cargarInteracciones();
    pintarInteracciones(interacciones);
    pintarCalendario();
});