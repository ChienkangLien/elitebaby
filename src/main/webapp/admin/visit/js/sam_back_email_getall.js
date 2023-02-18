
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
                    <td>${resData[i].preportCreateTime}</td>
                    <td>有回覆</td>
                    <td>  
                    <div class = "div_delete" visitId="${resData[i].mailId}">              
                      	 <input type="button" id="delete" value="刪除">
                    </div>
                    </FORM>
                    </td>
					`;
			} else {

				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].mailId}</td>
                    <td>${resData[i].categoryId}</td>
                    <td>${resData[i].userId}</td>
                    <td>${resData[i].adminId}</td>
                    <td>${resData[i].reportTile}</td>
                    <td>${resData[i].preportCreateTime}</td>
                    <td>未回覆</td>
                    <td>  
                    <div class = "div_delete" visitId="${resData[i].mailId}">              
                      	 <input type="button" id="delete" value="刪除">
                    </div>
                    </FORM>
                    </td>
					`;

			}



		}

	});
