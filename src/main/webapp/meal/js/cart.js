let meals = [];
// let userId1 = 1;
let total = null;
let address = null;

// if (userId1 == null || userId1 == undefined) {
//     alert("尚未登入，將跳轉至登入頁面");
//     location.href = "/elitebaby/member/login.html";
// }

//依使用者會員編號取得購物車內商品詳細資料
fetch("/elitebaby/Cart?name=getall"
    // , {
    //     method: "POST",
    //     headers: {
    //         "Content-Type": "application/json;charset=UTF-8",
    //         "Access-Control-Allow-Origin": "*",
    //     },
    //     body: JSON.stringify(
    //         {
    //             userId: userId1,
    //         }
    //     ),
    // }
)
    .then((resp) => {
        if (resp.status === 204) {
            console.log("resp.status===" + resp.status);
        } else {
            return resp.json();
        }
    })
    .then((body) => {
        if (body == null) {
            location.href = "/elitebaby/member/login.html";
        }
        if (body.length == 0) {
            // console.log("購物車內無商品");
            let cart = `<div>
                <div style="text-align:center; margin-top: 30px;">
                    <img src="https://cdn-icons-png.flaticon.com/512/3900/3900101.png" style="width: 200px ; height: 200px ;"
                        alt="Your Image">
                    <p style="font-size: 26px; font-weight: bold; margin-top: 10px; margin-left: 35px;">您的購物車還沒有任何商品</p>
                </div>
                <div style="margin: 0 auto; width: 250px; margin-left: 680px; margin-top: 20px;">
                    <a href="/elitebaby/member/homepage.html"><button class="btn">返回首頁</button></a>
                    <a href="/elitebaby/meal/meal_front.html"><button class="btn" style="margin-left: 20px">前往選購</button></a>
                </div>
            </div>
            <div style="height: 50px;"></div>`
            $("div.cart_table_block").html(cart);
            $("h3.title").css("display", "none");
        } else {
            meals = body;
            try {
                if (body.length != null) {
                    meals = body;
                    let td_str = "";
                    let img_str = "";
                    for (let i = 0; i < body.length; i++) {
                        // let status = "";
                        total += body[i].mealPrice * body[i].count;
                        if (body[i].mealStatus == 0) {
                            status = "下架";
                        } else {
                            status = "上架";
                        }
                        if (body[i].base64 == null || body[i] == "") {
                            img_str = `<img src="" id="mealPic">`
                        } else {
                            img_str = `<img src="data:image/png;base64,${body[i].base64}" id="mealPic" style="border-radius: 5px;">`
                        }
                        td_str += `
                        <tr data-id="${body[i].mealId}">
                            <td>${body[i].mealId}</td>
                            <td>${body[i].mealName}</td>
                            <td>${img_str}</td>
                            <td>$${body[i].mealPrice}</td>
                            <td>${body[i].count}</td>
                            <td>$${body[i].mealPrice * body[i].count}</td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_update" id="btn_update" data-bs-toggle="modal" data-bs-target="#update"
                                       data-bs-whatever="@fat">修改</button>
                                </div>
                                <br>
                                <div>
                                    <button type="button" class="btn btn_delete" id="btn_delete" data-bs-toggle="modal" data-bs-target="#delete"
                                       data-bs-whatever="@fat">刪除</button>
                                </div>
                            </td>
                        </tr>
                        `;
                        // console.log(body[i].base64);
                    }
                    $("tbody.getcart_tb").html(td_str);
                    $("span.total").text(total + " 元");
                    // $("div.getall").html(body[0].mealId);
                }
            } catch (error) {
                console.log(error + "，回傳失敗");
            }
        }
    });

// 取得購物車資料筆數
fetch("/elitebaby/Cart?name=getcart")
    .then(resp => {
        if (resp.status === 204) {
            console.log("resp.status===" + resp.status);
        } else {
            return resp.json();
        }
    })
    .then((body) => {
        try {
            if (body != null) {
                if (body.msg == "為已登入狀態") {
                    console.log("已登入並取得購物車內數量");
                    $("span#cartCount").html(body.cartcount);
                } else {
                    console.log("尚未登入");
                }
                // console.log("ttttt");
                // $("ul.meal_block").html(li_str);
            }

        } catch (error) {
            console.log(error + "，資料庫沒照片故後端沒回傳");
        }
    });

let data_id = null;
let mealcount = null;
let mealid = null;

//各元件的修改按鈕
$("table.cart_table").on("click", "button.btn_update", function () {
    data_id = Number($(this).closest("tr").attr("data-id"));
    // console.log(data_id);
    // $("input.meal-count").val() =
    for (let i = 0; i < meals.length; i++) {
        if (data_id === meals[i].mealId) {
            mealid = meals[i].mealId;
            document.querySelector(".meal_count").value = meals[i].count;
            break;
        }
    }
})

//修改按鈕內的確定修改按鈕-針對數量修改
$("button.btn_toupdate").on("click", function () {
    // console.log("rtyrtytry");
    mealcount = document.querySelector(".meal_count").value;
    console.log(mealcount);
    fetch("/elitebaby/Cart?name=update", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                // userId: userId1,
                mealId: mealid,
                count: mealcount
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
                if (body != null) {
                    if (body.msg == "修改成功") {
                        alert(body.msg);
                        // $("span#cartCount").html(body.cartcount);
                        mealid = null;
                        mealcount = null;
                        location.reload();
                    } else {
                        console.log("尚未登入");
                        alert(body.msg);
                    }
                    // console.log("ttttt");
                    // $("ul.meal_block").html(li_str);
                }

            } catch (error) {
                console.log(error + "，資料庫沒照片故後端沒回傳");
            }
        });
})

//各元件的刪除按鈕
$("tbody.getcart_tb").on("click", "button.btn_delete", function () {
    data_id = Number($(this).closest("tr").attr("data-id"));
    // console.log(data_id);
})

//刪除按鈕內的確認刪除
$("div#delete").on("click", "button.btn_todelete", function () {
    // console.log("rrrrr");
    fetch("/elitebaby/Cart?name=delete", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                // userId: userId1,
                mealId: data_id
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
                if (body != null) {
                    if (body.msg == "success") {
                        alert("刪除成功");
                        // $("span#cartCount").html(body.cartcount);
                        data_id = null;
                        location.reload();
                    } else {
                        console.log("刪除失敗");
                        alert(body.msg);
                    }
                    // console.log("ttttt");
                    // $("ul.meal_block").html(li_str);
                }

            } catch (error) {
                console.log(error + "，資料庫沒照片故後端沒回傳");
            }
        });
})

let twzipcode = new TWzipcode({
    "district": {
        onChange: function (id) {
            console.log(this.nth(id).get());
        }
    }
});

console.log(twzipcode.get());

//檢查付款方式的偽按鈕
$("div.checkout").on("click", "button.click_payment", function () {
    // console.log("我是按鈕B");
    let payment = document.querySelector("#payment").value;
    console.log(payment);
    if (payment == 0) {
        alert("請選擇付款方式");
    }
})

let zipcode = null;
let county = null;
let district = null;
let str = null;

function change_event() {
    let payment = document.querySelector("#payment").value;
    if (payment != 0) {
        $("button.click_payment").removeClass("display_block");
        $("button.click_payment").addClass("display_none");
        $("button.btn_checkout").removeClass("display_none");
        $("button.btn_checkout").addClass("display_block");
    } else {
        $("button.click_payment").addClass("display_block");
        $("button.click_payment").removeClass("display_none");
        $("button.btn_checkout").addClass("display_none");
        $("button.btn_checkout").removeClass("display_block");
    }

    zipcode = twzipcode.get("zipcode");
    county = twzipcode.get("county");
    district = twzipcode.get("district");
    str = document.querySelector("#address").value;
    console.log(zipcode + county + district + str);
    address = zipcode + county + district + str;
    //     console.log(county);
    //     console.log(district);
    //     console.log(address);
}



//結帳按鈕內互動視窗-確定結帳按鈕
$("div#checkout").on("click", "button#btn_tocheckout", function () {
    console.log("按了確定結帳");
    if (address == "" || zipcode == null || zipcode == "" || county == null || county == "" || district == null || district == "" || str == null || str == "") {
        alert("請輸入地址");
        location.reload();
    } else {
        let payment = document.querySelector("#payment").value;
        fetch("/elitebaby/Cart?name=checkout", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify(
                {
                    // userId: userId1,
                    orderPayment: payment,
                    address: address
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
                    if (body != null) {
                        if (body.msg == "success") {
                            alert("結帳成功!!返回訂單頁面!!");
                            // $("span#cartCount").html(body.cartcount);
                            location.href = "/elitebaby/meal/user_order.html";
                        } else {
                            console.log("結帳失敗!!請與客服人員確認!!");
                            alert(body.msg);
                        }
                        // console.log("ttttt");
                        // $("ul.meal_block").html(li_str);
                    }

                } catch (error) {
                    console.log(error + "，資料庫沒照片故後端沒回傳");
                }
            });
    }
})