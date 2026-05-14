import { loadData, openCreate, closeCreate } from './methods.js';

if (window.protegerPagina) window.protegerPagina();

const STORAGE_KEY = 'misVentas';
const DETAILS_KEY = 'misDetallesVentas';
const FIELDS = ['id', 'cliente', 'usuario', 'fecha', 'estado', 'total'];

const btnCreate = document.getElementById('btn__newSale');
const btnClose = document.querySelector('.icon__close');
const btnSubmit = document.getElementById('btn--submitSale');

let sales = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];
let details = JSON.parse(localStorage.getItem(DETAILS_KEY)) || [];

document.addEventListener('DOMContentLoaded', () => {
    loadData(sales, FIELDS, STORAGE_KEY, null);
    setupSelectors();
    setupSearch();
});

btnCreate?.addEventListener('click', () => openCreate('s-section', 's-creation'));
btnClose?.addEventListener('click', () => closeCreate('s-section', 's-creation'));

btnSubmit?.addEventListener('click', (e) => {
    e.preventDefault();
    createSale();
});

function setupSelectors() {
    const clienteSel = document.getElementById('cliente');
    const usuarioSel = document.getElementById('usuario');
    const cocheSel = document.getElementById('coche');

    const clientes = JSON.parse(sessionStorage.getItem('clientes')) || [];
    const usuarios = JSON.parse(sessionStorage.getItem('usuarios')) || [];
    const coches = JSON.parse(localStorage.getItem('misCoches')) || [];

    clientes.forEach(c => {
        const opt = document.createElement('option');
        opt.value = c.nombre;
        opt.textContent = c.nombre;
        clienteSel.appendChild(opt);
    });

    usuarios.forEach(u => {
        const opt = document.createElement('option');
        opt.value = u.nombre;
        opt.textContent = u.nombre;
        usuarioSel.appendChild(opt);
    });

    coches.forEach(c => {
        const opt = document.createElement('option');
        opt.value = JSON.stringify({ id: c.id, marca: c.marca, modelo: c.modelo, precio: c.precio });
        opt.textContent = `${c.marca} ${c.modelo} - ${c.precio}€`;
        cocheSel.appendChild(opt);
    });
}

function createSale() {
    const cliente = document.getElementById('cliente').value;
    const usuario = document.getElementById('usuario').value;
    const estado = document.getElementById('estado').value;
    const cocheData = JSON.parse(document.getElementById('coche').value || '{}');
    const cantidad = parseInt(document.getElementById('cantidad').value);

    if (!cliente || !usuario || !cocheData.id || isNaN(cantidad)) {
        alert('Por favor, rellena todos los campos.');
        return;
    }

    const total = cocheData.precio * cantidad;
    const fecha = new Date().toLocaleDateString();
    const saleId = sales.length > 0 ? Math.max(...sales.map(s => s.id)) + 1 : 1;

    const newSale = {
        id: saleId,
        cliente,
        usuario,
        fecha,
        estado,
        total: `${total}€`
    };

    const newDetail = {
        id: details.length > 0 ? Math.max(...details.map(d => d.id)) + 1 : 1,
        saleId: saleId,
        cliente,
        coche: `${cocheData.marca} ${cocheData.modelo}`,
        cantidad,
        total: `${total}€`
    };

    sales.push(newSale);
    details.push(newDetail);

    localStorage.setItem(STORAGE_KEY, JSON.stringify(sales));
    localStorage.setItem(DETAILS_KEY, JSON.stringify(details));

    loadData(sales, FIELDS, STORAGE_KEY, null);
    closeCreate('s-section', 's-creation');
    document.getElementById('saleForm').reset();
}

function setupSearch() {
    const searchInput = document.querySelector('.table__interaction .input');
    searchInput?.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
        const filtered = sales.filter(s =>
            Object.values(s).some(v => String(v).toLowerCase().includes(query))
        );
        loadData(filtered, FIELDS, STORAGE_KEY, null);
    });
}
