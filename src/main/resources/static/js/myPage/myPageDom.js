/*
*
* */
    let userId = $("input[name='userId']").val();
    let college = $(".Univer").prop("selected", true).val()

    show();

    function show() {
        // alert("show");
        // alert(userId);
        myPageService.collegeCityInfo(collegeCityInfo)
        myPageService.userInfo(userId, userInfo)
        myPageService.getList(userId, getList)
        myPageService.studyJoinList(userId,studyJoinList )
        myPageService.neosPowerList(userId, neosPowerList)
        myPageService.neosPointList(userId, neosPointList)
        myPageService.studyFollowList(userId, studyFollowList)
        myPageService.followList(userId, followList)
        myPageService.studySupporterList(userId, studySupporterList)
        myPageService.alarmList(userId, alarmList)
        // myPageService.checkCollegeId(college, checkCollegeId)
    }

    function showStore() {
        myPageService.getList(userId, getList)
    }

    function showUserInfo() {
        myPageService.userInfo(userId, userInfo)
    }

/*############################ 유저 삭제 START #####################################*/
$(".quitBtn").on("click", function(){
    var userId = $("input[name='userId']").val();
    myPageService.deleteUser(userId);
});
/*############################ 유저정보 수정 START #####################################*/
$(".updateBtn").on("click", function(){
    myPageService.modify({
        userId : $("input[name='userId']").val(),
        userFile : $(".userImg").attr("src"),
        userNickName: $("input[name='userNickName']").val(),
        collegeCity : $(".collegeCity").prop("selected", true).val(),
        collegeName : $(".Univer").prop("selected", true).val(),
        userCollegeCertify :$("input[name='CollegeCertify']").val(),
        userCollegeMajor : $(".uniPart").prop("selected", true).val(),
        userCollegeYear :  $(".uniYear").prop("selected", true).val(),
        userCollegeEmail : $(".uniEmail").val(),
        // userCollegeCertify : $(".userCollegeCertify").is(":checked") ? "noNeed" : "true",
        // userCollegeCertify : "true",
        userChattingPoint : $(".chatPoint").prop("selected", true).val(),
        userO2o : $(".otoo").prop("selected", true).val(),
        userCity : $(".areaSort").prop("selected", true).val(),
        userDay : $(".userDay").prop("selected", true).val(),
        userTime : $(".userTime").prop("selected", true).val(),
        userMbtiName : $(".mbtiSelect").prop("selected", true).val(),
        userIntroduce : $(".userIntroduce").val()
    }, function(){showUserInfo();});
});
// /*############################ 대학교 찾기 START #####################################*/
// function checkCollegeId(results) {
//     $("input[name='collegeId']").val(results);
// }


/*############################ 알림 지원 START #####################################*/
function alarmList(results) {
    let text = "";
    let day;
    results.forEach(item => {
        let today = myPageService.getDate(item.createdTime);
        console.log(today)
        text += "<li class='noticeDayWrap'>";
        // text += "<span class='day'>" + myPageService.getDate(item.createdTime) + "</span>";
        text += "<ul>";
        text += "<li class='noticeContentWrap'>";
        text += "<div class='left'>";
        text += "<p class='categoryAndDate'>";
        text += "<span>" + item.alarmContent + "</span>";
        text += "<span>" + myPageService.getReplyDate(item.createdTime) + "</span>";
        text += "</p>";
        text += "</div>";
        text += "<div class='btnWrap right'>";
        // text += "<a href='javascript:;'>프로필 가기</a>";
        if (item.alarmCategory == "STUDYSUPPORT"){
            text += "<a href='" + item.url + "'>스터디 가기</a>";
        }else if (item.alarmCategory == "COMMUNITYREPLY"){
            text += "<a href='" + item.url + "'>커뮤니티 가기</a>";
        }else if (item.alarmCategory == "SHOPREPLY"){
            text += "<a href='" + item.url + "'>자료상점 가기</a>";
        }
        text += "</div>";
        text += "</li>";
        text += "</ul>";
        text += "</li>";
    })


    $(".noticeListTimeLine").append(text);
}

/*############################ 스터디 지원 START #####################################*/
function studySupporterList(results) {
    let text = "";
    results.forEach(item => {
        text += `<div class="projectGridWrap" style="margin-right:10px; margin-bottom: 10px;">`
        text += `<input type="hidden" name="studyId" class="studyID_1j" value="` + item.studyId + `">`
        text += `<div class="projectTopInfo">`
        text += `<div class="top">`
        text += `<div class="badgeWrap">`
        text += `<div class="topBadge red">`
        text += `</div>`
        text += `<div class="badge black">`
        text += `<img src="` + item.collegeLogoFile + `"> </div>`
        text += `</div>`
        // $(item.follower).each((i,item)=>{
        //     if(item.userId==userSessionId){
        //         followCheck=true;
        //     }
        // })
        // if(followCheck){
        //     text+= `<div class="favorite listFavorite active "></div>`
        // }else{
        //     text+= `<div class="favorite listFavorite off "></div>`
        // }
        text += `</div>`
        if (item.studyType == '전공') {
            text += `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_12.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        } else if (item.studyType == '교양') {
            text += `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_01.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        } else if (item.studyType == '공통') {
            text += `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        }

        text += `</div>`
        text += `<a href="/study/list/` + item.studyId + `?view=true">`
        text += `<div class="projectBottomInfo">`
        text += `<div class="txtWrap projectWrap">`
        text += `<h3 class="category">` + item.studyType + `</h3>`
        text += `<h2 class="tit">` + item.studyTitle + `</h2>`
        if (item.studyO2o == 'all') {
            text += `<span class="studyCategory">#상관없음 </span>`
        } else if (item.studyO2o == 'online') {
            text += `<span class="studyCategory">#온라인 </span>`
        } else if (item.studyO2o == 'offline') {
            text += `<span class="studyCategory">#오프라인 </span>`
        }
        text += `<span class="studyCategory">#` + item.studyKeyword + `</span>`
        text += `</div>`
        text += `</div>`
        text += `<div class="projectInfo2">`
        text += `<div class="middleWrap">`
        text += `<div class="left">`
        text += `<div class="heartCount">`
        text += `<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 16 16" fill="none" style="position: relative;top: -0.1rem;">
                                                <path d="M8 2.48901C6.27297 2.48988 4.58622 3.01069 3.15934 3.98365C1.73246 4.9566 0.631569 6.33661 0 7.94401C0.630962 9.55179 1.73172 10.9321 3.15875 11.9051C4.58577 12.878 6.27285 13.3985 8 13.3985C9.72715 13.3985 11.4142 12.878 12.8413 11.9051C14.2683 10.9321 15.369 9.55179 16 7.94401C15.3684 6.33661 14.2675 4.9566 12.8407 3.98365C11.4138 3.01069 9.72703 2.48988 8 2.48901V2.48901ZM8 11.58C7.28087 11.58 6.57788 11.3668 5.97995 10.9672C5.38201 10.5677 4.91597 9.99984 4.64077 9.33545C4.36557 8.67106 4.29357 7.93998 4.43386 7.23466C4.57416 6.52935 4.92046 5.88148 5.42896 5.37297C5.93746 4.86447 6.58534 4.51817 7.29065 4.37788C7.99597 4.23758 8.72704 4.30959 9.39144 4.58479C10.0558 4.85999 10.6237 5.32602 11.0232 5.92396C11.4228 6.5219 11.636 7.22488 11.636 7.94401C11.6355 8.90818 11.2522 9.8327 10.5705 10.5145C9.88869 11.1962 8.96416 11.5795 8 11.58ZM8 5.76201C7.56844 5.76201 7.14657 5.88999 6.78775 6.12975C6.42892 6.36951 6.14925 6.71029 5.9841 7.109C5.81894 7.50771 5.77573 7.94643 5.85993 8.3697C5.94412 8.79297 6.15194 9.18176 6.45709 9.48692C6.76225 9.79208 7.15105 9.99989 7.57431 10.0841C7.99758 10.1683 8.43631 10.1251 8.83502 9.95992C9.23372 9.79477 9.57451 9.5151 9.81427 9.15627C10.054 8.79744 10.182 8.37557 10.182 7.94401C10.1824 7.65736 10.1262 7.37344 10.0167 7.10853C9.90719 6.84362 9.74648 6.60292 9.54379 6.40023C9.34109 6.19753 9.10039 6.03682 8.83548 5.9273C8.57057 5.81779 8.28666 5.76162 8 5.76201Z" fill="#A4A4B4"></path>
                                            </svg> <span style="margin-right:0.5rem;margin-left:0.2rem;font-size: 12px;color:#888">` + item.studyView + `</span>`
        text += `<img src="https://letspl.me/assets/images/ic-favorite-empty-white.svg" alt="구독자 수"><span>` + item.followTotal + `</span></div>`
        text += `</div> <div class="right">`
        text += `</div>`
        text += `</div>`
        text += `<div class="bottomWrap">`
        text += `<div class="gatherTxt">`
        text += `<div>`
        if (item.studyRecruitStatus == 'RECRUITING') {
            text += `<span>모집중</span>`
        } else if (item.studyRecruitStatus == 'COMPLETE') {
            text += `<span>모집완료</span>`
        }
        // text+= `<span>`+item.studyMemberList.length+`</span>`
        // text+= `<span>/</span>`
        // text+= `<span>`+item.studySupport+`</span>
        text += `</div></div>`
        text += `</div>`
        text += `</div>`
        text += `</div>`
        text += `</a>`

        // else{
        //     textProceeding += `<div class="noProject"><p>지원 중인 모임이 없습니다.</p></div>`
        // }
        $(".joinStudy").empty();
    })


    $(".joinStudy").append(text);
}

/*############################ 네오스인 팔로우 START #####################################*/
function followList(results) {
    let text = "";
    // results.forEach(item => {
    results.forEach(item => {
        text += "<li class='letspler_Re' style='max-width: 264px'>";
        text += "<div class='top' style='margin-bottom: 10px'>";
        text += "<div class='profilePicWrap'>";
        text += "<div class='profilePic'>";
        text += "<img src='" + item.userFile + "'>";
        text += "</div>";
        text += "<a class='favorite'></a>";
        text += "</div>";
        text += "<div class='profileTxt'>";
        text += "<div class='idWrap'>";
        text += "<img loading='lazy' style='width:22px; height: 23px' class='userLv' src='" + item.userNeosBadge +"'>";
        text += "<h2 class='userName'><span>" + item.userNickName +"</span>";
        text += "<ul class='idHoverMenu' style='display: none;'>";
        text += "<li><a >프로필 상세</a></li>";
        text += "<li class='detailChat'><a>1:1 대화</a></li>";
        text += "</ul>";
        text += "</h2>";
        text += "</div>";
        text += "<div class='coffeAndHeart'>";
        text += "<div><img loading='lazy' src='/images/neosUser/chatting-icon.png' style='margin-top: 6px;'>";
        text += "<span class='userPoint'>" + item.userChattingPoint + "</span>";
        text += "</div>";
        text += "<div>";
        text += "<img loading='lazy' src='https://letspl.me/assets/images/ico_like.png' style='width: 20px; height: 20px;'><span>0</span>";
        text += "</div>";
        text += "</div>";
        text += "</div>";
        text += "</div>";
        text += "<div class='bottom' style='min-height: 0'>";
        text += "<ul class='userFavorite'>";
        if(item.userCollegeCertify == "true"){
            text += "<li>";
            text += "<h3><span>[대학]&nbsp;<span class='university'>" + item.collegeName +"</span></span>";
            text += "<span class='grade'>" + item.userCollegeYear + "</span><span style='font-size: 14px;color: #999999;'>학년</span></h3>";
            text += "</li>";
            text += "<li>";
            text += "<h3><span>[전공]&nbsp;<span class='major'>" + item.userCollegeMajor +"</span></span><span></span></h3>";
            text += "</li>";
        }else{
            text += "<li>";
            text += "<h3><span>[대학]&nbsp;<span class='university'>해당사항없음</span></span>";
            text += "</li>";
            text += "<li>";
            text += "<h3><span>[전공]&nbsp;<span class='major'>해당사항없음</span></span><span></span></h3>";
            text += "</li>";
        }
        text += "</ul>";
        text += "<input type='hidden' class='writerUserId_1j' value='" + item.userId + "'>";
        text += "<a class='chatBtn neosBtn'>";
        text += "<div><img loading='lazy' src='/images/neosUser/chatting-icon.png' alt='커피포인트'><span>NEO CHAT</span></div>";
        text += "</a>";
        text += "</div>";
        text += "</li>";
    })

    $(".peopleGridList").append(text);
}

/*############################ 스터디 참여 START #####################################*/
function studyJoinList(results) {
    let text = "";
    let textProceeding = "";
    let textFinish = "";
    results.forEach(item => {
        text += `<div class="projectGridWrap" style="margin-right:10px; margin-bottom: 10px;">`
        text += `<input type="hidden" name="studyId" class="studyID_1j" value="` + item.studyId + `">`
        text += `<div class="projectTopInfo">`
        text += `<div class="top">`
        text += `<div class="badgeWrap">`
        text += `<div class="topBadge red">`
        text += `</div>`
        text += `<div class="badge black">`
        text += `<img src="` + item.collegeLogoFile + `"> </div>`
        text += `</div>`
        // $(item.follower).each((i,item)=>{
        //     if(item.userId==userSessionId){
        //         followCheck=true;
        //     }
        // })
        // if(followCheck){
        //     text+= `<div class="favorite listFavorite active "></div>`
        // }else{
        //     text+= `<div class="favorite listFavorite off "></div>`
        // }
        text += `</div>`
        if (item.studyType == '전공') {
            text += `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_12.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        } else if (item.studyType == '교양') {
            text += `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_01.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        } else if (item.studyType == '공통') {
            text += `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        }

        text += `</div>`
        text += `<a href="/study/list/` + item.studyId + `?view=true">`
        text += `<div class="projectBottomInfo">`
        text += `<div class="txtWrap projectWrap">`
        text += `<h3 class="category">` + item.studyType + `</h3>`
        text += `<h2 class="tit">` + item.studyTitle + `</h2>`
        if (item.studyO2o == 'all') {
            text += `<span class="studyCategory">#상관없음 </span>`
        } else if (item.studyO2o == 'online') {
            text += `<span class="studyCategory">#온라인 </span>`
        } else if (item.studyO2o == 'offline') {
            text += `<span class="studyCategory">#오프라인 </span>`
        }
        text += `<span class="studyCategory">#` + item.studyKeyword + `</span>`
        text += `</div>`
        text += `</div>`
        text += `<div class="projectInfo2">`
        text += `<div class="middleWrap">`
        text += `<div class="left">`
        text += `<div class="heartCount">`
        text += `<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 16 16" fill="none" style="position: relative;top: -0.1rem;">
                                                <path d="M8 2.48901C6.27297 2.48988 4.58622 3.01069 3.15934 3.98365C1.73246 4.9566 0.631569 6.33661 0 7.94401C0.630962 9.55179 1.73172 10.9321 3.15875 11.9051C4.58577 12.878 6.27285 13.3985 8 13.3985C9.72715 13.3985 11.4142 12.878 12.8413 11.9051C14.2683 10.9321 15.369 9.55179 16 7.94401C15.3684 6.33661 14.2675 4.9566 12.8407 3.98365C11.4138 3.01069 9.72703 2.48988 8 2.48901V2.48901ZM8 11.58C7.28087 11.58 6.57788 11.3668 5.97995 10.9672C5.38201 10.5677 4.91597 9.99984 4.64077 9.33545C4.36557 8.67106 4.29357 7.93998 4.43386 7.23466C4.57416 6.52935 4.92046 5.88148 5.42896 5.37297C5.93746 4.86447 6.58534 4.51817 7.29065 4.37788C7.99597 4.23758 8.72704 4.30959 9.39144 4.58479C10.0558 4.85999 10.6237 5.32602 11.0232 5.92396C11.4228 6.5219 11.636 7.22488 11.636 7.94401C11.6355 8.90818 11.2522 9.8327 10.5705 10.5145C9.88869 11.1962 8.96416 11.5795 8 11.58ZM8 5.76201C7.56844 5.76201 7.14657 5.88999 6.78775 6.12975C6.42892 6.36951 6.14925 6.71029 5.9841 7.109C5.81894 7.50771 5.77573 7.94643 5.85993 8.3697C5.94412 8.79297 6.15194 9.18176 6.45709 9.48692C6.76225 9.79208 7.15105 9.99989 7.57431 10.0841C7.99758 10.1683 8.43631 10.1251 8.83502 9.95992C9.23372 9.79477 9.57451 9.5151 9.81427 9.15627C10.054 8.79744 10.182 8.37557 10.182 7.94401C10.1824 7.65736 10.1262 7.37344 10.0167 7.10853C9.90719 6.84362 9.74648 6.60292 9.54379 6.40023C9.34109 6.19753 9.10039 6.03682 8.83548 5.9273C8.57057 5.81779 8.28666 5.76162 8 5.76201Z" fill="#A4A4B4"></path>
                                            </svg> <span style="margin-right:0.5rem;margin-left:0.2rem;font-size: 12px;color:#888">` + item.studyView + `</span>`
        text += `<img src="https://letspl.me/assets/images/ic-favorite-empty-white.svg" alt="구독자 수"><span>` + item.followTotal + `</span></div>`
        text += `</div> <div class="right">`
        text += `</div>`
        text += `</div>`
        text += `<div class="bottomWrap">`
        text += `<div class="gatherTxt">`
        text += `<div>`
        if (item.studyRecruitStatus == 'RECRUITING') {
            text += `<span>모집중</span>`
        } else if (item.studyRecruitStatus == 'COMPLETE') {
            text += `<span>모집완료</span>`
        }
        // text+= `<span>`+item.studyMemberList.length+`</span>`
        // text+= `<span>/</span>`
        // text+= `<span>`+item.studySupport+`</span>
        text += `</div></div>`
        text += `</div>`
        text += `</div>`
        text += `</div>`
        text += `</a>`

        if (item.studyStatus.toString() == "PROCEEDING") {
            textProceeding += text;
            text = "";
            $(".proceedingStudy").empty();
        }else if(item.studyStatus.toString() == "FINISH") {
            textFinish += text;
            text = "";
            $(".finishStudy").empty();
        }
        // else{
        //     textProceeding += `<div class="noProject"><p>진행 중인 모임이 없습니다.</p></div>`
        // }
    })




    $(".proceedingStudy").append(textProceeding);
    $(".finishStudy").append(textFinish);
}

/*############################ 스터디 팔로우 START #####################################*/
function studyFollowList(results) {
    let text = "";
    // results.forEach(item => {
    $(results).each((i,item)=>{
        text+= `<div class="projectGridWrap" style="margin-right:10px; margin-bottom: 10px;">`
        text+= `<input type="hidden" name="studyId" class="studyID_1j" value="`+item.studyId+`">`
        text+= `<div class="projectTopInfo">`
        text+= `<div class="top">`
        text+= `<div class="badgeWrap">`
        text+= `<div class="topBadge red">`
        text+= `</div>`
        text+= `<div class="badge black">`
        text+= `<img src="`+item.collegeLogoFile+`"> </div>`
        text+= `</div>`
        // $(item.follower).each((i,item)=>{
        //     if(item.userId==userSessionId){
        //         followCheck=true;
        //     }
        // })
        // if(followCheck){
        //     text+= `<div class="favorite listFavorite active "></div>`
        // }else{
        //     text+= `<div class="favorite listFavorite off "></div>`
        // }
        text+= `</div>`
        if(item.studyType =='전공'){
            text+= `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_12.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        }else if(item.studyType =='교양'){
            text+= `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_01.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        }else if(item.studyType =='공통'){
            text+= `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        }

        text+= `</div>`
        text += `<a href="/study/list/`+item.studyId+`?view=true">`
        text+= `<div class="projectBottomInfo">`
        text+= `<div class="txtWrap projectWrap">`
        text+= `<h3 class="category">`+item.studyType+`</h3>`
        text+= `<h2 class="tit">`+item.studyTitle+`</h2>`
        if(item.studyO2o=='all'){
            text+= `<span class="studyCategory">#상관없음 </span>`
        }else if(item.studyO2o=='online'){
            text+= `<span class="studyCategory">#온라인 </span>`
        }else if(item.studyO2o=='offline'){
            text+= `<span class="studyCategory">#오프라인 </span>`
        }
        text+= `<span class="studyCategory">#`+item.studyKeyword+`</span>`
        text+= `</div>`
        text+= `</div>`
        text+= `<div class="projectInfo2">`
        text+= `<div class="middleWrap">`
        text+= `<div class="left">`
        text+= `<div class="heartCount">`
        text+= `<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 16 16" fill="none" style="position: relative;top: -0.1rem;">
                                                <path d="M8 2.48901C6.27297 2.48988 4.58622 3.01069 3.15934 3.98365C1.73246 4.9566 0.631569 6.33661 0 7.94401C0.630962 9.55179 1.73172 10.9321 3.15875 11.9051C4.58577 12.878 6.27285 13.3985 8 13.3985C9.72715 13.3985 11.4142 12.878 12.8413 11.9051C14.2683 10.9321 15.369 9.55179 16 7.94401C15.3684 6.33661 14.2675 4.9566 12.8407 3.98365C11.4138 3.01069 9.72703 2.48988 8 2.48901V2.48901ZM8 11.58C7.28087 11.58 6.57788 11.3668 5.97995 10.9672C5.38201 10.5677 4.91597 9.99984 4.64077 9.33545C4.36557 8.67106 4.29357 7.93998 4.43386 7.23466C4.57416 6.52935 4.92046 5.88148 5.42896 5.37297C5.93746 4.86447 6.58534 4.51817 7.29065 4.37788C7.99597 4.23758 8.72704 4.30959 9.39144 4.58479C10.0558 4.85999 10.6237 5.32602 11.0232 5.92396C11.4228 6.5219 11.636 7.22488 11.636 7.94401C11.6355 8.90818 11.2522 9.8327 10.5705 10.5145C9.88869 11.1962 8.96416 11.5795 8 11.58ZM8 5.76201C7.56844 5.76201 7.14657 5.88999 6.78775 6.12975C6.42892 6.36951 6.14925 6.71029 5.9841 7.109C5.81894 7.50771 5.77573 7.94643 5.85993 8.3697C5.94412 8.79297 6.15194 9.18176 6.45709 9.48692C6.76225 9.79208 7.15105 9.99989 7.57431 10.0841C7.99758 10.1683 8.43631 10.1251 8.83502 9.95992C9.23372 9.79477 9.57451 9.5151 9.81427 9.15627C10.054 8.79744 10.182 8.37557 10.182 7.94401C10.1824 7.65736 10.1262 7.37344 10.0167 7.10853C9.90719 6.84362 9.74648 6.60292 9.54379 6.40023C9.34109 6.19753 9.10039 6.03682 8.83548 5.9273C8.57057 5.81779 8.28666 5.76162 8 5.76201Z" fill="#A4A4B4"></path>
                                            </svg> <span style="margin-right:0.5rem;margin-left:0.2rem;font-size: 12px;color:#888">`+item.studyView+`</span>`
        text+= `<img src="https://letspl.me/assets/images/ic-favorite-empty-white.svg" alt="구독자 수"><span>`+item.followTotal+`</span></div>`
        text+= `</div> <div class="right">`
        text+= `</div>`
        text+= `</div>`
        text+= `<div class="bottomWrap">`
        text+= `<div class="gatherTxt">`
        text+= `<div>`
        if(item.studyRecruitStatus=='RECRUITING'){
            text+= `<span>모집중</span>`
        }else if(item.studyRecruitStatus=='COMPLETE'){
            text+= `<span>모집완료</span>`
        }
        // text+= `<span>`+item.studyMemberList.length+`</span>`
        // text+= `<span>/</span>`
        // text+= `<span>`+item.studySupport+`</span>
        text+= `</div></div>`
        text+= `</div>`
        text+= `</div>`
        text+= `</div>`
        text+= `</a>`
    })
    $(".studyFollowTable").append(text);
}

/*############################ 네오포인트 START #####################################*/
function neosPointList(results) {
    let textPuls = "";
    let textMinus = "";

    results.forEach(info => {
        if(info.neosPointMoney > 0) {
            textPuls += "<tr>";
            textPuls += "<td>" + myPageService.getReplyDate(info.createdDate) + "</td>";
            textPuls += "<td>" + info.neosPointMoney + "</td>";
            textPuls += "<td>" + info.neosPointContent + "</td>";
            textPuls += "</tr>";
        }else{
            textMinus += "<tr>";
            textMinus += "<td>" + myPageService.getReplyDate(info.createdDate) + "</td>";
            textMinus += "<td>" + info.neosPointMoney + "</td>";
            textMinus += "<td>" + info.neosPointContent + "</td>";
            textMinus += "</tr>";
        }
    })
    $(".plusNeosPoint").append(textPuls);
    $(".minusNeosPoint").append(textMinus);
}


/*############################ 네오력 START #####################################*/
function neosPowerList(result) {
    let text = "";


    result.forEach(info => {
        console.log(info.neosPowerAbility);
        if (info.neosPowerContent == "LOGIN") {
            text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>로그인 완료</td></tr>";
        }else if(info.neosPowerContent == "STUDYFOLLOW"){
            if(info.neosPowerAbility < 0){
                text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>스터디 팔로우 취소</td></tr>";
            }
            text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>스터디 팔로우 진행</td></tr>";
        }else if(info.neosPowerContent == "POST"){
            if(info.neosPowerAbility == 100){
                console.log("+확인");
                text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>게시글(스터디, 자료상점) 작성</td></tr>";
            }else if(info.neosPowerAbility == 50){
                text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>커뮤니티(고민상담, 자유게시판) 작성</td></tr>";
            }else if(info.neosPowerAbility == -50){
                text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>커뮤니티(고민상담, 자유게시판) 삭제</td></tr>";
            }else if(info.neosPowerAbility == -100){
                console.log("-확인");
                text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>게시글(스터디, 자료상점) 삭제</td></tr>";
            }
        }else if(info.neosPowerContent == "POINT"){
            text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>포인트 구매 적립</td></tr>";
        }else if(info.neosPowerContent == "REPLY"){
            if(info.neosPowerAbility < 0){
                text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>댓글 작성 취소</td></tr>";
            }
            text += "<tr><td>" + myPageService.getReplyDate(info.createdDate) + "</td><td>" + info.neosPowerAbility + "</td><td>댓글 작성</td></tr>";
        }
    });

    $(".neosPowerTbl").append(text)
}


/*############################ 대학 정보 START #####################################*/

function collegeCityInfo(result) {
        let text = "";

        result.forEach(info => {
            text += "<option th:value='" + info + "'>" + info + "</option>";
        })

        $(".KRUni").append(text);
    }

/*############################ 유저 정보 START #####################################*/
    function userInfo(user) {

        // 프로필 이미지
        $(".userImg").attr("src", user.userFile);
        // OAuth 이메일
        $("input[name='userOAuthEmail']").val(user.userOAuthEmail);
        // 유저 닉네임
        $("input[name='userNickName']").val(user.userNickName);
        // 유저 핸드폰 번호
        $("input[name='userPhoneNumber']").val(user.userPhoneNumber);
        // 대학 관련(대학교 지역, 이름 제외)
        if(user.collegeCity){
            $(".collegeCity").val(user.collegeCity).prop("selected", true);
        }

        if(user.collegeName){
            $(".Univer").empty();
            $(".Univer").append(` <option class="collegeName_1j" value="`+user.collegeName+`">`+user.collegeName+`</option>`);
        }

        if(user.userCollegeCertify == "noNeed"){
            $(".userCollegeCertify").prop('checked', true);
            $(".CollegeCertify").val("noNeed");
            $('.KRUni').attr('disabled',true);
        }else if(user.userCollegeCertify == "true"){
            $(".userCollegeCertify").prop('checked', false);
            $(".CollegeCertify").val("true");
            $(".uniPart").val(user.userCollegeMajor).prop("selected", true);
            $(".uniYear").val(user.userCollegeYear).prop("selected", true);
            $(".uniEmail").val(user.userCollegeEmail);
        }
        // 채팅 포인트
        $(".chatPoint").val(user.userChattingPoint).prop("selected", true);

        // 선호 지역 설정
        if(user.userO2o){
            $(".otoo").val(user.userO2o).prop("selected", true);
        }

        if(user.userCity){
            $(".areaSort").val(user.userCity).prop("selected", true);
        }

        // 선호 시간 설정
        if(user.userDay){
            $(".userDay").val(user.userDay).prop("selected", true);
        }

        if(user.userTime){
            $(".userTime").val(user.userTime).prop("selected", true);
        }

        // MBTI
        $(".mbtiSelect").val(user.userMbtiName).prop("selected", true);
        $(".mbtiSelectText").val(user.userMbtiName);

        // 자세한 소개
        $(".userIntroduce").val(user.userIntroduce);

        // 네오력 레벨
        $(".totalNeos").html("MY 네오력 L" + user.userNeosPowerLevel + " (총 : " + user.userNeosPowerAbility + "NP)");
        // $(".Univer").html("네오력 L" + user.userNeosPowerLevel + "(" + user.userNeosPowerAbility + ") - 다음 레벨까지" + user.userNeosPowerAbility);

        // 네오포인트 보유량
        $(".totalNeosPoint").html("포인트 내역 (보유 포인트 : " + user.userNeosPoint + "P)");

    }
/*############################ 유저 정보 END #####################################*/

/*############################ 자료상점 조회 START #####################################*/
    function getList(stores) { //DOM 입력
        // alert("getList")
        // alert(stores.toString())

        let text = "";

        // stores.forEach(store => {
        $(stores.content).each((i, store) => {
            if(store.storeStatus.toString() == "FREE") {
                text += "<div class='Box free'>";
            }else{
                text += "<div class='Box lock'>";

            }
                text += "<a class='storyGridBox' href='javascript:;' target='_self'>";
                text += "<div class='top'>";
                text += "<img src='/images/store/storeNeos.png' alt=''>";
                text += "</div>";
                text += "<div class='bottom'>";
                text += "<div class='titWrap'>";
                text += "<h2 class='titTxt' >" + store.storeTitle + "</h2>";
            if(store.storeStatus.toString() == "FREE") {
                text += "<h3 class='category'>" + store.storeStatus + "</h3>";
            }else{
                text += "<h3 class='category'>네오포인트 : " + store.storePoint + "</h3>";
            }
                text += "</div>";
                text += "<div class='viewWrap'>";
                // text += "<h3 class='categoryDate' text='${#temporals.format(, 'yyyy-MM-dd HH:mm')}'>" + store.createdDate + "</h3>";
                // text += "<p>";
                // text += "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16' fill='none'>";
                // text += "<path d='M8 2.48901C6.27297 2.48988 4.58622 3.01069 3.15934 3.98365C1.73246 4.9566 0.631569 6.33661 0 7.94401C0.630962 9.55179 1.73172 10.9321 3.15875 11.9051C4.58577 12.878 6.27285 13.3985 8 13.3985C9.72715 13.3985 11.4142 12.878 12.8413 11.9051C14.2683 10.9321 15.369 9.55179 16 7.94401C15.3684 6.33661 14.2675 4.9566 12.8407 3.98365C11.4138 3.01069 9.72703 2.48988 8 2.48901V2.48901ZM8 11.58C7.28087 11.58 6.57788 11.3668 5.97995 10.9672C5.38201 10.5677 4.91597 9.99984 4.64077 9.33545C4.36557 8.67106 4.29357 7.93998 4.43386 7.23466C4.57416 6.52935 4.92046 5.88148 5.42896 5.37297C5.93746 4.86447 6.58534 4.51817 7.29065 4.37788C7.99597 4.23758 8.72704 4.30959 9.39144 4.58479C10.0558 4.85999 10.6237 5.32602 11.0232 5.92396C11.4228 6.5219 11.636 7.22488 11.636 7.94401C11.6355 8.90818 11.2522 9.8327 10.5705 10.5145C9.88869 11.1962 8.96416 11.5795 8 11.58ZM8 5.76201C7.56844 5.76201 7.14657 5.88999 6.78775 6.12975C6.42892 6.36951 6.14925 6.71029 5.9841 7.109C5.81894 7.50771 5.77573 7.94643 5.85993 8.3697C5.94412 8.79297 6.15194 9.18176 6.45709 9.48692C6.76225 9.79208 7.15105 9.99989 7.57431 10.0841C7.99758 10.1683 8.43631 10.1251 8.83502 9.95992C9.23372 9.79477 9.57451 9.5151 9.81427 9.15627C10.054 8.79744 10.182 8.37557 10.182 7.94401C10.1824 7.65736 10.1262 7.37344 10.0167 7.10853C9.90719 6.84362 9.74648 6.60292 9.54379 6.40023C9.34109 6.19753 9.10039 6.03682 8.83548 5.9273C8.57057 5.81779 8.28666 5.76162 8 5.76201Z' fill='#A4A4B4'></path>";
                // text += "</svg>";
                // text += "<span class='number'>508</span>";
                // text += "</p>";
                // text += "<p>";
                // text += "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16' fill='none'>";
                // text += "<path d='M13.6 2H2.4C2.02991 2.00185 1.67561 2.15017 1.41457 2.41252C1.15353 2.67487 1.007 3.02991 1.007 3.4L1 16L3.8 13.2H13.6C13.9713 13.2 14.3274 13.0525 14.5899 12.7899C14.8525 12.5274 15 12.1713 15 11.8V3.4C15 3.0287 14.8525 2.6726 14.5899 2.41005C14.3274 2.1475 13.9713 2 13.6 2V2ZM5.9 8.3H4.5V6.9H5.9V8.3ZM8.7 8.3H7.3V6.9H8.7V8.3ZM11.5 8.3H10.1V6.9H11.5V8.3Z' fill='#9D9DAC'></path>";
                // text += "</svg>";
                // text += "<span class='number'>0</span>";
                // text += "</p>";
                text += "</div>";
                text += "</div>";
                text += "</a>";
                text += "<div class='shopLink shop'>";
            if(store.storeStatus.toString() == "FREE") {
                text += "<a href='/store/store-detail?storeId=" + store.storeId + "' class='shopDetail detailBtn'>상세보기</a>";
            }else{
                text += "<a href='/store/store-detail?storeId=" + store.storeId + "' class='lockDetail shopDetail detailBtn'>상세보기</a>";
            }
                text += "</div>";
                text += "</div>";
            $(".noStoreWrap").empty();

        })

        $("div.contetnsGrid").append(text);
        if(stores.last){
            $(".moreButton").hide();
        }
    }

$(".moreButton").on("click", function(e){
    // alert("클릭");
    e.preventDefault();
    if(!globalThis.check){
        globalThis.page = globalThis.page+1;
        // alert("더보기");
        showStore();
    }
});
/*############################## 자료상점 조회 END #######################################*/





























