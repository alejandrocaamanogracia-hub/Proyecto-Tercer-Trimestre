import { loadData } from './methods.js';
import { openCreate } from './methods.js';
import { closeCreate } from './methods.js';

const btnCreate = document.getElementById('btn__newCar');
const btnClose = document.querySelector('.icon__close');
const carCreation = document.getElementById('c-creation');

const btnSubmit = document.getElementById('btn--submitCar');

btnClose.addEventListener('click', () => {
    closeCreate('c-section', 'c-creation');
});

class car {
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

let cars = JSON.parse(localStorage.getItem('misCoches')) || [
    new car(1, 'Toyota', 'Corolla', 2020, 'Automático', 20000, 'Gasolina'),
    new car(2, 'Honda', 'Civic', 2019, 'Manual', 18000, 'Gasolina')
];

btnCreate.addEventListener('click', () => {
    openCreate('c-section', 'c-creation');
});

document.addEventListener('DOMContentLoaded', () => {
    loadData(cars, ['id', 'marca', 'modelo', 'anio', 'cambio', 'combustible', 'precio']
    );
});

function newCar() {

    const id = cars.length > 0 ? cars[cars.length - 1].id + 1 : 1;

    const marca = document.getElementById('brand').value;
    const modelo = document.getElementById('model').value;
    const anio = parseInt(document.getElementById('year').value);
    const cambio = document.getElementById('cambio').value;
    const combustible = document.getElementById('combustible').value;
    const precio = parseFloat(document.getElementById('precio').value);

    const nuevoCoche = new car(id, marca, modelo, anio, cambio, precio, combustible);
    
    cars.push(nuevoCoche);

    localStorage.setItem('misCoches', JSON.stringify(cars));

    loadData(cars, ['id', 'marca', 'modelo', 'anio', 'cambio', 'combustible', 'precio']);
    closeCreate('c-section', 'c-creation');

}

if (btnSubmit) {
    btnSubmit.addEventListener('click', (e) => {
        e.preventDefault(); 
        newCar();
    });
}