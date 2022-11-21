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



/*--정보 대학교 선택 안함-----------------------------------------------------*/
$(function(){
    $("#uni_radio").change(function(){
        if($("#uni_radio").is(":checked")){
            $(".selectFour").attr("disabled", true);
        };
    });
});


/*--정보 관심태그 선택-----------------------------------------------------*/
var $interest = $(".interestWrap>p");

//최대 5개 조건 추가 해야함
var $interestP = $(".interestWrap>p.active");
var $interestPLenght = $interestP.length;

$interest.click(function () {
    $interestPLenght = $interestP.length+1;
    if($interestPLenght < 6){
        $(this).toggleClass("active");
        $interestP = $(".interestWrap>p.active");
    }
    if($interestPLenght > 5){
        $(this).removeClass("active");
        $interestP = $(".interestWrap>p.active");
    }
});

/*--정보 MBTI 선택-----------------------------------------------------*/
var $mbtiSelect = $(".mbtiSelect");
var $mbtiText = $(".mbtiText");

$mbtiSelect.on("change", function() {
    var $mbti = $(this).val();

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
        $mbtiText.text("사교적이고 타인의 의견을 존중하며, 비판을 받으면 예민하게 반응하기도합니다.");
    }
    if($mbti == "ENTJ"){
        $mbtiText.text("철저한 준비를 하며 활동적입니다.통솔력이 있으며 단호합니다.");
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
