
function saveChat(chattingRoom) {
    console.log(chattingRoom);
    $.ajax({
        url: "/chat/saveOk/" + chattingRoom.receiverRoomId + "/" + chattingRoom.myRoomId,
        type: "get",
        // data: chattingRoom,
        // contentType: "application/json; charset=utf-8",
        success: function(xhr,status) {
            console.log("성공");
        },
        error: function (xhr, status, err) {
        }
    });
}

$(".chatStart").on("click", function () {
    console.log("클릭됨")
    let sessionId = $(".sessionLoginUser").val();//내 아이디(세션 아이디)
    let receiver = $(".letspler_Re").find(".chattingDelete").val();//받는 사람 아이디
    $(".letspler_Re").find(".chattingDelete").removeClass("chattingDelete");
    location.reload();
    console.log(sessionId);
    console.log(receiver);
    saveChat({
        myRoomId:sessionId,
        receiverRoomId:receiver,
    })
});
