
document.querySelector(".sserchId").addEventListener("click",function(){

document.querySelector(".getall_tb").innerHTML = ""	;
const userid = document.querySelector(".userIdTest")


fetch('/elitebaby/visit/servlet?action=GET_ONE_MEMBER_VISIT', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				userId: userid.value,
			
			})
		})
			.then(resp => resp.json())
			.then(data => {
		let resData = [];
		resData = data;
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
				
			});
			
			})




			function trclickvisit(visitIdlink){

			   var link = `/elitebaby/visit/servlet?action=getOne_NO_Update&&visitid=${visitIdlink}`;
				window.document.location = link;
			};






			