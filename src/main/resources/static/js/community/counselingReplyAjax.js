/*
*
*
* */
$(document).ready(function () {

    function addReply(reply, callback) {
        $.ajax({
            url: "/counseling-reply/replyOk",
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                alert("댓글 달기 성공");
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        });
    }

    $("div.centerSectionBox").on("click", ".replyWrite", function(){
        var $replyContent = $(this).parent(".replyComponent_replyButtonGroup__2i0ow").prev().children(".replyContent");

        if($replyContent.val() == ""){
            alert("내용을 입력해주세요");
            return;
        }

        // alert($("#user").val());

        addReply({
            counselingReplyContent : $replyContent.val(),
            counselingId : $("#replyCounseling").val(),
            userId : $("#user").val()
        }, function () {
            showReply();
        })

        $replyContent.val("");

    })

    function showReply(id) {
        // alert("showReply")
        var idc = id;
        $.ajax({
            url: "/counseling-reply/reply/" + idc,
            type: "get",
            async: false,
            success: function (counselingReplyDTOS) {
                if(counselingReplyDTOS != null){
                    getReplyList(counselingReplyDTOS);
                }
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        });

    }






}