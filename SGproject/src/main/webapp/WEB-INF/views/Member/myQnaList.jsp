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

</script>

</head>

<body>
<div id="wish_wrap">
<div class="wish_title">
<div class="wish_title_font">
나의 QNA 게시판
</div>
</div>


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

<div class="wish_bottom">
<div class="wish_bottom_font">
<font color="green">${sessionScope.MEMBER_ID}님이 질문하신 답변을 보시려면 글 제목을 눌러주세요</font></div>
</div>

<div class="paging">${pagingHtml}</div>
</div>

</body>

</html>
