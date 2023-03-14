//載入房型
$(document).ready(function () {
  fetch("/elitebaby/RoomTypeController?task=getAll")
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
            if (body[i].roomStatus === "上架") {
              let html = `<div data-id-to-arr="${body[i].roomTypeId}">
        <div class="t2 title">${body[i].roomTypeName}</div>

        <div id="carouselExampleIndicators${body[i].roomTypeId}" class="carousel slide room_type_img" data-bs-ride="carousel">
            <div class="carousel-indicators " data-insertBtn="${body[i].roomTypeId}">
              
            </div>
            <div class="carousel-inner" data-insertImg="${body[i].roomTypeId}">
              
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators${body[i].roomTypeId}" data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators${body[i].roomTypeId}" data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
        </div>
        
        <div class="room_type_para t3" >
            ${body[i].roomDescription}
        </div>
        <button class="btn find_room_type" from="${body[i].roomTypeId}">立即訂房</button>
        <button class="room_type_btn btn" data-bs-toggle="modal" data-bs-target="#exampleModal${body[i].roomTypeId}" style="display:none;" to="${body[i].roomTypeId}">立即訂房</button>
  
  
        <hr>
        <div class="modal fade" id="exampleModal${body[i].roomTypeId}" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalToggleLabel">是否直接訂房</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              為了提升您的住宿品質，請務必在訂房前完成參觀流程
            </div>
            <div class="modal-footer">
              <a href="/elitebaby/visit/VisitRoomFrontInsert.html">
              <button class="btn" >預約參觀</button>
              </a>
              <button class="btn" data-bs-target="#exampleModalToggleNext${body[i].roomTypeId}" data-bs-toggle="modal">立即訂房</button>
            </div>
          </div>
        </div>
      </div>
    <div class="modal fade" id="exampleModalToggleNext${body[i].roomTypeId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" data-id="${body[i].roomTypeId}">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel" data-id="${body[i].roomTypeId}">立即訂房</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="mb-3">
                            <label for="orderStartDate${body[i].roomTypeId}" class="col-form-label">入住日</label>
                            <input type="date" class="form-control order_start_date" name="orderStartDate" id="orderStartDate${body[i].roomTypeId}">
                        </div>
                        <div class="mb-3">
                            <label for="orderEndDate${body[i].roomTypeId}" class="col-form-label">退房日</label>
                            <input type="date" class="form-control order_end_date" name="orderEndDate" id="orderEndDate${body[i].roomTypeId}" readonly>
                        </div>

                        <div class="mb-3">
                            <label for="orderResident${body[i].roomTypeId}" class="col-form-label">入住人</label>
                            <select name="orderResident" id="orderResident${body[i].roomTypeId}" class="resident">
                                <option value="本人">本人</option>
                                <option value="老婆">老婆</option>
                                <option value="其他">其他</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="roomId${body[i].roomTypeId}" class="col-form-label">選擇房間</label>
                            <div  class="room_num">${body[i].roomTypeName}
                                <select class="roomSelect" name="roomId" id="roomId${body[i].roomTypeId}" data-id="${body[i].roomTypeId}">
                                    
                                </select>
                            </div>
                           </div>
                        <div class="mb-3">
                            <label for="orderRemark${body[i].roomTypeId}" class="col-form-label">備註</label>
                            <textarea class="form-control remark" id="orderRemark${body[i].roomTypeId}" name="orderRemark" placeholder="入住人若選其他、可在此詳述"></textarea>
                        </div>
                        
                    </form>
                </div>
                <div class="modal-footer">
                    <span class="t2" data-price="${body[i].roomPrice}" style="width:50%"></span>
                    <button type="button" class="btn cancelbtn" data-bs-dismiss="modal">取消</button>
                    <button type="button" class="btn createOrder" data-id="${body[i].roomTypeId}">確定</button>
                </div>
            </div>
        </div>
    </div> 
    </div>`;
              $("div#main").append(html);
            }
          }
        }
      } catch (error) {
        console.log(error + "，資料庫沒房型資料故後端沒回傳");
      }
      //載入房型照片
      let roomTypeArr = [];
      $("div[data-id-to-arr]").each(function () {
        roomTypeArr.push($(this).data("id-to-arr"));
      });
      for (let i = 0; i < roomTypeArr.length; i++) {
        fetch(`/elitebaby/RoomPhotoController?id=${roomTypeArr[i]}`)
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
                  let img = `<div class="carousel-item">
                  <img src="data:image/*;base64,${body[i].roomPhoto}" class="d-block w-100" roomPhotoId="${body[i].roomPhotoId}">
                </div>`;
                  $(`div[data-insertImg='${body[i].roomTypeId}'`).append(img);
                  let imgBtn = `<button type="button" data-bs-target="#carouselExampleIndicators${
                    body[i].roomTypeId
                  }" data-bs-slide-to="${i}" aria-label="Slide ${
                    i + 1
                  }"></button>`;
                  $(`div[data-insertBtn='${body[i].roomTypeId}'`).append(
                    imgBtn
                  );
                }
              }
              $('button[data-bs-slide-to="1"]').each(function () {
                $(this).addClass("active").attr("aria-current", "true");
              });
              $("div.carousel-inner").each(function () {
                $(this).find("div.carousel-item:first").addClass("active");
              });
            } catch (error) {
              console.log(error + "，資料庫沒照片故後端沒回傳");
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
  $(document).on("change", ".order_end_date, .order_start_date", function () {
    const start = new Date(
      $(this).closest("form").find("input.order_start_date").val()
    );
    const end = new Date(
      $(this).closest("form").find("input.order_end_date").val()
    );
    const diff = end.getTime() - start.getTime();
    const days = Math.floor(diff / (1000 * 60 * 60 * 24));
    const cost =
      $(this).closest("div.modal-content").find("span").attr("data-price") *
      days;
    $(this)
      .closest("div.modal-content")
      .find("span")
      .html(
        `時段金額為<span class="t2 orderPrice">NT$${
          isNaN(cost) ? "-" : cost
        }</span>`
      );
  });
});

//訂房時清空篩選欄位
$(document).on("click", "button.room_type_btn", function () {
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
  $("select.resident").each(function () {
    $(this).find("option[value='本人']").prop("selected", true);
  });
  $("span.t2").each(function () {
    $(this).text("");
  });
});

//查詢空房
$(document).on(
  "change",
  "input.order_start_date, input.order_end_date",
  function () {
    const startDate = $(this)
      .closest("form")
      .find("input.order_start_date")
      .val();
    const endDate = $(this).closest("form").find("input.order_end_date").val();
    const typeId = $(this).closest("div.modal-content").attr("data-id");

    if (startDate != "" && endDate != "") {
      $(this).closest("form").find("select.roomSelect").empty();
      fetch(
        `/elitebaby/RoomController?task=available&startDate=${startDate}&endDate=${endDate}&typeId=${typeId}`
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
              $(`select#${body[0].roomTypeId}`).empty();
              for (let i = 0; i < body.length; i++) {
                let option_str = `<option data-room-id='${body[i].roomId}' value='${body[i].roomId}'>${body[i].roomName}</option>`;
                $(`select[data-id='${body[i].roomTypeId}'`).append(option_str);
              }
            }
          } catch (error) {
            console.log(error + "，資料庫沒可用房間故後端沒回傳");
          }
        });
    }
  }
);

//新增訂單
$(document).on("click", ".createOrder", function () {
  const roomId = $(this).closest("div.modal-content").find(".roomSelect").val();
  const orderRemark =
    $(this).closest("div.modal-content").find(".remark").val().trim() == ""
      ? null
      : $(this).closest("div.modal-content").find(".remark").val().trim();
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
    .find(".resident")
    .val();
  const orderPrice = $(this)
    .closest("div.modal-footer")
    .find(".orderPrice")
    .text()
    .substring(3);
  const orderStatus = "客訂單";
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
    fetch("/elitebaby/RoomOrderController", {
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
        orderResident: orderResident,
        orderPrice: orderPrice,
      }),
    })
      .then((resp) => resp.json())
      .then((body) => {
        alert(`message: ${body.message}`);
        if (body.message == "新增成功") {
          $(this).closest("div.modal-footer").find(".cancelbtn").click();
        }
      });
  }
});

//點擊立即訂房、做登入驗證
$(document).on("click", ".find_room_type", function () {
  const id = $(this).attr("from");

  fetch("/elitebaby/SimpleVerify")
    .then((resp) => {
      if (resp.redirected) {
        // 如果 response 是一個 redirect，就進行畫面跳轉
        window.location.href = resp.url;
      } else {
        return resp.json();
      }
    })
    .then((body) => {
      if (body.message == "已登入") {
        $(`button[to='${id}']`).click();
      }
    });
});
