
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

/*--정보 관심태그 선택-----------------------------------------------------*/
var $interest = $(".interestWrap>p");
console.log($interest);
//최대 5개 조건 추가 해야함
$interest.click(function () {
    console.log($interest);
    console.log($(this));

    $(this).toggleClass("active");
});

/*--정보 MBTI 선택-----------------------------------------------------*/
var $mbtiSelect = $(".mbtiSelect");
var $mbtiText = $(".mbtiText").text();

$mbtiSelect.on("change", function() {
    var $mbti = $(this).val();

    console.log($mbti);
    if($mbti == "ISTJ"){
        $mbtiText = "책임감이 강하며 현실적이고 매사에 철저하고 보수적인 성격입니다.";
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