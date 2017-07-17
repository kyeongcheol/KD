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
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script> -->
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
 
<script type="text/javascript">
function tab(num)
{
   
   if(num == 1){var url="/SG/orderInfo"}
   if(num == 2){var url="/SG/wishList"}
   if(num == 3){var url="/SG/mybasket"}
   if(num == 4){var url="/SG/qna"}
   
         $.ajax
         ({
            url: url,
            type : "get",
            success:function(data)
            {
               $("#account-contentsWrap").html(data);
               
            }
         });
   
  
   
};

$(document).ready(function () 
		{
		    //img를 꺼내와서 크기 지정
            $("#grade").attr('width', 50);
            $("#grade").attr('height', 50);
            
            var link = document.location.href;
        	var tab = link.split('mypage').pop(); //href 태그 중 mypage를 제거
        	
        	if(tab == "")
        	{
        		$('a[href=#myorder]').trigger("click");	
        	}
        	else 
        	{
        	    $('a[href$='+tab+']').trigger("click");
        	}
        });
                 
function member_info()
{
	//나의 정보보기 팝업 창
	window.open('/SG/memberInfo','','toolbar=no,menubar=no,location=no,height=950,width=1200'); 
}

function point_list()
{
	//나의 포인트 리스트 팝업 창
	window.open('/SG/myPoint','','toolbar=no,menubar=no,location=no,height=950,width=1000'); 
}

</script>
<style>
.mygrade{
   padding-top:5px;
   padding-bottom:5px;
   left:90px;
   position: absolute;
   top: 5px;
   bottom:9px;
   
  
}
</style>
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
            <div class="item cash col-xs-8 col-md-6" style="border-right:3px solid #cccccc;">
               <strong>
                  <span> <!-- class="level" style="text-align:center;" -->${sessionScope.MEMBER_ID}</span>(${sessionScope.MEMBER_NAME }님)
                  </strong>
                  <a href="#memberInfo" onclick="javascript:member_info();" class="button small">
                     <span class="button-label">내 정보 보기</span>
                  </a>
         
            </div>
            
            <div class="item cash col-xs-8 col-md-6" style="border-right:3px solid #cccccc;">
               <strong>포인트 : </strong>
               <em>${sumPoint}원</em><br>
             <!--   <button class="button small" onclick="javascript:tab(2)">
                  <span class="button-label">자세히 보기</span>
               </button> -->
                 <a href="#mypoint" onclick="javascript:point_list();" class="button small">
                     <span class="button-label">자세히보기</span>
                  </a>
               <div></div>
            </div>
            <div class="item cash col-xs-8 col-md-6" style="border-right:3px solid #cccccc;">
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
            <div class="item cash col-xs-8 col-md-6">
            <div class="mygrade">
               
            <c:choose>
            
            <c:when test="${(memberGrade)==0}">
            <span>나의 등급 : 그린</span> 
            <span style="margin-left:5px;">               
            <img id="grade" src="./resources/file/img/grade/green0.png"/>
            </span>
            </c:when>
            
            <c:when test="${(memberGrade)==1}">
            <span>나의 등급 : 핑크</span> 
            <span style="margin-left:15px;">
            <img id="grade" src="./resources/file/img/grade/pink1.png"/></span> 
            </c:when>
            
            <c:when test="${(memberGrade)==2}">
            <span>나의 등급 : 골드</span>
            <span style="margin-left:15px;">
            <img id="grade" src="./resources/file/img/grade/gold2.png"/>
            </span>
            </c:when>
            
            <c:when test="${(memberGrade)==3}">
            <span>나의 등급 : 다이아 </span>
            <img id="grade" src="./resources/file/img/grade/dia3.png"/>   
            </c:when>
            
            <c:when test="${(memberGrade)==4}">
            <span>나의 등급 : VIP</span>
            <span style="margin-left:15px;">   
            <img id="grade" src="./resources/file/img/grade/vip4.png"/>
            </span> 
            </c:when>
            
            <c:otherwise>
            admin
            </c:otherwise>
            </c:choose>
            
            
            </div>
            
               
            </div>
         </div>
      </section>
      
         <div class="category">
         <ul>
            <li><a href="#myorder" onclick="javascript:tab(1)">주문내역</a></li>
            <li><a href="#mywish" onclick="javascript:tab(2)">위시리스트</a></li>
            <li><a href="#mybasket" onclick="javascript:tab(3)">장바구니</a></li>
            <li><a href="#qna" onclick="javascript:tab(4)">Q&A</a></li>
         </ul>
      </div>
      
      <div id="account-contentsWrap">
      
      </div>
      </div> 
      </div>
      
</body>
</html>