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
				if(resData[i].determine === "會員"){
					allcount +=1 ;
				}

		};
		if( allcount > 5 ){
			document.querySelector(".sprn").innerHTML = `
			<input type="button" value="下一頁" class="next_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small;'>`
		}
		console.log(allcount);
	});





fetch(`/elitebaby/report/emailservlet?action=GETALL_EMAIL&offset=${offset}`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {

			if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					<input type="button" id="delete" value="刪除">
				</td>
			    </tr>
					`;
			} else if (typeof (resData[i].answerContent) != "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					<input type="button" id="delete" value="刪除">
				</td>
			    </tr>
					`;


			}



		}

	});




$(document).on("click","input.last_page",function(){

    page -= 1;
	let offset = (page - 1) * pageSize;
	document.querySelector(".getall_tb").innerHTML = "";
	fetch(`/elitebaby/report/emailservlet?action=GETALL_EMAIL&offset=${offset}`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {

			if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					<input type="button" id="delete" value="刪除">
				</td>
			    </tr>
					`;
			} else if (typeof (resData[i].answerContent) != "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					<input type="button" id="delete" value="刪除">
				</td>
			    </tr>
					`;


			}

			console.log(page)
			if( allcount > 5 ){
				document.querySelector(".sprn").innerHTML = `
				<input type="button" value="下一頁" class="next_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small;'>`
			  }
			  
			if(page > 1){
				
				document.querySelector(".sprl").innerHTML = `
				<input type="button" value="上一頁" class="last_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small; margin-right: 30px;'>`

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
	fetch(`/elitebaby/report/emailservlet?action=GETALL_EMAIL&offset=${offset}`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {

			if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					<input type="button" id="delete" value="刪除">
				</td>
			    </tr>
					`;
			} else if (typeof (resData[i].answerContent) != "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
				<input type="button" id="delete" value="刪除">
				</td>
			    </tr>
					`;


			}




		}

		if(page>1){
			if( allcount/(page*5) > page ){
			document.querySelector(".sprl").innerHTML = `
			<input type="button" value="上一頁" class="last_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small; margin-right: 30px;'>`;
			document.querySelector(".sprn").innerHTML = `
			<input type="button" value="下一頁" class="next_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small;'>`;
			
			}
			document.querySelector(".sprl").innerHTML = `
			<input type="button" value="上一頁" class="last_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small; margin-right: 30px;'>`
		}
		if(allcount/(page*5) <= 1){
			document.querySelector(".sprn").innerHTML = ``;

		}
	});


});



$("input#serch").on("click", function() {
	document.querySelector(".getall_tb").innerHTML = "";
	document.querySelector(".sprl").innerHTML = "";
	document.querySelector(".sprn").innerHTML = "";
	const serchdetile = document.querySelector(".selcet_email").value;
	const serchvalue =  document.querySelector(".selcet_pk").value;
	
	if(serchdetile==="userId"){
		console.log(typeof(serchvalue))
		if(typeof(serchvalue)){

		}
	}
	

	fetch(`/elitebaby/report/emailservlet?action=serch_by_info_member&${serchdetile}=${serchvalue}`, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		}
	})
		.then(resp => resp.json())
		.then(data => {
			let resData = [];
		    resData = data;
		if(resData.length == 0){
			alert("沒有資料");
		}
		for (let i = 0; i < resData.length; i++) {

			if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					<input type="button" id="delete" value="刪除">
				</td>
			    </tr>
					`;
			} else if (typeof (resData[i].answerContent) != "string" && resData[i].determine ==="會員") {

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
				<td>${resData[i].mailId}</td>
				<td>${resData[i].reportTile}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].reportCategory}</td>
				<td>${resData[i].strPreportCreateTime}</td>
				<td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					<input type="button" id="delete" value="刪除">
				</td>
			    </tr>
					`;


			}

    }
		});


});


function emailcategoery (){
	const serchdetile = document.querySelector(".selcet_email").value;
	document.querySelector(".selcet_pk").value = "";
	if(serchdetile === "category"){
		document.querySelector("#report_categoery").innerHTML = `<option >訂房</option>
		<option >參訪</option>
		<option >訂餐</option>
		<option >試吃</option>
		<option >文章</option>
		<option >消息</option>
		<option >會員</option>
		<option >其他</option>`;
	}else{
		document.querySelector("#report_categoery").innerHTML = ""
	}
	if( serchdetile === "category" || serchdetile === "likesome"){
		document.querySelector(".selcet_pk").removeAttribute("type");
	}else{
		document.querySelector(".selcet_pk").setAttribute("type","number");
	}
}



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
   var link = `/elitebaby/report/emailservlet?action=get_back_oneall&&authCode=${authCode}&&mailId=${mailId}`;
	window.document.location = link;
};