fetch("/elitebaby/visit/servlet?action=GET_ONE_VISIT_DATA",
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



	});