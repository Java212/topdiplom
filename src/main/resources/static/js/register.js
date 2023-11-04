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

    $('#button_reg').on('click', function (event) {
        event.preventDefault();
        inputName();
        inputPassword();
        inputPasswordRepeat();

        if (
            checkName(userNameInput.value) &&
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

    function inputName(){
        let name = userNameInput.value;
        console.log(name);
        console.log(doesUserExist(name));
        if(checkName(name) && !doesUserExist(name)){
            userNameInput.style.borderColor = 'green';
            errorInputName.style.display='none';
            errorUserExist.style.display='none';
        } else if (!checkName(name)) {
            userNameInput.style.borderColor = 'red';
            errorInputName.style.display='block';
        } else if (doesUserExist(name)){
            userNameInput.style.borderColor = 'red';
            errorUserExist.style.display='block';
        }
    }

    // function doesUserExist(name){
    //     let isExist = false;
    //     if (checkName(name)) {
    //         $.ajax({
    //             type: 'post',
    //             url: '/register/exist',
    //             data: name,
    //             dataType: 'json',
    //             contentType: 'application/json',
    //             processData: false,
    //         }).done(function (data){
    //             isExist = data;
    //         })
    //     } else {
    //         return false;
    //     }
    //     return isExist;
    // }

    // function doesUserExist(name, callback){
    //     let request = new XMLHttpRequest();
    //     request.open('GET', url, true);
    //     request.send(name);
    //     request.responseType = 'text';
    //     request.onload = function() {
    //         let status = request.status;
    //         if (status === 200) {
    //             callback(name, request.response);
    //         } else {
    //             callback(name, false);
    //         }
    //     };
    //     request.send();
    // }

    function doesUserExist(name){
        let isExist = false;
        fetch('/register/exist/' + name)
            .then((data) => {
                console.log(data.body.getReader().read())
            isExist = data.body.getReader().read();
        })
        return isExist;
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