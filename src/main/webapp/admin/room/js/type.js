$(document).ready(function () {
    $("#table_id").DataTable();
    $(".remark").summernote({
      tabsize: 2,
      height: 100,
    });
    $("#roomQuantity").change(function () {
      document.querySelectorAll("#roomName").forEach(function (e) {
        e.closest("div").remove();
      });
      let num = $("#roomQuantity").val();
      for (let i = 0; i < num; i++) {
        $("#roomQuantity").closest("div").after(`<div class="mb-3">
                <label for="roomName" class="col-form-label"
                  >房間名稱${num - i}:</label
                >
                <input
                  type="text"
                  class="form-control"
                  name="roomName"
                  id="roomName"
                />
              </div>`);
      }
    });
    $(".editRoomQuantity").change(function () {
      document.querySelectorAll("#roomName").forEach(function (e) {
        e.closest("div").remove();
      });
      let num = $(".editRoomQuantity").val();
      for (let i = 0; i < num; i++) {
        $(".editRoomQuantity").closest("div").after(`<div class="mb-3">
                <label for="roomName" class="col-form-label"
                  >房間名稱${num - i}:</label
                >
                <input
                  type="text"
                  class="form-control"
                  name="roomName"
                  id="roomName"
                />
              </div>`);
      }
    });
    $("#newRoom").on("click", function () {
      console.log(1);
      $("#roomTypeName").val("");
      $("#roomQuantity").val("");
      $("#roomPrice").val("");
      $(".note-editable").html("<p><br></p>");
      document.querySelectorAll("#roomName").forEach(function (e) {
        e.closest("div").remove();
      });
      document.getElementById("roomPhoto").value = "";
    });
  });