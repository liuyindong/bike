<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="<%=request.getContextPath()%>"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ attribute name="pageUrl" required="true" rtexprvalue="true" description="分页链接地址，页码占位符为 {page}" %>
<%@ attribute name="pageCount" required="true" rtexprvalue="true" description="总页数" %>
<%@ attribute name="currentPage" required="true" rtexprvalue="true" description="当前页" %>

<c:choose>

    <c:when test="${pageCount<=5}" >
        <c:set var='pageStart' value='1'></c:set>
        <c:set var='pageEnd' value='${pageCount}'></c:set>
    </c:when>



    <c:otherwise>

        <c:set var="pageStart" value="${currentPage-2}" />
        <c:set var="pageEnd" value="${currentPage+2}" />

        <c:if test="${pageEnd > pageCount}">

            <c:set var="pageStart" value="${pageCount-4}" />
            <c:set var="pageEnd" value="${pageCount}" />

        </c:if>

        <c:if test="${pageStart<1}">
            <c:set var="pageStart" value="1" />
            <c:set var="pageEnd" value="5" />

        </c:if>

    </c:otherwise>

</c:choose>


<div class="pagination pagination--rect devider--top">


    <c:set var="upPage" value="${currentPage - 1}" />

    <c:choose>
        <c:when test="${upPage <= 0}">
            <a class="pagination__prev" href="javascript:void(0)"><i class="fa fa-angle-left"></i></a>
        </c:when>
        <c:otherwise>
            <a class="pagination__prev" href="${fn:trim(pageUrl)}${upPage}"><i class="fa fa-angle-left"></i></a>


        </c:otherwise>
    </c:choose>

    <div class="pagination__block">

    <c:forEach begin="${pageStart}" end="${pageEnd}" var="pageIndex">

        <c:choose>

            <c:when test="${pageIndex == currentPage}">
                <a class="pagination__item active-page" href="${fn:trim(pageUrl)}${pageIndex}">${pageIndex}</a>
            </c:when>

            <c:otherwise>
                <a class="pagination__item mobile-small" href="${fn:trim(pageUrl)}${pageIndex}">${pageIndex}</a>
            </c:otherwise>

        </c:choose>

    </c:forEach>

    </div>

    <c:set var="downPage" value="${pageCount - currentPage}" />




    <c:choose>

        <c:when test="${downPage > 0}" >

            <a class="pagination__next" href="${fn:trim(pageUrl)}${currentPage+1}"><i class="fa fa-angle-right"></i></a>


        </c:when>
        <c:otherwise>

            <a class="pagination__next" href="javascript:void(0)"><i class="fa fa-angle-right"></i></a>

        </c:otherwise>

    </c:choose>

</div>