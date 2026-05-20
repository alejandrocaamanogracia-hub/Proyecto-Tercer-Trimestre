const CARS_KEY = 'coches';
const CLIENTS_KEY = 'clientes';
const USERS_KEY = 'usuarios';

// Metodo para tener datos por defecto al iniciar la pagina en caso de que no haya nada en el local storage.

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

    if (!localStorage.getItem('misVentas')) {
        const defaultSales = [
            { id: 1, cliente: "Carlos Martínez", usuario: "Pedro López", fecha: "15/5/2026", estado: "Completada", total: "20000€" },
            { id: 2, cliente: "Laura Sánchez", usuario: "Marta Fernández", fecha: "18/5/2026", estado: "Pendiente", total: "18000€" }
        ];
        localStorage.setItem('misVentas', JSON.stringify(defaultSales));
    }

    if (!localStorage.getItem('misDetallesVentas')) {
        const defaultDetails = [
            { id: 1, saleId: 1, cliente: "Carlos Martínez", coche: "Toyota Corolla", cantidad: 1, total: "20000€" },
            { id: 2, saleId: 2, cliente: "Laura Sánchez", coche: "Honda Civic", cantidad: 1, total: "18000€" }
        ];
        localStorage.setItem('misDetallesVentas', JSON.stringify(defaultDetails));
    }
    updateSidebarBadges();
}


// Metodo para crear elementos en la tabla.

function create(item, values, container, list, fields, storageKey, onEdit, showActions = true) {

    // Se crea un div donde introduciremos los datos.

    const element = document.createElement('div');
    element.classList.add('table__dataItem');
    element.classList.add('fade-in-section');

    // Por cada elemento de values se crea un texto nuevo con el nombre del campo y el valor.

    values.forEach((value, index) => {
        const data = document.createElement('p');
        data.classList.add('table__attributesText');

        const attrName = document.createElement('strong');
        attrName.textContent = fields[index].charAt(0).toUpperCase() + fields[index].slice(1) + ':  ';

        data.appendChild(attrName);
        data.appendChild(document.createTextNode(value ?? '—'));
        element.appendChild(data);
    });

    if (showActions) {
        // Se crean los botones de editar y eliminar.

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

        // Evento para eliminar un elemento.

        deleteButton.addEventListener('click', () => {
            if (!confirm('¿Seguro que quieres eliminar este elemento?')) return;

            const fullList = JSON.parse(localStorage.getItem(storageKey)) || list;
            const indexInFull = fullList.findIndex(c => c.id === item.id);

            if (indexInFull !== -1) {
                fullList.splice(indexInFull, 1);
                localStorage.setItem(storageKey, JSON.stringify(fullList));

                // Si estamos eliminando una venta, eliminamos también sus detalles.
                if (storageKey === 'misVentas') {
                    let detailsList = JSON.parse(localStorage.getItem('misDetallesVentas')) || [];
                    detailsList = detailsList.filter(d => d.saleId !== item.id);
                    localStorage.setItem('misDetallesVentas', JSON.stringify(detailsList));
                }

                const indexInCurrent = list.findIndex(c => c.id === item.id);
                if (indexInCurrent !== -1) list.splice(indexInCurrent, 1);

                element.remove();

                if (fullList.length === 0) {
                    container.innerHTML = '<p class="text">No hay elementos para mostrar</p>';
                }
                updateSidebarBadges();
            }
        });

        // Evento para editar un elemento.

        editButton.addEventListener('click', () => onEdit?.(item));
    }

    container.appendChild(element);
    
    if (viewportObserver) {
        viewportObserver.observe(element);
    }
}

// Metodo para actualizar los badges de la barra lateral.

export function updateSidebarBadges() {

    // Se cargan los datos de los coches, clientes y usuarios.

    const cars = JSON.parse(localStorage.getItem(CARS_KEY)) || [];
    const clients = JSON.parse(localStorage.getItem(CLIENTS_KEY)) || [];
    const users = JSON.parse(localStorage.getItem(USERS_KEY)) || [];

    // Se obtienen los badges.

    const badgeClientes = document.getElementById('badge-clientes');
    const badgeUsuarios = document.getElementById('badge-usuarios');
    const badgeCoches = document.getElementById('badge-coches');

    // Se actualizan los badges con la cantidad de elementos.

    if (badgeClientes) badgeClientes.textContent = clients.length;
    if (badgeUsuarios) badgeUsuarios.textContent = users.length;
    if (badgeCoches) badgeCoches.textContent = cars.length;
}

// Metodo para cargar los datos en la tabla.

export function loadData(dataList, fields, storageKey, onEdit, showActions = true) {

    // Se crea un nuevo div para guardar los datos y se actualizan los badges.

    const container = document.querySelector('.table__dataBox');
    updateSidebarBadges();
    if (!container) return;

    // Se vacia el contenedor.

    container.innerHTML = '';

    // Si no hay elementos, se muestra un mensaje.

    if (!dataList.length) {
        container.innerHTML = '<p class="text">No hay elementos para mostrar</p>';
        return;
    }

    // Se recorre la lista de datos y se crea un elemento por cada uno.

    dataList.forEach(item => {
        const values = fields.map(field => item[field]);
        create(item, values, container, dataList, fields, storageKey, onEdit, showActions);
    });
}

// Metodo para inicializar el tema oscuro o claro.

export function initTheme() {
    const theme = localStorage.getItem('theme') || 'dark';
    if (theme === 'light') {
        document.documentElement.classList.add('light-mode');
        updateThemeIcon(true);
    }
}

export function toggleTheme() {
    const isLight = document.documentElement.classList.toggle('light-mode');
    localStorage.setItem('theme', isLight ? 'light' : 'dark');
    updateThemeIcon(isLight);
}

function updateThemeIcon(isLight) {
    const icon = document.getElementById('themeIcon');
    const text = document.getElementById('themeText');
    if (icon) icon.textContent = isLight ? 'dark_mode' : 'light_mode';
    if (text) text.textContent = isLight ? 'Modo oscuro' : 'Modo claro';
}

// Se exportan los metodos para que puedan ser utilizados en otros archivos.

window.updateSidebarBadges = updateSidebarBadges;
window.initializeData = initializeData;
window.toggleTheme = toggleTheme;

// Evento que se ejecuta cuando se carga el DOM.

document.addEventListener('DOMContentLoaded', () => {
    initializeData();
    initTheme();
    initFadeAnimations();

    const themeBtn = document.getElementById('themeToggle');
    if (themeBtn) {
        themeBtn.addEventListener('click', toggleTheme);
    }

    const logoutBtn = document.getElementById('logoutButton');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', () => {
            if (typeof cerrarSesion === 'function') {
                cerrarSesion();
            } else if (window.cerrarSesion) {
                window.cerrarSesion();
            }
        });
    }
});

// Metodo para abrir un formulario.

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

let viewportObserver = null;

// Metodo para inicializar animaciones usando el Viewport (Intersection Observer)
export function initFadeAnimations() {
    viewportObserver = new IntersectionObserver((entries) => {
        const visibleEntries = entries.filter(e => e.isIntersecting);
        visibleEntries.forEach((entry, index) => {
            // Añadimos un pequeño retraso en cascada (stagger effect)
            entry.target.style.transitionDelay = `${index * 80}ms`;
            entry.target.classList.add('is-visible');
            viewportObserver.unobserve(entry.target); 
            
            // Limpiamos el retraso después para no afectar a otras animaciones como el :hover
            setTimeout(() => {
                entry.target.style.transitionDelay = '0ms';
            }, 800 + (index * 80));
        });
    }, {
        threshold: 0.1
    });
}
