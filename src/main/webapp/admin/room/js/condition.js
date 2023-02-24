//宣告函式(載入關房訂單)
function loadHistoryRoomOrder() {
  historyLimit = 0;
  $("tbody#target").empty();

  fetch("/elitebaby/admin/room/RoomOrderController?status=maintain&limit=0")
    .then((resp) => {
      if (resp.status === 204) {
        console.log("resp.status===" + resp.status);
        $("#container ul").remove();
      } else {
        return resp.json();
      }
    })
    .then((body) => {
      $("#container ul").remove();
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
      <td>${body[i].adminName}</td>
      <td>${body[i].orderRemark == undefined ? "" : body[i].orderRemark}</td>
      <td><button type="button" class="btn deleteStatus" data-id="${
        body[i].roomOrderId
      }">
      刪除
    </button></td>
    </tr>`;
            $("tbody#target").append(html);
            // let html2 = `<div class="modal fade" id="exampleModal${body[i].roomOrderId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            //   <div class="modal-dialog">
            //     <div class="modal-content">
            //       <div class="modal-header">
            //         <h5 class="modal-title" id="exampleModalLabel">維護完成刪除此關房單</h5>
            //         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            //       </div>

            //       <div class="modal-footer" data-id="${body[i].roomOrderId}">
            //         <button type="button" class="btn deleteStatus">刪除此筆訂單</button>
            //       </div>
            //     </div>
            //   </div>
            // </div>`;
            // $(".container").append(html2);
          }
          $("#container").append(`<nav aria-label="Page navigation example">
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

$(document).ready(function () {
  loadHistoryRoomOrder();

  //載入房型名稱到標籤
  fetch("/elitebaby/admin/room/RoomTypeController?task=getAll")
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
            let html = `<option data-id="${body[i].roomTypeId}" value="${body[i].roomTypeId}">${body[i].roomTypeName}</option>`;
            $("select#roomTypeId").append(html);
            $("select#roomTypeId2").append(html);
          }
        }
      } catch (error) {
        console.log(error + "，資料庫沒房型資料故後端沒回傳");
      }
      const trId = $("select#roomTypeId2").val();
      fetch(`RoomController?task=search&roomTypeId=${trId}`)
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
                let html = `<option data-id="${body[i].roomId}" value="${body[i].roomId}">${body[i].roomName}</option>`;
                $(`select#roomId2`).append(html);
              }
            }
          } catch (error) {
            console.log(error + "，資料庫沒房間故後端沒回傳");
          }
        });
    });

  //日期選擇
  $(document).on("click", ".order_start_date", function () {
    this.setAttribute("min", new Date().toISOString().split("T")[0]);
  });
  $(document).on("change", ".order_start_date", function () {
    $(this)
      .closest("form")
      .find("input.order_end_date")
      .attr("readonly", false);
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
});

//訂房時清空篩選欄位
$(document).on("click", "#newRoom", function () {
  $(".order_start_date").each(function () {
    $(this).val("").removeAttr("min").removeAttr("max");
  });
  $(".order_end_date").each(function () {
    $(this).val("").prop("readonly", true).removeAttr("min").removeAttr("max");
  });
  $(".remark").each(function () {
    $(this).val("");
  });
  $("select.roomSelect").each(function () {
    $(this).empty();
  });
  $("option:first-child").prop("selected", true);
});

// //查詢空房
$(document).on(
  "change",
  "input.order_start_date, input.order_end_date, select#roomTypeId",
  function () {
    const startDate = $(this)
      .closest("form")
      .find("input.order_start_date")
      .val();
    const endDate = $(this).closest("form").find("input.order_end_date").val();
    const typeId = $(this).closest("form").find("select#roomTypeId").val();

    if (startDate != "" && endDate != "" && typeId != "") {
      console.log(startDate);
      console.log(endDate);
      console.log(typeId);
      $(this).closest("form").find("select.roomSelect").empty();
      fetch(
        `/elitebaby/admin/room/RoomController?task=available&startDate=${startDate}&endDate=${endDate}&typeId=${typeId}`
      )
        .then((resp) => {
          if (resp.status === 204) {
            console.log("resp.status===" + resp.status);
            alert("目前時段此房型房間已被訂滿");
          } else {
            return resp.json();
          }
        })
        .then((body) => {
          try {
            if (body.length != null) {
              $(`select#roomId`).empty();
              console.log(body);
              for (let i = 0; i < body.length; i++) {
                let option_str = `<option data-room-id='${body[i].roomId}' value='${body[i].roomId}'>${body[i].roomName}</option>`;
                $(`select#roomId`).append(option_str);
              }
            }
          } catch (error) {
            console.log(error + "，資料庫沒可用房間故後端沒回傳");
          }
        });
    }
  }
);

let historyLimit = 0;
//分頁效果(已完成單)
$(document).on("click", "#historyN", function () {
  historyLimit = historyLimit + 5;

  fetch(`RoomOrderController?status=maintain&limit=${historyLimit}`)
    .then((resp) => {
      if (resp.status === 204) {
        console.log("resp.status===" + resp.status);
      } else {
        return resp.json();
      }
    })
    .then((body) => {
      $("#container ul").remove();
      try {
        if (body.length != null) {
          $("tbody#target").empty();
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
      <td>${body[i].adminName}</td>
      <td>${body[i].orderRemark == undefined ? "" : body[i].orderRemark}</td>
      <td><button type="button" class="btn deleteStatus" data-id="${
        body[i].roomOrderId
      }">
      刪除
    </button></td>
    </tr>`;
            $("tbody#target").append(html);
            //   let html2 = `<div class="modal fade" id="exampleModal${body[i].roomOrderId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            //   <div class="modal-dialog">
            //     <div class="modal-content">
            //       <div class="modal-header">
            //         <h5 class="modal-title" id="exampleModalLabel">維護完成刪除此關房單</h5>
            //         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            //       </div>

            //       <div class="modal-footer" data-id="${body[i].roomOrderId}">
            //         <button type="button" class="btn deleteStatus">刪除此筆訂單</button>
            //       </div>
            //     </div>
            //   </div>
            // </div>`;
            //   $(".container").append(html2);
          }
          $("#container").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
            <li class="page-item"><a class="page-link f3"  id="historyP">上一頁</a></li>
              <li class="page-item"><a class="page-link f3"  id="historyN">下一頁</a></li>
            </ul>
          </nav>`);
          console.log("sus N" + historyLimit);
        }
      } catch (error) {
        console.log(error + "，資料庫沒訂單資料故後端沒回傳");
        historyLimit = historyLimit - 5;
        $("#container").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item"><a class="page-link f3"  id="historyP">上一頁</a></li>
            </ul>
          </nav>`);
        console.log("fail N" + historyLimit);
        alert("已是最末頁");
      }
    });
});
$(document).on("click", "#historyP", function () {
  if (historyLimit >= 5) {
    historyLimit = historyLimit - 5;
    fetch(`RoomOrderController?status=maintain&limit=${historyLimit}`)
      .then((resp) => {
        if (resp.status === 204) {
          console.log("resp.status===" + resp.status);
        } else {
          return resp.json();
        }
      })
      .then((body) => {
        $("#container ul").remove();
        try {
          if (body.length != null) {
            $("tbody#target").empty();
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
              <td>${body[i].adminName}</td>
              <td>${
                body[i].orderRemark == undefined ? "" : body[i].orderRemark
              }</td>
              <td><button type="button" class="btn deleteStatus" data-id="${
                body[i].roomOrderId
              }">
              刪除
            </button></td>
            </tr>`;
              $("tbody#target").append(html);
              //   let html2 = `<div class="modal fade" id="exampleModal${body[i].roomOrderId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              //   <div class="modal-dialog">
              //     <div class="modal-content">
              //       <div class="modal-header">
              //         <h5 class="modal-title" id="exampleModalLabel">維護完成刪除此關房單</h5>
              //         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              //       </div>

              //       <div class="modal-footer" data-id="${body[i].roomOrderId}">
              //         <button type="button" class="btn deleteStatus">刪除此筆訂單</button>
              //       </div>
              //     </div>
              //   </div>
              // </div>`;
              //   $(".container").append(html2);
            }
            $("#container").append(`<nav aria-label="Page navigation example">
            <ul class="pagination">
            <li class="page-item"><a class="page-link f3"  id="historyP">上一頁</a></li>
              <li class="page-item"><a class="page-link f3" id="historyN">下一頁</a></li>
            </ul>
          </nav>`);
          }
          console.log("P>0" + historyLimit);
          if (historyLimit == 0) {
            $("#historyP").remove();
            console.log("P=0" + historyLimit);
            // alert("已是最前頁");
          }
        } catch (error) {
          console.log(error + "，資料庫沒訂單資料故後端沒回傳");
        }
      });
  }
});

//刪除訂單
$(document).on("click", ".deleteStatus", function () {
  let that = this;
  if (confirm("刪除後將從資料庫移除")) {
    const id = $(that).attr("data-id");
    fetch("RoomOrderController", {
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
          loadHistoryRoomOrder();
        }
      });
  }
});

//載入房間資料(房間瀏覽)
$(document).on("change", "select#roomTypeId2", function () {
  const trId = $(this).val();
  $("select#roomId2").empty();

  fetch(`RoomController?task=search&roomTypeId=${trId}`)
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
            let html = `<option data-id="${body[i].roomId}" value="${body[i].roomId}">${body[i].roomName}</option>`;
            $(`select#roomId2`).append(html);
          }
        }
      } catch (error) {
        console.log(error + "，資料庫沒房間故後端沒回傳");
      }
    });
});

let eventArr = [];
//載入訂單行程
$(document).on("click", "button#selectBtn", function () {
  const roomId = $(this).closest("div#roomSelectdiv").find("#roomId2").val();

  fetch(
    `/elitebaby/admin/room/RoomOrderController?status=calendar&roomId=${roomId}`
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
        eventArr = [];
        if (body.length != null) {
          for (let i = 0; i < body.length; i++) {
            const newObject = {
              title: `${
                body[i].userName == undefined
                  ? ""
                  : "會員:" + body[i].userName + " "
              }${
                body[i].orderResident == undefined
                  ? ""
                  : "入住人:" + body[i].orderResident + " "
              }${
                body[i].adminName == undefined
                  ? ""
                  : "關房員工:" + body[i].adminName + " "
              }${
                body[i].orderRemark == undefined
                  ? ""
                  : "備註:" + body[i].orderRemark
              }`,

              start: formatStartDate(body[i].orderStartDate),
              end: formatEndDate(body[i].orderEndDate),
            };
            eventArr.push(newObject);
          }
          console.log(eventArr);
          const calendarEl = document.getElementById("calendar");
          const calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: "dayGridMonth",
            locale: "zh",
            height: 550,
            events: eventArr,
            eventColor: "#ff8188",
          });
          calendar.render();
        }
      } catch (error) {
        console.log(error + "，資料庫沒訂單資料故後端沒回傳");
        const calendarEl = document.getElementById("calendar");
        const calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: "dayGridMonth",
          locale: "zh",
          height: 550,
          events: eventArr,
          eventColor: "#ff8188",
        });
        calendar.render();
      }
    });
});

//行事曆
// document.addEventListener("DOMContentLoaded", function () {
//   const calendarEl = document.getElementById("calendar");
//   const calendar = new FullCalendar.Calendar(calendarEl, {
//     initialView: "dayGridMonth",
//     locale: "zh",
//     height: 550,
//     events: [
//       // 事件
//       {
//         // 事件
//         title: "約會",
//         start: "2023-02-01",
//       },
//       {
//         // 事件(包含開始時間)
//         title: "中餐",
//         start: "2023-02-12",
//       },
//       {
//         // 事件(包含跨日開始時間、結束時間)
//         title: "音樂節",
//         start: "2023-02-07",
//         end: "2023-02-10",
//       },
//       {
//         // 事件(包含開始時間、結束時間)
//         title: "會議",
//         start: "2023-02-12",
//         end: "2023-02-12",
//       },
//       e1,
//     ],
//   });
//   calendar.render();
// });

//日期轉換函式(起始日)
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

//日期轉換函式(結束日)
function formatEndDate(dateStr) {
  const parts = dateStr.split(" ");
  const year = parts[2];
  const month = parts[0].replace("月", "");
  const day = parts[1].replace(",", "");
  const date = new Date(year, month - 1, day); // 將日期字串轉換成 Date 物件

  date.setDate(date.getDate() + 1); // 增加一天

  return (
    date.getFullYear() +
    "-" +
    (date.getMonth() + 1).toString().padStart(2, "0") +
    "-" +
    date.getDate().toString().padStart(2, "0")
  ); // 格式化日期字串;
}

//新增關房單
$(document).on("click", "#createRoomType", function () {
  const roomId = $("#roomId").val();
  const orderRemark =
    $("#orderRemark").val().trim() == ""
      ? null
      : $("#orderRemark").val().trim();
  const orderStartDate = $("#orderStartDate").val();
  const orderEndDate = $("#orderEndDate").val();
  const orderStatus = "關房單";
  let hasError = false;
  if (!orderEndDate) {
    alert("結束日不能是空白");
    hasError = true;
  }
  if (!orderStartDate) {
    alert("開始日不能是空白");
    hasError = true;
  }
  if (!roomId) {
    alert("房間不能是空白");
    hasError = true;
  }

  if (!hasError) {
    fetch("RoomOrderController", {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
      },
      body: JSON.stringify({
        orderRemark: orderRemark,
        roomId: roomId,
        orderStartDate: orderStartDate,
        orderEndDate: orderEndDate,
        orderStatus: orderStatus,
      }),
    })
      .then((resp) => resp.json())
      .then((body) => {
        alert(`message: ${body.message}`);
        if (body.message == "新增成功") {
          loadHistoryRoomOrder();
          $("#cancel").click();
        }
      });
  }
});
