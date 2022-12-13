/*
*
*
* */
// async function kakaoPay() {
//     const response = await Bootpay.requestPayment({
//         "application_id": "6396df69d01c7e001bfeb951",
//         "price": $(".results").text(),
//         "order_name": "NEOS",
//         "order_id": 'order_id_' + new Date().getTime(),
//         "pg": "카카오페이",
//         "method": "카카오페이",
//         "tax_free": 0,
//         "user": {
//             "id": $("#user").val()
//         },
//         "items": [
//             {
//                 "id": 'id_' + new Date().getTime(),
//                 "name": "Neos Point",
//                 "qty": 1,
//                 "price": $(".results").text()
//             }
//         ],
//         "extra": {
//             "open_type": "iframe",
//             "card_quota": "0,2,3",
//             "seller_name": "NEOS",
//             "escrow": false
//         }
//     })
// }
//
// async function publicPay() {
//     const response = await Bootpay.requestPayment({
//         "application_id": "6396df69d01c7e001bfeb951",
//         "price": $(".results").text(),
//         "order_name": "NEOS",
//         "order_id": 'order_id_' + new Date().getTime(),
//         "pg": "케이씨피",
//         "method": "카드",
//         "user": {
//             "id": $("#user").val()
//         },
//         "items": [
//             {
//                 "id": 'id_' + new Date().getTime(),
//                 "name": "Neos Point",
//                 "qty": 1,
//                 "price": $(".results").text()
//             }
//         ],
//         "extra": {
//             "open_type": "iframe",
//             "card_quota": "0,2,3",
//             "seller_name": "NEOS",
//             "escrow": false
//         }
//     })
//     switch (response.event) {
//         case 'done':
//             // 결제 완료 처리
//             pointSave({
//                 neosPointMoney: response.data.price,
//                 neosPointContent: "포인트충전",
//                 userId: $('#userId').val()
//             });
//             break;
//     }
// }
//
//
// function pointSave(pay) {
//     $.ajax({
//         url: "/pay/chargingOk",
//         type: "post",
//         data: JSON.stringify(pay),
//         contentType: "application/json; charset=utf-8",
//         success: function() {
//                 showPay();
//         },
//         error: function (xhr, status, err) {
//             console.log(xhr, status, err);
//         }
//     });
// }
//
// $(".kakaoPay").on("click", function () {
//     kakaoPay();
// })
//
// $(".public").on("click", function () {
//     publicPay();
// })