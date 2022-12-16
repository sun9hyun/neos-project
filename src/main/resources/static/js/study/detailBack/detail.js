$(".joinBtn_1j").on("click",function () {
    $("#joinForm").submit();

})

globalThis.size = 5;

showNews();
function showNews(){
    $.ajax({
        url:"/news/"+studyId+"?page=0&size="+globalThis.size,
        type:"get",
        success: newsList
    })
}

function newsList(result){
    console.log(result);
    let text = '';
    $(result.content).each((i,item)=>{
        if(item.category == 'member'){
            text += `<li>`
            text += `<div class="processStatus "><span>완료</span></div>`
            text += `<p class="milestoneDate">`+item.newsCreatedTime
            text += `</p>`
            text += `<h3 class="milestoneTxt"><span>`+item.studyMember.userDTO.userNickName+`</span>님이 스터디의 멤버가 되셨습니다.</h3>`
            text += `</li>`
        }else if(item.category =='mileStone'){

            text += `<li>`
            if(item.studyMilestone.studyMilestoneStatus=='FINISH'){
                text += `<div class="processStatus "><span>완료</span></div>`
            }else{
                text += `<div class="processStatus active"><span>진행 중</span></div>`
            }
            text += `<p class="milestoneDate">`+item.newsCreatedTime
            text += `</p>`
            if(item.studyMilestone.studyMilestoneStatus=='FINISH'){
                text += `<h3 class="milestoneTxt">목표 [<span>`+item.studyMilestone.studyMileStoneTitle+`</span>]을 완료하였습니다</h3>`
            }else{
                text += `<h3 class="milestoneTxt">목표 [<span>`+item.studyMilestone.studyMileStoneTitle+`</span>]을 진행중입니다</h3>`
            }

            text += `</li>`


        }else if(item.category == 'work'){
            text += `<li>`
            if(item.studyWork.studyWorkStatus=='FINISH'){
                text += `<div class="processStatus "><span>완료</span></div>`
            }else{
                text += `<div class="processStatus active"><span>진행 중</span></div>`
            }
            text += `<p class="milestoneDate">`+item.newsCreatedTime
            text += `</p>`
            if(item.studyWork.studyWorkStatus=='FINISH'){
                text += `<h3 class="milestoneTxt">할 일 [<span>`+item.studyWork.studyWorkContent+`</span>]을 완료하였습니다</h3>`
            }else{
                text += `<h3 class="milestoneTxt">할 일 [<span>`+item.studyWork.studyWorkContent+`</span>]을 진행중입니다</h3>`
            }
            text += `</li>`
        }
    })
    $(".newsListWrapper").html(text);
    if(result.last){
        $(".moreBtnWrapUl").hide();
    }

}


$(".moreBtnWrapUl").on("click",function () {
    globalThis.size=  globalThis.size+5;
    showNews()
})
