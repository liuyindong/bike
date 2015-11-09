
var BikeApp = angular.module("BikeApp",['ngBaiduMap']).config(config);;

BikeApp.controller('OverviewController', function($rootScope,$stateParams, $scope, $http, $timeout) {

    console.log($stateParams.bikeId);

    console.log($scope.latlng);



});

function config(baiduMapApiProvider)
{
    baiduMapApiProvider.version('2.0').accessKey('A4145e587f79e35cb7029f060282ef98');
}