
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







$("#sam_btn_submit").on("click", function() {

    const adminid = document.querySelector(".admintest");


	const userid = document.querySelector(".sam_adminId_anwser");
	//	if (userid.value == null) {
	//		alert("請先登入");
	//	}


	const title = document.querySelector("#sam_input_emailtitle");
	//	if (title.value == null || title.value.trim() == "") {
	//		alert("請輸入標題");
	//	}

	const category = document.querySelector("#sam_input_emailcategory");
	//	if (category.value == null || category.value.trim() == "" || category.value == 0) {
	//		alert("請選擇類別");
	//	}

	const remark = document.querySelector(".visitremark");
	//	if (remark.value == null || remark.value.trim() == "" ) {
	//		alert("請輸入回報內容");
	//	}



	var img_base64_el = document.querySelectorAll(".preview_img");

	for (var i = 0; i < img_base64_el.length; i++) {
		var base64get = `${img_base64_el[i].getAttribute("src").replace(/^data:image\/(png|jpg|jpeg|gif);base64,/, "")}`;
	}

	fetch('/elitebaby/report/emailInsert?action=INSERT_BACK', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			userId: userid.value,
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
					alert(`successful: ${data.successful}
                      message: ${data.message}`)
					location.href = "back_admin_mailbox.html"
				}

			} else {
				alert(`successful: ${data.successful}
                      message: ${data.message}`)
				location.href = "back_admin_mailbox.html"
			}


		});



	function inserPhoto() {

		var dddasa = [];
		for (var i = 0; i < img_base64_el.length; i++) {
			dddasa.push(`${img_base64_el[i].getAttribute("src").replace(/^data:image\/(png|jpg|jpeg|gif);base64,/, "")}`);
		}
		fetch('/elitebaby/report/emailPhotoInsert?action=insert_reportphoto', {
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
					alert(`successful: ${data.successful}
                      message: ${data.message}`)

					location.href = "back_admin_mailbox.html"
					
				} else {					
					alert(`successful: ${data.successful}
                      message: ${data.message}`)

				}

			});


	}


});





