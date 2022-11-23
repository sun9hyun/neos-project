/* 해당사항 없음 체크 할때 본 캐릭터 설정 막아줌 */
$('#checkB').on('click', function(){
    var cancelBtn = document.getElementById('checkB');

    if( cancelBtn.checked ){
        $('.checkBtn').attr('disabled',true)
    } else{
        $('.checkBtn').removeAttr('disabled')
    }
})


/* 정보 대학교 선택*/

var $KRUni = $(".KRUni");

$('.selectUni').attr('disabled',true);
$('.uniEmail').attr('disabled',true);

$KRUni.on("change", function() {
    var $KR = $(this).val();

    if($KR == "KR00"){
        $('.selectUni').attr('disabled',true);
        $('.uniEmail').attr('disabled',true);
    }else{
        $('.selectUni').removeAttr('disabled');
        $('.uniEmail').removeAttr('disabled');
    }
});

