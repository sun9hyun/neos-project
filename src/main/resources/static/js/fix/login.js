$profile = $("div.profile");

$hoverMenu = $("div.hoverMenu");
profileCheck = -1;

$profile.click(function () {
    profileCheck *= -1;
    if (profileCheck > 0) {
        $hoverMenu.removeClass("loginHeaderUnVisibie").addClass("loginHeaderVisibie")
    } else {
        $hoverMenu.removeClass("loginHeaderVisibie").addClass("loginHeaderUnVisibie")
    }

})

var swiper = new Swiper(".mySwiper", {
    spaceBetween: 30,
    centeredSlides: true,
    slidesPerView: 1,
    spaceBetween: 30,
    loop: true,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
});

let header = document.getElementsByClassName("Header");

header.addEventListener("mouseover", function (event) {
    event.target.style.color = "#fbdd97";
}, false);


header.addEventListener("mouseout", function(event){
    event.target.style.color = "white";
}, false)


var swiper2 = new Swiper2(".mySwiper", {
    scrollbar: {
        el: ".swiper-scrollbar",
        hide: false
    }
});

var mql = window.matchMedia("screen and (max-width: 1024px)");

if(mql.matches) {
    $(".rightHead .backBtn").on("click", function () {
        $(".chattingHeader .left").removeClass("leftHead");
        $(".chattingSection .list.left").removeClass("leftBody");
        $(".chattingHeader .right.active").addClass("rightHead");
        $(".chattingSection .right.active").addClass("rightBody");
    })

    $(".leftChattingList").on("click", function () {
        $(".chattingHeader .left").addClass("leftHead");
        $(".chattingSection .list.left").addClass("leftBody");
        $(".chattingHeader .right.active").removeClass("rightHead");
        $(".chattingSection .right.active").removeClass("rightBody");
    })
}