$(document).ready(function () {
    console.log('js is running')

    $('#buttonRegistration').on('click', function (e) {
        let modal = $('#registrationModal');

        let address = {
            district: modal.find('#inputDistrict').val(),
            street: modal.find('#inputAddressStreet').val(),
            numberOfHouse: modal.find('#inputAddressHouse').val(),
            apartmentNumber: modal.find('#inputAddressApartment').val(),
        };

        let user = {
            userName: modal.find('#inputName').val(),
            password: modal.find('#inputPassword').val(),
            email: modal.find('#inputEmail').val(),
            address: address,

        };

        $.ajax({
            url: '/users/registration',
            cache: false,
            type: 'POST',
            data: JSON.stringify(user),
            contentType: "application/json",
            dataType: "json",
            success: () => {
                location.reload()
            },
            error: (err) => {
                alert(err);
            }
        })
        modal.modal('hide');
    });
});