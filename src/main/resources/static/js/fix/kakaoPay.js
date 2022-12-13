/*---------------------------------------------------------------------------------------*/
//IMPORT
// $(".kakaoPay").on("click", function () {
//     //import 결제연동 -> 내 식별 코드 -> 가맹점 식별코드에서 확인 가능
//     var IMP = window.IMP; // 생략 가능
//     // ""안에 띄어쓰기 없이 가맹점 '식별코드'를 붙여주세요
//     // 없으면 결제창이 안뜹미다
//     IMP.init("imp20710076"); // 예: imp00000000
//
//     // IMP.request_pay(param, callback) 결제창 호출
//     //작성 할 값 설명
//     //https://docs.iamport.kr/sdk/javascript-sdk?lang=ko#request_pay
//     IMP.request_pay({ // param
//         pg: 'kakaopay',
//         pay_method: 'kakaopay',
//         //주문번호(중복 불가)
//         //이미 결게가 승인된 번호로는 재결제가 불가하기 때문
//         merchant_uid: 'merchant_' + new Date().getTime(),
//         //주문명
//         name: '네오스 포인트',
//         //model에 담은 정보를 넣어 쓸 수 있음
//         //구매 가격
//         amount: '${pay}',
//         //구매자 Id
//         buyer_name: '${userId}',
//         buyer_tel: '${userPhoneNumber}'
//     }, function (rsp) {
//             console.log(rsp);
//             if (rsp.success) {
//                 var msg = '결제가 완료되었습니다.';
//                 msg += '결제 금액 : ' + rsp.paid_amount;
//                 location.href='결제완료후 갈 url';
//             }else {
//                 var msg = '결제에 실패하였습니다.';
//                 msg += '에러내용 : ' + rsp.error_msg;
//
//             }
//             alert(msg);
//     });
// })

//KAKAO PAY
// $(".kakaoPay").on("click", function () {
//     var user = $("#userId").val();
//     let pay = $(".results").val();
//
// })

//BOOT PAY
import BootPay from "bootpay-js"
