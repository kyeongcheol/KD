<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
    
<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

<script type="text/javascript">
	

</script>
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
        <td>
        <c:choose>
        	<c:when test="${commentList.COMMENT_RATE == 1}">맛없어요</c:when>
        	<c:when test="${commentList.COMMENT_RATE == 2}">별루에요</c:when>
        	<c:when test="${commentList.COMMENT_RATE == 3}">좋아요</c:when>
        	<c:when test="${commentList.COMMENT_RATE == 4}">맛있어요</c:when>
        	<c:otherwise>최고에요</c:otherwise>
        
        </c:choose>
        
        
        
        
        
        </td>
        <td>${commentList.COMMENT_REGDATE}
        &nbsp;
        &nbsp;
        <span class="glyphicon glyphicon-remove" aria-hidden="true" id="${commentList.COMMENT_NO}" name="${commentList.COMMENT_ID}" onclick="dComment(this)"></span>
        </td>
        
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