
document.querySelector(".sserchId").addEventListener("click",function(){

document.querySelector(".getall_tb").innerHTML = ""	;
const userid = document.querySelector(".userIdTest")


fetch('/elitebaby/report/emailservlet?action=get_byUserId_member', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				userId: userid.value,
			
			})
		})
			.then(resp => resp.json())
			.then(data => {
		let resData = [];
		resData = data;
		for (let i = 0; i < resData.length; i++) {
				
			if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="visit_status" id="status1" ></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					    <input type="button" id="delete" value="刪除">
				</td>
				</tr>
					`;
			} else if (typeof (resData[i].answerContent) !== "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status" ><input type="hidden" name="visit_status" id="status1" ></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					    <input type="button" id="delete" value="刪除">
				</td>
				</tr>
					`;


		    }
				
			
			
    }
				
			});
			
})




function deleteemail(mailId,authCode) {
	console.log(authCode)
	console.log(mailId)
	event.stopPropagation();

	if (confirm("是否確定刪除")) {
		fetch("/elitebaby/report/emailservlet?action=delete_email", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			}, body: JSON.stringify({
				mailId: mailId,
				authCode : authCode
			})

		})
			.then(resp => resp.json())
			.then(data => {
				
				alert(`successful: ${data.successful}
                      message: ${data.message}`)

				if (data.successful) {
					location.reload();
				}


			});
	}else{
		event.stopPropagation();
	}


};




			function trclick(authCode,mailId){
				console.log("authCode=",authCode)
				console.log("mailId=",mailId)
			   var link = `/elitebaby/report/emailservlet?action=get_front_oneall&&authCode=${authCode}&&mailId=${mailId}`;
				window.document.location = link;
			};