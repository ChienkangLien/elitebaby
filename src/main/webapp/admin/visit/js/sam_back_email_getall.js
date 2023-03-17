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
				<svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
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
				<svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
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
				<svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
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
				<svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
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
				<svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
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
				<svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
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
	let serchvalue =  document.querySelector(".selcet_pk").value;
	
	if(serchdetile==="userId"){
		console.log(typeof(serchvalue))
	let reg = /[^0-9]/ig;
	serchvalue =  document.querySelector(".selcet_pk").value.replace(reg,"");
	}
	if(serchvalue===""){
		Swal.fire({
			icon: 'error',
			title: '不會打字是不是',
		  }).then((result) => {
			location.reload();
		  });
	}else{

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

			Swal.fire({
				icon: 'error',
				title: '查無資料',
			  }).then((result) => {
				location.reload();
			  });	

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
				<svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
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
				<svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
				</td>
			    </tr>
					`;


			}

    }
		});

	}
});


function emailcategoery (){
	const serchdetile = document.querySelector(".selcet_email").value;
	document.querySelector(".selcet_pk").value = "";
	document.querySelector("#report_categoery").innerHTML = ``;
	if(serchdetile === "category"){
		document.querySelector(".selcet_pk").setAttribute("list","report_categoery")
		document.querySelector("#report_categoery").innerHTML = `<option >訂房</option>
		<option >參訪</option>
		<option >訂餐</option>
		<option >試吃</option>
		<option >文章</option>
		<option >消息</option>
		<option >會員</option>
		<option >其他</option>`;
	}

	if(serchdetile === "likesome"){
		document.querySelector(".selcet_pk").removeAttribute("list");
	}

	if( serchdetile === "userId"){

	document.querySelector(".selcet_pk").setAttribute("list","report_categoery")

	fetch(`/elitebaby/report/emailservlet?action=GET_MEMBER`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {
			document.querySelector("#report_categoery").innerHTML += `<option value="${resData[i].id}${resData[i].username}">`
		}
	})

	}

}

fetch(`/elitebaby/report/emailservlet?action=GET_MEMBER`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {
			document.querySelector("#report_categoery").innerHTML += `<option value="${resData[i].id}${resData[i].username}">`
		}
	})



function deleteemail(mailId,authCode) {
	console.log(authCode)
	console.log(mailId)
	event.stopPropagation();

	
	Swal.fire({
		title: '確定刪除?',
		text: "刪除就找不回來囉!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '確定',
		cancelButtonText : '取消'
	  }).then((result) => {

		if (result.isConfirmed) {



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

				if(data.successful){
					
					Swal.fire({
                     title:"刪除成功",
					 text: '',
					 icon : 'success'
				}).then((result) => {
  					location.reload();
					});
			
				}else{					
					Swal.fire({
						icon: 'error',
						title: '刪除失敗',
						text: '請檢查信件是否存在'
					  })
				}


			});

	}

 	})
};






function trclick(authCode,mailId){
	console.log("authCode=",authCode)
	console.log("mailId=",mailId)
   var link = `/elitebaby/report/emailservlet?action=get_back_oneall&&authCode=${authCode}&&mailId=${mailId}`;
	window.document.location = link;
};