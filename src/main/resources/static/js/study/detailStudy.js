/*
 *
 */

console.log("상세보기 들어옴");

// 프로필 호버 DIV
$('.userIdWrap').on("mouseover",function(){
    $(this).children().children('.idHoverMenu').css("display", "block");
})

$('.userIdWrap').on("mouseout",function(){
    $(this).children().children('.idHoverMenu').css("display", "none");
})

$('.userName').on("mouseover",function(){
    $(this).children('.idHoverMenu').css("display", "block");
})

$('.userName').on("mouseout",function(){
    $(this).children('.idHoverMenu').css("display", "none");
})

$('.userIdTxt').on("mouseover",function(){
    $(this).children('.idHoverMenu').css("display", "block");
})

$('.userIdTxt').on("mouseout",function(){
    $(this).children('.idHoverMenu').css("display", "none");
})

$('.hoverNameWrap').on("mouseover",function(){
    $(this).children('.hoverName').css("display", "block");
})

$('.hoverNameWrap').on("mouseout",function(){
    $(this).children('.hoverName').css("display", "none");
})



// 작은 하트 호버
$('.favoriteSymbol').on("mouseover", function () {
    $(this).children('.favorite').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-empty-hover.03f7a2966e132a098a985cdc42f83b46.svg)")
})

$('.favoriteSymbol').on("mouseout", function () {
    $(this).children('.favorite').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-empty-white.b1db0e3e169a5cf61afa41daf5bfdc15.svg)")
    $(this).children('.active').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-full.5a5209416a8859031c9099b5540c281a.svg)")
})

$(".favoriteUser").on("click", function () {
    $(this).toggleClass("active");
})


/*-----------------------모달-------------------------------------------*/
// // 모달 종료
// $(".closeBtnImg").on("click",function(){
//     $(".modalWrap").css('display','none');
//     $(".modalWrapOpen").hide();
// });
//
// $(".blueBtn").on("click",function(){
//     $(".modalWrap").css('display','none');
//     $(".modalWrapOpen").hide();
// });
//
// $(".whiteBtn").on("click",function(){
//     $(".modalWrap").css('display','none');
//     $(".modalWrapOpen").hide();
// });

// 상세보기 정보 채팅
// $(".neosBtn").on("click",function(){
//     let name = $(this).parents(".letspler_Re").children(".top").children(".profileTxt").children(".idWrap").children(".userName").text().split(" ")[0];
//     $(".modal1").children(".modalContent").children(".modalName").text(name + "님과 대화를 시작합니다.");
//     $(".modalWrapOpen").show();
//     $(".modal1").css('display','inline-block');
// });

// $(".chatList").on("click",function(){
//     let name = $(this).parents(".userName").text().split(" ")[0];
//     $(".modal1").children(".modalContent").children(".modalName").text(name + "님과 대화를 시작합니다.");
//     $(".modalWrapOpen").show();
//     $(".modal1").css('display','inline-block');
// });

// 지원하기 버튼 클릭
$(".postionApplyBtn").on("click",function () {
    let name = $(this).parents(".projectPageWrap").prev().children(".projectPageHeaderContent").children(".projectTit").text();
    $(".modal3").children(".studyTit").text(name + "에");
    $(".modalWrapOpen").show();
    $(".modal3").css('display','inline-block');
})


/*------------------------상세보기 공통---------------------------------*/
/*
// 탑 작성자 채팅
$(".chatTop").on("click",function(){
    let name = $(this).parents(".userId").children(".userIdTxt").text();
    $(".modal1").children(".modalContent").children(".modalName").text(name + " 님과 대화를 시작합니다.");
    $(".modalWrapOpen").show();
    $(".modal1").css('display','inline-block');
});

// 오른쪽 리더 채팅
$(".chatReader").on("click",function(){
    let name = $(this).parents(".rightBox").children(".userIdTxt").text().split(" ")[0];
    $(".modal1").children(".modalContent").children(".modalName").text(name + "님과 대화를 시작합니다.");
    $(".modalWrapOpen").show();
    $(".modal1").css('display','inline-block');
});

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
*/






