<%--
  Created by IntelliJ IDEA.
  User: zz
  Date: 2015/9/6
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../inc/taglib.jsp" %>
<html>
<head>
    <title></title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css" href="${res}/bootstrap/dist/css/bootstrap.min.css">
    <!--引入CSS-->
    <link rel="stylesheet" type="text/css" href="${res}/webuploader/css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="http://fex.baidu.com/webuploader/css/style.css">


    <script src="http://lib.sinaapp.com/js/jquery/2.0.3/jquery-2.0.3.min.js"></script>

    <!--引入JS-->
    <script type="text/javascript" src="${res}/webuploader/dist/webuploader.js"></script>



</head>
<body>

    <div id="uploader" class="wu-example">
        <!--用来存放文件信息-->
        <div id="thelist" class="uploader-list"></div>
        <div class="btns">
            <div id="picker">选择文件</div>
            <button id="ctlBtn" class="btn btn-default">开始上传</button>
        </div>

        <div><label>组数</label><input type="text" value="1" id="groupNumber"/></div>
        <div><label>类型</label>

            <select id="groupType">
                <option value="jirounaili" >肌肉耐力</option>
                <option value="jixiantaping">极限踏频</option>
                <option value="liliangxunlian">力量训练</option>
            </select>



        </div>
    </div>


    <div id></div>




<script type="application/javascript">

    // 文件上传
    jQuery(function() {
        var $ = jQuery,
                $list = $('#thelist'),
                $btn = $('#ctlBtn'),
                state = 'pending',
                uploader;

        uploader = WebUploader.create({

            // 不压缩image
            resize: false,

            // swf文件路径
            swf: '${res}/webuploader/dist/Uploader.swf',

            // 文件接收服务端。
            server: '${path}/garmin/upload',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker'
        });

        // 当有文件添加进来的时候
        uploader.on( 'fileQueued', function( file ) {
            $list.append( '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
            '</div>' );
        });

        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });

        uploader.on( 'uploadSuccess', function( file,response)
        {
            $( '#'+file.id ).find('p.state').text('已上传');

    //        $("#uploader").hide();

    //        window.location.href = "/garmin/getFitMsg?name="+response.data;

        });

        uploader.on( 'uploadError', function( file ) {
            $( '#'+file.id ).find('p.state').text('上传出错');
        });

        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });

        uploader.on( 'all', function( type ) {
            if ( type === 'startUpload' ) {
                state = 'uploading';
            } else if ( type === 'stopUpload' ) {
                state = 'paused';
            } else if ( type === 'uploadFinished' ) {
                state = 'done';
            }

            if ( state === 'uploading' ) {
                $btn.text('暂停上传');
            } else {
                $btn.text('开始上传');
            }
        });

        $btn.on( 'click', function() {
            if ( state === 'uploading' ) {
                uploader.stop();
            } else {
                uploader.upload();
            }
        });

        uploader.on('uploadBeforeSend',function(object,data,header){
            data=$.extend(data,{
                groupNumber:$("#groupNumber").val(),
                groupType:$("#groupType").val()
            });
        });
    });



</script>


<script type="application/javascript" src="${res}/bootstrap/dist/js/bootstrap.min.js"></script>
<%--<script type="application/javascript" src="http://cdn.bootcss.com/angular.js/1.4.5/angular.min.js"></script>--%>

</body>
</html>
