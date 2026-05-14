import { loadData, openCreate, closeCreate } from './methods.js';

if (window.protegerPagina) window.protegerPagina();

const STORAGE_KEY = 'misCoches';
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

let cars = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [
    new Car(1, 'Toyota', 'Corolla', 2020, 'Automático', 20000, 'Gasolina'),
    new Car(2, 'Honda', 'Civic', 2019, 'Manual', 18000, 'Gasolina')
];


if (!localStorage.getItem(STORAGE_KEY)) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(cars));
}

document.addEventListener('DOMContentLoaded', () => {
    loadData(cars, FIELDS, STORAGE_KEY, updateCar);
    setupSearch();
});

btnCreate?.addEventListener('click', () => openCreate('c-section', 'creation'));

btnClose?.addEventListener('click', () => {
    closeCreate('c-section', 'creation');
    clearForm();
});

btnSubmit?.addEventListener('click', (e) => {
    e.preventDefault();
    newCar();
});

function getNextId() {
    return cars.length > 0 ? Math.max(...cars.map(c => c.id)) + 1 : 1;
}

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

function validateForm({ marca, modelo, anio, cambio, combustible, precio }) {
    if (!marca || !modelo || !cambio || !combustible) return 'Rellena todos los campos de texto.';
    if (isNaN(anio) || anio < 1900 || anio > new Date().getFullYear() + 1) return 'Año no válido.';
    if (isNaN(precio) || precio <= 0) return 'Precio no válido.';
    return null;
}

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
    closeCreate('c-section', 'creation');
    clearForm();
}

function updateCar(item) {

    document.getElementById('brand').value = item.marca;
    document.getElementById('model').value = item.modelo;
    document.getElementById('year').value = item.anio;
    document.getElementById('cambio').value = item.cambio;
    document.getElementById('combustible').value = item.combustible;
    document.getElementById('precio').value = item.precio;

    openCreate('c-section', 'creation');

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
            closeCreate('c-section', 'creation');
            clearForm();
            newSubmit.textContent = 'Crear coche';
        }
    });
}

function saveAndRender() {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(cars));
    loadData(cars, FIELDS, STORAGE_KEY, updateCar);
}

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
