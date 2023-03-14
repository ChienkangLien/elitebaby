let meals = [];
let userId = null;
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
                            <td>${body[i].strPayment}</td>
                            <td>
                            <div>
                            <button type="button" class="btn btn_detail" id="btn_detail" data-bs-toggle="modal" data-bs-target="#order_detail"
                               data-bs-whatever="@fat">查看明細</button>
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
    userId = Number($(this).closest("tr").attr("user-id"));
    fetch("/elitebaby/MealOrder?name=getorderdetail", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                userId: userId,
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
                    // for (let i = 0; i < body.length; i++) {
                    //     console.log(body[i].mealOrderDetailId);
                    //     console.log(body[i].mealId);
                    //     console.log(body[i].mealName);
                    //     console.log(body[i].orderCount);
                    //     console.log(body[i].mealPrice);
                    // }
                    // alert("查詢成功");
                    total2 = null;
                    let td_str = "";
                    let img_str = "";
                    for (let i = 0; i < body.length; i++) {
                        // let status = "";
                        // console.log(body[i].strStatus);
                        // if (body[i].strStatus == "取消") {
                        //     continue;
                        // }
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
                        // console.log(body[i].base64);
                    }

                    $("tbody.getuserorderdetail_tb").html(td_str);
                    $("span.detail_total").html("$" + total2);
                    // console.log("123");
                    // $("div.total").html(total + " 元");
                    // $("div.getall").html(body[0].mealId);
                }
            } catch (error) {
                console.log(error + "，回傳失敗");
            }
        });
})

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
                            <td>${body[i].strPayment}</td>
                            <td>
                            <div>
                            <button type="button" class="btn btn_detail" id="btn_detail" data-bs-toggle="modal" data-bs-target="#order_detail"
                               data-bs-whatever="@fat">查看明細</button>
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