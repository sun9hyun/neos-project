const $postBtn = $("#questionPostBtn_1j");
$postBtn.on("click",function () {
    if(!$("#studyQuestionContent").val()){
        alert("빈 내용은 등록하실 수 없습니다.");
        return;
    }
    $("#questionPostForm").submit();
})

const $deleteBtn = $(".deleteBtn_1j1");
$deleteBtn.on("click",function () {
    const id = $(this).prev().find(".questionId").val();
    $("#questionIdForDelete").val(id);
    $(".modalWrapOpen").show();
    $("#deleteModal_1j").show();
})

$("#deleteBtn_11j").on("click",function () {
    const id = $(this).parent().prev().val();
    $.ajax({
        url:"/quest/"+id,
        type:"delete",
        success:function(result){
            if(result == 'delete success'){
                $("#"+id).remove();
                var num = parseInt($("#size_1j").text())-1;
                $("#size_1j").text(num);
            }
        }
    })
})
globalThis.content = undefined;
var check = false;
$(".updateBtn_1j1").on("click",function () {
    if(!check){
        globalThis.content = $(this).parent().prev().find("textarea").val();
        $(this).parent().prev().find("textarea").attr("disabled", false)
            .css("height", "127px");
        $(this).hide();
        $(this).next().show();
        $(this).closest(".btnWrap").find(".deleteBtn_1j1").hide();
        $(this).closest(".btnWrap").find(".deleteBtn_1j1").next().show();
        check=true
    }else{
        alert("한번에 하나의 글만 수정이 가능합니다.")
    }
})

$(".cancelBtn_1j1").on("click",function () {
    $(this).parent().prev().find("textarea").attr("disabled", true)
        .css("height", "62px");

    $(this).parent().prev().find("textarea").val(globalThis.content)
    $(this).hide();
    $(this).prev().show();
    $(this).closest(".btnWrap").find(".updateBtn_1j1").show();
    $(this).closest(".btnWrap").find(".updateBtn_1j1").next().hide();
    check=false;
})

$(".updatePostBtn_1j1").on("click",function () {
    const id = $(this).next().find(".questionId").val();
    const content = $(this).closest(".btnWrap").prev().find(".studyQuestionContent_1j").val()
    if(!content){
        alert("빈 내용은 등록하실 수 없습니다.");
        return;
    }
    if(content == globalThis.content){
        alert("변경 사항이 없습니다.")
        return;
    }
    $(this).parent().prev().find("textarea").attr("disabled", true)
        .css("height", "62px");
    $.ajax({
        url:"/quest/"+id,
        type:"post",
        data:{content:content},
        success:function (result) {
            alert("수정이 완료되었습니다.")
            $("#"+result.studyQuestionId).find("span.createdDate").html(result.createdDate);
            $("#"+result.studyQuestionId).find(".studyQuestionContent_1j").text(result.studyQuestionContent);
            check=false;
            globalThis.content=undefined;
            $("#"+result.studyQuestionId).find(".updatePostBtn_1j1").hide();
            $("#"+result.studyQuestionId).find(".cancelBtn_1j1").hide();
            $("#"+result.studyQuestionId).find(".updateBtn_1j1").show();
            $("#"+result.studyQuestionId).find(".deleteBtn_1j1").show();
        }
    })

})






//댓글 작성
$(".replyPost_1j").on("click",function(){
    const id = $(this).closest(".feedUploadedSection").attr("id");
    const content = $(this).parent().prev().val();
    if(!content){
        alert("빈 내용은 등록하실 수 없습니다.");
        return;
    }
    $.ajax({
        url:"/quest-reply/"+id,
        type:"post",
        data:{studyQuestionReplyContent:content,userId:userId},
        success:function(result){
            $("#"+id).find(".replyPostTextArea_1j").val("");
            show(id)
        }
    })
})

// 댓글 목록
$(".feedReply").on("click", function () {
    $(this).toggleClass('active');
    if($(this).hasClass('active')){
        $(this).text("댓글 열기");
        $(this).parents('.feedUploadWrap').children('.replyWrap').css("display", "none");
    }else{
        $(this).text("댓글 닫기");
        $(this).parents('.feedUploadWrap').children('.replyWrap').css("display", "flex");
        const id = $(this).closest(".feedUploadedSection").attr("id");
        show(id)
    }

})

function show(obj){
    var id = obj;
    $.ajax({
        url:"/quest-reply/"+id,
        type:"get",
        success:function(result){
            showList(result,id)
        }
    })
}

function showList(result,id){
    let text='';
    $(result).each((i,item)=>{
        text += `<div class="top reply`+item.studyQuestionReplyId+`">`
        text += `<div class="left"><a href="/neosUser/info/yes?userId=`+item.studyQuestionReplyWriter.userId+`" >`
        text += `<div class="profileThumb"><img loading="lazy" width="44px" src="`+item.studyQuestionReplyWriter.userFile+`" height="44px" alt="user_image"></div>`
        text += `</a></div>`
        text += `<div class="right">`
        text += `<h4 class="profileName">`+item.studyQuestionReplyWriter.userNickName
        text += `<div class="hoverView">`
        text += `<ul>`
        text += `<li><a href="/neosUser/info/yes?userId=`+item.studyQuestionReplyWriter.userId+`">프로필 상세</a></li>`
        text += `<li class="questionChat"><a>1:1 대화</a></li>`
        text += `</ul>`
        text += `</div>`
        text += `</h4>`
        text += `<p class="dateTxt">`+item.createdDate+`</p></div>`
        text += `</div>`
        text += `<div class="bottom reply`+item.studyQuestionReplyId+`">`
        text += `<div class="left"></div>`
        text += `<div class="txtWrap"><textarea disabled="" maxlength="500" style="font-size: 0.875rem; height: 42px !important;">`+item.studyQuestionReplyContent+`</textarea>`
        text += `</div>`
        if(userId==item.studyQuestionReplyWriter.userId){
            text += `<div class="btnWrap">`
            text += `<button class="replyBtn txtBtn updateTry">수정</button>`
            text += `<button class="replyBtn txtBtn updatePost">등록</button>`
            text += `<div class="divider"><input type="hidden" id="replyId" value="`+item.studyQuestionReplyId+`"></div>`
            text += `<button class="replyBtn txtBtn cancelTry">취소</button>`
            text += `<button class="replyBtn txtBtn deleteTry">삭제</button>`
            text += `</div>`
        }
        text += `</div>`
    })
    $("#"+id).find(".replyWrapContent").html(text);
    $("#"+id).find(".replayCount").text(result.length);
}

//    수정

//    삭제
globalThis.reply = undefined;
var replyCheck =false;
$(".replyWrapContent").on("click",".cancelTry",function () {
    $(this).parent().prev().find("textarea").attr("disabled", true)
        .css("height", "42px");
    $(this).hide();
    $(this).next().show();
    $(this).prev().prev().hide();
    $(this).prev().prev().prev().show();
    $(this).parent().prev().find("textarea").val( globalThis.reply);
    replyCheck=false;
})

globalThis.replyContent = undefined;
$(".replyWrapContent").on("click",".updateTry",function () {
    globalThis.replyContent= $(this).parent().prev().find("textarea").val();
    if(!replyCheck){
        globalThis.reply = $(this).parent().prev().find("textarea").val();
        $(this).parent().prev().find("textarea").attr("disabled", false)
            .css("height", "62px");
        $(this).hide();
        $(this).next().show();
        $(this).next().next().next().show();
        $(this).next().next().next().next().hide();
        replyCheck=true
    }else{
        alert("한번에 하나의 댓글만 수정 할 수 있습니다.")
    }

})



$(".replyWrapContent").on("click",".deleteTry",function () {
    var id = $(this).prev().prev().find("#replyId").val();
    $("#questionIdForDelete2").val(id);
    $(".modalWrapOpen").show();
    $("#deleteModal_2j").show();
})

//삭제
$("#deleteBtn_21j").on("click",function () {
    var id = $(this).parent().prev().val();
    $.ajax({
        url:"/quest-reply/"+id,
        type:"delete",
        success:showre
    })
})

function showre(result){
    show(result);
}
//수정
let replyUpdateCheck = false;

$(".replyWrapContent").on("click",".updatePost",function () {
    if(!replyUpdateCheck){
        replyUpdateCheck = true;
        var id = $(this).next().find("#replyId").val();
        var content = $(this).parent().prev().find("textarea").val();

        if(!content){
            alert("빈 내용은 등록하실 수 없습니다.");
            return;
        }
        if(globalThis.replyContent == content){
            alert("같은 내용으로 수정하실 수 없습니다.");
            return;
        }
        $.ajax({
            url:"/quest-reply/"+id,
            type:"put",
            data:{studyQuestionReplyContent:content},
            success:function(result){
                globalThis.replyContent=undefined;
                show(result);
                replyUpdateCheck = false;
                replyCheck = false;

            }

        })
    }else{
        alert("한번에 하나의 댓글만 수정 가능합니다.")
    }


})


