/*
*------------ 채팅방 내용 확인 ajax--------------
* */
let sessionId = $(".sessionLoginUser").val();//내 아이디(세션 아이디)
let receiverRoomId;
let nickName;
let profile;
let roomId;
let msg;

$("li.leftChattingList").on("click", function () {
    receiverRoomId =$(this).attr("href"); // 채팅방 아이디
    nickName = $(this).find(".userId").text(); // 닉네임
    profile = $(this).find(".userProfile").attr("src") // 유저 프로필
    $(".chattingRoomName").text(nickName); // 해당 채팅방 닉네임 변경
    $(".profileImages").attr("src", profile); // 해당 채팅방 유저 프로필 변경
    connect();

    console.log(receiverRoomId);
    console.log(sessionId);
    console.log(nickName);



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
                    console.log(err);
                }


            }
        );

        function getChattingContentList(contents) {
            let text = "";
            contents.forEach(content => {
                if (content.writer.userId != sessionId) {
                    text += " <div class=\"opponent\">";
                    text += " <div class=\"thumb\">";
                    text += "<img src='" + content.writer.userFile + "'>";
                    text += "</div>"
                    text += "<div class='userIdChatTxt'>"
                    text += "<span class='userId'>" + content.writer.userNickName + "</span>";
                    text += "<div class='chatTxt'>";
                    text += "<span class='chatTxtContents'>";
                    text += "<a style='color: rgb(51, 51, 51);'>" + content.chattingContent + "</a>";
                    text += "</span>";
                    text += "<div class='timeWrap'>";
                    text += "<span class='time'>" + contentService.getReplyDates(content.chatDate) + "</span>";
                    text += "</div>";
                    text += "</div>";
                    text += "</div>";
                    text += "</div>";
                } else {
                    text += "<div class=\"my chatTxt\">";
                    text += "<p class=\"chatTxt\">";
                    text += "<span class=\"chatTxtContents\">"
                    text += "<a style=\"color: rgb(255, 255, 255);\">" + content.chattingContent;
                    +"</a>"
                    text += "</span>"
                    text += "<div>"
                    text += "<div class='timeWrap'>";
                    // text += "<span class=\"check\">" + "읽음" + "</span>";
                    text += "<span class=\"time\">" + contentService.getReplyDates(content.chatDate) + "</span>";
                    text += "</div>";
                    text += "</div>";
                    text += "</p>";
                    text += "</div>";
                }
            });
            $("div.chattingRoomWrap").html(text);
        }

    });


/*
* -----------채팅 내용 저장하기----------------
* */

function add(chattingContent) {
    console.log(chattingContent);
    $.ajax({
        url: "/chat/chattingOk/",
        type: "post",
        data: JSON.stringify(chattingContent),
        contentType: "application/json; charset=utf-8",
        success: function(xhr,status) {
        },
        error: function (xhr, status, err) {
        }
    });
}

$(".buttonComponents_circle__2iQ3w").on("click", function () {
    let sessionId = $(".sessionLoginUser").val();//내 아이디(세션 아이디)
    msg = document.getElementById("message").value; //메세지

    add({
        writerId:sessionId,
        chattingRoomId:receiverRoomId,
        chattingContent:msg,
        messageType: 1
    })
    send();
});


/*
* -------------------------채팅 목록 뿌려주기------------------------------
* */

// function show() {
//     let receiverRoomId =$(this).attr("href"); // 상대방 유저 아이디
//     $.ajax({
//         url: "/chat/chatContent/" + receiverRoomId,
//         type: "get",
//         async: false,
//         success: function (contents) {
//             if (contents != null) {
//                 list(contents);
//             }
//         },
//         error: function (xhr, status, err) {
//         }
//     });
//
// //채팅방 목록 따로 빼놓은 것
//     function list(contents) {
//         let text = "";
//         /* 채팅방 내용 */
//         contents.forEach(content => {
//             text += " <div class=\"opponent\">";
//             text += " <div class=\"thumb\">";
//             text += "<a href=\"/people/dddog\" target=\"_blank\">"
//             text += "<img src='" + content.receiver.userFile + "' alt='chat_image'>";
//             text += "</a>"
//             text += "</div>"
//             text += "<div class='userIdChatTxt'>"
//             text += "<span class='userId'>" + content.receiver.userNickName + "</span>";
//             text += "<div class='chatTxt'>";
//             text += "<span class='chatTxtContents'>";
//             text += "<a style='color: rgb(51, 51, 51);'>" + content.chattingContent + "</a>";
//             text += "</span>";
//             text += "<div class='timeWrap'>";
//             text += "<span class='time'>" + contentService.getReplyDates(content.createdDate) + "</span>";
//             text += "</div>";
//             text += "</div>";
//             text += "</div>";
//             text += "</div>";
//         })
//         $("div.chattingRoomWrap").html(text);
//
//     }
// }


/*
* ----------채팅방 나가기----------------
* */


$(".chatExitModal").on("click",".redBtn", function () {
    console.log("나가기 클릭");
    $.ajax({
        url: "/chat/chattingDelete/" + receiverRoomId,
        type: "delete",
        data: JSON.stringify({chattingRoomId:receiverRoomId}),
        contentType: "application/json; charset=utf-8",
        success: function () {
            console.log("삭제성공")
            location.reload();
        },
        error: function (xhr, status, err) {
            console.log(err);
        }
    })
});



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
    // webSocket.send(JSON.stringify({
    //     writerId : sessionId,
    //     messageType:'READ',
    //     chattingRoomId:receiverRoomId
    // }))
    webSocket.close();
    console.log("disconnect");
}

function send(){
    msg = document.getElementById("message").value; //메세지
    webSocket.send(JSON.stringify({
        writerId : sessionId,
        chattingContent : msg,
        messageType:'UNREAD',
        chattingRoomId:receiverRoomId
    }))
    console.log("~~~~send~~~~~~")
}

function onOpen(){
    msg = document.getElementById("message").value; //메세지
    // webSocket.send(JSON.stringify({
    //     writerId : sessionId,
    //     messageType:'READ',
    //     chattingRoomId:receiverRoomId
    // }))
    console.log("~~~~open~~~~~~");
}

function onMessage(e) {
    data = e.data;
    console.log("~~~~onMessage~~~~~~");
    let datas = data.replaceAll("\"", "");
    console.log(datas);
    let realDatas = datas.split(":");
    console.log(realDatas);
    let dateTime = new Date().toTimeString().split(' ')[0];
    let time = dateTime.split(":")
    // console.log(realDatas[0]) //채팅 내용
    // console.log(realDatas[1])
    // console.log(realDatas[2])
    // console.log(realDatas[3])
    // console.log(time[0])
    // console.log(time[1])
    chatroom = document.getElementById("chatroom");

chatroom.innerHTML = chatroom.innerHTML
    + " <div class=\"my chatTxt\">"
    + " <p class=\"chatTxt\">"
    + "<span class=\"chatTxtContents\">"
    + "<a style=\"color: rgb(255, 255, 255);\">" +realDatas[0] +"</a>"
    + "</span>"
    + "<div>"
    + "<div class='timeWrap'>"
    +" <span class=\"check\">" + 1 +"</span>"
    + "<span class=\"time\">" + time[0]+ ":" + time[1]+"</span>"
    + "</div>"
    + "</div>"
    +"</p>"
    + "</div>"
}

function onClose(){
    disconnect();
    console.log("~~~~onClose~~~~~~");
}

$(document).ready(function() {
    $('.chattingRoomWrap').scrollTop($('.chattingRoomWrap')[0].scrollHeight);
});


