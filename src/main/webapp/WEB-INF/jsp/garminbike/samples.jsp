<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../inc/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Highcharts Example</title>

    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.2.min.js"></script>
    <style type="text/css">
        .chart {
            min-width: 320px;
            max-width: 800px;
            height: 220px;
            margin: 0 auto;
        }
    </style>
    <!-- http://doc.jsfiddle.net/use/hacks.html#css-panel-hack -->
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style>
    </style>
    <script type="text/javascript">
        /*
         The purpose of this demo is to demonstrate how multiple charts on the same page can be linked
         through DOM and Highcharts events and API methods. It takes a standard Highcharts config with a
         small variation for each data set, and a mouse/touch event handler to bind the charts together.
         */

        $(function () {


            /**
             * In order to synchronize tooltips and crosshairs, override the
             * built-in events with handlers defined on the parent element.
             */
            $('#container').bind('mousemove touchmove', function (e) {
                var chart,
                        point,
                        i;

                for (i = 0; i < Highcharts.charts.length; i++) {
                    chart = Highcharts.charts[i];
                    e = chart.pointer.normalize(e); // Find coordinates within the chart
                    point = chart.series[0].searchPoint(e, true); // Get the hovered point

                    if (point) {
                        point.onMouseOver(); // Show the hover marker
                        chart.tooltip.refresh(point); // Show the tooltip
                        chart.xAxis[0].drawCrosshair(e, point); // Show the crosshair
                    }
                }
            });
            /**
             * Override the reset function, we don't need to hide the tooltips and crosshairs.
             */
            Highcharts.Pointer.prototype.reset = function () {};

            /**
             * Synchronize zooming through the setExtremes event handler.
             */
            function syncExtremes(e) {
                var thisChart = this.chart;

                Highcharts.each(Highcharts.charts, function (chart) {
                    if (chart !== thisChart) {
                        if (chart.xAxis[0].setExtremes) { // It is null while updating
                            chart.xAxis[0].setExtremes(e.min, e.max);
                        }
                    }
                });
            }

            // Get the data. The contents of the data file can be viewed at
            // https://github.com/highslide-software/highcharts.com/blob/master/samples/data/activity.json
            $.getJSON('${path}/garmin/samples/data?time=&name=2015-09-10-08-06-04.fit', function (activity)
            {
                $.each(activity.datasets, function (i, dataset)
                {

                    // Add X values
                    dataset.data = Highcharts.map(dataset.data, function (val, i) {
                        return [activity.xData[i], val];
                    });

                    $('<div class="chart">')
                            .appendTo('#container')
                            .highcharts({
                                chart: {
                                    marginLeft: 40, // Keep all charts left aligned
                                    spacingTop: 20,
                                    spacingBottom: 20
                                    // zoomType: 'x',
                                    // pinchType: null // Disable zoom on touch devices
                                },
                                title: {
                                    text: dataset.name,
                                    align: 'left',
                                    margin: 0,
                                    x: 30
                                },
                                credits: {
                                    enabled: false
                                },
                                legend: {
                                    enabled: false
                                },
                                xAxis: {
                                    crosshair: true,
                                    events: {
                                        setExtremes: syncExtremes
                                    },
                                    labels: {
                                        format: '{value} km'
                                    }
                                },
                                yAxis: {
                                    title: {
                                        text: null
                                    }
                                },
                                tooltip: {
                                    positioner: function () {
                                        return {
                                            x: this.chart.chartWidth - this.label.width, // right aligned
                                            y: -1 // align to title
                                        };
                                    },
                                    borderWidth: 0,
                                    backgroundColor: 'none',
                                    pointFormat: '{point.y}',
                                    headerFormat: '',
                                    shadow: false,
                                    style: {
                                        fontSize: '18px'
                                    },
                                    valueDecimals: dataset.valueDecimals
                                },
                                series: [{
                                    data: dataset.data,
                                    name: dataset.name,
                                    type: dataset.type,
                                    color: Highcharts.getOptions().colors[i],
                                    fillOpacity: 0.3,
                                    tooltip: {
                                        valueSuffix: ' ' + dataset.unit
                                    }
                                }]
                            });
                });
            });
        });
    </script>
</head>
<body>


<div id="container"></div>
</body>

<script src="${res}/Highcharts/js/highcharts.js"></script>
<script src="${res}/Highcharts/js/modules/exporting.js"></script>

</html>
