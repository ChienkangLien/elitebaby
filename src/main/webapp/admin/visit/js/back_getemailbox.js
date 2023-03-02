let page = 1;
const pageSize = 5;
let offset = (page - 1) * pageSize;
var allcount


fetch(`/elitebaby/report/emailservlet?action=GETALL_EMAIL_COUNT`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		 allcount = 0;
		 for (let i = 0; i < resData.length; i++) {
				if(resData[i].determine === "後台"){
					allcount +=1 ;
				}

		};
		if( allcount > 5 ){
			document.querySelector(".sprn").innerHTML = `
			<input type="button" value="下一頁" class="next_page">`
		}
		console.log(allcount);
	});





fetch(`/elitebaby/report/emailservlet?action=GETALL_EMAIL_ADMIN&offset=${offset}`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {

			if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="後台") {

				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].mailId}</td>
                    <td>${resData[i].reportCategory}</td>
                    <td>${resData[i].userId}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].reportTile}</td>
                    <td>${resData[i].strPreportCreateTime}</td>
                    <td><div class="visit_status" style="background-color:green"><input type="hidden" name="visit_status" id="status1" ></div></td>
                     <td>
                     <FORM METHOD="post" ACTION="/elitebaby/report/emailservlet?action=get_back_oneall" style="margin-bottom: 0px;">
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
			} else if (typeof (resData[i].answerContent) != "string" && resData[i].determine ==="後台") {

				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].mailId}</td>
                    <td>${resData[i].reportCategory}</td>
                    <td>${resData[i].userId}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].reportTile}</td>
                    <td>${resData[i].strPreportCreateTime}</td>
                    <td><div class="visit_status"><input type="hidden" name="visit_status" id="status1"></div></td>
                      <td>
                     <FORM METHOD="post" ACTION="/elitebaby/report/emailservlet?action=get_back_oneall" style="margin-bottom: 0px;">
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



// $(document).on("click", "input#getone", function() {


// 	fetch("/elitebaby/report/emailSetOne", {
// 		method: 'POST',
// 		headers: {
// 			'Content-Type': 'application/json'
// 		}, body: JSON.stringify({

// 			mailId: $(this).closest(".div_getone").attr("mailId"),
// 			authCode: $(this).closest(".div_getone").attr("authCode"),

// 		})

// 	})
// 		.then(resp => resp.json())
// 		.then(data => {


// 		});


// })



$(document).on("click", "input#delete", function() {
	alert("ok")


});


$(document).on("click","input.last_page",function(){

    page -= 1;
	let offset = (page - 1) * pageSize;
	document.querySelector(".getall_tb").innerHTML = "";
	fetch(`/elitebaby/report/emailservlet?action=GETALL_EMAIL_ADMIN&offset=${offset}`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {

			if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="後台") {

				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].mailId}</td>
                    <td>${resData[i].reportCategory}</td>
                    <td>${resData[i].userId}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].reportTile}</td>
                    <td>${resData[i].strPreportCreateTime}</td>
                    <td><div class="visit_status" style="background-color:green"><input type="hidden" name="visit_status" id="status1" ></div></td>
                     <td>
                     <FORM METHOD="post" ACTION="/elitebaby/report/emailservlet?action=get_back_oneall" style="margin-bottom: 0px;">
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
			} else if (typeof (resData[i].answerContent) != "string" && resData[i].determine ==="後台") {

				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].mailId}</td>
                    <td>${resData[i].reportCategory}</td>
                    <td>${resData[i].userId}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].reportTile}</td>
                    <td>${resData[i].strPreportCreateTime}</td>
                    <td><div class="visit_status"><input type="hidden" name="visit_status" id="status1"></div></td>
                      <td>
                     <FORM METHOD="post" ACTION="/elitebaby/report/emailservlet?action=get_back_oneall" style="margin-bottom: 0px;">
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

			console.log(page)
			if( allcount > 5 ){
				document.querySelector(".sprn").innerHTML = `
				<input type="button" value="下一頁" class="next_page">`
			  }
			  
			if(page > 1){
				
				document.querySelector(".sprl").innerHTML = `
				<input type="button" value="上一頁" class="last_page">`

			}  
			
			if(page == 1){
			
			document.querySelector(".sprl").innerHTML= "";
		}


		}

	});


});




$(document).on("click","input.next_page",function(){

	page += 1;
	let offset = (page - 1) * pageSize;
	document.querySelector(".getall_tb").innerHTML = "";
	fetch(`/elitebaby/report/emailservlet?action=GETALL_EMAIL_ADMIN&offset=${offset}`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {

			if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="後台") {

				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].mailId}</td>
                    <td>${resData[i].reportCategory}</td>
                    <td>${resData[i].userId}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].reportTile}</td>
                    <td>${resData[i].strPreportCreateTime}</td>
                    <td><div class="visit_status" style="background-color:green"><input type="hidden" name="visit_status" id="status1" ></div></td>
                     <td>
                     <FORM METHOD="post" ACTION="/elitebaby/report/emailservlet?action=get_back_oneall" style="margin-bottom: 0px;">
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
			} else if (typeof (resData[i].answerContent) != "string" && resData[i].determine ==="後台") {

				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].mailId}</td>
                    <td>${resData[i].reportCategory}</td>
                    <td>${resData[i].userId}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].reportTile}</td>
                    <td>${resData[i].strPreportCreateTime}</td>
                    <td><div class="visit_status"><input type="hidden" name="visit_status" id="status1"></div></td>
                      <td>
                     <FORM METHOD="post" ACTION="/elitebaby/report/emailservlet?action=get_back_oneall" style="margin-bottom: 0px;">
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

		if(page>1){
			if( allcount/(page*5) > page ){
			document.querySelector(".sprl").innerHTML = `
			<input type="button" value="上一頁" class="last_page">`;
			document.querySelector(".sprn").innerHTML = `
			<input type="button" value="下一頁" class="next_page">`;
			
			}
			document.querySelector(".sprl").innerHTML = `
			<input type="button" value="上一頁" class="last_page">`
		}
		if(allcount/(page*5) <= 1){
			document.querySelector(".sprn").innerHTML = ``;

		}
	});


});