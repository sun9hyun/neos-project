const $registerBtn = $(".showTextarea_1j");
const $cancelBtn = $(".update_1j");
let check = false;

$registerBtn.click(function () {
    if(check == false){
        $(this).closest(".content").next().show();
        // $(this).closest(".content").children().attr("style","border-bottom:none");
        $(this).closest(".content").next().next().children().attr("style","border-top:1px solid #E0E0E0;");
    }else{
        alert("등록 중인 답변을 완료해주세요.")
    }
    check = true;
})

$cancelBtn.click(function () {
    // $(this).closest(".apply_inquiry_data").prev().children().attr("style","border-bottom:1px solid #E0E0E0;");
    $(this).closest(".apply_inquiry_data").next().children().attr("style","border-top:none;")
    $(this).closest(".apply_inquiry_data").hide();
    check = false;
})

