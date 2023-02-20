fetch("/elitebaby/visit/getOneUpdate",
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visitone => {

		document.querySelector(".visitname").value = visitone.userName;

		document.querySelector(".userid").value = visitone.userId;

		document.querySelector(".visitemail").value = visitone.email;

		document.querySelector(".visitphone").value = visitone.phoneNumber;

		document.querySelector(".visitdate").value = visitone.strVisitTime;

		document.querySelector(".contacttime").value = visitone.contectTime;

		document.querySelector(".duedate").value = visitone.strDueDate;

		document.querySelector(".kids").value = visitone.kids;

		document.querySelector(".visitremark").value = visitone.remark;

		document.querySelector(".inputuserid").value = visitone.userId;

		document.querySelector(".inputvisitid").value = visitone.visitId;


		if (visitone.visitStatus == 1) {
			$(".yes_visit").prop("checked", true);
		}

		if (visitone.contactSatus == 1) {
			$(".yes_contact").prop("checked", true);

		}

	});





document.querySelector("#visitsubmit").addEventListener("click", function() {
	
	var statusvalue =  $("[name='yesnovisit']:checked").val()
	var statusvalue2 =  $("[name='yesnocontact']:checked").val()
	
	
	var result = confirm("是否確定修改");
	if (result) {
		fetch('/elitebaby/visit/update', {
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

				alert(`successful: ${data.successful}
                      message: ${data.message}`)

				if (data.successful) {
					location.href = "getall_visit.html"
				} else {
					location.reload();
				}

			});


	}
});


