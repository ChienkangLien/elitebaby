

$(document).ready(function() {
    $('#calendar').fullCalendar({
      locale: 'zh-tw', 
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      buttonText: { //各按钮的显示文本信息
        today: '今天',
        month: '月',
        agendaWeek: '周',
        agendaDay: '日',
        listMonth: '日程',
    },
    //      columnFormat: {
    //       month: 'dddd',
    //       week: 'dddd M-d',
    //       day: 'dddd M-d'
    //   },
    //titleFormat: {
    //       month: 'yyyy年 MMMM月',
    //       week: "[yyyy年] MMMM月d日 { '―' [yyyy年] MMMM月d日}",
    //       day: 'yyyy年 MMMM月d日 dddd',
    //   },
    monthNames: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"], //月份全称
    monthNamesShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"], //月份简写
    dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], //周全称
    dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"], //周简写
      events: [], // 空的 events 陣列，等待 fetch 回傳事件資料後加入
      eventClick: function(event) {

        fetch(`/elitebaby/visit/servlet?action=getOne_For_Update&visitid=${event.id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          }, 
    
        })
          .then(resp => {
            if(resp!=null){
              location.href = "update_visit.html"
            }})
          .then(data => {              
          });
        
      }
    });
  
    // fetch 從資料庫中取得事件資料，然後加入 events 陣列
    fetch(`/elitebaby/visit/servlet?action=GETALL_VISIT`,
{ header: ("Content-type:application/json;charset=utf-8") })
      .then(response => response.json())
      .then(data => {
        let events = [];
        data.forEach(event => {

          if(event.contactSatus == 0){
          events.push({
            id: event.visitId,
            title: event.userName,
            start: event.strVisitTime,
            end: event.strVisitTime,
            color: 'red'
          });
        }else{
          events.push({
            id: event.visitId,
            title: event.userName,
            start: event.strVisitTime,
            end: event.strVisitTime,
            color: 'green'
          });

        }
        });
        $('#calendar').fullCalendar('addEventSource', events); // 將事件資料加入 events 陣列
      });
  });