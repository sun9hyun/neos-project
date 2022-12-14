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
                if (callback) {
                    callback(result);
                    // alert("댓글 달기 성공");
                }
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        });
    }

    $("div.centerSectionBox").on("click", ".replyWrite", function(){
        var $replyContent = $(this).parent(".replyComponent_replyButtonGroup__2i0ow").prev().children(".replyContent");
        var $csid = $(this).prev(".replyCounseling").val();

        // alert("댓글작성클릭");
        // alert("$csid" +$csid);

        if($replyContent.val() == ""){
            alert("내용을 입력해주세요");
            return;
        }

        // alert($("#user").val());

        addReply({
            counselingReplyContent : $replyContent.val(),
            counselingId : $csid,
            userId : $("#user").val()
        }, function () {
            showReply($csid);
        })

        $replyContent.val("");
        $(".rereplyTextCount").text("0");

    })
    
    function getReplyList(counselingReplyDTOS) {
        let ic = $("#replyNumber").val();
        // alert("getReplyList");
        // alert("$('#replyNumber').val()  "+ic);
        // alert(counselingReplyDTOS);
        let seling = $(".loungeCard").eq(ic).find(".loungeCardContents").find(".loungeCardContentsComponents_loungeContents__262-A").find(".replyComponent_reply__3l-Wc").find(".replyComponent_replyBox__1duHS");
        let text = "";
        $(counselingReplyDTOS).each((i, cs) => {
            text += "<div style='border-style: solid; border-width: 0px 0px 1px; border-color: rgb(234, 234, 234); margin-bottom: 12px;'>";
            text += "<div class='userInformationComponents_userReplySection__3ty7Q'>";
            text += "<div class='userInformationComponents_profile__2pr8a'>";
            text += "<div class='userInformationComponents_profileLeftGroup__1lq6K'>";
            text += "<div class='userInformationComponents_profileLeftGroupLeftBox__2YqvE'>";
            text += "<div class='userInformationComponents_profileLeftGroupTagBox__1IPyA'>";
            text += "<div class='tag_tag__2gh0s tag_typeGray__3-1Ag tag_sizeDefault__2V2-5'>";
            text += "<span>익명</span>";
            text += "</div>";
            text += "</div>";
            text += "</div>";
            text += "<span class='userInformationComponents_recordTime__LAQ3h'>" +counselingService.timeForToday(cs.updatedDate)+ "</span>";
            text += "</div>";

            if ($("#userId").attr("value") == cr.user.userId) {
                text += "<div class='userInformationComponents_profileRightGroup__3Ro_j'>";
                text += "<div class='userInformationComponents_profileRightGroupModifyBox__3xfKH'>";
                text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>";
                text += "<img class='' src='\thttps://letspl.me/assets/images/ic-more.svg'>";
                text += "</button>";
                text += "<div class='userInformationComponents_profileRightGroupModifyBoxModal__1csNJ'>";
                // text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>수정</button>";
                text += "<button class='replyDelete buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>삭제</button>";
                text += "</div>";
                text += "</div>";
                text += "</div>";
            }

            text += "</div>";
            text += "</div>";
            text += "<div class='replyComponent_replyContent__3iS4J'>";
            text += "<p>" + cs.counselingReplyContent + "</p>";
            text += "<input type='hidden' class='csidCid' value='" + cs.counseling.counselingId + "'>";
            text += "<input type='hidden' class='csid' value='" + cs.counselingReplyId + "'>"
            text += "<div></div>";
            text += "</div>";
            text += "</div>";
        });

        seling.html(text);

    }

    function showReply(id) {
        $.ajax({
            url: "/counseling-reply/reply/" + id,
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

    $("div.centerSectionBox").on("click", ".replyList", function(){
        let counselingId = $(this).closest(".loungeCard").children(".cid").val();
        let i = $(this).closest(".loungeCard").index();
        $("#replyNumber").val(i);
        // alert("댓글리스트 클릭 counselingId");
        // alert(counselingId);
        // alert(i);

        showReply(counselingId);
    })

    /*-----------------------------------------------------------------------------------------------------------*/
    /*삭제*/
    $(".centerSectionBox").on("click", ".replyDelete", function () {
        let csid = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children(".csid").val();
        let csidCid = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children(".csidCid").val();
        $(".csidDelete").val(csid);
        $(".csidDeleteCid").val(csidCid);


        $(".replyDelete").attr("style","display : block !important")
        $(".modalTit").text("댓글 삭제 확인");
        $(".commonModalContent p").text("해당 댓글을 삭제하시겠습니까?");
    });

    $(".replyBtn").on("click", function () {
        var id =  $(".csidDeleteCid").val();
        var replyId= $(".csidDelete").val();
        deleteReply(replyId,id);
    })

    function deleteReply(replyId, id) {
        $.ajax({
            url: "/counseling-reply/replyDelete/" +replyId,
            type: "delete",
            contentType: "application/json; charset=utf-8",
            success: function(){
                showReply(id);
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        });
        $(".modalWrapOpen").attr("style","display : none !important")
    }




})