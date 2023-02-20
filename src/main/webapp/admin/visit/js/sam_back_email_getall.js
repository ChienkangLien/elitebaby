
fetch("/elitebaby/email/getAll?action=GETALL_EMAIL",
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {

			if (typeof (resData[i].answerContent) === "string") {

				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].mailId}</td>
                    <td>${resData[i].categoryId}</td>
                    <td>${resData[i].userId}</td>
                    <td>${resData[i].adminId}</td>
                    <td>${resData[i].reportTile}</td>
                    <td>${resData[i].strPreportCreateTime}</td>
                    <td><div class="visit_status" style="background-color:green"><input type="hidden" name="visit_status" id="status1" ></div></td>
                     <td>
                     <FORM METHOD="post" ACTION="/elitebaby/report/emailSetOne" style="margin-bottom: 0px;">
			     		<input type="submit" value="詳細">			     		
			     		<input type="hidden" name="authCode"  value="${resData[i].authCode}">
			     		<input type="hidden" name="mailId"  value="${resData[i].mailId}">
			  		</FORM>
                    </td>
                    <td>  
                    <div class = "div_delete" mailId="${resData[i].mailId}">              
                      	 <input type="button" id="delete" value="刪除">
                    </div>
                    </td>
					`;
			} else {

				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].mailId}</td>
                    <td>${resData[i].categoryId}</td>
                    <td>${resData[i].userId}</td>
                    <td>${resData[i].adminId}</td>
                    <td>${resData[i].reportTile}</td>
                    <td>${resData[i].strPreportCreateTime}</td>
                    <td><div class="visit_status"><input type="hidden" name="visit_status" id="status1"></div></td>
                      <td>
                     <FORM METHOD="post" ACTION="/elitebaby/report/emailSetOne" style="margin-bottom: 0px;">
			     		<input type="submit" value="詳細">			     		
			     		<input type="hidden" name="authCode"  value="${resData[i].authCode}">
			     		<input type="hidden" name="mailId"  value="${resData[i].mailId}">
			  		</FORM>
                    </td>
                    <td>  
                    <div class = "div_delete" mailId="${resData[i].mailId}">              
                      	 <input type="button" id="delete" value="刪除">
                    </div>
                    </td>
					`;

			}



		}

	});



$(document).on("click", "input#getone", function() {


	fetch("/elitebaby/report/emailSetOne", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		}, body: JSON.stringify({

			mailId: $(this).closest(".div_getone").attr("mailId"),
			authCode: $(this).closest(".div_getone").attr("authCode"),

		})

	})
		.then(resp => resp.json())
		.then(data => {


		});


})



$(document).on("click", "input#delete", function() {
	alert("ok")


})