//宣告函式(載入訂單)
function loadNewRoomOrder() {
  $("tbody#target").empty();
  $("#main2").empty();

  fetch("/elitebaby/RoomOrderController?status=userSearch")
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
          for (let i = 0; i < body.length; i++) {
            let html = `<tr data-id='${body[i].roomOrderId}'>
              <th scope="row">${body[i].roomOrderId}</th>
              <td>${body[i].roomTypeName}</td>
              <td>${body[i].roomName}</td>
              <td>
              ${body[i].orderStartDate}
              </td>
              <td>${body[i].orderEndDate}</td>
              <td>${body[i].formattedCreateTimestamp}</td>
              <td>${
                body[i].formattedChangeTimestamp == undefined
                  ? ""
                  : body[i].formattedChangeTimestamp
              }</td>
              <td>${body[i].orderResident}</td>
              <td>${body[i].orderPrice}</td>
              <td>${
                body[i].orderRemark == undefined ? "" : body[i].orderRemark
              }</td>
              <td><button
              class="btn editBtn"
              data-bs-toggle="modal"
              data-bs-target="#Modal${i + 1}"
              data-id='${body[i].roomOrderId}'
            >
              編輯
            </button></td>
            </tr>`;
            $("tbody#target").append(html);
            let html2 = `<div class="modal fade" id="Modal${
              i + 1
            }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">編輯訂單</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form data-id=${body[i].roomOrderId} data-room-id=${
              body[i].roomId
            }>
                            
                        </form>
                    </div>
                    <div class="modal-footer">
                    <span class="t2 orderPrice" "style="width:50%" data-price='${
                      body[i].orderPrice
                    }'>時段金額為NT$${body[i].orderPrice}</span>
                        <button type="button" class="btn cancelBtn" data-bs-dismiss="modal">取消</button>
                        <button type="button" class="btn confirmEdit" " data-id='${
                          body[i].roomOrderId
                        }'>確定修改</button>
                        <button type="button" class="btn deleteBtn"   data-id='${
                          body[i].roomOrderId
                        }'>刪除訂單</button>
                    </div>
                </div>
            </div>`;
            $("#main2").append(html2);
          }
        }
      } catch (error) {
        console.log(error + "，資料庫沒訂單資料故後端沒回傳");
      }
    });
}

$(document).ready(function () {
  loadNewRoomOrder();
});
let ava = false;
//點擊編輯按鈕、載入原始資料
$(document).on("click", "button.editBtn", function () {
  ava = true;
  const orderId = $(this).attr("data-id");
  fetch(
    `/elitebaby/RoomOrderController?status=orderIdSearch&orderId=${orderId}`
  )
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
          if (body.orderStatus == "完成單") {
            alert("此訂單已完成、無法編輯");
            $(`form[data-id='${orderId}'`)
              .closest("div.modal-content")
              .find("button.confirmEdit")
              .remove();
          }
          $(`form[data-id='${orderId}'`).empty();
          let html = `<div class="mb-3 orderStatus">
            <label for="orderStartDate" class="col-form-label">入住日</label>
            <input type="date" data-date='${formatStartDate(
              body.orderStartDate
            )}' class="form-control order_start_date" name="orderStartDate" id="orderStartDate" value="${formatStartDate(
            body.orderStartDate
          )}"${body.orderStatus == "完成單" ? "readonly" : ""}>
        </div>
        <div class="mb-3">
            <label for="orderEndDate" class="col-form-label">退房日</label>
            <input type="date" data-date='${formatStartDate(
              body.orderEndDate
            )}' class="form-control order_end_date" name="orderEndDate" id="orderEndDate" value="${formatStartDate(
            body.orderEndDate
          )}"${body.orderStatus == "完成單" ? "readonly" : ""}>
        </div>

        <div class="mb-3">
            <label for="orderResident" class="col-form-label">入住人</label>
            <select name="orderResident" class="orderResident"${
              body.orderStatus == "完成單" ? "disabled" : ""
            }>
                <option value="本人" ${
                  body.orderResident == "本人" ? "selected" : ""
                }>本人</option>
                <option value="老婆" ${
                  body.orderResident == "老婆" ? "selected" : ""
                }>老婆</option>
                <option value="其他" ${
                  body.orderResident == "其他" ? "selected" : ""
                }>其他</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="orderRemark" class="col-form-label">備註</label>
            <textarea class="form-control remark" id="orderRemark" name="orderRemark" placeholder="入住人若選其他、可在此詳述"${
              body.orderStatus == "完成單" ? "readonly" : ""
            }>${
            body.orderRemark == undefined ? "" : body.orderRemark
          }</textarea>
        </div>`;
          $(`form[data-id='${orderId}'`).append(html);

          $(`form[data-id='${orderId}'`)
            .closest("div.modal-content")
            .find(".orderPrice")
            .text(`時段金額為NT$${body.orderPrice}`);
        }
      } catch (error) {
        console.log(error + "，資料庫沒訂單資料故後端沒回傳");
      }
    });
});

//日期轉換函式
function formatStartDate(dateStr) {
  const parts = dateStr.split(" ");
  const year = parts[2];
  const month = parts[0].replace("月", "");
  const day = parts[1].replace(",", "");
  const date = new Date(year, month - 1, day); // 將日期字串轉換成 Date 物件

  return (
    date.getFullYear() +
    "-" +
    (date.getMonth() + 1).toString().padStart(2, "0") +
    "-" +
    date.getDate().toString().padStart(2, "0")
  ); // 格式化日期字串;
}

//刪除訂單
$(document).on("click", ".deleteBtn", function () {
  let that = this;
  if (confirm("確認刪除?")) {
    const id = $(that).attr("data-id");
    fetch("/elitebaby/RoomOrderController", {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
      },
      body: JSON.stringify({
        roomOrderId: id,
      }),
    })
      .then((resp) => resp.json())
      .then((body) => {
        alert(`message: ${body.message}`);
        if (body.message == "刪除成功") {
          $(".btn-close").click();
          loadNewRoomOrder();
          $(that).closest("div.modal-footer").find(".cancelBtn").click();
        }
      });
  }
});

//日期選擇
$(document).on("click", ".order_start_date", function () {
  this.setAttribute(
    "min",
    new Date().getFullYear() +
      "-" +
      (new Date().getMonth() + 1).toString().padStart(2, "0") +
      "-" +
      new Date().getDate().toString().padStart(2, "0")
  );

  const start = new Date(
    $(this).closest("form").find("input.order_end_date").val()
  );
  const end = new Date(start);
  end.setDate(end.getDate() - 1);
  $(this).attr("max", end.toISOString().split("T")[0]);
});
$(document).on("click", ".order_end_date", function () {
  const start = new Date(
    $(this).closest("form").find("input.order_start_date").val()
  );
  const end = new Date(start);
  end.setDate(end.getDate() + 1);
  $(this).attr("min", end.toISOString().split("T")[0]);
});
$(document).on("change", ".order_start_date", function () {
  const start = new Date($(this).val());
  const end = new Date(start);
  end.setDate(end.getDate() + 1);
  $(this)
    .closest("form")
    .find("input.order_end_date")
    .attr("min", end.toISOString().split("T")[0]);
});
$(document).on("change", ".order_end_date", function () {
  const end = new Date($(this).val());
  const start = new Date(end);
  start.setDate(start.getDate() - 1);
  $(this)
    .closest("form")
    .find("input.order_start_date")
    .attr("max", start.toISOString().split("T")[0]);
});

//查詢能否變更日期
$(document).on("change", ".order_end_date, .order_start_date", function () {
  const startDate = $(this)
    .closest("form")
    .find("input.order_start_date")
    .val();
  const endDate = $(this).closest("form").find("input.order_end_date").val();
  const roomId = $(this).closest("form").attr("data-room-id");
  const orderId = $(this).closest("form").attr("data-id");

  if (startDate != "" && endDate != "") {
    fetch(
      `/elitebaby/RoomController?task=check&startDate=${startDate}&endDate=${endDate}&roomId=${roomId}&orderId=${orderId}`
    )
      .then((resp) => {
        if (resp.status === 204) {
          console.log("resp.status===" + resp.status);
        } else {
          return resp.json();
        }
      })
      .then((body) => {
        try {
          if (body.message != "可以變更") {
            alert(body.message);
            ava = false;
          } else {
            ava = true;
          }
        } catch (error) {
          console.log(error + "，資料庫沒可用房間故後端沒回傳");
        }
      });
  }
});

//依照日期修改變更價格
$(document).on("change", ".order_end_date, .order_start_date", function () {
  const ori_start = new Date(
    $(this).closest("form").find("input.order_start_date").attr("data-date")
  );
  const ori_end = new Date(
    $(this).closest("form").find("input.order_end_date").attr("data-date")
  );
  const ori_diff = ori_end.getTime() - ori_start.getTime();
  const ori_days = Math.floor(ori_diff / (1000 * 60 * 60 * 24));
  const dailyPrice =
    $(this).closest("div.modal-content").find("span").attr("data-price") /
    ori_days;

  const start = new Date(
    $(this).closest("form").find("input.order_start_date").val()
  );
  const end = new Date(
    $(this).closest("form").find("input.order_end_date").val()
  );
  const diff = end.getTime() - start.getTime();
  const days = Math.floor(diff / (1000 * 60 * 60 * 24));
  const cost = dailyPrice * days;
  $(this)
    .closest("div.modal-content")
    .find("span")
    .html(`時段金額為NT$${isNaN(cost) ? "-" : cost}</span>`);
});

//修改訂單
$(document).on("click", "button.confirmEdit", function () {
  const orderStartDate = $(this)
    .closest("div.modal-content")
    .find(".order_start_date")
    .val();
  const orderEndDate = $(this)
    .closest("div.modal-content")
    .find(".order_end_date")
    .val();
  const orderResident = $(this)
    .closest("div.modal-content")
    .find(".orderResident")
    .val();
  const orderPrice = $(this)
    .closest("div.modal-content")
    .find("span.orderPrice")
    .text()
    .replace("時段金額為NT$", "");
  const orderRemark = $(this)
    .closest("div.modal-content")
    .find(".remark")
    .val()
    .trim();
  const roomOrderId = $(this).attr("data-id");
  const roomId = $(this)
    .closest("div.modal-content")
    .find("form")
    .attr("data-room-id");

  let hasError = false;
  if (!orderEndDate) {
    alert("結束日不能是空白");
    hasError = true;
  }
  if (!orderStartDate) {
    alert("開始日不能是空白");
    hasError = true;
  }

  if (ava && !hasError) {
    fetch("/elitebaby/RoomOrderController?action=edit", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
      },
      body: JSON.stringify({
        roomOrderId: roomOrderId,
        orderRemark: orderRemark,
        orderPrice: orderPrice,
        orderResident: orderResident,
        orderEndDate: orderEndDate,
        orderStartDate: orderStartDate,
        roomId: roomId,
      }),
    })
      .then((resp) => resp.json())
      .then((body) => {
        alert(`message: ${body.message}`);
        if (body.message == "訂單更新成功") {
          $(this).closest(".modal-footer").find(".cancelBtn").click();
          loadNewRoomOrder();
        }
        newData = [];
        oriData = [];
      });
  } else alert("請重新選擇日期");
});
