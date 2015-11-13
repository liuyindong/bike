
var BikeApp = angular.module("BikeApp",[]);




BikeApp.controller('OverviewController', function($rootScope,$stateParams, $scope, $http, $timeout) {




    $scope.loadMap = function()
    {


        $http.jsonp("/garmin/bikeMsgById/"+$stateParams.bikeId+"?jsonp=JSON_CALLBACK").success(function(data)
        {

            $scope.location = data.garminBike.location;

            $scope.bike = data;



            var pointStart = $scope.location[0];

            var marker = new AMap.Marker({position: [pointStart.lon, pointStart.lat]});




            var initmap = new AMap.Map("gpsGuiji", {
                level: 13,
                resizeEnable: true,
                continuousZoomEnable: false
            });



            var satellitePolylineCoordinates = [];



            for (var i = 0; i < $scope.location.length; i++) {
                satellitePolylineCoordinates.push(new AMap.LngLat($scope.location[i].lon, $scope.location[i].lat));
            }


            var initpolyline = new AMap.Polyline({
                path: satellitePolylineCoordinates, //设置线覆盖物路径
                strokeColor: "#3366FF", //线颜色
                strokeOpacity: 1, //线透明度
                strokeWeight: 5, //线宽s
                strokeStyle: "solid", //线样式
                strokeDasharray: [10, 5], //补充线样式
                geodesic: true
            });
            initpolyline.setMap(initmap);
            //添加起点
            marker.setMap(initmap);






            //添加地图类型切换插件
            initmap.plugin(["AMap.MapType"], function () {
                //地图类型切换
                type = new AMap.MapType({ defaultType: 0 }); //初始状态使用2D地图
                initmap.addControl(type);
            });

            initmap.plugin(["AMap.ToolBar"], function () {
                toolBar = new AMap.ToolBar();
                initmap.addControl(toolBar);
            });


            initmap.setFitView();

            var haiBa = data.garminBike.altitude;



            var maxaltitude = 0;

            for (var i = 0; i < haiBa.length; i++) {
                if (haiBa[i] > maxaltitude){
                    maxaltitude = haiBa[i];
                }
            }



            $('#haiba').highcharts({

                chart:{animation:false},
                title: {
                    text: '赛段摘要',
                    style:{
                        display:'none'
                    }
                },


                xAxis: {

                    labels: {
                        formatter: function() {
                            return this.value + 'km';
                        }
                    }
                },
                yAxis: { // Secondary yAxis
                    title: {
                        text: '海拔',
                        style: {
                            display:'none'
                        }
                    },
                    gridLineColor: 'rgba(233, 233, 233, 0.7)',
                    gridLineWidth: 1,
                    labels: {
                        formatter: function() {
                            return this.value +' m';
                        },
                        style: {
                            color: '#666'
                        }
                    },
                    max:maxaltitude
                },
                legend: {
                    enabled: false
                },
                credits: {
                    enabled: false
                },
                tooltip: {
                    animation:false,
                    crosshairs: true,
                    shared: true,
                    formatter: function () {//弹出提示
                        var s = '';
                        $.each(this.points, function (i,n) {
                            var idx = jQuery.inArray(this.point, this.series.data);


                            marker.setMap(null);

                            var point = $scope.location[idx];

                            marker = new AMap.Marker({position: [point.lon, point.lat]});


                            marker.setMap(initmap);


                           s = '海拔: ' + this.y + 'm' + ' 距离: ' + Math.round(this.x/1000*10)/10 + 'km<br />坡度: ' + data.garminBike.grade[idx] + '% 时间: ' + zhuanhuanTime(123,1);

                        });
                        return s;
                    },
                    followTouchMove:false
                },
                legend: {enabled:false},
                plotOptions: {
                    series: {
                        animation: false,
                        events: {
                            mouseOut: function () {
                                marker.setMap(null);
                            }
                        }
                    }
                },

                series: [{
                    name: '海拔',
                    type:'area',
                    color: '#D9D9D9',
                    zIndex:1,
                    marker: {//消除marker
                        enabled:false,
                        states: {
                            hover: {
                                enabled: false
                            }
                        }
                    },
                    data: haiBa
                }]
            });



        });



    }
    $scope.loadMap();
});

