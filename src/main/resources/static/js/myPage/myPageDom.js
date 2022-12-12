/*
*
* */
    let userId = $("input[name='userId']").val();

    show();

    function show() {
        // alert("show");
        // alert(userId);
        myPageService.userInfo(userId, userInfo)
        myPageService.getList(userId, getList)
    }

    function userInfo(user) {

        // 프로필 이미지
        $(".userImg").attr("src", user.userFile);
        // OAuth 이메일
        $("input[name='userOAuthEmail']").val(user.userOAuthEmail);
        // 유저 닉네임
        $("input[name='userNickName']").val(user.userNickName);
        // 유저 핸드폰 번호
        $("input[name='userPhoneNumber']").val(user.userPhoneNumber);
        // 유저
        let collegeCityText = "";
        collegeCityText = user.collegeCity;
        $(".KRUni").val(user.collegeCity).prop("selected", true);
        // let Text = "";
        // let Text = "";
        // let Text = "";
        // let Text = "";
        // let Text = "";
        // let Text = "";
        // let Text = "";


        // $("div.contetnsGrid").append(text);
    }




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
                text += "<p>";
                text += "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16' fill='none'>";
                text += "<path d='M8 2.48901C6.27297 2.48988 4.58622 3.01069 3.15934 3.98365C1.73246 4.9566 0.631569 6.33661 0 7.94401C0.630962 9.55179 1.73172 10.9321 3.15875 11.9051C4.58577 12.878 6.27285 13.3985 8 13.3985C9.72715 13.3985 11.4142 12.878 12.8413 11.9051C14.2683 10.9321 15.369 9.55179 16 7.94401C15.3684 6.33661 14.2675 4.9566 12.8407 3.98365C11.4138 3.01069 9.72703 2.48988 8 2.48901V2.48901ZM8 11.58C7.28087 11.58 6.57788 11.3668 5.97995 10.9672C5.38201 10.5677 4.91597 9.99984 4.64077 9.33545C4.36557 8.67106 4.29357 7.93998 4.43386 7.23466C4.57416 6.52935 4.92046 5.88148 5.42896 5.37297C5.93746 4.86447 6.58534 4.51817 7.29065 4.37788C7.99597 4.23758 8.72704 4.30959 9.39144 4.58479C10.0558 4.85999 10.6237 5.32602 11.0232 5.92396C11.4228 6.5219 11.636 7.22488 11.636 7.94401C11.6355 8.90818 11.2522 9.8327 10.5705 10.5145C9.88869 11.1962 8.96416 11.5795 8 11.58ZM8 5.76201C7.56844 5.76201 7.14657 5.88999 6.78775 6.12975C6.42892 6.36951 6.14925 6.71029 5.9841 7.109C5.81894 7.50771 5.77573 7.94643 5.85993 8.3697C5.94412 8.79297 6.15194 9.18176 6.45709 9.48692C6.76225 9.79208 7.15105 9.99989 7.57431 10.0841C7.99758 10.1683 8.43631 10.1251 8.83502 9.95992C9.23372 9.79477 9.57451 9.5151 9.81427 9.15627C10.054 8.79744 10.182 8.37557 10.182 7.94401C10.1824 7.65736 10.1262 7.37344 10.0167 7.10853C9.90719 6.84362 9.74648 6.60292 9.54379 6.40023C9.34109 6.19753 9.10039 6.03682 8.83548 5.9273C8.57057 5.81779 8.28666 5.76162 8 5.76201Z' fill='#A4A4B4'></path>";
                text += "</svg>";
                text += "<span class='number'>508</span>";
                text += "</p>";
                text += "<p>";
                text += "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16' fill='none'>";
                text += "<path d='M13.6 2H2.4C2.02991 2.00185 1.67561 2.15017 1.41457 2.41252C1.15353 2.67487 1.007 3.02991 1.007 3.4L1 16L3.8 13.2H13.6C13.9713 13.2 14.3274 13.0525 14.5899 12.7899C14.8525 12.5274 15 12.1713 15 11.8V3.4C15 3.0287 14.8525 2.6726 14.5899 2.41005C14.3274 2.1475 13.9713 2 13.6 2V2ZM5.9 8.3H4.5V6.9H5.9V8.3ZM8.7 8.3H7.3V6.9H8.7V8.3ZM11.5 8.3H10.1V6.9H11.5V8.3Z' fill='#9D9DAC'></path>";
                text += "</svg>";
                text += "<span class='number'>0</span>";
                text += "</p>";
                text += "</div>";
                text += "</div>";
                text += "</a>";
                text += "<div class='shopLink shop'>";
            if(store.storeStatus.toString() == "FREE") {
                text += "<a href='/store/store-detail?storeId=" + store.storeId + "' class='shopDetail detailBtn'>상세보기</a>";
            }else{
                text += "<a href='javascript:;' class='lockCash shopDetail'>결제하기</a>";
                text += "<a href='/store/store-detail?storeId=" + store.storeId + "' class='lockDetail shopDetail detailBtn'>상세보기</a>";
            }
                text += "</div>";
                text += "</div>";
        })

        $("div.contetnsGrid").append(text);
    }

$(".moreButton").on("click", function(e){
    // alert("클릭");
    e.preventDefault();
    if(!globalThis.check){
        globalThis.page = globalThis.page+1;
        // alert("더보기");
        show();
    }
});
/*############################## 자료상점 조회 END #######################################*/





























