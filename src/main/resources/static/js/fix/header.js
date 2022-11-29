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
