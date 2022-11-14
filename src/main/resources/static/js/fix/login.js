$profile = $("div.profile");

$hoverMenu = $("div.hoverMenu");
profileCheck = -1;

$profile.click(function () {
    profileCheck *= -1;
    if (profileCheck > 0) {
        $hoverMenu.removeClass("loginHeaderUnVisibie").addClass("loginHeaderVisibie")
    } else {
        $hoverMenu.removeClass("loginHeaderVisibie").addClass("loginHeaderUnVisibie")
    }

})

var swiper = new Swiper(".mySwiper", {
    spaceBetween: 30,
    centeredSlides: true,
    slidesPerView: 1,
    spaceBetween: 30,
    loop: true,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
});
