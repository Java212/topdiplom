const addExpenceButton = document.getElementById("addExpenceButton");
const addExpenceModalBackground = document.getElementById("addExpenceModalBackground");
const addExpenceModalButton = document.getElementById("addExpenceModalButton");
const successAddExpence = document.getElementById("successAddExpence");
const addMoreExpenceButton = document.getElementById("addMoreExpenceButton");
const addExpenceForm = document.getElementById("addExpenceForm");

const expenceNameInput = document.getElementById("expenceNameInput");
const expenceSummInput = document.getElementById("expenceSummInput");
const expenceCategoryInput = document.getElementById("expenceCategoryInput");
const expenceDateInput = document.getElementById("expenceDateInput");

const errorExpenceNameInput = document.getElementById("errorExpenceNameInput");
const errorExpenceSummInput = document.getElementById("errorExpenceSummInput");
const errorExpenceSummZero = document.getElementById("errorExpenceSummZero");
const errorExpenceCategoryInput = document.getElementById("errorExpenceCategoryInput");
const errorExpenceCategoryZero = document.getElementById("errorExpenceCategoryZero");
const errorExpenceDateInput = document.getElementById("errorExpenceDateInput");

const currentUserId = document.getElementById("currentUserId");
const currentUserName = document.getElementById("currentUserName");

const allExpencesSumm = document.getElementById("allExpencesSumm");
const userExpencesSumm = document.getElementById("userExpencesSumm");

// Get expences list

getExpences();

function getExpences(){

    $.ajax({
        type: 'get',
        url: '/expences/list',
        dataType: 'json',
    }).done(async function (data) {

        if (document.body.contains(document.getElementById("expencesList"))) {
            const expencesList = document.getElementById("expencesList");
            expencesList.parentNode.removeChild(expencesList);
        }

        if (data.length > 0) {

            const body = document.getElementsByTagName('Body')[0];
            const expencesList = document.createElement('div');
            expencesList.classList.add('alert', 'alert-light', 'm-3');
            expencesList.setAttribute('id', 'expencesList');
            
            let categories = await getAllCategories();

            function searchCategory(id) {
                for (let i = 0; i < categories.length; i++) {
                    if (categories[i].id === id) {
                        return categories[i];
                    }
                }
            }

            for (let i = 0; i < data.length; i++) {

                const expence = document.createElement('div');
                expence.classList.add('d-flex', 'alert', 'alert-secondary', 'w-100', 'category', 'align-items-center', 'mb-0')
                expence.setAttribute('id', 'expence-' + i);

                const name = document.createElement('h6');
                name.classList.add('me-3', 'mt-0', 'mb-0');
                name.innerText = data[i].name;
                expence.append(name);

                const summ = document.createElement('h6');
                summ.classList.add('me-3', 'mt-0', 'mb-0');
                summ.innerText = "(Сумма: " + data[i].summ + " р.,";
                expence.append(summ);

                const category = document.createElement('h6');
                category.classList.add('me-3', 'mt-0', 'mb-0');
                category.innerText = "Категория: " + searchCategory(data[i].categoryId).name + ",";
                expence.append(category);

                const date = document.createElement('h6');
                date.classList.add('me-3', 'mt-0', 'mb-0');
                date.innerText = "Дата: " + data[i].date + ")";
                expence.append(date);

                const deleteButton = document.createElement('button');
                deleteButton.classList.add('btn', 'btn-outline-danger', 'btn-sm', 'deleteExpenceButton');
                deleteButton.innerText = "X";
                deleteButton.addEventListener('click', function () {
                    $.ajax({
                        type: 'delete',
                        url: '/expences/' + (data[i].id),
                        complete: function () {
                            getExpences();
                            getAllExpencesSumm();
                            getUserExpencesSumm();
                            console.log('expence "' + data[i].name + '" delete successful');
                        }
                    })
                })
                expence.append(deleteButton);

                expencesList.append(expence);
            }

            body.append(expencesList);

        }

    });
}

async function getAllCategories() {
    const response = await fetch('/categories/expence');
    return await response.json();
}

// Get all expences summ

getAllExpencesSumm();

function getAllExpencesSumm() {
    removeAllExpencesSumm();
    $.ajax({
        type: 'get',
        url: '/expences/summ_all',
        dataType: 'json',
    }).done(function(data){
        if (data>0) {
            allExpencesSumm.style.display = 'block';
            const allExpencesSummName = document.createElement('span');
            allExpencesSummName.classList.add('fw-semibold', 'me-1');
            allExpencesSummName.innerText = "Общий расход: ";
            allExpencesSumm.append(allExpencesSummName);

            const allExpencesSummValue = document.createElement('span');
            allExpencesSummValue.innerText = data + " р.";
            allExpencesSumm.append(allExpencesSummValue);
        } else {
            removeAllExpencesSumm();
        }
    }).fail(function(){
        removeAllExpencesSumm()
    });
}

function removeAllExpencesSumm() {
    while (allExpencesSumm.firstChild) {
        allExpencesSumm.removeChild(allExpencesSumm.lastChild);
    }
    allExpencesSumm.style.display = 'none';
}

// Get user expences summ

getUserExpencesSumm();

function getUserExpencesSumm(){
    const userId = currentUserId.innerText;
    removeUserExpencesSumm();
    fetch('/expences/summ/'+userId)
        .then((responce) => responce.json())
        .then((data) => {
            if (data>0) {
                userExpencesSumm.style.display = 'block';
                const userExpencesSummName = document.createElement('span');
                userExpencesSummName.classList.add('fw-semibold', 'me-1');
                userExpencesSummName.innerText = "Расход " + currentUserName.innerText + ": ";
                userExpencesSumm.append(userExpencesSummName);

                const userExpencesSummValue = document.createElement('span');
                userExpencesSummValue.innerText = data + " р.";
                userExpencesSumm.append(userExpencesSummValue);
            } else {
                removeUserExpencesSumm();
            }
        })
        .catch(function(){
            removeUserExpencesSumm();
        });
}

function removeUserExpencesSumm() {
    while (userExpencesSumm.firstChild) {
        userExpencesSumm.removeChild(userExpencesSumm.lastChild);
    }
    userExpencesSumm.style.display = 'none';
}

// Add expence

addExpenceButton.addEventListener('click', function (){
    addExpenceModalBackground.style.display = "block";
})

addExpenceModalBackground.addEventListener("click", function(event){
    if(event.target === addExpenceModalBackground) {
        addExpenceModalBackground.style.display = "none";
        resetExpenceForm();
    }
});

addExpenceModalClose.addEventListener("click", function () {
    addExpenceModalBackground.style.display = "none";
    resetExpenceForm();
});

addExpenceModalButton.addEventListener('click', function (){
    inputExpenceName();
    inputExpenceSumm();
    inputExpenceCategory();
    inputExpenceDate();
    if (inputExpenceName() && inputExpenceSumm() && inputExpenceCategory() && inputExpenceDate()){
        let expence = {
            name: expenceNameInput.value,
            summ: expenceSummInput.value,
            userId: currentUserId.innerText,
            categoryId: expenceCategoryInput.value,
            date: expenceDateInput.value
        }
        $.ajax({
            type: 'post',
            url: '/expences',
            cache: false,
            dataType: 'json',
            processData: false,
            contentType: 'application/json',
            data: JSON.stringify(expence),
            complete: function () {
                getExpences();
                getAllExpencesSumm();
                getUserExpencesSumm();
                resetExpenceForm();
                addExpenceForm.style.display='none';
                successAddExpence.style.display = 'block';
                console.log('expence "' + expence.name + '" add successful');
            }
        })
    }
})

expenceNameInput.addEventListener('input', inputExpenceName);
expenceSummInput.addEventListener('input', inputExpenceSumm);
expenceCategoryInput.addEventListener('input', inputExpenceCategory);
expenceDateInput.addEventListener('input',inputExpenceDate);

addMoreExpenceButton.addEventListener("click", function () {
    resetExpenceForm();
});

function checkField(name){
    return(name != null && name !=='')
}

function checkMoreThan0(value){
    return(value=>0)
}

function checkMoreThan1(value){
    return(value>0)
}

function resetExpenceForm() {
    addExpenceForm.reset();
    expenceNameInput.style.borderColor = '#dee2e6';
    expenceSummInput.style.borderColor = '#dee2e6';
    expenceCategoryInput.style.borderColor = '#dee2e6';
    expenceDateInput.style.borderColor = '#dee2e6';

    errorExpenceNameInput.style.display='none';
    errorExpenceSummInput.style.display='none';
    errorExpenceSummZero.style.display='none';
    errorExpenceCategoryInput.style.display='none';
    errorExpenceCategoryZero.style.display='none';
    errorExpenceDateInput.style.display='none';

    addExpenceForm.style.display='block';
    successAddExpence.style.display = 'none';
}

function inputExpenceName(){
    if(checkField(expenceNameInput.value)){
        expenceNameInput.style.borderColor = 'green';
        errorExpenceNameInput.style.display='none';
        return true;
    } else {
        expenceNameInput.style.borderColor = 'red';
        errorExpenceNameInput.style.display = 'block';
        return false;
    }
}

function inputExpenceSumm(){
    if (checkField(expenceSummInput.value) && checkMoreThan0(expenceSummInput.value)){
        expenceSummInput.style.borderColor = 'green';
        errorExpenceSummInput.style.display='none';
        errorExpenceSummZero.style.display='none';
        return true;
    } else if (!checkMoreThan0(expenceSummInput.value)) {
        expenceSummInput.style.borderColor = 'red';
        errorExpenceSummZero.style.display='block';
        return false;
    } else if (!checkField(expenceSummInput.value)) {
        expenceSummInput.style.borderColor = 'red';
        errorExpenceSummInput.style.display='block';
        return false;
    }
}

function inputExpenceCategory(){
    if (checkField(expenceCategoryInput.value) && checkMoreThan1(expenceCategoryInput.value)) {
        expenceCategoryInput.style.borderColor = 'green';
        errorExpenceCategoryInput.style.display='none';
        errorExpenceCategoryZero.style.display='none';
        return true;
    } else if (!checkMoreThan1(expenceCategoryInput.value)) {
        expenceCategoryInput.style.borderColor = 'red';
        errorExpenceCategoryInput.style.display='block';
        return false;
    } else if (!checkField(expenceCategoryInput.value)) {
        expenceCategoryInput.style.borderColor = 'red';
        errorExpenceCategoryZero.style.display='block';
        return false;
    }
}

function inputExpenceDate(){
    if (checkField(expenceDateInput.value)) {
        expenceDateInput.style.borderColor = 'green';
        errorExpenceDateInput.style.display='none';
        return true;
    } else {
        expenceDateInput.style.borderColor = 'red';
        errorExpenceDateInput.style.display='block';
        return false;
    }
}

