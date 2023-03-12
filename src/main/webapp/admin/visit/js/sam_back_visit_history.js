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
                        if(resData[i].visitStatus == 1){
                            allcount +=1 ;
                        }

                };
                if( allcount > 5 ){
                    document.querySelector(".sprn").innerHTML = `
                    <input type="button" value="下一頁" class="next_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small; margin-right: 30px;'>`
                }
                console.log(allcount);
			})
			
		
		
		fetch(`/elitebaby/visit/servlet?action=GETALL_VISIT_PAGE_HISTORY&offset=${offset}`,
			{ header: ("Content-type:application/json;charset=utf-8") })
			.then(resp => resp.json())
			.then(visit => {
				let resData = [];
				resData = visit;
				for (let i = 0; i < resData.length; i++) {
		
					   if( resData[i].visitStatus == 1) {
						document.querySelector(".getall_tb").innerHTML += `
						<tr onclick='trclickvisit(${resData[i].visitId});'>
						<td>${resData[i].visitId}</td>
						<td>${resData[i].strVisitTime}</td>
						<td>[${resData[i].userId}]${resData[i].userName}</td>
						<td>${resData[i].strCreateTime}</td>
						<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
						<td onclick=delete_visit(${resData[i].visitId})>      
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
	console.log(offset);

	fetch(`/elitebaby/visit/servlet?action=GETALL_VISIT_PAGE_HISTORY&offset=${offset}`,
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
			if( resData[i].visitStatus == 1) {
                document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclickvisit(${resData[i].visitId});'>
				<td>${resData[i].visitId}</td>
				<td>${resData[i].strVisitTime}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].strCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick=delete_visit(${resData[i].visitId})>      
					<input type="button" id="delete" value="刪除">					
				</td>
				</tr>
                    `;
            }

			if( allcount > 5 ){
				document.querySelector(".sprn").innerHTML = `
				<input type="button" value="下一頁" class="next_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small; margin-right: 30px;'>`
			  }
			if(page > 1){

				document.querySelector(".sprl").innerHTML = `
				<input type="button" value="上一頁" class="last_page" style='background-color: #3b7bf1bd;border-color: #3b7bf1bd;align-items: center;height: 30px; width: 80px; font-size: small; margin-right: 30px;'>`

			}  
		}
	})

});


$(document).on("click","input.next_page",function(){
	page += 1;
	let offset = (page - 1) * pageSize;
	console.log(offset);
	document.querySelector(".getall_tb").innerHTML = "";
	fetch(`/elitebaby/visit/servlet?action=GETALL_VISIT_PAGE_HISTORY&offset=${offset}`,
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
			if( resData[i].visitStatus == 1) {
                document.querySelector(".getall_tb").innerHTML += `
				<tr onclick='trclickvisit(${resData[i].visitId});'>
				<td>${resData[i].visitId}</td>
				<td>${resData[i].strVisitTime}</td>
				<td>[${resData[i].userId}]${resData[i].userName}</td>
				<td>${resData[i].strCreateTime}</td>
				<td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
				<td onclick=delete_visit(${resData[i].visitId})>      
					<input type="button" id="delete" value="刪除">					
				</td>
				</tr>
                    `;
            }
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




function delete_visit(visitIdss) {

	event.stopPropagation();
 
	 if (confirm("是否確定刪除")) {
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


function trclickvisit(visitIdlink){

	var link = `/elitebaby/visit/servlet?action=getOne_For_Update&&visitid=${visitIdlink}`;
	 window.document.location = link;
 };