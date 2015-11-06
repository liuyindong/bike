<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="path" value="<%=request.getContextPath()%>"/>
<%@ attribute name="commentTypeId" required="true" rtexprvalue="true"
	description="评论模块 ID"%>
<%@ attribute name="commentTcid" required="true" rtexprvalue="true" description="评论对象 ID"%>
<script>
	$(function()
	{
		commentPag("${path}/comment/select");
	});
	function commentPag(url)
	{
		$(".comment_start").html('');
		$.ajax(
		{
			url : url,
			data :
			{
				"commentTcid" : "${commentTcid}",
				"commentTypeId":"${commentTypeId}"
			},
			dataType : 'json',
			type : 'post',
			success : function(data)
			{
				$(".block_comments_type_2 h3").html(data.context.total + "条评论");
				if(data.length <= 0)
				{
					return true;
				}
					var commentHtml = '';
					$.each(data.items, function(i, comment){
						commentHtml += 
							'<div class="comment"><div class="userpic">'+
							'<a href="#"><img src="${path}/'+comment.user.userioc+'" alt="" /></a></div>'+

				'	<div class="comment_wrap">'+
				'		<div class="name">'+
				'			<p>'+
				'				<a href="#">'+comment.user.username+'</a>'+
				'			</p>'+
				'		</div>'+
				'		<div class="date">'+
				'			<p>'+comment.createDate+'</p>'+
				'		</div>'+
				'		<div class="content">'+
				'			<p>'+comment.content+'</p>'+
				'		</div>'+
				'	</div>'+
				'	<div class="clearboth"></div>'+
				'	<div class="line_3"></div>'+
				'</div>';//
					});
			//		alert(commentHtml);
					$(".comment_start").slideDown("slow").append(commentHtml);
					
			//		alert(obj2str(data));
					if(data.context.pageCount > 1)
					{
						 var pagitemp = '';
						 
						 var pageSize = data.context.pageCount;
						 var index = 1;
						 
						 
						/*  if(parseInt(data.index) - 2 > 0 && parseInt(data.index) + 2 < data.context.pageCount)
						 {
								 index = data.index - 2;
								 pageSize = parseInt(data.index) + 2;
						 } */
						
						 
	                       for(var i = index; i <= pageSize; i++)
	                       {
	                    	  if(i ==  data.index)
	                    	  {
	                    		  pagitemp +=' <li class="current"><a href="#">'+i+'</a></li>';
	                    	  } else
	                    		 {
	                    		  pagitemp +=' <li><a href="javascript:void(0)" onclick=commentPag("${path}/comment/select?pn='+i+''+data.condition+'")>'+i+'</a></li>';
	                    		 } 
	                       }
	                       commPagNum = '';
	                       
	                       if(data.index > 1)
	                       {
	                    	   commPagNum += '<a  href="javascript:void(0)" onclick=commentPag("${path}/comment/select?pn='+(parseInt(data.index)-1)+data.condition+'") class="prev">上一页</a>';
	                       }
	                       if(data.index < data.context.pageCount)
	                    	{
	                    	   commPagNum +=  '<a  href="javascript:void(0)" onclick=commentPag("${path}/comment/select?pn='+(parseInt(data.index)+1)+data.condition+'") class="next">下一页</a>';
	                    	}   
	                           
						
						var page = 
					'<div style="margin:20px 0px 24px;"></div><div class="block_pager">'+commPagNum+'  <div class="pages">'+
                       ' 	<ul>'+pagitemp+'</ul>'+
                      '  </div>'+
                        
                        '<div class="clearboth"></div>'+
                   ' </div><div class="line_3" style="margin:24px 0px 10px;"></div>';
                   $(".comment_pages").html(page);
					}
			}
		});
	}
</script>
<div class="block_comments_type_2">
	<h3></h3>
	<a href="javascript:void(0)" class="add_new" onclick="scrolltop()">我要评论</a>
	<div class="comment_start"></div>
	<div class="comment_pages"></div>
</div>


<div class="separator" style="height: 30px;"></div>

<div class="block_leave_reply">
	<h3>发表评论</h3>
	<%-- <c:choose>
		<c:when test="${sessionScope.user.username == null}">
			<p class="text">您的电子邮件地址不会被公开。必填项已被标记为 <span>*</span></p>
			<p>
				名字<span>*</span>
			</p>
			<div class="field">
				<input type="text" class="req" id="username"/>
			</div>

			<p>
				邮箱<span>*</span>
			</p>
			<div class="field">
				<input type="text" class="req" id="email"/>
			</div>
		</c:when>
		<c:otherwise>
			
		</c:otherwise>
	</c:choose> --%>
	<p>评论内容</p>
	<div class="textarea" >
		<textarea cols="1" rows="1" id="comment_textarea"></textarea>
	</div>
	<input type="button" class="general_button" value="提交评论" onclick="saveComment()"/>
	
	<script type="text/javascript">
		function scrolltop()
		{
			$("html,body").animate(
			{
				scrollTop : $(".block_comments_type_2").offset().top - 100
			}, 1000);
		}
		function saveComment()
		{
			$.ajax({
				url:'${path}/comment/add',
				type:'post',
				dataType:'json',
				data:{
			//		"user.username":$("#username").val(),
			//		"user.email":$("#email").val(),
					"content":$("#comment_textarea").val(),
					"commentTcId":"${commentTcid}",
					"commentType.id":"${commentTypeId}"
				},
				success : function(data)
				{
				//	alert(obj2str(data));
					if(!data.result)
					{
						$(".open_popup").click();
						return true;
					}
					var comments = 
						'<div class="comment"><div class="userpic">'+
						'<a href="#"><img src="${path}/'+data.object.user.userioc+'" alt="" /></a></div>'+

			'	<div class="comment_wrap">'+
			'		<div class="name">'+
			'			<p>'+
			'				<a href="#">'+data.object.user.username+'</a>'+
			'			</p>'+
			'		</div>'+
			'		<div class="date">'+
			'			<p>'+data.object.createDate+'</p>'+
			'		</div>'+
			'		<div class="content">'+
			'			<p>'+data.object.content+'</p>'+
			'		</div>'+
			'	</div>'+
			'	<div class="clearboth"></div>'+
			'	<div class="line_3"></div>'+
			'</div>';
					$(".comment_start").slideDown("slow").append(comments);
					$("#comment_textarea").val('');
					$("html,body").animate(
							{
								scrollTop : $(".comment_pages").offset().top - 200
							}, 1000);
				}
			});
		}
	</script>

</div>