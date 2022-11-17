$(".dropdown-menu").find("a").click(function () {
    let text = "";
    text += ` <ul class="content _tbody" id="member_item_m20220929a41c742d48942" data-nick="관리자" data-app="">`
    text += `<li class="check">`
    text += `<div class="checkbox checkbox-styled no-margin">`
    text += `<label>`
    text += `<input type="checkbox" class="terms">`
    text += `<span></span> </label> </div></li>`
    text += ` <li class="postCategory">자유상점 게시판</li>`
    text += ` <li class="postTitle"><a href="#">이거좀... </a></li> <!-- 작성한 게시글 페이지 이동 -->`
    text += `<li class="postDate"><a href="#">종우종우</a></li><!-- 작성사 상세 페이지 이동 -->`
    text += ` <li class="postDate">2022-11-10 15:30</li>`
    text += `<li class="postLike">123</li>`
    text += ` <li class="action">`
    text += ` <div class="dropdown">`
    text += ` <button id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="btn btn-flat">`
    text += `  <i class="zmdi zmdi-more"></i> </button>`

    text += `  <ul class="dropdown-menu animation-dock right" role="menu" aria-labelledby="dLabel"> <li>`
    text += `  <a href="javascript:;">자료 보기</a></li> <li>`
    text += `  <a href="javascript:;">삭제</a></li>`;
    text += `  </ul></div> </li> </ul>`;

    $(this).closest("._tbody").after(text);
})


