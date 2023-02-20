const userid = document.querySelector(".userid");

const name = document.querySelector(".visitname");

const email = document.querySelector(".visitemail");

const phone = document.querySelector(".visitphone");

const visitdate = document.querySelector(".visitdate");

const contacttime = document.querySelector(".contacttime");

const duedate = document.querySelector(".duedate");

const kids = document.querySelector(".kids");

const visitremark = document.querySelector(".visitremark");



document.querySelector('#visitsubmit').addEventListener('click', () => {

	if (userid.value.trim() == "") {
		alert("會員id不可空白或空格")
	}
	if (name.value.trim() == "") {
		alert("名字不可空白或空格")
	}
	if (email.value.trim() == "") {
		alert("信箱不可空白或空格")
	}
	if (phone.value.trim() == "") {
		alert("電話不可空白或空格")
	}
	if (visitdate.value.trim() == "") {
		alert("預約日期不可空白或空格")
	}
	if (duedate.value.trim() == "") {
		alert("預產期不可空白或空格")
	}

if (userid.value.trim() != "" && name.value.trim() != "" && email.value.trim() != "" && phone.value.trim() != "" && visitdate.value.trim() != "" && duedate.value.trim() != "") {

		fetch('/elitebaby/visitInsert', {
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

				alert(`successful: ${data.successful}
                      message: ${data.message}`)

				
					location.href = "/elitebaby/visit/VisitRoomFrontGetAll.html"

				
			});


}



});


