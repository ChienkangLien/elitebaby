  let resData = [];
                      fetch("http://localhost:8080/elitebaby/VisitCJson?action=JSON",
                      {header:("Content-type:application/json;charset=utf-8")})
				.then(resp => resp.json())
				.then(visit => {
					resData = visit;
					for(let i =0;i<resData.length;i++){
						
						
						document.querySelector(".getall_tb").innerHTML += `
                    <td>${resData[i].visitId}</td>
                    <td>${resData[i].visitTime}</td>
                    <td>${resData[i].userName}</td>
                    <td>${resData[i].strCreateTime}</td>
                    <td>是否參訪</td>
                    <td>是否接洽</td>
                    <td>            
                     <FORM METHOD="post" ACTION="http://localhost:8080/elitebaby/VisitSetOneUpdate" style="margin-bottom: 0px;">
			     		<input type="submit" value="修改">
			     		<input type="hidden" name="visitid"  value="${resData[i].visitId}">
			     		<input type="hidden" name="action"	value="getOne_For_Update">
			  		</FORM>
                    </td>
                    <td>
                      <FORM METHOD="post" ACTION="/VisitController" style="margin-bottom: 0px;">
                      	 <input type="submit" id="delete"value="刪除">
                         <input type="hidden" name="visitid"  value=${resData[i].visitId}>
                         <input type="hidden" name="userid"  value=${resData[i].userId}>
                         <input type="hidden" name="action" value="delete">
                      </FORM>
                    </td>
                    <input type="hidden" name="visitId"  value="?">
					`;
						
					};
					
                    });
                    
                    
                    
                    
                   