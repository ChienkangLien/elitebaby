const userid = document.querySelector(".userid");

const name = document.querySelector(".visitname");

const email = document.querySelector(".visitemail");

const phone = document.querySelector(".visitphone");

const visitdate = document.querySelector(".visitdate");

const contacttime = document.querySelector(".contacttime");

const duedate = document.querySelector(".duedate");

const kids = document.querySelector(".kids");

const visitremark = document.querySelector(".visitremark");


fetch("/elitebaby/visit/servlet?action=GET_ONE_VISIT_DATA",
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visitone => {

		name.value = visitone.userName;

		userid.value = visitone.userId;

		email.value = visitone.email;

		phone.value = visitone.phoneNumber;

		visitdate.value = visitone.strVisitTime;

		contacttime.value = visitone.contectTime;

		duedate.value = visitone.strDueDate;

		kids.value = visitone.kids;

		visitremark.value = visitone.remark;

		document.querySelector(".inputuserid").value = visitone.userId;

		document.querySelector(".inputvisitid").value = visitone.visitId;


		if (visitone.visitStatus == 1) {
			$(".yes_visit").prop("checked", true);

			$('.visitname').attr('readonly', true);
			$('.visitemail').attr('readonly', true);
			$('.visitphone').attr('readonly', true);
			$('.visitdate').attr('readonly', true);
			$('.contacttime').attr('disabled', true);
			$('.duedate').attr('readonly', true);
			$('.kids').attr('disabled', true);
			$('.visitremark').attr('readonly', true);

		}

		if (visitone.contactSatus == 1) {
			$(".yes_contact").prop("checked", true);

		}

	


	});


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


document.querySelector("#visitsubmit").addEventListener("click", function() {

	var rstee = [];
	var statusvalue =  $("[name='yesnovisit']:checked").val()
	var statusvalue2 =  $("[name='yesnocontact']:checked").val()
	
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
	console.log(statusvalue);
	console.log(statusvalue2);

	if(rstee.length == 0){
		if(statusvalue == 1 && statusvalue2 == 0){
			Swal.fire({
				icon: 'error',
				title: '尚未接洽',
				text: '接洽完成才能更改狀態成參訪完成'
		   })
		}else{
		Swal.fire({
			title: '確定修改?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '確定',
			cancelButtonText : '取消'
		  }).then((result) => {
	
			if (result.isConfirmed) {
				
		fetch('/elitebaby/visit/servlet?action=UPDATE_VISIT', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				userId: document.querySelector(".userid").value,
				visitId: document.querySelector(".inputvisitid").value,
				userName: document.querySelector(".visitname").value,
				email: document.querySelector(".visitemail").value,
				phoneNumber: document.querySelector(".visitphone").value,
				strVisitTime: document.querySelector(".visitdate").value,
				contectTime: document.querySelector(".contacttime").value,
				strDueDate: document.querySelector(".duedate").value,
				kids: document.querySelector(".kids").value,
				remark: document.querySelector(".visitremark").value,
				visitStatus: statusvalue,
				contactSatus: statusvalue2,
			})
		})
			.then(resp => resp.json())
			.then(data => {

				if (data.successful) {

					
					Swal.fire({
						title:"更新成功",
						text: '',
						icon : 'success'
				   }).then((result) => {

					location.href =  "getall_visit.html";
 									   
					});

				} else {

					Swal.fire({
						icon: 'error',
						title: '更新失敗',
						text: '請檢查輸入格式是否正確'
				   })
				   
				}

			});


	}
	
		  })	
		}
};


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
	console.log(checkdate);
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