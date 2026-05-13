function create(item, values, container, list) {
    const element = document.createElement('div');
    element.classList.add('table__dataItem');

    values.forEach(value => {
        const data = document.createElement('p');
        data.classList.add('table__attributesText');
        data.textContent = value;
        element.appendChild(data);
    });

    const actions = document.createElement('div');
    actions.classList.add('table__actions');

    const editButton = document.createElement('button');
    editButton.textContent = 'Editar';
    editButton.classList.add('input');
    actions.appendChild(editButton);
    element.appendChild(actions);

    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Eliminar';
    deleteButton.classList.add('button');
    actions.appendChild(deleteButton);
    element.appendChild(actions);

    deleteButton.addEventListener('click', () => {
        const index = list.findIndex(c => c.id === item.id);
        
        if (index !== -1) {
            list.splice(index, 1);
            localStorage.setItem('misCoches', JSON.stringify(list));
            container.removeChild(element);
        }
    });

    editButton.addEventListener('click', () => {updateCar(item)});

    container.appendChild(element);
}

export function loadData(dataList, fields) {
    
    const container = document.querySelector('.table__dataBox'); 

    container.innerHTML = '';

    const columns = document.querySelectorAll('.table__attributes .table__attributesText').length;
    const gridTemplate = `repeat(${columns}, 1fr)`; 

    const header = document.querySelector('.table__attributes');
    if (header) {
        header.style.gridTemplateColumns = gridTemplate;
    }

    dataList.forEach(item => {

        const values = fields.map(field => item[field]);
        create(item, values, container, dataList);

        const lastRow = container.lastElementChild;
        if (lastRow) {
            lastRow.style.gridTemplateColumns = `repeat(${columns}, 1fr)`;
        }
    });
    
}

export function openCreate(section, form){
    const sectionBlur = document.getElementById(section);
    sectionBlur.classList.add('blur');
    const formElement = document.getElementById(form);
    formElement.style.display = 'block';
}

export function closeCreate(section, form){
    const sectionBlur = document.getElementById(section);
    sectionBlur.classList.remove('blur');
    const formElement = document.getElementById(form);
    formElement.style.display = 'none';

}
