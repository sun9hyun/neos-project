/*
*------------ 채팅방 내용 확인 ajax--------------
* */

let receiverRoomId;
let nickName;
let profile;
let roomId;
let msg;

$("li.leftChattingList").on("click", function () {
    receiverRoomId =$(this).attr("href"); // 상대방 유저 아이디
    nickName = $(this).find(".userId").text(); // 닉네임
    profile = $(this).find(".userProfile").attr("src") // 유저 프로필
    $(".chattingRoomName").text(nickName); // 해당 채팅방 닉네임 변경
    $(".profileImages").attr("src", profile); // 해당 채팅방 유저 프로필 변경
    connect();

    $.ajax({
        url: "/chat/chatContent/" + receiverRoomId,
        type: "get",
        async: false,
        success: function (contents) {
            if (contents != null) {
                getChattingContentList(contents);

            }
        },
        error: function (xhr, status, err) {
        }


    });

    function getChattingContentList(contents) {
        let text = "";

        contents.forEach(content => {
                text += " <div class=\"opponent\">";
                text += " <div class=\"thumb\">";
                text += "<a href=\"/people/dddog\" target=\"_blank\">"
                text += "<img src='" + content.receiver.userFile + "' alt='chat_image'>";
                text += "</a>"
                text += "</div>"
                text += "<div class='userIdChatTxt'>"
                text += "<span class='userId'>" + content.receiver.userNickName + "</span>";
                text += "<div class='chatTxt'>";
                text += "<span class='chatTxtContents'>";
                text += "<a style='color: rgb(51, 51, 51);'>" + content.chattingContent + "</a>";
                text += "</span>";
                text += "<div class='timeWrap'>";
                text += "<span class='time'>" + contentService.getReplyDates(content.createdDate) + "</span>";
                text += "</div>";
                text += "</div>";
                text += "</div>";
                text += "</div>";

        });
        $("div.chattingRoomWrap").html(text);
    }

});


/*
* -----------채팅 내용 저장하기----------------
* */

function add(chattingContent) {
    $.ajax({
        url: "/chat/chattingOk/",
        type: "post",
        data: JSON.stringify(chattingContent),
        contentType: "application/json; charset=utf-8",
        success: function(xhr,status) {
                send();
        },
        error: function (xhr, status, err) {
        }
    });
}

$(".buttonComponents_circle__2iQ3w").on("click", function () {
    let sessionId = $(".sessionLoginUser").val();//내 아이디(세션 아이디)
    msg = document.getElementById("message").value; //메세지
    roomId = $(this).closest('.chattingRoom').prev().find('.select').find('.chattingId').val();
    add({
        myId:sessionId,
        receiverId:receiverRoomId,
        chattingId:roomId,
        chattingContent:msg
    })


});


/*
* -------------------------채팅 목록 뿌려주기------------------------------
* */

function show() {
    let receiverRoomId =$(this).attr("href"); // 상대방 유저 아이디
    $.ajax({
        url: "/chat/chatContent/" + receiverRoomId,
        type: "get",
        async: false,
        success: function (contents) {
            if (contents != null) {
                list(contents);
            }
        },
        error: function (xhr, status, err) {
        }
    });

//채팅방 목록 따로 빼놓은 것
    function list(contents) {
        let text = "";
        /* 채팅방 내용 */
        contents.forEach(content => {
            text += " <div class=\"opponent\">";
            text += " <div class=\"thumb\">";
            text += "<a href=\"/people/dddog\" target=\"_blank\">"
            text += "<img src='" + content.receiver.userFile + "' alt='chat_image'>";
            text += "</a>"
            text += "</div>"
            text += "<div class='userIdChatTxt'>"
            text += "<span class='userId'>" + content.receiver.userNickName + "</span>";
            text += "<div class='chatTxt'>";
            text += "<span class='chatTxtContents'>";
            text += "<a style='color: rgb(51, 51, 51);'>" + content.chattingContent + "</a>";
            text += "</span>";
            text += "<div class='timeWrap'>";
            text += "<span class='time'>" + contentService.getReplyDates(content.createdDate) + "</span>";
            text += "</div>";
            text += "</div>";
            text += "</div>";
            text += "</div>";
        })
        $("div.chattingRoomWrap").html(text);

    }
}


/*
* ----------채팅방 나가기----------------
* */

// $("#exit").on("click", function () {
//     $.ajax({
//         url: "/chat/chattingDelete",
//         type: "delete",
//         data: JSON.stringify({receiverId :  (this).closest('.chattingRoom').prev().find('.select').find('.chattingId').val()}),
//         contentType: "application/json; charset=utf-8",
//         success: function () {
//             alert(receiverId)
//             show()
//         },
//         error: function (xhr, status, err) {
//         }
//     })
// });

// $("#morAndFoldBtnWrap").on("click", function () {
//
//     $.ajax({
//         url: "/chat/chattingDelete",
//         type: "delete",
//         data: JSON.stringify({receiverId :  (this).closest('.chattingRoom').prev().find('.select').find('.chattingId').val()}),
//         contentType: "application/json; charset=utf-8",
//         success: function () {
//             alert(receiverId)
//             show()
//         },
//         error: function (xhr, status, err) {
//         }
//     })
// });




/*
* ------- 시간 설정 JS------------
* */
let contentService = (function () {

    // 채팅 시간 변환
    function getReplyDates(replyDate) {
        let today = new Date();
        let registerDate = new Date(replyDate);
        let gap = today.getTime() - registerDate.getTime();

        // if(gap < 1000 * 60 * 60 * 24){
        let h = registerDate.getHours();
        let mm = registerDate.getMinutes();
        let s = registerDate.getSeconds();

        h = (h < 10 ? '0' : '') + h;
        mm = (mm < 10 ? '0' : '') + mm;
        // s = (s < 10 ? '0' : '') + s;

        return [h, mm].join(":");

        // else{
        //     let y = registerDate.getFullYear();
        //     let m = registerDate.getMonth() + 1;
        //     let d = registerDate.getDate();
        //
        //     m = (m < 10 ? '0' : '') + m;
        //     d = (d < 10 ? '0' : '') + d;
        //
        //     return [y, m, d].join("-");
        // }

    }

    return {getReplyDates: getReplyDates}
})();



/*
* ----------------------------------WebSocket-----------------------------------------
* */


let webSocket;
function connect(){
    webSocket = new WebSocket("ws://localhost:10718/chat");
    webSocket.onopen = onOpen;
    webSocket.onclose = onClose;
    webSocket.onmessage = onMessage;
    console.log("connect");
}

function disconnect(){
    webSocket.send(JSON.stringify({
        reiceiver : receiverRoomId
    }))
    webSocket.close();
    console.log("disconnect");
}

function send(){
    msg = document.getElementById("message").value; //메세지
    webSocket.send(JSON.stringify({
        reiceiverId : receiverRoomId,
        chattingContent : msg
    }))
    console.log("~~~~send~~~~~~")
}

function onOpen(){
    msg = document.getElementById("message").value; //메세지
    webSocket.send(JSON.stringify({
        reiceiverId : receiverRoomId,
        chattingContent : msg
    }))
    console.log("~~~~open~~~~~~");
}

function onMessage(e){
    data = e.data;
    console.log("~~~~onMessage~~~~~~");
    chatroom = document.getElementById("chatroom");
    chatroom.innerHTML = chatroom.innerHTML + "<br>" + data;
}
function onClose(){
    disconnect();
    console.log("~~~~onClose~~~~~~");
}







