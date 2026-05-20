import { loadData, openCreate, closeCreate, updateSidebarBadges } from './methods.js';

if (window.protegerPagina) window.protegerPagina();

const STORAGE_KEY = 'coches';
const FIELDS = ['id', 'marca', 'modelo', 'anio', 'cambio', 'combustible', 'precio'];

const btnCreate = document.getElementById('btn__newCar');
const btnClose = document.querySelector('.icon__close');
const btnSubmit = document.getElementById('btn--submitCar');

class Car {
    constructor(id, marca, modelo, anio, cambio, precio, combustible) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.cambio = cambio;
        this.combustible = combustible;
    }
}

let cars = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];

document.addEventListener('DOMContentLoaded', () => {
    if (cars.length === 0) {
        cars = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];
    }
    loadData(cars, FIELDS, STORAGE_KEY, updateCar);
    setupSearch();
});

btnCreate?.addEventListener('click', () => openCreate('c-section', 'c-creation'));

btnClose?.addEventListener('click', () => {
    closeCreate('c-section', 'c-creation');
    clearForm();
});

btnSubmit?.addEventListener('click', (e) => {
    e.preventDefault();
    newCar();
});

// Metodo para obtener el siguiente id.

function getNextId() {
    return cars.length > 0 ? Math.max(...cars.map(c => c.id)) + 1 : 1;
}

// Metodo para obtener los valores del formulario.

function getFormValues() {
    return {
        marca: document.getElementById('brand')?.value.trim(),
        modelo: document.getElementById('model')?.value.trim(),
        anio: parseInt(document.getElementById('year')?.value),
        cambio: document.getElementById('cambio')?.value,
        combustible: document.getElementById('combustible')?.value,
        precio: parseFloat(document.getElementById('precio')?.value)
    };
}

// Metodo para validar el formulario.

function validateForm({ marca, modelo, anio, cambio, combustible, precio }) {
    if (!marca || !modelo || !cambio || !combustible) return 'Rellena todos los campos de texto.';
    if (isNaN(anio) || anio < 1900 || anio > new Date().getFullYear() + 1) return 'Año no válido.';
    if (isNaN(precio) || precio <= 0) return 'Precio no válido.';
    return null;
}

// Metodo para crear un nuevo coche.

function newCar() {
    const values = getFormValues();
    const error = validateForm(values);

    if (error) {
        showFormError(error);
        return;
    }

    const nuevoCoche = new Car(
        getNextId(),
        values.marca,
        values.modelo,
        values.anio,
        values.cambio,
        values.precio,
        values.combustible
    );

    cars.push(nuevoCoche);
    saveAndRender();
    closeCreate('c-section', 'c-creation');
    clearForm();
}

// Metodo para actualizar un coche.

function updateCar(item) {

    document.getElementById('brand').value = item.marca;
    document.getElementById('model').value = item.modelo;
    document.getElementById('year').value = item.anio;
    document.getElementById('cambio').value = item.cambio;
    document.getElementById('combustible').value = item.combustible;
    document.getElementById('precio').value = item.precio;

    openCreate('c-section', 'c-creation');

    const h2Title = document.getElementById('create__title');
    h2Title.innerHTML = `
        Editar coche ${item.id}
        <span class="material-symbols-rounded sidebar__icon icon__close">close</span>
    `;
    h2Title.querySelector('.icon__close')?.addEventListener('click', () => {
        closeCreate('c-section', 'c-creation');
        clearForm();
    });

    const newSubmit = btnSubmit.cloneNode(true);
    btnSubmit.parentNode.replaceChild(newSubmit, btnSubmit);

    newSubmit.textContent = 'Guardar cambios';
    newSubmit.addEventListener('click', (e) => {
        e.preventDefault();
        const values = getFormValues();
        const error = validateForm(values);
        if (error) { showFormError(error); return; }

        const index = cars.findIndex(c => c.id === item.id);
        if (index !== -1) {
            cars[index] = { ...cars[index], ...values };
            saveAndRender();
            closeCreate('c-section', 'c-creation');
            clearForm();
            newSubmit.textContent = 'Crear coche';
        }
    });
}

// Metodo para guardar y renderizar los datos.

function saveAndRender() {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(cars));
    loadData(cars, FIELDS, STORAGE_KEY, updateCar);
}

// Metodo para limpiar el formulario.

function clearForm() {
    ['brand', 'model', 'year', 'precio'].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.value = '';
    });
    ['cambio', 'combustible'].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.selectedIndex = 0;
    });
    const errorEl = document.getElementById('form-error');
    if (errorEl) errorEl.textContent = '';

    const submit = document.getElementById('btn--submitCar');
    if (submit) submit.textContent = 'Crear coche';
}

// Metodo para mostrar un error en el formulario.

function showFormError(msg) {
    let errorEl = document.getElementById('form-error');
    if (!errorEl) {
        errorEl = document.createElement('p');
        errorEl.id = 'form-error';
        errorEl.style.cssText = 'color:#ffd2d2; font-size:0.9rem; font-weight:700; margin:0;';
        btnSubmit.insertAdjacentElement('beforebegin', errorEl);
    }
    errorEl.textContent = msg;
}

// Metodo para buscar en la tabla.

function setupSearch() {
    const searchInput = document.querySelector('.table__interaction .input');
    searchInput?.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
        const filtered = cars.filter(c =>
            Object.values(c).some(v => String(v).toLowerCase().includes(query))
        );
        loadData(filtered, FIELDS, STORAGE_KEY, updateCar);
    });
}
