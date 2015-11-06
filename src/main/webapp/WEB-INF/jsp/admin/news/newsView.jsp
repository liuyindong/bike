<%--
  Created by IntelliJ IDEA.
  User: ld
  Date: 2015/1/30
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../inc/taglib.jsp" %>
<!doctype html>
<html lang="en-us">
<head>
  <!-- Basic Page Needs -->
  <meta http-equiv="X-UA-Compatible" content="IE=Edge">
  <meta charset="utf-8">
  <title>Allec - Dashboard</title>
  <meta name="description" content="A Template by Designzway team">
  <meta name="keywords" content="HTML, CSS, JavaScript">
  <meta name="author" content="Designzway team">

  <jsp:include page="../../inc/css.jsp" />


</head>

<body>

<jsp:include page="../top.jsp" flush="false" />

<section>

  <jsp:include page="../left.jsp" flush="false" />


  <div class="content-dashboard">


      <h3 class="heading-helper heading-helper--sm heading-helper--markered heading-helper--markered-first">新闻管理</h3>

      <div class="full-row">

          <a class="btn btn-info btn-sm btn-present-sm" href="${path}/admin/to/addNews " >添加新闻</a>
          <a class="btn btn-warning btn-sm btn-present-sm" href="#">查看</a>





          <div class="widget widget--message">

              <div class="table-responsive">
                  <table class="table table-bordered table--wide table-present">
                      <colgroup class="col-sm-width">
                      <colgroup class="col-sm-width">
                      <colgroup class="col-sm-width">
                      <colgroup class="col-sm-width">
                      <colgroup class="col-sm-width">
                      <colgroup class="col-sm-width">

                          <thead>
                          <tr>
                              <th>标题</th>
                              <th>发布时间</th>
                              <th>新闻类型</th>
                              <th>展示类型</th>
                              <th>内容</th>
                              <th>操作</th>
                          </tr>
                          </thead>

                          <tbody>

                          <c:forEach items="${bikeMessages}" var="bikeMessage" >

                              <tr>
                                  <td>${bikeMessage.title}</td>
                                  <td><fmt:formatDate value="${bikeMessage.createTime}" pattern="MM/dd HH:mm"/></td>
                                  <td>${bikeMessage.showTypeId}</td>
                                  <td>${bikeMessage.typeName}</td>
                                  <td width="513", height="293">${bikeMessage.displayForm}</td>
                                  <td><a class="btn btn-primary btn-sm" href="${path}/message/confirmation/${bikeMessage.uuid}">发布</a></td>

                              </tr>

                          </c:forEach>

                          </tbody>
                  </table>



              </div>






          </div> <!-- end wrapper -->




  </div>

</section>
<%--

<div class="animationload">
  <div class="preloader"></div>
</div>
--%>


</body>
</html>
