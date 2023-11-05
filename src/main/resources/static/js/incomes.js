const addIncomeButton = document.getElementById("addIncomeButton");
const addIncomeModalBackground = document.getElementById("addIncomeModalBackground");
const addIncomeModalButton = document.getElementById("addIncomeModalButton");
const successAddIncome = document.getElementById("successAddIncome");
const addMoreIncomeButton = document.getElementById("addMoreIncomeButton");
const addIncomeForm = document.getElementById("addIncomeForm");

const incomeNameInput = document.getElementById("incomeNameInput");
const incomeSummInput = document.getElementById("incomeSummInput");
const incomeCategoryInput = document.getElementById("incomeCategoryInput");
const incomeDateInput = document.getElementById("incomeDateInput");

const errorIncomeNameInput = document.getElementById("errorIncomeNameInput");
const errorIncomeSummInput = document.getElementById("errorIncomeSummInput");
const errorIncomeSummZero = document.getElementById("errorIncomeSummZero");
const errorIncomeCategoryInput = document.getElementById("errorIncomeCategoryInput");
const errorIncomeCategoryZero = document.getElementById("errorIncomeCategoryZero");
const errorIncomeDateInput = document.getElementById("errorIncomeDateInput");

const currentUserId = document.getElementById("currentUserId");
const currentUserName = document.getElementById("currentUserName");

const allIncomesSumm = document.getElementById("allIncomesSumm");
const userIncomesSumm = document.getElementById("userIncomesSumm");

// Get incomes list

getIncomes();

function getIncomes(){

    $.ajax({
        type: 'get',
        url: '/incomes/list',
        dataType: 'json',
    }).done(async function (data) {

        if (document.body.contains(document.getElementById("incomesList"))) {
            const incomesList = document.getElementById("incomesList");
            incomesList.parentNode.removeChild(incomesList);
        }

        if (data.length > 0) {

            const body = document.getElementsByTagName('Body')[0];
            const incomesList = document.createElement('div');
            incomesList.classList.add('alert', 'alert-light', 'm-3');
            incomesList.setAttribute('id', 'incomesList');

            let categories = await getAllCategories();

            function searchCategory(id) {
                for (let i = 0; i < categories.length; i++) {
                    if (categories[i].id === id) {
                        return categories[i];
                    }
                }
            }

            for (let i = 0; i < data.length; i++) {

                const income = document.createElement('div');
                income.classList.add('d-flex', 'alert', 'alert-secondary', 'w-100', 'category', 'align-items-center', 'mb-0')
                income.setAttribute('id', 'income-' + i);

                const name = document.createElement('h6');
                name.classList.add('me-3', 'mt-0', 'mb-0');
                name.innerText = data[i].name;
                income.append(name);

                const summ = document.createElement('h6');
                summ.classList.add('me-3', 'mt-0', 'mb-0');
                summ.innerText = "(Сумма: " + data[i].summ + " р.,";
                income.append(summ);

                const category = document.createElement('h6');
                category.classList.add('me-3', 'mt-0', 'mb-0');
                category.innerText = "Категория: " + searchCategory(data[i].categoryId).name + ",";
                income.append(category);

                const date = document.createElement('h6');
                date.classList.add('me-3', 'mt-0', 'mb-0');
                date.innerText = "Дата: " + data[i].date + ")";
                income.append(date);

                const deleteButton = document.createElement('button');
                deleteButton.classList.add('btn', 'btn-outline-danger', 'btn-sm', 'deleteIncomeButton');
                deleteButton.innerText = "X";
                deleteButton.addEventListener('click', function () {
                    $.ajax({
                        type: 'delete',
                        url: '/incomes/' + (data[i].id),
                        complete: function () {
                            getIncomes();
                            getAllIncomesSumm();
                            getUserIncomesSumm();
                            console.log('income "' + data[i].name + '" delete successful');
                        }
                    })
                })
                income.append(deleteButton);

                incomesList.append(income);
            }

            body.append(incomesList);

        }

    });
}

async function getAllCategories() {
    const response = await fetch('/categories/income');
    return await response.json();
}

// Get all incomes summ

getAllIncomesSumm();

function getAllIncomesSumm() {
    removeAllIncomesSumm();
    $.ajax({
        type: 'get',
        url: '/incomes/summ_all',
        dataType: 'json',
    }).done(function(data){
        if (data>0) {
            allIncomesSumm.style.display = 'block';
            const allIncomesSummName = document.createElement('span');
            allIncomesSummName.classList.add('fw-semibold', 'me-1');
            allIncomesSummName.innerText = "Общий доход: ";
            allIncomesSumm.append(allIncomesSummName);

            const allIncomesSummValue = document.createElement('span');
            allIncomesSummValue.innerText = data + " р.";
            allIncomesSumm.append(allIncomesSummValue);
        } else {
            removeAllIncomesSumm();
        }
    }).fail(function(){
        removeAllIncomesSumm();
    });
}

function removeAllIncomesSumm() {
    while (allIncomesSumm.firstChild) {
        allIncomesSumm.removeChild(allIncomesSumm.lastChild);
    }
    allIncomesSumm.style.display = 'none';
}

// Get user incomes summ

getUserIncomesSumm();

function getUserIncomesSumm(){
    const userId = currentUserId.innerText;
    removeUserIncomesSumm();
    fetch('/incomes/summ/'+userId)
        .then((responce) => responce.json())
        .then((data) => {
            if (data>0) {
                userIncomesSumm.style.display = 'block';
                const userIncomesSummName = document.createElement('span');
                userIncomesSummName.classList.add('fw-semibold', 'me-1');
                userIncomesSummName.innerText = "Доход " + currentUserName.innerText + ": ";
                userIncomesSumm.append(userIncomesSummName);

                const userIncomesSummValue = document.createElement('span');
                userIncomesSummValue.innerText = data + " р.";
                userIncomesSumm.append(userIncomesSummValue);
            } else {
                removeUserIncomesSumm();
            }
        })
        .catch(() => {
            removeUserIncomesSumm();
        });
}

function removeUserIncomesSumm() {
    while (userIncomesSumm.firstChild) {
        userIncomesSumm.removeChild(userIncomesSumm.lastChild);
    }
    userIncomesSumm.style.display = 'none';
}

// Add income

addIncomeButton.addEventListener('click', function (){
    addIncomeModalBackground.style.display = "block";
})

addIncomeModalBackground.addEventListener("click", function(event){
    if(event.target === addIncomeModalBackground) {
        addIncomeModalBackground.style.display = "none";
        resetIncomeForm();
    }
});

addIncomeModalClose.addEventListener("click", function () {
    addIncomeModalBackground.style.display = "none";
    resetIncomeForm();
});

addIncomeModalButton.addEventListener('click', function (){
    inputIncomeName();
    inputIncomeSumm();
    inputIncomeCategory();
    inputIncomeDate();
    if (inputIncomeName() && inputIncomeSumm() && inputIncomeCategory() && inputIncomeDate()){
        let income = {
            name: incomeNameInput.value,
            summ: incomeSummInput.value,
            userId: currentUserId.innerText,
            categoryId: incomeCategoryInput.value,
            date: incomeDateInput.value
        }
        $.ajax({
            type: 'post',
            url: '/incomes',
            cache: false,
            dataType: 'json',
            processData: false,
            contentType: 'application/json',
            data: JSON.stringify(income),
            complete: function () {
                getIncomes();
                getAllIncomesSumm();
                getUserIncomesSumm();
                resetIncomeForm();
                addIncomeForm.style.display='none';
                successAddIncome.style.display = 'block';
                console.log('income "' + income.name + '" add successful');
            }
        })
    }
})

incomeNameInput.addEventListener('input', inputIncomeName);
incomeSummInput.addEventListener('input', inputIncomeSumm);
incomeCategoryInput.addEventListener('input', inputIncomeCategory);
incomeDateInput.addEventListener('input',inputIncomeDate);

addMoreIncomeButton.addEventListener("click", function () {
    resetIncomeForm();
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

function resetIncomeForm() {
    addIncomeForm.reset();
    incomeNameInput.style.borderColor = '#dee2e6';
    incomeSummInput.style.borderColor = '#dee2e6';
    incomeCategoryInput.style.borderColor = '#dee2e6';
    incomeDateInput.style.borderColor = '#dee2e6';

    errorIncomeNameInput.style.display='none';
    errorIncomeSummInput.style.display='none';
    errorIncomeSummZero.style.display='none';
    errorIncomeCategoryInput.style.display='none';
    errorIncomeCategoryZero.style.display='none';
    errorIncomeDateInput.style.display='none';

    addIncomeForm.style.display='block';
    successAddIncome.style.display = 'none';
}

function inputIncomeName(){
    if(checkField(incomeNameInput.value)){
        incomeNameInput.style.borderColor = 'green';
        errorIncomeNameInput.style.display='none';
        return true;
    } else {
        incomeNameInput.style.borderColor = 'red';
        errorIncomeNameInput.style.display = 'block';
        return false;
    }
}

function inputIncomeSumm(){
    if (checkField(incomeSummInput.value) && checkMoreThan0(incomeSummInput.value)){
        incomeSummInput.style.borderColor = 'green';
        errorIncomeSummInput.style.display='none';
        errorIncomeSummZero.style.display='none';
        return true;
    } else if (!checkMoreThan0(incomeSummInput.value)) {
        incomeSummInput.style.borderColor = 'red';
        errorIncomeSummZero.style.display='block';
        return false;
    } else if (!checkField(incomeSummInput.value)) {
        incomeSummInput.style.borderColor = 'red';
        errorIncomeSummInput.style.display='block';
        return false;
    }
}

function inputIncomeCategory(){
    if (checkField(incomeCategoryInput.value) && checkMoreThan1(incomeCategoryInput.value)) {
        incomeCategoryInput.style.borderColor = 'green';
        errorIncomeCategoryInput.style.display='none';
        errorIncomeCategoryZero.style.display='none';
        return true;
    } else if (!checkMoreThan1(incomeCategoryInput.value)) {
        incomeCategoryInput.style.borderColor = 'red';
        errorIncomeCategoryInput.style.display='block';
        return false;
    } else if (!checkField(incomeCategoryInput.value)) {
        incomeCategoryInput.style.borderColor = 'red';
        errorIncomeCategoryZero.style.display='block';
        return false;
    }
}

function inputIncomeDate(){
    if (checkField(incomeDateInput.value)) {
        incomeDateInput.style.borderColor = 'green';
        errorIncomeDateInput.style.display='none';
        return true;
    } else {
        incomeDateInput.style.borderColor = 'red';
        errorIncomeDateInput.style.display='block';
        return false;
    }
}