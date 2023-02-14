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
                    <FORM METHOD="post" ACTION="/elitebaby/visit/delete" style="margin-bottom: 0px;">             
                      	 <input type="submit" id="delete" value="刪除">
                         <input type="hidden" name="visitid" class="visitid" value=${resData[i].visitId}>
                         <input type="hidden" name="action" value="delete">
                    </FORM>
                    </td>
					`;

		};

	});



$(document).on("click","#delete",function(){
	document.querySelector("#delete").addEventListener("click", function() {
		var result = confirm("是否確定刪除");
		if (result) {
			fetch("http://localhost:8080/elitebaby/visit/delete", {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json'
				},

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

})