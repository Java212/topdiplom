(function () {
    'use strict'

    var forms = document.querySelectorAll('.needs-validation')
    let password = document.querySelector('#password')
    let passwordRepeat = document.querySelector('#passwordRepeat')

    Array.prototype.slice.call(forms)
        .forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity() && (password!=passwordRepeat) ) {
                event.preventDefault()
                event.stopPropagation()
            }

            form.classList.add('was-validated')
        }, false)
    })
})()
