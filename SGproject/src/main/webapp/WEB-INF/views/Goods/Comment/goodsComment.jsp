<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <c:choose>
      	  <c:when test="${fn:length(commentList) > 0}">	
      	  <table class="type07" width="100%">  
      	  <thead> 
    <tr>
        <th scope="cols">작성자</th>
        <th scope="cols">내용</th>
        <th scope="cols">평점</th>
        <th scope="cols">작성일</th>
    </tr>
      	</thead>
	<c:forEach var="commentList" items="${commentList}" varStatus="stat">

    <tr>
        <th scope="row">${commentList.COMMENT_ID}</th>
        <td>${commentList.COMMENT_CONTENT}</td>
        <td>${commentList.COMMENT_RATE}</td>
        <td>${commentList.COMMENT_REGDATE}</td>
    </tr>

   	</c:forEach>
   	</table>
		 </c:when>
                
                <c:otherwise>
                    <tr>
                        <td colspan="4">아직 작성된 후기가 없어요</td>
                    </tr>
                </c:otherwise>
   </c:choose>
		
</body>
</html>