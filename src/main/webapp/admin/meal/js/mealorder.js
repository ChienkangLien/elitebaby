let meals = [];

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
                        <tr data-id="${body[i].mealOrderId}">
                            <td>${body[i].mealOrderId}</td>
                            <td>${body[i].userId}</td>
                            <td>${body[i].orderDate}</td>
                            <td>${body[i].strstatus}</td>
                            <td>$${body[i].total}</td>
                            <td>${body[i].strPayment}</td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_meal_detail">查看明細</button>
                                </div>
                            </td>
                        </tr>
                        `;
                    // console.log(body[i].base64);
                }
                $("tbody.getall_tb").html(td_str);
                // $("div.getall").html(body[0].mealId);
            }
        } catch (error) {
            console.log(error + "，回傳失敗");
        }
    });