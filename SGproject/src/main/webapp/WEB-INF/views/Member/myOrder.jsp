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
 
 //주문 삭제 ajax
 function myOrderDel(deli_no, order_state, order_no, order_amount, goods_no)
 {

      if(!confirm("삭제하시겠습니까?"))
      {
        
         return;
      }
  
      else
      {
        var total =  
        ({
        	
        	"DELI_NO":deli_no, "ORDER_STATE":order_state, "ORDER_NO":order_no, "ORDER_GOODS_AMOUNT":order_amount,
        	"GOODS_NO":goods_no
        	
        });
        alert(order_amount);
        alert(goods_no);
        
    $.ajax
    ({
       type : "post",
       url : "/SG/orderInfo/order_del",
       data : total,
      
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
 }
 
 //같은 값 병합(배송번호)
 $( document ).ready(function() 
		 
         {
            $('#tb01').rowspan(0);           
         });
 
         $.fn.rowspan = function(colIdx, isStats) 
         {       
            return this.each(function()
             {      
               var that;     
               $('tr', this).each(function(row) 
               {      
                  $('td:eq('+colIdx+')', this).filter(':visible').each(function(col) 
                  {
                     
                     if ($(this).html() == $(that).html()
                        && (!isStats 
                              || isStats && $(this).prev().html() == $(that).prev().html()
                              )
                        ) {            
                        rowspan = $(that).attr("rowspan") || 1;
                        rowspan = Number(rowspan)+1;

                        $(that).attr("rowspan",rowspan);
                        
                        // do your action for the colspan cell here            
                        $(this).hide();
                        
                        //$(this).remove(); 
                        // do your action for the old cell here
                        
                     } else {            
                        that = this;         
                     }          
                     
                     // set the that if not already set
                     that = (that == null) ? this : that;      
                  });     
               });    
            });  
         };  
            
//order ajax paging
function ajaxPaging(page)
{	
        		
   var dataList =
   ({"PAGE" : page});	

   var url1 = "/SG/orderInfo";
        		
   $.ajax
   ({    
        	     
     type : "POST",
     url : url1,
     data : dataList,
     dataType : "text",      
        	        
     error : function() 
     {
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
<form name="frm">
<div id="wish_wrap">
<div class="wish_title">
<div class="wish_title_font">
주문내역</div>

</div>


<table id="tb01" class="wish_table" width="94%" >
<colgroup>
   <col width="10%" />
   <col width="10%" />
   <col width="10%" />
   <col width="15%" />
   <col width="10%" />
   <col width="10%" />
   <col width="10%" />
   <col width="10%" />
   </colgroup>

<tr>
    <th>NO</th>
    <th>상품이미지</th>
    <th>상품이름</th>
    <th>DIY상품</th>
    <th>주문금액</th>
    <th>주문상태</th>
    <th>주문취소</th>
    <th>주문날짜</th>
    

</tr>
          
          <c:choose>
              <c:when test="${fn:length(myOrderList) le 0}">
                <tr><td colspan="8" style="text-align:center;">주문 내역이 없습니다.</td></tr>
                 </c:when>
                 <c:otherwise>

                 <c:forEach var="list" items="${myOrderList}" varStatus="stat">
                 <c:url var="orderView" value="/orderInfoView">
                 <c:param name="DELI_NO" value="${list.DELI_NO}"/>
                 </c:url>   
                    <tr>
                        <td><a onclick="javascript:window.open('${orderView}','','toolbar=no,menubar=no,location=no,height=950,width=1200');">
                        ${list.DELI_NO}</a></td>
                        <td>
                        <img src="resources/file/img/${list.GOODS_THUMBNAIL}" width="120" height="90"
                        onclick="javascript:location.href=
                        'goodsDetail?goodsNo=${list.ORDER_GOODS_NO}&currentPage=${gcurrentPage}'"/>
                        </td>
                        <td><a href="goodsDetail?goodsNo=${list.ORDER_GOODS_NO}&currentPage=${gcurrentPage}">
                        ${list.GOODS_NAME}</a></td>
                        <td>${list.ORDER_TOPPING_NAME}</td>
                        <td>${list.ORDER_MONEY}</td>
                          

<%--주문 상태에 따른 메시지 표시 --%>               
<c:choose>
        
<c:when test="${list.ORDER_STATE == 0}">
<td>입금 대기</td>
<td class="wait"><div class="board_search_table">
<input type="button" value="주문취소"></div>
<input type="hidden" id="DELI_NO" name="DELI_NO" value="${list.DELI_NO}">  
<input type="hidden" id="ORDER_STATE" name="ORDER_STATE" value="${list.ORDER_STATE}" >
<input type="hidden" id="ORDER_NO" name="ORDER_NO" value="${list.ORDER_NO}">
<input type="hidden" id="ORDER_GOODS_AMOUNT" name="ORDER_GOODS_AMOUNT" value="${list.ORDER_GOODS_AMOUNT}">
<input type="hidden" id="GOODS_NO" name="GOODS_NO" value="${list.GOODS_NO}">
</td>
</c:when>

<c:when test="${list.ORDER_STATE == 1}">
<td>배송준비중</td>
<td class="ready"><div class="board_search_table"><input type="button" value="주문취소"></div>
<input type="hidden" id="DELI_NO" name="DELI_NO" value="${list.DELI_NO}">  
<input type="hidden" id="ORDER_STATE" name="ORDER_STATE" value="${list.ORDER_STATE}" >
<input type="hidden" id="ORDER_NO" name="ORDER_NO" value="${list.ORDER_NO}">
<input type="hidden" id="ORDER_GOODS_AMOUNT" name="ORDER_GOODS_AMOUNT" value="${list.ORDER_GOODS_AMOUNT}">
<input type="hidden" id="GOODS_NO" name="GOODS_NO" value="${list.GOODS_NO}">
</td>
</c:when>

<c:when test="${list.ORDER_STATE== 2}">
<td>배송중</td>
<td><a href='goodsDetail?goodsNo=${list.ORDER_GOODS_NO}&currentPage=${gcurrentPage}'>후기작성</a></td>
&nbsp;
</c:when>

<c:when test="${list.ORDER_STATE== 3}">
<td>배송완료</td>
<td><a href='goodsDetail?goodsNo=${list.ORDER_GOODS_NO}&currentPage=${gcurrentPage}'>후기작성</a></td>
&nbsp;
</c:when>

<c:otherwise>
없음
</c:otherwise>
</c:choose>
                       

<td>${list.ORDER_DATE}</td>                    
</tr>
 </c:forEach> 
                 
 </c:otherwise> 
 </c:choose> 
                
           
</table>
<div class="paging">${pagingHtml}</div>
</div>

</form>

</body>
<script>
$(".wait").on("click", function(e) //주문취소(입금 전)
{ 
   e.preventDefault();
   
   var deli_no =$(this).parent().find("#DELI_NO").val();
   var order_state =$(this).parent().find("#ORDER_STATE").val();
   var order_no =$(this).parent().find("#ORDER_NO").val();
   var order_amount = $(this).parent().find("#ORDER_GOODS_AMOUNT").val();
   var goods_no = $(this).parent().find("#GOODS_NO").val();
   myOrderDel(deli_no, order_state, order_no, order_amount, goods_no);
   
});

$(".ready").on("click", function(e) //주문취소(배송준비중)
{ 
   e.preventDefault(); 	 		
   var deli_no =$(this).parent().find("#DELI_NO").val();
   var order_state =$(this).parent().find("#ORDER_STATE").val();
   var order_no =$(this).parent().find("#ORDER_NO").val();
   var order_amount = $(this).parent().find("#ORDER_GOODS_AMOUNT").val();
   var goods_no = $(this).parent().find("#GOODS_NO").val();
   myOrderDel(deli_no, order_state, order_no, order_amount, goods_no);
		   
});	 
</script>
</html>