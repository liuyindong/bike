
var BikeApp = angular.module("BikeApp", []);


BikeApp.controller('UploadController', ['$rootScope', '$scope', 'settings', function($rootScope, $scope, settings)
{
    $scope.ngSubmit = function()
    {



        var uploadName = $("input[name='uploadName']").val(),
            errorMsg = $("#errorMsgDiv");



        if(uploadName == null || uploadName == '')
        {
            errorMsg.fadeIn("slow");

            $("#errorMsg").html("你还没有上传记录");



            setTimeout(function(){
                errorMsg.fadeOut("slow");
            }, 5000);

            return true;
        }




        show_loading_bar(50);
        $.post("/garmin/addBikeGroup",
            {
                uploadName:uploadName,
                groupType:$("select[name='groupType']").val(),
                groupNum:$("input[name='groupNum']").val(),
                remarks:$("textarea[name='remarks']").val()
            },function(result)
            {
                show_loading_bar({
                    delay: .5,
                    pct: 100,
                    finish: function(){

                        if(result.success)
                        {
                            $("#errorMsg").html("上传成功");
                            $(".alert").addClass("alert-success");
                            $(".alert").removeClass("alert-danger");

                            errorMsg.fadeIn("slow");

                            setTimeout(function(){
                                errorMsg.fadeOut("slow");
                            }, 5000);
                        }
                    }
                });

            });
    };


    $scope.uploadFit = function()
    {


        var i = 1,
            $example_dropzone_filetable = $("#example-dropzone-filetable"),
            example_dropzone = $("#advancedDropzone").dropzone({
                url: '/garmin/upload',
                acceptedFiles: ".fit",
                dictInvalidFileType:'文件类型错误，只能为.fit',
                addRemoveLinks:true,
                // Events
                addedfile: function(file)
                {
                    if(i == 1)
                    {
                        $example_dropzone_filetable.find('tbody').html('');
                    }

                    var size = parseInt(file.size/1024, 10);
                    size = size < 1024 ? (size + " KB") : (parseInt(size/1024, 10) + " MB");

                    var	$el = $('<tr>\
													<td class="text-center">'+(i++)+'</td>\
													<td>'+file.name+'</td>\
													<td><div class="progress progress-striped"><div class="progress-bar progress-bar-warning"></div></div></td>\
													<td>'+size+'</td>\
													<td>上传中...</td>\
												</tr>');

                    $example_dropzone_filetable.find('tbody').append($el);
                    file.fileEntryTd = $el;
                    file.progressBar = $el.find('.progress-bar');
                },

                uploadprogress: function(file, progress, bytesSent)
                {
                    file.progressBar.width(progress + '%');
                },

                success: function(file,data)
                {
                    file.fileEntryTd.find('td:last').html('<span class="text-success">上传成功</span>');
                    file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-success');


                    /*var point = [];


                    $.each(data.data.paths, function(index,obj)
                    {

                        point.push(obj.y + "," + obj.x)
                    });


                    var imgUrl = "http://api.map.baidu.com/staticimage?width=422&height=355&center="+data.data.center.y + "," + data.data.center.x+"&zoom=11&pathStyles=0xff0000,2,1&paths="+point.join(";") + "&copyright=1";


                    $("img").attr("src",imgUrl);*/


                },

                error: function(file,data)
                {
                    file.fileEntryTd.find('td:last').html('<span class="text-danger">'+data.errors+'</span>');
                    file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
                }
            });

        $("#advancedDropzone").css({
            minHeight: 200
        });


    };

    /*$scope.groupNumclick =  function()
    {
        $(".input-group.spinner").each(function(i, el)
        {
            var $ig = $(el),
                $dec = $ig.find('[data-type="decrement"]'),
                $inc = $ig.find('[data-type="increment"]'),
                $inp = $ig.find('.form-control'),

                step = attrDefault($ig, 'step', 1),
                min = attrDefault($ig, 'min', 0),
                max = attrDefault($ig, 'max', 0),
                umm = min < max;


            $dec.on('click', function(ev)
            {
                ev.preventDefault();

                var num = new Number($inp.val()) - step;

                if(umm && num <= min)
                {
                    num = min;
                }

                $inp.val(num);
            });

            $inc.on('click', function(ev)
            {
                ev.preventDefault();

                var num = new Number($inp.val()) + step;

                if(umm && num >= max)
                {
                    num = max;
                }

                $inp.val(num);
            });
        });

    } ;
    $scope.groupNumclick();*/
    $scope.uploadFit();

}]);
