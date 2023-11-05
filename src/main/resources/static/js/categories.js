const incomesButton = document.getElementById("incomesButton");
const incomesCategories = document.getElementById("incomesCategories");

const expenceButton = document.getElementById("expenceButton");
const expencesCategories = document.getElementById("expencesCategories");

const addIncomeCategoryButton = document.getElementById("addIncomeCategoryButton");
const addIncomeModalBackground = document.getElementById("addIncomeModalBackground");
const addIncomeModalClose = document.getElementById("addIncomeModalClose");

const addExpenceCategoryButton = document.getElementById("addExpenceCategoryButton");
const addExpenceModalBackground = document.getElementById("addExpenceModalBackground");
const addExpenceModalClose = document.getElementById("addExpenceModalClose");

const incomeCategoryNameInput = document.getElementById("incomeCategoryNameInput");
const addIncomeCategoryModalButton = document.getElementById("addIncomeCategoryModalButton");
const addMoreIncomeCategory = document.getElementById("addMoreIncomeCategory");
const addIncomeCategoryForm = document.getElementById("addIncomeCategoryForm");
const successAddIncomeCategoryForm = document.getElementById("successAddIncomeCategoryForm");
const successAddIncomeCategoryAlert = document.getElementById("successAddIncomeCategoryAlert");
const errorIncomeCategoryNameInput = document.getElementById("errorIncomeCategoryNameInput");

const expenceCategoryNameInput = document.getElementById("expenceCategoryNameInput");
const addExpenceCategoryModalButton = document.getElementById("addExpenceCategoryModalButton");
const addMoreExpenceCategory = document.getElementById("addMoreExpenceCategory");
const addExpenceCategoryForm = document.getElementById("addExpenceCategoryForm");
const successAddExpenceCategoryForm = document.getElementById("successAddExpenceCategoryForm");
const successAddExpenceCategoryAlert = document.getElementById("successAddExpenceCategoryAlert");
const errorExpenceCategoryNameInput = document.getElementById("errorExpenceCategoryNameInput");

// General

function checkName(name){
    return(name != null && name !=='')
}

// Radiobutton

incomesButton.addEventListener('click', function (){
    incomesCategories.style.display = "flex";
    expencesCategories.style.display = "none";
})

expenceButton.addEventListener('click', function (){
    incomesCategories.style.display = "none";
    expencesCategories.style.display = "flex";
})

// Add income category

addIncomeCategoryButton.addEventListener('click', function () {
    addIncomeModalBackground.style.display = "block";
})

addIncomeModalBackground.addEventListener("click", function(event){
    if(event.target === addIncomeModalBackground) {
        addIncomeModalBackground.style.display = "none";
        resetIncomeForm();
        errorIncomeCategoryNameInput.style.display='none';
        successAddIncomeCategoryAlert.style.display='none';
        successAddIncomeCategoryForm.style.display = 'none';
        addIncomeCategoryForm.style.display='block';
    }
});

addIncomeModalClose.addEventListener("click", function () {
    addIncomeModalBackground.style.display = "none";
    resetIncomeForm();
    errorIncomeCategoryNameInput.style.display='none';
    successAddIncomeCategoryAlert.style.display='none';
    successAddIncomeCategoryForm.style.display = 'none';
    addIncomeCategoryForm.style.display='block';
});

incomeCategoryNameInput.addEventListener('input', inputIncomeName);

addIncomeCategoryModalButton.addEventListener("click", function () {
    if (inputIncomeName()){
        let category = {
            name: incomeCategoryNameInput.value
        }
        $.ajax({
            type: 'post',
            url: '/categories/income',
            cache: false,
            dataType: 'json',
            processData: false,
            contentType: 'application/json',
            data: JSON.stringify(category),
            complete: function () {
                resetIncomeForm();
                addIncomeCategoryForm.style.display='none';
                successAddIncomeCategoryForm.style.display = 'block';
                successAddIncomeCategoryAlert.style.display='block';
                console.log('income category "' + category.name + '" add successful');
            }
        })
    }
});

addMoreIncomeCategory.addEventListener("click", function () {
    addIncomeCategoryForm.style.display='block';
    successAddIncomeCategoryForm.style.display = 'none';
    successAddIncomeCategoryAlert.style.display='none';
});

function resetIncomeForm() {
    addIncomeCategoryForm.reset();
    incomeCategoryNameInput.style.borderColor = '#dee2e6';
}

function inputIncomeName(){
    if(checkName(incomeCategoryNameInput.value)){
        incomeCategoryNameInput.style.borderColor = 'green';
        errorIncomeCategoryNameInput.style.display='none';
        return true;
    } else {
        incomeCategoryNameInput.style.borderColor = 'red';
        errorIncomeCategoryNameInput.style.display = 'block';
        return false;
    }
}

// Delete income category

document.querySelectorAll('.income-category').forEach((category) => {
    const id = category.getAttribute("category_id");
    const category_name = category.getAttribute("category_name");

    const deleteIncomeCategoryButton = document.getElementById("deleteIncomeCategoryButton-" + id);
    const deleteIncomeModalBackground = document.getElementById("deleteIncomeModalBackground-" + id);
    const deleteIncomeModalClose = document.getElementById("deleteIncomeModalClose-" + id);
    const deleteIncomeCategoryModalButton = document.getElementById("deleteIncomeCategoryModalButton-" + id);
    const incomeCategoryDeleteModal = document.getElementById("incomeCategoryDeleteModal-" + id);
    const incomeCategoryDeleteSucessModal = document.getElementById("incomeCategoryDeleteSucessModal-" + id);
    const incomeCategoryDeleteErrorUsed = document.getElementById("incomeCategoryDeleteErrorUsed-" + id);

    deleteIncomeCategoryButton.addEventListener('click', function () {
        deleteIncomeModalBackground.style.display = "block";
    });

    deleteIncomeModalBackground.addEventListener("click", function(event){
        if(event.target === deleteIncomeModalBackground) {
            deleteIncomeModalBackground.style.display = "none";
        }
    });

    deleteIncomeModalClose.addEventListener("click", function () {
        deleteIncomeModalBackground.style.display = "none";
    });

    deleteIncomeCategoryModalButton.addEventListener("click", async function () {

        let usedCategories = await getUsedIncomeCategories();

        function isUsed(id) {
            for (let i = 0; i < usedCategories.length; i++) {
                if (usedCategories[i].id == id) {
                    return true;
                }
            }
            return false;
        }

        if (isUsed(id)) {
            resetExpenceForm();
            deleteIncomeCategoryModalButton.style.display = "none";
            incomeCategoryDeleteModal.style.display = "none";
            incomeCategoryDeleteErrorUsed.style.display = "block";
        } else {
            $.ajax({
                type: 'delete',
                url: '/categories/income/' + id,
                complete: function () {
                    resetIncomeForm();
                    deleteIncomeCategoryModalButton.style.display = "none";
                    incomeCategoryDeleteModal.style.display = "none";
                    incomeCategoryDeleteSucessModal.style.display = "block";
                    console.log('income category "' + category_name + '" delete successful');
                }
            })
        }
    })
})

async function getUsedIncomeCategories() {
    const response = await fetch('/categories/income/used');
    return await response.json();
}

// Add expence category

addExpenceCategoryButton.addEventListener('click', function () {
    addExpenceModalBackground.style.display = "block";
})

addExpenceModalBackground.addEventListener("click", function(event){
    if(event.target === addExpenceModalBackground) {
        addExpenceModalBackground.style.display = "none";
        resetExpenceForm();
        errorExpenceCategoryNameInput.style.display='none';
        successAddExpenceCategoryAlert.style.display='none';
        successAddExpenceCategoryForm.style.display = 'none';
        addExpenceCategoryForm.style.display='block';
    }
});

addExpenceModalClose.addEventListener("click", function () {
    addExpenceModalBackground.style.display = "none";
    resetExpenceForm();
    errorExpenceCategoryNameInput.style.display='none';
    successAddExpenceCategoryAlert.style.display='none';
    successAddExpenceCategoryForm.style.display = 'none';
    addExpenceCategoryForm.style.display='block';
});

expenceCategoryNameInput.addEventListener('input', inputExpenceName);

addExpenceCategoryModalButton.addEventListener("click", function () {
    if (inputExpenceName()){
        let category = {
            name: expenceCategoryNameInput.value
        }
        $.ajax({
            type: 'post',
            url: '/categories/expence',
            cache: false,
            dataType: 'json',
            processData: false,
            contentType: 'application/json',
            data: JSON.stringify(category),
            complete: function () {
                resetExpenceForm();
                addExpenceCategoryForm.style.display='none';
                successAddExpenceCategoryForm.style.display = 'block';
                successAddExpenceCategoryAlert.style.display='block';
                console.log('expence category "' + category.name + '" add successful');
            }
        })
    }
});

addMoreExpenceCategory.addEventListener("click", function () {
    addExpenceCategoryForm.style.display='block';
    successAddExpenceCategoryForm.style.display = 'none';
    successAddExpenceCategoryAlert.style.display='none';
});

function resetExpenceForm() {
    addExpenceCategoryForm.reset();
    expenceCategoryNameInput.style.borderColor = '#dee2e6';
}

function inputExpenceName(){
    if(checkName(expenceCategoryNameInput.value)){
        expenceCategoryNameInput.style.borderColor = 'green';
        errorExpenceCategoryNameInput.style.display='none';
        return true;
    } else {
        expenceCategoryNameInput.style.borderColor = 'red';
        errorExpenceCategoryNameInput.style.display = 'block';
        return false;
    }
}

// Delete expence category

document.querySelectorAll('.expence-category').forEach((category) => {
    const id = category.getAttribute("category_id");
    const category_name = category.getAttribute("category_name");

    const deleteExpenceCategoryButton = document.getElementById("deleteExpenceCategoryButton-" + id);
    const deleteExpenceModalBackground = document.getElementById("deleteExpenceModalBackground-" + id);
    const deleteExpenceModalClose = document.getElementById("deleteExpenceModalClose-" + id);
    const deleteExpenceCategoryModalButton = document.getElementById("deleteExpenceCategoryModalButton-" + id);
    const expenceCategoryDeleteModal = document.getElementById("expenceCategoryDeleteModal-" + id);
    const expenceCategoryDeleteSucessModal = document.getElementById("expenceCategoryDeleteSucessModal-" + id);
    const expenceCategoryDeleteErrorUsed = document.getElementById("expenceCategoryDeleteErrorUsed-" + id);

    deleteExpenceCategoryButton.addEventListener('click', function () {
        deleteExpenceModalBackground.style.display = "block";
    });

    deleteExpenceModalBackground.addEventListener("click", function(event){
        if(event.target === deleteExpenceModalBackground) {
            deleteExpenceModalBackground.style.display = "none";
        }
    });

    deleteExpenceModalClose.addEventListener("click", function () {
        deleteExpenceModalBackground.style.display = "none";
    });

    deleteExpenceCategoryModalButton.addEventListener("click", async function () {

        let usedCategories = await getUsedExpenceCategories();

        function isUsed(id) {
            for (let i = 0; i < usedCategories.length; i++) {
                if (usedCategories[i].id == id) {
                    return true;
                }
            }
            return false;
        }

        if (isUsed(id)) {
            resetExpenceForm();
            deleteExpenceCategoryModalButton.style.display = "none";
            expenceCategoryDeleteModal.style.display = "none";
            expenceCategoryDeleteErrorUsed.style.display = "block";
        } else {
            $.ajax({
                type: 'delete',
                url: '/categories/expence/' + id,
                complete: function () {
                    resetExpenceForm();
                    deleteExpenceCategoryModalButton.style.display = "none";
                    expenceCategoryDeleteModal.style.display = "none";
                    expenceCategoryDeleteSucessModal.style.display = "block";
                    console.log('expence category "' + category_name + '" delete successful');
                }
            })
        }

    })
})

async function getUsedExpenceCategories() {
    const response = await fetch('/categories/expence/used');
    return await response.json();
}