import { loadData } from './methods.js';

if (window.protegerPagina) window.protegerPagina();

const STORAGE_KEY = 'misDetallesVentas';
const FIELDS = ['id', 'cliente', 'coche', 'cantidad', 'total'];

let details = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];

document.addEventListener('DOMContentLoaded', () => {
    if (details.length === 0) {
        details = JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];
    }
    loadData(details, FIELDS, STORAGE_KEY, null, false);
    setupSearch();
});

function setupSearch() {
    const searchInput = document.querySelector('.table__interaction .input');
    searchInput?.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
        const filtered = details.filter(d =>
            Object.values(d).some(v => String(v).toLowerCase().includes(query))
        );
        loadData(filtered, FIELDS, STORAGE_KEY, null, false);
    });
}
