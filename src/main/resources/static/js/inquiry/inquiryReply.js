/* 텍스트 글자수 카운팅 */

$(".text123").keyup(function(e) {
    //console.log("키업!");
    var content = $(this).val();
    $(".spanCount").text( content.length  ); //실시간 글자수 카운팅
    if (content.length > 500) {
        $(this).val(content.substring(0, 500));
        $('.spanCount').text("(45 / 최대 45자)");
    }
});

/* 수정 등록  */
let check=false;
$('#updateBtn').click(function () {
    // console.log(this);

    if(!check){
        $(this).closest('.feedContentsWrap').find(".textarea2").attr('disabled', false);
        $(this).text('등록')
        $(this).next().next().text('취소')
        check=true;
    }else{
        $(this).closest('.feedContentsWrap').find(".textarea2").attr('disabled', true);
        $(this).text('수정')
        $(this).next().next().text('삭제')
        check=false;
    }
    // $(this).parent().parent().find("textarea").prop('disabled',true);
})




/*댓글 열기*/
$(".feedReply").on("click", function () {
    /* 댓글 열기 두번째 클래스*/
    $(this).toggleClass('active');
    if($(this).hasClass('active')){
        $(this).text("댓글 열기");
        $(this).parents('.feedUploadWrap').children
            /*   댓글전체 클래스*/
            ('.replyWrap').css("display", "none");
    }else{
        $(this).text("댓글 닫기");
        $(this).parents('.feedUploadWrap').children('.replyWrap').css("display", "flex");
    }

})

/* 글 취소 버튼 누를떄 초기화 */

var $ipt = $('.text123');

var  $clearIpt = $('.cance');
var  $registrationPt = $('.registration');
var $countIpt = $('.count');


$ipt.keyup(function(){
    $(".cance").toggle(Boolean($(this).val()));
    $(".registration").toggle(Boolean($(this).val()));
    $(".count").toggle(Boolean($(this).val()));
});

$clearIpt.toggle(Boolean($ipt.val()));
// $registrationPt.toggle(Boolean($ipt.val()));

$clearIpt.click(function(){
    $(".text123").val('').focus();
    $(this).hide();
    $registrationPt.hide();
    $countIpt.hide();
});
