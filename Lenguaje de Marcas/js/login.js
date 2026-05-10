const loginForm = document.querySelector("#loginForm");
const emailInput = document.querySelector("#email");
const passwordInput = document.querySelector("#password");
const loginError = document.querySelector("#loginError");
const viewportInfo = document.querySelector("#viewportInfo");

function mostrarError(mensaje) {
    loginError.textContent = mensaje;
}

function limpiarError() {
    loginError.textContent = "";
}

function actualizarViewport() {
    viewportInfo.textContent = `${window.innerWidth}px x ${window.innerHeight}px`;
}

function mostrarMensajeConCallback(mensaje, callback) {
    loginError.textContent = mensaje;

    setTimeout(function () {
        callback();
    }, 700);
}

const redirigirDespuesDelLogin = () => {
    window.location.href = "dashboard.html";
};

loginForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();

    limpiarError();

    if (email === "" || password === "") {
        mostrarError("Debes completar todos los campos.");
        return;
    }

    const usuarioEncontrado = validarLogin(email, password);

    if (!usuarioEncontrado) {
        mostrarError("Email o contraseña incorrectos.");
        return;
    }

    guardarUsuarioActivo(usuarioEncontrado);

    mostrarMensajeConCallback("Login correcto. Redirigiendo...", redirigirDespuesDelLogin);
});

window.addEventListener("load", () => {
    actualizarViewport();

    const usuarioActivo = obtenerUsuarioActivo();

    if (usuarioActivo) {
        window.location.href = "dashboard.html";
    }
});

window.addEventListener("resize", () => {
    actualizarViewport();
});