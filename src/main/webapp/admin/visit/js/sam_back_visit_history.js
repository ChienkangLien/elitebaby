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
                    <input type="button" value="下一頁" class="next_page">`
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
							<td>${resData[i].visitId}</td>
							<td>${resData[i].strVisitTime}</td>
							<td>[${resData[i].userId}]${resData[i].userName}</td>
							<td>${resData[i].strCreateTime}</td>
							<td><div class="visit_status" style="background-color:green"><input type="hidden" name="visit_status" id="status1" value=${resData[i].visitStatus}></div></td>
							<td>            
							 <FORM METHOD="post" ACTION="/elitebaby/visit/servlet?action=getOne_For_Update" style="margin-bottom: 0px;">
								 <input type="submit" value="修改">
								 <input type="hidden" name="visitid"  value="${resData[i].visitId}">
								 <input type="hidden" name="action"	value="getOne_For_Update">
							  </FORM>
							</td>
							<td>  
							<div class = "div_delete" visitId="${resData[i].visitId}">              
								   <input type="button" id="delete" value="刪除">
							</div>
							</FORM>
							</td>
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
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>[${resData[i].userId}]${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td><div class="visit_status" style="background-color:green"><input type="hidden" name="visit_status" id="status1" value=${resData[i].visitStatus}></div></td>
                    <td>            
                     <FORM METHOD="post" ACTION="/elitebaby/visit/servlet?action=getOne_For_Update" style="margin-bottom: 0px;">
                         <input type="submit" value="修改">
                         <input type="hidden" name="visitid"  value="${resData[i].visitId}">
                         <input type="hidden" name="action"	value="getOne_For_Update">
                      </FORM>
                    </td>
                    <td>  
                    <div class = "div_delete" visitId="${resData[i].visitId}">              
                           <input type="button" id="delete" value="刪除">
                    </div>
                    </FORM>
                    </td>
                    `;
            }

			if( allcount > 5 ){
				document.querySelector(".sprn").innerHTML = `
				<input type="button" value="下一頁" class="next_page">`
			  }
			if(page > 1){

				document.querySelector(".sprl").innerHTML = `
				<input type="button" value="上一頁" class="last_page">`

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
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>[${resData[i].userId}]${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td><div class="visit_status" style="background-color:green"><input type="hidden" name="visit_status" id="status1" value=${resData[i].visitStatus}></div></td>
                    <td>            
                     <FORM METHOD="post" ACTION="/elitebaby/visit/servlet?action=getOne_For_Update" style="margin-bottom: 0px;">
                         <input type="submit" value="修改">
                         <input type="hidden" name="visitid"  value="${resData[i].visitId}">
                         <input type="hidden" name="action"	value="getOne_For_Update">
                      </FORM>
                    </td>
                    <td>  
                    <div class = "div_delete" visitId="${resData[i].visitId}">              
                           <input type="button" id="delete" value="刪除">
                    </div>
                    </FORM>
                    </td>
                    `;
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
		}
	})
});








	
	




$(document).on("click", "input#delete", function() {


	var result = confirm("是否確定刪除");
	if (result) {
		fetch("/elitebaby/visit/servlet?action=DELETE_ONE_VISIT", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			}, body: JSON.stringify({
				visitId: $(this).closest(".div_delete").attr("visitId")

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
	}

})

