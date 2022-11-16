var $tabMenu = $(".myTabMenu>ul>li");
var $myTabContents = $(".myTabContents");


$tabMenu.click(function () {
    $(this).addClass("active");
    $(this).siblings().removeClass("active");

    var $tabNumber = $(this).index();
    $myTabContents.eq($tabNumber).addClass("active");
    $myTabContents.eq($tabNumber).siblings().removeClass("active");
});

console.log($tabMenu);
console.log($myTabContents);