<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<head>
<script type="text/javascript">
function delchk(){
    return confirm("삭제하시겠습니까?");
    
    
}
</script>
<style type="text/css">
.paging{text-align:center;height:32px;margin-top:5px;margin-bottom:15px;}
.paging a,
.paging strong{display:inline-block;width:36px;height:32px;line-height:28px;font-size:14px;border:1px solid #e0e0e0;margin-left:5px;
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
</style>
</head>

<div class="row" style="padding-left:15px;width:900px;">    
	<h1 class="page-header">상품목록</h1>
</div>
<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">
                         [상품목록페이지] 상품을 검색, 수정, 삭제 기능하는 페이지입니다.
        </div>
        <div class="panel-body">
			<div class="dataTable_wrapper">
				<div id="dataTables-example_wrapper"
					class="dataTables_wrapper form-inline dt-bootstrap no-footer">
					<div class="row" style="margin-bottom:5px;">
						<div class="col-sm-6">
							<a href="/SG/admintoppingList"><button type="button" class="btn btn-outline btn-default">전체상품</button></a>
							<select class="form-control" name="select" onchange="window.open(value,'_self');">
								<option value ="">--카테고리--</option>
								<option value ="/SG/admintoppingList?searchNum=2&isSearch=완제품">육류</option>
								<option value ="/SG/admintoppingList?searchNum=2&isSearch=음료">채소</option>
								<option value ="/SG/admintoppingList?searchNum=2&isSearch=드레싱">드레싱</option>
							</select>
							<select class="form-control" name="select" onchange="window.open(value,'_self');">
								<option value ="">--상품정렬--</option>
								<option value ="/SG/admintoppingList?searchNum=3&isSearch=0">판매ON</option>
								<option value ="/SG/admintoppingList?searchNum=3&isSearch=1">판매OFF</option>
							</select>			
							<select class="form-control" name="select" onchange="window.open(value,'_self');">
								<option value ="">--상품정렬--</option>
								<option value ="/SG/admintoppingList?searchNum=4&isSearch=TOPPING_SELLCOUNT">판매수량순</option>
								<option value ="/SG/admintoppingList?searchNum=5&isSearch=TOPPING_AMOUNT">재고0</option>
							</select>											
						</div>
						<div class="col-sm-6" style="text-align:right;">
							<div class="dataTables_info" id="dataTables-example_info" role="status" aria-live="polite">총 상품수 : ${totalCount}</div>
						</div>
						
					</div>
					<div class="row">
						<div class="col-sm-12">
							<table
								class="table  table-bordered table-hover dataTable no-footer"
								id="dataTables-example" role="grid"
								aria-describedby="dataTables-example_info">
								<thead>
									<tr role="row" style="vertical-align:middle;">
										<th style="width: 5%; text-align:center;vertical-align:middle;">상품번호</th>
										<th style="width: 8%; text-align:center;vertical-align:middle;">상품 사진</th>										
										<th style="width: 7%; text-align:center;vertical-align:middle;">카테고리</th>
										<th style="width: 20%; text-align:center;vertical-align:middle;">상품명</th>
										<th style="width: 8%; text-align:center;vertical-align:middle;">상품노출여부</th>
										<th style="width: 7%; text-align:center;vertical-align:middle;">칼로리</th>
										<th style="width: 6%; text-align:center;vertical-align:middle;">상품가격</th>
										<th style="width: 7%; text-align:center;vertical-align:middle;">수량</th>
										<th style="width: 7%; text-align:center;vertical-align:middle;">판매량</th>
										<th style="width: 12%; text-align:center;vertical-align:middle;">등록일자</th>									
										<th style="width: 13%; text-align:center;vertical-align:middle;">관리</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="toppingList"  items="${toppingList}" varStatus="stat">
								<c:url var="viewURL" value="/TOPPING/TOPPINGModifyForm" >
									<c:param name="TOPPING_NO" value="${toppingList.TOPPING_NO}" />
								</c:url>									
									<tr class="gradeA even" role="row">
										<td style="text-align:center;vertical-align:middle;">${toppingList.TOPPING_NO}</td>										
										<td style="text-align:center;vertical-align:middle;"><img src="resources/file/goodsFile/${toppingList.TOPPING_THUMBNAIL}" width="60" height="60" /></td>
										<td style="text-align:center;vertical-align:middle;">${toppingList.TOPPING_TYPE}</td>
										<td style="text-align:center;vertical-align:middle;">${toppingList.TOPPING_NAME}</td>
										<c:choose>
											<c:when test="${toppingList.TOPPING_ONOFF == 0}">
											<td style="text-align:center;vertical-align:middle;">ON</td>
											</c:when>
											<c:otherwise>
											<td style="text-align:center;vertical-align:middle;">OFF</td>
											</c:otherwise>
										</c:choose>
										<td style="text-align:center;vertical-align:middle;">${toppingList.TOPPING_KCAL}kcal</td>
										<td style="text-align:center;vertical-align:middle;"><fmt:formatNumber value="${toppingList.TOPPING_PRICE}" type="number"/>원<div style='display:none;'>${toppingList.TOPPING_NO}</div></td>							
										<td style="text-align:center;vertical-align:middle;">${toppingList.TOPPING_AMOUNT}개</td>
										<td style="text-align:center;vertical-align:middle;">${toppingList.TOPPING_SELLCOUNT}개</td>
										<td style="text-align:center;vertical-align:middle;"><fmt:formatDate value="${toppingList.TOPPING_DATE}" pattern="YY.MM.dd HH:mm" /></td>										
										<td style="text-align:center;vertical-align:middle;">
											<a href="${viewURL}"><input type="image" src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Cog_font_awesome.svg/32px-Cog_font_awesome.svg.png"></a>&nbsp;&nbsp;
										<c:url var="viewURL2" value="/TOPPING/TOPPINGDelete" >
											<c:param name="TOPPING_NO" value="${toppingList.TOPPING_NO}" />							
										</c:url>	
										 <a href="${viewURL2}"><input type="image" src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Trash_font_awesome.svg/32px-Trash_font_awesome.svg.png" onclick="return delchk()"></a><div style='display:none;'>${toppingList.TOPPING_NO}</div></td>									
									</tr>
								</c:forEach>
								<!--  등록된 상품이 없을때 -->
									<c:if test="${fn:length(toppingList) le 0}">
										<tr><td colspan="11" style="text-align:center;">등록된 상품이 없습니다</td></tr>
									</c:if> 
								</tbody>
							</table>
						</div>
					</div>
					<div class="paging">
						${pagingHtml}
					</div>
					<div class="row">
							<div style="text-align:center;">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<form action="">
									<select class="form-control" name="searchNum" id="searchNum">
										<option value="0">상품명</option>
										<option value="1">상품번호</option>
									</select>
										<input class="form-control" type="text" name="isSearch" id="isSearch"/>
										<span>
										<button type="submit" class="btn btn-default">검색</button>
										</span>
									</form>
								</div>							
							</div>
							
					</div>
				</div>
			</div>
			<!-- /.table-responsive -->							
		</div>
	</div>
        <!-- /.panel -->   
</div>