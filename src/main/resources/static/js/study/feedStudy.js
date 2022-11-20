/**
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

/*댓글 작성자*/
$('.profileName').on("mouseover",function(){
    $(this).children('.hoverView').css("display", "block");
})

$('.profileName').on("mouseout",function(){
    $(this).children('.hoverView').css("display", "none");
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
$('.favoriteSymbol').click(function(){
    $(this).toggleClass('active'); /* active가 없으면 생성, 있으면 삭제*/
});

$(".favorite").on("click", function () {
    $(this).toggleClass('active');
})

// 댓글 열기, 닫기
$(".feedReply").on("click", function () {
    $(this).toggleClass('active');
    if($(this).hasClass('active')){
        $(this).text("댓글 열기");
        $(this).parents('.feedUploadWrap').children('.replyWrap').css("display", "none");
    }else{
        $(this).text("댓글 닫기");
        $(this).parents('.feedUploadWrap').children('.replyWrap').css("display", "flex");
    }

})


// 피드 작성 textarea
/*버튼, 카운팅 div display*/
$(".textarea").on("propertychange change keyup paste input", function () {
    if (!$(this).val()) {
        $("div.btnWrap").css("display", "none");
        $(this).attr("rows", 2);
    } else {
        $("div.btnWrap").css("display", "inline-flex");
        $(this).attr("rows", 5);
    }
})

$(".textarea").on("keyup", function () {
    $("p.count").children(":first").text($(this).val().length);
})