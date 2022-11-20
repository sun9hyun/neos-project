/* 프로필 사진 옆에 하트 hover click 이벤트 */

$(".userName").on("mouseover", function () {
    // $(this).css("color", "#24A");
    $(this).children('.idHoverMenu').css("display", "block");
})

$(".userName").on("mouseout", function () {
    // $(this).css("color", "#2A4");
    $(this).children('.idHoverMenu').css("display", "none");
})

$(".favorite").on("click", function () {
    $(this).toggleClass('active');
})


/* 1:1 대화, 스터디 초대, chat 버튼 누르면 로그인 해라고 뜸. */

$(".chat").on("click", function () {
    $(".noLoginModal").css("display", "block");
})

$(".invite").on("click", function () {
    $(".noLoginModal").css("display", "block");
})

$(".chatBtn").on("click", function () {
    $(".noLoginModal").css("display", "block");
})

$(".closeBtn").on("click", function () {
    $(".noLoginModal").css("display", "none");
})

$(".redBtn").on("click", function () {
    $(".noLoginModal").css("display", "none");
})

$(".whiteBtn").on("click", function () {
    $(".noLoginModal").css("display", "none");
})

