/*
*
*
* */

// 서비스에 대한 기능들을 하나의 모듈로 묶어서 처리한다.
let communityService = (function() {
    function timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금전';
        if (betweenTime < 60) {
            return `${betweenTime}분전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년전`;
    }

    return {timeForToday:timeForToday}
})();

// function getList(callback, error){
//     $.ajax({
//             url: "/community/communityList",
//             type: "get",
//             async: false,
//             success: function (communityDTOS) {
//                 if(communityDTOS != null){
//                     getList(communityDTOS);
//                 }
//             },
//             error: function (xhr, status, err) {
//                 console.log(xhr, status, err);
//             }
//         });
// }