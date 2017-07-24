<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="resources/file/js/jquery-2.0.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="/SG/resources/file/css/myWish.css">

<script type="text/javascript">
 
 function wish_del(wish_no)
 {
	 
	 
	 $.ajax
	 ({
		 type : "post",
		 url : "/SG/myWishList/wish_del",
		 data : ({"WISH_NO":wish_no}),
		 success : function(data)
		 {
			 alert("삭제되었습니다.");
			 $("#wish_wrap").html(data);
		 },
	     error : function()
	     {
	    	 alert("에러");
	     }
		 
	 });
 }
 
 //wish ajax Paging
 function ajaxPaging(page)
 {	
		
		alert(page);
		
		var dataList =
		({"PAGE" : page});	

		var url1 = "/SG/wishList";
		
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
나의 위시리스트</div>

</div>


<table class="wish_table" width="94%">
<colgroup>
	<col width="5%" />
	<col width="10%"/>
	<col width="10%" />
	<col width="20%" />
	<col width="20%" />
	<col width="10%" />
	<col width="5%" />
</colgroup>
<tr>
	<th>NO</th>
	<th>날짜</th>
	<th>상품번호</th>
    <th>상품이름</th>
    <th>상품이미지</th>
    <th>상품가격</th>
    <th></th>
</tr>

          <c:choose>
               <c:when test="${fn:length(wishlist) le 0}">
 					<tr>
                 		<td colspan="7">담은 위시리스트가 없습니다.</td>
                 	</tr>
                 </c:when>
                 <c:otherwise>

   				 <c:forEach var="list"  items="${wishlist}" varStatus="stat">
   				 <input type="hidden" id="WISH_MEMBER_NO" name="WISH_MEMBER_NO" value="${list.WISH_MEMBER_NO}">
                  <c:url var="viewURL" value="view" >
                     <c:param name="no" value="${list.WISH_GOODS_NO}" />
                     <c:param name="gcurrentPage" value="${gcurrentPage}" />
                  </c:url>
                 	<tr> 
                     	<td>${list.RNUM}</td>
                     	<td>${list.WISH_REG_DATE}</td>
                     	<td>${list.WISH_GOODS_NO}</td>
                     	<td><a href="goodsDetail?goodsNo=${list.WISH_GOODS_NO}&currentPage=${gcurrentPage}">
                     	${list.GOODS_NAME}</a></td>
                        <td>
                        <img src="resources/file/img/${list.GOODS_THUMBNAIL}" width="120" height="90"
                        onclick="javascript:location.href=
                        'goodsDetail?goodsNo=${list.WISH_GOODS_NO}&currentPage=${gcurrentPage}'"/>
                        </td>
                        <td>${list.GOODS_PRICE}</td>
                        <td class="wish_del">
                        <div class="board_search_table">
                        <input type="button" value="삭제하기"></div>
                        <input type="hidden" id="WISH_NO" name="WISH_NO" value="${list.WISH_NO}">  
                        </td>
                        
                    </tr>
                 </c:forEach> 
                 </c:otherwise> 
                </c:choose>  
           
</table>

<div class="wish_bottom">
<div class="wish_bottom_font">
<font color="green">위시 리스트 내역에 있는 상품 정보를 보고 싶으시면 상품이름이나 이미지를 눌러주세요</font></div>
</div>
<div class="paging">${pagingHtml}</div>
</div>

</body>
<script>
$(".wish_del").on("click", function(e) //수정
{ 
   e.preventDefault(); 	 		
   var wish_no =$(this).parent().find("#WISH_NO").val();
  
   wish_del(wish_no);
});
</script>
</html>
