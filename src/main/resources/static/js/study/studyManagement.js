/**
 *
 */


// 내용부 상단 탭에 따른 내용 변화
$('.memberMag').click(function(){
    $(this).toggleClass('active'); /* active가 없으면 생성, 있으면 삭제*/
    $(this).parent().children('.contentsMag').removeClass('active');
    $(this).parents('.projectTab').children('.memberManagementTab').toggleClass('active',true);
    $(this).parents('.projectTab').children('.projectMangementTab').toggleClass('active',false);
});

$('.contentsMag').click(function(){
    $(this).toggleClass('active'); /* active가 없으면 생성, 있으면 삭제*/
    $(this).parent().children('.memberMag').removeClass('active');
    $(this).parents('.projectTab').children('.memberManagementTab').toggleClass('active',false);
    $(this).parents('.projectTab').children('.projectMangementTab').toggleClass('active',true);
});