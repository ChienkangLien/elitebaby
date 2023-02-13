fetch("http://localhost:8080/elitebaby/VisitGetOneUpdate",
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





document.querySelector("#visitsubmit").addEventListener("click", function() {
 confirm("是否確定修改")
if(confirm!=true){
fetch('http://localhost:8080/elitebaby/VisitRoomUpdate',{
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
			userId: document.querySelector(".userid").value,
			visitId:document.querySelector(".inputvisitid").value,
            userName: document.querySelector(".visitname").value,
            email: document.querySelector(".visitemail").value,
            phoneNumber: document.querySelector(".visitphone").value,
            strVisitTime: document.querySelector(".visitdate").value,
            contectTime: document.querySelector(".contacttime").value,
            strDueDate: document.querySelector(".duedate").value,
            kids: document.querySelector(".kids").value,
            remark: document.querySelector(".visitremark").value,
        })
    })
    .then(resp => resp.json())
    .then(data => {
	
	if(data!=null){
      alert("修改成功");     
                location.href="GetAllVisit.html"
	}
	
    });

                

}
});


