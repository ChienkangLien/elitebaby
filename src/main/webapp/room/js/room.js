let all_room_type_btn = document.querySelectorAll('.room_type_btn');
let order_start_date = document.querySelector('.order_start_date');
let order_end_date = document.querySelector('.order_end_date');
let remark = document.querySelector('.remark');
let all_room_num = document.querySelectorAll('.room_num');

order_start_date.addEventListener('click',function(){
    this.setAttribute('min',new Date().toISOString().split("T")[0])
})
order_start_date.addEventListener('change',function(){
    order_end_date.removeAttribute('readonly');
    order_end_date.value="";
})

order_end_date.addEventListener('click',function(){
    this.setAttribute('min',order_start_date.value)
})

all_room_type_btn.forEach(function (element) {
    element.addEventListener('click', function () {
        order_end_date.setAttribute('readonly','');
        order_start_date.value="";
        order_end_date.value="";
        remark.value="";
    });
})

for(let i = 0;i<all_room_type_btn.length;i++){
    all_room_type_btn[i].addEventListener('click',function(){
        all_room_num.forEach(function(e){
            e.style.display='none';
        })
        all_room_num[i].style.display='inline-block';
    })
}
