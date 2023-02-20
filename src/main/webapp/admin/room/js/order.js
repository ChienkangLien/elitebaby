//宣告函式(載入所有訂單)
function loadRoomOrder() {
  $("tbody#target-undone").empty();
  $("tbody#target-done").empty();

  fetch("RoomOrderSearch")
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
            if (body[i].orderStatus == "客訂單") {
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
              <td>${body[i].userName}</td>
              <td>${
                body[i].orderRemark == undefined ? "" : body[i].orderRemark
              }</td>
              <td><button class="btn changeStatus">完成</button></td>
            </tr>`;
              $("tbody#target-undone").append(html);
            } else if (body[i].orderStatus == "完成單") {
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
      <td>${body[i].userName}</td>
      <td>${body[i].orderRemark == undefined ? "" : body[i].orderRemark}</td>
      <td><button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#exampleModal${
        body[i].roomOrderId
      }">
      編輯
    </button></td>
    </tr>`;
              $("tbody#target-done").append(html);
              let html2 = `<div class="modal fade" id="exampleModal${body[i].roomOrderId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">編輯該訂單狀態</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  
                  <div class="modal-footer" data-id="${body[i].roomOrderId}">
                    <button type="button" class="btn backStatus" data-bs-dismiss="modal">返回未完成狀態</button>
                    <button type="button" class="btn deleteStatus">刪除此筆訂單</button>
                  </div>
                </div>
              </div>
            </div>`;
              $(".container").append(html2);
            }
          }
        }
      } catch (error) {
        console.log(error + "，資料庫沒訂單資料故後端沒回傳");
      }
    });
}

//載入當下
$(document).ready(function () {
  loadRoomOrder();
});

//完成訂單
$(document).on("click", ".changeStatus", function () {
  const trId = $(this).closest("tr").attr("data-id");
  fetch("RoomOrderIndOpe", {
    method: "PUT",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    },
    body: JSON.stringify({
      roomOrderId: trId,
      orderStatus: "完成單",
    }),
  })
    .then((resp) => resp.json())
    .then((body) => {
      alert(`message: ${body.message}`);
      if (body.message == "狀態更新成功") {
        loadRoomOrder();
      }
    });
});

//返回訂單
$(document).on("click", ".backStatus", function () {
  const id = $(this).closest("div.modal-footer").attr("data-id");
  fetch("RoomOrderIndOpe", {
    method: "PUT",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    },
    body: JSON.stringify({
      roomOrderId: id,
      orderStatus: "客訂單",
    }),
  })
    .then((resp) => resp.json())
    .then((body) => {
      alert(`message: ${body.message}`);
      if (body.message == "狀態更新成功") {
        loadRoomOrder();
      }
    });
});

//刪除訂單
$(document).on("click", ".deleteStatus", function () {
  let that = this;
  if (confirm("刪除後將從資料庫移除")) {
    const id = $(that).closest("div.modal-footer").attr("data-id");
    fetch("RoomOrderIndOpe", {
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
          loadRoomOrder();
        }
      });
  }
});
