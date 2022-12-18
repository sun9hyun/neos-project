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
                    // alert("댓글 달기 성공");
                }
            }
            // },
            // error: function (xhr, status, err) {
            //     alert("add 오류");
            //     console.log(xhr, status, err);
            // }
        });
    }

    $("div.centerSectionBox").on("click", ".replyWrite", function(){
        var $replyContent = $(this).parent(".replyComponent_replyButtonGroup__2i0ow").prev().children(".replyContent");
        var $cmid = $(this).prev(".replyCommunity").val();

        // alert("댓글작성클릭");
        // alert("$cmid" +$cmid);
        if($replyContent.val() == ""){
            alert("내용을 입력해주세요");
            return;
        }

        addReply({
            communityReplyContent : $replyContent.val(),
            communityId : $cmid,
            userId : $("#user").val()
        }, function () {
            showReply($cmid);
        })

        $replyContent.val("");
        $(".rereplyTextCount").text("0");

    })


    function getReplyList(communityReplyDTOS) {
        let ic = $("#replyNumber").val();
        // console.log(communityReplyDTOS);
        // console.log(communityReplyDTOS[0].community.communityId);
        // let id = communityReplyDTOS[0].community.communityId;
        // console.log(communityReplyDTOS);
        // let community = $("#"+id).find(".loungeCardContents").find(".loungeCardContentsComponents_loungeContents__262-A").find(".replyComponent_reply__3l-Wc").find(".replyComponent_replyBox__1duHS");
        let community = $(".loungeCard").eq(ic).find(".loungeCardContents").find(".loungeCardContentsComponents_loungeContents__262-A").find(".replyComponent_reply__3l-Wc").find(".replyComponent_replyBox__1duHS");
        let text = "";
        $(communityReplyDTOS).each((i, cr) => {
                text += "<div class='replyComponent_replyContainer__3dxJZ'>";
                text += "<div style='border-style: solid; border-width: 0px 0px 1px; border-color: rgb(234, 234, 234); margin-bottom: 12px;'>";
                text += "<div class='userInformationComponents_userReplySection__3ty7Q'>";
                text += "<div class='userInformationComponents_profile__2pr8a'>";
                text += "<div class='userInformationComponents_profileLeftGroup__1lq6K'>";
                text += "<div class='userInformationComponents_profileLeftGroupLeftBox__2YqvE'>";
                text += "<div class='userInformationComponents_profileImg__3qJcX'>";
                text += "<img class='userInformationComponents_smImg__2uLTY' src='" + cr.user.userFile + "'>";
                text += "</div>";
                text += "<p class='userInformationComponents_profileName__3l0ya'>" + cr.user.userNickName;
                text += "<i class='userInformationComponents_profileLevel__6Bl4w'>";
                text += "<img src='" + cr.user.userNeosPower.userNeosBadge + "' alt='lv'>";
                text += "</i>";
                text += "</p>";

                if (cr.community.user.userId == cr.user.userId) {
                    text += "<div class='userInformationComponents_profileLeftGroupTagBox__1IPyA'>";
                    text += "<div class='tag_tag__2gh0s tag_typeGray__3-1Ag tag_sizeDefault__2V2-5'>";
                    text += "<span style='padding-top: 2px;'>작성자</span>";
                    text += "</div>";
                    text += "</div>";
                }
                text += "</div>";

                text += "<span class='userInformationComponents_recordTime__LAQ3h'>" + communityService.timeForToday(cr.updatedDate) + "</span>";
                text += "</div>";

                if ($("#userId").attr("value") == cr.user.userId) {
                    text += "<div class='userInformationComponents_profileRightGroup__3Ro_j'>";
                    text += "<div class='userInformationComponents_profileRightGroupModifyBox__3xfKH'>";
                    text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>";
                    text += "<img class='' src='https://letspl.me/assets/images/ic-more.svg'>";
                    text += "</button>";
                    text += "<div class='userInformationComponents_profileRightGroupModifyBoxModal__1csNJ'>";
                    text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 updateTry'>수정</button>";
                    text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 update'>등록</button>";
                    text += "<button class='replyDelete buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>삭제</button>";
                    text += "<button class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 cancelTry'>취소</button>";
                    text += "</div></div></div>";
                }

                text += "</div>";
                text += "</div>";
                text += "<div class='replyComponent_replyContent__3iS4J'>";
                text += "<p>" + cr.communityReplyContent + "</p>";
                text += "<input type='hidden' class='cridCid' value='" + cr.community.communityId + "'>"
                text += "<input type='hidden' class='crid' value='" + cr.communityReplyId + "'>"

                text += "<div></div></div></div></div>";
        });

        community.html(text);
    }

    function showReply(id) {
        $.ajax({
            url: "/community-reply/reply/" + id,
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

    $("div.centerSectionBox").on("click", ".replyList", function(){
        let communityId = $(this).closest(".loungeCard").children(".cid").val();
        let i = $(this).closest(".loungeCard").index();
        $("#replyNumber").val(i);
        // alert("댓글리스트 클릭 communityId");
        // alert(communityId);
        // alert(i);

        showReply(communityId);
    })

    /*-----------------------------------------------------------------------------------------------------------*/
    /*삭제*/
    $(".centerSectionBox").on("click", ".replyDelete", function () {
        let crid = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children(".crid").val();
        let cridCid = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children(".cridCid").val();
        $(".cridDelete").val(crid);
        $(".cridDeleteCid").val(cridCid);


        $(".replyDelete").attr("style","display : block !important")
        $(".modalTit").text("댓글 삭제 확인");
        $(".commonModalContent p").text("해당 댓글을 삭제하시겠습니까?");
    });

    $(".replyBtn").on("click", function () {
       var id =  $(".cridDeleteCid").val();
       var replyId= $(".cridDelete").val();
        deleteReply(replyId,id);
    })

    function deleteReply(replyId, id) {
        $.ajax({
            url: "/community-reply/replyDelete/" +replyId,
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
    // $("div.centerSectionBox").on("click", ".postDelete_1j", function () {
    //     let cid = $(this).closest(".loungeCard").children(".cid").val();
    //     $(".cidDelete").val(cid);
    //
    //     $(".modalWrapOpen").attr("style","display : block !important")
    //     $(".modalTit").text("글 삭제 확인");
    //     $(".commonModalContent p").text("해당 글을 삭제하시겠습니까?");
    // })
    //
    // $(".modalWrapOpen").find(".closeBtn").on("click",function () {
    //     $(".modalWrapOpen").attr("style","display : none !important")
    // })
    //
    // $(".modalWrapOpen").find(".doubleBtnWrap").click(function () {
    //     $(".modalWrapOpen").attr("style","display : none !important")
    // })
    //
    // $(".modalWrapOpen").on("click", ".redBtn", function () {
    //     $.ajax({
    //         url: "/community/communityDelete",
    //         type: "delete",
    //         data: JSON.stringify({communityId : $(".cidDelete").val()}),
    //         contentType: "application/json; charset=utf-8",
    //         success: function(){
    //             showUpdate();
    //         },
    //         error: function (xhr, status, err) {
    //             console.log(xhr, status, err);
    //         }
    //     });
    //     $(".modalWrapOpen").attr("style","display : none !important")
    // })

    let reply;
    let updateCheck = false;
//수정
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

    $(".centerSection").on("click",".update",function () {
        const $textareaTag = $(this).closest(".replyComponent_replyContainer__3dxJZ").find(".replyComponent_replyContent__3iS4J textarea");
        var content = $textareaTag.val();
        var id = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().find(".crid").val();
        var postId = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().find(".cridCid").val();
        $.ajax({
            url:"/community-reply/replyUpdate/"+id,
            type:"put",
            data:{communityReplyContent:content},
            success:function (result) {
                showReply(postId);
                updateCheck=false;

            }
        })


    })

})






