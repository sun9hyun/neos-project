$(".chattingWrap2").on("click", function () {
    $(".chattingWrap2").css("display", "none");
    $("#chattingList").css("display", "block");
})

$(".foldChatBtn").on("click", function () {
    $("#chattingList").css("display", "none");
    $(".chattingWrap2").css("display", "block");
})
