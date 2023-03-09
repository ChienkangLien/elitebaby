let meal = [];
let userId = 1;

fetch(`/elitebaby/Meal?name=getall`)
    .then((resp) => {
        if (resp.status === 204) {
            console.log("resp.status===" + resp.status);
        } else {
            return resp.json();
        }
    })
    .then((body) => {
        try {
            if (body.length != null) {
                // console.log("ttttt");
                meal = body;
                let img_str = "";
                let li_str = "";
                for (let i = 0; i < body.length; i++) {
                    // console.log("進入迴圈");
                    if (body[i].base64 == null || body[i] == "") {
                        img_str = `<img src="" id="meal_pic">`
                    } else {
                        img_str = `<img src="data:image/png;base64,${body[i].base64}" class="meal_pic">`
                    }
                    // console.log("照片標籤處完成");
                    li_str += `
                    <li class="meal_block_1" data-id="${body[i].mealId}">
                        ${img_str}
                        <div class="meal_name" id="meal_name">${body[i].mealName}</div>
                        <div class="meal_info">我是介紹我是介紹我是介紹我是介紹我是介紹我是介紹我是介紹我是介紹我是介紹</div>
                        <div class="meal_price">${body[i].mealPrice}</div>
                        <button class="btn add_cart">加入購物車</button>
                        <button class="btn read_more">閱讀更多</button>
                    </li>`
                }
                $("ul.meal_block").html(li_str);
            }

        } catch (error) {
            console.log(error + "，資料庫沒照片故後端沒回傳");
        }
    });

fetch("/elitebaby/Cart?name=getcart", {
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
    .then(resp => {
        if (resp.status === 204) {
            console.log("resp.status===" + resp.status);
        } else {
            return resp.json();
        }
    })
    .then((body) => {
        try {
            console.log(body);
            if (body != null) {
                console.log(body.msg);
                if (body.msg == "為已登入狀態") {
                    console.log(body.cartcount);
                    // document.querySelector("#cartCount").innerHTML(body.cartcount);
                    $("span#cartCount").html(body.cartcount);
                    alert("已登入並取得購物車內數量");
                } else {
                    alert("尚未登入");
                }
                // console.log("ttttt");
                // $("ul.meal_block").html(li_str);
            }

        } catch (error) {
            console.log(error + "，資料庫沒照片故後端沒回傳");
        }
    });

// =============== 宣告 ===============
let data_id = "";


// 各商品元件加入購物車按鈕
$("ul.meal_block").on("click", "button.add_cart", function () {
    data_id = Number($(this).closest("li").attr("data-id"));
    // console.log("123123");
    console.log(data_id);
    fetch("/elitebaby/Cart?name=addmeal", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                userId: userId,
                mealId: data_id,
                orderCount: 1
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
                if (body.msg === "success") {
                    // let str = "";
                    alert("已新增至購物車!!");
                    console.log(body.cartcount);
                    $("span#cartCount").html(body.cartcount);
                    // document.querySelector("#cartCount").innerHTML(body.cartcount);
                    data_id = "";
                    // location.reload();
                } else {
                    alert("修改失敗!!!!");
                    // console.log("新增失敗");
                    // location.reload();
                }
            } catch (error) {
                alert("出現錯誤!!!!");
                // location.reload();
                console.log(error + "，修改失敗");
            }
        });
})

$("button#cart_btn").on("click", function () {
    // console.log("ttttt");
    fetch("/elitebaby/Cart?name=tocart", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                userId: userId,
                mealId: data_id,
                orderCount: 1
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
                if (body.msg === "success") {
                    // let str = "";
                    alert("已新增至購物車!!");
                    console.log(body.cartcount);
                    $("span#cartCount").html(body.cartcount);
                    // document.querySelector("#cartCount").innerHTML(body.cartcount);
                    data_id = "";
                    // location.reload();
                } else {
                    alert("修改失敗!!!!");
                    // console.log("新增失敗");
                    // location.reload();
                }
            } catch (error) {
                alert("出現錯誤!!!!");
                // location.reload();
                console.log(error + "，修改失敗");
            }
        });
})
