/* 프로필 사진 옆에 하트 hover click 이벤트 */

$(".userName").on("mouseover", function () {
    // $(this).css("color", "#24A");
    $(this).children('.idHoverMenu').css("display", "block");
})

$(".userName").on("mouseout", function () {
    // $(this).css("color", "#2A4");
    $(this).children('.idHoverMenu').css("display", "none");
})

$(".heart").on("click", function () {
    $(this).toggleClass('active');
})


/* 스터디 초대 눌렀을 때 가능하다면 나오는 모달 */

$(".invite").on("click", function () {
    let name = $(this).closest(".letspler_Re").find(".name").text();
    let text = "";
    text += `<span class="inviteHead">` + name + `님께 초대장을 보내시겠습니까?</span>`
    $(".invitationModal .requestModal .tit").html(text);

    $(".invitationModal").css("display", "block");
})

$(".closeBtn").on("click", function () {
    $(".invitationModal").css("display", "none");
    $(".okModal").css("display", "none");
})

$(".inviteYesBtn").on("click", function () {
    $(".invitationModal").css("display", "none");
    $(".okModal").css("display", "block");
})

$(".redWhiteBtn").on("click", function () {
    $(".invitationModal").css("display", "none");
})

$(".okBtn").on("click", function () {
    $(".okModal").css("display", "none");
})


/* 초대 불가능 하다면 뜨는 모달 */

// $(".invite").on("click", function () {
//     $(".noModal").css("display", "block");
// })
//
// $(".closeBtn").on("click", function () {
//     $(".noModal").css("display", "none");
// })
//
// $(".redBtn").on("click", function () {
//     $(".noModal").css("display", "none");
// })



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



/* 반응형 관련 */
var mql = window.matchMedia("screen and (max-width: 1024px)");

if(mql.matches) {
    $(".rightHead .backBtn").on("click", function () {
        $(".chattingHeader .left").removeClass("leftHead");
        $(".chattingSection .list.left").removeClass("leftBody");
        $(".chattingHeader .right.active").addClass("rightHead");
        $(".chattingSection .right.active").addClass("rightBody");
    })

    $(".leftChattingList").on("click", function () {
        $(".chattingHeader .left").addClass("leftHead");
        $(".chattingSection .list.left").addClass("leftBody");
        $(".chattingHeader .right.active").removeClass("rightHead");
        $(".chattingSection .right.active").removeClass("rightBody");
    })
}
