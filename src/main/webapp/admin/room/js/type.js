//函式宣告(載入既有房型)
function loadRoomType() {
  fetch("RoomTypeSearch")
    .then((resp) => resp.json())
    .then((body) => {
      // console.log(body);
      // console.log(body.length);
      $("tbody").empty();
      for (let i = 0; i < body.length; i++) {
        let html = `<tr data-id=${body[i].roomTypeId}>
      <th scope="row">${body[i].roomTypeName}</th>
      <td>${body[i].roomQuantity}</td>
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
          class="btn"
          data-bs-toggle="modal"
          data-bs-target="#exampleModal${i + 1}"
        >
          編輯
        </button>
      </td>
    </tr>`;
        $("#target").append(html);

        $("div.container").append(`<div
    class="modal fade"
    id="exampleModal${i + 1}"
    tabindex="-1"
    aria-labelledby="exampleModalLabel${i + 1}"
    aria-hidden="true"
  >
    <div class="modal-dialog" data-id=${body[i].roomTypeId}>
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
            <div class="mb-3">
              <label for="editRoomTypeName${i + 1}" class="col-form-label"
                >房型名稱</label
              >
              <input
                type="text"
                class="form-control"
                name="roomTypeName"
                id="editRoomTypeName${i + 1}"
                value="${body[i].roomTypeName}"
              />
            </div>
            <div class="mb-3">
              <label for="editRoomQuantity${i + 1}" class="col-form-label"
                >房數</label
              >
              <input
                type="number"
                class="form-control editRoomQuantity"
                name="roomQuantity"
                id="editRoomQuantity${i + 1}"
                min="1"
                value="${body[i].roomQuantity}"
              />
            </div>

            <div class="mb-3">
              <label for="editRoomPrice${i + 1}" class="col-form-label"
                >房間單價</label
              >
              <input
                type="number"
                class="form-control"
                name="roomPrice"
                id="editRoomPrice${i + 1}"
                min="1"
                value="${body[i].roomPrice}"
              />
            </div>

            <div class="mb-3">
              <label for="editRoomStatus${i + 1}" class="col-form-label"
                >狀態</label
              >
              <select name="roomStatus" id="editRoomStatus${i + 1}">
                <option value="上架" ${
                  body[i].roomStatus == "上架" ? "selected" : ""
                }>上架</option>
                <option value="下架" ${
                  body[i].roomStatus == "下架" ? "selected" : ""
                }>下架</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="roomDescription${i + 1}" class="col-form-label"
                >房型描述</label
              >
              <div
                class="form-control remark"
                id="createRoomDescription${i + 1}"
                name="editRoomDescription"
              >${body[i].roomDescription}
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn cancelbtn" data-bs-dismiss="modal">
            取消
          </button>
          <button type="button" class="btn comfirmEditbtn" name="${
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
        <button type="button" class="btn comfirmEditbtn" name="${
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
    });
}

$(document).ready(function () {
  $(".remark").summernote({
    tabsize: 2,
    height: 100,
  });

  loadRoomType();

  // 依照房數添加輸入框
  // $("#createRoomQuantity").change(function () {
  //   document.querySelectorAll(".roomName").forEach(function (e) {
  //     e.closest("div").remove();
  //   });
  //   let num = $("#createRoomQuantity").val();
  //   for (let i = 0; i < num; i++) {
  //     $("#createRoomQuantity").closest("div").after(`<div class="mb-3">
  //               <label for="roomName" class="col-form-label"
  //                 >房間名稱${num - i}:</label
  //               >
  //               <input
  //                 type="text"
  //                 class="form-control roomName"
  //                 name="roomName"
  //                 id="roomName${i + 1}"
  //               />
  //             </div>`);
  //   }
  // });

  // $("#editRoomQuantity").change(function () {
  //   document.querySelectorAll("#roomName").forEach(function (e) {
  //     e.closest("div").remove();
  //   });
  //   let num = $(".editRoomQuantity").val();
  //   for (let i = 0; i < num; i++) {
  //     $(".editRoomQuantity").closest("div").after(`<div class="mb-3">
  //               <label for="roomName" class="col-form-label"
  //                 >房間名稱${num - i}:</label
  //               >
  //               <input
  //                 type="text"
  //                 class="form-control"
  //                 name="roomName"
  //                 id="roomName"
  //               />
  //             </div>`);
  //   }
  // });

  // 再次點選新增房型，重置輸入框
  $("#newRoom").on("click", function () {
    $("#createRoomTypeName").val("");
    $("#createRoomQuantity").val("");
    $("#createRoomPrice").val("");
    imageList = [];
    // $(".note-editable").html("<p><br></p>");
    document.querySelector(".note-editable").innerHTML = "<p><br></p>";
    document.querySelectorAll(".roomName").forEach(function (e) {
      e.closest("div").remove();
    });
    $("#createRoomPhoto").val("");
    $("ul.picture_list").children().remove();
  });

  // 新增房型及照片
  $("#createRoomType").on("click", function () {
    // console.log(imageList);
    var base64Array = [];
    for (var i = 0; i < imageList.length; i++) {
      var data = imageList[i];
      var base64Code = data.split(",")[1];
      base64Array.push(base64Code);
    }
    let arrOfObjs = base64Array.map((roomPhoto) => ({ roomPhoto }));
    // let imgJsonStr = JSON.stringify(arrOfObjs);
    const roomTypeName = document.getElementById("createRoomTypeName");
    const roomQuantity = document.getElementById("createRoomQuantity");
    const roomPrice = document.getElementById("createRoomPrice");
    const roomDescription = $("#createRoomDescription").summernote("code");
    const roomStatus = document.getElementById("createRoomStatus");
    let hasError = false;
    if (!roomTypeName.value) {
      alert("房型名稱不能是空白");
      hasError = true;
    }
    if (!roomQuantity.value) {
      alert("房數不能是空白");
      hasError = true;
    }
    if (roomQuantity.value <= 0 || roomQuantity.value % 1 !== 0) {
      alert("房數必須是正整數且不能小於1");
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
      fetch("RoomTypeCreate", {
        method: "POST",
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
          "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify([
          {
            roomTypeName: roomTypeName.value,
            roomQuantity: roomQuantity.value,
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
            // location.reload();
          }
        });
    }
  });
});

//刪除房型
// $(document).on("click", ".deletebtn", function () {
//   if (confirm("確定刪除")) {
//     let that = this;
//     const roomTypeName = $(this).attr("name");
//     fetch("RoomTypeRemove", {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json;charset=UTF-8",
//       },
//       body: JSON.stringify({
//         roomTypeName: roomTypeName,
//       }),
//     })
//       .then((resp) => resp.json())
//       .then((body) => {
//         alert(`message: ${body.message}`);
//         if (body.message == "刪除成功") {
//           $(".cancelbtn").click();
//           $("tr:has(th:contains('" + roomTypeName + "'))").remove();
//         }
//       });
//   }
// });

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
        // console.log(this.result);
        imageList.push(this.result);

        //寫法一
        //   let li_str = `
        //   <li>
        //   <img src='${reader.result}' class='preview'>
        //   </li>
        //   `

        //寫法二
        let li_str = "";
        li_str += "<li>";
        li_str += "<img src='" + reader.result + "' class='preview'>";
        li_str += "</li>";

        // let ul_el = document.querySelector(".picture_list");
        //   ul_el.innerHTML = li_str;
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
  fetch("RoomPhotoSearch", {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    },
    body: JSON.stringify({
      id: trId,
    }),
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
      // console.log(this.result);
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

//送出照片編輯
// let result = [];
// $(document).on("click", "button.comfirmEditbtn", function () {
//   console.log(deletePhotoId);
//   if (reimageList.length > 0) {
//     let id = $(this).attr("data-id");
//     for (var i = 0; i < reimageList.length; i++) {
//       var data = reimageList[i];
//       var base64Code = data.split(",")[1];
//       // base64Array.push(base64Code);

//       result.push({ roomPhoto: base64Code, roomTypeId: id });
//     }
//     // let arrOfObjs = base64Array.map((roomPhoto) => ({ roomPhoto }));
//     console.log(result);

//     fetch("RoomPhotoEdit", {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json;charset=UTF-8",
//         "Access-Control-Allow-Origin": "*",
//       },
//       body: JSON.stringify(result),
//     })
//       .then((resp) => resp.json())
//       .then((body) => {
//         alert(`message: ${body.message}`);
//         if (body.message == "加入成功") {
//           // loadRoomType();
//           $(".cancelbtn").click();
//           // location.reload();
//           result = [];
//         }
//       });
//   }
// });

//送出照片編輯、加入刪除照片
let result = [];
$(document).on("click", "button.comfirmEditbtn", function () {
  console.log(deletePhotoId);
  if (reimageList.length > 0) {
    let id = $(this).attr("data-id");
    for (var i = 0; i < reimageList.length; i++) {
      var data = reimageList[i];
      var base64Code = data.split(",")[1];
      // base64Array.push(base64Code);

      result.push({ roomPhoto: base64Code, roomTypeId: id });
    }
    // let arrOfObjs = base64Array.map((roomPhoto) => ({ roomPhoto }));
    console.log(result);
    console.log(JSON.stringify([result, deletePhotoId]));
  }
  fetch("RoomPhotoEdit", {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      "Access-Control-Allow-Origin": "*",
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
