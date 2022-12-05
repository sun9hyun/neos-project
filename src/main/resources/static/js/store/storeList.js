var $lock = $(".lock");
var $free = $(".free");
var $top = $(".top");
var $topImg = $(".top img");
var $shopLink = $(".shopLink");

console.log("들어옴");

$lock.hover(function(){
    $(this).find($top).css("filter", "brightness(0.6)");
    $(this).find($shopLink).toggleClass("shopLink");
},function(){
    $(this).find($top).css("filter", "brightness(1)");
    $(this).find($shopLink).toggleClass("shopLink");
});

$free.hover(function(){
    $(this).find($topImg).attr("src", "/images/store/qr.jpg");
    $(this).find($shopLink).toggleClass("shopLink");
},function(){
    $(this).find($topImg).attr("src", "/images/store/storeNeos.png");
    $(this).find($shopLink).toggleClass("shopLink");
});