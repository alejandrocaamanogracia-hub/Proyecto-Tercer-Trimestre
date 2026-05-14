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
        }
    });

    // Evento editar
    editButton.addEventListener('click', () => onEdit?.(item));

    container.appendChild(element);
}

export function loadData(dataList, fields, storageKey, onEdit) {
    const container = document.querySelector('.table__dataBox');
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
