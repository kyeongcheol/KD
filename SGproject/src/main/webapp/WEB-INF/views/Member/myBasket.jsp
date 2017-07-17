<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
 
/* function basket_del()
 {
	 var basket_no = $("#BASKET_NO").val();
	 
	 $.ajax
	 ({
		 type : "post",
		 url : "/SG/myBasketList/Delete",
		 data : ({"BASKET_NO":basket_no}),
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
 } */
 
 $(document).ready(function() 
 {
	  $("#submitFrm").on("click", function() 
	  {
	         
	    if ( $(".chkclass :checked").size() < 0 ) 
	    {
	      alert("체크 개수가 0개 이하입니다.");
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
	           alert("성공");
	           $("#wish_wrap").html(data);
	        },
	        error : function() { console.log('error');}
	      });
	    }
	  });
	});
 


</script>

</head>

<body>
<div id="wish_wrap">
<div class="wish_title">
<div class="wish_title_font">
장바구니</div>

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
               <c:when test="${wishlist} == null">
 					<tr>
                 		<td colspan="7">담은 장바구니가 없습니다.</td>
                 	</tr>
                 </c:when>
                 <c:otherwise>

   				 <c:forEach var="list" items="${basketlist}" varStatus="stat">
                 	<tr> 
                     	<td>${stat.count}</td>
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
                        <input type="checkbox" id="BASKET_NO" value="${list.BASKET_NO}"></td>
                        <input type="hidden" id="BASKET_NO" value="${list.BASKET_NO}"/> 
                    </tr>
                 </c:forEach> 
                 </c:otherwise> 
                </c:choose> 
                
           
</table>
<%-- <form action="/SG/wishList" method="post">
<table class="board_search_table" width="94%" style="padding-top: 20px;">
               <colgroup>
                  <col width="20%" />
                    <col width="30%" />
                    <col width="50%" />
               </colgroup>
               <thead>
                  <tr class="wish_table">
                     <th>SEARCH</th>
                     <th><select id="select" name="searchNum" >
                         <option value="0">상품이름</option>
                        </select>
                     </th>
                        
                        <th><input type="text" name="isSearch" id="search_text" placeholder="검색할 키워드를 입력해주세요">
                        <input type="submit" value="검색"></th>
                  </tr>
               </thead>
            </table>
            </form>
<div class="paging" style="text-align:center;">
${pagingHtml}
</div> --%>
<input type="button" value="전체선택" onclick="checkboxSelectQue(1,'chk[]')" />
<input type="button" value="전체해제" onclick="checkboxSelectQue(2,'chk[]')" />
<input type="button" id="submitFrm" value="삭제하기"> 
<div class="wish_bottom">
<div class="wish_bottom_font">
<font color="green">장바구니 내역에 있는 상품 정보를 보고 싶으시면 상품이름이나 이미지를 눌러주세요</font></div>
</div>
</div>

</body>
</html>
