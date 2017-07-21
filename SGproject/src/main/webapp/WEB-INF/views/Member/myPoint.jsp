<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="resources/file/js/jquery-2.0.0.min.js"></script>
<style type="text/css">
@import url('//cdn.rawgit.com/young-ha/webfont-archive/master/css/PureunJeonnam.css');
#faq_admin_wrap {
	width: 1500px;
	font-family: PureunJeonnam;
}
.faq_admin_title {text-align: left; color: #212121; padding-top: 50px;}
.faq_admin_title div {padding-bottom: 10px;}
.title_font1 {font-family: PureunJeonnam; font-size: 40px;}
.title_font2 {font-family: PureunJeonnam; font-size: 20px; color: #999;}
.faq_admin_table{
	text-align: center;
	font-family: PureunJeonnam;
	font-size:15px;
}
.faq_line {margin: 0 50px 0 50px;}

.faq_admin_table th {
	text-align: center; 
	border-top: 1px solid #e5e5e5; 
	border-bottom: 1px solid #e5e5e5;
	padding: 8px 0; 
	font-size:15px; 
	background: #f5f5f5;
	font-family: PureunJeonnam;
}

.faq_admin_table td {
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
   
.paging{text-align:center;height:32px;margin-top:5px;margin-bottom:15px;}
.paging a,
.paging strong{display:inline-block;width:36px;height:32px;line-height:28px;font-size:14px;margin-left:5px;/* border:1px solid #e0e0e0; */
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

.totalpoint {

 padding-right:50px;
 text-align:center;
 margin-left:850px;
 margin-top:15px;
}
.totalpoint label {
    float:left;
   	text-align: center; 
   	padding-left:
	border-top: 1px solid #e5e5e5; 
	border-bottom: 1px solid #e5e5e5;
	 
	font-family: PureunJeonnam;
}
 .totalpoint input {
       padding-right:50px;
       float:left;
       margin-left:15px;
       
}

</style>


</head>

<body>
<div id="faq_admin_wrap">
<div class="faq_admin_title">
<div class="title_font1"><a href="#" style="text-align:center;text-decoration: none;color:black;">나의 포인트 내역</a></div>

</div>


<table class="faq_admin_table" width="80%">
<colgroup>
	<col width="10%" />
	<col width="20%"/>
	<col width="30%" />
	<col width="40%" />
	
</colgroup>
<tr>
	<th>NO</th>
    <th>날짜</th>
    <th>포인트</th>
    <th>포인트 내용</th>
    
</tr>



          <c:choose>
               <c:when test="${fn:length(pointList) le 0}">
 					<tr><td colspan="7" style="text-align:center;">포인트 내역이 없습니다.</td></tr>
                 </c:when>
                 
                 <c:otherwise>

   				 <c:forEach var="pointList"  items="${pointList}" varStatus="stat">
                 	<tr> 
                     	<td>${pointList.RNUM}</td>
                     	<td>${pointList.POINT_DATE}</td> 
                     	<td>${pointList.POINT_MONEY}</td>
                     	<td>${pointList.POINT_CONTENT}</td>
                      
                    </tr>
                 </c:forEach> 
                 </c:otherwise> 
                </c:choose>  
           
</table>
      
		<div class="totalpoint">
         <label for="sumPoint">
            <strong>총 포인트</strong>
         </label>
         <%-- <input type="text" name="sumPoint" id="sumPoint" label="총포인트" value="${sumPoint}" readOnly class="xx-control"> --%>
         <div style="margin-left:150px; float:left;">${sumPoint}원</div>
         </div>
      </div>
<div class="paging" style="text-align:center; float:left; padding-top:40px; padding-left:550px; position:absolute;">
${pagingHtml}
</div>     
</body>

</html>
