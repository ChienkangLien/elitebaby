let meal = [];
// let userId1 = 1;

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
                    if (body[i].mealStatus == 0) {
                        continue;
                    }
                    if (body[i].base64 == null || body[i] == "") {
                        img_str = `<img src="" id="meal_pic">`
                    } else {
                        img_str = `<img src="data:image/png;base64,${body[i].base64}" class="meal_pic">`
                    }
                    // console.log("照片標籤處完成");
                    li_str += `
                    <li class="meal_block_1" data-id="${body[i].mealId}">
                        ${img_str}
                        <div class="t2 meal_name" id="meal_name">${body[i].mealName}</div>
                        <br>
                        <div class="f5 meal_info" style="height: 120px; margin-left: 5px; margin-right: 5px;">${body[i].mealInfo}</div>
                        <div class="meal_price">每份價格 $${body[i].mealPrice}<button class="btn add_cart" style="margin-right: 12px;">加入購物車</button></div>
                    </li>`
                }
                $("ul.meal_block").html(li_str);
            }

        } catch (error) {
            console.log(error + "，資料庫沒照片故後端沒回傳");
        }
    });

// fetch("/elitebaby/Cart?name=getcart")
//     .then(resp => {
//         if (resp.status === 204) {
//             console.log("resp.status===" + resp.status);
//         } else {
//             return resp.json();
//         }
//     })
//     .then((body) => {
//         try {
//             if (body != null) {
//                 if (body.msg == "為已登入狀態") {
//                     console.log("已登入並取得購物車內數量");
//                     $("span#cartCount").html(body.cartcount);
//                 } else {
//                     console.log("尚未登入");
//                 }
//                 // console.log("ttttt");
//                 // $("ul.meal_block").html(li_str);
//             }

//         } catch (error) {
//             console.log(error + "，資料庫沒照片故後端沒回傳");
//         }
//     });

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
                // userId: userId1,
                mealId: data_id,
                count: 1
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
                } else if (body.msg === "fail") {
                    alert("無法新增商品至購物車");
                    // console.log("新增失敗");
                    location.reload();
                } else {
                    alert("請登入會員!!");
                    location.href = "/elitebaby/member/login.html";
                }
            } catch (error) {
                alert("出現錯誤!!!!");
                // location.reload();
                console.log(error + "，新增商品至購物車有錯誤!");
            }
        });
})


// //右上角購物車按鈕
// $("button#cart_btn").on("click", function () {
//     // console.log("ttttt");
//     fetch("/elitebaby/Cart?name=tocart", {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json;charset=UTF-8",
//             "Access-Control-Allow-Origin": "*",
//         },
//         body: JSON.stringify(
//             {
//                 userId: userId,
//             }
//         ),
//     })
//         .then((resp) => {
//             if (resp.status === 204) {
//                 console.log("resp.status===" + resp.status);
//             } else {
//                 return resp.json();
//             }
//         })
//         .then((body) => {
//             try {
//                 if (body.msg == "為已登入狀態") {
//                     location.href = "/elitebaby/meal/cart.html";
//                 } else {
//                     alert("跳轉到登入頁面(未引入)");
//                     // console.log("新增失敗");
//                     location.href = "/elitebaby/member/login.html";
//                 }
//             } catch (error) {
//                 alert("出現錯誤!!!!");
//                 // location.reload();
//                 console.log(error + "，跳轉購物車頁面失敗");
//             }
//         });
// })
