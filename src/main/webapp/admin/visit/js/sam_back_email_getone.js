fetch(`/elitebaby/report/emailGetOne?action=getEmail`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(email => {


		document.querySelector(".one_emailId").value = email.mailId;

		document.querySelector("#sam_input_emailtitle").value = email.reportTile;

		document.querySelector("#sam_input_emailcategory").value = email.categoryId;

		document.querySelector(".visitremark").value = email.reportContent;






		if (email.adminId != 0) {

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


		}

	});


fetch(`/elitebaby/report/emailGetOne?action=getPhoto`,
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
					var img = `<div class='previewtest' style ='display: inline-block; min-height: 100px; width: 100px;'> <img class='preview_img' style='display: inline-block;width: 100%;' src="data:image/*;base64,${str}" > </div>`;
					$("#preview").append(img);
				}
			}
		}

	});





$(document).on("click", "#sam_btn_submit", function() {

	const adminId = document.querySelector(".sam_adminId_anwser");
	const answer_title = document.querySelector("#sam_input_emailtitle_anwser");
	const answer_remark = document.querySelector("#sam_input_emailcontent_anwser");
	const emailID = document.querySelector(".one_emailId");
	var result = confirm("確認修改");
	if (result) {
		fetch("/elitebaby/report/emailGetOne?action=get_one_answer", {
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
						alert(`successful: ${data.successful}
                      message: ${data.message}`)

					location.href = "getall_email.html"
				} else {
						alert(`successful: ${data.successful}
                      message: ${data.message}`)

				}
			});

	}


})
