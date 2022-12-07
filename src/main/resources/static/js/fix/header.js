$kakaoBtn = $("button.joinKakao");
$naverBtn = $("button.joinNaver");
$googleBtn = $("button.joinGoogle")


$kakaoBtn.on("click",function () {
    location.href= "https://kauth.kakao.com/oauth/authorize?client_id=e29231bc42552a6803c663f6ba7bad63&redirect_uri=http://localhost:10718/join/kakao&response_type=code";
})
$naverBtn.on("click",function () {
    location.href= "https://nid.naver.com/oauth2.0/authorize?client_id=mCOGR2PgjWmMz5WHuVwE&response_type=code&redirect_uri=http://localhost:10718/join/naver/navercallback";
})
$googleBtn.on("click",function () {
    location.href="https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar&access_type=offline&include_granted_scopes=true&response_type=code&state=state_parameter_passthrough_value&redirect_uri=http://localhost:10718/join/google&client_id=514426667105-1lnfsvd1qiivml6ajencglio5lpvct8d.apps.googleusercontent.com";
})



$KaKaoLoginBtn = $("button.loginKakao");
$NaverLoginBtn = $("button.loginNaver");
$GoogleLoginBtn = $("button.loginGoogle");
$KaKaoLoginBtn.on("click",function () {
    location.href= "https://kauth.kakao.com/oauth/authorize?client_id=e29231bc42552a6803c663f6ba7bad63&redirect_uri=http://localhost:10718/login/kakao&response_type=code";
})
$NaverLoginBtn.on("click",function () {
    location.href= "https://nid.naver.com/oauth2.0/authorize?client_id=mCOGR2PgjWmMz5WHuVwE&response_type=code&redirect_uri=http://localhost:10718/login/naver/navercallback";
})

$GoogleLoginBtn.on("click",function () {
    location.href="https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fdrive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&state=state_parameter_passthrough_value&redirect_uri=http%3A%2F%2Flocalhost%3A10718%2Flogin%2Fgoogle&client_id=514426667105-1lnfsvd1qiivml6ajencglio5lpvct8d.apps.googleusercontent.com&service=lso&o2v=2&flowName=GeneralOAuthFlow";
})


var userIdasdfasdfa = document.querySelector("#userId").value;
show();
function show(){
    $.ajax({
        url: "/user/"+userIdasdfasdfa,
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
