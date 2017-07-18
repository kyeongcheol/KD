<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	function calcurate(){
		var price = document.getElementById("form_price").value;
		var kcal = document.getElementById("form_kcal").value;
		price *=1;
		kcal *=1;
		
		<c:forEach items="${toppingList}" var="toppingList">
		
		price = price + ${toppingList.TOPPING_PRICE};
		kcal = kcal + ${toppingList.TOPPING_KCAL};
		</c:forEach>
		
		document.getElementById("form_price").setAttribute("value",price);
		document.getElementById("form_kcal").setAttribute("value",kcal);

		document.getElementById("price").innerHTML =price;
		document.getElementById("kcal").innerHTML=kcal;
		document.getElementById("point").innerHTML = price/100;
		
	}
	

</script>
</head>
<body style="height: auto;" onload="calcurate();"  >

<div class="bkform" style="height: auto;">
	<center>

<div  style=" padding-top: 20px;margin-top: 20px; height: 400px; overflow-y:scroll;"> 
		
				<c:choose>
					<c:when test="${fn:length(toppingList) > 0}">
						<c:forEach var="toppingList" items="${toppingList}" varStatus="stat">
					                          
							    
							<table class="table table-hover">
							<tr>
								<td width="80px">							    
									<div style="margin-top: 20px;">
									<img  class="img-circle" style=" width: 70px;height: 70px;" alt="" src="resources/file/goodsFile/${toppingList.TOPPING_IMG}"/>
									</div>
								</td> 
								<td><h3>&nbsp;&nbsp;${toppingList.TOPPING_NAME}&nbsp;&nbsp;<span class="glyphicon glyphicon-remove" aria-hidden="true" id="${stat.index}"  onclick="javascript:location.href='/BasketDiy';" style="margin-top: 20px;"></span></h3>
								
										
										<br/>
										&nbsp;${toppingList.TOPPING_PRICE}원  &nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;${toppingList.TOPPING_KCAL}kcal</td>
										        
										
								</tr>
							
							</table>
							
					   
					         
						</c:forEach>
				
					</c:when>
				                
				<c:otherwise>
				   재료를 선택해 주세요
				</c:otherwise>
			</c:choose>
					
			</div>
			<div style="background-color: #F9F9F9;border-radius: 10px;  height: auto;">
				<div  style="height: 240px; border-radius:10px; ">
					<br/>
				<center>
					<table border="0" width="70%" style="margin-bottom: 20px;margin-top: 20px; padding-left: 10px;padding-right: 10px;" class="table">
						<tr>
							<td width="30%"><h4><strong>총 결제 금액</strong></h4></td><td><center><span id="price">0</span>원</center></td>
						</tr>
						<tr>
							<td><strong>적립 예정 포인트</strong></td><td><center><span id="point">0</span>Point</center></td>
						</tr>
						<tr>
							<td><strong>총 Kcal</strong></td><td><center><span id="kcal">0</span>Kcal</center></td>
						</tr>
					</table>
					</center>
					
					<button type="button" class="btn btn-default btn-lg" style="margin-left: 10px;margin-right: 10px;" onclick="document.getElementById('basketForm').submit();">
					  <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>BASKET
					</button>
					
					<button type="button" class="btn btn-default btn-lg" style="margin-left: 10px;margin-right: 10px;" onclick="">
					  <span class="	glyphicon glyphicon-credit-card" aria-hidden="true"></span>PAYMENT
					</button>
			
					<form id="basketForm" action="BasketDiy" >
						<input type="hidden" id="form_price" name="form_price" value="0"/>
						<input type="hidden" id="form_kcal" name="form_kcal" value="0"/>
					</form>
				
				</div>
			</div>
		</center>


</div>
</body>
</html>