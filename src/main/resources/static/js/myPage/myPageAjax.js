/*
*
* */

console.log("myPageAjax js 들어옴")

globalThis.page = 0;

let myPageService = (function () {

    // 내정보 대학교ID 찾기
    function checkCollegeId(college, callback, error) {
        $.ajax({
            url: "/my-detail/check/" + college,
            type: "post",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // 내정보 수정
    function modify(user, callback, error) {
        $.ajax({
            url: "/my-detail/modify/" + user.userId,
            type: "post",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(user),
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // 알림 조회
    function alarmList(userId, callback, error) {
        $.ajax({
            url: "/my-detail/alarm/" + userId,
            type: "get",
            success: function(result){
                // alert(user);
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // 스터디 지원 현황 조회
    function studySupporterList(userId, callback, error) {
        $.ajax({
            url: "/my-detail/study/" + userId,
            type: "post",
            success: function(result){
                // alert(user);
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // 스터디 참여 조회
    function studyJoinList(userId, callback, error) {
        $.ajax({
            url: "/my-detail/study/" + userId,
            type: "get",
            success: function(result){
                // alert(user);
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // 스터디 구독 조회
    function studyFollowList(userId, callback, error) {
        $.ajax({
            url: "/my-detail/study-follow/" + userId,
            type: "get",
            success: function(result){
                // alert(user);
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // 네오스인 구독 조회
    function followList(userId, callback, error) {
        $.ajax({
            url: "/my-detail/follow/" + userId,
            type: "get",
            success: function(result){
                // alert(user);
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // 네오력 조회
    function neosPointList(userId, callback, error) {
        $.ajax({
            url: "/my-detail/neosPoint/" + userId,
            type: "get",
            success: function(result){
                // alert(user);
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // 네오력 조회
    function neosPowerList(userId, callback, error) {
        $.ajax({
            url: "/my-detail/neosPower/" + userId,
            type: "get",
            success: function(result){
                // alert(user);
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    // 대학교 도시 전체 조회
    function collegeCityInfo(callback, error) {
        $.ajax({
            url: "/my-detail/information/city",
            type: "get",
            success: function(result){
                // alert(user);
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }


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

    function getReplyDate(replyDate){
        let today = new Date();
        let registerDate = new Date(replyDate);
        let gap = today.getTime() - registerDate.getTime();

        let h = registerDate.getHours();
        let mm = registerDate.getMinutes();
        let y = registerDate.getFullYear();
        let m = registerDate.getMonth() + 1;
        let d = registerDate.getDate();

        h = (h < 10 ? '0' : '') + h;
        mm = (mm < 10 ? '0' : '') + mm;
        m = (m < 10 ? '0' : '') + m;
        d = (d < 10 ? '0' : '') + d;

        return [y, m, d].join("-") + "   " +[h, mm].join(":");

    }

    function getDate(replyDate){
        let today = new Date();
        let registerDate = new Date(replyDate);
        let gap = today.getTime() - registerDate.getTime();


        let y = registerDate.getFullYear();
        let m = registerDate.getMonth() + 1;
        let d = registerDate.getDate();

        m = (m < 10 ? '0' : '') + m;
        d = (d < 10 ? '0' : '') + d;

        return [y, m, d].join("-");

    }


    return {getList: getList, userInfo: userInfo, collegeCityInfo: collegeCityInfo, neosPowerList: neosPowerList, getDate: getDate, getReplyDate:getReplyDate, neosPointList: neosPointList, studyFollowList: studyFollowList, studyJoinList:studyJoinList, followList:followList, studySupporterList:studySupporterList, alarmList:alarmList, modify:modify, checkCollegeId:checkCollegeId}
})();