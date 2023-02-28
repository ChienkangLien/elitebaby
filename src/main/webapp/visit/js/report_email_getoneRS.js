
document.querySelector(".sserchId").addEventListener("click",function(){

    document.querySelector(".getall_tb").innerHTML = ""	;
    const userid = document.querySelector(".userIdTest")
    
    
    fetch('/elitebaby/email/getAll?action=get_byUserId', {
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
                    
                if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="後台") {
                    document.querySelector(".getall_tb").innerHTML += `
                        <td>${resData[i].mailId}</td>
                        <td>${resData[i].reportTile}</td>
                        <td>${resData[i].categoryId}</td>
                        <td>${resData[i].preportCreateTime}</td>
                        <td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                        <td>            
                        <FORM METHOD="post" ACTION="/elitebaby/report/emailSetOne?action=get_front_oneall" style="margin-bottom: 0px;">
                             <input type="submit" value="詳細">			     		
                             <input type="hidden" name="authCode"  value="${resData[i].authCode}">
                             <input type="hidden" name="mailId"  value="${resData[i].mailId}">
                          </FORM>
                        </td>
                        <td> <input type="button" id="delete" value="刪除"></td>
                        `;
                        
                    } else if (typeof (resData[i].answerContent) != "string" && resData[i].determine ==="後台") {
                    document.querySelector(".getall_tb").innerHTML += `
                        <td>${resData[i].mailId}</td>
                        <td>${resData[i].reportTile}</td>
                        <td>${resData[i].categoryId}</td>
                        <td>${resData[i].preportCreateTime}</td>
                        <td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                        <td>            
                        <FORM METHOD="post" ACTION="/elitebaby/report/emailSetOne?action=get_front_oneall" style="margin-bottom: 0px;">
                             <input type="submit" value="詳細">			     		
                             <input type="hidden" name="authCode"  value="${resData[i].authCode}">
                             <input type="hidden" name="mailId"  value="${resData[i].mailId}">
                          </FORM>
                        </td>
                        <td> <input type="button" id="delete" value="刪除"></td>
                        `;
                        
                }
                    
                
                
    }
                    
                });
                
                })