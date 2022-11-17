/**
 *
 */


// 내용부 상단 탭 작동


$('.workMag').click(function(){
    $(this).toggleClass('active'); /* active가 없으면 생성, 있으면 삭제*/
    $(this).parent().children('.milestoneMag').removeClass('active');
    $(this).parents('.projectTab').children('.taskTab').toggleClass('active',true);
    $(this).parents('.projectTab').children('.mileStoneTab').toggleClass('active',false);
});

$('.milestoneMag').click(function(){
    $(this).toggleClass('active'); /* active가 없으면 생성, 있으면 삭제*/
    $(this).parent().children('.workMag').removeClass('active');
    $(this).parents('.projectTab').children('.taskTab').toggleClass('active',false);
    $(this).parents('.projectTab').children('.mileStoneTab').toggleClass('active',true);
});