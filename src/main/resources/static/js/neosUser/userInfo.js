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

// $(".top .favorite2").on("click", function () {
//     $(".modalWrapOpen").css("display", "block");
// })
//
// $(".modalWrapOpen .commonModal .btnWrap button.whiteBtn").on("click", function () {
//     $(".modalWrapOpen").css("display", "none");
// })
//
// $(".modalWrapOpen .commonModal .closeBtn img").on("click", function () {
//     $(".modalWrapOpen").css("display", "none");
// })
//
// $(".modalWrapOpen .commonModal .btnWrap button.redBtn").on("click", function () {
//     $(".modalWrapOpen").css("display", "none");
//     $(".top .favorite2").addClass('active2');
//     $(".top .favorite2").removeClass('favorite2');
// })
//
// $(".top .active2").on("click", function () {
//     $(".top active2").addClass('favorite2');
//     $(".top .favorite2").removeClass('active2');
// })



$(".favorite2").click(function(){
    if($(this).hasClass("off")) {
        $(".modalWrapOpen").css("display", "block");
    } else {
        $(this).removeClass('active2');
        $(this).addClass('off');
        $(this).removeClass('on');
    }
});

// $(".favorite2").on("click", function () {
//     $(".modalWrapOpen").css("display", "block");
// })

$(".modalWrapOpen .commonModal .btnWrap button.whiteBtn").on("click", function () {
    $(".modalWrapOpen").css("display", "none");
})

$(".modalWrapOpen .commonModal .closeBtn img").on("click", function () {
    $(".modalWrapOpen").css("display", "none");
})

$(".modalWrapOpen .commonModal .btnWrap button.redBtn").on("click", function () {
    $(".modalWrapOpen").css("display", "none");
    $(".top .favorite2").addClass('active2');
    $(".top .favorite2").addClass('on');
    $(".top .favorite2").removeClass('off');
})












