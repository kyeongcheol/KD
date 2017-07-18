<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 상세 보기</title>

<link rel="stylesheet" href="resources/file/css/css3.css" type="text/css" />
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
 -->
 <style type="text/css">

.star_rating {font-size:0; letter-spacing:-4px;}
.star_rating a {
    font-size:22px;
    letter-spacing:0;
    display:inline-block;
    margin-left:5px;
    color:#ccc;
    text-decoration:none;
}
.star_rating a:first-child {margin-left:0;}
.star_rating a.on {color:#777;}


table.type05 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    margin: 20px 10px;
}
table.type05 th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #efefef;
}
table.type05 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}




table.type07 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border: 1px solid #ccc;
    margin: 0px 0px;
}
table.type07 thead {
    border-right: 1px solid #ccc;
    border-left: 1px solid #ccc;
    background: #4FC9DE;
}
table.type07 thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #fff;
}
table.type07 tbody th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #F1FFFF;
}
table.type07 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
</style>


<script type="text/javascript">


$(document).ready(function(){


    $("#button1").click(function ajax(){ 
    	
    	
    	var src =document.getElementById("commentContents");
    	
    	
    	
    	var commentStr = $("#commentContents").val();
    	var commentNo = $("#commentGoodsNo").val();
    	var commentRate = rate;
    	
    	$.ajax({url: "goodsComment",  
   		type : "POST", 
   		data : { 
   			str : commentStr,
   			no : commentNo,
   			rate : commentRate
   		},
   		success: function(result){ 
   			$("#commentContents").val("");
   			$("#commentList").html(result); 
   		},
   		error: function(result){ 
   			alert("실패"); 
   		}   		
       });
   }); 
});





$( document ).ready(function() {

	$( ".star_rating a" ).click(function() {
	    $(this).parent().children("a").removeClass("on");
	    $(this).addClass("on").prevAll("a").addClass("on");
	    
	    rate = $(this).attr("id");

	    return false;
	});
});



var rate=0;

function cal(){

	var price = "${goodsDetail.GOODS_PRICE }";
	var kcal = "${goodsDetail.GOODS_KCAL}";
	var multi = document.getElementById("amount").value;

	
	
	var num = price * multi;
	var kcalNum = kcal * multi;
	
	document.getElementById("totalprice_put").setAttribute("value",num);
	document.getElementById("totalKcal_put").setAttribute("value",kcalNum);

	$("#totalprice").html(num);
	$("#totalKcal").html(kcalNum);
	
}

function change(num)
{
var x  = document.getElementById("amount");
var y = Number(x.value) + num;
if(y < 1) y = 1;
x.value = y;


cal();
}



function DetailBasket(){
	var GOODS_NO = document.getElementById("commentGoodsNo").value;
	var GOODS_AMOUNT = document.getElementById("amount").value;
	var GOODS_PRICE = document.getElementById("totalprice_put").value;
	
	var theUrl = "BasketAdd";
	
    	$.ajax({url: theUrl,  
   		type : "POST", 
   		data : { 
   			goodsNo: GOODS_NO,
   			GOODS_AMOUNT:GOODS_AMOUNT,
   			GOODS_PRICE : GOODS_PRICE
   		},
   		success: function(result){ 
   			$("#sideBar").html(result); 

   			alert("장바구니에 추가했습니다.");
   			

   		},
   		error: function(result){ 
   			alert("실패"); 
   		}   
    	
    });
    	
	
}





function dComment(test){
	var COMMETN_NO = test.getAttribute("id");
	var COMMENT_ID = test.getAttribute("name");
	var commentNo = $("#commentGoodsNo").val();

	var theUrl = "CommentDel";
	
    	$.ajax({url: theUrl,  
   		type : "POST", 
   		data : { 
   			COMMETN_NO: COMMETN_NO,
   			COMMENT_ID:COMMENT_ID,
   			COMMENT_NO:commentNo
   		},
   		success: function(result){ 
   			alert("삭제 했습니다.");
   			$("#commentList").html(result); 

   		},
   		error: function(result){ 
   			alert("실패"); 
   		}   
    	
    });
    	
	
}



</script>

</head>
<body>


<div id="wrap" style="height:500px; width:1000px; margin-left:auto;
   			margin-right:auto;">

<div class="img" style=" float:left; width: 500px; height: 500px;">


<img src="resources/file/goodsFile/${goodsDetail.GOODS_THUMBNAIL}" style="width: 400px; margin-top: 50px;" >
</div>

	<div class="inform" style="float:right; width:300px;height:300px; background:#FFFFFF; margin-right: 100px;padding-top:15px;margin-top:15px; font-family: PureunJeonnam; ">

	<table class="type05" width="450px" >
	<tr><td>상품이름</td><td>${goodsDetail.GOODS_NAME}</td></tr>
	<tr><td>상품 가격</td><td>${goodsDetail.GOODS_PRICE } 원</td></tr>
	<tr><td>포인트</td><td>${point} point</td></tr>
	<tr><td>원산지</td><td>국내산</td></tr>
	<tr><td>상세 설명</td><td>${goodsDetail.GOODS_DETAIL}</td></tr>
    <tr>
    	<td>상품 수량</td>
       	<td>
       	<div>
      		     <img src='resources/file/img/down.ico' style="width: 20px; float: left" onclick="change(-1);">
       			 <input type='text' id="amount" name='amount' value='1' size='3' readonly style="float: left">
       			 <img src='resources/file/img/up.ico' style="width: 20px; float: left" onclick="change(1);">
       </div></td>
    </tr>
    <tr>
    <td>총 가격</td><td><span id="totalprice">${goodsDetail.GOODS_PRICE }</span>원&nbsp;&nbsp;<span id="totalKcal">${goodsDetail.GOODS_KCAL}</span>Kcal
    	<input type="hidden" id="totalprice_put" value="${goodsDetail.GOODS_PRICE}"></td>
        <input type="hidden" id="totalKcal_put" value="${goodsDetail.GOODS_KCAL}"></td>
    
    </tr>


	
	</table>
	
	<br/>
	<input type="button" class="btn btn-success btn-lg" value="BUY" onclick="javascript:location.href='goodsOrder?GOODS_NO=${goodsDetail.GOODS_NO}'"/>&nbsp;&nbsp;&nbsp;
	<input type="button" class="btn btn-warning btn-lg" value="basket" onclick="DetailBasket();"/>
	</div>
</div>

<div class="content" style="margin-left:auto;
   			margin-right:auto; width:1000px;">
	
	<center>
	<img src="resources/file/goodsFile/${image}" style="width: 1000px;">
	</center>
	
	
	</div>




<center>
<div id="comment" class="comment" style="background:#F6F6F6;padding: 20px;width:1000px;margin-left:auto;
   			margin-right:auto;padding: 20px;">
   			맛있었나요?
<p class="star_rating" id="star_rating">
    <a href="#" id="1">★</a>
    <a href="#" id="2">★</a>
    <a href="#" id="3">★</a>
    <a href="#" id="4">★</a>
    <a href="#" id="5">★</a>
</p>


   			
   			
   			

<textarea rows="4" cols="100" id="commentContents" name="commentContents"   ></textarea>
<input type="button" class="btn btn-default" id="button1" value="후기작성" style="height:60pt;width:60pt;margin-bottom: 70px;"  />
<input type="hidden" id="commentGoodsNo" name="goodsNo" value="${goodsDetail.GOODS_NO}"/>

</div>

<div id="commentList" style="width:1000px;margin-left:auto; margin-right:auto;"><jsp:include page="Comment/goodsComment.jsp"></jsp:include></div>
</center>
</body>
</html>