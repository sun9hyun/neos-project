/* 프로필 사진 옆에 하트 hover click 이벤트 */

/*$(".userName").on("mouseover", function () {
    // $(this).css("color", "#24A");
    $(this).children('.idHoverMenu').css("display", "block");
})

$(".userName").on("mouseout", function () {
    // $(this).css("color", "#2A4");
    $(this).children('.idHoverMenu').css("display", "none");
})*/

/*$(".heart").on("click", function () {
    $(this).toggleClass('active');
})*/


/* 스터디 초대 눌렀을 때 가능하다면 나오는 모달 */

$(".invite").on("click", function () {
    let name = $(this).closest(".letspler_Re").find(".name").text();
    let text = "";
    text += `<span class="inviteHead">` + name + `님께 초대장을 보내시겠습니까?</span>`
    $(".invitationModal .requestModal .tit").html(text);

    $(".invitationModal").css("display", "block");
})

$(".closeBtn").on("click", function () {
    $(".invitationModal").css("display", "none");
    $(".okModal").css("display", "none");
})

$(".inviteYesBtn").on("click", function () {
    $(".invitationModal").css("display", "none");
    $(".okModal").css("display", "block");
})

$(".redWhiteBtn").on("click", function () {
    $(".invitationModal").css("display", "none");
})

$(".okBtn").on("click", function () {
    $(".okModal").css("display", "none");
})


/* 초대 불가능 하다면 뜨는 모달 */

// $(".invite").on("click", function () {
//     $(".noModal").css("display", "block");
// })
//
// $(".closeBtn").on("click", function () {
//     $(".noModal").css("display", "none");
// })
//
// $(".redBtn").on("click", function () {
//     $(".noModal").css("display", "none");
// })



/* 지역 선택시 대학 선택 셀렉박스 보이게 하기 */
// $(document).ready(function() {
//     $('.locationSelect').change(function() {
//         var result = $('.locationSelect option:selected').val();
//         if (result == '00') {
//             $('.universitySelect').hide();
//         } else {
//             $('.universitySelect').show();
//         }
//     });
// });


/* 네오챗 마우스 오버하면 이미지도 하얀걸로 바뀌기 */
$(".letspler_Re .bottom .chatBtn").on("mouseover", function () {
    console.log("1");
    $(this).find('img').attr('src', '/images/neosUser/chatting-icon-hover.png');
})

$(".letspler_Re .bottom .chatBtn").on("mouseout", function () {
    $(this).find('img').attr('src', '/images/neosUser/chatting-icon.png');
})



/* 반응형 관련 */
var mql = window.matchMedia("screen and (max-width: 1024px)");

if(mql.matches) {
    $(".rightHead .backBtn").on("click", function () {
        $(".chattingHeader .left").removeClass("leftHead");
        $(".chattingSection .list.left").removeClass("leftBody");
        $(".chattingHeader .right.active").addClass("rightHead");
        $(".chattingSection .right.active").addClass("rightBody");
    })

    $(".leftChattingList").on("click", function () {
        $(".chattingHeader .left").addClass("leftHead");
        $(".chattingSection .list.left").addClass("leftBody");
        $(".chattingHeader .right.active").removeClass("rightHead");
        $(".chattingSection .right.active").removeClass("rightBody");
    })
}


/* */



/*globalThis.page = 0;
$(document).ready(function () {
    globalThis.check = false;
    show();

    function show() {
        console.log("들어옴")
        $.ajax({

            url:"/neosUser/neosUserList?page=" + (globalThis.page), /!**4*!/
            type : "get",
            async: false,
            success: function (userDTOS) {
                console.log(userDTOS)
                if(userDTOS != null){
                    getList(userDTOS);
                }
            },
            error: function (xhr , status , err) {
                console.log(xhr,status,err);
            }

        });
    }

    function getList(userDTOS) {
        let text = '';
        $(userDTOS.content).each((i,item) => {

            $college = item.collegeId;

            $user = item.userName;


            text += `<li class="letspler_Re">`
            text += `<img loading="lazy" class="newMark"  src="https://letspl.me/assets/images/ic-badge_project_new.png" alt="신규 렛플인">`
            text += `<div class="top">`
            text += `<div class="profilePicWrap">`
            text += `<div class="profilePic" >`
            text += `<img src="` + item.userFile + `" alt='민쇼니'></div>`
            text += `<a class="favorite heart"></a>`
            text += `</div>`
            text += `<div class="profileTxt">`
            text += `<div class="idWrap">`
            text += `<img src="` + item.userNeosBadge + `" loading=\`lazy\` class=\`userLv\` style='width: 20px'>`
            text += `<div class="userName" >`
            text += `<span class="name">`+item.userNickName+`</span>`
            text += `<ul class="idHoverMenu">`
            text += `<li>`
            text += `<a class="detail">프로필 상세</a>`
            text += `<input type="hidden" href=" ` +userId +`">`
            text += `</li>`
            text += `<li><a class="chat">1:1대화</a></li>`
            text += `<li><a class="invite">스터디 초대</a></li>`
            text += `</ul>`
            text += `</div>`
            text += `<div class="certiWrap">`
            text += `<img loading="lazy" class="userCertIcon"  src="https://letspl.me/assets/images/ic-certi-red.png"  alt="실명인증">`
            text += `</div>`
            text += `<div class="certiWrap"></div>`
            text += `</div>`
            text += `<div class="coffeAndHeart">`
            text += `<div>`
            text += `<p>`
            text += `<img loading ="lazy" src="/images/neosUser/chatting-icon.png" alt="네오스포인트" style="margin-top: 6px;">`
            text += `</p>`
            text += `<span class="userPoint">`+item.userChattingPoint+`</span>`
            text += `</div>`
            text += `<div>`
            text += `<img loading="lazy" src="https://letspl.me/assets/images/ico_like.png" alt="구독자 수"  style="width: 20px; height: 20px;">`
            text += `<span id="result">0</span>`
            text += `</div>`
            text += `</div>`
            text += `</div>`
            text += `</div>`
            text += `<div class="bottom">`
            text += `<ul class="userFavorite">`
            text += `<li>`

            if ($college != null){
                text += `<h3><span>[대학]&nbsp;<span class="university">`+ item.collegeName+`</span></span>`
                text += `<span class="grade">`+ item.userCollegeYear +`</span>`
                text += `<span style="font-size: 14px;color: #999999;">학년</span></h3>`
            }

            if ($college == null){
                text += `<h3><span>[대학]&nbsp;<span class="university">해당사항없음</span>`
                text += `</span>`
                text += `<span class="grade">`
                text += `</span>`
                text += `<span style="font-size: 14px;color: #999999;">`
                text += `</span>`
                text += `</h3>`
            }

            text += `</li>`
            text += `<li>`

            if ($college != null){
                text += `<h3><span>[전공]&nbsp;<span class="major">`+item.userCollegeMajor+`</span></span>`
                text += `<span></span>`
                text += `</h3>`
            }

            if ($college == null){
                text += `<h3><span>[전공]&nbsp;<span class="major">해당사항없음</span></span>`
                text += `<span></span>`
                text += `</h3>`
            }

            text += `</li>`
            text += `</ul>`
            text += `<div class="userInfoTagWrap"></div>`
            text += `<a class="chatBtn">`
            text += `<div>`
            text += `<img loading="lazy" src="/images/neosUser/chatting-icon.png" alt="네오스포인트">`
            text += `<span >NEO CHAT</span>`
            text += `</div>`
            text += `</a>`
            text += `</div>`
            text += `</li>`

            /!* 되는거 *!/
            /!*1text += "<li class='letspler_Re'>";
            2text += "<img loading='lazy' class='newMark' src='https://letspl.me/assets/images/ic-badge_project_new.png'>";
            3text += "<div class='top'>";
            4text += "<div class='profilePicWrap'>";
            5text += "<div class='profilePic'>";
            6text += "<img src='" + item.userFile + "' alt='민쇼니'></div>";
            7text += "<a class='favorite heart' ></a>";
            8text += "</div>";
            9text += "<div class='profileTxt'>";
            0text += "<div class='idWrap'>";
            11text += "<img  src='" + item.userNeosBadge + "' loading=`lazy` class=`userLv` style='width: 20px'>";
            12text += "<div class='userName'>";


            13text += "<span class='name'> " + item.userNickName + "</span>";;
            14text += "<ul class= 'idHoverMenu'>";
            15text += "<li><a class='detail'>프로필 상세</a>";
            16text += "<input type='hidden'  href=' " + item.userId + "'>";
            17text += "</li>";
            18text += "<li><a class='chat'>1:1대화</a></li>";
            19text += "<li><a class='invite'>스터디 초대</a></li>";
            20text += "</ul>";
            21text += "</div>";
            22text += "<div class='certiWrap'>";
            23text += "<div class='certiWrap'><img loading='lazy' class='userCertIcon'  src='https://letspl.me/assets/images/ic-certi-red.png'\n" +
                "                                                        alt='실명인증'></div>";
            24text += "</div>";
            25text += "<div class='coffeAndHeart'>";
            26text += "<div><p><img loading ='lazy' src='/images/neosUser/chatting-icon.png' style='margin-top: 6px;'>";
            27text += " </p>";
            28text += "<span class='userPoint'>" + item.userChattingPoint + " </span>";
            29text += "</div>";
            30text += "<div><img loading='lazy' src='https://letspl.me/assets/images/ico_like.png' alt='구독자 수' style='width: 20px; height: 20px;'>";
            31text += "<span id='result'>0</span></div>";
            32text += "</div>";
            33text += "</div>";
            34text += "<div class='bottom'>";
            35text += "<ul class='userFavorite'>";
            36text += "<li>";

            if ($college != null) {
                text += "<h3><span>[대학]&nbsp;<span class='university'> " + item.collegeName + "</span></span>";
                text += "<span class='grade'> " + item.userCollegeYear + "</span>";
                text += "<span style='font-size: 14px;color: #999999;'>학년</span></h3>";
            }

            if ($college == null){
                text += "<h3><span>[대학]&nbsp;<span class='university'>해당사항없음</span></span>";
                text += "<span class='grade'></span><span style='font-size: 14px;color: #999999;'></span></h3>";
            }

            text += "</li>";
            text += "<li>";

            if($college != null){
                text += "<h3><span>[전공]&nbsp;<span class='major' >"+item.userCollegeMajor+"</span></span><span></span></h3>";
            }


            if($college == null){
                text += "<h3><span>[전공]&nbsp;<span class='major'>해당사항없음</span></span><span></span></h3>";
            }

            text += "</li>";
            text += "</ul>";
            text += "<div class='userInfoTagWrap'></div>";
            text += "<a class='chatBtn' >";
            text += "<div><img loading='lazy' src='/images/neosUser/chatting-icon.png'alt='네오스포인트'>";
            text += "<span >NEO CHAT</span></div>";
            text += "</a>";
            text += "</div>";
            text += "</li>";*!/

        });

        $("ul.peopleGridList").append(text);
    }



/!*더 보기 버틀*!/

$(".bigMore").on("click", function(e){
    e.preventDefault();
    if(!globalThis.check){
        globalThis.page = globalThis.page+1;
        show();
    }
});

});*/













