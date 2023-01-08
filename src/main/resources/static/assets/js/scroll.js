// scroll to top
$(document).ready(function () {
    //show or hidden button scroll
    $(window).scroll(function () {
        $('#scrollToTop').each(function (i) {
            if ($(window).scrollTop() > 260) {
                $(this).stop().animate({
                    'opacity': '1'
                }, 200);
            } else {
                $(this).stop().animate({
                    'opacity': '0'
                }, 200);
            }

        });
    })

    //smooth scrolling to top
    $('#scrollToTop').click(function () {
        $('html, body').animate({screenTop: 250}, 100000)

    });
})