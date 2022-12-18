/* *
*
* */
var $tabMenu = $(".myTabMenu>ul>li");
var $myTabContents = $(".myTabContents");

$tabMenu.click(function () {
    $(this).addClass("active");
    $(this).siblings().removeClass("active");

    var $tabNumber = $(this).index();
    $myTabContents.eq($tabNumber).addClass("active");
    $myTabContents.eq($tabNumber).siblings().removeClass("active");
});

/*--구독 탭메뉴 이동-----------------------------------------------------*/
var $fTabMenu = $(".workTabMenu>ul>li");
var $favoriteTable = $(".favoriteTable");

$fTabMenu.click(function () {
    $(this).addClass("active");
    $(this).siblings().removeClass("active");

    var $fTabNumber = $(this).index();
    $favoriteTable.eq($fTabNumber).addClass("active");
    $favoriteTable.eq($fTabNumber).siblings().removeClass("active");
});

/*--포인트 탭메뉴 이동-----------------------------------------------------*/
var $pTabMenu = $(".payTabmenu>ul>li");
var $payTable = $(".payTable");

$pTabMenu.click(function () {
    $(this).addClass("active");
    $(this).siblings().removeClass("active");

    var $pTabNumber = $(this).index();
    $payTable.eq($pTabNumber).addClass("active");
    $payTable.eq($pTabNumber).siblings().removeClass("active");
})

/*--정보 대학교 선택-----------------------------------------------------*/
var $KRUni = $(".KRUni");

$('.selectUni').attr('disabled',true);
$('.uniEmail').attr('disabled',true);
$('.uniButton').attr('disabled',true);

$KRUni.on("change", function() {
    var $KR = $(this).val();

    if($KR == "KR00"){
        $('.selectUni').attr('disabled',true);
        $('.uniEmail').attr('disabled',true);
        $('.uniButton').attr('disabled',true);
    }else{
        $('.selectUni').removeAttr('disabled');
        $('.uniEmail').removeAttr('disabled');
        $('.uniButton').removeAttr('disabled');
    }
});


/*--정보 대학교 선택 안함-----------------------------------------------------*/
$("#uni_radio").on('click', function(){
    console.log("check들어옴");
    // var $cancelBtn = $("#uni_radio");
    var cancelBtn = document.getElementById('uni_radio');

    if(cancelBtn.checked){
        $('.KRUni').attr('disabled',true);
        $('.selectUni').attr('disabled',true);
        $('.uniEmail').attr('disabled',true);
        $('.uniButton').attr('disabled',true);

    } else{
        $('.KRUni').removeAttr('disabled');
        $('.selectUni').removeAttr('disabled');
        $('.uniEmail').removeAttr('disabled');
        $('.uniButton').removeAttr('disabled');
    }
});



/*--정보 관심태그 선택-----------------------------------------------------*/
var $interest = $(".interestWrap>p");

//최대 5개 조건 추가 해야함
var $interestP = $(".interestWrap>p.active");
var $interestPLength = $interestP.length;
$(".interestAll").val($interestP.text());

$interest.click(function () {
    $interestPLength = $interestP.length+1;
    $(".interestAll").val("");
    let text = ""

    if($interestPLength < 2){
        $(this).toggleClass("active");
        $interestP = $(".interestWrap>p.active");
    }
    if($interestPLength > 1){
        $(this).removeClass("active");
        $interestP = $(".interestWrap>p.active");
    }

    $(".interestAll").val($interestP.text());
});


// $interestTag = $(".interestWrap>p.active").text();
// $interestTag = $(".interestWrap>p.active");
// console.log("-------시작-----------");
// console.log($interestTag.innerText);
// console.log($interestTag[0].innerText);
// console.log($interestTag[1].innerText);
// console.log($interestTag[2].innerText);
// console.log("-------종료-----------");



/*--정보 MBTI 선택-----------------------------------------------------*/
function mbtiTest() {
    var $MBTI = $(".mbtiSelectText").val();
    // var $MBTI = $("select[name='mbtiSelect'] option:selected").val();


    console.log("mbti:" + $MBTI)


    if($MBTI == "ISTJ"){
        $mbtiText.text("책임감이 강하며 현실적이고 매사에 철저하고 보수적인 성격입니다.");
    }
    if($MBTI == "ISFJ"){
        $mbtiText.text("차분하고 헌신적이며 인내심이 강하고 타인의 감정 변화에 주의를 기울입니다.");
    }
    if($MBTI == "INFJ"){
        $mbtiText.text("높은 통찰력으로 사람들에게 영감을 주며 공동체의 이익을 우선으로 합니다.");
    }
    if($MBTI == "INTJ"){
        $mbtiText.text("의지가 강하고 독립적이며 분석력이 뛰어납니다.");
    }
    if($MBTI == "ISTP"){
        $mbtiText.text("과묵하고 분석적이며 적응력이 강합니다.");
    }
    if($MBTI == "ISFP"){
        $mbtiText.text("온화하고 겸손하며 삶의 여유를 만끽합니다.");
    }
    if($MBTI == "INFP"){
        $mbtiText.text("성실하고 이해심 많으며 개방적이며, 잘 표현하지 않으나 내적 신념이 강합니다.");
    }
    if($MBTI == "INTP"){
        $mbtiText.text("지적 호기심이 높으며 잠재력과 가능성을 중요시합니다.");
    }
    if($MBTI == "ESTP"){
        $mbtiText.text("느긋하고 관용적이며 타협을 잘 합니다. 현실적 문제 해결에 능숙합니다.");
    }
    if($MBTI == "ESFP"){
        $mbtiText.text("호기심이 많으며 개발적입니다. 구체적인 사실을 중시합니다.");
    }
    if($MBTI == "ENFP"){
        $mbtiText.text("상상력이 풍부하고 순발력이 뛰어나며 일상적인 활동에 지루함을 느낍니다.");
    }
    if($MBTI == "ENTP"){
        $mbtiText.text("박학다식하고 독창적입니다. 끊임없이 새로운 시도를 합니다.");
    }
    if($MBTI == "ESTJ"){
        $mbtiText.text("체계적으로 일하고 규칙을 준수합니다. 사실적 목표 설정에 능합니다.");
    }
    if($MBTI == "ESFJ"){
        $mbtiText.text("사람에 대한 관심이 많으며 친절하며 동정심이 많습니다.");
    }
    if($MBTI == "ENFJ"){
        $mbtiText.text("사교적이고 타인의 의견을 존중하며, 비판을 받으면 예민하게 반응하기도 합니다.");
    }
    if($MBTI == "ENTJ"){
        $mbtiText.text("철저한 준비를 하며 활동적입니다. 통솔력이 있으며 단호합니다.");
    }
    if($MBTI == "0000"){
        $mbtiText.text("");
    }
};



var $mbtiSelect = $(".mbtiSelect");
var $mbtiText = $(".mbtiText");

$mbtiSelect.on("change", function() {
    var $mbti = $(this).val();
    $(".mbtiSelectText").val($mbti);

    if($mbti == "0000"){
        $mbtiText.text("");
    }
    if($mbti == "ISTJ"){
        $mbtiText.text("책임감이 강하며 현실적이고 매사에 철저하고 보수적인 성격입니다.");
    }
    if($mbti == "ISFJ"){
        $mbtiText.text("차분하고 헌신적이며 인내심이 강하고 타인의 감정 변화에 주의를 기울입니다.");
    }
    if($mbti == "INFJ"){
        $mbtiText.text("높은 통찰력으로 사람들에게 영감을 주며 공동체의 이익을 우선으로 합니다.");
    }
    if($mbti == "INTJ"){
        $mbtiText.text("의지가 강하고 독립적이며 분석력이 뛰어납니다.");
    }
    if($mbti == "ISTP"){
        $mbtiText.text("과묵하고 분석적이며 적응력이 강합니다.");
    }
    if($mbti == "ISFP"){
        $mbtiText.text("온화하고 겸손하며 삶의 여유를 만끽합니다.");
    }
    if($mbti == "INFP"){
        $mbtiText.text("성실하고 이해심 많으며 개방적이며, 잘 표현하지 않으나 내적 신념이 강합니다.");
    }
    if($mbti == "INTP"){
        $mbtiText.text("지적 호기심이 높으며 잠재력과 가능성을 중요시합니다.");
    }
    if($mbti == "ESTP"){
        $mbtiText.text("느긋하고 관용적이며 타협을 잘 합니다. 현실적 문제 해결에 능숙합니다.");
    }
    if($mbti == "ESFP"){
        $mbtiText.text("호기심이 많으며 개발적입니다. 구체적인 사실을 중시합니다.");
    }
    if($mbti == "ENFP"){
        $mbtiText.text("상상력이 풍부하고 순발력이 뛰어나며 일상적인 활동에 지루함을 느낍니다.");
    }
    if($mbti == "ENTP"){
        $mbtiText.text("박학다식하고 독창적입니다. 끊임없이 새로운 시도를 합니다.");
    }
    if($mbti == "ESTJ"){
        $mbtiText.text("체계적으로 일하고 규칙을 준수합니다. 사실적 목표 설정에 능합니다.");
    }
    if($mbti == "ESFJ"){
        $mbtiText.text("사람에 대한 관심이 많으며 친절하며 동정심이 많습니다.");
    }
    if($mbti == "ENFJ"){
        $mbtiText.text("사교적이고 타인의 의견을 존중하며, 비판을 받으면 예민하게 반응하기도 합니다.");
    }
    if($mbti == "ENTJ"){
        $mbtiText.text("철저한 준비를 하며 활동적입니다. 통솔력이 있으며 단호합니다.");
    }

});

/*--정보 프로필 이미지 변경-----------------------------------------------------*/
let $tempTr;
var $file = $("#ImgBtn");
var $userImg = $(".userImg");

$tempTr = $userImg.attr('src');

$file.change(function(e){
    var reader = new FileReader();

    reader.readAsDataURL(e.target.files[0]);
    reader.onload = function(e){
        let url = e.target.result;

        if(url.includes('image')){
            $file.attr('src', url);
            $userImg.attr('src', url);
        }else{
            console.log("이미지가 아님");
            alert("이미지 파일만 가능합니다.");
            $userImg.attr('src', $tempTr);
            console.log($userImg.attr('src', $tempTr));
        }
    };
});


/*--모달 관련-----------------------------------------------------*/
$(".updateBtn").on("click", function () {
    $(".updateOkModal").css("display", "block");
})

$(".quitBtn").on("click", function () {
    $(".quitModal").css("display", "block");
})

$(".closeBtn").on("click", function () {
    $(".updateOkModal").css("display", "none");
})

$(".okBtn").on("click", function () {
    $(".updateOkModal").css("display", "none");
})

$(".closeBtn").on("click", function () {
    $(".quitModal").css("display", "none");
})

$(".redBtn").on("click", function () {
    $(".quitModal").css("display", "none");
})

$(".whiteBtn").on("click", function () {
    $(".quitModal").css("display", "none");
})


/*--정보 선호 지역 설정-----------------------------------------------------*/
var $otoo= $(".otoo");

$otoo.on("change", function() {
    var $online = $(this).val();

    if($online == "01"){
        $('.areaSort').attr('disabled',true);
    }else{
        $('.areaSort').removeAttr('disabled');
    }
});

/*-----------------------------자료상점----------------------------------*/
var $lock = $(".lock");
var $free = $(".free");
var $top = $(".top");
var $topImg = $(".top img");
var $shopLink = $(".shopLink");

console.log("storedList 들어옴");

// $lock.hover(function(){
//     $(this).find($top).css("filter", "brightness(0.6)");
//     $(this).find($shopLink).toggleClass("shopLink");
// },function(){
//     $(this).find($top).css("filter", "brightness(1)");
//     $(this).find($shopLink).toggleClass("shopLink");
// });

$('.contetnsGrid').on("mouseover", ".free" ,function(){
    $(this).find(".top").css("filter", "brightness(0.6)");
    $(this).find(".shop").removeClass("shopLink");
})

$('.contetnsGrid').on("mouseout", ".free" ,function(){
    $(this).find(".top").css("filter", "brightness(1)");
    $(this).find(".shop").addClass("shopLink");
})

$('.contetnsGrid').on("mouseover", ".lock" ,function(){
    $(this).find(".top").css("filter", "brightness(0.6)");
    $(this).find(".shop").removeClass("shopLink");
})

$('.contetnsGrid').on("mouseout", ".lock" ,function(){
    $(this).find(".top").css("filter", "brightness(1)");
    $(this).find(".shop").addClass("shopLink");
})

console.log($('.userImg').attr("src"));

// $('.userImg').click(function () {
//     const test = atob( $(this).attr("src"));
//     console.log(test);
// })'

/*-----------------------------프로필 사진 수정----------------------------------*/


$("input[name='userFileInput']").on("change", function(){
    console.log("이미지 체인지 들어옴");
    let formData = new FormData();
    let files = $(this)[0].files;

    $(files).each(function(i, file){
        formData.append("upload", file);
    });
    console.log("이미지 체인지 들어옴22");

    $.ajax({
        url: "/my-detail/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (realName) {
            console.log("이미지 체인지 들어옴333");
            let text = "";
            let path = "/upload/" + realName;
            text += `<input type="text" name="userFile" value="` + realName + `">`;
            $(".left").append(text);
        }
    });
});

