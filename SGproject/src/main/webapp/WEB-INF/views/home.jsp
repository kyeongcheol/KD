<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<html>

<head>


<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
 --><link rel="stylesheet" href="resources/file/css/SiderBar.css" type="text/css" />
<!-- 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>  -->
<script src="resources/file/js/SiderBarJs.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 -->

<script type="text/javascript">
<!--

//-->


function SideBasket(test){ 

	
	var goodsNo =test.getAttribute("id");
var theUrl = "BasketAdd";


$.ajax({url: theUrl,  
	type : "POST", 
	data : { 
		goodsNo : goodsNo,
	},
	success: function(result){ 
		$("#sideBar").html(result); 

		alert("장바구니에 담았습니다");
	},
	error: function(result){ 
		alert("실패"); 
	}   



});

}

function delGoods(test){
	var goodsNo =test.getAttribute("id");
	var theUrl = "BasketDelete";
	
 	$.ajax({url: theUrl,  
   		type : "POST", 
   		data : { 
   			goodsNo : goodsNo,
   		},
   		success: function(result){ 
   			$("#sideBar").html(result); 

   			alert("장바구니에서 제거했습니다");
   		},
   		error: function(result){ 
   			alert("실패"); 
   		}   
    	
    	
    	
    });
}

</script>
</head>
<body>
    <div id="wrapper">
        <div class="overlay"></div>
    
        <!-- Sidebar -->
        <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
            <ul class="nav sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                       BASKET
                    </a>
                </li>
		                
			  <c:choose>
						<c:when test="${fn:length(basketList) > 0}">
							<c:forEach var="basketList" items="${basketList}" varStatus="stat">
				                <li>
				                    <img alt="" class="" src="resources/file/goodsFile/${basketList.GOODS_THUMBNAIL}" style="width: 70px; margin-bottom: 5px; float: left;">
				                    <div style="background-color: #212121;width250px; float:left; width: 250px;height: 70px; "> 
					                    <table style="width: 250px;">
						                    <tr>
						                    	<td width="200"><div style="font-size: 20px;color: #FFFFFF;">&nbsp;&nbsp;${basketList.GOODS_NAME }</div><br/><div style="color: #FFFFFF;">&nbsp;&nbsp;${basketList.GOODS_PRICE}원 &nbsp;&nbsp;${basketList.GOODS_KCAL }kcal</div></td><td width="50px;">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-remove" id="${stat.index}" aria-hidden="true" style="color: #FFFFFF;" onclick="delGoods(this);"></span></td>
						                    </tr>
					                    </table>
				                    </div>
				                </li>
		                         
							</c:forEach>
							          
					      <div style="z-index: 1000;margin-top: 40px;  margin-left: 110px;;margin-right: auto;">
						    <button type="button" class="btn btn-warning">주문하기</button>
						  </div>
						</c:when>
						                
						<c:otherwise>
						    
						       장바구니가 비었어요
						  
						</c:otherwise>
			</c:choose>
            </ul>
  
        </nav>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
                <span class="hamb-top"></span>
    			<span class="hamb-middle"></span>
				<span class="hamb-bottom"></span>
            </button>
   
        </div>
        <!-- /#page-content-wrapper -->
        
    

    </div>
    <!-- /#wrapper -->
   </body>
	</html>
    