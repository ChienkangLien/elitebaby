

    document.querySelector(".getall_tb").innerHTML = ""	;
    const userid = document.querySelector(".userIdTest")
    
    
    fetch('/elitebaby/report/emailservlet?action=get_byUserId_admin', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(resp => resp.json())
                .then(data => {
            let resData = [];
            resData = data;
        if( resData.length > 0 ){	
            for (let i = 0; i < resData.length; i++) {
                    
                if (typeof (resData[i].answerContent) === "string" && resData[i].determine ==="後台") {
                    document.querySelector(".getall_tb").innerHTML += `
                    <tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
                        <td>${resData[i].mailId}</td>
                        <td>${resData[i].reportTile}</td>
                        <td>${resData[i].reportCategory}</td>
                        <td>${resData[i].strPreportCreateTime}</td>
                        <td><div class="contact_status" style="background-color:green"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                        <td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					    <svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
				        </td>
                    </tr>
                        `;
                        
                    } else if (typeof (resData[i].answerContent) != "string" && resData[i].determine ==="後台") {
                    document.querySelector(".getall_tb").innerHTML += `
                    <tr onclick='trclick("${resData[i].authCode}",${resData[i].mailId});'>
                        <td>${resData[i].mailId}</td>
                        <td>${resData[i].reportTile}</td>
                        <td>${resData[i].reportCategory}</td>
                        <td>${resData[i].strPreportCreateTime}</td>
                        <td><div class="contact_status"><input type="hidden" name="contact_status" id="status2" value=${resData[i].contactSatus}></div></td>
                        <td onclick='deleteemail(${resData[i].mailId},"${resData[i].authCode}")'> 
					    <svg style="height: 30px;" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--! Font Awesome Pro 6.3.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
				        </td>
                    </tr>
                        `;
                        
                }
                    
                
                
             }
         }else{

                Swal.fire({
                    icon: 'error',
                    title: '目前您沒有任何信件',
                    footer: '<a href="ReportEmailFrontInsert.html">有什麼問題想回報嗎?點我立即回報!</a>'
                  })
            
            }	    
                    
                });
                
                




                function deleteemail(mailId,authCode) {
                    console.log(authCode)
                    console.log(mailId)
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
                
                     
                        fetch("/elitebaby/report/emailservlet?action=delete_email", {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            }, body: JSON.stringify({
                                mailId: mailId,
                                authCode : authCode
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
                                        text: '請檢查信件是否存在'
                                      })
                                }
                                
                
                            });
                       }
                  })
                }              

                function trclick(authCode,mailId){
                    console.log("authCode=",authCode)
                    console.log("mailId=",mailId)
                   var link = `/elitebaby/report/emailservlet?action=get_front_oneall&&authCode=${authCode}&&mailId=${mailId}`;
                    window.document.location = link;
                };
                