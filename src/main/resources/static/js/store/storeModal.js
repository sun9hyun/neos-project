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

    if(parseInt(userPoint) >= parseInt(storePoint)){
        console.log("구매 가능");
        console.log(storePoint);
        console.log(userPoint);
        $(".modalWrapOpen").show();
        $(".modal1").css('display','inline-block');
    }else if(parseInt(userPoint) < parseInt(storePoint)){
        console.log("구매 불가");
        console.log($(".storePoint").val());
        console.log($(".userNeosPoint").val());
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');
    }

})

