

$(document).ready(function() {
    $('#calendar').fullCalendar({
      locales: 'zh-TW', 
      lang: 'zh-tw',
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      events: [], // 空的 events 陣列，等待 fetch 回傳事件資料後加入
      eventClick: function(event) {
        alert('Event: ' + event.title);
      }
    });
  
    // fetch 從資料庫中取得事件資料，然後加入 events 陣列
    fetch(`/elitebaby/visitGetAll?action=GETALL_VISIT`,
{ header: ("Content-type:application/json;charset=utf-8") })
      .then(response => response.json())
      .then(data => {
        let events = [];
        data.forEach(event => {
          events.push({
            title: event.userName,
            start: event.strVisitTime,
            end: event.strVisitTime
          });
        });
        $('#calendar').fullCalendar('addEventSource', events); // 將事件資料加入 events 陣列
      });
  });