const $checkBtn = $("#checkB")
$checkBtn.on("click",function () {
    var checked = $checkBtn.is(":checked")
    var checkedTest = `<input type="hidden" class="certify_1j" name="userCollegeCertify" value="noNeed">`
    var noCheckedTest = `<input type="hidden" class="certify_1j" name="userCollegeCertify" value="false">`
    if(checked){
        $(this).parent().find(".certify_1j").remove();
        $(this).after(checkedTest)
    }else{
        $(this).parent().find(".certify_1j").remove();
        $(this).after(noCheckedTest)
    }
})

function joinSubmit() {
    if($("#user_nickname").val().length==0){
        alert("닉네임은 빈칸일 수 없습니다.");
        $("#user_nickname").focus();
        return;
    }
    if($("#user_phone").val().length==0){
        alert("핸드폰 번호는 빈칸일 수 없습니다.");
        $("#user_phone").focus();
        return;
    }

    if(!$("#user_phone").val().startsWith("010")){
        alert("핸드폰 번호 형식이 아닙니다.")
        $("#user_phone").focus();
        return;
    }
    if($("#user_phone").val().length!=11){
        alert("핸드폰 번호 형식이 아닙니다.")
        $("#user_phone").focus();
        return;
    }

    if($(".certify_1j").val()=='false'){

        if($('#location_1j > option:selected').val() =='KR00'){
            alert("지역 선택을 해야합니다.")
            $("#location_1j").focus();
            return;
        }

        if($('#collegeName > option:selected').val() == '02'){
            alert("대학교 선택을 해야합니다.")
            $("#collegeName").focus();
            return;
        }

        if($('#collegeMajor > option:selected').val() == '0201'){
            alert("전공 선택을 해야합니다.")
            $("#collegeMajor").focus();
            return;
        }

        if($('#collegeGrade > option:selected').val() == '01'){
            alert("학년 선택을 해야합니다.")
            $("#collegeGrade").focus();
            return;
        }

        if($("#collegeEmail").val().length==0){
            alert("대학교 이메일은 빈칸일 수 없습니다.");
            $("#collegeEmail").focus();
            return;
        }


    }

    $("#joinForm").submit();

}