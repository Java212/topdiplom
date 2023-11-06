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