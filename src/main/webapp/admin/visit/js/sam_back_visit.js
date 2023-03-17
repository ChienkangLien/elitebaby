
fetch(`/elitebaby/report/emailservlet?action=GET_MEMBER`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {
			document.querySelector("#member_info").innerHTML += `<option value="${resData[i].id}${resData[i].username}">`
		}
	})






let page = 1;
const pageSize = 5;
let offset = (page - 1) * pageSize;
var allcount








		fetch(`/elitebaby/visit/servlet?action=GETALL_VISIT`,
			{ header: ("Content-type:application/json;charset=utf-8") })
			.then(resp => resp.json())
			.then(visit => {
				let resData = [];
				resData = visit;
				 allcount = 0;
                 for (let i = 0; i < resData.length; i++) {
                        if(resData[i].visitStatus == 0){
                            allcount +=1 ;
                        }

                };
                if( allcount > 5 ){
                    document.querySelector(".sprn").innerHTML = `
                    <input type="button" value="下一頁" class="next_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small;'>`
                }
                console.log(allcount);
			})
			
		
		
		fetch(`/elitebaby/visit/servlet?action=GETALL_VISIT_PAGE&offset=${offset}`,
			{ header: ("Content-type:application/json;charset=utf-8") })
			.then(resp => resp.json())
			.then(visit => {
				let resData = [];
				resData = visit;
				for (let i = 0; i < resData.length; i++) {
		
					if( resData[i].contactSatus == 0 && resData[i].visitStatus == 0) {
						document.querySelector(".getall_tb").innerHTML += `
						    <tr onclick='trclickvisit(${resData[i].visitId});'>
							<td>${resData[i].visitId}</td>
							<td>${resData[i].strVisitTime}</td>
							<td>[${resData[i].userId}]${resData[i].userName}</td>
							<td>${resData[i].strCreateTime}</td>
							<td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
							<td onclick="delete_visit(${resData[i].visitId})">   
				     			<svg style="height: 30px;"  xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>					
							</td>
							</tr>
							`;
					}else if(resData[i].contactSatus == 1 && resData[i].visitStatus == 0){
		
						document.querySelector(".getall_tb").innerHTML += `
							<tr onclick='trclickvisit(${resData[i].visitId});'>
							<td>${resData[i].visitId}</td>
							<td>${resData[i].strVisitTime}</td>
							<td>[${resData[i].userId}]${resData[i].userName}</td>
							<td>${resData[i].strCreateTime}</td>
							<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
							<td onclick="delete_visit(${resData[i].visitId})">   
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
	console.log(offset);

	fetch(`/elitebaby/visit/servlet?action=GETALL_VISIT_PAGE&offset=${offset}`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => {
		
		if(page == 1){
			
			document.querySelector(".last_page").remove();
		}

		
		if(resp.status === 200){
			
			return resp.json();
			
		}
		
	
})
	.then(visit => {
		let resData = [];
		resData = visit;
		document.querySelector(".getall_tb").innerHTML = "";
		for (let i = 0; i < resData.length; i++) {
			if( resData[i].contactSatus == 0 && resData[i].visitStatus == 0) {
				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclickvisit(${resData[i].visitId});'>
				<td>${resData[i].visitId}</td>
				<td>${resData[i].strVisitTime}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].strCreateTime}</td>
				<td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick="delete_visit(${resData[i].visitId})">   
				     <svg style="height: 30px;"  xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>					
				</td>
				</tr>
					`;
			}else if(resData[i].contactSatus == 1 && resData[i].visitStatus == 0){

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclickvisit(${resData[i].visitId});'>
				<td>${resData[i].visitId}</td>
				<td>${resData[i].strVisitTime}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].strCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick="delete_visit(${resData[i].visitId})">   
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
				<input type="button" value="上一頁" class="last_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small;  margin-right: 30px;'>`

			}  
		}
	})

});


$(document).on("click","input.next_page",function(){
	page += 1;
	let offset = (page - 1) * pageSize;
	console.log(offset);
	document.querySelector(".getall_tb").innerHTML = "";
	fetch(`/elitebaby/visit/servlet?action=GETALL_VISIT_PAGE&offset=${offset}`,
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => {
		
		if(resp.status === 204){

		   return alert("最後一頁");
		   		   
		}
		
		if(resp.status === 200){
			
			return resp.json();
			
		}
		
		
	
	})
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length ; i++) {
			if( resData[i].contactSatus == 0 && resData[i].visitStatus == 0) {
				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclickvisit(${resData[i].visitId});'>
				<td>${resData[i].visitId}</td>
				<td>${resData[i].strVisitTime}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].strCreateTime}</td>
				<td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick="delete_visit(${resData[i].visitId})">   
				     <svg style="height: 30px;"  xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>					
				</td>
				</tr>
					`;
			}else if(resData[i].contactSatus == 1 && resData[i].visitStatus == 0){

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclickvisit(${resData[i].visitId});'>
				<td>${resData[i].visitId}</td>
				<td>${resData[i].strVisitTime}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].strCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick="delete_visit(${resData[i].visitId})">      
				     <svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>					
				</td>
				</tr>
					`;	



			}
			console.log(allcount/(page*5));
			if(page>1){
				if( allcount/(page*5) > page ){
				document.querySelector(".sprl").innerHTML = `
				<input type="button" value="上一頁" class="last_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small;  margin-right: 30px;'>`;
				document.querySelector(".sprn").innerHTML = `
				<input type="button" value="下一頁" class="next_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small;'>`;
				
				}
				document.querySelector(".sprl").innerHTML = `
				<input type="button" value="上一頁" class="last_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small;  margin-right: 30px;'>`
			}
			if(allcount/(page*5) <= 1){
				document.querySelector(".sprn").innerHTML = ``;

			}
		}
	})
});




function trclickvisit(visitIdlink){

	var link = `/elitebaby/visit/servlet?action=getOne_For_Update&&visitid=${visitIdlink}`;
	 window.document.location = link;
 };




	
	




 function delete_visit(visitIdss) {

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
		 fetch("/elitebaby/visit/servlet?action=DELETE_ONE_VISIT", {
			 method: 'POST',
			 headers: {
				 'Content-Type': 'application/json'
			 }, body: JSON.stringify({
				 visitId: visitIdss
 
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
						text: '請檢查預約是否存在'
					  })
				}
 
			 });
	  }
	})
 
 };


 $("input#serch").on("click", function() {
	document.querySelector(".getall_tb").innerHTML = "";
	document.querySelector(".sprl").innerHTML = "";
	document.querySelector(".sprn").innerHTML = "";

	let reg = /[^0-9]/ig;
	const serchvalue =  document.querySelector(".selcet_pk").value.replace(reg,"");

	if(serchvalue===""){
		Swal.fire({
			icon: 'error',
			title: '不會打字是不是',
		  }).then((result) => {
			location.reload();
		  });
	}else{
	fetch('/elitebaby/visit/servlet?action=SERCH_ONE_MEMBER_VISIT', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			userId:  serchvalue,
		})
	})
		.then(resp => resp.json())
		.then(data => {


			
		let resData = [];
		resData = data;
		let cheackstatus = 0;
			if(data.length != 0){	
				for(let xx = 0 ; xx < data.length; xx++){
                       if(data[xx].visitStatus == 0){	
							 cheackstatus = 1 ;
					   }
				}
			}

		if(data.length==0){
			Swal.fire({
				icon: 'error',
				title: '查無資料',
			  }).then((result) => {
				location.reload();
			  });	

		}

		if(cheackstatus==0){

			Swal.fire({
				icon: 'error',
				title: '查無資料',
			  }).then((result) => {
				location.reload();
			  });	

		}

		for (let i = 0; i < resData.length ; i++) {
			if( resData[i].contactSatus == 0 && resData[i].visitStatus == 0) {
				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclickvisit(${resData[i].visitId});'>
				<td>${resData[i].visitId}</td>
				<td>${resData[i].strVisitTime}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].strCreateTime}</td>
				<td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick="delete_visit(${resData[i].visitId})">   
				     <svg style="height: 30px;"  xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>					
				</td>
				</tr>
					`;
			}else if(resData[i].contactSatus == 1 && resData[i].visitStatus == 0){

				document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclickvisit(${resData[i].visitId});'>
				<td>${resData[i].visitId}</td>
				<td>${resData[i].strVisitTime}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].strCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick="delete_visit(${resData[i].visitId})">      
				     <svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>					
				</td>
				</tr>
					`;	
			}

		}


		});
	}

	});		







		