<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
@import url('//cdn.rawgit.com/young-ha/webfont-archive/master/css/PureunJeonnam.css');

#faq_admin_wrap {
	width: 1500px;
	font-family: PureunJeonnam;
}
.faq_admin_title {text-align: center; color: #212121; padding-top: 50px;}
.faq_admin_title div {padding-bottom: 10px;}
.title_font1 {font-family: Orbitron; font-size: 24px; font-weight: bold;}
.title_font2 {font-family: PureunJeonnam; font-size: 12px; color: #999;}
.faq_admin_table{
	text-align: center;
	font-family: PureunJeonnam;	
}
.faq_line {margin: 0 50px 0 50px;}

.faq_admin_table th {
	text-align: center; 
	border-top: 1px solid #e5e5e5; 
	border-bottom: 1px solid #e5e5e5; 
	background: #f5f5f5;
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
 .category{}
   .category ul{margin-left:35px; list-style-type: none;}
   .category ul li
   {
   float:left;
   padding-right:15px;
   padding-left:15px;
   margin-left:2px;
   background-color:black;
   color:white;
   font-size:12px;
   font-family:PureunJeonnam;
   line-height:20px;
   }
   .category ul li a{
   text-decoration:none;
   color:white;
   }
</style>
</head>

<body>
<div id="faq_admin_wrap">
<div class="faq_admin_title">
<div class="title_font1"><a href="#" style="text-decoration: none;color:black;">FAQ</a></div>
<div class="title_font2">관리자페이지 입니다.</div>
</div>

<div class="faq_line">
<hr color="#777" width="100%" size="1">
</div>
         <div class="category">
         <ul>
         	<li><a href="#">회원안내</a></li>
         	<li><a href="#">배송안내</a></li>
         	<li><a href="#">주문/결제</a></li>
         	<li><a href="#">주문취소/변경</a></li>
         	<li><a href="#">교환/반품</a></li>
         	<li><a href="#">환불</a></li>
         	<li><a href="#">상품</a></li>
         	<li><a href="#">사이트이용안내</a></li>
         </ul>
</div>

<table class="faq_admin_table" width="100%">
<colgroup>
	<col width="10%" />
	<col width="20%"/>
	<col width="20%" />
	<col width="10%" />
	<col width="10%" />
	<col width="10%" />
    <col width="10%"/>
</colgroup>
<tr>
	<th>NO</th>
    <th>카테고리</th>
    <th>글제목</th>
    <th>작성자</th>
    <th>등록일자</th>
    <th>조회수</th>
    <th>관리</th>
</tr>

          <c:choose>
               <c:when test="${list} == null">
 					<tr>
                 		<td colspan="7">게시글이 없습니다.</td>
                 	</tr>
                 </c:when>
                 <c:otherwise>

   				 <c:forEach var="list"  items="${list}" varStatus="stat">
                 	<tr> 
                     	<td>${list.FAQ_NO}</td>
                     	<td>${list.FAQ_CATEGORY}</td>
                        <td><a href="#">${list.FAQ_TITLE}</a></td>
                        <td>SG운영자</td>
                        <td>${list.FAQ_REGDATE}</td>
                        <td>${list.FAQ_HITCOUNT}</td>
                        <td>관리버튼</td>
                    </tr>
                 </c:forEach> 
                 </c:otherwise> 
                </c:choose>  
                
</table>
<div class="faq_admin_search">
<form>
<table>
<colgroup>
    <col width="30%"/>
    <col width="60%"/>
</colgroup>
<tr>
    <th>
    <select class="faq_select" id="select" name="SearchNum">
    	<option selected="selected">제목</option>
        <option>내용</option>
        <option>카테고리</option>
     </select>
     </th>
     <th>
     <input type="text" name="isSearch" id="isSearch" placeholder="검색할 키워드를 입력해주세요">
     <input type="button" value="검색" onclick="#"></th>   
</table>
</form>
</div>
</div>
</body>
</html>
