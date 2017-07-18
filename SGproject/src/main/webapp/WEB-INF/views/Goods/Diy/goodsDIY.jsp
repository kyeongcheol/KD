<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<style type="text/css">
	.imgover img:hover{
	
	color:#F6FFCC;
	border:3px solid #86E57F;
	background-color: #86E57F;
	
	}
	
	

</style>


<script type="text/javascript">

function addTopping(test){
	var topping =  test.getAttribute("id");
	var theUrl = "ToppingAdd";
	
    	$.ajax({url: theUrl,  
   		type : "POST", 
   		data : { 
   			topping:topping
   		},
   		success: function(result){ 
   			$("#goodsBasket").html(result); 
   			calcurate();
   		},
   		error: function(result){ 
   			alert("실패"); 
   		}   
    	
    });
    	
	
}

function delTopping(test){
	var no =  test.getAttribute("id");
	var theUrl = "ToppingDelete";
	
    	$.ajax({url: theUrl,  
   		type : "POST", 
   		data : { 
   			no:no
   		},
   		success: function(result){ 
   			$("#goodsBasket").html(result);
   			calcurate();

   		},
   		error: function(result){ 
   			alert("실패"); 
   		}   
    	
    });
}

</script>
</head>
<body style="height: auto;">
	<div class="container" style="height: auto;" id="diy">
	<table border="0" width="100% " >
	<tr >
		<td colspan="2"><center>만들어 먹어요</center></td>
	</tr>
		<tr>
			<td>
			
				<div class="goods" style="height: auto; clear:both; ">
				<div class="goodsimg" >
				<img src="resources/file/img/SG_diy.jpg" class="img-circle" style="margin-top: 20px; margin-left: 50px" >
				</div>
				</div>
			</td>
			<td rowspan="2">
			
			<div id="goodsBasket" class="goodsBasket" style="width: 100%; height: auto; ">
				<jsp:include page="../Diy/goodsTopping.jsp"></jsp:include>
			
			</div>
			</td>
		</tr>
		<tr>
			<td>
			
				<div class="diy" style="clear:both">
					<div class="container" style="  width: 100%; height: 300px; padding-top: 20px;margin-top: 20px; "> 
					
						<c:choose>
							<c:when test="${fn:length(topping) > 0}">
								<c:forEach var="topping" items="${topping}" varStatus="stat">
							                          
									    <div class="thumbnail imgover" style="width: 100px; height: 115px; float: left; margin-right: 20px;">
									      <img src="resources/file/goodsFile/${topping.TOPPING_IMG}" id="${topping.TOPPING_NO }" name="${topping.TOPPING_NAME }" alt="..." onclick="addTopping(this)" class="img-circle" style="width: 70px; height: 70px; "/>
									      <div class="caption">
									      
									        <div style="text-align: center; margin-bottom: 5px;"><h4>${topping.TOPPING_NAME}</h4></div>
									        <div style="text-align: center;"><h5>${topping.TOPPING_PRICE }원</h5></div>
									 		<div style="text-align: center;"><h6>${topping.TOPPING_KCAL }kcal</h6></div>
									     
									         
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
					
					</div>
			
				</div>
			</td>
		</tr>
	</table>
		
	
	
	
	
	</div>
</body>
</html>