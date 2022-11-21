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






/* 지역 선택시 대학 선택 셀렉박스 보이게 하기 */
$(document).ready(function() {
    $('.locationSelect').change(function() {
        var result = $('.locationSelect option:selected').val();
        if (result == '00') {
            $('.universitySelect').hide();
        } else {
            $('.universitySelect').show();
        }
    });
});



/* 네오챗 마우스 오버하면 이미지도 하얀걸로 바뀌기 */
$(".letspler_Re .bottom .chatBtn").on("mouseover", function () {
    console.log("1");
    $(this).find('img').attr('src', '/images/neosUser/chatting-icon-hover.png');
})

$(".letspler_Re .bottom .chatBtn").on("mouseout", function () {
    $(this).find('img').attr('src', '/images/neosUser/chatting-icon.png');
})

