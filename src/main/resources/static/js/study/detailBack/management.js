




$("#studyO2o").on("change",function () {
    if($('#studyO2o > option:selected').val() == 'online') {
        $("#studyCity").val('all').prop("selected", true);
        $("#studyCity").attr('disabled',true);
        $("#studyCity").append(`<input type="hidden" name="studyCity" value="no" id="studyCity_1j">`)
    }else{
        $("#studyCity").val('all').prop("selected", true);
        $("#studyCity").attr('disabled',false);
        $("#studyCity_1j").remove();

    }
})




$(".studyDeleteBtn").on("click",function () {
    $("#DeleteForm").submit();
})

$(".postUpdate").on("click",function () {
    $("#updateForm").submit();
})

$(".studySupportCountModify").on("click",function () {
    $("#supportForm_1j").submit();
})


$(".rejection").on("click",function () {
    var supportId= $(this).closest(".idHoverMenu").find(".supportId_1jasd").val();
    $(".modalWrapOpen").show();
    $(".modal8").show();
    $("#rejectUserNumber").val(supportId);
})

$(".rejectBtn_1j").on("click",function () {
    $("#rejectForm").submit();
})

$(".memberJoinBtn").on("click",function () {
    var supportId= $(this).closest(".idHoverMenu").find(".supportId_1jasd").val();
    var userId= $(this).closest(".idHoverMenu").find(".userId_1jasd").val();
    $(".modalWrapOpen").show();
    $(".modal50").show();
    $("#okUserNumber").val(supportId);
    $("#okUserId").val(userId);
})


$(".okBtn_1j").on("click",function () {
    $("#okForm").submit()
})

$(".releaseBtn").on("click",function () {
    var studyMemberId = $(this).closest(".idHoverMenu").find(".memberId_1jasd").val();
    $("#studyMemberId").val(studyMemberId);
})

$(".releaseOk").on("click",function () {
    $("#releaseForm").submit();
})
