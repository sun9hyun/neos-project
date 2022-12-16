//페이징 처리및 동적쿼리
globalThis.studyCity = undefined;
globalThis.collegeName = undefined;
globalThis.studyType = undefined;


$("#citySelect_1j").on("change",function () {
    var collegeCity = $("#citySelect_1j option:selected").val();
    globalThis.page = 1;
    if(collegeCity=='전체'){
        globalThis.studyCity = undefined;
        globalThis.collegeName = undefined;
    }else{
        globalThis.studyCity=collegeCity.trim()
        globalThis.collegeName = undefined;
    }
    showList()
    if(collegeCity == '전체'){
        $("#collegeNameSelect_1j option:eq(0)").attr("selected", "selected");
    }
})

$("#collegeNameSelect_1j").on("change",function () {
    globalThis.page = 1;
    var collegeName = $("#collegeNameSelect_1j option:selected").text();
    if(collegeName=='대학교'){
        globalThis.collegeName = undefined;
    }else{
        globalThis.collegeName=collegeName.trim()
    }
    showList()
})
$("#studyTypeSelect_1j").on("change",function () {
    var studyType = $("#studyTypeSelect_1j option:selected").val();
    globalThis.page = 1;
    if(studyType=='모집분야'){
        globalThis.studyType = undefined;
    }else{
        globalThis.studyType=studyType.trim()
    }
    showList()
})






globalThis.page = 1;
showList()
function showList(){
    $.ajax({
        url:"/stu/list?page="+(globalThis.page-1),
        type:"get",
        data:{studyCity:globalThis.studyCity, collegeName:globalThis.collegeName, studyType:globalThis.studyType},
        success:
        show
    })
}

function show(result){
    let text='';
    var count= result.totalElements;
    var end = result.totalPages;
    $(result.content).each((i,item)=>{
        var followCheck = false;
        text+= `<div class="projectGridWrap">`
        text+= `<input type="hidden" name="studyId" class="studyID_1j" value="`+item.studyId+`">`
        text+= `<div class="projectTopInfo">`
        text+= `<div class="top">`
        text+= `<div class="badgeWrap">`
        text+= `<div class="topBadge red">`
        text+= `</div>`
        text+= `<div class="badge black">`
        text+= `<img src="`+item.collegeLogoFile+`"> </div>`
        text+= `</div>`
        $(item.follower).each((i,item)=>{
            if(item.userId==userSessionId){
                followCheck=true;
            }
        })
        if(followCheck){
            text+= `<div class="favorite listFavorite active "></div>`
        }else{
            text+= `<div class="favorite listFavorite off "></div>`
        }
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
        text+= `<span>`+item.studyMemberList.length+`</span>`
        text+= `<span>/</span>`
        text+= `<span>`+item.studySupport+`</span></div>`
        text+= `</div>`
        text+= `</div>`
        text+= `</div>`
        text+= `</div>`
        text+= `</a>`

    })

    $(".projectGridView").html(text);
    pagination(count,end)
}

function pagination(count,end){
    let endNumber = Math.ceil(globalThis.page / 5) * 5;
    let startNumber = endNumber - 4;
    let realEnd = /*Math.ceil(count / 5);*/ end;
    if(endNumber > realEnd){
        endNumber = realEnd == 0 ? '1' : realEnd;
    }

    let prev = startNumber > 1;
    let next = endNumber < realEnd;

    var pageText = '';

    if(prev){
        pageText += `<a class="btn_paging prev screen_out changePage paging" href="` + (startNumber - 1) + `"></a>`
    }
    for (let i=startNumber; i<=endNumber; i++){
        if(globalThis.page == i){
            pageText += `<a class="active changePage">`+i+`</a>`
            continue;
        }
        pageText += `<a class="changePage paging" href="` + i + `">`+i+`</a>`
    }
    if(next){
        pageText += `<a class="btn_paging next screen_out changePage paging" href="` + (endNumber + 1) + `"></a>`
    }

    $(".pagination_1j").html(pageText);
}

$("div.pagingAndBtn").on("click", "a.changePage", function(e){
    e.preventDefault();
    globalThis.page = $(this).attr("href");
    showList();
});

//최근 4개 게시글
getRecent()
function getRecent(){
    $.ajax({
        url:"/stu/recent",
        type:"get",
        success: showRecent
    })
}
function showRecent(result) {
    let recentText= '';
    $(result).each((i,item)=>{
        recentText += `<a href="/study/list/`+item.studyId+`">`
        recentText += `<div class="swiper-slide carousel carousel-slider">`
        recentText += `<input type="hidden" name="studyId" class="studyID_1j" value="`+item.studyId+`">`
        recentText += `<div class="newProjectTopWrap">`
        recentText += `<div class="projectThumbInfo">`
        if(item.studyType =='전공'){
            recentText+= `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_12.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        }else if(item.studyType =='교양'){
            recentText+= `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_01.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        }else if(item.studyType =='공통'){
            recentText+= `<div class="projectThumb"><img src="https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png" alt="커뮤니티 연계 교통 정보 어플"></div>`
        }
        recentText += `<div class="top">`
        recentText += `<div class="badgeWrap"></div>`
        recentText += `<div class="favorite topFavorite off" style="margin-top: 0"></div>`
        recentText += `</div>`
        recentText += `</div>`
        recentText += `<div class="txtWrap">`
        recentText += `<div class="left">`
        recentText += `<h3 class="categoryTxt">`+item.studyType+`</h3>`
        recentText += `<h2 class="tit">`+item.studyTitle+`</h2>`
        recentText += `<h3 class="recruitTxt">`+item.studyContent+`</h3>`
        recentText += `</div>`
        recentText += `<div class="right">`
        recentText += `<div class="heartCount">`
        recentText += `<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 16 16" fill="none" style="position: relative;top: -0.1rem;">
                                                <path d="M8 2.48901C6.27297 2.48988 4.58622 3.01069 3.15934 3.98365C1.73246 4.9566 0.631569 6.33661 0 7.94401C0.630962 9.55179 1.73172 10.9321 3.15875 11.9051C4.58577 12.878 6.27285 13.3985 8 13.3985C9.72715 13.3985 11.4142 12.878 12.8413 11.9051C14.2683 10.9321 15.369 9.55179 16 7.94401C15.3684 6.33661 14.2675 4.9566 12.8407 3.98365C11.4138 3.01069 9.72703 2.48988 8 2.48901V2.48901ZM8 11.58C7.28087 11.58 6.57788 11.3668 5.97995 10.9672C5.38201 10.5677 4.91597 9.99984 4.64077 9.33545C4.36557 8.67106 4.29357 7.93998 4.43386 7.23466C4.57416 6.52935 4.92046 5.88148 5.42896 5.37297C5.93746 4.86447 6.58534 4.51817 7.29065 4.37788C7.99597 4.23758 8.72704 4.30959 9.39144 4.58479C10.0558 4.85999 10.6237 5.32602 11.0232 5.92396C11.4228 6.5219 11.636 7.22488 11.636 7.94401C11.6355 8.90818 11.2522 9.8327 10.5705 10.5145C9.88869 11.1962 8.96416 11.5795 8 11.58ZM8 5.76201C7.56844 5.76201 7.14657 5.88999 6.78775 6.12975C6.42892 6.36951 6.14925 6.71029 5.9841 7.109C5.81894 7.50771 5.77573 7.94643 5.85993 8.3697C5.94412 8.79297 6.15194 9.18176 6.45709 9.48692C6.76225 9.79208 7.15105 9.99989 7.57431 10.0841C7.99758 10.1683 8.43631 10.1251 8.83502 9.95992C9.23372 9.79477 9.57451 9.5151 9.81427 9.15627C10.054 8.79744 10.182 8.37557 10.182 7.94401C10.1824 7.65736 10.1262 7.37344 10.0167 7.10853C9.90719 6.84362 9.74648 6.60292 9.54379 6.40023C9.34109 6.19753 9.10039 6.03682 8.83548 5.9273C8.57057 5.81779 8.28666 5.76162 8 5.76201Z" fill="#A4A4B4"></path>
                                            </svg> <span style="margin-right:0.5rem;margin-left:0.2rem;font-size: 12px;color:#888">`+item.studyView+`</span>`
        recentText += `<img loading="lazy" src="https://letspl.me/assets/images/ic-favorite-empty-white.svg" alt="구독자 수">`
        recentText += `<span>`+item.followTotal+`</span></div>`
        recentText += `<div class="gatherTxt">`
        recentText += `<div>`
        if(item.studyRecruitStatus=='RECRUITING'){
            recentText+= `<span>모집중</span>`
        }else if(item.studyRecruitStatus=='COMPLETE'){
            recentText+= `<span>모집완료</span>`
        }
        recentText+=`<span>`+item.studyMemberList.length+`</span>`
        recentText+=`<span>/</span>`
        recentText+=`<span>`+item.studySupport+`</span></div>`
        recentText += `</div>`
        recentText+=`</a>`
        recentText += `</div>`
        recentText += `</div>`
        recentText += `</div>`
        recentText += `</div>`
    })

    $(".carousel-root").html(recentText)

}

getCollege()
function getCollege(){
    $.ajax({
        url:"/stu/college",
        type:"put",
        success:function(result){
            let text = '';
            $(result).each((i,item)=>{
                text+=`<option value="`+item+`">`+item+`</option>`
            })
            $("#collegeNameSelect_1j").append(text);
        }
    })
}



$("#_next").on("click",".favorite",function(){
    let name = $(this).closest(".projectGridWrap").find(".projectWrap .tit").text();
    let studyId = $(this).closest(".projectTopInfo").prev().val();
    if($(this).hasClass("off")){
        $(".modal1").find(".studyTit").text(name);
        $(".modal1").find("#studyId_1jsd").val(studyId);
        $(".modal1").find("#myId").val(userSessionId);
        $(".modal1").find(".studyAsk").text("구독 하시겠습니까?");
        $(".modalWrapOpen").show();
        $(".modal1").show();
    }else{
        $(".modal1").find(".studyTit").text(name);
        $(".modal1").find("#studyId_1jsd").val(studyId);
        $(".modal1").find("#myId").val(userSessionId);
        $(".modal1").find(".studyAsk").text("구독 취소하시겠습니까?");
        $(".modalWrapOpen").show();
        $(".modal1").show();
    }
})

$(".followBtn_1j").on("click",function () {
    let myIdNumber = $(".modal1").find("#myId").val();
    let studyIdNumber = $(".modal1").find("#studyId_1jsd").val();

    if(!userSessionId){
        alert("로그인 후 구독 가능합니다.");
        $(".modalWrapOpen").hide();
        $(".modal1").hide();
        return;
    }

    $.ajax({
        url:"/study-follow/follow",
        type:"post",
        data:{myId: myIdNumber,studyId:studyIdNumber},
        success:function(result){
            showList();
        }

    })

    $(".modalWrapOpen").hide();
    $(".modal1").hide();
})


