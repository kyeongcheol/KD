<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 상세 보기</title>

<link rel="stylesheet" href="resources/file/css/css3.css" type="text/css" />

<style type="text/css">
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

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script> 
<script type="text/javascript">

$(document).ready(function(){
	
	
	

    $("#button1").click(function ajax(){ 
    	
    	
    	var src =document.getElementById("commentContents");
    	
    	
    	
    	var commentStr = $("#commentContents").val();
    	var commentNo = $("#commentGoodsNo").val();
    	$.ajax({url: "goodsComment",  
   		type : "POST", 
   		data : { 
   			str : commentStr,
   			no : commentNo
   		},
   		success: function(result){ 
   			src.setAttribute("value","");
           	$("#commentList").html(result); 
   		},
   		error: function(result){ 
   			alert("실패"); 
   		}   		
       });
   }); 
});



function change(num)
{
var x  = document.getElementById("amount");
var y = Number(x.value) + num;
if(y < 1) y = 1;
x.value = y;


cal();
};

function cal(){

	var price = "${goodsDetail.GOODS_PRICE }";
	
	var multi = document.getElementById("amount").value;

	
	
	var num = price * multi;
	
	
	$("#totalprice").html(num);
	
};


</script>

</head>
<body>
<div id="wrap" style="height:500px; width:1000px; margin-left:auto;
   			margin-right:auto;">

<div class="img" style=" float:left; width: 500px; height: 500px;">


<img src="resources/file/img/${goodsDetail.GOODS_THUMBNAIL}" style="width: 400px; margin-top: 50px;" >
</div>

	<div class="inform" style="float:right; width:300px;height:300px; background:#FFFFFF; margin-right: 100px;padding-top:15px;margin-top:15px; font-family: PureunJeonnam; ">

	<table class="type05" width="400px">
	<tr><td>상품이름</td><td>${goodsDetail.GOODS_NAME}</td></tr>
	<tr><td>상품 가격</td><td>${goodsDetail.GOODS_PRICE } 원</td></tr>
	<tr><td>포인트</td><td>${point} point</td></tr>
	<tr><td>원산지</td><td>국내산</td></tr>
	<tr><td>상세 설명</td><td>${goodsDetail.GOODS_DETAIL}</td></tr>
    <tr>
    	<td>상품 수량</td>
       	<td>
       	<div>
      		        			 <img src='resources/file/img/down.ico' style="width: 20px; float: left" onclick='javascript_:change(-1);'>
      		 
       			 <input type='text' id="amount" name='amount' value='1' size='3' readonly style="float: left">
       			        			 <img src='resources/file/img/up.ico' style="width: 20px; float: left" onclick='javascript_:change(1);'>
       </div></td>
    </tr>
    <tr>
    <td>총 가격</td><td><span id="totalprice">${goodsDetail.GOODS_PRICE }</span></td>
    
    </tr>


	
	</table>
	
	<br/>
	
	
	<input type="button" value="BUY" onclick="javascript:location.href='goodsOrder?GOODS_NO=${goodsDetail.GOODS_NO}'"/>&nbsp;&nbsp;&nbsp;
	<input type="button" value="basket"/>
	</div>
</div>

<div class="content" style="margin-left:auto;
   			margin-right:auto; width:1000px;">
	
	<center>
	<img src="resources/file/img/${image}" style="width: 1000px;">
	</center>
	
	
	</div>




<center>
<div id="comment" class="comment" style="background:#F6F6F6;padding: 20px;width:960px;margin-left:auto;
   			margin-right:auto;">

<textarea rows="5" cols="100" id="commentContents" name="commentContents" ></textarea>
<input type="button" id="button1" value="후기 작성" height="5" />
<input type="hidden" id="commentGoodsNo" name="goodsNo" value="${goodsDetail.GOODS_NO}"/>

</div>

<div id="commentList" style="width:1000px;margin-left:auto; margin-right:auto;"><jsp:include page="Comment/goodsComment.jsp"></jsp:include></div>
</center>
</body>
</html>