<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../inc/taglib.jsp" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:400px"></div>


<script src="${res}/echarts/build/dist/echarts.js"></script>
<script src="http://lib.sinaapp.com/js/jquery/2.0.3/jquery-2.0.3.min.js"></script>

<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: '${res}/echarts/build/dist'
        }
    });


    // 使用
    require(
            [
                'echarts',
                'echarts/chart/line',
                'echarts/chart/bar',
                'echarts/chart/scatter',
                'echarts/chart/k',
                'echarts/chart/pie',
                'echarts/chart/radar',
                'echarts/chart/force',
                'echarts/chart/chord',
                'echarts/chart/gauge',
                'echarts/chart/funnel',
                'echarts/chart/eventRiver',
                'echarts/chart/venn',
                'echarts/chart/treemap',
                'echarts/chart/tree',
                'echarts/chart/wordCloud',
                'echarts/chart/heatmap',
                'echarts/chart/map'
            ],

            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'));

                /*var option = {
                    title : {
                        text: '未来一周气温变化',
                        subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['最高气温','最低气温']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            boundaryGap : false,
                            data : ['周一','周二','周三','周四','周五','周六','周日']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            axisLabel : {
                                formatter: '{value} °C'
                            }
                        }
                    ],
                    series : [
                        {
                            name:'最高气温',
                            type:'line',
                            data:[11, 11, 15, 13, 12, 13, 10]
                        },
                        {
                            name:'最低气温',
                            type:'line',
                            data:[1, -2, 2, 5, 3, 2, 0]
                        }
                    ]
                };*/

                var option = {
                    "calculable": true,
                    "title": {
                        "text": "2015-10-01"
                    },
                    "toolbox": {
                        "show": true
                    },
                    "tooltip": {
                        "trigger": "axis"
                    },
                    "legend": {
                        "data": [
                            "xin lv",
                            "su du"
                        ]
                    },
                    "xAxis": [
                        {
                            "boundaryGap": false,
                            "type": "category",
                            "data": [ "1",  "2",  "3", "4", "6" ]
                        }
                    ],
                    "yAxis": [
                        {
                            "type": "value",
                            "axisLine": {
                                "onZero": false
                            },
                            "axisLabel": {
                                "formatter": "{value} km"
                            }
                        }
                    ],
                    "series": [
                        {
                            "name": "xin lv",
                            "type": "line",
                            "data": [
                                "10",
                                "20",
                                "12"
                            ]
                        },
                        {
                            "name": "su du",
                            "type": "line",
                            "data": [
                                "100",
                                "100",
                                "100"
                            ]
                        }
                    ]
                }

           //     myChart.setOption(option);

                var url = "http://localhost:8080/fit/getFitMsg?fileName=D://bike27.fit";

                /*$.getJSON(url, function(data){
                    // 为echarts对象加载数据
                    myChart.setOption(data);
                    console.log(data);
                });*/

                $.ajax({
                    type: "get",
                    url: url,
                    dataType: "json",
                    success:function(data) {
                        myChart.setOption(data);
                    }

                });



            }
    );
</script>

</body>
</body>