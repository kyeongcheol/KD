<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<style>
@import url('//cdn.rawgit.com/young-ha/webfont-archive/master/css/PureunJeonnam.css');
   /* body */
   .faq_member_wrap {width: 1500px; margin-left:auto; margin-right:auto;}
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

   .search_faq
   .search_faq input[type='text']{width:300px;}   
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

<title>FAQ게시판</title>
</head>
<body>
<div class="faq_member_wrap">
         <div class="board_title">
            <div class="title_font1"><a href="#" style="text-decoration: none;color:black;">FAQ 게시판</a></div>
            <div class="title_font2">자주묻는질문</div>
         </div>
         <div class="line">
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
                     	<td>${list.FAQ_NO}</td>
                     	<td>${list.FAQ_CATEGORY}</td>
                        <td><a href="#">${list.FAQ_TITLE}</a></td>
                        <td>SG운영자</td>
                        <td>${list.FAQ_REGDATE}</td>
                        <td>${list.FAQ_HITCOUNT}</td>
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
</body>
</html>