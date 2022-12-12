/*
*
* */

console.log("myPageAjax js 들어옴")

globalThis.page = 0;

let myPageService = (function () {
    
    // 로그인 회원 정보 조회
    function userInfo(userId, callback, error) {
        $.ajax({
            url: "/my-detail/information/" + userId,
            type: "get",
            success: function(user, status, xhr){
                // alert(user);
                if(callback){
                    callback(user);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }


    // 내가 작성한 자료상점 게시글 조회
    function getList(userId, callback, error){
        $.ajax({
            url: "/my-detail/store/" + userId +"?page=" + (globalThis.page),
            // url: "/my-detail/store/" + userId,
            type: "get",
            success: function(stores, status, xhr){
                if(callback){
                    // alert("들어옴" + userId + "page: " +(globalThis.page) )
                    callback(stores);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }


    return {getList: getList, userInfo: userInfo}
})();