fetch(`/elitebaby/report/emailservlet?action=GET_ADMIN`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(admin => {
		document.querySelector(".sam_adminId_anwser").value = admin.empid;
})




fetch(`/elitebaby/report/emailservlet?action=getEmail`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(email => {

if(email!=null){
		document.querySelector(".one_emailId").value = email.mailId;

		document.querySelector("#sam_input_emailtitle").value = email.reportTile;

		document.querySelector("#sam_input_emailcategory").value = email.categoryId;

		document.querySelector(".visitremark").value = email.reportContent;

		document.querySelector(".one_authcode").value = email.authCode;

		document.querySelector(".userIdd").value = email.userId;

		if (email.adminId != 0 && email.determine === "會員"  && typeof(email.answerContent) === "string") {

			document.querySelector(".inserthere").innerHTML =
				`
			<div class="sam_div_emailtilte">
					<label id="sam_label_emailtitle">回覆標題:</label> <input type="text"
						id="sam_input_emailtitle_anwser" value="" disabled >
				</div>

				<div class="sam_div_adminId">
					<label id="sam_label_emailtitle">回覆時間</label> <input
						type="text" class="sam_adminId_anwser" disabled>
				</div>

				<div id="sam_div_emailcontent">
					<label id="sam_label_emailcontent">回覆內容:</label>
					<textarea class="visitremark" name="remark"
						id="sam_input_emailcontent_anwser" disabled></textarea>
				</div>


				<div id="preview_back">
					<span class="text">預覽圖</span>
				</div>
		`;



			document.querySelector(".sam_adminId_anwser").value = email.strAnswerCreateTime;

			document.querySelector("#sam_input_emailtitle_anwser").value = email.answerTitle;

			document.querySelector("#sam_input_emailcontent_anwser").value = email.answerContent;
				
			getanswerphoto();
		}



		if (email.adminId != 0 && email.determine === "後台"  && typeof(email.answerContent) === "string") {

			document.querySelector(".inserthere").innerHTML =
				`
			<div class="sam_div_emailtilte">
					<label id="sam_label_emailtitle">標題:</label> <input type="text"
						id="sam_input_emailtitle_anwser" value="" disabled >
				</div>

				<div class="sam_div_adminId" style="display: inline-block;margin-left: 45px;">
					<label id="sam_label_emailtitle">回覆用戶:</label> <input
						type="text" class="sam_adminId_anwser" disabled>
				</div>

				<div class="sam_div_emailcategory"  style="display: inline-block;margin-left: 45px;">
					<label id="sam_label_emailcategory">回覆時間</label> <input
					type="text" id="sam_input_emailcategory" class="answertime"  disabled>
				</div>


				<div id="sam_div_emailcontent">
					<label id="sam_label_emailcontent">內容:</label>
					<textarea class="visitremark" name="remark"
						id="sam_input_emailcontent_anwser" disabled></textarea>
				</div>


				<div id="preview_back">
					<span class="text">預覽圖</span>
				</div>
		`;


		document.querySelector("#sam_input_emailtitle_anwser").value = email.answerTitle;

		document.querySelector(".sam_adminId_anwser").value = email.userName;

		document.querySelector(".answertime").value = email.strAnswerCreateTime;

		document.querySelector("#sam_input_emailcontent_anwser").value = email.answerContent;
				
			getanswerphoto();
		}



		if(email.determine === "後台" && typeof(email.answerContent) != "string"){
			
			document.querySelector(".inserthere").innerHTML = ""

		}


	}else{

		Swal.fire({
			icon: 'error',
			title: '找不到信件',
			text: '請檢查信件是否存在'
		  }).then(result =>{
			location.href = "getall_email.html";
		  })

	}

	});



fetch(`/elitebaby/report/emailservlet?action=getPhoto`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(photo => {


		if (photo.testBase64 != null) {
			var base64 = [];
			base64 = photo.testBase64
			console.log(base64.length);
			for (var i = 0; i < base64.length; i++) {
				var str = base64[i];
				if (str != null) {
					var img = `<div class='previewtest' style ='display: inline-block; min-height: 100px; width: 100px;'> <img class='preview_img_report' style='display: inline-block;width: 100%;' src="data:image/*;base64,${str}" > </div>`;
					$("#preview").append(img);
				}
			}
		}

	});




function getanswerphoto() {
	
	const ansertauthcode = document.querySelector(".one_authcode").value;
	     fetch(`/elitebaby/report/emailservlet?action=get_answerphoto`,{
		            method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					}, body: JSON.stringify({
						authCode : ansertauthcode,
					})

				})
				.then(resp => resp.json())
				.then(photo => {

					if (photo.testBase64 != null) {
						var base64 = [];
						base64 = photo.testBase64
						for (var i = 0; i < base64.length; i++) {
							var str = base64[i];
							if (str != null) {
								var img = `<div class='previewtest' style ='display: inline-block; min-height: 100px; width: 100px;'> <img class='preview_img_answer' style='display: inline-block;width: 100%;' src="data:image/*;base64,${str}" > </div>`;
								$("#preview_back").append(img);
							}
						}
					}

				});
		
}




















//==========================處理預覽圖=================================================
var p_file_el = document.getElementById("sam_input_emailfile");
var preview_el = document.getElementById("preview_back");
$("#sam_input_emailfile").change(function() {
	$("#preview_back").html(""); // 清除預覽
	readURL(this);
});

function readURL(input) {
	if (input.files && input.files.length >= 0) {
		for (var i = 0; i < input.files.length; i++) {
			var reader = new FileReader();
			reader.onload = function(e) {
				var img = $(`<div class='previewtest' style='display: inline-block; min-height: 100px; width: 100px; '><img class= 'preview_img_answer' style='display: inline-block;width: 100%;' src='${e.target.result}' > </div>`);
				$("#preview_back").append(img);
			}
			reader.readAsDataURL(input.files[i]);
		}
	} else {
		var noPictures = $("<p>目前沒有圖片</p>");
		$("#preview_back").append(noPictures);
	}
}



//===============================================================================



$(document).on("click", "#sam_btn_submit", function() {

	var rstee = [];
	const adminId = document.querySelector(".sam_adminId_anwser");
	const answer_title = document.querySelector("#sam_input_emailtitle_anwser");
	if (answer_title.value == null || answer_title.value.trim() == "" || answer_title.length == 0) {
		rstee.push("請輸入標題\r");
	}
	const answer_remark = document.querySelector("#sam_input_emailcontent_anwser");
	if (answer_remark.value == null || answer_remark.value.trim() == "" || answer_remark.length == 0) {
		rstee.push("請輸入回報內容\r");
	}
	const emailID = document.querySelector(".one_emailId");
	
	var img_base64_el = document.querySelectorAll(".preview_img_answer");


	for (var i = 0; i < img_base64_el.length; i++) {
		var base64get = `${img_base64_el[i].getAttribute("src").replace(/^data:image\/(png|jpg|jpeg|gif);base64,/, "")}`;
	}

if(rstee.length == 0){

	Swal.fire({
		title: '確定回覆?',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '確定',
		cancelButtonText : '取消'
	  }).then((result) => {

		if (result.isConfirmed) {

		sendMessage()
		fetch("/elitebaby/report/emailservlet?action=get_one_answer", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			}, body: JSON.stringify({
				mailId: emailID.value,
				adminId: adminId.value,
				answerTitle: answer_title.value,
				answerContent: answer_remark.value,
			})

		})
			.then(resp => resp.json())
			.then(data => {

				if (data.successful) {
					
					if(img_base64_el.length > 0){
						
						inserPhoto()
						
					}else{
						Swal.fire({
							title:"回覆成功",
							text: '',
							icon : 'success'
					   }).then((result) => {
						location.href = "getall_email.html"
						   });
					}

				} else {

					Swal.fire({
						icon: 'error',
						title: '回覆失敗',
						text: '請檢查信件格式是否正確或是重新寄一次'
					  })
				}



			});

	    }
	})
}

if(rstee!=null && rstee.length > 0 ){
	var emtyarry = [];

	Swal.fire({
		icon: 'error',
		title: '以下格式錯誤',
		text: `${rstee}`
	  })
	}

	rstee = emtyarry;
function inserPhoto() {

const authcode = document.querySelector(".one_authcode").value;
		var dddasa = [];
		for (var i = 0; i < img_base64_el.length; i++) {
			dddasa.push(`${img_base64_el[i].getAttribute("src").replace(/^data:image\/(png|jpg|jpeg|gif);base64,/, "")}`);
		}
		fetch('/elitebaby/report/emailservlet?action=insert_answerphoto', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				arryBase64: dddasa,
				authCode:   authcode
			})
		})
			.then(resp => resp.json())
			.then(data => {
				
				if (data.successful) {

					
					Swal.fire({
						title:"回覆成功",
						text: '',
						icon : 'success'
				   }).then((result) => {
					location.href = "getall_email.html"
					   });
					
				} else {		

					Swal.fire({
						icon: 'error',
						title: '圖片新增失敗',
						text: '請檢查圖片格式是否正確'
					  })

				}

			});


	}


	function sendMessage() {
		const useriddd = document.querySelector(".userIdd").value;
		var MyPoint = `/emailBell/55688`;
			var host = window.location.host;
			var path = window.location.pathname;
			var webCtx = path.substring(0, path.indexOf('/', 1));
			var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
				
			var webSocket;
			webSocket = new WebSocket(endPointURL);
			
			var jsonObj = {
				"status": "unread",
				"from"  : 'member',
				"userId": useriddd,
				"unreadCount" : 1
				};
			webSocket.onopen = function(event) {
				console.log("Connect Success!");
	
				webSocket.send(JSON.stringify(jsonObj));
			};
	
	
		   
			webSocket.onclose = function(event) {
			console.log("Disconnected!");
		  };
		};



})




setTimeout(function() {
	const imagesreport = document.querySelectorAll(".preview_img_report");
	const imagesanswer = document.querySelectorAll(".preview_img_answer");
	
    $(function () {
		imagesreport.forEach((imgess) => {
		  imgess.addEventListener("click", (e) => {
			  var imgsrc = $(e.target).attr("src");
		   var large_image = `<img src="${imgsrc}"</img>`;
		   console.log(large_image);
		   getCurrentPhoto()
		   $('#dialog_large_image_report').html($(large_image).animate({ height: '100%', width: '100%' }, 500));
		  });
		 });
	  });
  
	  $(function () {
		  imagesanswer.forEach((imgeyy) => {
			  imgeyy.addEventListener("click", (e) => {
			   var imgsrcss = $(e.target).attr("src");
			   var large_image_ab = `<img src="${imgsrcss}"</img>`;
			   console.log(large_image_ab);
			   getCurrentPhoto()
			   $('#dialog_large_image_report').html($(large_image_ab).animate({ height: '100%', width: '100%' }, 500));
			  });
			 });
	  });
  

 
}, 750);




function getCurrentPhoto(){
	$(".modalimg").css("display", "block"); // 顯示modal，遮住畫面背景。
	$(".dialogimg").css("display", "block"); // 顯示dialog。
	
	$(".dialogimg").animate({			   
	  opacity: '1',
	  top: '50px' // 決定對話框要滑到哪個位置停止。		   
	}, 550);
  };
  
  $(".modalimg").click( function () {
	$(".dialogimg").animate({			   
	  opacity: '0',
	  top: '-50px' // 需與CSS設定的起始位置相同，以保證下次彈出視窗的效果相同。			   
	}, 350, function () {
	  // 此區塊為callback function，會在動畫結束時被呼叫。
	  $(".modalimg").css("display", "none"); // 隱藏modal。
	  $(".dialogimg").css("display", "none"); // 隱藏dialog。
	});
  });	






  $("#sam_btn_cancle").on("click", function() {

	Swal.fire({
		title: '確定取消?',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '確定',
		cancelButtonText : '取消'
	  }).then((result) => {

		if (result.isConfirmed) {
			location.href = "back_admin_mailbox.html"
	  }

	 })
	
	})