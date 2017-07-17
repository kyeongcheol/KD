<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="resources/file/js/jquery-2.0.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<style type="text/css">
@import url('//cdn.rawgit.com/young-ha/webfont-archive/master/css/PureunJeonnam.css');
#wish_wrap 
{
	width: 1500px;
	font-family: PureunJeonnam;
}
.wish_title {text-align: left; color: #212121; padding-top: 50px;}
.wish_title div {padding-top: 20px; padding-bottom: 10px;}
.wish_title_font {font-family: PureunJeonnam; font-size: 25px; font-weight: bold;}
.title_font2 {font-family: PureunJeonnam; font-size: 20px; color: #999;}
.wish_table{
	text-align: center;
	font-family: PureunJeonnam;
	font-size:15px;
	font-weight:bold;
}
.faq_line {margin: 0 50px 0 50px;}

.wish_table th {
	text-align: center; 
	border-top: 1px solid #e5e5e5; 
	border-bottom: 1px solid #e5e5e5;
	padding: 8px 0; 
	font-size:15px; 
	background: #f5f5f5;
	font-family: PureunJeonnam;
}

.wish_table td {
	text-align: center; 
	border-top: 1px solid #e5e5e5; 
	border-bottom: 1px solid #e5e5e5; 
	font-family: PureunJeonnam;
}
.faq_admin_search{
	width: 60%;
	margin-right: auto;
	margin-left: auto;
	font-family: PureunJeonnam;
}
.faq_admin_search input[type=text]{
	width: 300px;
	height: 25px;
}
.faq_admin_searchT {
	text-align: center; 
	border-top: 1px solid #e5e5e5; 
	border-bottom: 1px solid #e5e5e5; 
	padding: 8px 0; 
	background: #f5f5f5;
	
}
.faq_select{
	height: 25px;
}
 
   
.paging{text-align:center;height:32px;margin-top:5px;margin-bottom:15px;}
.paging a,
.paging strong{display:inline-block;width:36px;height:32px;line-height:28px;font-size:14px;border:1px solid #e0e0e0;margin-left:5px;
-webkit-border-radius:3px;
   -moz-border-radius:3px;
		border-radius:3px;
-webkit-box-shadow:1px 1px 1px 0px rgba(235,235,235,1);
	-moz-box-shadow:1px 1px 1px 0px rgba(235,235,235,1);
		  box-shadow:1px 1px 1px 0px rgba(235,235,235,1);
}
.paging a:first-child{margin-left:0;}
.paging strong{color:#fff;background:#337AB7;border:1px solid #337AB7;}
.paging .page_arw{font-size:11px;line-height:30px;}

.wish_bottom {text-align: center; color: #212121; padding-top: 40px;}
.wish_bottom div {padding-top: 20px; padding-bottom: 10px;}
.wish_bottom_font {font-family: PureunJeonnam; font-size: 20px;}

/* 검색 css */
.board_search_table{font-family: PureunJeonnam; font-size: 12px; color: #212121;}
.board_search_table thead th{text-align: center; border-top: 1px solid #e5e5e5; border-bottom: 1px solid #e5e5e5; padding: 2px 0; background: #f5f5f5;}
.board_search_table input[type=text]{width:200px;height: 20px;padding-left:13px; }
.board_search_table input[type=button]{border:1px solid silver; background-color: white; padding:2px 6px; border-radius:5px;}
.board_search_table input[type=button]:HOVER{background-color: #e5e5e5;  }

</style>

<script type="text/javascript">
 
 function wish_del()
 {
	 var wish_no = $("#WISH_NO").val();
	 
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
   				 <input type="hidden" id="WISH_NO" name="WISH_NO" value="${list.WISH_NO}">
   				 <input type="hidden" id="WISH_MEMBER_NO" name="WISH_MEMBER_NO" value="${list.WISH_MEMBER_NO}">
                  <c:url var="viewURL" value="view" >
                     <c:param name="no" value="${list.WISH_GOODS_NO}" />
                     <c:param name="gcurrentPage" value="${gcurrentPage}" />
                  </c:url>
                 	<tr> 
                     	<td>${stat.count}</td>
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
                        <td><a onclick="wish_del()">삭제</a></td> 
                        
                    </tr>
                 </c:forEach> 
                 </c:otherwise> 
                </c:choose>  
           
</table>

<div class="wish_bottom">
<div class="wish_bottom_font">
<font color="green">위시 리스트 내역에 있는 상품 정보를 보고 싶으시면 상품이름이나 이미지를 눌러주세요</font></div>
</div>
</div>

</body>
</html>
