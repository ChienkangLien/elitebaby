// 一進到商品管理頁面自動傳送GET請求取得所有商品並show在頁面上

let meals = [];
let status = "";
let select_id = null;

fetch("/elitebaby/Meal?name=getall")
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
                let img_str = "";
                for (let i = 0; i < body.length; i++) {
                    // let status = "";
                    if (body[i].mealStatus == 0) {
                        status = "下架";
                    } else {
                        status = "上架";
                    }
                    if (body[i].base64 == null || body[i] == "") {
                        img_str = `<td><img src="" id="mealPic"></td>`
                    } else {
                        img_str = `<td><img src="data:image/png;base64,${body[i].base64}" id="mealPic" style="border-radius: 5px;"></td>`
                    }
                    td_str += `
                        <tr data-id="${body[i].mealId}">
                            <td>${body[i].mealId}</td>
                            <td>${body[i].mealName}</td>
                            ${img_str}
                            <td>${body[i].mealPrice}</td>
                            <td style="width: 300px;">${body[i].mealInfo}</td>
                            <td>${status}</td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_update" id="btn_update" data-bs-toggle="modal" data-bs-target="#update"
                                       data-bs-whatever="@fat">修改</button>
                                </div>
                                <br>
                                    <div>
                                    <button type="button" class="btn btn_delete" id="btncreate" data-bs-toggle="modal" data-bs-target="#delete"
                                       data-bs-whatever="@fat">刪除</button>
                                    </div>
                            </td>
                        </tr>
                        `;
                    // console.log(body[i].base64);
                }
                $("tbody.getall_tb").html(td_str);
                $("table.visit_table").css("margin", "0 auto");
                // $("div.getall").html(body[0].mealId);
            }
        } catch (error) {
            console.log(error + "，回傳失敗");
        }
    });

// ==================== 宣告 ==================== 
let mealpic = ""; // getall用照片
let upmealpic = ""; // update用照片
let upmealname = "";
let upmealprice = "";
let upmealmealinfo = "";
let upmealstatus = "";
let data_id = "";

// 新增商品互動視窗-讀預覽圖
var preview_img = function (file) {
    var reader = new FileReader(); // 用來讀取檔案
    reader.readAsDataURL(file); // 讀取檔案
    reader.addEventListener("load", function () {
        // console.log(reader.result);
        let img_str = '<img src="' + reader.result + '" class="preview_img">';
        mealpic = reader.result.split(",")[1];
        $("div#preview").html(img_str);
    });
};

// 修改商品互動視窗-讀預覽圖
var uppreview_img = function (file) {

    var reader = new FileReader(); // 用來讀取檔案
    reader.readAsDataURL(file); // 讀取檔案
    reader.addEventListener("load", function () {
        // console.log(reader.result);
        let img_str = '<img src="' + reader.result + '" class="preview_img">';
        upmealpic = reader.result.split(",")[1];
        $("div#uppreview").html(img_str);
    });
};

// 商品修改按鈕-呼叫修改商品互動視窗
$("table.visit_table").on("click", "button.btn_update", function () {
    data_id = Number($(this).closest("tr").attr("data-id"));
    console.log(data_id);
    for (let i = 0; i < meals.length; i++) {
        if (data_id === meals[i].mealId) {
            document.querySelector(".update_meal_name").value = meals[i].mealName;
            document.querySelector("#update_mealpic").setAttribute("src", `data:image/png;base64,${meals[i].base64}`);
            document.querySelector(".update_meal_price").value = meals[i].mealPrice;
            document.querySelector(".update_meal_info").value = meals[i].mealInfo;
            document.querySelector(".update_meal_status").value = meals[i].mealStatus;
            break;
        }
    }
})

// 修改按鈕互動視窗-確定修改按鈕
$(".btn_toupdate").on("click", function (event) {
    console.log("按了確定修改");
    // data_id = Number($(this).closest("tr").attr("data-id"));
    console.log(data_id);
    upmealname = $("input.update_meal_name").val();
    if (upmealpic == null || upmealpic == "") {
        // alert("請選擇照片");
        // event.stopPropagation();
        for (let i = 0; i < meals.length; i++) {
            if (data_id === meals[i].mealId) {
                console.log(meals[i].base64);
                upmealpic = meals[i].base64;
                // console.log(upmealpic);
                break;
            }
        }
    }
    upmealprice = Number($("input.update_meal_price").val());
    upmealmealinfo = $("input.update_meal_info").val();
    upmealstatus = Number($("select.update_meal_status").val());
    let check = 0;
    if (upmealname == null || upmealname == "") {
        check += 1;
        alert("請輸入商品名稱");
    }
    if (upmealprice == null || upmealprice == "" || upmealprice == 0) {
        check += 1;
        alert("請輸入商品價格");
    }
    if (upmealmealinfo == null || upmealmealinfo == "") {
        check += 1;
        alert("請輸入商品介紹");
    }
    if (check == 0) {
        fetch("/elitebaby/Meal?name=update", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify(
                {
                    mealId: data_id,
                    mealName: upmealname,
                    base64: upmealpic,
                    mealPrice: upmealprice,
                    mealInfo: upmealmealinfo,
                    mealStatus: upmealstatus
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
                        alert("修改成功!!!!");
                        data_id = "";
                        location.reload();
                    } else {
                        alert("修改失敗!!!!");
                        // console.log("新增失敗");
                        // location.reload();
                    }
                } catch (error) {
                    alert("出現錯誤!!!!");
                    location.reload();
                    console.log(error + "，修改失敗");
                }
            });
    }
})

// 修改商品互動視窗-商品照片change事件
$("input.update_meal_pic").on("change", function (e) {
    console.log(this.files.length);
    if (this.files.length > 0) {
        uppreview_img(this.files[0]);
    } else {
        preview_el.innerHTML = '<span class="text">預覽圖</span>';
    }
});

// 刪除商品按鈕
$("table.visit_table").on("click", "button.btn_delete", function () {
    console.log("123");
    data_id = $(this).closest("tr").attr("data-id");
    console.log(data_id);

})

// 刪除按鈕互動視窗-確定刪除按鈕
$("button.btn_todelete").on("click", function () {
    // console.log("ttt");
    console.log(data_id);
    fetch("/elitebaby/Meal?name=delete", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                mealId: data_id
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
                    alert("刪除成功!!!!");
                    location.reload();
                } else {
                    alert("刪除失敗!!!!");
                    // console.log("新增失敗");
                    location.reload();
                }
            } catch (error) {
                alert("出現錯誤!!!!");
                location.reload();
                console.log(error + "，刪除失敗");
            }
        });
})

// 新增商品按鈕
$("button#btncreate").on("click", function () {
    // console.log("按了新增商品");
    $("input.meal_name").val("");
    $("input.meal_pic").val("");
    $("input.meal_price").val("");
    $("input.meal_info").val("");
})

// 新增商品互動視窗-取消按鈕
$("button.btn-secondary").on("click", function () {
    // console.log("按了取消");

})



// 新增商品互動視窗-商品照片change事件
$("input.meal_pic").on("change", function (e) {
    console.log("觸發change事件");
    // console.log(e.dataTransfer.files[0]);
    console.log(this.files.length);
    if (this.files.length > 0) {
        console.log(this.files[0]);
        preview_img(this.files[0]);
        console.log("123");
    } else {
        preview_el.innerHTML = '<span class="text">預覽圖</span>';
    }
    // let reader = new FileReader(); // 用來讀取檔案
    // mealpic = reader.readAsDataURL(file); // 讀取檔案
    // console.log(mealpic);

})

// 新增商品互動視窗-確定新增按鈕
$("button.btn_insert").on("click", function () {
    console.log("按了確定新增");
    let mealname = $("input.meal_name").val();
    // console.log(mealname);
    let mealprice = $("input.meal_price").val();
    // console.log(mealprice);
    let mealinfo = $("input.meal_info").val();
    // console.log(mealinfo);
    let mealstatus = $("select.mealstatus").val();
    // console.log(mealstatus);
    let check = 0;
    if (mealname == null || mealname == "") {
        check += 1;
        alert("請輸入商品名稱");
    }
    if (mealpic == null || mealpic == "") {
        check += 1;
        alert("請選擇照片");
    }
    if (mealprice == null || mealprice == "") {
        check += 1;
        alert("請輸入價格");
    }
    if (mealinfo == null || mealinfo == "") {
        check += 1;
        alert("請輸入商品介紹");
    }
    if (check == 0) {
        fetch("/elitebaby/Meal?name=insert", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify(
                {
                    mealName: mealname,
                    base64: mealpic,
                    mealPrice: mealprice,
                    mealInfo: mealinfo,
                    mealStatus: mealstatus
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
                        alert("新增成功!!!!");
                        location.reload();
                    } else {
                        alert("新增失敗!!!!");
                        console.log("新增失敗");
                        location.reload();
                    }
                } catch (error) {
                    alert("出現錯誤!!!!");
                    location.reload();
                    console.log(error + "，新增失敗");
                }
            });
    }
})

$("div.btn_create").on("click", "button.mealselect", function () {
    // console.log("qqqqq");
    select_id = $("input.mealselect").val();
    // console.log(select_id);
    fetch("/elitebaby/Meal?name=getonemeal", {
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify(
            {
                mealId: select_id,
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
            let td_str = "";
            try {
                if (body != null) {
                    let img_str = "";
                    if (body.mealStatus == 0) {
                        status = "下架";
                    } else {
                        status = "上架";
                    }
                    if (body.base64 == null || body == "") {
                        img_str = `<td><img src="" id="mealPic"></td>`
                    } else {
                        img_str = `<td><img src="data:image/png;base64,${body.base64}" id="mealPic" style="border-radius: 5px;"></td>`
                    }
                    td_str += `
                        <tr data-id="${body.mealId}">
                            <td>${body.mealId}</td>
                            <td>${body.mealName}</td>
                            ${img_str}
                            <td>${body.mealPrice}</td>
                            <td style="width: 300px;">${body.mealInfo}</td>
                            <td>${status}</td>
                            <td>
                                <div>
                                    <button type="button" class="btn btn_update" id="btn_update" data-bs-toggle="modal" data-bs-target="#update"
                                       data-bs-whatever="@fat">修改</button>
                                </div>
                                <br>
                                    <div>
                                    <button type="button" class="btn btn_delete" id="btncreate" data-bs-toggle="modal" data-bs-target="#delete"
                                       data-bs-whatever="@fat">刪除</button>
                                    </div>
                            </td>
                        </tr>
                        `;
                    $("tbody.getall_tb").html(td_str);
                    $("table.visit_table").css("margin", "0 auto");
                    $("table.visit_table").css("width", "850px");
                } else {
                    alert("查無此商品，請重新輸入");
                    location.reload();
                }
            } catch (error) {
                console.log(error + "，資料庫沒照片故後端沒回傳");
            }
        });
})
