
let page = 1;
const pageSize = 5;
const offset = (page - 1) * pageSize;

document.querySelector(".last_page").addEventListener("click",function(){
	page -= 1;
	console.log(page);
	

});


document.querySelector(".next_page").addEventListener("click",function(){
	page += 1;
	console.log(page);

});





fetch("/elitebaby/visitGetAll?action=GETALL_VISIT",
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visit => {
		let resData = [];
		resData = visit;
		for (let i = 0; i < resData.length; i++) {

			if (resData[i].visitStatus == 1 && resData[i].contactSatus == 0) {
				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>[${resData[i].userId}]${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td><div class="visit_status"><input type="hidden" name="visit_status" id="status1" value=${resData[i].visitStatus} ></div></td>
                    <td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                    <td>            
                     <FORM METHOD="post" ACTION="/elitebaby/visit/setOneUpdate" style="margin-bottom: 0px;">
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

					
		

			if (resData[i].contactSatus == 1 && resData[i].visitStatus == 0) {
				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>[${resData[i].userId}]${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td><div class="visit_status"><input type="hidden" name="visit_status" id="status1" value=${resData[i].visitStatus}></div></td>
                    <td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                    <td>            
                     <FORM METHOD="post" ACTION="/elitebaby/visit/setOneUpdate" style="margin-bottom: 0px;">
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

			if (resData[i].visitStatus == 1 && resData[i].contactSatus == 1) {
				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>[${resData[i].userId}]${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td><div class="visit_status" style="background-color:green"><input type="hidden" name="visit_status" id="status1" value=${resData[i].visitStatus}></div></td>
                    <td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                    <td>            
                     <FORM METHOD="post" ACTION="/elitebaby/visit/setOneUpdate" style="margin-bottom: 0px;">
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


			if (resData[i].visitStatus == 0 && resData[i].contactSatus == 0) {
				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>[${resData[i].userId}]${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td><div class="visit_status" ><input type="hidden" name="visit_status" id="status1" value=${resData[i].visitStatus}></div></td>
                    <td><div class="contact_status" ><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                    <td>            
                     <FORM METHOD="post" ACTION="/elitebaby/visit/setOneUpdate" style="margin-bottom: 0px;">
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
	
	




$(document).on("click", "input#delete", function() {


	var result = confirm("是否確定刪除");
	if (result) {
		fetch("/elitebaby/visit/delete", {
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

