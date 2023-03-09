<%@ page import="forum.pojo.*" %>
<%@ page import="java.util.ArrayList" %>
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

    <template id="post-template">
        <article class="post-article">
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
                <div class="edit">編輯</div>
                <div class="delete">刪除</div>
                <div class="post-img"/>
            </div>
        </article>
    </template>
    <template id="msg-template">
        <article class="msg-article">
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
    <template id="post-form-template">
        <article class="post-form-article">
            <div class="d1">
                <div class="user"></div>
            </div>
            <div class="d2">
                <form
                        class="form"
                        action="../publish/publishPost"
                        method="post"
                        enctype="multipart/form-data"
                >
                    <label for="category">類型:</label>
                    <select class="category" id="category" name="category" required></select>
                    <br/><br/>
                    <label for="topic">標題:</label>
                    <input type="text" id="topic" name="topic" class="topic" required/><br/><br/>

                    <label for="textarea">內容:</label><br>
                    <textarea class="textarea" name="content" required></textarea>
                    <br/><br/>
                    <label class="label-img">插入圖片:</label>
                    <input type="file" name="image" multiple/><br/><br/>
                    <input type="submit" name="submit" value="送出"/>
                </form>
            </div>
            <hr/>
        </article>
    </template>
    <template id="msg-form-template">
        <article class="msg-form-article">
            <div class="d1">
                <div class="user"></div>
            </div>
            <div class="d2">
                <form
                        class="form"
                        action="../publish/publishMsg"
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

    <link type="text/css" rel="stylesheet" href="../frontForum/css/forum.css"/>
</head>
<body>
<% Access access = (Access) session.getAttribute("access");%>
<header>Header</header>

<div class="sticky">
    <div class="welcome">
        <%
            String userName = "訪客";
            if (access != null) {
                userName = access.getUserName();
            }
        %>
        歡迎 <span class="wel-user"><%=userName%></span>
    </div>
    <div class="homepage button">首頁</div>
    <div id="publish" class="button">我要發文</div>
    <div class="login button">登入</div>
    <div class="logout button">登出</div>
</div>

<% boolean order = (boolean) request.getAttribute("order");
    String showOrder = (order) ? "熱門" : "最新";
    String categoryId = (String) request.getAttribute("categoryId");
%>
<div class="before-main">
    <main>
        <section class="left">
            <div class="switch">文章排序:
                <a href="../forum/home?order=<%=order%>&switch=1"><%=showOrder%>
                </a>
            </div>
            <form style="display: inline-block" action="../forum/home">
                <input class="input-text" type="text" placeholder="文字搜尋" name="topic"/>
                <input type="hidden" name="order" value="<%=order%>">
                <input type="hidden" name="categoryId" value="<%=categoryId%>">
                <input type="submit" value="標題搜尋">
            </form>
            <br/>
            <div class="category">
                <div class="text">我的最愛</div>
                <div class="cloneBlock">
                    <%
                        if (access != null) {
                            ArrayList<Category> CCs = (ArrayList<Category>) request.getAttribute("CCs");
                            for (Category cc : CCs) {
                    %>
                    <div class="items category<%=cc.getId()%>">
                        <a href="../forum/home?order=<%=order%>&categoryId=<%=cc.getId()%>">
                            <span class="item"> <%=cc.getCategory()%></span>
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
                <div class="text">話題分類</div>
                <% for (Category lc : LCs) {%>
                <div class="items category<%=lc.getId()%>">
                    <a href="../forum/home?order=<%=order%>&categoryId=<%=lc.getId()%>">
                        <span class="item"> <%=lc.getCategory()%></span>
                    </a>
                    <span class="addCollections" data-value="<%=lc.getId()%>">收藏</span>
                </div>
                <%}%>


                <% for (Category hc : HCs) {%>
                <div class="items category<%=hc.getId()%>">
                    <a href="../forum/home?order=<%=order%>&categoryId=<%=hc.getId()%>">
                        <span class="item"> <%=hc.getCategory()%></span>
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
            <article class="post-article">
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

        </section>
    </main>
</div>
</body>

<script src="../frontForum/js/forum.js"></script>
</html>
