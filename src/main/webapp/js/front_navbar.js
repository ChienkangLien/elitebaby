
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

// ======================鈴鐺===========================
var userId = 4;


var MyPoint = `/emailBell/${userId}`;
  var host = window.location.host;
  var path = window.location.pathname;
  var webCtx = path.substring(0, path.indexOf('/', 1));
  var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

  var webSocket;



function get_email_websucket(){
  
webSocket = new WebSocket(endPointURL);

webSocket.onopen = function(event) {
			console.log("Connect Success!");
};

webSocket.onmessage = function(event) {
  var jsonObj = JSON.parse(event.data);
  console.log(jsonObj)
  console.log(jsonObj.from)


  if (typeof(jsonObj.from) ==="string") {
    var bellcounter = parseInt(document.querySelector(".emailBill").outerText);
    console.log(bellcounter);
    if( bellcounter > 0 && bellcounter !== "undefined"){
      bellcounter += 1;
    document.querySelector(".emailBill").innerText = `${bellcounter}`;
    }else{
      document.querySelector(".emailBill").innerText = "1";
    }
  } else {
    // 隱藏提示沒有新郵件
    
  }

};

webSocket.onclose = function(event) {
  console.log("Disconnected!");
};


};

if( userId != null) {

  get_email_websucket();
}


fetch(`/elitebaby/report/emailservlet?action=get_email_redis&userId=user${userId}`, {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json'
  }
})
  .then(resp => resp.json())
  .then(data => {
    let emailBellData = [];
    emailBellData = data
    console.log(data)
    var bell_count ;
    if(data.length==1){  

    if(emailBellData[0].status === "unread"){
      bell_count = parseInt(emailBellData[0].unreadCount);
      console.log(emailBellData[0].unreadCount)
      document.querySelector(".emailBill").innerText = `${bell_count}`;
    }else{
      document.querySelector(".emailBill").innerText = "";
    }

  }else if(data.length==2){
    if(emailBellData[0].status === "unread"){

      var bell_count1 = parseInt(emailBellData[0].unreadCount);
      console.log(bell_count1)

    }else{
      var bell_count1 = 0;
      document.querySelector(".emailBill").innerText = "";
    }

    if(emailBellData[1].status === "unread"){
      var bell_count2 = parseInt(emailBellData[1].unreadCount);
      console.log(bell_count2)

    }else{
      var bell_count2 = 0;
      document.querySelector(".emailBill").innerText = "";
    }
    
    bell_count = bell_count1 + bell_count2;
    console.log(bell_count);

    if(bell_count !== "undefined" && bell_count > 0){
      document.querySelector(".emailBill").innerText = `${bell_count}`;
    }
  }else{
    document.querySelector(".emailBill").innerText = "";
  }
  });



  document.querySelector(".bi-bell").addEventListener("click",function(){

    document.querySelector(".emailBill").innerText = "";


console.log(userId);
	fetch(`/elitebaby/report/emailservlet?action=get_email_redis&userId=user${userId}`, {
     method: 'GET',
    headers: {
    'Content-Type': 'application/json'
     }
    })
      .then(resp => resp.json())
      .then(data => {
        let emailBellData = [];
        emailBellData = data
        console.log(data)
      if(data.length==0){
        document.querySelector("#popupContent").innerHTML =`<a href="/elitebaby/visit/ReportEmailFrontRSMail.html" style="display: block; margin-bottom: 10px;">"您的收件匣有0封未讀信件"</a>
                                                            <a href="/elitebaby/visit/ReportEmailFrontGetAll.html">"您的寄件匣有0封回覆信件"</a>`;
      }else if(data.length==1){
        for(let xe = 0 ; xe<emailBellData.length ; xe++){
          if(emailBellData[xe].from==="admin"){
            var str1 =  `<a href="/elitebaby/visit/ReportEmailFrontRSMail.html" style="display: block; margin-bottom: 10px;">"您的收件匣有${emailBellData[xe].unreadCount}封未讀信件"</a>`;
            var str2 = `<a href="/elitebaby/visit/ReportEmailFrontGetAll.html">"您的寄件匣有0封回覆信件"</a>`;
          }else{
            var str1 =  `<a href="/elitebaby/visit/ReportEmailFrontRSMail.html" style="display: block; margin-bottom: 10px;">"您的收件匣有0封未讀信件"</a>`;
            var str2 = `<a href="/elitebaby/visit/ReportEmailFrontGetAll.html">"您的寄件匣有${emailBellData[xe].unreadCount}封回覆信件"</a>`;
          }
        }
        var compelt = str1+str2
        document.querySelector("#popupContent").innerHTML = compelt;       
      }else{
        for(let xe = 0 ; xe<emailBellData.length ; xe++){
          if(emailBellData[xe].from==="admin"){
            var str1 =  `<a href="/elitebaby/visit/ReportEmailFrontRSMail.html" style="display: block; margin-bottom: 10px;">"您的收件匣有${emailBellData[xe].unreadCount}封未讀信件"</a>`;
            console.log("您的收件匣有"+emailBellData[xe].unreadCount+"封未讀信件");
          }else{
            var str2 = `<a href="/elitebaby/visit/ReportEmailFrontGetAll.html">"您的寄件匣有${emailBellData[xe].unreadCount}封回覆信件"</a>`;
            console.log("您的寄件匣有"+emailBellData[xe].unreadCount+"封回覆信件");
          }
        }
        var compelt = str1+str2
        document.querySelector("#popupContent").innerHTML = compelt; 
      }

    });
    

    fetch(`/elitebaby/report/emailservlet?action=get_email_chang_status&userId=user${userId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(resp => resp.json())
      .then(data => {
 

      });
    
    });
    
    const bellbtn = document.querySelector(".bi-bell");
    const popupWrapper = document.getElementById("popupWrapper");
  document.querySelector(".bi-bell").onclick = function() {
    // 顯示小小的視窗
    if (popupWrapper.style.display === "block") {
      popupWrapper.style.display = "none";
    } else {
      popupWrapper.style.display = "block";
    }
   
  }

  document.addEventListener("click", function(event) {

    if (event.target !== popupWrapper && bellbtn !== event.target) {
      popupWrapper.style.display = "none";
    }
  });

  document.addEventListener("keydown", (event) => {
    if (event.key === "Escape") {
      popupWrapper.style.display = "none";
    }
  });