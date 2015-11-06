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
  <title>后台管理</title>


  <jsp:include page="../../inc/css.jsp" />
  <link href="${res}/external/bootstrap-select/bootstrap-select.css" rel="stylesheet">


</head>

<body>

<jsp:include page="../top.jsp" flush="false" />

<section>

  <jsp:include page="../left.jsp" flush="false" />

  <div class="content-dashboard">


      <h3 class="heading-helper heading-helper--sm heading-helper--markered heading-helper--markered-first">添加新闻</h3>

      <div class="full-row">

          <div class="form-wrapper">
              <form class="contact select select--simple" name="contact-form" method="post" action="${path}/message/addNews" novalidate
                   onsubmit="return newSubmit()" >


                  <div class="row">

                      <div class="col-sm-4">

                          <input class="contact__field" name="title" type="text" placeholder="标题">

                      </div>


                      <div class="col-sm-4">

                          <select class="select-box " name="typeUuid" tabindex="0">
                              <option value="-1" selected="selected">新闻类型</option>

                              <c:forEach items="${newTypeList}" var="newType">
                                  <c:if test="${newType.parentId !=null}">
                                      <option value="${newType.uuid}">${newType.name}</option>
                                  </c:if>
                              </c:forEach>

                          </select>


                      </div>


                      <div class="col-sm-4">
                          <select class="select-box invalid_field" name="showTypeId" tabindex="1">
                              <option value="-1" selected="selected">展示类型</option>
                              <option value="1">视频</option>
                              <option value="2">音乐</option>
                              <option value="3">图片</option>
                          </select>


                      </div>
                      <!-- end col -->


                  </div>

                  <div class="row">

                      <script id="editor" type="text/plain" style="height:500px;"></script>

                  </div>


                  <button class="btn btn-info btn--decorated btn-present" type="submit" >添加</button>
              </form>
          </div>
          <!-- end wrapper -->

          <div id="error-panel">

          </div>


      </div>




  </div>

</section>
<%--

<div class="animationload">
  <div class="preloader"></div>
</div>
--%>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="${res}/js/bootstrap.min.js"></script>
<script src="${res}/external/z-nav/jquery.mobile.menu.js"></script>
<script src="${res}/external/bootstrap-select/bootstrap-select.js"></script>

<script type="text/javascript" charset="utf-8" src="${path}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${path}/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${path}/js/ueditor/lang/zh-cn/zh-cn.js"></script>

<script>

    $(function () {
        $('.select-box').selectpicker();
        UE.getEditor('editor');

        $("#editor").addClass("container-fluid")
    })
    function newSubmit()
    {
        var ok = true;

        $("select").each(
                function ()
                {
                    if ($(this).val() == -1)
                    {
                        addErrorDiv();
                        ok = false;
                    }
                }
        );

        if($(".contact__field").val() == '')
        {
            $(".contact__field").addClass("invalid_field");
            addErrorDiv();
            ok = false;
        }

       if(ok)
       {
           return true;
       }

        return false;
    }
    function addErrorDiv()
    {
        var html = '<div class="alert alert-danger"><span class="alert-market"><i class="fa fa-ban"></i></span> <strong>错误提示!</strong> 你有一个内容没有填写,请把必要信息填写完整...<button type="button" class="close" data-dismiss="alert" aria-hidden="true"><i class="fa fa-times"></i></button></div>';

        $("#error-panel").html(html);
    }

</script>




</body>
</html>
