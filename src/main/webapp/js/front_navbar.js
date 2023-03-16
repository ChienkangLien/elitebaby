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

// ======================購物車按鈕觸發=========================== 
$("button#cart_btn").on("click", function () {
    fetch("/elitebaby/Cart?name=tocart", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                userId: userId,
            }
        ),
    })
        .then((resp) => {
            if (resp.status === 204) {
                console.log("resp.status===" + resp.status);
            } else {
                return resp.json();
            }
        })
        .then((body) => {
            try {
                if (body.msg == "為已登入狀態") {
                    location.href = "/elitebaby/meal/cart.html";
                } else {
                    alert("跳轉到登入頁面(未引入)");
                    // console.log("新增失敗");
                    location.href = "/elitebaby/member/login.html";
                }
            } catch (error) {
                alert("出現錯誤!!!!");
                // location.reload();
                console.log(error + "，跳轉購物車頁面失敗");
            }
        });
})