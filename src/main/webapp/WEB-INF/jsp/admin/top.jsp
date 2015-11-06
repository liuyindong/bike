<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper">
    <header class="header-fluid">
        <!-- Logo -->
        <a class="logo logo--block" href="index.html">
            <h1 class="logo__text">Allec</h1>
        </a>
        <!-- end logo -->

        <!-- Search field -->
        <form class="search search--empty" id="search-form" name="search-form" method="get" action="#">
            <input class="search__field" name="search-request" type="search" placeholder="Search request here..." value="">
            <button class="search__btn" type="submit">
                <i class="fa fa-search"></i>
            </button>
        </form>
        <!-- end search field -->

        <div class="header__controls">
            <!-- User login area -->
            <a class="user" href="#">
                <div class="user__photo">
                    <img src="" alt="">
                </div>

                <span class="user__name">Marissa Tommen <i class="fa fa-angle-down"></i></span>
            </a>
            <!-- end user login area -->

            <!-- Notification (tast, mails) -->
            <a class="notification" href="#">
                <i class="fa fa-envelope"></i>
            </a>

            <a class="notification" href="#">
                <i class="fa fa-tasks"></i>
            </a>
            <!-- end Notification -->

            <!-- Sign out button -->
            <a class="btn btn-warning btn--decorated btn--logout" href="#"><i class="fa fa-sign-out"></i></a>
        </div>

    </header>
    <!-- Colored devider -->
    <div class="devider-color"></div>
</div>