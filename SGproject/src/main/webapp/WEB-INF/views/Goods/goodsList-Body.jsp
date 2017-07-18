<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

                  
<input type="hidden" id="currentPage" value="${currentPage }"/>
                
<c:choose>
	<c:when test="${fn:length(goodsList) > 0}">
		<c:forEach var="goodsList" items="${goodsList}" varStatus="stat">
	                          
			  <div style=" width:240px; height:360px; float:left; margin-left: 20px;margin-right: 20px; margin-bottom: 150px;"> 
			    <div class="thumbnail" style="height:400px;">
			      <img src="resources/file/goodsFile/${goodsList.GOODS_THUMBNAIL}" alt="..." onclick="javascript:location.href='goodsDetail?goodsNo=${goodsList.GOODS_NO}&currentPage=${currentPage}'"/>
			      <div class="caption">
			      
			        <div style="text-align: center;"><h3>${goodsList.GOODS_NAME}</h3></div>
			        <div style="text-align: right;"><h4>${goodsList.GOODS_PRICE }원</h4></div>
			     
			          <div class="basketWish" style="float:left; margin-top: 10px;">
			          
			         	<div class="basket" style="margin:auto; padding-left:10px;float:left;">
			        		 <img id="${goodsList.GOODS_NO}" src="resources/file/img/basket.ico" width="20" height="20" onclick="SideBasket(this)"/>
			        	</div>
			         
			              <c:if test="${sessionScope.MEMBER_NO !=0 }">
			        		<c:choose>
			        
			        		 	<c:when test="${goodsList.WISH_NO==0}">
			         	
			         				<div class="wish" style="margin:auto; padding-left:10px;float:left">
			       						<img id="w_${goodsList.GOODS_NO}" src="resources/file/img/wishoff.png" width="20" height="20" onclick="imgToggle(this)"/>
			        				</div>
			         			</c:when>
			         	
			         			<c:otherwise>
			         				<div class="wish" style="margin:auto; padding-left:10px;float:left">
			       						<img id="w_${goodsList.GOODS_NO}" src="resources/file/img/wish.ico" width="20" height="20" onclick="imgToggle(this)"/>
			        				</div>
			         	
			         			</c:otherwise>
			         		</c:choose>
			         </c:if>
			       </div>
			      </div>
			    </div>
			  </div>
			
	   
	         
		</c:forEach>
	</c:when>
	                
	<c:otherwise>
	    <tr>
	       <td colspan="4">조회된 결과가 없습니다.</td>
	    </tr>
	</c:otherwise>
</c:choose>





   

   
