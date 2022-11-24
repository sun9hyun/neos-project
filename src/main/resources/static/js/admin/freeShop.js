const $fileList = $(".file-list_1js1ees");
const $fileExitBtn = $(".card-exit")

function showFiles(){
    $fileList.show();
}

//
// $(".dropdown-menu").find("a").click(function () {
//     $fileList.show();
// })

$fileExitBtn.click(function () {
    $fileList.hide();
})

