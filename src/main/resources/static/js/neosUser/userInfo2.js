/* 프로필에서 하트에 hover 효과*/

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


/*프로필 옆에 클릭하면 하트에 불 들어오고 나가고*/

$(".favorite").on("click", function () {
    $(this).toggleClass('active');
})


/*스터디 누르면 나오는 모달 + 하트 불 들어오고 나가고*/

$(".favorite2").click(function(){
    if($(this).hasClass("off")) {
        $(".modalWrapOpen").css("display", "block");
    } else {
        $(this).removeClass('active2');
        $(this).addClass('off');
        $(this).removeClass('on');
    }
});

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


/* 스터디 초대 눌렀을 때 초대 가능하면 나오는 모달 관련*/

// $(".blackBtn").click(function(){
//     $(".modalBg").css("display", "block");
// });

$(".modalBg .modal .closeBtn.imgBtn img").on("click", function () {
    $(".modalBg").css("display", "none");
})

$(".modalBg .modal .btnWrap button.redWhiteBtn").on("click", function () {
    $(".modalBg").css("display", "none");
})

$(".inviteYesBtn").on("click", function () {
    $(".modalBg").css("display", "none");
    $(".okModal").css("display", "block");
})

$(".okBtn").on("click", function () {
    $(".okModal").css("display", "none");
})

$(".commonModal .closeBtn img").on("click", function () {
    $(".okModal").css("display", "none");
})




/* 스터디 초대 눌렀을 때 초대 불가능하면 나오는 모달 관련 */

$(".blackBtn").click(function(){
    $(".noModal").css("display", "block");
});

$(".noModal .commonModal .closeBtn").on("click", function () {
    $(".noModal").css("display", "none");
})

$(".commonModal .btnWrap.singleBtnWrap button").on("click", function () {
    $(".noModal").css("display", "none");
})














