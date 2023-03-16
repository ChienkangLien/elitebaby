//所有post的like添加監聽器click
function likeListener() {
    const likes = document.querySelectorAll(".like");
    for (const like of likes) {
        like.addEventListener("click", () => {
            const showLike = like.querySelector(".showLike");
            fetch("../forum/likeclick?postId=" + like.dataset.value)
                .then(response => response.text())
                .then(text => JSON.parse(text))
                .then(data => {
                    if (data === "login") {
                        window.location.href = "../frontForum/login.html";
                    }
                    if (typeof data === "number") {
                        showLike.innerText = data;
                    }
                })
        })
    }
}

likeListener();

//所有msg的like添加監聽器click
function likeMsgListener() {
    const likes = document.querySelectorAll(".m-like");
    for (const like of likes) {
        like.addEventListener("click", () => {
            const showLike = like.querySelector(".showLike");
            fetch("../msg/likeclick?msgId=" + like.dataset.value)
                .then(response => response.text())
                .then(text => JSON.parse(text))
                .then(data => {
                    if (data === "login") {
                        window.location.href = "../frontForum/login.html";
                    }
                    if (typeof data === "number") {
                        showLike.innerText = data;
                    }
                })
        })
    }
}

//category產生收藏功能
function categoryCollectedListener() {
    const cloneBlock = document.querySelector(".cloneBlock");
    const collections = document.querySelectorAll(".addCollections")
    for (const coll of collections) {
        coll.addEventListener("click", () => {
            fetch("../forum/addCategoryCollect?categoryId=" + coll.dataset.value)
                .then(response => response.text())
                .then(text => JSON.parse(text))
                .then(data => {
                    // console.log(data);
                    if (data === "login") {
                        window.location.href = "../frontForum/login.html";
                    }
                    if (data === true) {
                        //產生收藏文章 clone
                        const cloneCateId = ".category" + coll.dataset.value;
                        const cloneCate = cloneBlock.querySelector(cloneCateId);
                        if (cloneCate == null) {
                            const collClone = coll.parentElement.cloneNode(true);
                            cloneBlock.append(collClone);
                            window.location.reload();
                        }
                    }
                    if (data === false) {
                        //移除收藏文章 clone
                        const cloneCateId = ".category" + coll.dataset.value;
                        const cloneCate = cloneBlock.querySelector(cloneCateId);
                        if (cloneCate != null) {
                            cloneCate.remove();
                        }
                    }
                })
        })
    }
}

categoryCollectedListener();

//填入post-template
function postTemplateFill(post, templatePost) {
    const postClone = templatePost.cloneNode(true);
    postClone.querySelector(".user").textContent = post.userName;
    postClone.querySelector(".date").textContent = post.timestamp;
    postClone.querySelector(".d2").setAttribute('data-value', post.postId);
    postClone.querySelector(".category").textContent = "《" + post.category + "》";
    postClone.querySelector(".topic").textContent = post.topic;
    postClone.querySelector(".content").textContent = post.content;
    postClone.querySelector(".d3").setAttribute('data-value', post.postId);
    postClone.querySelector(".like").setAttribute('data-value', post.postId);
    postClone.querySelector(".showLike").textContent = post.like;
    const postImgs = post.imgs;
    if (postImgs != null && postImgs.length > 0) {
        const postImgsDiv = postClone.querySelector(".post-img");
        for (let i = 0; i < postImgs.length; i++) {
            const imgElement = document.createElement("img");
            imgElement.src = "data:image/jpeg;base64," + postImgs[i];
            postImgsDiv.appendChild(imgElement)
        }
    }
    return postClone
}

//填入msg-template
function msgTemplateFill(msg, templateMsg) {
    const msgClone = templateMsg.cloneNode(true);
    msgClone.querySelector(".user").textContent = msg.userName;
    msgClone.querySelector(".date").textContent = msg.timestamp;
    msgClone.querySelector(".d2").setAttribute('data-value', msg.msgId);
    msgClone.querySelector(".content").textContent = msg.content;
    msgClone.querySelector(".d3").setAttribute('data-value', msg.msgId);
    msgClone.querySelector(".m-like").setAttribute('data-value', msg.msgId);
    msgClone.querySelector(".showLike").textContent = msg.like;

    const msgImgs = msg.imgs;
    if (msgImgs != null && msgImgs.length > 0) {
        const msgImgsDiv = msgClone.querySelector(".msg-img");
        for (let i = 0; i < msgImgs.length; i++) {
            const imgElement = document.createElement("img");
            imgElement.src = "data:image/jpeg;base64," + msgImgs[i];
            msgImgsDiv.appendChild(imgElement)
        }
    }
    return msgClone;
}

//點文章d2區塊，可打開文章
function postEntranceListener() {
    const templatePost = document.querySelector("#post-template").content;
    const templateMsg = document.querySelector("#msg-template").content;
    const templateForm = document.querySelector("#msg-form-template").content;
    const postsD2 = document.querySelectorAll(".d2")
    for (const p of postsD2) {
        p.addEventListener("click", () => {
            fetch("../forum/postBean?postId=" + p.dataset.value)
                .then(response => response.text())
                .then(text => JSON.parse(text))
                .then(data => {
                    console.log(data); //OK
                    const post = data.post;
                    const msgs = data.msgs;

                    const panel = document.querySelector(".right");
                    panel.innerText = '';

                    const postClone = postTemplateFill(post, templatePost);
                    if (!data.edit) {
                        postClone.querySelector(".edit").style.display = "none";
                        postClone.querySelector(".delete").style.display = "none";
                    }
                    panel.appendChild(postClone);

                    for (let i = 0; i < msgs.length; i++) {
                        const msgClone = msgTemplateFill(msgs[i], templateMsg)
                        panel.appendChild(msgClone);
                    }
                    ;

                    if (data.userId > 0) {
                        const formClone = templateForm.cloneNode(true);
                        formClone.querySelector(".user").textContent = data.userName;
                        formClone.querySelector('input[name="postId"]').value = post.postId;
                        const form = formClone.querySelector(".form");
                        form.addEventListener("submit", (event) => {
                            event.preventDefault();
                            fetch('../publish/publishMsg', {
                                body: new FormData(form),
                                method: "post"
                            })
                                .then(response => response.text())
                                .then(text => JSON.parse(text))
                                .then(data => {
                                    // console.log(data);
                                    if (data != null) {
                                        const articles = document.querySelectorAll("article");
                                        const lastArticle = articles[articles.length - 1];
                                        lastArticle.before(msgTemplateFill(data, templateMsg));
                                        form.reset();
                                    }
                                })
                        })
                        panel.appendChild(formClone)
                    }
                    likeListener();
                    likeMsgListener();
                    editListener(post);
                    deleteListener(post);
                })
        })
    }
}

postEntranceListener();
//回首頁
const homepage = document.querySelector(".homepage");
homepage.addEventListener("click", () => {
    window.location.href = "../forum/home";
});
//我要發文按鈕監聽器
const publish = document.querySelector("#publish");
publish.addEventListener("click", () => {
    getPublishForm();
});

//填入發文模板
function fillPostFormTemplate(data, template) {
    const userName = data.userName;
    const categoryNames = data.categoryNames;
    const formClone = template.cloneNode(true);
    formClone.querySelector(".user").textContent = userName;
    const selectCategory = formClone.querySelector(".category");
    for (let i = 0; i < categoryNames.length; i++) {
        const optionElement = document.createElement("option");
        optionElement.value = categoryNames[i];
        optionElement.textContent = categoryNames[i];
        selectCategory.appendChild(optionElement);
    }
    return formClone;
}

//獲取發文模板參數並填入模板
function getPublishForm() {
    const panel = document.querySelector(".right");
    const postFormTemplate = document.querySelector("#post-form-template").content;
    fetch("../publish/getForm")
        .then((response) => response.text())
        .then((text) => JSON.parse(text))
        .then((data) => {
            if (data === "login") {
                window.location.href = "../frontForum/login.html";
            }
            panel.innerText = '';
            const postForm = fillPostFormTemplate(data, postFormTemplate);
            panel.appendChild(postForm);
        });
}

//編輯功能
function editListener(post) {
    const panel = document.querySelector(".right");
    const edit = document.querySelector(".edit");
    const postFormTemplate = document.querySelector("#post-form-template").content;
    edit.addEventListener("click", () => {
        fetch("../publish/getForm")
            .then((response) => response.text())
            .then((text) => JSON.parse(text))
            .then((data) => {
                if (data === "login") {
                    window.location.href = "../frontForum/login.html";
                }
                panel.innerText = '';
                const postForm = fillPostFormTemplate(data, postFormTemplate);//填入部分資料
                postForm.querySelector(".category").value = post.category;
                postForm.querySelector(".topic").value = post.topic;
                postForm.querySelector(".textarea").value = post.content;
                //圖片修改功能
                postForm.querySelector(".form").action = "../publish/update?postId=" + post.postId;
                postForm.querySelector(".label-img").remove();
                postForm.querySelector('input[name="image"]').remove();
                postForm.querySelector('input[name="submit"]').value = "確認編輯";
                panel.appendChild(postForm);
            });
    })
}

//刪除按鈕監聽器
function deleteListener(post) {
    const delete1 = document.querySelector(".delete");
    delete1.addEventListener("click", () => {
        if (confirm("您確定刪除?")) {
            window.location.href = "../publish/delete?postId=" + post.postId;
        }
    });
}

//登入
const login = document.querySelector(".loginforum");
console.log(login)
login.addEventListener("click", () => {
    window.location.href = "../frontForum/login.html";
});

//登出
const logout = document.querySelector(".logoutforum");
logout.addEventListener("click", () => {
    window.location.href = "../access/logout";
});