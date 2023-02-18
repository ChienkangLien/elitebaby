
//==========================處理預覽圖=================================================
var p_file_el = document.getElementById("sam_input_emailfile");
var preview_el = document.getElementById("preview");
$("#sam_input_emailfile").change(function(){
  $("#preview").html(""); // 清除預覽
  readURL(this);
});

function readURL(input){
  if (input.files && input.files.length >= 0) {
    for(var i = 0; i < input.files.length; i ++){
      var reader = new FileReader();
      reader.onload = function (e) {
        var img = $(`<div class='previewtest' style='display: inline-block; min-height: 100px; width: 100px; '><img class= 'preview_img' style='display: inline-block;width: 100%;' src='${e.target.result}' > </div>`);
        $("#preview").append(img);
      }
      reader.readAsDataURL(input.files[i]);
    }
  }else{
     var noPictures = $("<p>目前沒有圖片</p>");
     $("#preview").append(noPictures);
  }
}



//===============================================================================



const userid = document.querySelector(".userId");

const title = document.querySelector("#sam_input_emailtitle");

const category = document.querySelector("#sam_input_emailcategory");

const remark = document.querySelector(".visitremark");




 $("#sam_btn_submit").on("click",function(){
	
var img_base64_el = document.querySelector(".preview_img");
        
var img_base64 = img_base64_el.getAttribute("src").replace(/^data:image\/(png|jpg|jpeg|gif);base64,/, "");

		fetch('/elitebaby/report/emailInsert', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				userId: userid.value,
				categoryId: category.value,
				reportContent: remark.value,
				reportTile: title.value,

			})
		})
			.then(resp => resp.json())
			.then(data => {

				alert(`successful: ${data.successful}
                      message: ${data.message}`)
				
					location.href = "/elitebaby/admin/visit/getall_email.html"

				
			});

	



		



});