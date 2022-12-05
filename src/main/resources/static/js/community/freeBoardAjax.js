/*
*
*
* */

// 서비스에 대한 기능들을 하나의 모듈로 묶어서 처리한다.
let communityService = (function() {
    function getList(callback, error){
        $.ajax({
            url: "/community/community",
            type: "get",
            success: function(communityDTO, status, xhr){
                if(callback){
                    callback(communityDTO);
                }
            },
            error: function(xhr, status, err){
                if(error){
                    error(err);
                }
            }
        });
    }

    function add(reply, callback, error) {
        $.ajax({
            url: "/reply/new",
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    return {getList:getList, add: add}
})
