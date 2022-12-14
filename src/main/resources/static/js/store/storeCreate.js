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
    console.log($(".addFile").length);

    if($(".textInput").val().length < 3){ // 제목 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal2").css('display','inline-block');
    }else if($(".addFile").length < 1){ // 자료 조건 미충족
        $(".modalWrapOpen").show();
        $(".modal5").css('display','inline-block');
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
var $fileText = $(".baseText").text();

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

        // $(this).next().next().children($("li")).text($fileName);

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
    $(this).prev(".file").val("");
    //
    $fileNameText.text($fileText);

    $img.attr('src', $tempTr);
    $img.css('width', '');
    $img.css('height', '');
    $imgBox.css('background-color', '#f5f5f5');
});
/*------------------------------------------------------------------*/
// let arrayFile = [];


// 첨부파일 추가하는 경우
$("input[name='upload1']").on("change", function(){
    let formData = new FormData();
    let files = $(this)[0].files;

    $(files).each(function(i, file){
        formData.append("upload", file);
    });

    $.ajax({
        url: "/file/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (files) {
            let text1 = "";
            let text2 = "";
            let text3 = "";
            $(files).each(function(i, file){
                console.log("여기 확인 필요");
                console.log(file);

                text1 += file.storeFileName +`(` + parseInt(file.storeFileSize / 1024) +`KB)`;

                text2 += `<div class="fileVal1 addFile" type="hidden" data-file-size="` + file.storeFileSize + `" data-file-name="` + file.storeFileName + `" data-file-upload-path="` + file.storeFilePath + `" data-file-uuid="` + file.storeFileUuid + `">`;
                text2 += `</div>`;

                text3 += `<div type="hidden" class="fileData1">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[0].storeFileName" value="` + file.storeFileName + `">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[0].storeFilePath" value="` + file.storeFilePath + `">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[0].storeFileUuid" value="` + file.storeFileUuid + `">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[0].storeFileSize" value="` + file.storeFileSize + `">`;
                text3 += `</div>`
            });

            $(".uploadResult1").append(text2);
            $(".uploadResult1").children(".fileText1").html(text1);
            $(".fileDataWrap").append(text3);

        }
    });
});

// 등록 취소 하는 경우
$(".cancelBtn1").on("click", function(){
    const $div1 = $(this).next().children(".fileVal1");
    const $data1 = $(".fileDataWrap").children(".fileData1");
    // let i = $(".uploadResult ul span").index($(this));
    let uploadPath = $div1.data("file-upload-path");
    let fileName = $div1.data("file-uuid") + "_" + $div1.data("file-name");
    console.log($div1);
    console.log(uploadPath);
    console.log(fileName);
    $.ajax({
        url: "/file/delete",
        type: "post",
        data: {uploadPath: uploadPath, fileName: fileName},
        success: function(){
            $div1.remove();
            $data1.remove();
            // arrayFile.splice(i, 1);
            // const dataTransfer = new DataTransfer();
            // arrayFile.forEach(file => dataTransfer.items.add(file));
            // $("input[name='upload']")[0].files = dataTransfer.files;
        }
    });
});

/*-------------------------------------첨부2----------------------------------*/
// 첨부파일 추가하는 경우
$("input[name='upload2']").on("change", function(){
    let formData = new FormData();
    let files = $(this)[0].files;

    $(files).each(function(i, file){
        formData.append("upload", file);
    });

    $.ajax({
        url: "/file/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (files) {
            let text1 = "";
            let text2 = "";
            let text3 = "";
            $(files).each(function(i, file){
                console.log("여기 확인 필요");
                console.log(file);

                text1 += file.storeFileName +`(` + parseInt(file.storeFileSize / 1024) +`KB)`;

                text2 += `<div class="fileVal2 addFile" type="hidden" data-file-size="` + file.storeFileSize + `" data-file-name="` + file.storeFileName + `" data-file-upload-path="` + file.storeFilePath + `" data-file-uuid="` + file.storeFileUuid + `">`;
                text2 += `</div>`;

                text3 += `<div type="hidden" class="fileData2">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[1].storeFileName" value="` + file.storeFileName + `">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[1].storeFilePath" value="` + file.storeFilePath + `">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[1].storeFileUuid" value="` + file.storeFileUuid + `">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[1].storeFileSize" value="` + file.storeFileSize + `">`;
                text3 += `</div>`
            });

            $(".uploadResult2").append(text2);
            $(".uploadResult2").children(".fileText2").html(text1);
            $(".fileDataWrap").append(text3);

        }
    });
});

// 등록 취소 하는 경우
$(".cancelBtn2").on("click", function(){
    const $div2 = $(this).next().children(".fileVal2");
    const $data2 = $(".fileDataWrap").children(".fileData2");
    // let i = $(".uploadResult ul span").index($(this));
    let uploadPath = $div2.data("file-upload-path");
    let fileName = $div2.data("file-uuid") + "_" + $div2.data("file-name");
    console.log($div2);
    console.log(uploadPath);
    console.log(fileName);
    $.ajax({
        url: "/file/delete",
        type: "post",
        data: {uploadPath: uploadPath, fileName: fileName},
        success: function(){
            $div2.remove();
            $data2.remove();
            // arrayFile.splice(i, 1);
            // const dataTransfer = new DataTransfer();
            // arrayFile.forEach(file => dataTransfer.items.add(file));
            // $("input[name='upload']")[0].files = dataTransfer.files;
        }
    });
});
/*-------------------------------------첨부3----------------------------------*/
// 첨부파일 추가하는 경우
$("input[name='upload3']").on("change", function(){
    let formData = new FormData();
    let files = $(this)[0].files;

    $(files).each(function(i, file){
        formData.append("upload", file);
    });

    $.ajax({
        url: "/file/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (files) {
            let text1 = "";
            let text2 = "";
            let text3 = "";
            $(files).each(function(i, file){
                console.log("여기 확인 필요");
                console.log(file);

                text1 += file.storeFileName +`(` + parseInt(file.storeFileSize / 1024) +`KB)`;

                text2 += `<div class="fileVal3 addFile" type="hidden" data-file-size="` + file.storeFileSize + `" data-file-name="` + file.storeFileName + `" data-file-upload-path="` + file.storeFilePath + `" data-file-uuid="` + file.storeFileUuid + `">`;
                text2 += `</div>`;

                text3 += `<div type="hidden" class="fileData3">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[2].storeFileName" value="` + file.storeFileName + `">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[2].storeFilePath" value="` + file.storeFilePath + `">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[2].storeFileUuid" value="` + file.storeFileUuid + `">`;
                text3 += `<input type="hidden" name="storeFlieDTOS[2].storeFileSize" value="` + file.storeFileSize + `">`;
                text3 += `</div>`
            });

            $(".uploadResult3").append(text2);
            $(".uploadResult3").children(".fileText3").html(text1);
            $(".fileDataWrap").append(text3);

        }
    });
});

// 등록 취소 하는 경우
$(".cancelBtn3").on("click", function(){
    const $div3 = $(this).next().children(".fileVal3");
    const $data3 = $(".fileDataWrap").children(".fileData3");
    // let i = $(".uploadResult ul span").index($(this));
    let uploadPath = $div3.data("file-upload-path");
    let fileName = $div3.data("file-uuid") + "_" + $div3.data("file-name");
    console.log($div3);
    console.log(uploadPath);
    console.log(fileName);
    $.ajax({
        url: "/file/delete",
        type: "post",
        data: {uploadPath: uploadPath, fileName: fileName},
        success: function(){
            $div3.remove();
            $data3.remove();
            // arrayFile.splice(i, 1);
            // const dataTransfer = new DataTransfer();
            // arrayFile.forEach(file => dataTransfer.items.add(file));
            // $("input[name='upload']")[0].files = dataTransfer.files;
        }
    });
});

/*-----------------------------------------------------------------------*/



function showUploadResult(files){
    let text = "";
    $(files).each(function(i, file){
        console.log("여기 확인 필요");
        console.log(file);

        text += `<li data-file-size="` + file.storeFileSize + `" data-file-name="` + file.storeFileName + `" data-file-upload-path="` + file.storeFilePath + `" data-file-uuid="` + file.storeFileUuid + `">`;
        text += file.storeFileName +`(` + parseInt(file.storeFileSize / 1024) +`KB)`;
        text += `</li>`;
    });
    // $(".uploadResult").append(text);
    $(".uploadResult").children(".fileText").html(text);
}

