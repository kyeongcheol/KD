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
 
function basket_order()
{
   
   var f =  document.getElementById('frm');

   
   if($("input:checkbox[name='BASKET_NO']").is(":checked") == false) 
   {
      alert("상품을 선택해 주세요");
      return false;
   }
   
   else
   {
      f.submit();
  
   }
   
}

function basket_del()
{
	
	if($("input:checkbox[name='BASKET_NO']").is(":checked") == false) 
	{
	      alert("상품을 선택해 주세요");
	      return false;
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
       } //if
       else 
       {	
         param = param + "&BASKET_NO="+$(this).parent().children("#BASKET_NO").val(); 
         alert(param); 
       
       } //else

      }); //선택자(checked)
	
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
     }); //ajax
     
   } //else
   	
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

<div class="board_search_table" style="float:left; margin-left:880px; margin-top:30px;">
<input class="button_all" type="button" value="전체선택"/>	
<input class="button_unall" type="button" value="전체해제"/>
</div>


<form id="frm" action="basketOrder" method="post">
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
                     	<td>${list.RNUM}</td>
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
                        <input type="checkbox" id="BASKET_NO" name="BASKET_NO" value="${list.BASKET_NO}"></td>
                        <%-- <input type="hidden" id="BASKET_NO" name="BASKET_NO" value="${list.BASKET_NO}">  --%>
                    </tr>
                 </c:forEach> 
                 </c:otherwise> 
                </c:choose> 
                
           
</table>

<div class="board_search_table" style="float:left; margin-left:1280px; margin-top:20px;">
<input type="button" id="orderFrm" value="주문하기" onclick="basket_order()">
<input type="button" id="delFrm" value="삭제하기" onclick="basket_del()"> 
</div>
</form>

<div class="wish_bottom">
<div class="wish_bottom_font">
<font color="green">장바구니 내역에 있는 상품 정보를 보고 싶으시면 상품이름이나 이미지를 눌러주세요</font></div>
</div>

<div class="paging">${pagingHtml}</div>
</div>


</body>
<script>
$(".button_all").click(function()
{

	   $("input:checkbox[name='BASKET_NO']").not(":checked").trigger("click");
});

$(".button_unall").click(function()
{
	$("input:checkbox[name='BASKET_NO']:checked").trigger("click");
});

</script>
</html>
