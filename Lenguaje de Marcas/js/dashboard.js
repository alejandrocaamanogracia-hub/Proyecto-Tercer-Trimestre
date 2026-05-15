import { updateSidebarBadges, initializeData } from './methods.js';

if (window.protegerPagina) window.protegerPagina();

document.addEventListener('DOMContentLoaded', () => {
    initializeData();
    updateDashboardMetrics();
    updateSidebarBadges();
});

function updateDashboardMetrics() {
    // Keys from other scripts
    const SALES_KEY = 'misVentas';
    const CARS_KEY = 'coches';
    const CLIENTS_KEY = 'clientes';
    const USERS_KEY = 'usuarios';

    // Get data from localStorage
    const sales = JSON.parse(localStorage.getItem(SALES_KEY)) || [];
    const cars = JSON.parse(localStorage.getItem(CARS_KEY)) || [];
    const clients = JSON.parse(localStorage.getItem(CLIENTS_KEY)) || [];
    const users = JSON.parse(localStorage.getItem(USERS_KEY)) || [];

    // Calculate total sales amount
    const totalSalesAmount = sales.reduce((acc, sale) => {
        const amount = parseFloat(sale.total.replace('€', '').replace('.', '').replace(',', '.'));
        return acc + (isNaN(amount) ? 0 : amount);
    }, 0);

    // Update metrics in the DOM
    const totalVentasEl = document.getElementById('total-ventas');
    const totalUsuariosEl = document.getElementById('total-usuarios');
    const totalClientesEl = document.getElementById('total-clientes');
    const totalCochesEl = document.getElementById('total-coches');

    if (totalVentasEl) totalVentasEl.textContent = formatCurrency(totalSalesAmount);
    if (totalUsuariosEl) totalUsuariosEl.textContent = users.length;
    if (totalClientesEl) totalClientesEl.textContent = clients.length;
    if (totalCochesEl) totalCochesEl.textContent = cars.length;

    // Badge update is now handled by badges.js

    // Update last sales
    const lastSalesContainer = document.getElementById('ultimas-ventas-container');
    if (lastSalesContainer) {
        lastSalesContainer.innerHTML = '';
        
        // Get last 3 sales, reversed to show newest first
        const lastThreeSales = [...sales].reverse().slice(0, 3);

        if (lastThreeSales.length === 0) {
            lastSalesContainer.innerHTML = '<p class="text" style="padding: 1rem; text-align: center;">No hay ventas registradas.</p>';
        } else {
            lastThreeSales.forEach(sale => {
                const saleDiv = document.createElement('div');
                saleDiv.className = 'dash-total__innerDiv';
                saleDiv.innerHTML = `
                    <div class="dash-total__textContainer">
                        <h2 class="text">${sale.fecha}</h2>
                        <h2 class="heading">${sale.cliente}</h2>
                        <h2 class="text">Atendido por: ${sale.usuario}</h2>
                    </div>
                    <h1 class="heading">${sale.total}</h1>
                `;
                lastSalesContainer.appendChild(saleDiv);
            });
        }
    }
}

function formatCurrency(amount) {
    return new Intl.NumberFormat('es-ES', {
        minimumFractionDigits: 0,
        maximumFractionDigits: 0
    }).format(amount);
}
