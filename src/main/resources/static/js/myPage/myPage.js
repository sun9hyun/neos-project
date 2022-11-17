/*--큰 탭메뉴 이동--------------------------------------------------------*/
var $tabMenu = $(".myTabMenu>ul>li");
var $myTabContents = $(".myTabContents");

$tabMenu.click(function () {
    $(this).addClass("active");
    $(this).siblings().removeClass("active");

    var $tabNumber = $(this).index();
    $myTabContents.eq($tabNumber).addClass("active");
    $myTabContents.eq($tabNumber).siblings().removeClass("active");
});

/*--구독 탭메뉴 이동-----------------------------------------------------*/
var $fTabMenu = $(".workTabMenu>ul>li");
var $favoriteTable = $(".favoriteTable");

$fTabMenu.click(function () {
    $(this).addClass("active");
    $(this).siblings().removeClass("active");

    var $fTabNumber = $(this).index();
    $favoriteTable.eq($fTabNumber).addClass("active");
    $favoriteTable.eq($fTabNumber).siblings().removeClass("active");
});

/*--구독 탭메뉴 이동-----------------------------------------------------*/
var $pTabMenu = $(".payTabmenu>ul>li");
var $payTable = $(".payTable");

$pTabMenu.click(function () {
    $(this).addClass("active");
    $(this).siblings().removeClass("active");

    var $pTabNumber = $(this).index();
    $payTable.eq($pTabNumber).addClass("active");
    $payTable.eq($pTabNumber).siblings().removeClass("active");
})