

$(".subscribeBtn").on("click", function () {
    if($(this).hasClass("active")){
        let name = $(this).parents(".projectPageWrap").prev().children(".projectPageHeaderContent").children(".projectTit").text();
        $(".modal2").children(".studyTit").text(name + "을");
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');

    }else{
        $(this).removeClass("active");
        $(".rightFavorite").addClass("active");
    }
})

$(".okBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
    $(".rightFavorite").addClass("active").css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-full.5a5209416a8859031c9099b5540c281a.svg)");
    $(".subscribeBtn").removeClass("active");

    $.ajax({
        url:"/study-follow/follow",
        type:"post",
        data:{myId: userSessionId,studyId:studyId},
        success: followActive

    })

});

function followActive(result) {
    if(result=='success'){
        window.location.reload()
    }else{
        alert("알수 없는 오류")
    }
}

$(".rightFavorite").on("click", function () {
    if(!$(this).hasClass("active")){
        $(".modal2").children(".studyTit").text(studyName + "을");
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');
    }else{
        $.ajax({
            url:"/study-follow/follow",
            type:"post",
            data:{myId: userSessionId,studyId:studyId},
            success: function(result){
                window.location.reload()
            }
        })
    }

})