$(".userName").on("mouseover", function () {
    $(this).children('.idHoverMenu').css("display", "block");
})

$(".userName").on("mouseout", function () {
    $(this).children('.idHoverMenu').css("display", "none");
})

$(".imgWrap").on("mouseover", function () {
    $(this).prev().css("display", "block");
})

$(".imgWrap").on("mouseout", function () {
    $(this).prev().css("display", "none");
})

$(".hoverName").on("mouseover", function () {
    $(this).css("display", "block");
})

$(".hoverName").on("mouseout", function () {
    $(this).css("display", "none");
})

$(".favorite").on("click", function () {
    $(this).toggleClass('active');
})




