/* *
*
* */


/* 글자 카운터  */
$("#textId").keyup(function(e) {
    //console.log("키업!");
    var content = $(this).val();
    $(".countSpan").text( content.length  ); //실시간 글자수 카운팅
    if (content.length > 500) {
        $(this).val(content.substring(0, 500));

    }
});

/* 나의 문의 수정 수정완료 */

let check=false;
$('#upbtn').click(function () {
    // console.log(this);

    if(!check){
        $(this).closest('.feedContentsWrap').find(".textarea1").attr('disabled', false);
        $(this).text('등록')
        check=true;
    }else{
        $(this).closest('.feedContentsWrap').find(".textarea1").attr('disabled', true);
        $(this).text('수정')
        check=false;
    }
    let $replyText;

    if($(this).text()=="취소"){
        $(this).text("삭제");
        $(this).prev().prev().text("수정");
        $(this).closest('.bottom').find("textarea").val($replyText);
        $(this).closest('.bottom').find("textarea").attr("disabled", true)
            .css("height", "42px");
    }



    // $(this).parent().parent().find("textarea").prop('disabled',true);
})


/* 등록 수정글 */
var $ipt = $('.textarea1');

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
    $(".textarea1").val('').focus();
    $(this).hide();
    $registrationPt.hide();
    $countIpt.hide();
});