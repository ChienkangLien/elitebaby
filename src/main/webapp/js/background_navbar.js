$("button.btn_logout").on("click", function () {
  location.href = "/elitebaby/admin/member/emplogout";
});

$(document).ready(function () {
  $("a[href='/elitebaby/admin/forum/backend.html']").css("display", "none");
});
