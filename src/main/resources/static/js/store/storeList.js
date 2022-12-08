var $lock = $(".lock");
var $free = $(".free");
var $top = $(".top");
var $topImg = $(".top img");
var $shopLink = $(".shopLink");

console.log("storedList 들어옴");

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

/*------------------------------페이지 이동-------------------------------------*/
const $detail = $("a.detailBtn");

$detail.on("click", function (e) {
    e.preventDefault();
    location.href = "/store/store-detail" + "?storeId=" + $(this).attr("href");
})