show();
function show(){
    $.ajax({
        url:"/study-feed/show-list/"+studyId,
        type:"get",
        success:showList
    })
}
function showList(result){
    let text= '';
    $(result).each((i,item)=>{
            text += `<div class="section feedUploadedSection" id="`+item.studyFeedId+`">`
            text+= `<input type="hidden" name="studyFeedId" class="studyFeedId" value="`+item.studyFeedId+`">`
            text += `<div class="feedUploadWrap">`
            text += `<div class="feedContentsWrap">`
            text += `<div class="top">`
            text += `<div class="thumb"><a href="/neosUser/info/yes?userId=`+item.studyFeedWriter.userId+`"><img loading="lazy" width="44px" height="44px" src="`+item.studyFeedWriter.userFile+`" alt="user_image"></a></div>`
            text += `<div class="name">`
            text += `<h2 class="profileName">`+item.studyFeedWriter.userNickName
            text += `<div class="hoverView">`
            text += `<ul>`
            text += `<li><a href="/neosUser/info/yes?userId=`+item.studyFeedWriter.userId+`" >프로필 상세</a></li>`
            text += `<li class="chatList"><a>1:1 대화</a></li>`
            text += `</ul>`
            text += `</div>`
            text += `</h2>`
            text += `<span>`+item.createdDate+`</span>`
            text += `</div>`
            text += `</div>`
            text += `<div class="feedContents"><textarea disabled="" maxlength="1000" style="font-size: 0.875rem; height: 62px !important;">`+item.studyFeedContent+`</textarea>`
            text += `</div>`
            if(item.studyFeedWriter.userId == userId){
                text += `<div class="btnWrap">`
                text += `<button class="feedBtn updateTry_1j" >수정</button>`
                text += `<button class="feedBtn update_1j" >등록</button>`
                text += `<div class="divider"></div>`
                text += `<button class="feedBtn delete_1j" >삭제</button>`
                text += `<button class="feedBtn cancelUpdate_1j" >취소</button>`
                text += `</div>`
            }
            text += `<div class="bottom"><a class="feedReply active">댓글 열기</a> <span class="replayCount">`+item.replyLength+`</span></div>`
            text += `</div>`
            text += `<div class="replyWrap">`
            text += `<div class="replyWrapper">`
            text += `<div class="replyWrapContent">`
            text += `<div class="top">`
            text += `<div class="left">`
            text += `<div class="profileThumb"><a href="/people/undefined" ><img loading="lazy" width="44px" height="44px" src="https://phinf.pstatic.net/contact/20220616_30/1655372073682zvrW8_GIF/%B0%B6%B7%B0%BD%C3.gif" alt="user_image"></a>`
            text += `</div> </div>`
            text += `<div class="right">`
            text += `<h4 class="profileName">ROSA`
            text += `<div class="hoverView">`
            text += `<ul>`
            text += `<li><a href="/people/ROSA" >프로필 상세</a>`
            text += `</li>`
            text += `<li class="chatList"><a>1:1 대화</a></li>`
            text += `</ul>`
            text += `</div>`
            text += `</h4>`
            text += `<p class="dateTxt">22.11.13 22:00</p>`
            text += `</div>`
            text += `</div>`
            text += `<div class="bottom">`
            text += `<div class="left"></div>`
            text += `<div class="txtWrap"><textarea disabled="" maxlength="500" style="font-size: 0.875rem; height: 42px !important;">파일은 어떻게 올리나요?</textarea>`
            text += `</div>`
            text += `</div>`
            text += `</div>`
            text += `<div class="replyWrapContent">`
            text += `<div class="top">`
            text += `<div class="left">`
            text += `<div class="profileThumb"><a href="/people/undefined" ><img loading="lazy" width="44px" height="44px" src="https://letspl.me/assets/images/prof-no-img.png" alt="user_image"></a>`
            text += `</div>`
            text += `</div>`
            text += `<div class="right">`
            text += `<h4 class="profileName">추추추`
            text += `<div class="hoverView">`
            text += `<ul>`
            text += `<li><a href="/people/추추추" >프로필 상세</a>`
            text += `</li>`
            text += `<li class="chatList"><a>1:1 대화</a></li>`
            text += `</ul>`
            text += `</div>`
            text += `</h4>`
            text += `<p class="dateTxt">22.11.14 09:01</p>`
            text += `</div>`
            text += `</div>`
            text += `<div class="bottom">`
            text += `<div class="left"></div>`
            text += `<div class="txtWrap">`
            text += `<textarea maxlength="500" style="font-size: 0.875rem; height: 42px !important;" disabled="">마우스를 클릭하세요 오른쪽을 한번 누르면 클릭</textarea>`
            text += `</div>`
            text += `<div class="btnWrap">`
            text += `<button class="replyBtn txtBtn">수정</button>`
            text += `<div class="divider"></div>`
            text += `<button class="replyBtn txtBtn">삭제</button>`
            text += `</div>`
            text += `</div>`
            text += `</div>`
            text += `</div>`
            text += `<div class="replayInput">`
            text += `<div class="profileThumb"><img loading="lazy" width="44px" height="44px" src="`+nowWriter+`" alt="user_image"></div>`
            text += `<div class="txtInput"><textarea type="text" placeholder="댓글을 작성해주세요." rows="4" maxlength="500"></textarea>`
            text += `<div class="btnWrap">`
            text += `<button class="whiteBtn replyPost">등록</button>`
            text += `<button class="whiteBtn replyPostCancel" style="margin-left: 8px">취소</button>`
            text += `</div>`
            text += `</div>`
            text += `</div>`
            text += `</div>`
            text += `</div>`
            text += `</div>`
        }
    )
    $("#feedWrapper").html(text);
    //찾는용도입니다.
}


//수정
//updateTry_1j
let $replyText;
let updateCheck = false;
$("#_next").on("click", ".updateTry_1j",function () {
    if(!updateCheck){
        var $textareaTag = $(this).parent().prev().find("textarea");
        $replyText = $textareaTag.text();
        $textareaTag.attr("disabled",false).css("height","127px");
        $(this).hide();
        $(this).parent().find(".update_1j").show();
        $(this).parent().find(".delete_1j").hide();
        $(this).parent().find(".cancelUpdate_1j").show();
        updateCheck=true;
    }else{
        alert("한번에 하나의 댓글만 수정하실 수 있습니다.")
    }

})

$("#_next").on("click", ".cancelUpdate_1j",function () {
    var $textareaTag = $(this).parent().prev().find("textarea");
    $textareaTag.attr("disabled",true).css("height","62px");
    $textareaTag.val($replyText);

    $(this).parent().find(".updateTry_1j").show();
    $(this).parent().find(".update_1j").hide();
    $(this).parent().find(".delete_1j").show();
    $(this).hide();
    updateCheck=false;

})


$("#_next").on("click", ".update_1j",function () {
    var $textareaTag = $(this).parent().prev().find("textarea");
    var content =$textareaTag.val();
    var thisId = $(this).closest(".feedUploadedSection").find(".studyFeedId").val();
    if(!content){
        alert("빈 내용은 등록하실 수 없습니다.")
        return;
    }
    if(content==$replyText){
        alert("같은 내용으로 수정하실 수 없습니다.")
        return;
    }

    $.ajax({
        url:"/study-feed/"+thisId,
        type:"post",
        data:{studyFeedContent : content},
        success: show
    })
    updateCheck=false;
})

//삭제
$("#_next").on("click", ".delete_1j",function () {
    var thisId = $(this).closest(".feedUploadedSection").find(".studyFeedId").val();
    $.ajax({
        url:"/study-feed/"+thisId,
        type:"delete",
        success: show
    })
})






$("#_next").on("click",".feedReply",function () {
    var feedId = $(this).closest(".feedUploadedSection").attr("id");
    $(this).toggleClass('active');
    replyShow(feedId);
    if($(this).hasClass('active')){
        $(this).text("댓글 열기");
        $(this).parents('.feedUploadWrap').children('.replyWrap').css("display", "none");

    }else{
        $(this).text("댓글 닫기");
        $(this).parents('.feedUploadWrap').children('.replyWrap').css("display", "flex");
    }

})

//댓글 작성 취소
$("#_next").on("click",".replyPostCancel",function () {
    $(this).parent().prev().val("");
})

//댓글 작성
$("#_next").on("click",".replyPost",function () {
    var textareaTag = $(this).parent().prev();
    var content = $(this).parent().prev().val();
    var feedId = $(this).closest(".feedUploadedSection").find(".studyFeedId").val();
    var count = parseInt($("#"+feedId).find(".replayCount").text());
    if(!content){
        alert("빈 문자열은 등록하실 수 없습니다.")
        return;
    }
    $.ajax({
        url:"/feed-reply/"+feedId,
        type:"post",
        data:{studyFeedReplyContent : content, userId : userId},
        success:function (result) {
            replyShow(feedId);
            $("#"+feedId).find(".replayCount").text(count+1);
            textareaTag.val("");
        }
    })
})

function replyShow(obj){
    var id = obj;

    $.ajax({
        url:"/feed-reply/"+id,
        type:"get",
        success:function (result) {
            let text = '';

            $(result).each((i,item)=>{
                text += `<div class="replyWrapContent" id="`+item.studyFeedReplyId+`">`
                text += `<div class="top">`
                text += `<div class="left">`
                text += `<div class="profileThumb"><a href="/neosUser/info/yes?userId=`+item.studyFeedReplyWriter.userId+`" ><img loading="lazy" width="44px" height="44px" src="`+item.studyFeedReplyWriter.userFile+`" alt="user_image"></a>`
                text += `</div>`
                text += `</div>`
                text += `<div class="right">`
                text += `<h4 class="profileName">`+item.studyFeedReplyWriter.userNickName+`<div class="hoverView">`
                text += `<ul>`
                text += `<li><a href="/neosUser/info/yes?userId=`+item.studyFeedReplyWriter.userId+`" >프로필 상세</a></li>`
                text += `<li class="chatList"><a>1:1 대화</a></li>`
                text += `</ul>`
                text += `</div>`
                text += `</h4>`
                text += `<p class="dateTxt">`+item.createdDate+`</p>`
                text += `</div>`
                text += `</div>`
                text += `<div class="bottom">`
                text += `<div class="left"></div>`
                text += `<div class="txtWrap"><textarea maxlength="500" style="font-size: 0.875rem; height: 42px !important;" disabled="">`+item.studyFeedReplyContent+`</textarea></div>`
                if(userId == item.studyFeedReplyWriter.userId){
                    text += `<div class="btnWrap"><button class="replyBtn updateReplyTryBtn">수정</button>`
                    text += `<button class="replyBtn updateReplyBtn">등록</button>`
                    text += `<div class="divider"></div><button class="replyBtn cancelReplyBtn">취소</button><button class="replyBtn deleteReplyTryBtn">삭제</button>`
                }
                text += `</div>`
                text += `</div>`
                text += `</div>`
            })
            $("#"+id).find(".replyWrapper").html(text);


        }
    })
}


//댓글 삭제
$("#_next").on("click",".deleteReplyTryBtn",function () {
    var feedId = $(this).closest(".feedUploadedSection").attr("id");
    var thisId = $(this).closest(".replyWrapContent").attr("id");


    $(".modalWrapOpen").show();
    $(".modal13").show();
    $("#modalFeedId").val(feedId);
    $("#modalReplyId").val(thisId);

})

$("#deleteBtn_21j").on("click",function () {

    var feedId = $("#modalFeedId").val();
    var thisId = $("#modalReplyId").val();
    var count = parseInt($("#"+feedId).find(".replayCount").text());
    $.ajax({
        url:"/feed-reply/"+thisId,
        type:"delete",
        success:function(result){
            if(result=='success'){
                replyShow(feedId);
                $("#"+feedId).find(".replayCount").text(count-1);
                $(".modalWrapOpen").hide();
                $(".modal13").hide();
            }
        }
    })
})


let $feedReply ;
let feedReplyCheck = false;
$("#_next").on("click",".updateReplyTryBtn",function () {

    if(!feedReplyCheck) {
        feedReplyCheck=true;
        var $textareaTags = $(this).parent().prev().find("textarea");
        $feedReply = $textareaTags.val();
        $textareaTags.attr("disabled", false).css("height", "62px");

        $(this).parent().find(".updateReplyBtn").show();
        $(this).parent().find(".cancelReplyBtn").show();
        $(this).parent().find(".deleteReplyTryBtn").hide();
        $(this).hide();
    }else{
        alert("한번에 하나의 댓글만 수정 가능합니다.")
    }
})

$("#_next").on("click",".cancelReplyBtn",function () {

    var $textareaTags = $(this).parent().prev().find("textarea");
    $textareaTags.attr("disabled",true).css("height","42px");
    $textareaTags.val($feedReply)

    $(this).parent().find(".updateReplyTryBtn").show();
    $(this).parent().find(".updateReplyBtn").hide();
    $(this).parent().find(".deleteReplyTryBtn").show();
    $(this).hide();
    feedReplyCheck=false;
})

$("#_next").on("click",".updateReplyBtn",function () {

    var $textareaTags = $(this).parent().prev().find("textarea");
    var content = $textareaTags.val();
    var feedId = $(this).closest(".feedUploadedSection").attr("id");
    var thisId = $(this).closest(".replyWrapContent").attr("id");

    $.ajax({
        url:"/feed-reply/"+thisId,
        type:"put",
        data:{studyFeedReplyContent:content},
        success:function (result) {
            if(result=='success'){
                replyShow(feedId);
                feedReplyCheck=false;
            }
        }

    })


})