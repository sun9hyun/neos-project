
function saveChat(chattingRoom) {
    console.log(chattingRoom);
    $.ajax({
        url: "/chat/saveOk/" + chattingRoom.receiverRoomId + "/" + chattingRoom.myRoomId,
        type: "post",
        data: JSON.stringify(chattingRoom),
        contentType: "application/json; charset=utf-8",
        success: function(xhr,status) {
        },
        error: function (xhr, status, err) {
        }
    });
}

$(".chatStart").on("click", function () {
    console.log("클릭됨")
    let sessionId = $(".sessionLoginUser").val();//내 아이디(세션 아이디)
    let receiver = $(this).prev(".receiverId").attr("href");//받는 사람 아이디
    console.log(sessionId);
    console.log(receiver);
    saveChat({
        myRoomId:sessionId,
        receiverRoomId:receiver,
    })
});
