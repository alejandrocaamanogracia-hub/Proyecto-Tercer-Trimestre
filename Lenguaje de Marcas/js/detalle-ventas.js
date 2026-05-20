import { loadData, openCreate, closeCreate } from './methods.js';

if (window.protegerPagina) window.protegerPagina();

const STORAGE_KEY = 'misDetallesVentas';
const FIELDS = ['id', 'cliente', 'coche', 'cantidad', 'total'];

let details = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];
let editingId = null;

const btnClose = document.querySelector('.icon__close');

document.addEventListener('DOMContentLoaded', () => {
    if (details.length === 0) {
        details = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];
    }
    loadData(details, FIELDS, STORAGE_KEY, openEditPanel);
    setupSearch();
});

btnClose?.addEventListener('click', () => {
    closeCreate('sd-section', 'sd-creation');
    editingId = null;
});

function openEditPanel(item) {
    editingId = item.id;

    document.getElementById('edit-cantidad').value = item.cantidad;

    const heading = document.querySelector('.creation .heading');
    if (heading) {
        heading.innerHTML = `
            Editar detalle #${item.id}
            <span class="material-symbols-rounded sidebar__icon icon__close">close</span>
        `;
        heading.querySelector('.icon__close')?.addEventListener('click', () => {
            closeCreate('sd-section', 'sd-creation');
            editingId = null;
        });
    }

    openCreate('sd-section', 'sd-creation');
}

document.getElementById('btn--saveEdit')?.addEventListener('click', () => {

    const nuevaCantidad = parseInt(document.getElementById('edit-cantidad').value);
    if (!nuevaCantidad || nuevaCantidad < 1) {
        alert('Introduce una cantidad válida.');
        return;
    }

    details = details.map(d => {
        if (d.id === editingId) {
            const detalle = details.find(d => d.id === editingId);
            const coches = JSON.parse(localStorage.getItem('coches')) || [];
            const coche = coches.find(c => `${c.marca} ${c.modelo}` === detalle.coche);
            const precioCoche = coche ? coche.precio : 0;

            const nuevoTotal = precioCoche * nuevaCantidad;

            // Actualizar venta asociada
            const ventas = JSON.parse(localStorage.getItem('misVentas')) || [];
            const saleId = d.saleId;
            const ventaIndex = ventas.findIndex(v => v.id === saleId);

            if (ventaIndex !== -1) {
                const todosDetalles = details.map(det =>
                    det.id === editingId
                        ? { ...det, cantidad: nuevaCantidad, total: `${nuevoTotal}€` }
                        : det
                );
                const nuevoTotalVenta = todosDetalles
                    .filter(det => det.saleId === saleId)
                    .reduce((acc, det) => acc + parseFloat(det.total), 0);

                ventas[ventaIndex].total = `${nuevoTotalVenta}€`;
                localStorage.setItem('misVentas', JSON.stringify(ventas));
            }

            return { ...d, cantidad: nuevaCantidad, total: `${nuevoTotal}€` };
        }
        return d;
    });

    localStorage.setItem(STORAGE_KEY, JSON.stringify(details));
    loadData(details, FIELDS, STORAGE_KEY, openEditPanel);
    closeCreate('sd-section', 'sd-creation');
    editingId = null;
});

function setupSearch() {
    const searchInput = document.querySelector('.table__interaction .input');
    searchInput?.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
        const filtered = details.filter(d =>
            Object.values(d).some(v => String(v).toLowerCase().includes(query))
        );
        loadData(filtered, FIELDS, STORAGE_KEY, openEditPanel);
    });
}