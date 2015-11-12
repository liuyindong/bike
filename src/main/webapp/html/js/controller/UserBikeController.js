
var BikeApp = angular.module("BikeApp",['infinite-scroll']);


BikeApp.controller('UserBikeLoadController',function($rootScope,$scope,UserBike)
{
    $scope.bike = new UserBike($rootScope);

    $scope.layoutCssPath = $rootScope.settings.layoutCssPath+"/uploadFile/";

});

BikeApp.factory('UserBike', function($rootScope,$http) {
    var UserBike = function() {
        this.items = [];
        this.busy = false;
        this.pageNo = 0;
    };


    UserBike.prototype.nextPage = function()
    {
        if(this.busy)
        {
            return
        }

        this.busy = true;

        var go_url = "/garmin/userBike";
        var url = sessionStorage.loadUrl;

        if(typeof(url) == "undefined")
        {
            url = go_url
            sessionStorage.loadUrl = url;
        }

        url += "?pageNo="+this.pageNo+"&jsonp=JSON_CALLBACK";


        $http.jsonp(url).success(function(data) {

            var items = data.content;

            if(data.number <= data.totalPages)
            {
                this.busy = false;
                this.pageNo = data.number + 1;
            }else{
                this.busy = true;
            }

            for (var i = 0; i < items.length; i++) {
                this.items.push(items[i]);
            }



        }.bind(this));
    };

    return UserBike;
});