<%--
  Created by IntelliJ IDEA.
  User: ld
  Date: 2015/1/20
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../inc/taglib.jsp" %>


<html lang="zh-cn">

<head>
    <!-- Basic Page Needs -->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta charset="utf-8">
    <title>骑友之家 - 新闻资讯频道</title>
    <meta name="description" content="自行车知识相关">
    <meta name="keywords" content="单车知识,单车技巧,了解单车,自行车骑行,骑行技巧">



    <jsp:include page="../inc/css.jsp" flush="false" />

</head>

<body>


<jsp:include page="../inc/head.jsp" flush="true" />



<!-- Section show current page and breadcrumb -->
<section class="page-indecator">
    <div class="container">

        <h2 class="heading">

            <a href="${path}">首页</a> > 新闻资讯  > ${bikeType.name}

        </h2>
        <!-- Default one color devider -->
        <div class="devider devider--bottom-md"></div>
    </div>
</section>
<!-- end section show current page and breadcrumb -->

<section class="container">

    <h3 class="not-visible">Main conrainer</h3>

    <div class="row">
        <div class="col-sm-8 post-wide">
            <div class="post-author">
                <div class="image-container image-container--border-inner post-author__photo">
                    <img src="${res}/images/20120229110452_zNBaH.jpg" alt="骑友之家微信" title="微信，微博">
                </div>

                <h2 class="post-author__name">骑友之家</h2>
                <p class="post-author__desctipt">欢迎你进入骑友之家网站,关注微信,微博,QQ及时了解单车信息。</p>
                <div class="social social--primary post-author__social">
                    <!-- List with social icons -->
                    <ul>
                        <li class="social__item"><a class="social__link" href="tencent://message/?uin=211406311" target="_blank"><i class="social__icon fa fa-qq"></i></a></li>
                        <li class="social__item"><a class="social__link" href="javascript:void(0)" onclick="solrFootQrcode()"><i class="social__icon fa fa-weixin"></i></a></li>
                        <li class="social__item"><a class="social__link" href="http://www.weibo.com/p/1005055491688935" target="_blank"><i class="social__icon fa fa-weibo"></i></a></li>
                    </ul>
                </div>
            </div>


            <h2 class="block-title block-title--bottom">资讯频道</h2>

            <c:forEach var="pageItem" items="${page.datas}" >

            <c:if test="${pageItem.showTypeId == 0}">

                <!-- Standart arcticle with image -->
                <article class="post post--table post--table-horisontal ">
                    <a class="image-container  image-container--max post__link-area img-responsive" href="${path}/message/item/${pageItem.uuid}" target="_blank">
                        <img src="${pageItem.frontcoverImg}?imageMogr2/thumbnail/215x215!" alt="${pageItem.title}" title="${pageItem.title}" class="img-responsive">
                    </a>
                    <div class="post__detail">
                        <a class="post__link" href="${path}/message/item/${pageItem.uuid}" target="_blank">
                            <h3 class="post__heading">${pageItem.title} </br> [

                                ${pageItem.typeName}

                                ] </h3>
                        </a>
                        <p class="post__date"><fmt:formatDate value="${pageItem.createTime}" pattern="yyyy/MM/dd HH:mm" /></p>
                        <p class="post__text">${fn:substring(pageItem.summary, 0, 110)}...</p>
                    </div>
                </article>

            </c:if>

            <c:if test="${pageItem.showTypeId == 1 or pageItem.showTypeId == 2}">

                <article class="post post--preview">
                    <div class="image-container image-container--empty">
                        <div class="video-container">
                            ${pageItem.displayForm}
                            <p class="post__date post__date--rect"><fmt:formatDate value="${pageItem.createTime}" pattern="yyyy/MM/dd HH:mm" /></p>
                        </div>
                    </div>

                    <a class="post--preview__link" href="${path}/message/item/${pageItem.uuid}"><h2 class="post-heading">${pageItem.title}</h2></a>
                    <p>${fn:substring(pageItem.summary, 0, 110)}...</p>
                </article> <!-- end article -->

            </c:if>






            </c:forEach>


            <tag:pages pageUrl="${path}/message/byType?tagUUID=${tagUUID}&typeUUID=${bikeType.uuid}&pagNo=" pageCount="${page.totalPage}" currentPage="${page.pageNo}" />

        </div><!-- end col -->

        <aside class="col-sm-4">
            <div class="sidebar">
                <h2 class="heading heading--section heading--first">频道分类</h2>
                <ul class="list list--marker">

                    <c:forEach items="${newType}" var="newType">

                    <c:if test="${newType.parentId != null}" >

                        <li class="list__item"><a class="list__link" href="${path}/message/byType?pagNo=1&typeUUID=${newType.uuid}">${newType.name}</a></li>

                    </c:if>


                    </c:forEach>
                </ul>



                <h2 class="heading heading--section-separate">标签TOP10</h2>

                <div class="tags tags--display">

                    <c:forEach var="bikeTag" items="${bikeTags}" >

                        <a class="tags__link <c:if test="${tagUUID == bikeTag.uuid}">tags__link--active</c:if>" href="${path}/message/byType?pagNo=1&tagUUID=${bikeTag.uuid}">${bikeTag.name}</a>

                    </c:forEach>

                </div>

                <h2 class="heading heading--section-separate">资讯排行榜</h2>
                <div class="testimonial--centered">

                    <c:forEach var="newsTop" items="${newTopTen}">

                        <a class="post post--popular" href="${path}/message/item/${newsTop.uuid}">







                            <div class="image-container image-container--expository">

                                    ${newsTop.displayForm}

                                <div class="image-container__info">
                                    <span class="image-container__info-main">${newsTop.title}</span>
                                    <span class="image-container__info-more"><fmt:formatDate value="${newsTop.createTime}" pattern="MM/dd HH:mm"/></span>
                                </div>

                                <div class="post--popular__marker">资讯TOP10</div>
                            </div>
                        </a>

                        <div class="clearfix"></div>
                        <div class="hidden-xs" style="height:10px;"></div>


                    </c:forEach>
                </div>

            </div>
        </aside><!-- end col -->
    </div><!-- end row -->
</section><!-- end container -->

<jsp:include page="../inc/foot.jsp" flush="false" />
<script data-main="${path}/js/main.js" src="${res}/js/require.js"></script>


</body>
</html>
