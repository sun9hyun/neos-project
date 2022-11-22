
const $replyContent = $(".inquiryReplyContent");
const $exitBtn = $(".card-exit");
const $cancelBtn = $(".replyCancel");
const $replyTextArea = $(".reply_textarea");

$exitBtn.click(function () {
    $replyContent.hide();
    $replyTextArea.val('')
    $replyTextArea.css('height','23px')
})

$cancelBtn.click(function () {
    $replyContent.hide();
    $replyTextArea.val('')
    $replyTextArea.css('height','23px')
})

console.log($replyTextArea)
function replyOn(){
//    문의번호 받아오시오.
    var inquiryNumber=0;

    $replyContent.show();

}