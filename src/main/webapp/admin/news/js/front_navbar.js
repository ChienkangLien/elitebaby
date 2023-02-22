$(function () {

    const navlink = document.querySelectorAll(".nav-link");
    navlink.forEach(function (e) {
        e.addEventListener("mouseenter", function () {
            this.setAttribute("style", "color: #ff8188;");
        });
        e.addEventListener("mouseleave", function () {
            this.removeAttribute("style");
        });
    });
    // ======================登入按鍵隱藏購物車跟鈴鐺===========================
    $("#login_btn").on("click", function () {
        $("#login").css("display", "none");
        $("#logout").css("display", "block")
    })

    $("#logout_btn").on("click", function () {
        $("#logout").css("display", "none");
        $("#login").css("display", "block");
    })

    //控制主要內容從導覽列高度以下開始
    let nav_height = $('#nav').css('height')
    $('#blank_area').css('height', nav_height)

    $('#book_visit').css('height', '20%')
    $('#book_visit').css('width', '10%')

    // 瀏覽器的寬高有改變時觸發
    window.addEventListener("resize", function () {
        let nav_height = $('#nav').css('height')
        $('#blank_area').css('height', nav_height)

        $('#book_visit').css('height', '20%')
        $('#book_visit').css('width', '10%')
    });


})