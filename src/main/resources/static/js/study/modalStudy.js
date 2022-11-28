/*
 *
 */
/*------------------------상세보기 공통---------------------------------*/

// 모달 종료
$(".closeBtnImg").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

$(".blueBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

$(".whiteBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

// // 탑 작성자 채팅
// $(".chatTop").on("click",function(){
//     let name = $(this).parents(".userId").children(".userIdTxt").text();
//     $(".modal1").children(".modalContent").children(".modalName").text(name + " 님과 대화를 시작합니다.");
//     $(".modalWrapOpen").show();
//     $(".modal1").css('display','inline-block');
// });
//
// // 오른쪽 리더 채팅
// $(".chatReader").on("click",function(){
//     let name = $(this).parents(".rightBox").children(".userIdTxt").text().split(" ")[0];
//     $(".modal1").children(".modalContent").children(".modalName").text(name + "님과 대화를 시작합니다.");
//     $(".modalWrapOpen").show();
//     $(".modal1").css('display','inline-block');
// });

// 작은 하트로 스터디 구독
$(".rightFavorite").on("click", function () {
    if($(this).hasClass("active")){
        $(this).removeClass("active");
        $(".subscribeBtn").addClass("active");
    }else{
        let name = $(this).parents(".projectPageWrap").prev().children(".projectPageHeaderContent").children(".projectTit").text();
        $(".modal2").children(".studyTit").text(name + "을");
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');
    }
})

$(".okBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
    $(".rightFavorite").addClass("active").css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-full.5a5209416a8859031c9099b5540c281a.svg)");
    $(".subscribeBtn").removeClass("active");
});

// 구독하기 버튼 구독
$(".subscribeBtn").on("click", function () {
    if($(this).hasClass("active")){
        let name = $(this).parents(".projectPageWrap").prev().children(".projectPageHeaderContent").children(".projectTit").text();
        $(".modal2").children(".studyTit").text(name + "을");
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');
    }else{
        $(this).removeClass("active");
        $(".rightFavorite").addClass("active");
    }
})






