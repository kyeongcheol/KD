<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="/SG/resources/file/css/main.min.css">

<link rel="stylesheet" href="/SG/resources/file/css/bootstrap.css"><!-- 위의 정보 확인 바 관련 css -->
	


<style>
@import url('//cdn.rawgit.com/young-ha/webfont-archive/master/css/PureunJeonnam.css');
   /* body */
   .mypage_member_wrap {width: 1500px; margin-left:auto; margin-right:auto;}
   .board_title {text-align: center; color: #212121; padding-top: 50px;}
   .board_title div {padding-bottom: 10px;}
   .title_font1 {font-family: Orbitron; font-size: 24px; font-weight: bold;}
   .title_font2 {font-family: PureunJeonnam; font-size: 12px; color: #999;}
   .line {margin: 0 50px 0 50px;}
   .board_table thead th {text-align: center; border-top: 1px solid #e5e5e5; border-bottom: 1px solid #e5e5e5; padding: 8px 0; background: #f5f5f5;}
   .board_table tbody td { text-align: center;  border-bottom: 1px solid #e5e5e5; padding: 5px 0;}
   .board_main {width: 1500px; padding-top: 30px; padding-bottom: 30px;}
   .board_table {font-family: PureunJeonnam; font-size: 12px; color: #212121;}
   .board_table tbody tr td a {color: #212121; text-decoration: none;}
   .board_table tbody tr td a:HOVER {color: #aaa;}
   .btn_board {color: #212121; font-size: 14px; border: 1px solid #bbb; width: 80px; text-align: center; border-radius: 5px; padding: 5px 3px; cursor: pointer; margin: 0 0 70px 850px;}
   .btn_board:HOVER {background-color: #eee; transition-duration: 0.5s;}
   .btn_board img {padding: 0px 5px 0 0;}
   
   .board_search_table{font-family: PureunJeonnam; font-size: 12px; color: #212121;}
   .board_search_table thead th{text-align: center; border-top: 1px solid #e5e5e5; border-bottom: 1px solid #e5e5e5; padding: 2px 0; background: #f5f5f5;}
   .board_search_table input[type=text]{width:200px;height: 20px;padding-left:13px; }
   .board_search_table input[type=button]{border:1px solid silver; background-color: white; padding:2px 6px; border-radius:5px;}
   .board_search_table input[type=button]:HOVER{background-color: #e5e5e5;  }
   
   .paging{text-align:center; padding-top: 20px;}
    .paging a{text-decoration: none; color:black;}

   .search_mypage
   .search_mypage input[type='text']{width:300px;}   
   .category{
      padding-bottom: 100px;
      padding-left:350px;
    }
   .category ul{margin-left:35px; list-style-type: none;}
   .category ul li
   {
   width:155px;
   height:35px;
   float:left;
   margin-top:20px;
   padding-top:5px;
   padding-right:25px;
   padding-left:25px;
   margin-left:2px;
   margin-botton:20px;
   background-color:black;
   color:white;
   font-size:14px;
   font-family:PureunJeonnam;
   line-height:20px;
   text-align:center;
   }
   .category ul li a{
   text-decoration:none;
   color:white;

   }
   
   .account-nav {display:block;}
.account-nav ul li {width:14.285%;}
@media (max-width: 767px){
	.account-nav ul li {width:25%; float:left; border:1px solid #000; margin-right:-1px; margin-bottom:-1px; border-radius:0 !important; padding:5px 0;}
	.account-nav ul li.m-1 {margin-left:-1px;}
	.account-nav ul li.xx {width:33.333%;}
}
   
</style>
<script type="text/javascript">
function tab(num){
	if(num == 1){
			$.ajax({
				url: "/SIRORAGI/orderlist",
				type : "get",
				success:function(data){
					$("#account-contentsWrap").html(data);
					
				}
			});
	}
	if(num == 2){
			$.ajax({
		url: "/SIRORAGI/exchangelist",
		type : "get",
		success:function(data){
			$("#account-contentsWrap").html(data);
		}
	});
	}
	if(num == 3){
			$.ajax({
		url: "/SIRORAGI/returnlist",
		type : "get",
		success:function(data){
			$("#account-contentsWrap").html(data);
		}
	});
	}
	if(num == 4){
			$.ajax({
		url: "/SIRORAGI/review",
		type : "get",
		success:function(data){
			$("#account-contentsWrap").html(data);
		}
	});
	}
	if(num == 5){
			$.ajax({
		url: "/SIRORAGI/qna",
		type : "get",
		success:function(data){
			$("#account-contentsWrap").html(data);
		}
	});
	}
	if(num == 6){
			$.ajax({
		url: "/SIRORAGI/oneToOne",
		type : "get",
		success:function(data){
			$("#account-contentsWrap").html(data);
		}
	});
	}
	if(num == 7){
			$.ajax({
		url: "/SG/myinfo",
		type : "get",
		success:function(data){
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
						<a href="memberInfo" onclick="javascript:tab(7)" class="button small">
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

         <div class="board_main" align="center">
            <table class="board_table" width="90%">
               <colgroup>
                  <col width="10%" />
                  	<col width="10%"/>
                    <col width="40%" />
                    <col width="20%" />
                    <col width="10%" />
                    <col width="10%" />
               </colgroup>
               <thead>
                  <tr class="table_title">
                     <th>NO</th>
                     <th>카테고리</th>
                     <th>제목</th>
                     <th>작성자</th>
                     <th>등록일</th>
                     <th>조회수</th>
                  </tr>
               </thead>
               <tbody>
               <c:choose>
               <c:when test="${list} == null">
 					<tr>
                 		<td colspan="6">게시글이 없습니다.</td>
                 	</tr>
                 </c:when>
                 <c:otherwise>
 
   				 <c:forEach var="list"  items="${list}" varStatus="stat">
                 	<tr> 
                     	<td>${list.mypage_NO}</td>
                     	<td>${list.mypage_CATEGORY}</td>
                        <td><a href="#">${list.mypage_TITLE}</a></td>
                        <td>SG운영자</td>
                        <td>${list.mypage_REGDATE}</td>
                        <td>${list.mypage_HITCOUNT}</td>
                    </tr>
                 </c:forEach> 
                 </c:otherwise>
                </c:choose> 	
               </tbody>
            </table>
            
            <form action="#" name="searchForm" method="post">
            <table class="board_search_table" width="90%" style="padding-top: 10px;">
               <colgroup>
                  <col width="20%" />
                    <col width="30%" />
                    <col width="50%" />
               </colgroup>
               <thead>
                  <tr class="table_title">
                     <th>SEARCH</th>
                     <th><select id="select" name="searchNum">
                              <option selected="selected">TITLE</option>
                              <option>CONTENT</option>
                              <option>CATEGORY</option>
                        </select></th>
                        
                        <th><input type="text" name="isSearch" id="search_text" placeholder="검색할 키워드를 입력해주세요">
                        <input type="button" value="검색" onclick="boardSearch()"></th>
                  </tr>
               </thead>
            </table>
            </form>
         </div>
        </div> 
        </div>
</body>
</html>