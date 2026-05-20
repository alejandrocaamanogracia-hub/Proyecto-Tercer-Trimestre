import { loadData, openCreate, closeCreate, updateSidebarBadges } from './methods.js';

if (window.protegerPagina) window.protegerPagina();

const STORAGE_KEY = 'misVentas';
const DETAILS_KEY = 'misDetallesVentas';
const FIELDS = ['id', 'cliente', 'usuario', 'fecha', 'estado', 'total'];

const btnCreate = document.getElementById('btn__newSale');
const btnClose = document.querySelector('.icon__close');
const btnSubmit = document.getElementById('btn--submitSale');
const btnAddCar = document.getElementById('btn--addCar');
const carsListContainer = document.getElementById('carsList');

let sales = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];
let details = JSON.parse(localStorage.getItem(DETAILS_KEY)) || [];
let selectedCars = [];
let editingSaleId = null;

document.addEventListener('DOMContentLoaded', () => {
    if (sales.length === 0) {
        sales = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];
    }
    if (details.length === 0) {
        details = JSON.parse(localStorage.getItem(DETAILS_KEY)) || [];
    }
    loadData(sales, FIELDS, STORAGE_KEY, updateSale);
    setupSelectors();
    setupSearch();
});

btnCreate?.addEventListener('click', () => {
    clearSaleForm();
    openCreate('s-section', 's-creation');
});
btnClose?.addEventListener('click', () => {
    closeCreate('s-section', 's-creation');
    clearSaleForm();
});

btnSubmit?.addEventListener('click', (e) => {
    e.preventDefault();
    if (editingSaleId === null) {
        createSale();
    } else {
        saveEditSale();
    }
});

function clearSaleForm() {
    document.getElementById('saleForm').reset();
    selectedCars = [];
    renderSelectedCars();
    editingSaleId = null;

    if (btnSubmit) {
        btnSubmit.textContent = 'Registrar Venta';
    }

    const heading = document.querySelector('.creation .heading');
    if (heading) {
        heading.innerHTML = `
            Crear una venta
            <span class="material-symbols-rounded sidebar__icon icon__close">close</span>
        `;
        const btnCloseNew = heading.querySelector('.icon__close');
        btnCloseNew?.addEventListener('click', () => {
            closeCreate('s-section', 's-creation');
            clearSaleForm();
        });
    }
}

function updateSale(item) {
    editingSaleId = item.id;

    const currentDetails = JSON.parse(localStorage.getItem(DETAILS_KEY)) || [];
    const saleDetails = currentDetails.filter(d => d.saleId === item.id);
    const coches = JSON.parse(localStorage.getItem('coches')) || [];

    selectedCars = saleDetails.map(d => {
        const matchedCar = coches.find(c => `${c.marca} ${c.modelo}` === d.coche);
        let cocheData;
        if (matchedCar) {
            cocheData = {
                id: matchedCar.id,
                marca: matchedCar.marca,
                modelo: matchedCar.modelo,
                precio: matchedCar.precio
            };
        } else {
            const parts = d.coche.split(' ');
            const marca = parts[0] || 'Desconocido';
            const modelo = parts.slice(1).join(' ') || 'Coche';
            const precio = parseFloat(d.total) / d.cantidad || 0;
            cocheData = {
                id: null,
                marca,
                modelo,
                precio
            };
        }
        return {
            cocheData,
            cantidad: d.cantidad
        };
    });

    renderSelectedCars();

    const clienteSel = document.getElementById('cliente');
    const usuarioSel = document.getElementById('usuario');
    const estadoSel = document.getElementById('estado');

    if (clienteSel) clienteSel.value = item.cliente;
    if (usuarioSel) usuarioSel.value = item.usuario;
    if (estadoSel) estadoSel.value = item.estado;

    const heading = document.querySelector('.creation .heading');
    if (heading) {
        heading.innerHTML = `
            Editar venta #${item.id}
            <span class="material-symbols-rounded sidebar__icon icon__close">close</span>
        `;
        const btnCloseNew = heading.querySelector('.icon__close');
        btnCloseNew?.addEventListener('click', () => {
            closeCreate('s-section', 's-creation');
            clearSaleForm();
        });
    }

    if (btnSubmit) {
        btnSubmit.textContent = 'Guardar cambios';
    }

    openCreate('s-section', 's-creation');
}

function saveEditSale() {
    const cliente = document.getElementById('cliente').value;
    const usuario = document.getElementById('usuario').value;
    const estado = document.getElementById('estado').value;

    if (!cliente || !usuario) {
        alert('Por favor, selecciona cliente y usuario.');
        return;
    }

    if (selectedCars.length === 0) {
        alert('Por favor, añade al menos un coche a la venta.');
        return;
    }

    let totalSale = 0;
    let allDetails = JSON.parse(localStorage.getItem(DETAILS_KEY)) || [];
    allDetails = allDetails.filter(d => d.saleId !== editingSaleId);

    selectedCars.forEach(item => {
        const totalDetalle = item.cocheData.precio * item.cantidad;
        totalSale += totalDetalle;

        const newDetail = {
            id: editingSaleId,
            saleId: editingSaleId,
            cliente,
            coche: `${item.cocheData.marca} ${item.cocheData.modelo}`,
            cantidad: item.cantidad,
            total: `${totalDetalle}€`
        };
        allDetails.push(newDetail);
    });

    const index = sales.findIndex(s => s.id === editingSaleId);
    if (index !== -1) {
        sales[index] = {
            ...sales[index],
            cliente,
            usuario,
            estado,
            total: `${totalSale}€`
        };

        localStorage.setItem(STORAGE_KEY, JSON.stringify(sales));
        localStorage.setItem(DETAILS_KEY, JSON.stringify(allDetails));

        details = allDetails;

        loadData(sales, FIELDS, STORAGE_KEY, updateSale);
        updateSidebarBadges();
        closeCreate('s-section', 's-creation');
        clearSaleForm();
    }
}

btnAddCar?.addEventListener('click', () => {
    const cocheValue = document.getElementById('coche').value;
    const cantidad = parseInt(document.getElementById('cantidad').value);

    if (!cocheValue || isNaN(cantidad) || cantidad <= 0) {
        alert('Por favor, selecciona un coche y una cantidad válida.');
        return;
    }

    const cocheData = JSON.parse(cocheValue);
    
    selectedCars.push({
        cocheData,
        cantidad
    });

    renderSelectedCars();
});

function renderSelectedCars() {
    if (!carsListContainer) return;
    carsListContainer.innerHTML = '';
    selectedCars.forEach((item, index) => {
        const div = document.createElement('div');
        div.style.cssText = "display: flex; justify-content: space-between; padding: 5px 0; border-bottom: 1px solid rgba(255,255,255,0.1);";
        div.innerHTML = `
            <span>${item.cantidad}x ${item.cocheData.marca} ${item.cocheData.modelo}</span>
            <span>${item.cocheData.precio * item.cantidad}€ <button type="button" onclick="window.removeCar(${index})" style="background: none; border: none; color: #ff6b6b; cursor: pointer; margin-left: 10px;">x</button></span>
        `;
        carsListContainer.appendChild(div);
    });
}

window.removeCar = function(index) {
    selectedCars.splice(index, 1);
    renderSelectedCars();
};

// Metodo para obtener los datos de los para poder modificarlos.

function setupSelectors() {
    const clienteSel = document.getElementById('cliente');
    const usuarioSel = document.getElementById('usuario');
    const cocheSel = document.getElementById('coche');

    const clientes = JSON.parse(localStorage.getItem('clientes')) || [];
    const usuarios = JSON.parse(localStorage.getItem('usuarios')) || [];
    const coches = JSON.parse(localStorage.getItem('coches')) || [];

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

// Metodo para crear una venta.

function createSale() {

    // Se obtienen los datos de los selectores y se valida que no esten vacios.

    const cliente = document.getElementById('cliente').value;
    const usuario = document.getElementById('usuario').value;
    const estado = document.getElementById('estado').value;

    if (!cliente || !usuario) {
        alert('Por favor, selecciona cliente y usuario.');
        return;
    }

    if (selectedCars.length === 0) {
        alert('Por favor, añade al menos un coche a la venta.');
        return;
    }

    // Se calcula el total de la venta.

    let totalSale = 0;
    const fecha = new Date().toLocaleDateString();
    const saleId = sales.length > 0 ? Math.max(...sales.map(s => s.id)) + 1 : 1;

    // Se iteran los coches seleccionados para crear los detalles de venta
    selectedCars.forEach(item => {
        const totalDetalle = item.cocheData.precio * item.cantidad;
        totalSale += totalDetalle;

        const newDetail = {
            id: saleId,
            saleId: saleId,
            cliente,
            coche: `${item.cocheData.marca} ${item.cocheData.modelo}`,
            cantidad: item.cantidad,
            total: `${totalDetalle}€`
        };
        details.push(newDetail);
    });

    // Se crea una nueva venta y un nuevo detalle.

    const newSale = {
        id: saleId,
        cliente,
        usuario,
        fecha,
        estado,
        total: `${totalSale}€`
    };

    sales.push(newSale);

    localStorage.setItem(STORAGE_KEY, JSON.stringify(sales));
    localStorage.setItem(DETAILS_KEY, JSON.stringify(details));

    loadData(sales, FIELDS, STORAGE_KEY, updateSale);
    updateSidebarBadges();
    closeCreate('s-section', 's-creation');
    document.getElementById('saleForm').reset();
    
    selectedCars = [];
    renderSelectedCars();
}

// Metodo para buscar en la tabla.

function setupSearch() {
    const searchInput = document.querySelector('.table__interaction .input');
    searchInput?.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
        const filtered = sales.filter(s =>
            Object.values(s).some(v => String(v).toLowerCase().includes(query))
        );
        loadData(filtered, FIELDS, STORAGE_KEY, updateSale);
    });
}
