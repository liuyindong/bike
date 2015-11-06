<%--
  Created by IntelliJ IDEA.
  User: ld
  Date: 2015/1/20
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="wrapper" id="top">
    <!-- Header section -->
    <header class="header header--social">
        <div class="header-fixed">
            <div class="header-line waypoint" data-animate-down="header-up" data-animate-up="header-down">
                <div class="container">
                    <!-- Contact information about company -->
                    <address class="contact-info pull-left hidden-lower">
                        <span class="contact-info__item">
                            <i class="fa fa-bicycle" data-selector="i.fa" style="outline: none; cursor: default;"></i>
                            www.yyi.bike
                        </span>
                        <span class="contact-info__item">
                            <i class="fa fa-envelope" data-selector="i.fa" style="outline: none; cursor: default;"></i>
                            211406311@qq.com
                        </span>
                    </address>
                    <!-- end contact information -->

                    <div class="social social--default social--small">
                        <!-- List with social icons -->
                        <ul>
                            <li class="social__item"><a class="social__link"  href="tencent://message/?uin=211406311"
                                                        target="_blank"><i class="social__icon fa fa-qq"
                                                                           data-selector="i.fa" id="ui-id-22"
                                                                           style="outline: none; cursor: default; color: rgb(52, 73, 94); font-size: 14px;"></i></a>
                            </li>
                                <li class="social__item"><a class="social__link" href="javascript:void(0)" onclick="solrFootQrcode()"
                                                       ><i class="social__icon fa fa-weixin"
                                                                           data-selector="i.fa" id="ui-id-23"
                                                                           style="outline: none; cursor: default; color: rgb(52, 73, 94); font-size: 14px;"></i></a>
                            </li>
                            <li class="social__item"><a class="social__link"
                                                        href="http://www.weibo.com/p/1005055491688935"
                                                        target="_blank"><i class="social__icon fa fa-weibo"
                                                                           data-selector="i.fa" id="ui-id-24"
                                                                           style="outline: none; cursor: default; color: rgb(52, 73, 94); font-size: 14px;"></i></a>
                            </li>
                            <br></ul>
                    </div>
                </div>
                <!-- end container -->
            </div>

            <div class="fixed-top header-down">
                <div class="container">
                    <!--  Logo  -->
                    <a class="logo" href="${path}/">
                        <img src="${res}/img/logo-4.png">
                    </a>
                    <!-- End Logo -->

                    <!-- Navigation section -->
                    <nav class="z-nav">
                        <!-- Toggle for menu mobile view -->
                        <a href="#" class="z-nav__toggle" data-selector="nav a, a.edit"
                           style="outline: none; cursor: default;">
                            <span class="menu-icon"></span>
                            <span class="menu-text">菜单</span>

                            <div class="menu-head"></div>
                        </a>

                        <ul class="z-nav__list">
                            <li class="z-nav__item">
                                <a class="z-nav__link z-nav__link--active" href="${path}/" data-selector="nav a, a.edit"
                                   style="outline: none; cursor: default;">首页</a>
                            </li>

                            <li class="z-nav__item">
                                <a class="z-nav__link" href="${path}/message/byType?pagNo=1" data-selector="nav a, a.edit"
                                   style="outline: none; cursor: default;">新闻频道</a>
                            </li>

                            <li class="z-nav__item">
                                <a class="z-nav__link" href="http://www.7lbike.com/forum.php" data-selector="nav a, a.edit"
                                   style="outline: none; cursor: default;">论坛</a>
                            </li>

                            <li class="z-nav__item">
                                <a class="z-nav__link" href="javascript:void(0)" data-selector="nav a, a.edit"
                                   style="outline: none; cursor: default;">商城</a>
                            </li>

                        </ul>
                        <!-- end list menu item -->
                    </nav>
                    <!-- end navigation section -->
                </div>
                <!-- end container -->

                <!-- Colored devider -->
                <div class="devider-color"></div>

            </div>
            <!-- end fixed top block -->

        </div>
    </header>
