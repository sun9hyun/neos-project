/*
 *
 */

// 프로필 호버 DIV
/*상세보기 상단 작성자*/
$('.userIdWrap').on("mouseover",function(){
    $(this).children().children('.idHoverMenu').css("display", "block");
})

$('.userIdWrap').on("mouseout",function(){
    $(this).children().children('.idHoverMenu').css("display", "none");
})

/*우측 리더 정보*/
$('.userIdTxt').on("mouseover",function(){
    $(this).children('.idHoverMenu').css("display", "block");
})

$('.userIdTxt').on("mouseout",function(){
    $(this).children('.idHoverMenu').css("display", "none");
})

/*우측 구독중인 네오스인*/
$('.hoverNameWrap').on("mouseover",function(){
    $(this).children('.hoverName').css("display", "block");
})

$('.hoverNameWrap').on("mouseout",function(){
    $(this).children('.hoverName').css("display", "none");
})

/*스터디 멤버*/
$('.userName').on("mouseover",function(){
    $(this).children('.idHoverMenu').css("display", "block");
})

$('.userName').on("mouseout",function(){
    $(this).children('.idHoverMenu').css("display", "none");
})



// 작은 하트 호버
$('.favoriteSymbol').on("mouseover", function () {
    $(this).children('.favorite').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-empty-hover.03f7a2966e132a098a985cdc42f83b46.svg)")
})

$('.favoriteSymbol').on("mouseout", function () {
    $(this).children('.favorite').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-empty-white.b1db0e3e169a5cf61afa41daf5bfdc15.svg)")
    $(this).children('.active').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-full.5a5209416a8859031c9099b5540c281a.svg)")
})

// 작은 하트 활성화 선택
$(".favoriteUser").on("click", function () {
    $(this).toggleClass("active");
})


// 내용부 상단 탭에 따른 내용 변화
$('.memberMag').click(function(){
    $(this).toggleClass('active'); /* active가 없으면 생성, 있으면 삭제*/
    $(this).parent().children('.contentsMag').removeClass('active');
    $(this).parents('.projectTab').children('.memberManagementTab').toggleClass('active',true);
    $(this).parents('.projectTab').children('.projectMangementTab').toggleClass('active',false);
});

$('.contentsMag').click(function(){
    $(this).toggleClass('active'); /* active가 없으면 생성, 있으면 삭제*/
    $(this).parent().children('.memberMag').removeClass('active');
    $(this).parents('.projectTab').children('.memberManagementTab').toggleClass('active',false);
    $(this).parents('.projectTab').children('.projectMangementTab').toggleClass('active',true);
});

// 정보관리, 업데이트 완료
const $placeholder = $("#contents").attr("placeholder");
$(".proejectModifyBtn").on("click",function(){
    if($(".studyTitleInput").val().length < 3){ // 제목 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal3").css('display','inline-block');
    }else if($(".studyKeywordInput").val().length < 1){ // 키워드 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal4").css('display','inline-block');
    }else if($placeholder == $("#contents").val()){ // 내용 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal5").css('display','inline-block');
    }else{  // 작성 완료
        $(".modalWrapOpen").show();
        $(".modal1").css('display','inline-block');
    }
});

// 스터디 포기 버튼
$(".proejectCloseBtn").on("click",function () {
    $(".modalWrapOpen").show();
    $(".modal6").css('display','inline-block');
})

// 모두 멤버로 승인
$(".allJoin").on("click",function () {
    $(".modalWrapOpen").show();
    $(".modal7").css('display','inline-block');
})

// 지원자 탈락
$(".rejectionBtn").on("click",function () {
    $(".modalWrapOpen").show();
    $(".modal8").css('display','inline-block');
})

// 모집 종료
$(".endBtn").on("click",function () {
    $(".modalWrapOpen").show();
    $(".modal9").css('display','inline-block');
})

// 인원 추가
$(".addBtn").on("click",function () {
    $(".modalWrapOpen").show();
    $(".modal10").css('display','inline-block');
})

// 리더 변경
$(".readerChangeBtn").on("click",function () {
    $(".modalWrapOpen").show();
    $(".modal11").css('display','inline-block');
})

// 멤버 방출
$(".releaseBtn").on("click",function () {
    $(".modalWrapOpen").show();
    $(".modal12").css('display','inline-block');
})