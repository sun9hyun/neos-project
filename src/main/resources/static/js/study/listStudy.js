/*
 *
 */

console.log("스터디 목록 들어옴")

// 슬라이드 배너 관련
var swiper = new Swiper(".mySwiper", {
    slidesPerView: 1,
    spaceBetween: 30,
    centeredSlides: true,
    rewind: true,
    autoplay: {
        delay: 5000,
        disableOnInteraction: false,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
});



/*--정보 대학교 선택-----------------------------------------------------*/
var $citySelect = $(".citySelect");

$('.uniSelect').attr('disabled',true);
// $('.fieldSelect').attr('disabled',true);

$citySelect.on("change", function() {
    var $city = $(this).val();

    if($city == "KR00"){
        $('.uniSelect').attr('disabled',true);
        // $('.fieldSelect').attr('disabled',true);
    }else{
        $('.uniSelect').removeAttr('disabled');
        // $('.fieldSelect').removeAttr('disabled');
    }
});

/*-----------------------모달-------------------------------------------*/
// 모달 종료
$(".closeBtnImg").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

// $(".blueBtn").on("click",function(){
//     $(".modalWrap").css('display','none');
//     $(".modalWrapOpen").hide();
// });

$(".whiteBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

// 스터디 좋아요 버튼 클릭
// $(".favorite").on("click",function(){
//     if($(this).hasClass("active")){
//         $(this).removeClass('active');
//     }else{
//         let name = $(this).parent().parent().next().children().children(".tit").text();
//         // let name = $(this).parents(".studyInfoWrap").find(".txtWrap").children(".tit").text();
//         console.log(name);
//         $(".modal1").children(".studyTit").text(name + "을");
//         $(".modalWrapOpen").show();
//         $(".modal1").css('display','inline-block');
//
//         $(".blueBtn").on("click",function(){
//             $(".modalWrap").css('display','none');
//             $(".modalWrapOpen").hide();
//             $(".tit").indexOf(name).parent().parent().next().children()
//             $(this).toggleClass('active');
//         });
//         }
// });


/*스터디 누르면 나오는 모달 + 하트 불 들어오고 나가고*/
function modalOk(heart) {
    $(".modalWrapOpen .modal1 .btnWrap button.blueBtn").on("click", function () {
        $(".modal1").css('display','none');
        $(".modalWrapOpen").hide();
        $(heart).addClass('active2');
        $(heart).addClass('on');
        $(heart).removeClass('off');
    })
}

$(".listFavorite.off").click(function(){
    let name = $(this).closest(".projectGridWrap").find(".projectWrap .tit").text();
    const heart = $(this);
    if($(this).hasClass("off")) {
        heart.addClass('click');
    }

    $(".modal1").children(".studyTit").text(name + "을");

    if($(this).hasClass("off")) {
        $(".modalWrapOpen").show();
        $(".modal1").css('display','inline-block');
        $(".modalWrapOpen .modal1 .btnWrap button.blueBtn").on("click", function () {
            $(".modal1").css('display','none');
            $(".modalWrapOpen").hide();
            $(".click").addClass('active');
            $(".click").addClass('on');
            $(".click").removeClass('off');
            $(".click").removeClass('click');
        })
    } else {
        $(this).removeClass('active');
        $(this).addClass('off');
        $(this).removeClass('on');
    }
});

// 탑 스터디 좋아요 구독하기 버튼
$(".topFavorite.off").click(function(){
    let name = $(this).closest(".newProjectTopWrap").find(".txtWrap .left .tit").text();
    const heart = $(this);
    if($(this).hasClass("off")) {
        heart.addClass('click');
    }

    $(".modal1").children(".studyTit").text(name + "을");

    if($(this).hasClass("off")) {
        $(".modalWrapOpen").show();
        $(".modal1").css('display','inline-block');
        $(".modalWrapOpen .modal1 .btnWrap button.blueBtn").on("click", function () {
            $(".modal1").css('display','none');
            $(".modalWrapOpen").hide();
            $(".click").addClass('active');
            $(".click").addClass('on');
            $(".click").removeClass('off');
            $(".click").removeClass('click');
        })
    } else {
        $(this).removeClass('active');
        $(this).addClass('off');
        $(this).removeClass('on');
    }
});


// $(".blueBtn").on("click",function(){
//     $(".modalWrap").css('display','none');
//     $(".modalWrapOpen").hide();
//     $(this).toggleClass('active');
// });


/*
// 하트 활성화 선택
$(".favorite").on("click", function () {
    $(this).toggleClass('active');
})*/
