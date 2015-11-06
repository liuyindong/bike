<%--
  Created by IntelliJ IDEA.
  User: ld
  Date: 2015/1/19
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../inc/taglib.jsp" %>

<html lang="zh-cn">
<head>

    <title>骑友之家-一个专属骑友的乐园</title>
    <meta name="keywords" content="骑友之家,骑友乐园,骑友聚集,俱乐部,自行车价格,自行车活动,自行车乐园" />
    <meta name="description" content="骑友之家-为你提供最新自行车信息,单车美图,骑行路线,精彩的自行车新闻、行情、评测、导购内容，是提供信息最快最全的自行车网站 " />
    <jsp:include page="../inc/css.jsp" flush="false"/>

</head>
<body>


<jsp:include page="../inc/head.jsp" flush="true"/>

<div class="bottom-space--sm"></div>

<section class="container">
    <h3 class="not-visible">骑友之家</h3>

    <div class="row bottom-space">
        <div class="col-sm-4 hidden-xs">
            <div class="feature">
                <div class="feature__image feature__image--page">
                    <img src="${res}/images/feature/1.png" alt="骑行新闻">
                </div>
                <a href="${path}/message/byType?pagNo=1" target="_blank"><h3 class="feature__heading">骑友之家</h3></a>
                <p class="feature__info"> 为你提供最新自行车信息,单车美图,骑行路线,精彩的自行车新闻、行情、评测</p>
            </div>
        </div><!-- end col -->

        <div class="col-sm-4 hidden-xs">
            <div class="feature">
                <div class="feature__image feature__image--responsive">
                    <img src="${res}/images/feature/2.png" alt="单车旅行">
                </div>
                <h3 class="feature__heading">旅行</h3>
                <p class="feature__info">旅行，就是离开熟悉的地方，然后不一样的归来。</p>
            </div>
        </div><!-- end col -->


        <div class="col-sm-4 hidden-xs">
            <div class="feature">
                <div class="feature__image feature__image--clock">
                    <img src="${res}/images/feature/3.png" alt="自行车商城">
                </div>
                <h3 class="feature__heading">商城</h3>
                <p class="feature__info">保证所有产品均为正品。一直秉承"为客户省更多的钱买同样的产品"的宗旨为消费者服务。</p>
            </div>
        </div><!-- end col -->
    </div><!-- end row -->

    <div class="swiper-container fearure-slider">
        <div class="swiper-wrapper">
            <!--Slide-->
            <div class="swiper-slide">
                <div class="feature">
                    <div class="feature__image feature__image--page">
                        <img src="${res}/images/feature/1.png" alt="">
                    </div>
                    <a href="${path}/message/byType?pagNo=1" target="_blank"><h3 class="feature__heading">骑友之家</h3></a>
                    <p class="feature__info">为你提供最新自行车信息,单车美图,骑行路线,精彩的自行车新闻、行情、评测.</p>
                </div>
            </div>

            <!--Slide-->
            <div class="swiper-slide">
                <div class="feature">
                    <div class="feature__image feature__image--responsive">
                        <img src="${res}/images/feature/2.png" alt="">
                    </div>
                    <h3 class="feature__heading">旅行</h3>
                    <p class="feature__info">旅行，就是离开熟悉的地方，然后不一样的归来。</p>
                </div>
            </div>

            <!--Slide-->
            <div class="swiper-slide">
                <div class="feature">
                    <div class="feature__image feature__image--clock">
                        <img src="${res}/images/feature/3.png" alt="">
                    </div>
                    <h3 class="feature__heading">商城(敬请期待)</h3>
                    <p class="feature__info">保证所有产品均为正品。一直秉承"为客户省更多的钱买同样的产品"的宗旨为消费者服务。</p>
                </div>
            </div>
        </div>

        <div class="product-slider-pagination feature-pagination"></div>
    </div>
</section><!-- end container -->




<div class="block">


    <!-- Section show current page and breadcrumb -->
    <section class="page-indecator">

        <div class="container">
            <h2 class="block-title block-title--simple" data-selector="h2" style="outline: none; cursor: default;">最新资讯</h2>
        </div>
    </section>
    <!-- end section show current page and breadcrumb -->

    <section class="container">

        <c:forEach  items="${page.datas}" varStatus="status" begin="0" step="2">


            <c:set value="${page.datas[status.count * status.step - 2]}" var="newvarj" ></c:set>



            <div class="row">
                <div class="col-sm-6">
                    <article class="post post--video">
                        <div class="image-container image-container--empty ">
                            <div class="video-container">
                                  ${newvarj.displayForm}
                            </div>
                        </div>

                        <p class="post__date post__date--rect post__date--static"><fmt:formatDate value="${newvarj.createTime}" pattern="MM/dd HH:mm"/></p>
                        <a class="post--preview__link" href="${path}/message/item/${newvarj.uuid}" target="_blank"><h2 class="post-heading">${newvarj.title}</h2></a>
                        <p>${newvarj.summary}</p>
                    </article>
                </div>


                <c:set value="${page.datas[status.count * status.step -1]}" var="newvarO" ></c:set>

                <div class="col-sm-6">
                    <article class="post post--video">
                        <div class="image-container image-container--empty ">
                            <div class="video-container">
                                    ${newvarO.displayForm}
                            </div>
                        </div>

                        <p class="post__date post__date--rect post__date--static"><fmt:formatDate value="${newvarO.createTime}" pattern="MM/dd HH:mm"/></p>
                        <a class="post--preview__link" href="${path}/message/item/${newvarO.uuid}" target="_blank"><h2 class="post-heading">${newvarO.title}</h2></a>
                        <p>${newvarO.summary}</p>
                    </article>
                </div>



            </div>

        </c:forEach>


        <div class="row">

            <div class="col-sm-6">
                <a class="btn btn-info btn--decorated btn-present pull-left" href="${path}/message/byType?typeUUID=&tagUUID=&pagNo=2" target="_blank">查看更多</a>
            </div>

            <div class="col-sm-6">
                <a class="btn btn-info btn--decorated btn-present pull-right" href="${path}">随便逛逛</a>
            </div>



        </div>




    </section><!-- end container -->
</div>



<jsp:include page="../inc/foot.jsp" flush="true"/>


<script data-main="${path}/js/main" src="${res}/js/require.js"></script>


</body>
</html>
