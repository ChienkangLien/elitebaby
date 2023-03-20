fetch("/elitebaby/visit/servlet?action=GET_ONE_VISIT_DATA",
	{ header: ("Content-type:application/json;charset=utf-8") })
	.then(resp => resp.json())
	.then(visitone => {

		document.querySelector(".visitname").value = visitone.userName;

		document.querySelector(".visitemail").value = visitone.email;

		document.querySelector(".visitphone").value = visitone.phoneNumber;

		document.querySelector(".visitdate").value = visitone.strVisitTime;

		document.querySelector(".contacttime").value = visitone.contectTime;

		document.querySelector(".duedate").value = visitone.strDueDate;

		document.querySelector(".kids").value = visitone.kids;

		document.querySelector(".visitremark").value = visitone.remark;
		
		console.log(visitone.contactSatus)

		if(visitone.contactSatus == '1'){
			console.log(visitone.contactSatus)
			getalert();
		}

	});





function getalert(){
	$(function() {
		$(".modalnotice").css("display", "block"); // 顯示modal，遮住畫面背景。
		$(".dialognotice").css("display", "block"); // 顯示dialog。
		
		$(".dialognotice").animate({			   
		  opacity: '1',
		  top: '50px' // 決定對話框要滑到哪個位置停止。		   
		}, 550);
	  });
	  
	  $(".cancelBtn").click( function () {
		$(".dialognotice").animate({			   
		  opacity: '0',
		  top: '-50px' // 需與CSS設定的起始位置相同，以保證下次彈出視窗的效果相同。			   
		}, 350, function () {
		  // 此區塊為callback function，會在動畫結束時被呼叫。
		  $(".modalnotice").css("display", "none"); // 隱藏modal。
		  $(".dialognotice").css("display", "none"); // 隱藏dialog。
		});
	  });	

};