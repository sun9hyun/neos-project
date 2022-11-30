$kakaoBtn = $("button.joinKakao");
$naverBtn = $("button.joinNaver");
$googleBtn = $("button.joinGoogle")


$kakaoBtn.on("click",function () {
    location.href= "https://kauth.kakao.com/oauth/authorize?client_id=e29231bc42552a6803c663f6ba7bad63&redirect_uri=http://localhost:10718/join/kakao&response_type=code&prompt=login";
})
$naverBtn.on("click",function () {
    location.href= "https://nid.naver.com/oauth2.0/authorize?client_id=mCOGR2PgjWmMz5WHuVwE&response_type=code&redirect_uri=http://localhost:10718/join/naver/navercallback";
})



$KaKaoLoginBtn = $("button.loginKakao");
$KaKaoLoginBtn.on("click",function () {
    location.href= "https://kauth.kakao.com/oauth/authorize?client_id=e29231bc42552a6803c663f6ba7bad63&redirect_uri=http://localhost:10718/login/kakao&response_type=code&prompt=login";
})

var userId = document.querySelector("#userId").value;
function show(){
    $.ajax({
        url: "/user/"+userId,
        type: "get",
        success: getInfo,
        error: function(xhr, status, err){
           alert("d")
        }
    })
}

function getInfo(result){
    var userName = result.userNickName;
    var userPoint = result.userNeosPoint;
    var userNeosBadge = result.userNeosBadge;
    var userProfile = result.userFile;
    var userCollegeName = result.collegeName;
    var userOauthId = result.userOAuthId;

    const $nameTag = $("#userName");
    const $pointTag = $("#neosPoint");
    const $userNeosBadge = $("#userLevel");
    const $userProfileImg = $("#userProfileImg");
    const $userCollege = $("#userCollege");
    const $userOauthId = $("#userOauthId")

    $nameTag.text(userName);
    $pointTag.text(userPoint);
    $userNeosBadge.attr("src",userNeosBadge)
    $userProfileImg.attr("src",userProfile);
    $userOauthId.val(userOauthId);
    if(userCollegeName==null){
        $userCollege.text("학생")
    }else{
        $userCollege.text(userCollegeName)
    }

}
