<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="initial-scale=1">
<title>Insert title here</title>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/aio.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/liquid-slider.css" />" rel="stylesheet" />

<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.easing.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.touchSwipe.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.liquid-slider.min.js" />"></script>



</head>
<body>
<header>
	<div class="header-inner">
		<div class="nav-icon">
			<div class="icon-part"></div>
			<div class="icon-part"></div>
			<div class="icon-part"></div>
		</div>
		<h1 class="logo">SME Mobile</h1>
	</div>
</header>

<div class="questions-container">

	<div class="liquid-slider" id="questions-slider">
		<c:forEach items="${questions}" var="question">
			<div>
	        	<p class="questions"> ${question.description}</p>
				<c:forEach items="${question.securityAnswers}" var="answer">
					<div class="bt-answers-container">
						<a href="#right" id="${question.id}" class="btn btn-lg btn-outline" onclick="api.setNextPanel('right');api.updateClass($(this))">${answer.description}</a>
					</div>
				</c:forEach>
	     	</div>
		</c:forEach>
		<div>
			<div class="answers-checking-container">
        		<div class="loader"></div><p>Aguarde, estamos conferindo suas respostas.</p>
        	</div>
     	</div>
	</div>
</div>

<script>
	var hashmap = {};
	
	 var queries = {};
	  $.each(document.location.search.substr(1).split('&'),function(c,q){
	    var i = q.split('=');
	    queries[i[0].toString()] = i[1].toString();
	  });
	
	Object.size = function(obj) {
	    var size = 0, key;
	    for (key in obj) {
	        if (obj.hasOwnProperty(key)) size++;
	    }
	    return size;
	};
	
	$(function(){
		
	     $('#questions-slider').liquidSlider();
	     api = $.data( $('#questions-slider')[0], 'liquidSlider');
	     api.options.slideEaseDuration = 800;
	     
	     $('a').click(function(){
	 		hashmap[$(this).attr('id')] = $(this).text();
	 		console.log(Object.size(hashmap));
	 		if(Object.size(hashmap) > 2){
	 			 $.ajax({
	 			        url:"/aio/pergunta/enviar",
	 			        type:"POST",
	 			        contentType:"application/json",
	 			        data:JSON.stringify({
	 			        	questions:hashmap,
	 			        	key: queries['key']
	 			         }),
	 			        success: function(data, textStatus, xhr){
	 			            console.log(data);
	 			            console.log(textStatus);
	 			            console.log(xhr);
		 			           $(".answers-checking-container").addClass( "alert alert-success" ).html("<strong>Sucesso! </strong> "+ data.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, ''));
	 			        },
	 			        error:function(data){
	 			        	console.log(data.responseText);
	 			           $(".answers-checking-container").addClass( "alert alert-danger" ).html("<strong>Erro: </strong> "+ data.responseText.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, ''));
	 			        }
	 			    })
	 		}
	 		  return false;
	 	});
	});
	
	
</script>

</body>
</html>