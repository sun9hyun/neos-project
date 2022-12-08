
let receiverRoomId;
$("li.active").on("click", function () {
    receiverRoomId = $(this).attr("href");
    show();
});


show({
    receiverRoom: receiverRoomId
}, getList(contents));


function show(callback, error) {
    console.log("show");
    alert("show")
    alert(receiverRoomId)
    $.ajax({
        url: "/chat/chatContent/" + receiverRoomId,
        type: "get",
        // data: {receiverId: receiverId},
        async: false,
            success: function (contents) {
            alert("sc")
            if (callback) {
                callback(contents);
            }
        },
        error: function (xhr, status, err) {
            alert(error)
            if (error) {
                error(err);
            }
        }
    });
};

function getList(contents) {
    console.log("getList");
    alert("getList")
    let text = "";
    contents.forEach(content => {
        alert(contents)
        text += "<div class='thumb'>";
        text += "<a href='/people/엔잡쏘' target='_blank'>"
        text += "<img src='https://letspl.s3.ap-northeast-2.amazonaws.com/user/4535/images/%EB%B3%B4%EA%B1%B0%EC%8A%A4.png' alt='chat_image'>";
        text += "</a>"
        text += "</div>"
        text += "<div class='userIdChatTxt'>"
        text += "<span class='userId'> + content.receiver + </span>";
        text += "<div class='chatTxt'>";
        text += "<span class='chatTxtContents'>";
        text += "<a style='color: rgb(51, 51, 51);'> + content.chattingContent + </a>";
        text += "</span>";
        text += "<div class='timeWrap'>";
        text += "<span class='time'> + content.createdDate +</span>";
        text += "</div>";
        text += "</div>";

    });

    $("div.opponent").html(text);
}



