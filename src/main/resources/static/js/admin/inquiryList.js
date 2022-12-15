
const $replyContent = $(".inquiryReplyContent");
const $exitBtn = $(".card-exit");
const $cancelBtn = $(".replyCancel");
const $replyTextArea = $(".reply_textarea");

$('#card-head2').on("click", ".card-exit", function () {
    $(".inquiryReplyContent").hide();
    $(".reply_textarea").val('')
    $(".reply_textarea").css('height','23px')
})


    // $exitBtn.click(function () {
    //     $replyContent.hide();
    //     $replyTextArea.val('')
    //     $replyTextArea.css('height','23px')
    // })

$('.replyCancel').on("click", function () {
    $('#pageSection').css("margin-top", "0rem")
    $(".inquiryReplyContent").hide();
    $(".reply_textarea").val('')
    $(".reply_textarea").css('height','23px')
})

// $cancelBtn.click(function () {
//     $replyContent.hide();
//     $replyTextArea.val('')
//     $replyTextArea.css('height','23px')
// })

console.log($replyTextArea)
function replyOn(){
//    문의번호 받아오시오.
//     var inquiryNumber=0;

    $replyContent.show();

}