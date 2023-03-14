const userid = document.querySelector(".userid");

const name = document.querySelector(".visitname");

const email = document.querySelector(".visitemail");

const phone = document.querySelector(".visitphone");

const visitdate = document.querySelector(".visitdate");

const contacttime = document.querySelector(".contacttime");

const duedate = document.querySelector(".duedate");

const kids = document.querySelector(".kids");

const visitremark = document.querySelector(".visitremark");



document.querySelector('.getmember').addEventListener('click', () => {

	fetch('/elitebaby/visit/servlet?action=GET_MEMBER_INFO', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			userId:  userid.value,
		})
	})
		.then(resp => resp.json())
		.then(data => {

			if(data != null){

				name.value = data.userName;
				email.value = data.userEmail;
				phone.value = data.phoneNumber;

			}else{
				alert("查無此會員")
			}

		})


})


function validateEmail() {
	const email = document.querySelector('.visitemail').value;
	const regex = /\S+@\S+\.\S+/;

	if (regex.test(email)) {	

	} else {
		document.querySelector('.visitemail').value=""
		Swal.fire({
			icon: 'error',
			title: '請輸入正確的Email格式!'
		  })
		return true;

	}
}
document.querySelector('#visitsubmit').addEventListener('click', () => {
	
	var rstee = [];
	
	if (userid.value.trim() == ""||userid.value == 0) {
		rstee.push("請先登入\r")
	}
	if (name.value.trim() == "") {
		rstee.push("名字格式不正確\r")
	}
	if (email.value.trim() == "" || validateEmail()) {
		rstee.push("信箱格式不正確\r")
	}
	if (phone.value.trim() == "" || phone.value.length != 10 ) {
		rstee.push("電話格式不正確\r")
	}
	if (visitdate.value.trim() == "") {
		rstee.push("預約日期不可空白或空格\r")
	}
	if (duedate.value.trim() == "") {
		rstee.push("預產期不可空白或空格\r")
	}
	console.log(rstee.length);

	if(rstee.length == 0){

	fetch('/elitebaby/visit/servlet?action=MEMBER_INDERT_VISIT', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				userId: userid.value,
				userName: name.value,
				email: email.value,
				phoneNumber: phone.value,
				strVisitTime: visitdate.value,
				contectTime: contacttime.value,
				strDueDate: duedate.value,
				kids: kids.value,
				remark: visitremark.value,
			})
		})
			.then(resp => resp.json())
			.then(data => {


				if (data.successful) {

					
					Swal.fire({
						title:"恭喜預約成功",
						text: '會有專人聯絡您',
						icon : 'success'
				   }).then((result) => {

					location.href = "/elitebaby/visit/VisitRoomFrontGetAll.html"
 									   
					});

				} else {

					Swal.fire({
						icon: 'error',
						title: '預約失敗',
						text: '請重新預約'
				   })
				   
				}


			});
   }

   
	if(rstee !=null && rstee.length != 0 ){

		var emtyarry = [];
		Swal.fire({
			icon: 'error',
			title: '以下格式錯誤',
			text: `${rstee}`
		  })
	
		rstee = emtyarry;
	
	
		} 

});



const today = new Date().toISOString().split('T')[0];
console.log(today)
// 設定日期輸入框最小日期為當前日期
document.getElementById("datepicker").setAttribute("min",today);
document.getElementById("duedateId").setAttribute("min",today);


function validate(input) {
	const chineseRegex = /^[\u4e00-\u9fa5]+$/; // 只允許中文字符
	if (!chineseRegex.test(input.value)) {	
		Swal.fire({
			icon: 'error',
			title: `請輸入中文`,
		  })
		  input.value = "";
		  input.focus();
	}
  }


  document.getElementById("datepicker").addEventListener("change",function(){

	const checkdate = document.getElementById("datepicker").value;

	fetch('/elitebaby/visit/servlet?action=check_visit', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			strVisitTime: checkdate,
		
		})
	})
		.then(resp => resp.json())
		.then(data => { 

			if(data.successful){
				Swal.fire({
					icon: 'error',
					title: `非常抱歉${checkdate}預約已滿`,
					text: `麻煩請重新選擇其他日期`
				  })
				document.getElementById("datepicker").value = "";
			}



		})


})


// ===============================================

$(function() {
	$(".modal").css("display", "block"); // 顯示modal，遮住畫面背景。
	$(".dialog").css("display", "block"); // 顯示dialog。
	
	$(".dialog").animate({			   
	  opacity: '1',
	  top: '100px' // 決定對話框要滑到哪個位置停止。		   
	}, 550);
  });
  
  $(".cancelBtn").click( function () {
	$(".dialog").animate({			   
	  opacity: '0',
	  top: '-50px' // 需與CSS設定的起始位置相同，以保證下次彈出視窗的效果相同。			   
	}, 350, function () {
	  // 此區塊為callback function，會在動畫結束時被呼叫。
	  $(".modal").css("display", "none"); // 隱藏modal。
	  $(".dialog").css("display", "none"); // 隱藏dialog。
	});
  });	