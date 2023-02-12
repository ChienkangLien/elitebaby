const userid = document.querySelector(".userid");

const name = document.querySelector(".visitname");

const email = document.querySelector(".visitemail");

const phone = document.querySelector(".visitphone");

const visitdate = document.querySelector(".visitdate");

const contacttime = document.querySelector(".contacttime");

const duedate = document.querySelector(".duedate");

const kids = document.querySelector(".kids");

const visitremark = document.querySelector(".visitremark");

document.querySelector('.visitbutton').addEventListener('click', () => {
    fetch('http://localhost:8080/elitebaby/VisitInsertController',{
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
			userId: userid.value,
            userName: name.value,
            email: email.value,
            phoneNumber: phone.value,
            strVisitTime: visitdate.value,
            contectTime: contacttime.value,
            strDueDate: duedate.value,
            kids: kids.value,
            remark: visitremark.value,
        })
    })
    .then(resp => resp.json())
    .then(body => {
      alert("送出")
    });
}); 