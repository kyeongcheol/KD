<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String cp = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="ko">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>샐러드구쁘다 관리자페이지</title>

    <!-- Bootstrap Core CSS -->
    <link href="resources/file/css/bootstrapadmin.min.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	@import url('//cdn.rawgit.com/young-ha/webfont-archive/master/css/PureunJeonnam.css');
	body{font-family: PureunJeonnam;}
		@media(min-width:768px) {
   		 #page-wrapper {
        margin: 0 0 0 250px !important;
       
  
    }
}
	</style>

	 <!-- Custom CSS -->
    <link href="resources/file/css/sb-admin-2.css" rel="stylesheet">
    <link href="resources/file/css/ui.css" rel="stylesheet">
	<!-- jQuery -->
	
    <script src="resources/file/js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="resources/file/js/bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="resources/file/js/sb-admin-2.js"></script>
     <script src="resources/file/js/common.js"></script>
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;background-color:#00CCCF">
            <div class="navbar-header" style="background-color:#00CCCF">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand"style="color:#fff" href="/SG/adminHome"><strong>샐러드구쁘다 관리자페이지</strong></a>
            </div>
            <!-- /.navbar-header -->



            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        
                        <li>
                            <a href="/SG/adminHome" style="background: #e7e7e7;border-bottom: 1px solid #F8F8F8;"><i class="fa fa-dashboard fa-fw"></i>관리자홈</a>
                        </li>
						<li>
                            <a href="/SG/main" style="background: #e7e7e7;border-bottom: 1px solid #F8F8F8;"><i class="fa fa-dashboard fa-fw"></i>쇼핑몰로 이동</a>
                        </li>
						<li class="active">
                            <a href="#"style="background: #e7e7e7;"><i class="fa fa-bar-chart-o fa-fw"></i>상품관리<span class="fa arrow">▼</span></a>
							 <ul class="nav nav-second-level">
                                <li>
                                    <a href="/SG/adminGoodsList">- 완제품 목록</a>
                                </li>
                                <li>
                                    <a href="/SG/adminGoodsForm">- 완제품등록</a>
                                </li>
                                <li>
                                    <a href="/SG/adminToppingList">- 토핑목록</a>
                                </li>
                                <li>
                                    <a href="/SG/adminToppingForm">- 토핑등록</a>
                                </li>
                            </ul>
                        </li>
                        <li class="active">
                            <a href="#"style="background: #e7e7e7;"><i class="fa fa-dashboard fa-fw"></i>회원관리<span class="fa arrow">▼</span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">- 회원목록</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>   
                                             
                        <li class="active">
                            <a href="#"style="background: #e7e7e7;"><i class="fa fa-bar-chart-o fa-fw"></i>주문관리<span class="fa arrow">▼</span></a>
							 <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">- 주문목록</a>
                                </li>

                            </ul>
                        </li>
                        <li class="active">
                            <a href="#"style="background: #e7e7e7;"><i class="fa fa-bar-chart-o fa-fw"></i>게시판관리<span class="fa arrow">▼</span></a>
							 <ul class="nav nav-second-level">
                                <li>
                                    <a href="/SG/adminNotice">- 공지사항</a>
                                </li>
                                <li>
                                    <a href="/SG/adminDIY">- DIY게시판</a>
                                </li>
               
                                 <li>
                                    <a href="/SG/adminFaq">- FAQ</a>
                                </li>
                                <li>
                                    <a href="/SG/adminQna">- Q&A</a>
                                </li>

                            </ul>
                        </li>
                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
       		
            <!-- 메인container-->
           	 <tiles:insertAttribute name="body"/>
            <!-- // container -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    

</body>

</html>