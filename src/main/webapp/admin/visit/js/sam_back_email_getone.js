fetch(`/elitebaby/report/emailGetOne?action=getEmail`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(email => {

		document.querySelector("#sam_input_emailtitle").value = email.reportTile;

		document.querySelector("#sam_input_emailcategory").value = email.categoryId;

		document.querySelector(".visitremark").value = email.reportContent;

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
					var img = $(`<div class='previewtest' style='display: inline-block; min-height: 100px; width: 100px; '><img class= 'preview_img' style='display: inline-block;width: 100%;' src="data:image/png;base64,${str}" > </div>`);
					$("#preview").append(img);
				}
			}
		}

	});
	
	
