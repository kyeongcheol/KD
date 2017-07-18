<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="resources/file/js/jquery-2.0.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="/SG/resources/file/css/myBasket.css">

<script type="text/javascript">
 

 $(document).ready(function() 
 {
	  $("#orderFrm").on("click", function()
	  {
		 if ( $(".chkclass :checked").size() < 1 ) 
		 {
		      alert("주문할 상품을 선택해주세요!!");
		      return;
		 }
	  });
	  
	  $("#delFrm").on("click", function() 
	  {
	         
	    if ( $(".chkclass :checked").size() < 1 ) 
	    {
	      alert("삭제할 상품을 선택해주세요!!");
	      return;
	    }
	    
	    else 
	    {
	      var param = "";
	      $(".chkclass :checked").each(function() 
	      {
	        if( param == "" )
	        {
	          param = "BASKET_NO="+$(this).parent().children("#BASKET_NO").val();
	          alert(param);
	        }
	        else 
	        {	param = param + "&BASKET_NO="+$(this).parent().children("#BASKET_NO").val(); alert(param); }
	 
	      });
	           
	      $.ajax
	      ({
	        url : '/SG/myBasketList/Delete',
	        type : 'post',
	        data : param,
	        success : function(data) 
	        {
	           alert("삭제되었습니다.");
	           $("#wish_wrap").html(data);
	        },
	        error : function() { console.log('error');}
	      });
	    }
	  });
	});
 
 function checkboxSelectQue(n,obj) 
 {
	    var i;
	    var chk = document.getElementsByName(obj);
	    var tot = chk.length;
	    for (i = 0; i < tot; i++) 
	    {
	        if (n == 1) chk[i].checked = true;
	        if (n == 2) chk[i].checked = false;
	    }
 }

 function ajaxPageView(page)
 {	
		
		alert(page);
		
		var dataList =
		({"PAGE" : page});	

		var url1 = "/SG/mybasket";
		
	    $.ajax({    
	     
	    	type : "POST",
	        url : url1,
	        data : dataList,
	        dataType : "text",      
	        
	        error : function() {
	      	  
	      		alert('오류임!');     	
	        },
	       
	        success : function(data) 
	        {  
	      		 $("#wish_wrap").html(data);          		
	        }
	        
	      });        

	}
</script>

</head>

<body>
<div id="wish_wrap">
<div class="wish_title">
<div class="wish_title_font">
장바구니
</div>
</div>
<div style="float:left; margin-left:863px; margin-top:30px;">
<input class="button_all" type="button" value="전체선택" onclick="checkboxSelectQue(1,'chk[]')" />	
<input class="button_all" type="button" value="전체해제" onclick="checkboxSelectQue(2,'chk[]')" />
</div>


<form action="" method="post">
<table class="wish_table" width="94%">
<colgroup>
	<col width="5%" />
	<col width="10%"/>
	<col width="10%" />
	<col width="20%" />
	<col width="10%" />
	<col width="10%" />
	<col width="25%" />
	<col width="10%" />
</colgroup>
<tr>
	<th>NO</th>
	<th>날짜</th>
    <th>상품이름</th>
    <th>상품이미지</th>
    <th>상품가격</th>
    <th>상품수량</th>
    <th>재료이름</th>
    <th>선택</th>
</tr>

          <c:choose>
               <c:when test="${fn:length(basketlist) le 0}">
 					<tr>
                 		<td colspan="8" style="text-align:center;">담은 장바구니가 없습니다.</td>
                 	</tr>
                 </c:when>
                 <c:otherwise>

   				 <c:forEach var="list" items="${basketlist}" varStatus="stat">
                 	<tr> 
                     	<td>${list.}</td>
                     	<td>${list.BASKET_REG_DATE}</td>
                     	<td><a href="goodsDetail?goodsNo=${list.BASKET_GOODS_NO}&currentPage=${gcurrentPage}">
                     	${list.BASKET_GOODS_NAME}</a></td>
                        <td>
                        <img src="resources/file/img/${list.GOODS_THUMBNAIL}" width="120" height="90"
                        onclick="javascript:location.href=
                        'goodsDetail?goodsNo=${list.BASKET_GOODS_NO}&currentPage=${gcurrentPage}'"/>
                        </td>
                        <td>${list.GOODS_PRICE}</td>
                        <td>${list.BASKET_GOODS_AMOUNT}</td>
                        <td>${list.BASKET_TOPPING_NAME}</td>
                        <td class="chkclass">
                        <input type="checkbox" id="BASKET_NO" name="chk[]" value="${list.BASKET_NO}"></td>
                        <input type="hidden" id="BASKET_NO" value="${list.BASKET_NO}"> 
                    </tr>
                 </c:forEach> 
                 </c:otherwise> 
                </c:choose> 
                
           
</table>

<div style="float:left; margin-left:1250px; margin-top:20px;">
<input type="submit" id="orderFrm" value="주문하기">
<input type="button" id="delFrm" value="삭제하기"> 
</div>
</form>
<div class="wish_bottom">
<div class="wish_bottom_font">
<font color="green">장바구니 내역에 있는 상품 정보를 보고 싶으시면 상품이름이나 이미지를 눌러주세요</font></div>
</div>
<div class="paging">${pagingHtml}</div>
</div>

</body>
</html>
