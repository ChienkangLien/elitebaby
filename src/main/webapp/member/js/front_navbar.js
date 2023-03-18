$(function(){

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
        $("#logout").css("display", "block")
    })

    $("#logout_btn").on("click", function () {
        $("#logout").css("display", "none");
        $("#login").css("display", "block");
    })

    //控制主要內容從導覽列高度以下開始
    let nav_height = $('#nav').css('height')
    $('#blank_area').css('height',nav_height)

    $('#book_visit').css('height','20%')
    $('#book_visit').css('width','10%')
        
    // 瀏覽器的寬高有改變時觸發
    window.addEventListener("resize", function(){
    let nav_height = $('#nav').css('height')
    $('#blank_area').css('height',nav_height)

    $('#book_visit').css('height','20%')
    $('#book_visit').css('width','10%')
    });

})

// 確認登入狀態
function checkLogin(){
  let root = location.origin
  let check = "/elitebaby/member/check"
    $.ajax({
    url: root + check,           // 資料請求的網址
    type: "GET",                  // GET | POST | PUT | DELETE | PATCH
    // data: 物件資料,             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    success: function(resp){      // request 成功取得回應後執行
      if(resp.message == "已登入"){
          // console.log(resp);
          console.log(resp.message);
          var loginButton =  $('#loginButton');
          loginButton.remove();
          var registerButton = $('#registerButton');
          registerButton.remove();

        }else{ 
          console.log(resp.message);
          var member = $('.member');
          member.remove();
          var logoutButton = $("#logoutButton");
          logoutButton.remove();
        }
    }
  });  
  }
  checkLogin();


//   彈跳視窗 會員資料編輯
  function getAPI(){
    $.ajax({
              url: "Find",
              method: "GET",
              dataType: 'json',
              contentType:"application/json",
              
            success: function(response) {
              $('#name').val(response.username);
              $('#address').val(response.address);
              $('#phoneNumber').val(response.phoneNumber);
           
            },
            error: function(error) {
              alert("Error calling API:" + error);
            }
          });
  }