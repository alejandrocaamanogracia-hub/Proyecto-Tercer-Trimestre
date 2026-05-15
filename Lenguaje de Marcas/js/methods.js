const CARS_KEY = 'coches';
const CLIENTS_KEY = 'clientes';
const USERS_KEY = 'usuarios';

export function initializeData() {
    if (!localStorage.getItem(CARS_KEY)) {
        const defaultCars = [
            { id: 1, marca: 'Toyota', modelo: 'Corolla', anio: 2020, cambio: 'Automático', combustible: 'Gasolina', precio: 20000 },
            { id: 2, marca: 'Honda', modelo: 'Civic', anio: 2019, cambio: 'Manual', combustible: 'Gasolina', precio: 18000 }
        ];
        localStorage.setItem(CARS_KEY, JSON.stringify(defaultCars));
    }

    if (!localStorage.getItem(CLIENTS_KEY)) {
        const defaultClients = [
            { id: 1, nombre: "Carlos Martínez", email: "carlos.martinez@email.com", telefono: "600111222", direccion: "Calle Mayor 12, Madrid" },
            { id: 2, nombre: "Laura Sánchez", email: "laura.sanchez@email.com", telefono: "600333444", direccion: "Avenida Andalucía 45, Sevilla" },
            { id: 3, nombre: "Miguel Torres", email: "miguel.torres@email.com", telefono: "600555666", direccion: "Calle Valencia 8, Barcelona" }
        ];
        localStorage.setItem(CLIENTS_KEY, JSON.stringify(defaultClients));
    }

    if (!localStorage.getItem(USERS_KEY)) {
        const defaultUsers = [
            { id: 1, nombre: "Admin Principal", email: "admin@driveflow.com", rol: "Administrador", password: "admin123" },
            { id: 2, nombre: "Pedro López", email: "pedro@driveflow.com", rol: "Comercial", password: "comercial123" },
            { id: 3, nombre: "Marta Fernández", email: "marta@driveflow.com", rol: "Gestor", password: "gestor123" }
        ];
        localStorage.setItem(USERS_KEY, JSON.stringify(defaultUsers));
    }
    updateSidebarBadges();
}

function create(item, values, container, list, fields, storageKey, onEdit) {
    const element = document.createElement('div');
    element.classList.add('table__dataItem');

    values.forEach((value, index) => {
        const data = document.createElement('p');
        data.classList.add('table__attributesText');

        const attrName = document.createElement('strong');
        attrName.textContent = fields[index].charAt(0).toUpperCase() + fields[index].slice(1) + ':  ';

        data.appendChild(attrName);
        data.appendChild(document.createTextNode(value ?? '—'));
        element.appendChild(data);
    });

    const actions = document.createElement('div');
    actions.classList.add('table__actions');

    const editButton = document.createElement('button');
    editButton.textContent = 'Editar';
    editButton.classList.add('input');

    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Eliminar';
    deleteButton.classList.add('button');

    actions.appendChild(editButton);
    actions.appendChild(deleteButton);
    element.appendChild(actions);

    deleteButton.addEventListener('click', () => {
        if (!confirm('¿Seguro que quieres eliminar este elemento?')) return;

        const fullList = JSON.parse(localStorage.getItem(storageKey)) || list;
        const indexInFull = fullList.findIndex(c => c.id === item.id);

        if (indexInFull !== -1) {
            fullList.splice(indexInFull, 1);
            localStorage.setItem(storageKey, JSON.stringify(fullList));

            const indexInCurrent = list.findIndex(c => c.id === item.id);
            if (indexInCurrent !== -1) list.splice(indexInCurrent, 1);

            element.remove();

            if (fullList.length === 0) {
                container.innerHTML = '<p class="text">No hay elementos para mostrar</p>';
            }
            updateSidebarBadges();
        }
    });

    editButton.addEventListener('click', () => onEdit?.(item));

    container.appendChild(element);
}

export function updateSidebarBadges() {
    const cars = JSON.parse(localStorage.getItem(CARS_KEY)) || [];
    const clients = JSON.parse(localStorage.getItem(CLIENTS_KEY)) || [];
    const users = JSON.parse(localStorage.getItem(USERS_KEY)) || [];

    const badgeClientes = document.getElementById('badge-clientes');
    const badgeUsuarios = document.getElementById('badge-usuarios');
    const badgeCoches = document.getElementById('badge-coches');

    if (badgeClientes) badgeClientes.textContent = clients.length;
    if (badgeUsuarios) badgeUsuarios.textContent = users.length;
    if (badgeCoches) badgeCoches.textContent = cars.length;
}

export function loadData(dataList, fields, storageKey, onEdit) {
    const container = document.querySelector('.table__dataBox');
    updateSidebarBadges();
    if (!container) return;

    container.innerHTML = '';

    if (!dataList.length) {
        container.innerHTML = '<p class="text">No hay elementos para mostrar</p>';
        return;
    }

    dataList.forEach(item => {
        const values = fields.map(field => item[field]);
        create(item, values, container, dataList, fields, storageKey, onEdit);
    });
}

window.updateSidebarBadges = updateSidebarBadges;
window.initializeData = initializeData;

// Initialize on load if not already done
document.addEventListener('DOMContentLoaded', () => {
    initializeData();
});

export function openCreate(sectionId, formId) {
    document.getElementById(sectionId)?.classList.add('blur');
    const form = document.getElementById(formId);
    if (form) form.style.display = 'flex';
}

export function closeCreate(sectionId, formId) {
    document.getElementById(sectionId)?.classList.remove('blur');
    const form = document.getElementById(formId);
    if (form) form.style.display = 'none';
}
