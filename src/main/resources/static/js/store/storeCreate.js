// 무료 체크 시 인풋 입력 막기
$(function(){
    if($("#step1_radio").is(":checked")){
        $(".cashCheck").attr("readonly", true);
        $(".storeStatus").val("FREE")
    };

    $("#step1_radio").change(function(){
        if($("#step1_radio").is(":checked")){
            $(".cashCheck").attr("readonly", true);
            $(".storeStatus").val("FREE")
            $(".cashCheck").val("");
        };
    });
    $("#step2_radio").change(function(){
        if($("#step2_radio").is(":checked")){
            $(".cashCheck").attr("readonly", false);
            $(".storeStatus").val("PAY")
        };
    });
});


/*-------------------------------------------------------------------------------------------------------------*/
// 모달 종료
$(".closeBtnImg").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

$(".blueBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
});

$(".modal4 .blueBtn").on("click",function(){
    $(".modalWrap").css('display','none');
    $(".modalWrapOpen").hide();
    storeForm.submit();
});

// 작성완료 버튼 클릭
function submit() {
    console.log($(".storeStatus").val());
    console.log($(".cashCheck").val());
    console.log($(".textInput").val());
    console.log($(".storeContent").val());
    console.log($(".cashCheck").val().length);

    if($(".textInput").val().length < 3){ // 제목 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');
    }else if($(".storeContent").val().length < 1){ // 내용 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal3").css('display','inline-block');
    }else if($(".storeStatus").val() == "FREE"){  // 작성 완료
        $(".cashCheck").val("0");
        $(".modalWrapOpen").show();
        $(".modal4").css('display','inline-block');
    }else if($(".cashCheck").val().length < 4){ // 최소 포인트 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal1").css('display','inline-block');
    }else if($(".cashCheck").val().length >= 4){
        $(".modalWrapOpen").show();
        $(".modal4").css('display','inline-block');
    }
}
/*-------------------------------------------------------------------------------------------------------------*/
var $file = $(".file");
var $fileText = $(".fileText").text();

let $tempTr;
var $userImg = $(".thumbnailImage2");

$tempTr = $userImg.attr('src');

$file.on('change',function(e){
    var $fileName = $(this).val();
    var $fileNameText = $(this).next().next().children($("li"));

    var $img = $(this).parent(".uploadBox").prev(".thumbnailBox").find($userImg);
    var $imgBox = $(this).parent(".uploadBox").prev(".thumbnailBox");

    //첨부파일 확장자 체크
    var ext = $fileName.split('.').pop().toLowerCase(); //확장자분리
    //아래 확장자가 있는지 체크
    if($.inArray(ext, ['hwp', 'pdf', 'png', 'jpg', 'jpeg', 'png', 'ppt', 'pptx', 'xlsx', 'xls', 'docx', 'txt']) == -1) {
        alert('"hwp, pdf, png, jpg, jpeg, png, ppt, pptx, xlsx, xls, docx, txt" 파일만 업로드 할수 있습니다.');
        $fileNameText.text($fileText);
        $img.attr('src', $tempTr);
        $img.css('width', '');
        $img.css('height', '');
        $imgBox.css('background-color', '#f5f5f5');
        return;
    }

    // 첨부 파일 이름으로 변경
    if(!$fileName){
        $fileNameText.text($fileText);

        $img.attr('src', $tempTr);
        $img.css('width', '');
        $img.css('height', '');
        $imgBox.css('background-color', '#f5f5f5');
    }else {
        $(this).next().next().children($("li")).text($fileName);

        $(this).toggleClass("active");
        $(this).prev("label").toggleClass("active");
        $(this).next(".fileButton").toggleClass("active");

        //썸네일 변경
        if(ext == 'hwp' || ext =='docx'){
            $img.attr('src', '/images/store/word.png');
        }
        if(ext == 'pptx' || ext =='ppt'){
            $img.attr('src', '/images/store/ppt.png');
        }
        if(ext == 'xlsx' || ext == 'xls'){
            $img.attr('src', '/images/store/excel.png');
        }
        if(ext == 'jpg' || ext == 'jpeg'){
            $img.attr('src', '/images/store/jpg.png');
        }
        if(ext == 'png'){
            $img.attr('src', '/images/store/png.png');
        }
        if(ext == 'pdf'){
            $img.attr('src', '/images/store/pdf.png');
        }
        if(ext == 'txt'){
            $img.attr('src', '/images/store/txt.png');
        }

        $img.css('width', '90%');
        $img.css('height', '90%');
        $imgBox.css('background-color', '#fff');
    }

});

var $cancel = $(".cancel");

$cancel.on("click", function () {
    var $fileNameText = $(this).next().children($("li"));

    var $img = $(this).parent(".uploadBox").prev(".thumbnailBox").find($userImg);
    var $imgBox = $(this).parent(".uploadBox").prev(".thumbnailBox");

    $(this).toggleClass("active");
    $(this).prev().prev("label").toggleClass("active");
    $(this).prev(".file").toggleClass("active");

    $fileNameText.text($fileText);

    $img.attr('src', $tempTr);
    $img.css('width', '');
    $img.css('height', '');
    $imgBox.css('background-color', '#f5f5f5');
});
