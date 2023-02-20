<%@ page import="forum.pojo.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="forum.pojo.Category" %>
<%@ page import="login.User" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Forum</title>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
            integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
    />
    <link type="text/css" rel="stylesheet" href="http://localhost:8080/elitebaby/forum.css"/>
</head>
<body>
<header>Header</header>

<div class="sticky">
    <div class="search">
        <span>歡迎 USERNAME</span>
        <span>積分: 100</span>
    </div>
    <div class="homepage">首頁</div>
    <div id="publish">我要發文</div>
    <div class="follow">收藏</div>
</div>


<% boolean order = (boolean) request.getAttribute("order");
    String showOrder = (order) ? "熱門" : "最新";
    String categoryId = (String) request.getAttribute("categoryId");
%>
<div class="before-main">
    <main>
        <section class="left">
            <div class="switch">文章排序:
                <a href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&switch=1"><%=showOrder%>
                </a>
            </div>
            <form style="display: inline-block" action="http://localhost:8080/elitebaby/forum/home">
                <input class="input-text" type="text" placeholder="文字搜尋" name="topic"/>
                <input type="hidden" name="order" value="<%=order%>">
                <input type="hidden" name="categoryId" value="<%=categoryId%>">
                <input type="submit" value="標題搜尋">
            </form>

            <br/>
            <br/>

            <div class="category">
                <div class="text">我的最愛</div>
                <div class="cloneBlock">
                    <%
                        User user = (User) session.getAttribute("user");
                        if (user != null) {
                            ArrayList<Category> CCs = (ArrayList<Category>) request.getAttribute("CCs");
                            System.out.println(CCs);
                            for (Category cc : CCs) {
                    %>
                    <div class="items category<%=cc.getId()%>">
                        <a href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&categoryId=<%=cc.getId()%>">
                            <span class="<%=cc.getImg()%> item"> <%=cc.getCategory()%></span>
                        </a>
                        <span class="addCollections" data-value="<%=cc.getId()%>">收藏</span>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                </br>
                <%
                    ArrayList<Category> LCs = (ArrayList<Category>) request.getAttribute("LCs");
                    ArrayList<Category> HCs = (ArrayList<Category>) request.getAttribute("HCs");
                %>
                <div class="text">幼兒生</div>
                <% for (Category lc : LCs) {%>
                <div class="items category<%=lc.getId()%>">
                    <a href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&categoryId=<%=lc.getId()%>">
                        <span class="<%=lc.getImg()%> item"> <%=lc.getCategory()%></span>
                    </a>
                    <span class="addCollections" data-value="<%=lc.getId()%>">收藏</span>
                </div>
                <%}%>

                <div class="text">大學生</div>
                <% for (Category hc : HCs) {%>
                <div class="items category<%=hc.getId()%>">
                    <a href="http://localhost:8080/elitebaby/forum/home?order=<%=order%>&categoryId=<%=hc.getId()%>">
                        <span class="<%=hc.getImg()%> item"> <%=hc.getCategory()%></span>
                    </a>
                    <span class="addCollections" data-value="<%=hc.getId()%>">收藏</span>
                </div>
                <%}%>
            </div>
        </section>

        <section class="right">
            <%
                ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
            %>
            <% for (Post p : posts) { %>
            <article>
                <div class="d1">
                    <div class="user"><%=p.getUserName()%>
                    </div>
                    </br>
                    <div class="date"><%=p.getTimestamp()%>(<%=p.getPostId()%>)
                    </div>
                </div>
                <div class="d2" data-value="<%=p.getPostId()%>">
                    <div class="category">《<%=p.getCategory()%>》</div>
                    <div class="topic"><%=p.getTopic()%>
                    </div>
                    <p class="content"><%=p.getContent()%>
                    </p>
                </div>
                <hr/>
                <div class="d3">
                    <div class="like" data-value="<%=p.getPostId()%>">
                        <i class="fa-solid fa-thumbs-up"></i>
                        <span class="showLike"><%=p.getLike()%></span>
                    </div>
                    <div class="post-img">
                        <% if (p.getImgs() != null && p.getImgs().size() > 0) { %>
                        <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(p.getImgs().get(0)) %>"
                             alt="view"/>
                        <%}%>
                    </div>
                </div>
            </article>
            <%}%>
            <template id="post-template">
                <article>
                    <div class="d1">
                        <div class="user"></div>
                        </br>
                        <div class="date"></div>
                    </div>
                    <div class="d2" data-value="">
                        <div class="category"></div>
                        <div class="topic"></div>
                        <p class="content"></p>
                    </div>
                    <hr/>
                    <div class="d3">
                        <div class="like" data-value="">
                            <i class="fa-solid fa-thumbs-up"></i>
                            <span class="showLike"></span>
                        </div>
                        <div class="post-img"/>
                    </div>
                </article>
            </template>
            <template id="msg-template">
                <article>
                    <div class="d1">
                        <div class="user"></div>
                        </br>
                        <div class="date"></div>
                    </div>
                    <div class="d2" data-value="">
                        <p class="content"></p>
                    </div>
                    <hr/>
                    <div class="d3">
                        <div class="m-like" data-value="">
                            <i class="fa-solid fa-thumbs-up"></i>
                            <span class="showLike"></span>
                        </div>
                        <div class="msg-img"/>
                    </div>
                </article>
            </template>
            <template id="form-template">
                <article>
                    <div class="d1">
                        <div class="user"></div>
                    </div>
                    <div class="d2">
                        <form
                                class="form"
                                action="http://localhost:8080/elitebaby/publish/publishMsg"
                                method="post"
                                enctype="multipart/form-data"
                        >
                            <input type="hidden" name="postId">
                            <label for="textarea">留言:</label><br>
                            <textarea id="textarea" class="textarea" name="content" required></textarea>
                            <br/><br/>
                            <label>插入圖片:</label>
                            <input type="file" name="image" multiple/><br/><br/>
                            <input type="submit" value="送出"/>
                        </form>
                    </div>
                    <hr/>
                </article>
            </template>
        </section>
    </main>
</div>
</body>

<script>
    //所有post的like添加監聽器click
    function likeListener() {
        const likes = document.querySelectorAll(".like");
        for (const like of likes) {
            like.addEventListener("click", () => {
                const showLike = like.querySelector(".showLike");
                fetch("http://localhost:8080/elitebaby/forum/likeclick?postId=" + like.dataset.value)
                    .then(response => response.text())
                    .then(text => JSON.parse(text))
                    .then(data => {
                        if (data === "login") {
                            window.location.href = "http://localhost:8080/elitebaby/myForum/login.jsp";
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
                fetch("http://localhost:8080/elitebaby/msg/likeclick?msgId=" + like.dataset.value)
                    .then(response => response.text())
                    .then(text => JSON.parse(text))
                    .then(data => {
                        if (data === "login") {
                            window.location.href = "http://localhost:8080/elitebaby/myForum/login.jsp";
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
                fetch("http://localhost:8080/elitebaby/forum/addCategoryCollect?categoryId=" + coll.dataset.value)
                    .then(response => response.text())
                    .then(text => JSON.parse(text))
                    .then(data => {
                        // console.log(data);
                        if (data === "login") {
                            window.location.href = "http://localhost:8080/elitebaby/myForum/login.jsp";
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
        const templateForm = document.querySelector("#form-template").content;
        const postsD2 = document.querySelectorAll(".d2")
        for (const p of postsD2) {
            p.addEventListener("click", () => {
                fetch("http://localhost:8080/elitebaby/forum/postBean?postId=" + p.dataset.value)
                    .then(response => response.text())
                    .then(text => JSON.parse(text))
                    .then(data => {
                        // console.log(data); //OK
                        const post = data.post;
                        const msgs = data.msgs;
                        const length = data.dataLength;

                        const panel = document.querySelector(".right");
                        panel.innerText = '';

                        const postClone = postTemplateFill(post, templatePost);
                        panel.appendChild(postClone);

                        for (let i = 0; i < msgs.length; i++) {
                            const msgClone = msgTemplateFill(msgs[i], templateMsg)
                            panel.appendChild(msgClone);
                        }
                        ;

                        if (data.userId > 0) {
                            const formClone = templateForm.cloneNode(true);
                            formClone.querySelector(".user").textContent = "名字";
                            formClone.querySelector('input[name="postId"]').value = post.postId;
                            const form = formClone.querySelector(".form");
                            form.addEventListener("submit", (event) => {
                                event.preventDefault();
                                fetch('http://localhost:8080/elitebaby/publish/publishMsg', {
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
                                            lastArticle.before(msgTemplateFill(data,templateMsg));
                                            form.reset();
                                        }
                                    })
                            })
                            panel.appendChild(formClone)
                        }
                        likeListener();
                        likeMsgListener();
                    })
            })
        }
    }

    postEntranceListener();
    //回首頁
    const homepage = document.querySelector(".homepage");
    homepage.addEventListener("click", () => {
        window.location.href = "http://localhost:8080/elitebaby/forum/home";
    });
    //發文
    const publish = document.querySelector("#publish");
    publish.addEventListener("click", () => {
        window.location.href = "http://localhost:8080/elitebaby/myForum/publish.html"
    })


</script>

<style>
    * {
        box-sizing: border-box; }

    header {
        font-size: 50px;
        text-align: center;
        background-color: pink; }

    .sticky {
        display: flex;
        flex-wrap: wrap;
        border: 1px dotted black; }
    .sticky .search {
        margin: 0 2rem;
        width: 60%; }
    .sticky .switch {
        width: 10%;
        border: 1px red solid; }
    .sticky .post {
        width: 10%; }
    .sticky .follow {
        width: 10%; }

    main {
        margin: 3rem;
        display: flex;
        border: 1px dotted black; }
    main section.left {
        background-color: antiquewhite;
        width: 20%; }
    main section.left .category {
        border: 1px solid blue; }
    main section.left .category .items {
        border: 1px sandybrown solid;
        padding: 1rem; }
    main section.left div.search-text {
        border: 1px solid red; }
    main section.left ul li {
        border: 1px solid red; }
    main section.left ul li a {
        text-decoration: none; }
    main section.right {
        background-color: antiquewhite;
        margin-left: 2rem;
        width: 70%; }
    main section.right article {
        margin: 1rem;
        background-color: azure; }
    main section.right article .d1 {
        display: flex;
        flex-wrap: wrap;
        padding: 0.5rem;
        border: 1px dotted blue; }
    main section.right article .d1 .user {
        border: 1px solid red; }
    main section.right article .d1 .date {
        border: 1px solid red; }
    main section.right article .d2 {
        display: flex;
        flex-wrap: wrap;
        padding: 0.5rem;
        border: 1px dotted blue; }
    main section.right article .d2 .category {
        border: 1px solid red;
        width: 70%; }
    main section.right article .d2 .topic {
        border: 1px solid red;
        width: 10%; }
    main section.right article .d2 .content {
        border: 1px solid red;
        width: 10%;
        overflow: hidden;
        height: 50px; }
    main section.right article .d2 .form {
        width: 100%; }
    main section.right article .d2 .form .textarea {
        border: 1px solid red;
        width: 100%;
        height: 200px; }
    main section.right article .d3 {
        padding: 0.5rem;
        border: 1px dotted blue; }
    main section.right article .d3 .like {
        border: 1px solid red; }
    main section.right article .d3 .m-like {
        border: 2px solid red; }
    main section.right article .d3 .post-img {
        display: flex;
        flex-wrap: wrap; }
    main section.right article .d3 .post-img img {
        display: inline-block;
        width: 100px;
        height: 100px;
        transition: all 0.5s; }
    main section.right article .d3 .post-img img:hover {
        transform: scale(5);
        cursor: pointer; }
    main section.right article .d3 .msg-img {
        display: flex;
        flex-wrap: wrap; }
    main section.right article .d3 .msg-img img {
        display: inline-block;
        width: 100px;
        height: 100px;
        transition: all 0.5s; }
    main section.right article .d3 .msg-img img:hover {
        transform: scale(5);
        cursor: pointer; }


</style>
</html>
