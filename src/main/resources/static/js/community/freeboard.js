

/*const $commentButton = $(".replyComponent_replyOnOff__QKoso").find(
    ".buttonComponents_button__1hvQa"
);

let commentButtonCheck = false;

$commentButton.on("click", function () {
    var $superTag = $(this).closest(".replyComponent_reply__3l-Wc");
    if ($superTag.children().length == 1 && !commentButtonCheck) {
        commentButtonCheck = true;
        $superTag.append(
            `<div class="replyComponent_replyFilter__10kow">
        <button class="buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 buttonComponents_black__3vMzB   ">
        <img class="buttonComponents_smImg__1vYDM" src="../images/community/bluedot.png">등록순</button>
        <button class="buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 buttonComponents_gray__1blI9   ">최신순</button></div>`
        );
        $superTag.append(
            `<div class="replyComponent_replyInputBox__2yLKK">
        <div class="replyComponent_replyInputContainer__3TJW8">
        <textarea class="replyComponent_replyInput__2vKhX" maxlength="1000" placeholder="댓글을 입력해주세요." style="height: 21px !important;
        resize: none;" oninput='this.style.height = "21px"; this.style.height = this.scrollHeight + "px"'></textarea></div>
        <div class="replyComponent_replyButtonGroup__2i0ow">
        <div class="replyComponent_replyButtonGroupCount__j0XkV">
        <span class="textCount">0</span><span>/</span><span>1000</span>
        </div><button class="buttonComponents_button__1hvQa buttonComponents_square__3hf2r    ">확인</button></div></div>`
        );
        $superTag.append(
            `<div class="replyComponent_replyBox__1duHS"><div class="replyComponent_replyContainer__3dxJZ"></div></div>`
        );
    } else if ($superTag.children().length == 4) {
        $superTag.find(".replyComponent_replyFilter__10kow").remove();
        $superTag.find(".replyComponent_replyInputBox__2yLKK").remove();
        $superTag.find(".replyComponent_replyBox__1duHS").remove();
        commentButtonCheck = false;
    }
});*/

/*댓글*/
var $commentButton = $(".replyComponent_replyOnOff__QKoso");


$commentButton.on("click",function () {
    if($(this).closest(".replyComponent_reply__3l-Wc").find(".replyComponent_replyFilter__10kow").css("display")=="flex"){
        $(this).closest(".replyComponent_reply__3l-Wc").find(".replyComponent_replyFilter__10kow").css("display","none");
        $(this).closest(".replyComponent_reply__3l-Wc").find(".replyComponent_replyInputBox__2yLKK").attr("style","display: none !important");
        $(this).closest(".replyComponent_reply__3l-Wc").find(".replyComponent_replyBox__1duHS").css("display","none");
    }else{
        $(this).closest(".replyComponent_reply__3l-Wc").find(".replyComponent_replyFilter__10kow").css("display","flex");
        $(this).closest(".replyComponent_reply__3l-Wc").find(".replyComponent_replyInputBox__2yLKK").attr("style","display: flex !important");
        $(this).closest(".replyComponent_reply__3l-Wc").find(".replyComponent_replyBox__1duHS").css("display","block");
    }

})


/*댓글*/
$(".replyComponent_reply__3l-Wc").on("keyup", ".replyTextArea",function (e) {
        let content = $(this).val();
        if (content.length == 0 || content == "") {
            $(this).parent().next().find(".replyTextCount").text("0");
        } else {
            $(this).parent().next().find(".replyTextCount").text(content.length);
        }
    }
);

$(".rereply_textarea").on("keyup", function (e) {
        let content = $(this).val();
        if (content.length == 0 || content == "") {
            $(this).next().find(".rereplyTextCount").text("0")
        } else {
            $(this).next().find(".rereplyTextCount").text(content.length);
        }
    }
);


/* 위까지가 텍스트에리아 즉 댓글쓰기 */
const $heartButtons = $(".replyComponent_replyButtonBox__2O3ME").find(
    ".button_heart_1ljw5"
);

$heartButtons.on("click", function () {
    var count = parseInt($(this).text());
    var emptyHeart = `<img class="" src="https://letspl.me/assets/images/ic-letspler-heart-empty.png">`;
    var fullHeart = `<img class="" src="https://letspl.me/assets/images/ic-letspler-heart-full.png">`;
    if (
        $(this).find("img").attr("src") ==
        "https://letspl.me/assets/images/ic-letspler-heart-empty.png"
    ) {
        $(this).html(fullHeart + (count + 1));
    } else {
        $(this).html(emptyHeart + (count - 1));
    }
});

/* 좋아요버튼 */
$(".replyComponent_reply__3l-Wc").on(
    "click",
    ".replyComponent_replyFilter__10kow button",
    function () {
        if ($(this).hasClass("buttonComponents_gray__1blI9")) {
            $(this)
                .parent()
                .find("button")
                .each((i, item) => {
                    if ($(item).hasClass("buttonComponents_black__3vMzB")) {
                        $(item)
                            .removeClass("buttonComponents_black__3vMzB")
                            .addClass("buttonComponents_gray__1blI9");
                        $(item).find("img").remove();
                    }
                });
            $(this)
                .removeClass("buttonComponents_gray__1blI9")
                .addClass("buttonComponents_black__3vMzB");
            $(this).prepend(
                `<img class="buttonComponents_smImg__1vYDM" src="../images/community/bluedot.png">`
            );
        }
    }
);
/* 댓글 최신순 정렬 */

const $cardMoreButton = $(
    ".loungeCardContentsComponents_loungeContentsMoreButtonWrap__1LR4v"
).find(".buttonComponents_blue__1FlTU");

$cardMoreButton.on("click", function () {
    const $card = $(this).parent().prev();
    if (
        $card.hasClass(
            "loungeCardContentsComponents_loungeContentsAreaDefault__3QCG4"
        )
    ) {
        $card
            .removeClass(
                "loungeCardContentsComponents_loungeContentsAreaDefault__3QCG4"
            )
            .addClass("loungeCardContentsComponents_loungeContentsAreaExpand__3U5Se");
        $(this).text("닫기");
    } else {
        $card
            .removeClass(
                "loungeCardContentsComponents_loungeContentsAreaExpand__3U5Se"
            )
            .addClass(
                "loungeCardContentsComponents_loungeContentsAreaDefault__3QCG4"
            );
        $(this).text("펼치기");
    }
});

/* 게시글 펼치기 */


/*대댓글 펼치기*/
const $rereplyButton = $(".replyComponent_replyOpenAndLikeButtonGroup__3r9hv").find(".rereplyButton_1j")
$rereplyButton.on("click",function(){
    var $button = $(this).parent().next().find(".rereplyContainer__3x");
    if($button.css("display")=='block'){
        $button.css("display","none");
    }else{
        $button.css("display","block");
    }
})

/*모달*/
const $writeBtn = $(".postBtn");
const $cancelBtn = $(".editorWrapButtonBoxLeft").find(".buttonComponents_md__26Zib");
const $exitBtn = $(".closeButtonBox").find("img");
const $registerBtn = $(".editorWrapButtonBoxRight").find("button")
$writeBtn.on("click",function(){
    $(".editorMdoal").attr("style","display:flex !important;");
    $(".registerPost_1j").css("display","block");
})

//취소버튼
$(document).ready(function(){
    $cancelBtn.hover(function(){
        $(this).css('background-color','#1a7cff');
        $(this).css('border-color','#1a7cff');
        $(this).css('color','#fff');
        $(this).css('opacity','0.5')
    },function(){
        $(this).css('background-color','#fff');
        $(this).css('border','1px solid #ced2d4');
        $(this).css('color','black');
        $(this).css('opacity','1')
    })

})

$cancelBtn.on("click",function(){
    $(".editorMdoal").attr("style","display:none !important;")
    $(".editorMdoal").find(".editorTitle input").val("");
    $(".editorMdoal").find(".note-editable").text("");
    $(".updatePost_1j").css("display","none");
})



$exitBtn.on("click",function(){
    $(".editorMdoal").attr("style","display:none !important;")
    $(".editorMdoal").find(".editorTitle input").val("");
    $(".editorMdoal").find(".note-editable").text("");
    $(".updatePost_1j").css("display","none");
})


$registerBtn.on("click",function(){
    $(".editorMdoal").attr("style","display:none !important;")
    $(".editorMdoal").find(".editorTitle input").val("");
    $(".editorMdoal").find(".note-editable").text("");
    $(".updatePost_1j").css("display","none");
})



/*수정*/
const $postUpdateBtn = $(".loungeCardContentsComponents_loungeContentsMoreButtonBox__2jERo").find(".postUpdate_1j")

$postUpdateBtn.click(function () {
    $(".editorMdoal").attr("style","display:flex !important;");
    $(".updatePost_1j").css("display","block");
    $(".registerPost_1j").css("display","none");
})

$(".updatePost_1j").click(function(){
    $(".updatePost_1j").css("display","none");
    $(".editorMdoal").attr("style","display:none !important;")
    $(".editorMdoal").find(".editorTitle input").val("");
    $(".editorMdoal").find(".note-editable").text("");

})



const $replyUpdateBtn = $(".userInformationComponents_userReplySection__3ty7Q").find(".replyUpdate");
let replyText = '';
let replyCommentCheck = false;
$replyUpdateBtn.click(function () {
    if(!replyCommentCheck){
        var text = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children().eq(0).text();
        replyText = text;
        const $replaceTag =  $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children().eq(0);
        var tab = `<textarea maxlength="1000" placeholder="대댓글을 입력해주세요." style="resize: none; padding: 16px 24px; border: 1px solid rgb(223, 227, 228); border-radius: 6px; height: 63px !important;" oninput='this.style.height = "21px"; this.style.height = this.scrollHeight + "px"'>`+text+`</textarea>`
        tab += `<div class="replyComponent_replyButtonGroup__2i0ow" style="justify-content: flex-end;"><button class = "replyUpdateButton_1j" style="border: 1px solid rgb(206, 210, 212); padding: 8px 16px; border-radius: 6px;">확인</button><button class = "replyCancelButton_1j" style="border: 1px solid rgb(206, 210, 212); padding: 8px 16px; border-radius: 6px;">취소</button></div>`;
        if($replaceTag.prop(`tagName`)=='P'){
            $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children().eq(0).replaceWith(tab);
        }
        replyCommentCheck=true;
    }

})

$(".replyComponent_replyContent__3iS4J").on("click",".replyCancelButton_1j",function () {
   var text= $(this).parent().prev().val();
    $(this).parent().prev().replaceWith(`<p class="replyContent_content_1j">`+replyText+`</p>`);
    $(this).parent().remove();
    replyCommentCheck=false;
})



/*삭제 모달*/

const $postDeleteBtn = $(".loungeCardContentsComponents_loungeContentsMoreButtonBox__2jERo").find(".postDelete_1j");


$postDeleteBtn.on("click",function(){
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

$(".modalWrapOpen").find(".redBtn").click(function () {
    $(".modalWrapOpen").attr("style","display : none !important")
})

const $replyDeleteBtn = $(".userInformationComponents_userReplySection__3ty7Q").find(".replyDelete");
$replyDeleteBtn.click(function () {
    $(".modalWrapOpen").attr("style","display : block !important")
    $(".modalTit").text("댓글 삭제 확인");
    $(".commonModalContent p").text("해당 댓글을 삭제하시겠습니까?");
});