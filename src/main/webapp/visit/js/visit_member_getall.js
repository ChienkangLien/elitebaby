

document.querySelector(".getall_tb").innerHTML = ""	;



fetch('/elitebaby/visit/servlet?action=GET_ONE_MEMBER_VISIT', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
		})
			.then(resp => resp.json())
			.then(data => {
		let resData = [];
		resData = data;
		console.log(resData);
		
	if( resData.length > 0 ){	
		for (let i = 0; i < resData.length; i++) {
				
				if ( resData[i].contactSatus == 0) {
				document.querySelector(".getall_tb").innerHTML += `
				    <tr onclick='trclickvisit(${resData[i].visitId});'>
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td>${resData[i].strDueDate}</td>
                    <td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
					</tr>
					`;
					
				}
			
			
				if ( resData[i].contactSatus == 1) {
				document.querySelector(".getall_tb").innerHTML += `
				    <tr onclick='trclickvisit(${resData[i].visitId});'>
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td>${resData[i].strDueDate}</td>
                    <td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
					</tr>
					`;
					
				}
	
       	}

	}else{

		Swal.fire({
			icon: 'error',
			title: '目前您沒有預約',
			footer: '<a href="VisitRoomFrontInsert.html">想要預約參觀嗎?點我立即預約</a>'
		  })
	
	}	
			});
			
	




			function trclickvisit(visitIdlink){

			   var link = `/elitebaby/visit/servlet?action=getOne_NO_Update&&visitid=${visitIdlink}`;
				window.document.location = link;
			};






			