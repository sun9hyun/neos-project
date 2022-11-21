// 무료 체크 시 인풋 입력 막기
$(function(){
    if($("#step1_radio").is(":checked")){
        $(".cashCheck").attr("disabled", true);
    };

    $("#step1_radio").change(function(){
        if($("#step1_radio").is(":checked")){
            $(".cashCheck").attr("disabled", true);
        };
    });
    $("#step2_radio").change(function(){
        if($("#step2_radio").is(":checked")){
            $(".cashCheck").attr("disabled", false);
        };
    });
});
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
