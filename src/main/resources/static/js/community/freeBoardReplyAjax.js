/*
*
*
* */
$(document).ready(function () {

    function addReply(reply, callback) {
        $.ajax({
            url: "/community-reply/replyOk",
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                if (callback) {
                    callback(result);
                    alert("댓글 달기 성공");
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
            communityReplyContent : $replyContent.val(),
            communityId : $("#replyCommunity").val(),
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
            url: "/community-reply/reply/" + idc,
            type: "get",
            async: false,
            success: function (communityReplyDTOS) {
                if(communityReplyDTOS != null){
                    getReplyList(communityReplyDTOS);
                }
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        });

    }

    function getReplyList(communityReplyDTOS) {
        // alert("getReplyList");
        // alert($(".cid").attr("value") + " $('#cid').attr('value')");
        // alert("빠른 for문 전")
        // alert(communityReplyDTOS.community.communityId)
        var ic = $("#replyNumber").val();
        alert(ic);
        alert($("div.replyComponent_replyBox__1duHS").eq(ic));
        let text = "";
        $(communityReplyDTOS).each(cr => {
                text += "<div class='replyComponent_replyContainer__3dxJZ'>";
                text += "<div style='border-style: solid; border-widt: 0px 0px 1px; border-color: rgb(234, 234, 234); margin-bottom: 12px;'>";
                text += "<div class='userInformationComponents_userReplySection__3ty7Q'>";
                text += "<div class='userInformationComponents_profile__2pr8a'>";
                text += "<div class='userInformationComponents_profileLeftGroup__1lq6K'>";
                text += "<div class='userInformationComponents_profileLeftGroupLeftBox__2YqvE'>";
                text += "<div class='userInformationComponents_profileImg__3qJcX'>";
                text += "<img class='userInformationComponents_smImg__2uLTY' src='" + cr.user.userFile + "' alt=''>";
                text += "</div>";
                text += "<p class='userInformationComponents_profileName__3l0ya'>" + cr.user.userNickName;
                text += "<i class='userInformationComponents_profileLevel__6Bl4w'>";
                text += "<img src='" + cr.user.userNeosPower.userNeosBadge + "' alt='lv'>";
                text += "</i>";
                text += "</p>";

                // if (cr.community.user.userId == cr.user.userId) {
                    text += "<div class='userInformationComponents_profileLeftGroupTagBox__1IPyA'>";
                    text += "<div class='tag_tag__2gh0s tag_typeGray__3-1Ag tag_sizeDefault__2V2-5'>";
                    text += "<span style='padding-top: 2px;'>작성자</span>";
                    text += "</div>";
                    text += "</div>";
                // }
                text += "</div>";

                text += "<span class='userInformationComponents_recordTime__LAQ3h'>" + communityService.timeForToday(cr.updatedDate) + "</span>";
                text += "</div>";

                // if ($("#userId").attr("value") == cr.user.userId) {
                    text += "<div class='userInformationComponents_profileRightGroup__3Ro_j'>";
                    text += "<div class='userInformationComponents_profileRightGroupModifyBox__3xfKH'>";
                    text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>";
                    text += "<img class='' src='https://letspl.me/assets/images/ic-more.svg'>";
                    text += "</button>";
                    text += "<div class='userInformationComponents_profileRightGroupModifyBoxModal__1csNJ'>";
                    text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>수정</button>";
                    text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>삭제</button>";
                    text += "</div></div></div>";
                // }

                text += "</div>";
                text += "</div>";
                text += "<div class='replyComponent_replyContent__3iS4J'>";
                text += "<p>"+ cr.communityReplyContent+"</p>";
                text += "<div class='replyComponent_replyOpenAndLikeButtonGroup__3r9hv'>";
                text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 buttonComponents_red__3SLsc'>";
                text += "<img class='' src='https://letspl.me/assets/images/ic_talk_up_o.svg'>";
                text += "<span class=''>" + cr.communityReplyLikeCount + "</span>";
                text += "</button>";
                text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 buttonComponents_lightGray__cqa_C'>대댓글쓰기</button>";

                text += "</div><div></div></div></div></div>";
        });

        // var i = $(".cid").attr("value");
        // if($(".loungeCard").find(".cid").val() == i){
        //     var c = $(".loungeCard").find(".cid").val().eq(i).index();
        //     alert(c);
        // };
        // var c = $(".loungeCard").find(".cid").charAt(i);
        // alert(i);
        // alert("c");
        // alert(c);
        // console.log(c);

        // var $card = $(".loungeCard");
        // $card.eq(ic).find(".replyComponent_replyBox__1duHS").append(text);
        $(".loungeCard").eq(ic).find(".loungeCardContents").find(".loungeCardContentsComponents_loungeContents__262-A").find(".replyComponent_reply__3l-Wc").find(".replyComponent_replyBox__1duHS").append(text);
        // $("div.replyComponent_replyBox__1duHS").eq(ic).append(text);
        // console.log($(".loungeCard"));
        // console.log($(".replyComponent_replyBox__1duHS"));
        // console.log($card.eq(ic));
        // console.log($card.eq(ic).find(".replyComponent_replyBox__1duHS"));
        // alert($card.eq(ic).find(".replyComponent_replyBox__1duHS"));
    }

    $("div.centerSectionBox").on("click", ".replyList", function(){
        let communityId = $(this).closest(".loungeCard").children(".cid").val();
        var i = $(this).closest(".loungeCard").index();
        $("#replyNumber").val(i);
        alert("communityId");
        alert(communityId);
        alert(i);


        showReply(communityId);
    })



})