/**
 * Created by ld on 2015/1/20.
 */

require.config({
    baseUrl: "http://test.res.rdec.com.cn/test/",
    paths: {
        "jquery":"js/jquery-2.1.3.min",
        "bootstrap":"js/bootstrap.min",
        "TweenLite":"external/animated-header/js/TweenLite.min",
        "swiper":"external/swiper/idangerous.swiper",
        "modernizr.custom":"external/modernizr/modernizr.custom",
        "jquery.mobile.menu":"external/z-nav/jquery.mobile.menu",
        "query.magnific-popup":"external/magnific-popup/jquery.magnific-popup.min",
        "custom":"/js/custom",
        "waypoints":"external/waypoint/waypoints.min",
        "scrollTo":"external/scrollto/jquery.scrollTo.min"
    },
    waitSeconds: 15
});

require(["jquery"],function(uril){

    $("p img").addClass("img-responsive");

    require(["bootstrap","TweenLite","swiper","modernizr.custom","jquery.mobile.menu","query.magnific-popup",'scrollTo'], function(uril) {

        require(["waypoints"],function(uril){

            require(['custom'], function(uril) {
                featureSlider();

                $("embed").removeAttr("width");
                $("embed").removeAttr("height");

                $("embed").parent().removeAttr("style");

                $("embed").parent().addClass("embed-responsive embed-responsive-16by9");

            });

        })
    });

});