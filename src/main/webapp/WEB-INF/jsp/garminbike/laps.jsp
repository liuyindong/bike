<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../inc/taglib.jsp" %>
<html>
<head>
    <title>数据</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="${res}/bootstrap/dist/css/bootstrap.min.css">


</head>
<body>


<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>总时间(分)</th>
            <th>总距离(km)</th>
            <th>平均速度</th>
            <th>最大速度</th>
            <th>平均心率</th>
            <th>最大心率</th>
            <th>平均踏频</th>
            <th>最大踏频</th>
            <th>平均功率</th>
            <th>最大功率</th>
            <th>强度</th>
            <th>心率分区</th>

        </tr>
        </thead>
        <tbody>

        <c:forEach items="${lapMesg}" var="lapmesg">

            <tr>
                <td>${bike:secToTime(lapmesg.totalTimerTime)}</td>
                <td>${bike:mTokm(lapmesg.totalDistance)}</td>
                <td>${bike:msTokmh(lapmesg.avgSpeed)}</td>
                <td>${bike:msTokmh(lapmesg.maxSpeed)}</td>
                <td>${lapmesg.avgHeartRate}</td>
                <td>${lapmesg.maxHeartRate}</td>
                <td>${lapmesg.avgCadence}</td>
                <td>${lapmesg.maxCadence}</td>
                <td>${lapmesg.avgPower}</td>
                <td>${lapmesg.maxPower}</td>
                <td>
                    <c:set var="totalStrength" value="0"></c:set>
                    <c:forEach var="strength" items="${lapmesg.strength}">

                        <c:if test="${strength.key != 8}">
                            <c:set var="totalStrength" value="${strength.value + totalStrength}"></c:set>
                        </c:if>


                    </c:forEach>

                    ${totalStrength}

                </td>
                <td>



                    <c:forEach var="heartInterval" items="${lapmesg.heartRateInterval}">

                        <c:if test="${heartInterval.key != 8}">

                            <c:if test="${heartInterval.key == 1}">一区：</c:if>
                            <c:if test="${heartInterval.key == 2}">二区：</c:if>
                            <c:if test="${heartInterval.key == 3}">三区：</c:if>
                            <c:if test="${heartInterval.key == 4}">四区：</c:if>
                            <c:if test="${heartInterval.key == 5}">五A：</c:if>
                            <c:if test="${heartInterval.key == 6}">五B：</c:if>
                            <c:if test="${heartInterval.key == 7}">五C：</c:if>

                            ${bike:secToTime(heartInterval.value)}


                        </c:if>

                    </c:forEach>



                </td>


            </tr>

        </c:forEach>


        </tbody>
    </table>




</body>


<script type="application/javascript" src="${res}/bootstrap/dist/js/bootstrap.min.js"></script>

</html>
