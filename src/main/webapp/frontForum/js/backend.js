//backend主模板
const panel = document.querySelector(".panel");
//討論區管理
const categoryBtn = document.querySelector(".backend-btn");
categoryBtn.addEventListener("click", () => {
    getBackend()
});

function getBackend() {
    //取得話題後台模板並clone實體
    const categoryBackend = document.querySelector(".category-backend-template").content.cloneNode(true);
    //取得資料庫categoryList，並填入checkboxes
    const checkboxes = categoryBackend.querySelector(".checkboxes");
    const checkboxTemp = document.querySelector(".category-checkbox-template").content;
    fetch("../backend/categoryList")
        .then((response) => response.text())
        .then((text) => JSON.parse(text))
        .then((data) => {
            // console.log(data);
            for (let i = 0; i < data.length; i++) {
                let checkboxClone = checkboxTemp.cloneNode(true);
                checkboxClone.querySelector("input[type='checkbox']").id = data[i].id;
                checkboxClone.querySelector(".category-name").innerText = "《" + data[i].category + "》";
                checkboxes.append(checkboxClone);
            }
            panel.innerHTML = '';
            panel.appendChild(categoryBackend);
        })//話題填充完畢

    //新增話題按鈕
    const addCategoryBtn = categoryBackend.querySelector(".add-button")
    addCategoryBtn.addEventListener("click", () => {
        if (!panel.querySelector("form")) {
            //製作新增表單
            let form = document.createElement("form");
            form.innerHTML = '<input type="text" placeholder="話題名稱" name="newCategory">' +
                '<input type="submit" value="確認送出">';
            form.addEventListener("submit", (event) => {
                event.preventDefault();
                const formData = new FormData(form);
                const newCategory = formData.get("newCategory").trim(); // 取得輸入字段的值並去除前後空格
                if (!newCategory) {
                    alert("請輸入話題名稱"); // 如果值為空，提示用戶輸入必要的信息
                    return;
                }
                fetch("../backend/categoryAdd", {
                    method: "post",
                    body: formData
                })
                    .then(response => response.text())
                    .then((text) => JSON.parse(text))
                    .then((data) => {
                        if (data == true) {
                            alert("新增成功")
                        } else {
                            alert("話題重複，請重新輸入")
                        }
                    })
            })
            panel.appendChild(form);
        }
    });

    //批量搜尋按鈕
    const SearchBtn = categoryBackend.querySelector(".search-button")
    SearchBtn.addEventListener("click", () => {
        let checkedIds = [];
        const checkboxlist = document.querySelectorAll(".checkbox");
        for (let i = 0; i < checkboxlist.length; i++) {
            const checkbox = checkboxlist[i].querySelector("input[type='checkbox']");
            if (checkbox.checked) {
                checkedIds.push(checkbox.id)
            }
        }
        if (checkedIds.length > 0) {
            const encodedIds = encodeURIComponent(JSON.stringify(checkedIds));
            fetch("../backend/postSearch?ids=" + encodedIds)
                .then((response) => response.text())
                .then((text) => JSON.parse(text))
                .then((data) => {
                    // console.log(data);
                    if (data.length > 0) {
                        panel.innerHTML = '';
                        panel.appendChild(fillPostsTemplate(data));
                    } else {
                        alert("查無資料")
                    }
                })
        } else {
            alert("請選擇要搜尋的分類");
        }
    })

}//getBackend()

//批量搜尋文章，填充模板並回傳posts
function fillPostsTemplate(data) {
    const postsTemp = document.querySelector(".posts-template").content;
    const posts = document.createElement("div");
    posts.classList.add("posts");
    for (let i = 0; i < data.length; i++) {
        const post = postsTemp.cloneNode(true);
        post.querySelector(".user").innerText = data[i].userName;
        post.querySelector(".category").innerText = "《" + data[i].category + "》";
        post.querySelector(".topic").innerText = data[i].topic;
        post.querySelector(".date").innerText = data[i].timestamp;
        post.querySelector(".view").addEventListener("click", () => {
            viewBtnListener(data[i].postId);
        })
        posts.appendChild(post);
    }
    return posts
}

//文章檢視Listener
function viewBtnListener(postId) {
    fetch("../forum/postBean?postId=" + postId)
        .then(response => response.text())
        .then(text => JSON.parse(text))
        .then(data => {
            // console.log(data);
            const postByView = fillPostTemplate(data.post)
            const msgsByView = fillMsgTemplate(data.msgs);
            panel.innerHTML = '';
            panel.appendChild(postByView)
            panel.appendChild(msgsByView);
        })
}

//填入post-template
function fillPostTemplate(postData) {
    const postView = document.createElement("div");
    postView.classList.add("post-view");

    const postTemp = document.querySelector(".post-template").content;
    const post = postTemp.cloneNode(true);
    post.querySelector(".user").innerText = postData.userName;
    post.querySelector(".category").innerText = "《" + postData.category + "》";
    post.querySelector(".topic").innerText = postData.topic;
    post.querySelector(".date").innerText = postData.timestamp;
    post.querySelector(".content").innerText = postData.content;

    const postImgs = post.querySelector(".img");
    const postDataImgs = postData.imgs;
    if (postDataImgs != null && postDataImgs.length > 0) {
        for (let i = 0; i < postDataImgs.length; i++) {
            const postImg = document.createElement("img");
            postImg.src = "data:image/jpeg;base64," + postDataImgs[i];
            postImgs.appendChild(postImg)
        }
    }

    post.querySelector(".post-delete").addEventListener("click", () => {
        postDelete(postData.postId);
    })

    postView.appendChild(post)
    return postView
}

//刪除文章
function postDelete(postId) {
    if (confirm("您確定刪除?")) {
        fetch("../backend/deletePost?postId=" + postId)
            .then(response => response.text())
            .then(text => JSON.parse(text))
            .then(data => {
                if (data == true) {
                    alert("刪除成功")
                    window.location.href = "../frontForum/backend.html";
                } else {
                    alert("刪除失敗")
                }
            })
    }
}

//填入msg-template (回傳一串留言)
function fillMsgTemplate(msgsData) {
    const msgsView = document.createElement("div");
    msgsView.classList.add("msgs-view")

    const msgTemp = document.querySelector(".msg-template").content;
    for (let i = 0; i < msgsData.length; i++) {
        const msgView = document.createElement("div");
        msgView.classList.add("msg-view");
        const msg = msgTemp.cloneNode(true);
        msg.querySelector("input[type='checkbox']").id = msgsData[i].msgId;
        msg.querySelector(".user").innerText = msgsData[i].userName;
        msg.querySelector(".date").innerText = msgsData[i].timestamp;
        msg.querySelector(".content").innerText = msgsData[i].content;
        const msgImgs = msg.querySelector(".img");
        let msgDataImgs = msgsData[i].imgs;
        if (msgDataImgs != null && msgDataImgs.length > 0) {
            for (let i = 0; i < msgDataImgs.length; i++) {
                const msgImg = document.createElement("img");
                msgImg.src = "data:image/jpeg;base64," + msgDataImgs[i];
                msgImgs.appendChild(msgImg)
            }
        }
        msgView.appendChild(msg)
        msgsView.append(msgView)
    }
    const msgsDelete = document.createElement("button");
    msgsDelete.classList.add("msg-delete");
    msgsDelete.innerText = "批次刪除留言";
    msgsDelete.addEventListener("click", () => {
        checkedMsgDelete()
    })
    msgsView.appendChild(msgsDelete);

    return msgsView
}


//刪除留言
function checkedMsgDelete() {
    let checkedMsgIds = [];
    const checkboxMsglist = document.querySelectorAll(".checkbox-msg");

    for (let i = 0; i < checkboxMsglist.length; i++) {
        if (checkboxMsglist[i].checked) {
            checkedMsgIds.push(checkboxMsglist[i].id)
        }
    }
    if (confirm("您確定刪除?")) {
        if (checkedMsgIds.length > 0) {
            const encodedIds = encodeURIComponent(JSON.stringify(checkedMsgIds));
            fetch("../backend/deleteMsgs?ids=" + encodedIds)
                .then(response => response.text())
                .then(text => JSON.parse(text))
                .then(data => {

                    if (data > 0) {
                        alert("刪除" + data + "筆留言")
                        window.location.href = "../frontForum/backend.html";
                    } else {
                        alert("刪除失敗")
                    }
                })
        } else {
            alert("請選擇要刪除的留言")
        }
    }
}

