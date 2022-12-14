/*
*
*/

const $detail = $("a.detailBtn");
const $change = $("a.changeBtn");

$detail.on("click", function (e) {
    e.preventDefault();
    location.href = "/store/store-delete" + "?storeId=" + $(this).attr("href");
})

$change.on("click", function (e) {
    e.preventDefault();
    location.href = "/store/store-update" + "?storeId=" + $(this).attr("href");
})

// 첨부파일 이미지 변경
var $attachFile1 = $(".attachFile1");
var $attachFile2 = $(".attachFile2");
var $attachFile3 = $(".attachFile3");


changeImg1();
changeImg2();
changeImg3();

function changeImg1() {
    let $fileName = $attachFile1.val().split('.').pop().toLowerCase();
    let $img = $attachFile1.parent().children().children(".fileImg");


    if($fileName == 'hwp' || $fileName =='docx'){
        $img.attr('src', '/images/store/word.png');
    }
    if($fileName == 'pptx' || $fileName =='ppt'){
        $img.attr('src', '/images/store/ppt.png');
    }
    if($fileName == 'xlsx' || $fileName == 'xls'){
        $img.attr('src', '/images/store/excel.png');
    }
    if($fileName == 'jpg' || $fileName == 'jpeg'){
        $img.attr('src', '/images/store/jpg.png');
    }
    if($fileName == 'png'){
        $img.attr('src', '/images/store/png.png');
    }
    if($fileName == 'pdf'){
        $img.attr('src', '/images/store/pdf.png');
    }
    if($fileName == 'txt'){
        $img.attr('src', '/images/store/txt.png');
    }
}

function changeImg2() {
    let $fileName = $attachFile2.val().split('.').pop().toLowerCase();
    let $img = $attachFile2.parent().children().children(".fileImg");


    if($fileName == 'hwp' || $fileName =='docx'){
        $img.attr('src', '/images/store/word.png');
    }
    if($fileName == 'pptx' || $fileName =='ppt'){
        $img.attr('src', '/images/store/ppt.png');
    }
    if($fileName == 'xlsx' || $fileName == 'xls'){
        $img.attr('src', '/images/store/excel.png');
    }
    if($fileName == 'jpg' || $fileName == 'jpeg'){
        $img.attr('src', '/images/store/jpg.png');
    }
    if($fileName == 'png'){
        $img.attr('src', '/images/store/png.png');
    }
    if($fileName == 'pdf'){
        $img.attr('src', '/images/store/pdf.png');
    }
    if($fileName == 'txt'){
        $img.attr('src', '/images/store/txt.png');
    }
}

function changeImg3() {
    let $fileName = $attachFile3.val().split('.').pop().toLowerCase();
    let $img = $attachFile3.parent().children().children(".fileImg");


    if($fileName == 'hwp' || $fileName =='docx'){
        $img.attr('src', '/images/store/word.png');
    }
    if($fileName == 'pptx' || $fileName =='ppt'){
        $img.attr('src', '/images/store/ppt.png');
    }
    if($fileName == 'xlsx' || $fileName == 'xls'){
        $img.attr('src', '/images/store/excel.png');
    }
    if($fileName == 'jpg' || $fileName == 'jpeg'){
        $img.attr('src', '/images/store/jpg.png');
    }
    if($fileName == 'png'){
        $img.attr('src', '/images/store/png.png');
    }
    if($fileName == 'pdf'){
        $img.attr('src', '/images/store/pdf.png');
    }
    if($fileName == 'txt'){
        $img.attr('src', '/images/store/txt.png');
    }
}

$(".replyStatusCheck").change(function(){
    console.log("들어옴")
    if($(".replyStatusCheck").is(":checked")){
        $(".replyStatus").val("PRIVATE")
    }else {
        $(".replyStatus").val("PUBLIC")
    };
});

// 댓글 상태 변경
// 무료 체크 시 인풋 입력 막기
$(function(){
    $(".replyStatusCheck").change(function(){
        console.log("들어옴")
        if($(".replyStatusCheck").is(":checked")){
            $(".replyStatus").val("PRIVATE")
        }else {
            $(".replyStatus").val("PUBLIC")
        };
    });
});

