/**
 *
 */

// 슬라이드 배너 관련
var swiper = new Swiper(".mySwiper", {
    slidesPerView: 1,
    spaceBetween: 30,
    centeredSlides: true,
    rewind: true,
    autoplay: {
        delay: 5000,
        disableOnInteraction: false,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
});


// 하트 활성화 선택
$('.topFavorite').click(function(){
    $(this).toggleClass('active'); /* active가 없으면 생성, 있으면 삭제*/
});

$(".favorite").on("click", function () {
    $(this).toggleClass('active');
})