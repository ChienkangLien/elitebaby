<%@ page import="forum.pojo.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>菁英產後護理之家</title>
<%--團隊引入開始--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous" />
    <link rel="stylesheet" href="../css/official.css" />
    <link rel="stylesheet" href="../css/front_navbar.css">
<%--團隊引入結束--%>
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
<div id="nav">
    <nav class="navbar navbar-expand-lg navbar-light c1 fw-bold  ">
        <div class="container-fluid" >
            <img src="../images/logo.jpg">
            <a class="navbar-brand" href="/elitebaby/member/homepage.html" id="home">菁英產後護理之家</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" style=" justify-content: space-between;" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">最新消息 </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="/elitebaby/news/front_allnews.jsp">綜合</a></li>
                            <li><a class="dropdown-item" href="/elitebaby/news/front_alldiscount.jsp">優惠</a></li>
                            <li><a class="dropdown-item" href="/elitebaby/news/front_allnormal.jsp">一般</a></li>

                        </ul>
                    <li class="nav-item"><a class="nav-link" href="/elitebaby/about.html">關於我們</a></li>

                    <li class="nav-item"><a class="nav-link" href="/elitebaby/room/introduce.html">房型介紹</a></li>
                    <li class="nav-item"><a class="nav-link" href="/elitebaby/meal/meal_front.html">月子膳食</a></li>
                    <li class="nav-item"><a class="nav-link" href="../forum/home">討論區</a></li>

                    <!-- ====================================會員中心================================================= -->
                    <li class="nav-item dropdown member checkIfIn">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            會員中心
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <button type="button" class=" dropdown-item btn_edit" data-toggle="modal" data-target="#editModal">
                                編輯會員資料
                            </button>

                            <li><a class="dropdown-item" href="/elitebaby/room/order.html">房型訂單</a></li>
                            <li><a class="dropdown-item" href="/elitebaby/meal/user_order.html">商品訂單</a></li>
                            <li> <a class="dropdown-item" href="/elitebaby/visit/VisitRoomFrontGetAll.html">預約訂單</a> </li>
                            <li> <a class="dropdown-item" href="/elitebaby/visit/ReportEmailFrontGetAll.html">寄信匣</a> </li>
                            <li> <a class="dropdown-item" href="/elitebaby/visit/ReportEmailFrontRSMail.html">收信匣</a> </li>
                            <li> <a class="dropdown-item" href="/elitebaby/visit/ReportEmailFrontInsert.html">問題回報</a> </li>
                        </ul>
                    </li>

            </div>
            <!-- ==============================================按鈕觸發============================================ -->
            <!-- ===============================================購物車============================================= -->
            <div id="logout">
                <ul class="navbar-nav navbar-right">
                    <button id="cart_btn"
                            style="border: 0px; margin-right: 10px;margin-top: 5px; " class="c1 checkIfIn"><svg
                            xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                            class="bi bi-cart2" viewBox="0 0 16 16">
                        <path
                                d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l1.25 5h8.22l1.25-5H3.14zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z" />
                    </svg>
                        <span class="badge badge-danger navbar-badge ;" id="cartCount"></span>
                    </button>

                    <!-- ===================================鈴鐺==================================== -->
                    <button id="cart_btn"
                            style="border: 0px; margin-right: 10px; margin-top: 5px;" class="c1 checkIfIn"><svg
                            xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                            class="bi bi-bell" viewBox="0 0 16 16">
                        <path
                                d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
                    </svg>
                        <span class="badge badge-danger navbar-badge emailBill"></span>
                    </button>


                    <!-- Button trigger modal -->
                    <button type="button" id="logoutButton" class="btn btn-primary checkIfIn" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                        登出
                    </button>
                    <button id="registerButton" class="register_btn checkIfOut">註冊</button>
                    <!-- 登入按鈕 -->
                    <button id="loginButton" class="login_btn checkIfOut">登入</button>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div id="book_visit">
    <a href="/elitebaby/visit/VisitRoomFrontInsert.html">
        <img class="img-responsive"
             src="../images/book_visit.png"
        />
    </a>
</div>
<div id="blank_area">
    <!-- 此處留空 -->
</div>
<!-- 會員資料編輯彈跳視窗 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editModalLabel">編輯會員資料</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="name">姓名</label>
              <input type="text" class="form-control" id="name">
            </div>
            <div class="form-group">
              <label for="address">居住地址</label>
              <input type="text" class="form-control" id="address">
            </div>
            <div class="form-group">
              <label for="phoneNumber">手機</label>
              <input type="text" class="form-control" id="phoneNumber">
            </div>
            <div class="form-group">
              <label for="password">密碼</label>
              <input type="password" class="form-control" id="password">
            </div>
            <div class="form-group">
              <label for="confirmpassword">確認密碼</label>
              <input type="password" class="form-control" id="confirmpassword">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary btn_save ">儲存</button>
        </div>
      </div>
    </div>
  </div>
<%--團隊引入結束--%>
<% Access access = (Access) session.getAttribute("access");%>
<%--<header>Header</header>--%>

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
    <div id="publish" class="button btn">我要發文</div>
    <div class="homepage button btn" style="display: none">首頁</div>
    <div class="loginforum button btn" style="display: none">登入</div>
    <div class="logoutforum button btn" style="display: none">登出</div>
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
                <input type="submit" value="標題搜尋" class="button btn">
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
<%--團隊引入開始--%>

<script src="../vendors/jquery/jquery-3.6.3.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous">
</script>
<script src="../js/front_navbar.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<%--團隊引入結束--%>

<script src="../frontForum/js/forum.js"></script>
</html>
