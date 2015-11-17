
var BikeApp = angular.module("BikeApp",['ngTable']);




BikeApp.controller('OverviewController', function($rootScope,$stateParams, $scope, $http, $timeout,ngTableParams) {




    $scope.loadMap = function()
    {


        $http.jsonp("/garmin/bikeMsgById/"+$stateParams.bikeId+"?jsonp=JSON_CALLBACK").success(function(data)
        {

            $scope.bike = data;


            $scope.tableParams = new ngTableParams({
                group: "totalTimerHHmmSS"
            }, {
                dataset: data.lapBikeList
            });

            $scope.tableParams.count(50);







            /*var pointStart = $scope.bike.location[0];

            var marker = new AMap.Marker({position: [pointStart.lon, pointStart.lat]});




            var initmap = new AMap.Map("gpsGuiji", {
                level: 13,
                resizeEnable: true,
                continuousZoomEnable: false
            });



            var satellitePolylineCoordinates = [];



            for (var i = 0; i < $scope.bike.location.length; i++) {
                satellitePolylineCoordinates.push(new AMap.LngLat($scope.bike.location[i].lon, $scope.bike.location[i].lat));
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



            initmap.plugin(["AMap.MapType"], function () {
                type = new AMap.MapType({ defaultType: 0 });
                initmap.addControl(type);
            });

            initmap.plugin(["AMap.ToolBar"], function () {
                toolBar = new AMap.ToolBar();
                initmap.addControl(toolBar);
            });


            initmap.setFitView();

            var haiBa = data.garminBike.altitude;


            var maxaHaiBa = 0;

            for (var i = 0; i < haiBa.length; i++) {
                if (haiBa[i] > maxaHaiBa){
                    maxaHaiBa = haiBa[i];
                }
            }

            var haiBaArray = [];

            $.each(data.garminBike.distance, function(i, n){
                haiBaArray.push([n,data.garminBike.altitude[i]]);
            });





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
                        formatter: function()
                        {
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
                    max:maxaHaiBa
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

                            var point = $scope.bike.location[idx];

                            marker = new AMap.Marker({position: [point.lon, point.lat]});


                            marker.setMap(initmap);

                            var time = data.garminBike.timestamp[idx] - data.startTime;



                            s = '海拔: ' + this.y + 'm' + ' 距离: ' + this.x + 'km<br />坡度: ' + data.garminBike.grade[idx] + '% 时间: ' + zhuanhuanTime(time/1000,1);

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
                    data: haiBaArray
                }]
            });
*/


        });



    }
    $scope.checkBoxClick = function(aa)
    {
        alert(aa);
    }

    $scope.loadMap();
});

