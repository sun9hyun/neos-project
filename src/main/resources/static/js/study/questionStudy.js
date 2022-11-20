/**
 *
 */

// 작은 하트 호버
$('.favoriteSymbol').on("mouseover", function () {
    $(this).children('.favorite').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-empty-hover.03f7a2966e132a098a985cdc42f83b46.svg)")
})

$('.favoriteSymbol').on("mouseout", function () {
    $(this).children('.favorite').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-empty-white.b1db0e3e169a5cf61afa41daf5bfdc15.svg)")
    $(this).children('.active').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-full.5a5209416a8859031c9099b5540c281a.svg)")
})

// 작은 하트 활성화 선택
$(".favorite").on("click", function () {
    $(this).toggleClass('active');
})