
const $replyContent = $(".inquiryReplyContent");
const $exitBtn = $(".card-exit");
const $cancelBtn = $(".replyCancel");


$exitBtn.click(function () {
    $replyContent.hide();
})

$cancelBtn.click(function () {
    $replyContent.hide();
})

function replyOn(){
//    문의번호 받아오시오.
    var inquiryNumber=0;

    $replyContent.show();

}