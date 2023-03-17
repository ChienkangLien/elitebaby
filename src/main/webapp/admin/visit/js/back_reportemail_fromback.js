fetch(`/elitebaby/report/emailservlet?action=GET_MEMBER`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {
			document.querySelector("#member_info").innerHTML += `<option value="${resData[i].id}${resData[i].username}">`
		}
})



//==========================處理預覽圖=================================================
var p_file_el = document.getElementById("sam_input_emailfile");
var preview_el = document.getElementById("preview");
$("#sam_input_emailfile").change(function() {
	$("#preview").html(""); // 清除預覽
	readURL(this);
});

function readURL(input) {
	if (input.files && input.files.length >= 0) {
		for (var i = 0; i < input.files.length; i++) {
			var reader = new FileReader();
			reader.onload = function(e) {
				var img = $(`<div class='previewtest' style='display: inline-block; min-height: 100px; width: 100px; '><img class= 'preview_img' style='display: inline-block;width: 100%;' src='${e.target.result}' > </div>`);
				$("#preview").append(img);
			}
			reader.readAsDataURL(input.files[i]);
		}
	} else {
		var noPictures = $("<p>目前沒有圖片</p>");
		$("#preview").append(noPictures);
	}
}



//===============================================================================

fetch(`/elitebaby/report/emailservlet?action=GET_ADMIN`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(admin => {
		document.querySelector(".admintest").value = admin.empid;
})






$("#sam_btn_submit").on("click", function() {
	
	var rstee = [];
    const adminid = document.querySelector(".admintest");
			if(adminid.value == null || adminid.value == 0 || adminid.length == 0){
				rstee.push("請先登入\r");
			}

	let reg = /[^0-9]/ig;
	const userid = document.querySelector(".sam_adminId_anwser").value.replace(reg,"");
			if (userid == null || userid <= 0 ) {
				rstee.push("請選擇回報對象\r");
			}
			console.log(userid);
			console.log(userid.length);
	
	const title = document.querySelector("#sam_input_emailtitle");
			if (title.value == null || title.value.trim() == "" || title.length == 0) {
				rstee.push("請輸入標題\r");
				
			}
	
	const category = document.querySelector("#sam_input_emailcategory");
			if (category.value == null || category.value.trim() == "" || category.value == 0) {
				rstee.push("請選擇類別\r");
			}
	
	const remark = document.querySelector(".visitremark");
			if (remark.value == null || remark.value.trim() == "" || remark.length == 0) {
				rstee.push("請輸入回報內容\r");
				
			}



	var img_base64_el = document.querySelectorAll(".preview_img");

	for (var i = 0; i < img_base64_el.length; i++) {
		var base64get = `${img_base64_el[i].getAttribute("src").replace(/^data:image\/(png|jpg|jpeg|gif);base64,/, "")}`;
	}

	if(rstee.length == 0 ){
		sendMessage();
		fetch('/elitebaby/report/emailservlet?action=INSERT_BACK', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				userId: userid,
				adminId : adminid.value,
				categoryId: category.value,
				reportContent: remark.value,
				reportTile: title.value,
				determine : "後台"
				
			})
		})
		.then(resp => resp.json())
		.then(data => {
			
			console.log(img_base64_el.length > 0);
			if (data.successful) {
				
				if (img_base64_el.length > 0) {
					inserPhoto();
				} else {

					Swal.fire({
						title:"成功寄出",
						text: '',
						icon : 'success'
				   }).then((result) => {
					location.href = "back_admin_mailbox.html"
					   });
			   
				}
				
			} else {

				Swal.fire({
					icon: 'error',
					title: '寄出失敗',
					text: '請檢查信件格式是否正確或是重新寄一次'
				  })
				
			}
			
			
		});
		
	}

	if(rstee!=null && rstee.length > 0 ){		
		var emtyarry = [];
		Swal.fire({
		icon: 'error',
		title: '格式錯誤',
		text: `${rstee}`
	  })
		rstee = emtyarry;
	
		}
	
	function inserPhoto() {

		var dddasa = [];
		for (var i = 0; i < img_base64_el.length; i++) {
			dddasa.push(`${img_base64_el[i].getAttribute("src").replace(/^data:image\/(png|jpg|jpeg|gif);base64,/, "")}`);
		}
		fetch('/elitebaby/report/emailservlet?action=insert_reportphoto', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				arryBase64: dddasa,

			})
		})
			.then(resp => resp.json())
			.then(data => {
				
				if (data.successful) {

					Swal.fire({
						title:"成功寄出",
						text: '',
						icon : 'success'
				   }).then((result) => {
					location.href = "back_admin_mailbox.html"
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
		const user = document.querySelector(".sam_adminId_anwser").value.replace(reg,"");
		var MyPoint = `/emailBell/55688`;
			var host = window.location.host;
			var path = window.location.pathname;
			var webCtx = path.substring(0, path.indexOf('/', 1));
			var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
				
			var webSocket;
			webSocket = new WebSocket(endPointURL);
			
			var jsonObj = {
				"status": "unread",
				"from"  : 'admin',
				"userId": user,
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

