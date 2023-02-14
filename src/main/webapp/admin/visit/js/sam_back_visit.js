
	fetch("/elitebaby/visitGetAll?action=GETALL_VISIT",
		{ header: ("Content-type:application/json;charset=utf-8") })
		.then(resp => resp.json())
		.then(visit => {
			let resData = [];
			resData = visit;
			for (let i = 0; i < resData.length; i++) {


				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].visitTime}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td>是否參訪</td>
                    <td>是否接洽</td>
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

		});




$(document).on("click","input#delete",function(){
	
		var result = confirm("是否確定刪除");
		if (result) {
			fetch("/elitebaby/visit/delete", {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				}, body: JSON.stringify({
					visitId : $(this).closest(".div_delete").attr("visitId")		
					
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

