<%--
  Created by IntelliJ IDEA.
  User: ld
  Date: 2015/1/20
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../inc/taglib.jsp" %>
<html lang="zh-cn">
<head>

    <title>${bikeMessages.title}</title>
    <meta name="description" content="${bikeMessages.summary}">
    <meta name="keywords" content="骑友之家,新闻资讯,${bikeMessages.title}">

    <jsp:include page="../inc/css.jsp" flush="true" />



</head>

<body>

<jsp:include page="../inc/head.jsp" flush="true"/>



    <!-- Section show current page and breadcrumb -->
    <section class="page-indecator">
        <div class="container">



            <h2 class="heading">

                <a href="${path}/">首页</a> > <a href="${path}/message/byType?typeUUID=&tagUUID=&pagNo=1">新闻资讯</a> > ${bikeMessages.title}

            </h2>

            <div class="devider devider--bottom-md"></div>
        </div>
    </section>
    <!-- end section show current page and breadcrumb -->

    <section class="container present-addition-lg">
        <div class="row">
            <div class="col-sm-8 post-wide">
                <div class="post">

                    <article class="post post--preview">
                        <p class="post__date post__date--rect post__date--static"><fmt:formatDate value="${bikeMessages.createTime}" pattern="MM/dd HH:mm"/></p>
                        <h2 class="post-heading">${bikeMessages.title}</h2>

                    </article> <!-- end article -->

                    <div class="icon icon-present tags">
                       <i class="icon__item fa fa-user">骑友之家</i>
                       <i class="icon__item fa fa-eye">${bikeMessages.showNum}</i>
                    </div>

                    <div class="devider-color bottom-space--sm"></div>

                    ${bikeMessages.content}


                </div><!-- end post -->

                <div class="icon icon-present tags">
                        <i class="icon__item fa fa-tags">标签</i>
                </div>

                <div class="tags tags--display">
                    <div class="bottom-space--lsm"></div>


                       <c:forEach items="${bikeMessages.bikeTagList}" var="bikeTag">
                            <a class="tags__link" href="${path}/message/byType?pagNo=1&tagUUID=${bikeTag.uuid}">${bikeTag.name}</a>
                        </c:forEach>

                </div>

                <div class="post__interaction bottom-universal">

                    <h2 class="block-title">扫一扫分享大家</h2>

                    <div class="image-container--empty row">

                        <div class="col-sm-6 ">
                            <p class="link link-sample">文章分享</p>
                            <p><img src="${pat}/message/getQrcode?context=${path}/message/item/${bikeMessages.uuid}" alt="${bikeMessages.title}"></p>
                        </div>
                        <div class="col-sm-6 ">
                            <p class="link link-sample">关注微信</p>
                            <P><img src="${res}/img/weixincode_258.jpg" alt="骑友之家微信"></P>
                        </div>


                    </div>



                </div>
                <!-- end social share buttons and subscibe form-->

                <div>
                    <h2 class="block-title" id="user-post-start">猜你喜欢</h2>
                    <div class="row">

                        <c:forEach items="${newsXiangsi}" var="newsXiangSi" begin="0" end="1" >

                            <div class="col-sm-6">
                                <article class="post post--similar">
                                    <h3 class="not-visible">${newsXiangSi.title}</h3>
                                    <a class="post__images" href="${path}/message/item/${newsXiangSi.uuid}">
                                        <img src="${newsXiangSi.frontcoverImg}?imageMogr2/thumbnail/80x80!" alt="${newsXiangSi.title}">
                                    </a>
                                    <a class="post__heading link--title" href="${path}/message/item/${newsXiangSi.uuid}">${newsXiangSi.title}</a>
                                    <time class="post__date"><fmt:formatDate value="${newsXiangSi.createTime}" pattern="MM/dd HH:mm"/></time>
                                </article>
                            </div>

                        </c:forEach>



                    </div>
                </div>
                <!-- end similar post section -->


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

                </div>

                <h2 class="heading heading--section">相关资讯</h2>
                <div class="testimonial--centered">

                    <c:forEach var="newsXiangsi" items="${newsXiangsi}" begin="0" end="6" >

                        <a class="post post--popular" href="${path}/message/item/${newsXiangsi.uuid}">
                            <div class="image-container image-container--expository">
                                    ${newsXiangsi.displayForm}
                                <div class="image-container__info">
                                    <span class="image-container__info-main">${newsXiangsi.title}</span>
                                    <span class="image-container__info-more"><fmt:formatDate value="${newsXiangsi.createTime}" pattern="MM/dd HH:mm"/></span>
                                </div>

                                <div class="post--popular__marker">骑友之家</div>
                            </div>
                        </a>

                        <div class="clearfix"></div>
                        <div class="hidden-xs" style="height:10px;"></div>

                    </c:forEach>
                </div>



            </aside><!-- end col -->
        </div><!-- end row -->
    </section>

    <!-- Colored devider -->

<jsp:include page="../inc/foot.jsp" flush="true" />

<script data-main="${path}/js/main.js" src="${res}/js/require.js"></script>

</body>
</html>
