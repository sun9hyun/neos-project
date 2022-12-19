/*
*
* */


// 모달 종료
$(".closeBtnImg").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

$(".blueBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

$(".whiteBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

$(".modal1 .blueBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
    location.href = "/store/store-purchase/" + $(".storeId").val();

});

$(".modal3 .blueBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
    location.href = "/store/store-delete" + "?storeId=" + $(".storeId").val();

});

$("a.detailBtn").on("click", function () {
    $(".modalWrapOpen").show();
    $(".modal3").css('display','inline-block');
})

$(".cashButton").on("click", function (e) {
    // e.preventDefault();
    // location.href = "/store/store-purchase/" + $(this).attr("href");

    console.log("결제하기 정보 시작");
    console.log($(".storePoint").val());
    console.log($(".userNeosPoint").val());

    let storePoint = $(".storePoint").val();
    let userPoint = $(".userNeosPoint").val();

    if(userPoint >= storePoint){
        $(".modalWrapOpen").show();
        $(".modal1").css('display','inline-block');
    }else{
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');
    }

})

// 작성완료 버튼 클릭
function submit() {
    console.log($(".storeStatus").val());
    console.log($(".cashCheck").val());
    console.log($(".textInput").val());
    console.log($(".storeContent").val());
    console.log($(".cashCheck").val().length);
    console.log($(".addFile").length);

    if($(".textInput").val().length < 3){ // 제목 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');
    }else if($(".addFile").length < 1){ // 자료 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal5").css('display','inline-block');
    }else if($(".storeContent").val().length < 1){ // 내용 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal3").css('display','inline-block');
    }else if($(".storeStatus").val() == "FREE"){  // 작성 완료
        $(".cashCheck").val("0");
        $(".modalWrapOpen").show();
        $(".modal4").css('display','inline-block');
    }else if($(".cashCheck").val().length < 4){ // 최소 포인트 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal1").css('display','inline-block');
    }else if($(".cashCheck").val().length >= 4){
        $(".modalWrapOpen").show();
        $(".modal4").css('display','inline-block');
    }
}
