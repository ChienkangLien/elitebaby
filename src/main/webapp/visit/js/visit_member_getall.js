
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
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td>${resData[i].strDueDate}</td>
                    <td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                    <td>            
                     <FORM METHOD="post" ACTION="/elitebaby/visit/servlet?action=getOne_NO_Update" style="margin-bottom: 0px;">
			     		<input type="submit" value="詳細">
			     		<input type="hidden" name="visitid"  value="${resData[i].visitId}">
			     		<input type="hidden" name="action"	value="getOne_NO_Update">
			  		</FORM>
                    </td>
					`;
					
			}
			
			
			if ( resData[i].contactSatus == 1) {
				document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].strVisitTime}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td>${resData[i].strDueDate}</td>
                    <td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                    <td>            
                     <FORM METHOD="post" ACTION="/elitebaby/visit/servlet?action=getOne_NO_Update" style="margin-bottom: 0px;">
			     		<input type="submit" value="詳細">
			     		<input type="hidden" name="visitid"  value="${resData[i].visitId}">
			     		<input type="hidden" name="action"	value="getOne_NO_Update">
			  		</FORM>
                    </td>
					`;
					
			}
			
			
			
			
}
				
			});
			
			})