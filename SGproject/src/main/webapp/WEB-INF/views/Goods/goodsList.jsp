<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  


<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="resources/file/css/css3.css" type="text/css" />


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script> 
<script type="text/javascript">

function basket(test){
   alert("장바구니에 담았습니다.");
   

   
   var GOODS_NO = test.getAttribute("id");
   var GOODS_AMOUNT=1;
   var id=$(document).getAttribute("id").getAttribute("id");
   var MEMBER_NO =60;
   location.href = 'addBasket?BASKET_GOODS_NO='+GOODS_NO+'&id='+id+'&BASKET_GOODS_AMOUNT='+GOODS_AMOUNT;
   
}




    		
    function ajax(test){ 

    	var BASKET_GOODS_AMOUNT = 1;
 		var BASKET_GOODS_NO =test.getAttribute("id");
 		var BASKET_MEMBER_NO =60;
    	var theUrl = "addBasket";
    	
    
    	$.ajax({url: theUrl,  
   		type : "POST", 
   		data : { 
   			BASKET_GOODS_NO : BASKET_GOODS_NO,
   			BASKET_MEMBER_NO : BASKET_MEMBER_NO,
   			BASKET_GOODS_AMOUNT : BASKET_GOODS_AMOUNT
   		},
   		success: function(result){ 
   			alert("장바구니에 담았습니다");
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
alert("위시리스트에 담았셈");
}else{
test.setAttribute( "src", "resources/file/img/wishoff.png" );
alert("위시리스트에서 삭제");
}



}


</script>
</head>
<body>

<div class="searchBar" style="width:1000px; height:100px; padding:auto; margin:auto;">
<form action="goodsSearch?keyword=${keyword}" method="GET">
<center>
<input type="text" name="keyword">
<input type ="submit" value="상품검색">
</center>
</form>
</div>

<div style="margin-left:200px; margin-right:200px; padding-left:50px; padding-right: 50px;">
 <c:choose>
           <c:when test="${fn:length(goodsList) > 0}">

         <c:forEach var="goodsList" items="${goodsList}" varStatus="stat">
                  <c:url var="viewURL" value="view" >
                     <c:param name="no" value="${goodsList.GOODS_NO}" />
                      <c:param name="currentPage" value="${currentPage}" />
                  </c:url>
   
      
      <div class="box" style="border:0px solid black; list-style:none; width:300px; height:370px; float:left; margin-left: 20;margin-right: 20px; margin-bottom: 50px;">   
         <hr>
         <div class="box_image"  style=" width:300px; height:300px; margin:auto; padding-left:8px; margin-top: 10px;">
         <img src="resources/file/img/${goodsList.GOODS_THUMBNAIL}" width="284" height="284" onclick="javascript:location.href='goodsDetail?goodsNo=${goodsList.GOODS_NO}&currentPage=${currentPage}'"/></div>
      
         <center>
         <div class="name" style="font-family: PureunJeonnam;">${goodsList.GOODS_NAME}</div>
         <div class="price"  style="font-family: PureunJeonnam;">${goodsList.GOODS_PRICE }</div>
         </center>
         
         <div class="basketWish" style="float:left;">
         <div class="basket" style="margin:auto; padding-left:10px;float:left;">
         <img id="${goodsList.GOODS_NO}" src="resources/file/img/basket.ico" width="20" height="20" onclick="ajax(this)"/>
         <input type="hidden" name="id" id="${id}"/>
         </div>
         <div class="wish" style="margin:auto; padding-left:10px;float:left">
         <img id="wishoff${stat.index}" src="resources/file/img/wishoff.png" width="20" height="20" onclick="imgToggle(this)"/>
         </div>
         </div>

         <br>
         
         

   </div>
   
         
          
      
      </c:forEach>
   
        </c:when>
                
                <c:otherwise>
                    <tr>
                        <td colspan="4">조회된 결과가 없습니다.</td>
                    </tr>
                </c:otherwise>
            </c:choose>





   

   

</div>

</div>


</body>

</html>