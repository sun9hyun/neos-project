/*
*
*
* */
globalThis.page1 = 0;
globalThis.page2 = 0;
$(document).ready(function () {
    globalThis.check = false;


    function showStudy() {
        let keyword = $("#keyword").val();

        $.ajax({
            url: "/search/study?page="+ (globalThis.page1),
            type: "post",
            data: {
                keyword:keyword
            },
            success: function (study) {
                studyList(study);
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        })
    }

    function showStore(){
        let keyword = $("#keyword").val();

        $.ajax({
            url: "/search/store?page="+ (globalThis.page2),
            type: "post",
            data: {
                keyword:keyword
            },
            success: function (store) {
                storeList(store);
            },
            error: function (xhr, status, err) {
                console.log(xhr, status, err);
            }
        })
    }


    function studyList(study){
        let text = "";
        console.log(study);

        $(study.content).each((i, stu) => {
            text += "<div class='recommendProjectlist'>";
            text += "<div class='projectThumbWrap'>";
            if(studyType =='전공'){
                text += "<img class='buttonComponents_lgImg__2-hZO' src='https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_12.png'>";
            }
            if(studyType =='교양'){
                text += "<img class='buttonComponents_lgImg__2-hZO' src='https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_01.png'>";
            }
            if(studyType =='공통'){
                text += "<img class='buttonComponents_lgImg__2-hZO' src='https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png'>";
            }
            text += "</div>";
            text += "<div class='txtWrap'>";
            text += "<p class='tit' style='font-size: 20px;'>"+ stu.studyTitle +"</p>";
            text += "<p class='desTxt'>";
            text += "<span>" + stu.studyContent +"</span>";
            text += "</p>";
            text += "<p class='typeTxt' style='font-size: 14px;'>" + stu.studyKeyword + "</p>";
            text += "</div>";
            text += "</div>";

        });

        $(".studyWrap").html(text);
        if(stu.last){
            $(".studyBtn").hide();
        }
    }


    function storeList(store){
        let text = "";
        console.log(store);
        $(store.content).each((i, sto) => {
            text += "<li>";
            text += "<div class='left'>";
            text += "<div class='listTIt'>";
            text += "<div class='categoryTitWrap'>";
            text += "<span class='category2'>" + sto.storeStatus + "</span>";
            text += "<p class='titTxt'>" + sto.storeTitle + "</p>";
            text += "</div>";
            text += "<div class='previewTxt'>﻿" + sto.storeContent + "</div>";
            text += "</div>";
            text += "</div>";
            text += "</li>";
        });
        $(".storeAdd").html(text);
        if(sto.last){
            $(".storeBtn").hide();
        }
    }


    $(".studyBtn").on("click", function(e){
        alert("클릭1");
        e.preventDefault();
        if(!globalThis.check){
            globalThis.page1 = globalThis.page1+1;
            alert("더보기1");
            showStudy();
        }
    });

    $(".storeBtn").on("click", function(e){
        alert("클릭2");
        e.preventDefault();
        if(!globalThis.check){
            globalThis.page2 = globalThis.page2+1;
            alert("더보기2");
            showStore();
        }
    });

});

