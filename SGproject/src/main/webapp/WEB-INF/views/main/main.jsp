<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Place somewhere in the <head> of your document -->

<link rel="stylesheet" href="resources/file/css/flexslider.css" type="text/css">
<link rel="stylesheet" href="resources/file/css/main.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="resources/file/js/jquery.flexslider.js"></script>



<!-- Place in the <head>, after the three links -->
<script type="text/javascript" charset="utf-8">
  $(window).load(function() {
    $('.flexslider').flexslider({
    	animation: "slider"
    });
  });
</script>
	 
</head>
<body>

<!-- Place somewhere in the <body> of your page -->
<div class="flexslider" style="width: 100%; margin:auto; z-index: -10;">

  <ul class="slides">
    <li>
   		 <img src="resources/file/img/Slide4.jpg" style="height: 300px"/>
   </li>
    
    <li>
         <img src="resources/file/img/Slide2.jpg" style="height: 300px"/>
                 
    </li>
    <li>
      <img src="resources/file/img/Slide3.jpg" style="height: 300px"/>
    </li>
  </ul>
</div>
<div id="wrap">
  <div id="best_main">best상품</div>
  <div id="event_main">event메인</div>
  <div id="review_main">review메인</div>
</div>


</body>
</html>