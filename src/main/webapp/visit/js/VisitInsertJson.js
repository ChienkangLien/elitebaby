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

		alert('請輸入正確的Email格式!');
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

				if(data.successful){


				alert(`successful: ${data.successful}
                      message: ${data.message}`)

					location.href = "/elitebaby/visit/VisitRoomFrontGetAll.html"

				}else{

                alert(`successful: ${data.successful}
                      message: ${data.message}`)
					  
				}
				
			});
   }

   
	if(rstee !=null && rstee.length != 0 ){

		alert(rstee);
		var emtyarry = [];
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
	  alert("請輸入中文");  
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
				alert(`${checkdate}預約已滿`)
				document.getElementById("datepicker").value = "";
			}



		})


})


