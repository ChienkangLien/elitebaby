let meals = [];
let total2 = null;
let select_id = null

fetch("/elitebaby/MealOrder?name=getall")
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
                meals = body;
                let td_str = "";
                for (let i = 0; i < body.length; i++) {
                    // let status = "";
                    if (body[i].mealStatus == 0) {
                        status = "下架";
                    } else {
                        status = "上架";
                    }
                    td_str += `
                        <tr data-id="${body[i].mealOrderId}" user-id="${body[i].userId}">
                            <td>${body[i].mealOrderId}</td>
                            <td>${body[i].userId}</td>
                            <td>${body[i].orderDate}</td>
                            <td>${body[i].strStatus}</td>
                            <td>$${body[i].total}</td>
                            <td>${body[i].address}</td>
                            <td>${body[i].strPayment}</td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_detail" id="btn_detail" data-bs-toggle="modal" data-bs-target="#order_detail"
                                    data-bs-whatever="@fat">明細</button>
                                </div>
                            </td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_update" id="btn_update" data-bs-toggle="modal" data-bs-target="#order_update"
                                    data-bs-whatever="@fat">修改</button>
                                </div>
                            </td>
                        </tr>
                        `;
                    // console.log(body[i].base64);
                }
                $("tbody.getall_tb").html(td_str);
                $("table.meal_order").css("margin", "0 auto");
                // $("div.getall").html(body[0].mealId);
            }
        } catch (error) {
            console.log(error + "，回傳失敗");
        }
    });


//查看明細按鈕按下後發送請求訂單明細內容
$("tbody.getall_tb").on("click", "button.btn_detail", function () {
    // console.log("rtyrty");
    data_id = Number($(this).closest("tr").attr("data-id"));
    console.log(data_id);
    // userId = Number($(this).closest("tr").attr("user-id"));
    fetch("/elitebaby/MealOrder?name=getorderdetail", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                // userId: userId,
                mealOrderId: data_id
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
            meals = body;
            try {
                if (body.length != null) {
                    total2 = null;
                    let td_str = "";
                    let img_str = "";
                    for (let i = 0; i < body.length; i++) {
                        console.log(typeof (body[i].mealPrice));
                        console.log(typeof (body[i].orderCount));
                        total2 += body[i].mealPrice * body[i].orderCount;
                        console.log(total2);
                        if (body[i].base64 == null || body[i] == "") {
                            img_str = `<td><img src="" id="mealPic"></td>`
                        } else {
                            img_str = `<td><img src="data:image/png;base64,${body[i].base64}" id="mealPic"></td>`
                        }
                        td_str += `
                                <tr data-id="${body[i].mealId}">
                                    <td>${body[i].mealId}</td>
                                    <td>${body[i].mealName}</td>
                                    ${img_str}
                                    <td>${body[i].mealPrice}</td>
                                    <td>${body[i].orderCount}</td>
                                    <td>${body[i].mealPrice * body[i].orderCount}</td>
                                </tr>
                                `;
                    }

                    $("tbody.getuserorderdetail_tb").html(td_str);
                    $("span.detail_total").html("$" + total2);
                }
            } catch (error) {
                console.log(error + "，回傳失敗");
            }
        });
})

//單筆訂單查詢按鈕
$("button#btn_select").on("click", function () {
    // console.log("qweqwe");
    select_id = $("input#select_id").val();

    fetch("/elitebaby/MealOrder?name=getbyorderid", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                // userId: userId,
                mealOrderId: select_id
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
                if (body.length != 0) {
                    meals = body;
                    let td_str = "";
                    for (let i = 0; i < body.length; i++) {
                        // let status = "";
                        if (body[i].mealStatus == 0) {
                            status = "下架";
                        } else {
                            status = "上架";
                        }
                        td_str += `
                        <tr data-id="${body[i].mealOrderId}" user-id="${body[i].userId}">
                            <td>${body[i].mealOrderId}</td>
                            <td>${body[i].userId}</td>
                            <td>${body[i].orderDate}</td>
                            <td>${body[i].strStatus}</td>
                            <td>$${body[i].total}</td>
                            <td>${body[i].address}</td>
                            <td>${body[i].strPayment}</td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_detail" id="btn_detail" data-bs-toggle="modal" data-bs-target="#order_detail"
                                    data-bs-whatever="@fat">明細</button>
                                </div>
                            </td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_update" id="btn_update" data-bs-toggle="modal" data-bs-target="#order_update"
                                    data-bs-whatever="@fat">修改</button>
                                </div>
                            </td>
                        </tr>
                        `;
                        // console.log(body[i].base64);
                    }
                    $("tbody.getall_tb").html(td_str);
                    // $("div.getall").html(body[0].mealId);
                } else {
                    alert("查無此訂單資料");
                    location.reload();
                }
            } catch (error) {
                console.log(error + "，回傳失敗");
            }

        });
})

let status = null;
let address = null;

//修改按鈕
$("tbody.getall_tb").on("click", "button.btn_update", function () {
    // console.log("111111");
    data_id = Number($(this).closest("tr").attr("data-id"));
    console.log(data_id);
    for (let i = 0; i < meals.length; i++) {
        if (data_id == meals[i].mealOrderId) {
            if (meals[i].strStatus == "未付款") {
                status = 0;
            } else if (meals[i].strStatus == "已付款") {
                status = 1;
            } else if (meals[i].strStatus == "取消") {
                status = 2;
            } else if (meals[i].strStatus == "已完成") {
                status = 3;
            }
            document.querySelector(".update_meal_id").value = meals[i].mealOrderId;
            document.querySelector(".update_order_date").value = meals[i].orderDate;
            document.querySelector(".update_order_status").value = status;
            document.querySelector(".update_total").value = meals[i].total;
            document.querySelector(".update_address").value = meals[i].address;
            document.querySelector(".update_payment").value = meals[i].strPayment;
            // address = meals[i].address;
            break;
        }
    }
})


//確定修改按鈕
$("button#btn_toupdate").on("click", function () {
    address = document.querySelector(".update_address").value;
    status = document.querySelector(".update_order_status").value;
    console.log(address);
    let check = 0;
    if (address == null || address == "") {
        check += 1;
        alert("請輸入地址");
    }
    // console.log("rrrrrr");
    if (check == 0) {
        fetch("/elitebaby/MealOrder?name=updateMealWithAddress", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify(
                {
                    mealOrderId: data_id,
                    orderStatus: status,
                    address: address
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
                    if (body != null) {
                        if (body.msg == "success") {
                            alert("修改成功!");
                            data_id = null;
                            location.reload();
                        } else {
                            console.log("修改失敗!");
                            alert("修改失敗!");
                        }
                    }

                } catch (error) {
                    console.log(error + "，資料庫沒照片故後端沒回傳");
                }

            });
    }
})