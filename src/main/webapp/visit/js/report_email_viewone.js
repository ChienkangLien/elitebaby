fetch(`/elitebaby/report/emailservlet?action=getEmail`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(email => {


		document.querySelector(".one_emailId").value = email.mailId;

		document.querySelector("#sam_input_emailtitle").value = email.reportTile;

		document.querySelector("#sam_input_emailcategory").value = email.categoryId;

		document.querySelector(".visitremark").value = email.reportContent;

		document.querySelector(".one_authcode").value = email.authCode;




		if (email.adminId != 0 && email.determine === "會員" && typeof(email.answerContent) === "string") {

			document.querySelector(".inserthere").innerHTML =
				`
			<div class="sam_div_emailtilte">
					<label id="sam_label_emailtitle">標題:</label> <input type="text"
						id="sam_input_emailtitle_anwser" value="" disabled >
				</div>

				<div class="sam_div_adminId">
					<label id="sam_label_emailtitle">後台管理員ID:</label> <input
						type="text" class="sam_adminId_anwser" disabled>
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



			document.querySelector(".sam_adminId_anwser").value = email.adminId;

			document.querySelector("#sam_input_emailtitle_anwser").value = email.answerTitle;

			document.querySelector("#sam_input_emailcontent_anwser").value = email.answerContent;
				
			getanswerphoto();
		}


		if (email.adminId != 0 && email.determine === "後台" && typeof(email.answerContent) === "string") {

			document.querySelector(".inserthere").innerHTML =
				`
			<div class="sam_div_emailtilte">
					<label id="sam_label_emailtitle">標題:</label> <input type="text"
						id="sam_input_emailtitle_anwser" value="" disabled >
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

			document.querySelector("#sam_input_emailcontent_anwser").value = email.answerContent;
				
			getanswerphoto();
		}


		if(email.determine === "會員" && typeof (email.answerContent) != "string"){
			
			document.querySelector(".inserthere").innerHTML = ""

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
								var img = `<div class='previewtest' style ='display: inline-block; min-height: 100px; width: 100px;'> <img class='preview_img_report' style='display: inline-block;width: 100%;' src="data:image/*;base64,${str}" > </div>`;
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
	var result = confirm("確認修改");
	if (result) {
		fetch("/elitebaby/report/emailservlet?action=get_one_user_answer", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			}, body: JSON.stringify({
				mailId: emailID.value,
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
						
						alert(`successful: ${data.successful}
                      message: ${data.message}`)
					location.href = "ReportEmailFrontRSMail.html"
					}

				} else {
						alert(`successful: ${data.successful}
                      message: ${data.message}`)

				}
			});

	}
}

	if(rstee!=null && rstee.length > 0 ){
		alert(rstee);
		var emtyarry = [];
		rstee = emtyarry;
		}



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
					alert(`successful: ${data.successful}
                      message: ${data.message}`)

					location.href = "ReportEmailFrontRSMail.html"
					
				} else {					
					alert(`successful: ${data.successful}
                      message: ${data.message}`)

				}

			});


	}






})


$("#sam_btn_cancle").on("click", function() {

	var result = confirm("確定取消")
	if(result){
		location.href = "ReportEmailFrontRSMail.html"
	}
	
	})