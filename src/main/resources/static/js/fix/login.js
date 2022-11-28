$profile = $("div.profile");


$hoverMenu = $("div.hoverMenu");
profileCheck = -1;
$profile.click(function () {
    profileCheck *= -1;
    if (profileCheck > 0) {
        // $hoverMenu.removeClass("loginHeaderUnVisibie").addClass("loginHeaderVisibie")
        $hoverMenu.show()
    } else {
        $hoverMenu.hide()
        // $hoverMenu.removeClass("loginHeaderVisibie").addClass("loginHeaderUnVisibie")
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

//
// /* 회원 가입 시 모달창 */
// $(".joinAndLogin").on("click", function () {
//     $(".joinModal1").css("display", "block");
//     alert("들어옴")
// })
//
// $(".closeBtn").on("click", function () {
//     $(".joinModal1").css("display", "none");
// })
//
// $(".signupBtn").on("click", function () {
//     $(".joinModal1").css("display", "none");
// })
//
//
//
// /* 로그인 클릭시 뜨는 로그인 모달 */
// $(".logingbutton").on("click", function () {
//     $(".loginModal1").css("display", "block");
//     alert("emfdj")
// })
//
// $(".closeButton").on("click", function () {
//     $(".loginModal1").css("display", "none");
// })
//
// $(".signupButton").on("click", function () {
//     $(".loginModal1").css("display", "none");
// })

function joinShow() {
    $(".joinModal1").show();
}
function joinHide() {
    $(".joinModal1").hide();
}
function loginShow() {
    $(".loginModal1").show();
}
function loginHide() {
    $(".loginModal1").hide();
}

function closeHeaderModal() {
    $(".loginModal1").hide();
    $(".joinModal1").hide();
}
