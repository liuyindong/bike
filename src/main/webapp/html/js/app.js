
var BikeApp = angular.module('bikeApp', ['ui.router',"oc.lazyLoad",'ui.bootstrap','ngSanitize'])


BikeApp.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        debug: ['debug'],
        events: ['debug'],
        loadedModules: ['BikeApp']
    });
}]);


BikeApp.factory('settings', ['$rootScope', function($rootScope) {

    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        layoutImgPath: CDN_HOST,
        layoutCssPath: CDN_HOST
    };

    $rootScope.settings = settings;

    return settings;
}]);


BikeApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {


    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/home");



    $stateProvider

        .state('home', {
            url: "/home.html",
            templateUrl: "view/home.html",
            controller: "HomeController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'BikeApp',
                        files: [
                            'js/controller/HomeController.js'
                        ]
                    });
                }]
            }
        })
        .state('userBike', {
            url: "/userBike.html",
            templateUrl: "view/userBike.html",
            controller: "UserBikeLoadController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({

                        name: 'rwdTable',
                        files: [
                            'js/controller/UserBikeController.js',
                            CDN_HOST + "js/ngInfiniteScroll/ng-infinite-scroll.min.js"
                        ]

                    });
                }]
            }
        })
        .state('upload', {
            url: "/upload.html",
            templateUrl: "view/upload.html",
            controller: "UploadController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({

                        name: 'upload',
                        files: [
                            CDN_HOST + 'js/dropzone/dropzone.min.js',
                            CDN_HOST + 'js/dropzone/css/dropzone.css',
                            'js/controller/UploadController.js'
                        ]

                    });
                }]
            }
        })
        .state('activities', {
            url: "/activities/:bikeId/overview.html",
            templateUrl: "view/overview.html",
            controller: "OverviewController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({

                        name: 'BikeApp',
                        files: [
                            'js/controller/OverviewController.js',
                            'assets/core.js',
                            CDN_HOST + 'js/Highcharts-4.1.9/js/highcharts.js',
                            CDN_HOST + 'js/Highcharts-4.1.9/js/modules/exporting.js'
                        ]

                    });
                }]
            }
        })



}]);

BikeApp.run(["$rootScope","$state","settings", function($rootScope,$state,settings)
{
    $rootScope.$state = $state;
}]);