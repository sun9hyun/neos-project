$(".chattingWrap2").on("click", function () {
    $(".chattingWrap2").css("display", "none");
    $("#chattingList").css("display", "block");
})

$(".foldChatBtn").on("click", function () {
    $("#chattingList").css("display", "none");
    $(".chattingWrap2").css("display", "block");
})

$(".moreBtn").on("click", function () {
    $(".chatExitModal").css("display", "block");
})

$(".closeBtn").on("click", function () {
    $(".chatExitModal").css("display", "none");
})

$(".whiteBtn").on("click", function () {
    $(".chatExitModal").css("display", "none");
})

$(".redBtn").on("click", function () {
    $(".chatExitModal").css("display", "none");
})



$("li.active").on("click", function () {
    $("li.active").removeClass("select");
    $(this).addClass("select");
})


$(".pinkBtn").on("click", function () {
    $(".request_chat").css("display", "block");
})

$(".closeBtn").on("click", function () {
    $(".request_chat").css("display", "none");
})

$(".whiteBtn").on("click", function () {
    $(".request_chat").css("display", "none");
})

$(".chatStart").on("click", function () {
    $(".request_chat").css("display", "none");
    $(".chattingWrap2").css("display", "none");
    $("#chattingList").css("display", "block");
})


$(".chat").on("click", function () {
    $(".request_chat").css("display", "block");
})

$(".letspler_Re .bottom .chatBtn").on("click", function () {
    $(".request_chat").css("display", "block");
})







