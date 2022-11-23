

/* 로그인 클릭시 뜨는 로그인 모달 */
$(".logingbutton").on("click", function () {
    $(".loginModal1").css("display", "block");
})

$(".closeButton").on("click", function () {
    $(".loginModal1").css("display", "none");
})

$(".signupButton").on("click", function () {
    $(".loginModal1").css("display", "none");
})
