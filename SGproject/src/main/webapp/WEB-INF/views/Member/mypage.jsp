<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/SG/resources/file/css/main.min.css">

<link rel="stylesheet" href="/SG/resources/file/css/bootstrap.css"><!-- 위의 정보 확인 바 관련 css -->
<link rel="stylesheet" href="/SG/resources/file/css/mypage.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>      

<script type="text/javascript">
function tab(num)
{
   if(num == 1)
   {
         $.ajax
         ({
            url: "/SG/memberInfo",
            type : "get",
            success:function(data)
            {
               $("#account-contentsWrap").html(data);
               
            }
         });
   }
  
   return false;
};

</script>
<title>mypage게시판</title>
</head>
<body>
<div class="mypage_member_wrap">
         <div class="board_title">
            <div class="title_font1"><a href="#" style="text-decoration: none;color:black;">My Page</a></div>
         </div>
         <div class="line">
            <hr color="#777" width="100%" size="1">
         </div>
      <div class="personal-account-info container">
   <div class="my-account row">
      <section class="col-xs-24 my-info">
         <div class="section-body">
            <div class="item cash col-xs-8 col-md-6">
               <strong>
                  <span> <!-- class="level" style="text-align:center;" -->${sessionScope.MEMBER_ID}</span>(${sessionScope.MEMBER_NAME }님)
                  </strong>
                  <a href="#memberInfo" onclick="javascript:tab(1)" class="button small">
                     <span class="button-label">내 정보 보기</span>
                  </a>
         
            </div>
            
            <div class="item point col-xs-8 col-md-6">
               <strong>포인트 : </strong>
               <em>${sumPoint}원</em><br>
               <button class="button small" target="modal" data-size="md" data-label="나의 적립금" href="/SG/member/myPoint">
                  <span class="button-label">자세히 보기</span>
               </button>
               <div></div>
            </div>
            <div class="item cash col-xs-8 col-md-6">
            <div class="info">
            <c:choose>
            <c:when test="${sumTradeMoney eq null }">
            <span>총  구입금액 : </span>
             <span>0원</span>
             </c:when>
             <c:otherwise>
             <span>총  구입금액 : </span>
             <span>${sumTradeMoney}원</span>
             </c:otherwise>
            </c:choose>
            
            </div>
            </div>
            <div class="item coupon col-xs-8 col-md-6">
            <div>
               <span>나의 등급: </span>
            <span>일반회원</span><br> 
            <span>일반회원 이미지 </span>
            </div>
            
               
            </div>
         </div>
      </section>
      
         <div class="category">
         <ul>
            <li><a href="#">주문내역</a></li>
            <li><a href="#">위시리스트</a></li>
            <li><a href="#">장바구니</a></li>
            <li><a href="#">Q&A</a></li>
         </ul>
      </div>
      
      <div id="account-contentsWrap">
      
      </div>
      </div> 
      </div>
      
</body>
</html>