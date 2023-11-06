$(document).ready(function () {
    console.log('js is running');
    const registration = document.getElementById("registration");
    const userNameInput = document.getElementById("userNameInput");
    const passwordInput = document.getElementById("passwordInput");
    const passwordRepeatInput = document.getElementById("passwordRepeatInput");
    const errorInputName = document.getElementById("errorInputName");
    const errorUserExist = document.getElementById("errorUserExist");
    const errorInputPassword = document.getElementById("errorInputPassword");
    const errorPasswordRepeatIncorrect = document.getElementById("errorPasswordRepeatIncorrect");
    const errorPasswordsMismatch = document.getElementById("errorPasswordsMismatch");
    const successRegistration = document.getElementById("successRegistration");

    userNameInput.addEventListener('input', inputName);
    passwordInput.addEventListener('input', inputPassword);
    passwordRepeatInput.addEventListener('input', inputPasswordRepeat);

    $('#button_reg').on('click', async function (event) {
        event.preventDefault();
        inputName();
        inputPassword();
        inputPasswordRepeat();

        if (
            checkName(userNameInput.value) &&
            !(await doesUserExist(name)) &&
            checkPassword(passwordInput.value) &&
            passwordInput.value === passwordRepeatInput.value
        ) {
            let newUser = {
                username: userNameInput.value,
                password: passwordInput.value
            };

            $.ajax({
                type: 'post',
                url: '/register',
                cache: false,
                dataType: 'json',
                processData: false,
                contentType: 'application/json',
                data: JSON.stringify(newUser),
                complete: function () {
                    registration.reset();
                    registration.style.display = 'none';
                    successRegistration.style.display = 'block';
                    console.log('registration is successful');
                }
            })
        } else {
            console.log('incorrect data');
        }
    })

    async function inputName() {
        let name = userNameInput.value;
        if (checkName(name) && !(await doesUserExist(name))) {
            userNameInput.style.borderColor = 'green';
            errorInputName.style.display = 'none';
            errorUserExist.style.display = 'none';
        } else if (!checkName(name)) {
            userNameInput.style.borderColor = 'red';
            errorInputName.style.display = 'block';
            errorUserExist.style.display = 'none';
        } else if (await doesUserExist(name)) {
            userNameInput.style.borderColor = 'red';
            errorInputName.style.display = 'none';
            errorUserExist.style.display = 'block';
        }
    }

    async function doesUserExist(name) {
        const response = await fetch('/register/exist/' + name);
        return await response.json();
    }

    function inputPassword() {
        if (checkPassword(passwordInput.value)) {
            passwordInput.style.borderColor = 'green';
            errorInputPassword.style.display = 'none';
        } else {
            passwordInput.style.borderColor = 'red';
            errorInputPassword.style.display = 'block';
        }
    }

    function inputPasswordRepeat() {
        if (checkPassword(passwordRepeatInput.value) && passwordInput.value === passwordRepeatInput.value) {
            passwordRepeatInput.style.borderColor = 'green';
            errorPasswordRepeatIncorrect.style.display='none';
            errorPasswordsMismatch.style.display = 'none';
        } else if (!checkPassword(passwordRepeatInput.value)){
            passwordRepeatInput.style.borderColor = 'red';
            errorPasswordRepeatIncorrect.style.display = 'block';
            errorPasswordsMismatch.style.display = 'none';
        } else if (passwordInput.value !== passwordRepeatInput.value){
            passwordRepeatInput.style.borderColor = 'red';
            errorPasswordRepeatIncorrect.style.display='none';
            errorPasswordsMismatch.style.display = 'block';
        }
    }

    function checkName(name){
        return(name != null && name !=='')
    }

    function checkPassword(password){
        return(password != null && password !== '')
    }
});