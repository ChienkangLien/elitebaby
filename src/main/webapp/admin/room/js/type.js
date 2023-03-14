//函式宣告(載入既有房型)
function loadRoomType() {
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
          $("#container2").empty();
          $("tbody").empty();
          for (let i = 0; i < body.length; i++) {
            let html = `<tr data-id=${body[i].roomTypeId}>
      <th scope="row">${body[i].roomTypeName}</th>
      <td><button
      class="btn quantityCheck"
      data-bs-toggle="modal"
      data-bs-target="#quantityModal${i + 1}"
    >
    ${body[i].roomQuantity == undefined ? "" : body[i].roomQuantity}
    </button></td>
      <td>${body[i].roomPrice}</td>
      <td>
        ${body[i].roomDescription}
      </td>
      <td>${body[i].formattedCreateTimestamp}</td>
      <td>${
        body[i].formattedChangeTimestamp == undefined
          ? ""
          : body[i].formattedChangeTimestamp
      }</td>
      <td>${body[i].roomStatus}</td>
      <td id=imageNum${i + 1}><button
      class="btn editPhotoBtn"
      data-bs-toggle="modal"
      data-bs-target="#photoModal${i + 1}"
    >
      編輯
    </button></td>
      <td>
        <button
          class="btn getSingleType"
          data-bs-toggle="modal"
          data-bs-target="#exampleModal${i + 1}"
        >
          編輯
        </button>
      </td>
    </tr>`;
            $("#target").append(html);

            $("#container2").append(`<div
    class="modal fade"
    id="exampleModal${i + 1}"
    tabindex="-1"
    aria-labelledby="exampleModalLabel${i + 1}"
    aria-hidden="true"
  >
    <div class="modal-dialog" data-id=${body[i].roomTypeId} data-quantity=${
              body[i].roomQuantity
            }>
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel${i + 1}">編輯房型</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form>
            
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn cancelbtn" data-bs-dismiss="modal">
            取消
          </button>
          <button type="button" class="btn confirmTypeEditbtn" name="${
            body[i].roomTypeName
          }">
            確定修改
          </button>
        </div>
      </div>
    </div>
  </div>
  <div
  class="modal fade"
  id="photoModal${i + 1}"
  tabindex="-1"
  aria-labelledby="photoModalLabel${i + 1}"
  aria-hidden="true"
>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="photoModalLabel${i + 1}">編輯照片</h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Close"
        ></button>
      </div>
      <div class="modal-body">
        <form>
        <label>原始照片--雙擊點選以刪除</label>
        <ul class="ori_picture_list" data-id="ori_${body[i].roomTypeId}"></ul>
        <div class="mb-3">
        <label for="reCreateRoomPhoto${i + 1}" class="col-form-label"
          >加入照片</label
        >
        <input
          type="file"
          class="form-control reCreateRoomPhoto"
          name="roomPhoto"
          id="reCreateRoomPhoto${i + 1}"
          multiple
          accept="image/*"
        />
        <ul class="edit_picture_list" data-id="${body[i].roomTypeId}"></ul>
      </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn cancelbtn" data-bs-dismiss="modal">
          取消
        </button>
        <button type="button" class="btn confirmPhotoEditbtn" name="${
          body[i].roomTypeName
        }" data-id=${body[i].roomTypeId}>
          確定修改
        </button>
      </div>
    </div>
  </div>
</div>
<div
class="modal fade"
id="quantityModal${i + 1}"
tabindex="-1"
aria-labelledby="quantityModalLabel${i + 1}"
aria-hidden="true"
>
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h5 class="modal-title" id="quantityModalLabel${i + 1}">編輯房間名稱</h5>
      <button
        type="button"
        class="btn-close"
        data-bs-dismiss="modal"
        aria-label="Close"
      ></button>
    </div>
    <div class="modal-body">
      <form data-type-id="${body[i].roomTypeId}" class="roomEedit">
      </form>
    </div>
    <div class="modal-footer">
    <button type="button" class="btn increaseRoom">
        新增房間
      </button>
      <button type="button" class="btn cancelbtn" data-bs-dismiss="modal">
        取消
      </button>
      <button type="button" class="btn confirmRoomQuantityEditbtn" name="${
        body[i].roomTypeName
      }" data-id=${body[i].roomTypeId}>
        確定修改
      </button>
    </div>
  </div>
</div>
</div>`);
            $(".remark").summernote({
              tabsize: 2,
              height: 100,
            });
          }
        }
      } catch (error) {
        console.log(error + "，資料庫沒房型資料故後端沒回傳");
      }
    });
}

$(document).ready(function () {
  $(".remark").summernote({
    tabsize: 2,
    height: 100,
  });

  loadRoomType();

  // 再次點選新增房型，重置輸入框
  $("#newRoom").on("click", function () {
    $("#createRoomTypeName").val("");
    $("#createRoomQuantity").val("");
    $("#createRoomPrice").val("");
    imageList = [];
    document.querySelector(".note-editable").innerHTML = "<p><br></p>";
    document.querySelectorAll(".roomName").forEach(function (e) {
      e.closest("div").remove();
    });
    $("#createRoomPhoto").val("");
    $("ul.picture_list").children().remove();
  });

  // 新增房型及照片
  $("#createRoomType").on("click", function () {
    let base64Array = [];
    for (let i = 0; i < imageList.length; i++) {
      let data = imageList[i];
      let base64Code = data.split(",")[1];
      base64Array.push(base64Code);
    }
    let arrOfObjs = base64Array.map((roomPhoto) => ({ roomPhoto }));
    const roomTypeName = document.getElementById("createRoomTypeName");
    const roomPrice = document.getElementById("createRoomPrice");
    const roomDescription = $("#createRoomDescription").summernote("code");
    const roomStatus = document.getElementById("createRoomStatus");
    let hasError = false;
    if (!roomTypeName.value) {
      alert("房型名稱不能是空白");
      hasError = true;
    }
    if (!roomPrice.value) {
      alert("房間單價不能是空白");
      hasError = true;
    }
    if (roomPrice.value <= 0 || roomPrice.value % 1 !== 0) {
      alert("房間單價必須是正整數且不能小於1");
      hasError = true;
    }
    if (roomDescription == "<p><br></p>") {
      alert("房型描述不能是空白");
      hasError = true;
    }
    if (!hasError) {
      fetch("/elitebaby/RoomTypeController", {
        method: "POST",
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
        },
        body: JSON.stringify([
          {
            roomTypeName: roomTypeName.value.trim(),
            roomPrice: roomPrice.value,
            roomDescription: roomDescription,
            roomStatus: roomStatus.value,
          },
          arrOfObjs,
        ]),
      })
        .then((resp) => resp.json())
        .then((body) => {
          alert(`message: ${body.message}`);
          if (body.message == "新增成功") {
            loadRoomType();
            $("#cancel").click();
          }
        });
    }
  });
});

//新增照片
let imageList = [];
document
  .querySelector("#createRoomPhoto")
  .addEventListener("change", function () {
    imageList = [];
    let ul_el = document.querySelector(".picture_list");
    ul_el.innerHTML = "";

    for (let i = 0; i < this.files.length; i++) {
      let reader = new FileReader(); // 用來讀取檔案的物件
      reader.readAsDataURL(this.files[i]); // 讀取檔案
      // 檔案讀取完畢時觸發
      reader.addEventListener("load", function () {
        // 可以透過 reader.result 取得圖片讀取完成時的 Base64 編碼格式
        imageList.push(this.result);

        let li_str = "";
        li_str += "<li>";
        li_str += "<img src='" + reader.result + "' class='preview'>";
        li_str += "</li>";

        ul_el.insertAdjacentHTML("beforeend", li_str);
      });
    }
  });

//點下編輯照片、載入既有照片
let reimageList = [];
$(document).on("click", ".editPhotoBtn", function () {
  reimageList = [];
  deletePhotoId = [];
  const trId = $(this).closest("tr").attr("data-id");
  $(`ul.edit_picture_list[data-id=${trId}]`).empty();
  $(`ul.edit_picture_list[data-id=${trId}]`)
    .closest("div")
    .find("input")
    .val("");
  fetch(`/elitebaby/RoomPhotoController?id=${trId}`)
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
          $(`ul[data-id='ori_${body[0].roomTypeId}'`).empty();
          for (let i = 0; i < body.length; i++) {
            let li_str = `<li><img src="data:image/*;base64,${body[i].roomPhoto}" class='preview fromDatabase' roomPhotoId="${body[i].roomPhotoId}"></li>`;
            $(`ul[data-id='ori_${body[i].roomTypeId}'`).append(li_str);
          }
        }
      } catch (error) {
        console.log(error + "，資料庫沒照片故後端沒回傳");
      }
    });
});

//編輯照片(在編輯底下新增、刪除)
$(document).on("change", ".reCreateRoomPhoto", function () {
  let ul_el = $(this).parent().find("ul.edit_picture_list");
  ul_el.html("");
  reimageList = [];
  for (let i = 0; i < this.files.length; i++) {
    let reader = new FileReader(); // 用來讀取檔案的物件
    reader.readAsDataURL(this.files[i]); // 讀取檔案
    // 檔案讀取完畢時觸發
    reader.addEventListener("load", function () {
      // 可以透過 reader.result 取得圖片讀取完成時的 Base64 編碼格式
      reimageList.push(this.result);

      let li_str = "";
      li_str += "<li>";
      li_str += "<img src='" + reader.result + "' class='preview'>";
      li_str += "</li>";

      ul_el.append(li_str);
    });
  }
});

//刪除既有照片
let deletePhotoId = [];
$(document).on("dblclick", "img.fromDatabase", function () {
  $(this).closest("li").addClass("-hide");
  deletePhotoId.push({ roomPhotoId: $(this).attr("roomphotoid") });
});

//送出照片編輯、加入刪除照片
let result = [];
$(document).on("click", "button.confirmPhotoEditbtn", function () {
  if (reimageList.length > 0) {
    let id = $(this).attr("data-id");
    for (let i = 0; i < reimageList.length; i++) {
      let data = reimageList[i];
      let base64Code = data.split(",")[1];

      result.push({ roomPhoto: base64Code, roomTypeId: id });
    }
  }
  fetch("/elitebaby/RoomPhotoController", {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    },
    body: JSON.stringify([result, deletePhotoId]),
  })
    .then((resp) => resp.json())
    .then((body) => {
      alert(`message: ${body.message}`);
      if (body.message == "修改成功") {
        // loadRoomType();
        $(".cancelbtn").click();
        // location.reload();
        result = [];
      }
    });
});

let increaseNum = 1; //為了計算新增的房間數量
let newData = [];
let oriData = []; //為了記錄哪些房間要被新增及修改
//載入房間資料
$(document).on("click", "button.quantityCheck", function () {
  const roomTypeQuantity = $(this).text().trim();
  const targetValue = $(this).data("bs-target"); // 取得data-bs-target屬性的值
  const targetId = targetValue.replace("#", ""); // 刪除其中的`#`字符
  const trId = $(this).closest("tr").attr("data-id");
  $(`div#${targetId}`).find("form").empty();

  increaseNum = 1;

  fetch(`/elitebaby/RoomController?task=search&roomTypeId=${trId}`)
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
            let html = `<div class="mb-3 putToOri">
    <label for="editRoomName${i + 1}" class="col-form-label"
      >房間名稱${i + 1}</label
    >
    <input
      type="text"
      class="form-control"
      name="roomTypeName"
      id="editRoomName${i + 1}"
      value="${body[i].roomName}"
      data-id="${body[i].roomId}"
      data-type-id="${body[i].roomTypeId}"
    />
  </div>`;
            $(`div#${targetId}`).find("form").append(html);
          }
        }
      } catch (error) {
        console.log(error + "，資料庫沒房間故後端沒回傳");
      }
    });
});

//增加房間數量
$(document).on("click", "button.increaseRoom", function () {
  let html = `<div class="mb-3 putToNew">
  <label for="editRoomName${increaseNum}" class="col-form-label"
    >新增房間名稱</label
  >
  <input
    type="text"
    class="form-control"
    name="roomTypeName"
    id="editRoomName${increaseNum}"
  />
</div>`;
  $(this).closest("div.modal-content").find("form").append(html);
  increaseNum++;
});

//送出房間修改
$(document).on("click", "button.confirmRoomQuantityEditbtn", function () {
  hasError = false;

  $(this)
    .closest("div.modal-content")
    .find("div.putToNew")
    .each(function () {
      let roomTypeId = $(this).closest("form").attr("data-type-id");
      let roomName = $(this).find("input").val().trim();

      if (!roomName) {
        hasError = true;
      } else {
        let roomData = { roomTypeId: roomTypeId, roomName: roomName };
        newData.push(roomData);
      }
    });

  $(this)
    .closest("div.modal-content")
    .find("div.putToOri")
    .each(function () {
      let roomId = $(this).find("input").attr("data-id");
      let roomName = $(this).find("input").val().trim();

      if (!roomName) {
        hasError = true;
      } else {
        let roomData = { roomId: roomId, roomName: roomName };
        oriData.push(roomData);
      }
    });

  if (hasError) {
    alert("房間名稱請勿空白");
    newData = [];
    oriData = [];
  } else {
    fetch("/elitebaby/RoomController", {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
      },
      body: JSON.stringify([newData, oriData]),
    })
      .then((resp) => resp.json())
      .then((body) => {
        alert(`message: ${body.message}`);
        if (body.message == "修改成功") {
          loadRoomType();
          $(".cancelbtn").click();
        }
        newData = [];
        oriData = [];
      });
  }
});

//房型修改
$(document).on("click", "button.confirmTypeEditbtn", function () {
  let that = this;
  const roomTypeId = $(this).closest("div.modal-dialog").attr("data-id");
  const roomTypeName = $(this)
    .closest("div.modal-content")
    .find('input[name="roomTypeName"]')
    .val()
    .trim();
  const roomPrice = $(this)
    .closest("div.modal-content")
    .find('input[name="roomPrice"]')
    .val();
  const roomDescription = $(this)
    .closest("div.modal-content")
    .find('div[name="editRoomDescription"]')
    .summernote("code")
    .trim();
  const roomStatus = $(this).closest("div.modal-content").find("select").val();
  const roomQuantity = $(`tr[data-id=${roomTypeId}]`)
    .find("button.quantityCheck")
    .text()
    .trim();
  let hasError = false;
  if (!roomTypeName) {
    alert("房型名稱不能是空白");
    hasError = true;
  }
  if (!roomPrice) {
    alert("房間單價不能是空白");
    hasError = true;
  }
  if (roomPrice <= 0 || roomPrice % 1 !== 0) {
    alert("房間單價必須是正整數且不能小於1");
    hasError = true;
  }
  if (roomDescription == "<p><br></p>") {
    alert("房型描述不能是空白");
    hasError = true;
  }
  if (!hasError) {
    fetch("/elitebaby/RoomTypeController", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
      },
      body: JSON.stringify({
        roomTypeId: roomTypeId,
        roomTypeName: roomTypeName,
        roomPrice: roomPrice,
        roomQuantity: roomQuantity,
        roomDescription: roomDescription,
        roomStatus: roomStatus,
      }),
    })
      .then((resp) => resp.json())
      .then((body) => {
        alert(`message: ${body.message}`);
        if (body.message == "修改成功") {
          loadRoomType();
          $(that).closest("div.modal-footer").find("button.cancelbtn").click();
        }
      });
  }
});

//點擊房型修改、載入既有資料
$(document).on("click", "button.getSingleType", function () {
  const roomTypeId = $(this).closest("tr").attr("data-id");
  $("div#container2").find(`div[data-id="${roomTypeId}"]`).find("form").empty();

  fetch(`/elitebaby/RoomTypeController?task=getSingle&roomTypeId=${roomTypeId}`)
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
          let html = `<div class="mb-3">
      <label for="editRoomTypeName" class="col-form-label"
        >房型名稱</label
      >
      <input
        type="text"
        class="form-control"
        name="roomTypeName"
        id="editRoomTypeName"
        value="${body.roomTypeName}"
      />
    </div>
    

    <div class="mb-3">
      <label for="editRoomPrice" class="col-form-label"
        >房間單價</label
      >
      <input
        type="number"
        class="form-control"
        name="roomPrice"
        id="editRoomPrice"
        min="1"
        value="${body.roomPrice}"
      />
    </div>

    <div class="mb-3">
      <label for="editRoomStatus" class="col-form-label"
        >狀態</label
      >
      <select name="roomStatus" id="editRoomStatus">
        <option value="上架" ${
          body.roomStatus == "上架" ? "selected" : ""
        }>上架</option>
        <option value="下架" ${
          body.roomStatus == "下架" ? "selected" : ""
        }>下架</option>
      </select>
    </div>
    <div class="mb-3">
      <label for="roomDescription" class="col-form-label"
        >房型描述</label
      >
      <div
        class="form-control remark"
        id="createRoomDescription"
        name="editRoomDescription"
      >${body.roomDescription}
      </div>
    </div>`;
          $("div#container2")
            .find(`div[data-id="${roomTypeId}"]`)
            .find("form")
            .append(html);
          $(".remark").summernote({
            tabsize: 2,
            height: 100,
          });
        }
      } catch (error) {
        console.log(error + "，資料庫沒房型資料故後端沒回傳");
      }
    });
});
