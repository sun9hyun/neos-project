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
    $(".noLoginModal").css("display", "block");
})


/*스터디 누르면 나오는 모달 + 하트 불 들어오고 나가고*/

$(".favorite2").click(function(){
    $(".noLoginModal").css("display", "block");
});



/* 스터디 초대 눌렀을 때 초대 가능하면 나오는 모달 관련*/

$(".blackBtn").click(function(){
    $(".noLoginModal").css("display", "block");
});


/* 1:1 대화 누르면 나오는 모달*/

$(".pinkBtn").click(function(){
    $(".noLoginModal").css("display", "block");
});


/* 화면 밑 프로필에서 1:1 대화 스터디 초대 부분*/

$(".chatMini").click(function(){
    $(".noLoginModal").css("display", "block");
});

$(".inviteMini").click(function(){
    $(".noLoginModal").css("display", "block");
});


/* 로그인 확인 모달 닫기*/
$(".closeBtn").click(function(){
    $(".noLoginModal").css("display", "none");
});

$(".redBtn").click(function(){
    $(".noLoginModal").css("display", "none");
});

$(".whiteBtn").click(function(){
    $(".noLoginModal").css("display", "none");
});















