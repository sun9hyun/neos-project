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


/* 스터디 초대 눌렀을 때 가능하다면 나오는 모달 */

// $(".invite").on("click", function () {
//     $(".invitationModal").css("display", "block");
// })
//
// $(".closeBtn").on("click", function () {
//     $(".invitationModal").css("display", "none");
//     $(".okModal").css("display", "none");
// })
//
// $(".redBtn").on("click", function () {
//     $(".invitationModal").css("display", "none");
//     $(".okModal").css("display", "block");
// })
//
// $(".redWhiteBtn").on("click", function () {
//     $(".invitationModal").css("display", "none");
// })
//
// $(".okBtn").on("click", function () {
//     $(".okModal").css("display", "none");
// })


/* 초대 불가능 하다면 뜨는 모달 */

$(".invite").on("click", function () {
    $(".noModal").css("display", "block");
})

$(".closeBtn").on("click", function () {
    $(".noModal").css("display", "none");
})

$(".redBtn").on("click", function () {
    $(".noModal").css("display", "none");
})


