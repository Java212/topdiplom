if ($('.slider' )) {
    $('.slider').slick({
        lazyLoad: 'ondemand',
        dots: true,
        infinite: true,
        slidesToScroll: 1,
        speed: 500,
        centerMode: true,
        slidesToShow: 3,


    });
}


let get = window.location.href.split('?');
if(get[1]=='authModal'){
    $(document).ready(function(){
        $("#authModal").modal('show');
    });
}
if(get[1]=='registrationModal'){
    $(document).ready(function(){
        $("#registrationModal").modal('show');
        $("#registrationError").text('пользователь с таким именем уже существует');
    });
}
if(get[2]=='error'){
    $(document).ready(function(){
        $("#errorMessage").text('неверное имя пользователя или пароль');
    });

}