/*
*
*
* */
globalThis.page1 = 0;
globalThis.page2 = 0;
$(document).ready(function () {
    globalThis.check = false;

    let keywordText = $("#keyword").text();
    let stuCount = parseInt($(".studyCount").text());
    let stoCount = parseInt($(".storeCount").text());
    let totalCount = parseInt($(".totalCount").text());

    // console.log(keywordText);
    // console.log(stuCount);
    // console.log(stoCount);
    // console.log(totalCount);

    function showStudy() {
        $.ajax({
            url: "/search/study?page="+ (globalThis.page1),
            type: "post",
            data: {
                keyword:keywordText
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
        $.ajax({
            url: "/search/store?page="+ (globalThis.page2),
            type: "post",
            data: {
                keyword:keywordText
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
            text += "<a class='studyDetail' href='/study/list?studyId=" +stu.studyId +"' style='display:flex;'>";
            text += "<div class='projectThumbWrap'>";
            if(stu.studyType =='전공'){
                text += "<img class='buttonComponents_lgImg__2-hZO' src='https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_12.png'>";
            }
            if(stu.studyType =='교양'){
                text += "<img class='buttonComponents_lgImg__2-hZO' src='https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_01.png'>";
            }
            if(stu.studyType =='공통'){
                text += "<img class='buttonComponents_lgImg__2-hZO' src='https://letspl.s3.ap-northeast-2.amazonaws.com/images/project_thumb_05.png'>";
            }
            text += "</div>";
            text += "<div class='txtWrap' style='display: inline-grid;'>";
            text += "<p class='tit' style='font-size: 20px;'>"+ stu.studyTitle +"</p>";
            text += "<p class='desTxt'>";
            text += "<span>" + stu.studyContent +"</span>";
            text += "</p>";
            text += "<p class='typeTxt' style='font-size: 14px;'>" + stu.studyKeyword + "</p>";
            text += "</div>";
            text += "</a>";
            text += "</div>";

        });
        stuCount = stuCount+study.numberOfElements;
        totalCount = totalCount+study.numberOfElements;
        $(".studyCount").html(stuCount);
        $(".totalCount").html(totalCount);

        $(".studyWrap").append(text);
        if(study.last){
            $(".studyBtn").hide();
        }
    }


    function storeList(store){
        let text = "";
        console.log(store);
        $(store.content).each((i, sto) => {
            text += "<li>";
            text += "<a class='storeDetail' href='/store/store-detail?storeId=" + sto.storeId + "'>";
            text += "<div class='left'>";
            text += "<div class='listTIt'>";
            text += "<div class='categoryTitWrap'>";
            text += "<span class='category2'>" + sto.storeStatus + "</span>";
            text += "<p class='titTxt'>" + sto.storeTitle + "</p>";
            text += "</div>";
            text += "<div class='previewTxt'>﻿" + sto.storeContent + "</div>";
            text += "</div>";
            text += "</div>";
            text += "</a>";
            text += "</li>";
        });
        stoCount = stoCount+store.numberOfElements;
        totalCount = totalCount+store.numberOfElements;
        $(".storeCount").html(stoCount);
        $(".totalCount").html(totalCount);

        $(".storeAdd").append(text);
        if(store.last){
            $(".storeBtn").hide();
        }
    }


    $(".studyBtn").on("click", function(e){
        e.preventDefault();
        if(!globalThis.check){
            globalThis.page1 = globalThis.page1+1;
            showStudy();
        }
    });

    $(".storeBtn").on("click", function(e){
        e.preventDefault();
        if(!globalThis.check){
            globalThis.page2 = globalThis.page2+1;
            showStore();
        }
    });


});

