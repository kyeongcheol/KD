<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  


<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="resources/file/css/css3.css" type="text/css" />

<script type="text/javascript">

function WishDel(test){ 

	var WISH_GOODS_NO =test.getAttribute("id");
		var WISH_MEMBER_NO =${sessionScope.MEMBER_NO};

	var theUrl = "delWish";
	

	$.ajax({url: theUrl,  
		type : "POST", 
		data : { 
			WISH_GOODS_NO : WISH_GOODS_NO,
			WISH_MEMBER_NO : WISH_MEMBER_NO
		},
		success: function(result){ 
			alert("위시리스트 삭제");
		},
		error: function(result){ 
			alert("실패"); 
		}   
	
	
	
});
	
}

    
function imgToggle(test){
	
	 
	var wish = document.getElementById(test.getAttribute("id")).getAttribute("src");

	if(wish=="resources/file/img/wishoff.png"){
   		test.setAttribute( "src", "resources/file/img/wish.ico" );
   		WishAdd(test);

	}else{
	test.setAttribute( "src", "resources/file/img/wishoff.png" );
		WishDel(test);
		}

}



function Search(){ 

	var keyword =document.getElementById("search").value;
	var MEMBER_NO = ${sessionScope.MEMBER_NO};	
	var currentPage = document.getElementById("currentPage").value;

	var theUrl = "search";

	
	$.ajax({url: theUrl,  
		type : "POST", 
		data : { 
			keyword : keyword,
			currentPage:currentPage,
			MEMBER_NO : MEMBER_NO
	
		},
		success: function(result){ 
   			$("#wrap").html(result); 

		},
		error: function(result){ 
			alert("실패"); 
		}   
	
	
	
});
	
}

   
   
   
    
    
  
     


 


</script>

</head>
<body>
<script type="text/javascript">
function CategoryOn(test){ 

	var CategoryNo = test.getAttribute("id");
	var currentPage = document.getElementById("currentPage").value;
    var theUrl = "category"; 
    	
    
    	$.ajax({url: theUrl,  
   		type : "POST", 
   		data : { 
   			 GOODS_TYPE: CategoryNo,
   			currentPage:currentPage
   		},
   		success: function(result){ 
   			$("#wrap").html(result); 
   		},
   		error: function(result){ 
   			alert("실패"); 
   		}   
    	
    }); 
    	
    }
    
    
    

function WishAdd(test){ 

		var WISH_GOODS_NO =test.getAttribute("id");
		var WISH_MEMBER_NO =${sessionScope.MEMBER_NO};
	var theUrl = "addWish";


	$.ajax({url: theUrl,  
		type : "POST", 
		data : { 
			WISH_GOODS_NO : WISH_GOODS_NO,
			WISH_MEMBER_NO : WISH_MEMBER_NO
		},
		success: function(result){ 
			alert("위시리스트 추가");
		},
		error: function(result){ 
			alert("실패"); 
		}   
	
	
	
});
	
}
    </script>

<div class="container" >
<!-- 검색 -->
	<div class="row" style="width:500px;float:right;">
	  <div class="col-lg-6" style="float:right;">
	    <div class="input-group">
	      <input type="text" class="form-control" placeholder="Search for..." id="search" >
	      <span class="input-group-btn">
	        <button class="btn btn-default" type="button" onclick="Search()">Go!</button>
	      </span>
	    </div><!-- /input-group -->
	  </div><!-- /.col-lg-6 -->
	</div><!-- /.row -->


<!--상단 카테고리  -->
<div class="btn-group btn-group-justified" role="group" aria-label="..." style="padding-bottom: 50px;padding-top: 10px;">
  <div class="btn-group" role="group">
    <button id="1" type="button" class="btn btn-default" onclick="javascript:CategoryOn(this);">Salad</button>
  </div>
  <div class="btn-group" role="group">
    <button id="2" type="button" class="btn btn-default" onclick="javascript:CategoryOn(this);">Beverage</button>
  </div>
  <div class="btn-group" role="group">
    <button id="3" type="button" class="btn btn-default" onclick="javascript:CategoryOn(this)">Side Dish</button>
  </div>
  <div class="btn-group" role="group">
    <button id="4" type="button" class="btn btn-default" onclick="javascript:CategoryOn(this)">Package</button>
  </div>
</div>

<div id="wrap">
 <jsp:include page="goodsList-Body.jsp"></jsp:include>
</div>

</div>

</body>

</html>