
$(".milestonePost").on("click",function () {
    let $titleTag = $("#milestoneTitle_1j");
    let $contentTag = $("#milestoneContent_1j");

    let title = $titleTag.val();
    let content = $contentTag.val();
    if(!title){
        $(".modalWrapOpen").show();
        $(".modal4").show();
        return;
    }
    if(!content){
        $(".modalWrapOpen").show();
        $(".modal4").show();
        return;
    }

    $.ajax({
        url:"/mile-stone/"+studyId,
        type:"post",
        data:{studyMileStoneTitle:title, studyMileStoneContent:content, userId: userId},
        success: function (result) {
            if(result == 'success'){
                show();
                $titleTag.val('');
                $contentTag.val('');
            }
        }
    })

})
show();
function show(){
    $.ajax({
        url:"/mile-stone/"+studyId,
        type:"get",
        success:showList
    })
}

function showList(result) {
    let text = '';
    $(result).each((i,item)=>{
        text += `<div class="nowMilestoneWrap mileStoneContentsWrap" id="`+item.studyMileStoneId+`">`
        text += `<div class="top"><p class="date">`+item.createDate
        text += `</p>`
        if(userId == item.mileStoneWriter.userId){
            text += `<div class="btnWrap">`
            text += `<div class="statusCircle"><span>진행</span></div>`
            text += `<button class="firstBtn mileBtn updateTry">수정</button>`
            text += `<button class="firstBtn mileBtn updateMileStone">등록</button>`
            text += `<input type="hidden" class="mileStoneId_1j" id="`+item.studyMileStoneId+`">`
            text += `<button class="mileBtn completeBtn_1j">완료</button>`
            text += `<button class="mileBtn cancelTry_1j">취소</button>`
            text += `</div>`
        }
        text += `</div>`
        text += `<input type="text" class="mileStoneTit" value="`+item.studyMileStoneTitle+`" disabled="">`
        text += `<div class="mileStoneTxt"><textarea rows="1" disabled="">`+item.studyMileStoneContent+`</textarea>`
        text += `</div>`
        text += `</div>`
    })
    $(".nowWrapper_1j").html(text);
}

finishListGet();
function finishListGet(){
    $.ajax({
        url:"/mile-stone/finish/"+studyId,
        type:"get",
        success:showFinish
    })
}

function showFinish(result) {
    let high = '';
    $(result).each((i,item)=>{
        high += `<div class="completedMilestoneWrap mileStoneContentsWrap">`
        high += `<div class="top">`
        high += `<p class="date">`+item.createDate
        high += `</p>`
        high += `</div>`
        high += `<input type="text" class="mileStoneTit" disabled="" value="`+item.studyMileStoneTitle+`">`
        high += `<div class="mileStoneTxt"><textarea rows="1" disabled="">`+item.studyMileStoneContent+`</textarea>`
        high += `</div>`
        high += `</div>`
    })

    $(".completeWrapper_1j").html(high);
}


$(".nowWrapper_1j").on("click",".completeBtn_1j",function () {
    var mileStoneId = $(this).parent().find(".mileStoneId_1j").attr("id");
    var title = $(this).closest(".nowMilestoneWrap").find(".mileStoneTit").val();
    $(".mileStoneTitle").text(title);
    $("#mileStoneId").val(mileStoneId);
    $(".modalWrapOpen").show();
    $(".modal5").show();


})

$(".mileStoneComplete").on("click",function () {
    var mileStoneId = $(this).prev().val();

    $.ajax({
        url:"/mile-stone/finish/"+mileStoneId,
        type:"post",
        success:function (result) {
            if(result == 'success'){
                show();
                finishListGet();
            }
        }

    })
})


let titleMileStone;
let contentMileStone;
let mileStoneCheck = false;


$(".nowWrapper_1j").on("click",".updateTry",function () {


    if(!mileStoneCheck){
        mileStoneCheck=true;
        const $titleTag = $(this).closest(".nowMilestoneWrap").find(".mileStoneTit");
        const $contentTag = $(this).closest(".nowMilestoneWrap").find(".mileStoneTxt textarea");
        titleMileStone = $titleTag.val();
        contentMileStone = $contentTag.val();


        $titleTag.attr("disabled",false).css("height","32px");
        $contentTag.attr("disabled",false).css("height","16px");


        $(this).parent().find(".updateTry").hide()
        $(this).parent().find(".updateMileStone").show()
        $(this).parent().find(".completeBtn_1j").hide()
        $(this).parent().find(".cancelTry_1j").show()
    }else{
        alert("한번에 하나의 목표만 수정가능 합니다.")
    }

})


$(".nowWrapper_1j").on("click",".cancelTry_1j",function () {
    const $titleTag = $(this).closest(".nowMilestoneWrap").find(".mileStoneTit");
    const $contentTag = $(this).closest(".nowMilestoneWrap").find(".mileStoneTxt textarea");

    $titleTag.val(titleMileStone)
    $contentTag.val(contentMileStone)

    $titleTag.attr("disabled",true).css("height","auto");
    $contentTag.attr("disabled",true).css("height","auto");


    $(this).parent().find(".updateTry").show()
    $(this).parent().find(".updateMileStone").hide()
    $(this).parent().find(".completeBtn_1j").show()
    $(this).parent().find(".cancelTry_1j").hide()
    mileStoneCheck=false;

})


$(".nowWrapper_1j").on("click",".updateMileStone",function () {
    const $titleTag = $(this).closest(".nowMilestoneWrap").find(".mileStoneTit");
    const $contentTag = $(this).closest(".nowMilestoneWrap").find(".mileStoneTxt textarea");


    var id = $(this).closest(".mileStoneContentsWrap").attr("id");
    var title = $titleTag.val();
    var content = $contentTag.val();

    $.ajax({
        url:"/mile-stone/"+id,
        type:"put",
        data:{studyMileStoneTitle: title,studyMileStoneContent:content},
        success:function (result) {
            if(result=='success'){
                show();
                mileStoneCheck=false;
            }
        }
    })


})





$(".workPost").on("click",function () {
    if(!$(`input[name="studyWorkContent"]`).val()){
        $(".modalWrapOpen").show();
        $(".modal6").show();
        return;
    }

    if($(".choiceMember option:selected").val() =='none'){
        $(".modalWrapOpen").show();
        $(".modal6").show();
        return;
    }

    if(   $(".choiceMember option:selected").val()=='0'&&$(".meeting option:selected").val()=='00'){
        $(".modalWrapOpen").show();
        $(".modal6").show();
        return;
    }

    if(!$("#date_input1").val()){
        $(".modalWrapOpen").show();
        $(".modal6").show();
        return;
    }



    if($('.choiceMember option:selected').val() == '0'){
        $('.choiceMember').attr('disabled',true);

    }

    if($('.choiceMember option:selected').val() == '0' && $('.meeting option:selected').val()=='OFFLINE'){
        $("#locationTag").val($meetingSearch.val());
    }

    $("#workPostForm").submit()

})

// 할일 완료 처리
$(".statusCircle").on("click",function () {
    if($(this).children().text() == "완료") {
        let name = $(this).parents(".taskContentsWrap").children(".tastContent").children(".taskTxt").text().split("]")[1];
        $(".modal1").children(".modalContent").children(".modalName").text("[" + name + " ]");

        let workId = $(this).closest(".memberTaskWrap").attr("id");
        $("#workId_1jsd").val(workId);

        $(".modalWrapOpen").show();
        $(".modal1").css('display', 'inline-block');
    }
})


$(".workCompleteBtn_1j").on("click",function () {
    $("#completeForm_1j").submit();
})