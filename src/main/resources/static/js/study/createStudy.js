/*
 *
 */

console.log("스터디 모집글 작성 들어옴");
console.log($("#contents"));
console.log($("#contents").attr("placeholder"));

// 모달 종료
$(".closeBtnImg").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

$(".blueBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

// 임시저장 버튼 클릭
$(".saveBtn").on("click",function(){
    $(".modalWrapOpen").show();
    $(".modal1").css('display','inline-block');
});

// 작성완료 버튼 클릭
const $placeholder = $("#contents").attr("placeholder");
$(".confirmBtn").on("click",function(){
    if($(".studyTitleInput").val().length < 3){ // 제목 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');
        console.log($(".studyTitleInput").val().length);
    }else if($(".studyKeywordInput").val().length < 1){ // 키워드 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal3").css('display','inline-block');
    }else if($placeholder == $("#contents").val()){ // 내용 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal4").css('display','inline-block');
    }else if($("#contents").val().length < 1){ // 내용 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal4").css('display','inline-block');
    }else{  // 작성 완료
        $(".modalWrapOpen").show();
        $(".modal5").css('display','inline-block');
    }
});

// 온, 오프라인 설정에 따른 지역 미설정
var $onOffSelect = $(".onOffSelect");

$onOffSelect.on("change", function() {
    if($onOffSelect.val() == "01"){
        $('.onOffResult').attr('disabled',true);
    }else{
        $('.onOffResult').removeAttr('disabled');
    }
});


