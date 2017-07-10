<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
html,body{
	height:100%;
	margin:0;
	padding:0;
	}
</style>
<title>샐러드구쁘다</title>
</head>
<body style="overflower-x:hidden;">

<div>
<tiles:insertAttribute name="header" />
</div>


<div style="height:auto; overflow-x:hidden; ">
<tiles:insertAttribute name="body" />
</div>

<div>
<tiles:insertAttribute name="footer" />
</div>
</body>
</html>