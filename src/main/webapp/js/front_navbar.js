$(function () {
  const navlink = document.querySelectorAll(".nav-link");
  navlink.forEach(function (e) {
    e.addEventListener("mouseenter", function () {
      this.setAttribute("style", "color: #ff8188;");
    });
    e.addEventListener("mouseleave", function () {
      this.removeAttribute("style");
    });
  });
  // ======================登入按鍵隱藏購物車跟鈴鐺===========================
  $("#login_btn").on("click", function () {
    $("#login").css("display", "none");
    $("#logout").css("display", "block");
  });

  $("#logout_btn").on("click", function () {
    $("#logout").css("display", "none");
    $("#login").css("display", "block");
  });

  //控制主要內容從導覽列高度以下開始
  let nav_height = $("#nav").css("height");
  $("#blank_area").css("height", nav_height);

  $("#book_visit").css("height", "20%");
  $("#book_visit").css("width", "10%");

  // 瀏覽器的寬高有改變時觸發
  window.addEventListener("resize", function () {
    let nav_height = $("#nav").css("height");
    $("#blank_area").css("height", nav_height);

    $("#book_visit").css("height", "20%");
    $("#book_visit").css("width", "10%");
  });

  // 登入按鈕
  document.getElementById("loginButton").addEventListener("click", function () {
    window.location.href = "/elitebaby/member/login.html";
  });
  const register_btn = document.querySelector(".register_btn");
  register_btn.addEventListener("click", function () {
    // 在這裡編寫按鈕點擊後的處理邏輯，比如返回上一頁
    window.location.href = "/elitebaby/member/register.html";
  });

  // 取得表單數據

  //  進入首頁馬上執行
  checkLogin();
});

//===============================================鈴鐺==============================================

fetch("/elitebaby/visit/servlet?action=GET_MEMBER_INFO", {
  method: "GET",
  headers: {
    "Content-Type": "application/json",
  },
})
  .then((resp) => {
    if (resp.status === 204) {
      console.log("未登入");
    }

    if (resp.status === 200) {
      return resp.json();
    }
  })
  .then((data) => {
    try {
      var userId = data.id;

      console.log(userId);

      var MyPoint = `/emailBell/${userId}`;
      var host = window.location.host;
      var path = window.location.pathname;
      var webCtx = path.substring(0, path.indexOf("/", 1));
      var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

      var webSocket;

      function get_email_websucket() {
        webSocket = new WebSocket(endPointURL);

        webSocket.onopen = function (event) {
          console.log("Connect Success!");
        };

        webSocket.onmessage = function (event) {
          var jsonObj = JSON.parse(event.data);
          console.log(jsonObj);
          console.log(jsonObj.from);

          if (typeof jsonObj.from === "string") {
            var bellcounter = parseInt(
              document.querySelector(".emailBill").outerText
            );
            console.log(bellcounter);
            if (bellcounter > 0 && bellcounter !== "undefined") {
              bellcounter += 1;
              document.querySelector(".emailBill").innerText = `${bellcounter}`;
            } else {
              document.querySelector(".emailBill").innerText = "1";
            }
          } else {
            // 隱藏提示沒有新郵件
          }
        };

        webSocket.onclose = function (event) {
          console.log("Disconnected!");
        };
      }

      if (userId != null) {
        get_email_websucket();
      }

      fetch(
        `/elitebaby/report/emailservlet?action=get_email_redis&userId=user${userId}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
        .then((resp) => resp.json())
        .then((data) => {
          let emailBellData = [];
          emailBellData = data;
          console.log(data);
          var bell_count;
          if (data.length == 1) {
            if (emailBellData[0].status === "unread") {
              bell_count = parseInt(emailBellData[0].unreadCount);
              console.log(emailBellData[0].unreadCount);
              document.querySelector(".emailBill").innerText = `${bell_count}`;
            } else {
              document.querySelector(".emailBill").innerText = "";
            }
          } else if (data.length == 2) {
            if (emailBellData[0].status === "unread") {
              var bell_count1 = parseInt(emailBellData[0].unreadCount);
              console.log(bell_count1);
            } else {
              var bell_count1 = 0;
              document.querySelector(".emailBill").innerText = "";
            }

            if (emailBellData[1].status === "unread") {
              var bell_count2 = parseInt(emailBellData[1].unreadCount);
              console.log(bell_count2);
            } else {
              var bell_count2 = 0;
              document.querySelector(".emailBill").innerText = "";
            }

            bell_count = bell_count1 + bell_count2;
            console.log(bell_count);

            if (bell_count !== "undefined" && bell_count > 0) {
              document.querySelector(".emailBill").innerText = `${bell_count}`;
            }
          } else {
            document.querySelector(".emailBill").innerText = "";
          }
        });

      document.querySelector(".bi-bell").addEventListener("click", function () {
        document.querySelector(".emailBill").innerText = "";

        console.log(userId);
        fetch(
          `/elitebaby/report/emailservlet?action=get_email_redis&userId=user${userId}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
          .then((resp) => resp.json())
          .then((data) => {
            let emailBellData = [];
            emailBellData = data;
            console.log(data);
            if (data.length == 0) {
              document.querySelector(
                "#popupContent"
              ).innerHTML = `<a href="/elitebaby/visit/ReportEmailFrontRSMail.html" style="display: block; margin-bottom: 10px;">"您的收件匣有0封未讀信件"</a>
                                                              <a href="/elitebaby/visit/ReportEmailFrontGetAll.html">"您的寄件匣有0封回覆信件"</a>`;
            } else if (data.length == 1) {
              for (let xe = 0; xe < emailBellData.length; xe++) {
                if (emailBellData[xe].from === "admin") {
                  var str1 = `<a href="/elitebaby/visit/ReportEmailFrontRSMail.html" style="display: block; margin-bottom: 10px;">"您的收件匣有${emailBellData[xe].unreadCount}封未讀信件"</a>`;
                  var str2 = `<a href="/elitebaby/visit/ReportEmailFrontGetAll.html">"您的寄件匣有0封回覆信件"</a>`;
                } else {
                  var str1 = `<a href="/elitebaby/visit/ReportEmailFrontRSMail.html" style="display: block; margin-bottom: 10px;">"您的收件匣有0封未讀信件"</a>`;
                  var str2 = `<a href="/elitebaby/visit/ReportEmailFrontGetAll.html">"您的寄件匣有${emailBellData[xe].unreadCount}封回覆信件"</a>`;
                }
              }
              var compelt = str1 + str2;
              document.querySelector("#popupContent").innerHTML = compelt;
            } else {
              for (let xe = 0; xe < emailBellData.length; xe++) {
                if (emailBellData[xe].from === "admin") {
                  var str1 = `<a href="/elitebaby/visit/ReportEmailFrontRSMail.html" style="display: block; margin-bottom: 10px;">"您的收件匣有${emailBellData[xe].unreadCount}封未讀信件"</a>`;
                  console.log(
                    "您的收件匣有" +
                      emailBellData[xe].unreadCount +
                      "封未讀信件"
                  );
                } else {
                  var str2 = `<a href="/elitebaby/visit/ReportEmailFrontGetAll.html">"您的寄件匣有${emailBellData[xe].unreadCount}封回覆信件"</a>`;
                  console.log(
                    "您的寄件匣有" +
                      emailBellData[xe].unreadCount +
                      "封回覆信件"
                  );
                }
              }
              var compelt = str1 + str2;
              document.querySelector("#popupContent").innerHTML = compelt;
            }
          });

        fetch(
          `/elitebaby/report/emailservlet?action=get_email_chang_status&userId=user${userId}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        )
          .then((resp) => resp.json())
          .then((data) => {});
      });

      const bellbtn = document.querySelector(".bi-bell");
      const popupWrapper = document.getElementById("popupWrapper");
      document.querySelector(".bi-bell").onclick = function () {
        // 顯示小小的視窗
        if (popupWrapper.style.display === "block") {
          popupWrapper.style.display = "none";
        } else {
          popupWrapper.style.display = "block";
        }
      };

      document.addEventListener("click", function (event) {
        if (event.target !== popupWrapper && bellbtn !== event.target) {
          popupWrapper.style.display = "none";
        }
      });

      document.addEventListener("keydown", (event) => {
        if (event.key === "Escape") {
          popupWrapper.style.display = "none";
        }
      });
    } catch {}
  });
// ================================================================================================

// 確認登入狀態
function checkLogin() {
  let root = location.origin;
  let check = "/elitebaby/member/check";
  $.ajax({
    url: root + check, // 資料請求的網址
    type: "GET", // GET | POST | PUT | DELETE | PATCH
    // data: 物件資料,             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (resp) {
      // request 成功取得回應後執行
      if (resp.message == "已登入") {
        // console.log(resp);
        console.log(resp.message);
        // var loginButton =  $('#loginButton');
        // loginButton.remove();
        // var registerButton = $('#registerButton');
        // registerButton.remove();
        $(".checkIfIn").css("display", "block");
      } else {
        console.log(resp.message);
        //           var member = $('.member');
        //           member.remove();
        //           var logoutButton = $("#logoutButton");
        //           logoutButton.remove();
        //           const cartButtons = document.querySelectorAll('button#cart_btn');

        // cartButtons.forEach((button) => {
        //   button.remove();
        // });
        $(".checkIfOut").css("display", "block");
      }
    },
  });
}
checkLogin();

//   彈跳視窗 會員資料編輯
function getAPI() {
  $.ajax({
    url: "/elitebaby/member/Find",
    method: "GET",
    dataType: "json",
    contentType: "application/json",

    success: function (response) {
      $("#name").val(response.username);
      $("#address").val(response.address);
      $("#phoneNumber").val(response.phoneNumber);
    },
    error: function (error) {
      alert("Error calling API:" + error);
    },
  });
}

// --------- 會員首頁js --------------
function callAPI() {
  var username = $("#name").val();
  var address = $("#address").val();
  var phoneNumber = $("#phoneNumber").val();
  var password = $("#password").val();
  var confirmpassword = $("#confirmpassword").val();

  var error = true;
  if (password != confirmpassword) {
    alert("密碼與確認密碼不一致，請重新輸入。");
    error = false;
  }
  if (username == null || username == "") {
    alert("名字不可為空白!");
    error = false;
  }
  if (address == null || address == "") {
    alert("地址不可為空白!");
    error = false;
  }
  if (phoneNumber == null || phoneNumber == "") {
    alert("手機不可為空白!");
    error = false;
  }
  if (
    password == null ||
    password == "" ||
    confirmpassword == null ||
    confirmpassword == ""
  ) {
    alert("密碼或確認密碼不得為空!");
    error = false;
  }

  if (password == "") {
    alert("請輸入密碼");
    $("input#password").focus();
    return (error = false);
  } else {
    var passwordRegxp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if (passwordRegxp.test(password) != true) {
      alert("密碼格式錯誤");
      alert("密碼必須包含至少一個字母和一個數字，長度至少為8位");
      $("input#password").focus();
      $("input#password").select();
      return (error = false);
    }
  }

  // 使用AJAX傳送表單數據
  if (error) {
    $.ajax({
      url: "change",
      method: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify({
        username: username,
        password: password,
        address: address,
        phoneNumber: phoneNumber,
      }),
      success: function (response) {
        alert(response.message);
        if (response.message == "修改成功") {
          $(location).attr("href", "homepage.html");
        }
      },
      error: function (error) {
        alert("Error calling API:" + error);
      },
    });
  }
}

$("button.btn_save").on("click", function () {
  callAPI();
});
$("button.btn_edit").on("click", function () {
  getAPI();
});
$("button#logoutButton").on("click", function () {
  location.href = "/elitebaby/member/logout.html";
});
// ======================購物車按鈕觸發===========================
$("button#cart_btn:first").on("click", function () {
  location.href = "/elitebaby/meal/cart.html";

  // fetch("/elitebaby/Cart?name=tocart", {
  //     method: "POST",
  //     headers: {
  //         "Content-Type": "application/json;charset=UTF-8",
  //         "Access-Control-Allow-Origin": "*",
  //     },
  //     body: JSON.stringify({
  //         userId: userId,
  //     }),
  // })
  //     .then((resp) => {
  //         if (resp.status === 204) {
  //             console.log("resp.status===" + resp.status);
  //         } else {
  //             return resp.json();
  //         }
  //     })
  //     .then((body) => {
  //         try {
  //             if (body.msg == "為已登入狀態") {
  //                 location.href = "/elitebaby/meal/cart.html";
  //             } else {
  //                 alert("跳轉到登入頁面(未引入)");
  //                 // console.log("新增失敗");
  //                 location.href = "/elitebaby/member/login.html";
  //             }
  //         } catch (error) {
  //             alert("出現錯誤!!!!");
  //             // location.reload();
  //             console.log(error + "，跳轉購物車頁面失敗");
  //         }
  //     });
});

// ======================購物車商品筆數請求===========================
fetch("/elitebaby/Cart?name=getcart")
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
        if (body.msg == "為已登入狀態") {
          console.log("已登入並取得購物車內數量");
          $("span#cartCount").html(body.cartcount);
        } else {
          console.log("尚未登入");
        }
        // console.log("ttttt");
        // $("ul.meal_block").html(li_str);
      }
    } catch (error) {
      console.log(error + "，資料庫沒照片故後端沒回傳");
    }
  });

$(document).ready(function () {
  $("li.nav-item a.nav-link[href='../forum/home']")
    .parent()
    .css("display", "none");
});
