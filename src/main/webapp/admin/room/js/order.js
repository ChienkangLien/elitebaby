//宣告函式(載入未完成訂單)
function loadNewRoomOrder() {
  newLimit = 0;
  $("tbody#target-undone").empty();

  fetch("/elitebaby/RoomOrderController?status=new&limit=0")
    .then((resp) => {
      if (resp.status === 204) {
        console.log("resp.status===" + resp.status);
        $("#container1 ul").remove();
      } else {
        return resp.json();
      }
    })
    .then((body) => {
      $("#container1 ul").remove();
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
              <td>${body[i].userName}</td>
              <td>${
                body[i].orderRemark == undefined ? "" : body[i].orderRemark
              }</td>
              <td><button class="btn changeStatus">完成</button></td>
            </tr>`;
            $("tbody#target-undone").append(html);
          }
          $("#container1").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item"><a class="page-link f3"  id="newN">下一頁</a></li>
            </ul>
          </nav>`);
        }
      } catch (error) {
        console.log(error + "，資料庫沒訂單資料故後端沒回傳");
        $("tbody#target-undone").empty();
      }
    });
}
//宣告函式(載入歷史訂單)
function loadHistoryRoomOrder() {
  historyLimit = 0;
  $("tbody#target-done").empty();

  fetch("/elitebaby/RoomOrderController?status=history&limit=0")
    .then((resp) => {
      if (resp.status === 204) {
        console.log("resp.status===" + resp.status);
        $("#container2 ul").remove();
      } else {
        return resp.json();
      }
    })
    .then((body) => {
      $("#container2 ul").remove();
      try {
        if (body.length != null) {
          $("#container3").empty();
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
            $("#container3").append(html2);
          }
          $("#container2").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item"><a class="page-link f3"  id="historyN">下一頁</a></li>
            </ul>
          </nav>`);
        }
      } catch (error) {
        console.log(error + "，資料庫沒訂單資料故後端沒回傳");
      }
    });
}

//載入當下
$(document).ready(function () {
  loadNewRoomOrder();
  loadHistoryRoomOrder();
});

//完成訂單
$(document).on("click", ".changeStatus", function () {
  const trId = $(this).closest("tr").attr("data-id");
  fetch("/elitebaby/RoomOrderController?action=status", {
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
        loadNewRoomOrder();
        loadHistoryRoomOrder();
      }
    });
});

//返回訂單
$(document).on("click", ".backStatus", function () {
  const id = $(this).closest("div.modal-footer").attr("data-id");
  fetch("/elitebaby/RoomOrderController?action=status", {
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
        loadNewRoomOrder();
        loadHistoryRoomOrder();
      }
    });
});

//刪除訂單
$(document).on("click", ".deleteStatus", function () {
  let that = this;
  if (confirm("刪除後將從資料庫移除")) {
    const id = $(that).closest("div.modal-footer").attr("data-id");
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
          loadHistoryRoomOrder();
        }
      });
  }
});

let newLimit = 0;
let historyLimit = 0;
//分頁效果(未完成單)
$(document).on("click", "#newN", function () {
  newLimit = newLimit + 5;
  fetch(`/elitebaby/RoomOrderController?status=new&limit=${newLimit}`)
    .then((resp) => {
      if (resp.status === 204) {
        console.log("resp.status===" + resp.status);
      } else {
        return resp.json();
      }
    })
    .then((body) => {
      $("#container1 ul").remove();
      try {
        if (body.length != null) {
          $("tbody#target-undone").empty();
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
              <td>${body[i].userName}</td>
              <td>${
                body[i].orderRemark == undefined ? "" : body[i].orderRemark
              }</td>
              <td><button class="btn changeStatus">完成</button></td>
            </tr>`;
            $("tbody#target-undone").append(html);
          }
          $("#container1").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item"><a class="page-link f3"  id="newP">上一頁</a></li>
              <li class="page-item"><a class="page-link f3"  id="newN">下一頁</a></li>
            </ul>
          </nav>`);
        }
      } catch (error) {
        console.log(error + "，資料庫沒訂單資料故後端沒回傳");
        newLimit = newLimit - 5;
        $("#container1").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item"><a class="page-link f3"  id="newP">上一頁</a></li>
            </ul>
          </nav>`);
        alert("已是最末頁");
      }
    });
});
$(document).on("click", "#newP", function () {
  if (newLimit >= 5) {
    newLimit = newLimit - 5;
    fetch(`/elitebaby/RoomOrderController?status=new&limit=${newLimit}`)
      .then((resp) => {
        if (resp.status === 204) {
          console.log("resp.status===" + resp.status);
        } else {
          return resp.json();
        }
      })
      .then((body) => {
        $("#container1 ul").remove();
        try {
          if (body.length != null) {
            $("tbody#target-undone").empty();
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
              <td>${body[i].userName}</td>
              <td>${
                body[i].orderRemark == undefined ? "" : body[i].orderRemark
              }</td>
              <td><button class="btn changeStatus">完成</button></td>
            </tr>`;
              $("tbody#target-undone").append(html);
            }
            $("#container1").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item"><a class="page-link f3"  id="newP">上一頁</a></li>
              <li class="page-item"><a class="page-link f3"  id="newN">下一頁</a></li>
            </ul>
          </nav>`);
          }
          if (newLimit == 0) {
            $("#newP").remove();
          }
        } catch (error) {
          console.log(error + "，資料庫沒訂單資料故後端沒回傳");
        }
      });
  }
});

//分頁效果(已完成單)
$(document).on("click", "#historyN", function () {
  historyLimit = historyLimit + 5;

  fetch(`/elitebaby/RoomOrderController?status=history&limit=${historyLimit}`)
    .then((resp) => {
      if (resp.status === 204) {
        console.log("resp.status===" + resp.status);
      } else {
        return resp.json();
      }
    })
    .then((body) => {
      $("#container2 ul").remove();
      try {
        if (body.length != null) {
          $("tbody#target-done").empty();
          $("#container3").empty();
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
            $("#container3").append(html2);
          }
          $("#container2").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
            <li class="page-item"><a class="page-link f3"  id="historyP">上一頁</a></li>
              <li class="page-item"><a class="page-link f3"  id="historyN">下一頁</a></li>
            </ul>
          </nav>`);
        }
      } catch (error) {
        console.log(error + "，資料庫沒訂單資料故後端沒回傳");
        historyLimit = historyLimit - 5;
        $("#container2").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item"><a class="page-link f3"  id="historyP">上一頁</a></li>
            </ul>
          </nav>`);
        alert("已是最末頁");
      }
    });
});
$(document).on("click", "#historyP", function () {
  if (historyLimit >= 5) {
    historyLimit = historyLimit - 5;
    fetch(`/elitebaby/RoomOrderController?status=history&limit=${historyLimit}`)
      .then((resp) => {
        if (resp.status === 204) {
          console.log("resp.status===" + resp.status);
        } else {
          return resp.json();
        }
      })
      .then((body) => {
        $("#container2 ul").remove();
        try {
          if (body.length != null) {
            $("tbody#target-done").empty();
            $("#container3").empty();
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
              $("#container3").append(html2);
            }
            $("#container2").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
            <li class="page-item"><a class="page-link f3"  id="historyP">上一頁</a></li>
              <li class="page-item"><a class="page-link f3" id="historyN">下一頁</a></li>
            </ul>
          </nav>`);
          }
          if (historyLimit == 0) {
            $("#historyP").remove();
          }
        } catch (error) {
          console.log(error + "，資料庫沒訂單資料故後端沒回傳");
        }
      });
  }
});
