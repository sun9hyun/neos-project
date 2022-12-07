/*
*
*
* */
$(document).ready(function () {
    show();

    function add(community, callback) {
        $.ajax({
            url: "/community/communityOk",
            type: "post",
            data: JSON.stringify(community),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        });
    }

    function show() {
        $.ajax({
            url: "/community/communityList",
            type: "get",
            async: false,
            success: function (communityDTOS) {
                if(communityDTOS != null){
                    getList(communityDTOS);
                }
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        });
    };

    $("#sendButton").on("click", function () {
        var $title = $("#communityTitle");
        var $content = $("#summernote");

        if($title.val() == ""){
            alert("제목을 입력해주세요");
            return;
        }

        if($content.val() == ""){
            alert("내용을 입력해주세요");
            return;
        }

        add({
            communityTitle: $title.val(),
            communityContent: $content.val(),
            userId: $("#user").val()
        }, function(){
            show();
        });

        $(".editorMdoal").attr("style","display:none !important;");
        $(".editorMdoal").find(".editorTitle input").val("");
        $(".editorMdoal").find(".note-editable").text("");
        $(".updatePost_1j").css("display","none");
    });

    function getList(communityDTOS) {
        // alert(communityDTOS);
        let text = "";
        communityDTOS.forEach(c => {
            text += "<div class='loungeCard' style='margin-bottom: -10px;'>";
            text += "<div class='userSection'>";
            text += "<div class='userInformationComponents_profile__2pr8a'>";
            text += "<div class='userInformationComponents_profileLeftGroup__1lq6K'>";
            text += "<div class='userInformationComponents_profileImg__3qJcX'>";
            text += "<img src='" + c.user.userFile + "' alt=''>";
            text += "</div>";
            text += "<p class='userInformationComponents_profileName__3l0ya'>" + c.user.userNickName;
            text += "<i class='userInformationComponents_profileLevel__6Bl4w'>";
            text += "<img src='" + c.user.userNeosPower.userNeosBadge + "' alt=''>";
            text += "</i>";
            text += "</p>";
            text += "</div>";
            text += "<div class='userInformationComponents_profileRightGroup__3Ro_j'>";
            text += "<div class='userInformationComponents_profileRightGroupFollow__kBAl7'>";
            text += "<button type='button' class='buttonComponents_button__1hvQa buttonComponents_radius__2l9SM'>";
            //userFollower
            //if(userFollower == )
            text += "<img src='https://letspl.me/assets/images/ic-letspler-heart-full.png'>팔로잉</button>";
            text += "</div></div></div></div>";
            text += "<div class='loungeCardContents'>";
            text += "<div class='loungeCardContentsComponents_loungeContents__262-A'>";

            //if(session.loginUser == c.userId)?
            if($("#userId").attr("value") == c.user.userId){
                text += "<div class='loungeCardContentsComponents_loungeContentsTagMoreBox__1oKkG'>";
                text += "<div class='undefined'>";
                text += "</div>";
                text += "<div class='loungeCardContentsComponents_loungeContentsRight__14cdz'>";
                text += "<div class='loungeCardContentsComponents_loungeContentsMore__2AXVI'>";
                text += "<button type='button' class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5'>";
                text += "<img class='buttonComponents_mdImg__G1pNE' src='https://letspl.me/assets/images/ic-more.svg'>";
                text += "</button>";
                text += "<div class='loungeCardContentsComponents_loungeContentsMoreButtonBox__2jERo'>";
                text += "<button type='button' class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 postUpdate_1j'>수정</button>";
                text += "<button type='button' class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 postDelete_1j'>삭제</button>";
                text += "</div></div></div></div>";
            }

            text += "<div class='loungeCardContentsComponents_loungeContentsTitleBox__2NtjH'>";
            text += "<span class='loungeCardContentsComponents_loungeContentsTitleBoxNewsTxt__9_Iok'>자유게시글</span>";
            text += "<div class='loungeCardContentsComponents_loungeContentsTitleDate__1-Xd2'>";
            text += "<h3 class='comTitle loungeCardContentsComponents_title__1s8RE'>" + c.communityTitle + "</h3>";
            text += "<span class='loungeCardContentsComponents_date__3pY5X'>" +  communityService.timeForToday(c.updatedDate) + "</span>";
            text += "</div></div>";
            text += "<div class='comContent loungeCardContentsComponents_loungeContentsAreaDefault__3QCG4'>"+ c.communityContent +"</div>";
            text += "<div class='loungeCardContentsComponents_loungeContentsMoreButtonWrap__1LR4v'>";
            text += "<button type='button' class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 buttonComponents_blue__1FlTU'>펼치기</button>";
            text += "</div>";
            text += "<div class='replyComponent_reply__3l-Wc'>";
            text += "<div class='replyComponent_replyButtonBox__2O3ME'>";
            text += "<button type='button' class='likeBtn buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 button_heart_1ljw5'>";
            text += "<img class='likeImg' src='https://letspl.me/assets/images/ic-letspler-heart-empty.png'>"+ c.communityLikeCount;
            text += "</button>";
            text += "<div class='replyComponent_replyOnOff__QKoso'>";
            text += "<button type='button' class='buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 buttonComponents_blue__1FlTU'>";
            text += "<span class='buttonComponents_gray__1blI9'>첫번째 댓글을 달아주세요</span>";
            text += "<img class='buttonComponents_smMdImg__2IOAr' src='https://letspl.me/assets/images/ic-arrow-up.svg'>";
            text += "</button>";
            text += "</div>";
            text += "</div>";
            text += "</div></div></div>";
            text += "<input id='cid' type='hidden' value='" + c.communityId + "'>";
            text += "<input type='hidden' value='" + c.user.userId + "'>";
            text += "</div>";
        });

        $("div.centerSectionBox").html(text);
    }

    /*-----------------------------------------------------------------------------------------------------------*/
    //수정
    $("div.centerSectionBox").on("click", ".postUpdate_1j", function () {
        const $titles = $(".comTitle");
        const $contents = $(".comContent");

        let i = $(this).closest(".loungeCard").index();
        let up = $(this).closest(".loungeCard").children("#cid").val();

        $("#cidUpdate").val(up);

        var $title = $titles.eq(i).text();
        var $content = $contents.eq(i).text();

        $(".editorMdoal").find(".editorTitle input").val($title);
        $(".editorMdoal").find(".note-editable").text($content);

        $(".editorMdoal").attr("style","display:flex !important;");
        $(".updatePost_1j").css("display","block");
        $(".registerPost_1j").css("display","none");
    })


    $(".updatePost_1j").on("click", function () {
        $.ajax({
            url: "/community/communityUpdate",
            type: "put",
            data: JSON.stringify({
                communityTitle: $("#communityTitle").val(),
                communityContent: $("#summernote").val(),
                communityId : $("#cidUpdate").val()
            }),
            contentType: "application/json; charset=utf-8",
            success: function(){
                show();
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        });

        $(".updatePost_1j").css("display","none");
        $(".editorMdoal").attr("style","display:none !important;")
        $(".editorMdoal").find(".editorTitle input").val("");
        $(".editorMdoal").find(".note-editable").text("");
    })

    /*-----------------------------------------------------------------------------------------------------------*/
    /*삭제*/
    $("div.centerSectionBox").on("click", ".postDelete_1j", function () {
        let cid = $(this).closest(".loungeCard").children("#cid").val();
        $(".cidDelete").val(cid);

        $(".modalWrapOpen").attr("style","display : block !important")
        $(".modalTit").text("글 삭제 확인");
        $(".commonModalContent p").text("해당 글을 삭제하시겠습니까?");
    })

    $(".modalWrapOpen").find(".closeBtn").on("click",function () {
        $(".modalWrapOpen").attr("style","display : none !important")
    })

    $(".modalWrapOpen").find(".doubleBtnWrap").click(function () {
        $(".modalWrapOpen").attr("style","display : none !important")
    })

    $(".modalWrapOpen").on("click", ".redBtn", function () {
        $.ajax({
            url: "/community/communityDelete",
            type: "delete",
            data: JSON.stringify({communityId : $(".cidDelete").val()}),
            contentType: "application/json; charset=utf-8",
            success: function(){
                show();
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        });
        $(".modalWrapOpen").attr("style","display : none !important")
    })




})

// 서비스에 대한 기능들을 하나의 모듈로 묶어서 처리한다.
let communityService = (function() {
    function timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금전';
        if (betweenTime < 60) {
            return `${betweenTime}분전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년전`;
    }

    function detail(communityId){
        $.ajax({
            url: "/community/communityDetail",
            type: "post",
            data: JSON.stringify(communityId),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    return {timeForToday:timeForToday, detail:detail}
})();
