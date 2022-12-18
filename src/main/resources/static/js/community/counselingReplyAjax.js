/*
*
*
* */
$(document).ready(function () {

    //작성 ajax
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

    // 댓글 send 클릭
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

    // 화면 dom
    function getReplyList(counselingReplyDTOS) {
        let ic = $("#replyNumber").val();
        // let id = counselingReplyDTOS[0].counseling.counselingId;
        // let seling = $("#"+id).find(".loungeCardContents").find(".loungeCardContentsComponents_loungeContents__262-A").find(".replyComponent_reply__3l-Wc").find(".replyComponent_replyBox__1duHS");
        let seling = $(".loungeCard").eq(ic).find(".loungeCardContents").find(".loungeCardContentsComponents_loungeContents__262-A").find(".replyComponent_reply__3l-Wc").find(".replyComponent_replyBox__1duHS");

        let text = "";
        $(counselingReplyDTOS).each((i, cs) => {
            text += `<div style='border-style: solid; border-width: 0px 0px 1px; border-color: rgb(234, 234, 234); margin-bottom: 12px;' class="replyComponent_replyContainer__3dxJZ">`;
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

            if ($("#userId").attr("value") == cs.user.userId) {
                text += "<div class='userInformationComponents_profileRightGroup__3Ro_j'>";
                text += "<div class='userInformationComponents_profileRightGroupModifyBox__3xfKH'>";
                text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>";
                text += "<img class='' src='\thttps://letspl.me/assets/images/ic-more.svg'>";
                text += "</button>";
                text += "<div class='userInformationComponents_profileRightGroupModifyBoxModal__1csNJ'>";
                text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 updateTry'>수정</button>";
                text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 update'>등록</button>";
                text += "<button class='replyDelete buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>삭제</button>";
                text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 cancelTry'>취소</button>";
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

    // 목록 ajax
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

    //댓글 목록 클릭
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
    // 삭제 모달 띄우기
    $(".centerSectionBox").on("click", ".replyDelete", function () {
        let csid = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children(".csid").val();
        let csidCid = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children(".csidCid").val();
        $(".csidDelete").val(csid);
        $(".csidDeleteCid").val(csidCid);


        $(".replyDelete").attr("style","display : block !important")
        $(".modalTit").text("댓글 삭제 확인");
        $(".commonModalContent p").text("해당 댓글을 삭제하시겠습니까?");
    });

    //삭제 클릭
    $(".replyBtn").on("click", function () {
        var id =  $(".csidDeleteCid").val();
        var replyId= $(".csidDelete").val();
        deleteReply(replyId,id);
    })

    //삭제 ajax
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

    let reply;
    let updateCheck = false;
    //수정 textarea
    $(".centerSection").on("click",".updateTry",function () {
        if(!updateCheck){
            const $pTag = $(this).closest(".replyComponent_replyContainer__3dxJZ").find(".replyComponent_replyContent__3iS4J p");
            reply = $pTag.text();

            $pTag.contents().unwrap().wrap( '<textarea class="replyContent_1j"></textarea>' );

            $(this).parent().find(".updateTry").hide();
            $(this).parent().find(".update").show();
            $(this).parent().find(".replyDelete").hide();
            $(this).parent().find(".cancelTry").show();
            updateCheck=true
        }else{
            alert("한번에 하나의 댓글만 수정 가능합니다.");
        }
    })

    // 수정 취소
    $(".centerSection").on("click",".cancelTry",function () {
        const $textareaTag = $(this).closest(".replyComponent_replyContainer__3dxJZ").find(".replyComponent_replyContent__3iS4J textarea");

        $textareaTag.text(reply)
        $textareaTag.contents().unwrap().wrap( '<p></p>' );

        $(this).parent().find(".updateTry").show();
        $(this).parent().find(".update").hide();
        $(this).parent().find(".replyDelete").show();
        $(this).parent().find(".cancelTry").hide();
        updateCheck=false

    })

    // 수정 send 클릭
    $(".centerSection").on("click",".update",function () {
        const $textareaTag = $(this).closest(".replyComponent_replyContainer__3dxJZ").find(".replyComponent_replyContent__3iS4J textarea");
        var content = $textareaTag.val();
        var id = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().find(".csid").val();
        var postId = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().find(".csidCid").val();

        $.ajax({
            url:"/counseling-reply/replyUpdate/"+id,
            type:"put",
            data:{counselingReplyContent:content},
            success:function (result) {
                showReply(postId);
                updateCheck=false;
            }
        })
    })

})