<%--
  Created by IntelliJ IDEA.
  User: zz
  Date: 2015/2/6
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3 class="not-visible">Main conrainer</h3>

<aside class="nav-sidebar">
    <div class="nav-sidebar-container">
        <h3 class="nav-sidebar__title">权限管理</h3>
        <nav>
            <a class="navigation-toggle" href="#"><i class="fa fa-bars"></i></a>
            <ul class="navigation">
                <li class="navigation__item"><a class="navigation__link" href="${path}/admin/to/newsView"><i class="fa fa-dashboard"></i>新闻管理</a></li>
                <li class="navigation__item"><a class="navigation__link" href="#"><i class="fa fa-signal"></i>类别管理</a></li>
                <li class="navigation__item"><a class="navigation__link" href="#"><i class="fa fa-folder"></i>标签管理<span class="badge pull-right">26</span></a></li>
                <li class="navigation__item"><a class="navigation__link" href="#"><i class="fa fa-user"></i>用户管理</a></li>

            </ul>
        </nav>
    </div>

    <div class="devider devider--dark"></div>

    <div class="nav-sidebar-container">
        <h3 class="nav-sidebar__title"></h3>
        <ul class="labels">
            <li class="labels__item"><i class="fa fa-circle-o icon--warning"></i>Social</li>
            <li class="labels__item"><i class="fa fa-circle-o icon--danger"></i>College</li>
            <li class="labels__item"><i class="fa fa-circle-o icon--secondary"></i>Projects</li>
        </ul>
    </div>
    <div class="devider devider--dark"></div>

</aside>