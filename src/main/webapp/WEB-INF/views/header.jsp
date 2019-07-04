<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*"%>
<%@ page import ="javax.naming.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>DDinside</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Digital Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />

	<!-- css files -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" >
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet" >
    <link href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet" >
    <link href="${pageContext.request.contextPath}/resources/css/GalleryList.css" rel="stylesheet" >
    <link href="${pageContext.request.contextPath}/resources/css/css/main.css" rel="stylesheet" >
    <link href="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.js" rel="stylesheet" >

	<!-- //css files -->
	
	<!-- google fonts -->
	<link href="https://fonts.googleapis.com/css?family=Noto+Serif+JP&display=swap" rel="stylesheet">
	<!-- //google fonts -->
	<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	
<%
	String message = (String)request.getAttribute("message");
%>
<c:if test="${message ne null}">
	<script>
		alert("<%=message%>");
	</script>
</c:if>

    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
	

	
</head>
<body>	

<!-- //header -->
<header class="py-4">
	<div class="container">
			<div id="logo">
				<h1> <a href="Main"> DDinside</a></h1>			
			</div>
			
		<!-- nav -->
		<!-- 검색  -->
		<c:set var="total" value="統合"/>
			<div id="searchbar toppadding01" class="py-2">
				<form action="Search?board_id=${total}" method="post">
						<div class="form-group">
							<input type="text" name="keyWord" class="form-control06">
							<button type="submit" class="btn01 btn-primary submit mt-2">検索</button>
						</div>
					</form>
			</div>
		<!-- 검색 끝 -->
	</div>
		
	<div>
	
		<nav class="py-4 menubar">
			<ul class="menu mt-2 ml-auto">
				<li class="" >
				<!-- First Tier Drop Down -->
				<label for="drop-2" class="toggle">掲示板リスト <span class="fa fa-angle-down" aria-hidden="true"></span> </label>
				<a href="Main">掲示板リスト </a>
				</li>
				
				<li class="">
				<!-- First Tier Drop Down -->
				<label for="drop-2" class="toggle">ヒット掲示板 <span class="fa fa-angle-down" aria-hidden="true"></span> </label>
				<a href="#">ヒット掲示板 </a>
				<ul class="inner-ul">
				
					<li><c:set var="best" value="高評価"/><a href="BoardList?board_id=${best}">高評価 </a></li>
					<li><c:set var="worst" value="低評価"/><a  href="BoardList?board_id=${worst}">低評価</a></li>
					<li><c:set var="aho" value="アホ"/><a href="BoardList?board_id=${aho}">アホ </a></li>
				</ul>
				</li>
				
				<li class="">
				<!-- First Tier Drop Down -->
				<label for="drop-2" class="toggle">お知らせ <span class="fa fa-angle-down" aria-hidden="true"></span> </label>
				<a href="#">お知らせ </a>
				<ul class="inner-ul">
					<c:set var="notice" value="お知らせ"/>
					<li><a href="BoardList?board_id=${notice }">お知らせ</a></li>
				</ul>
				</li>
				
			
			<c:if test="${Admin eq null }">
			
			</c:if>
			<c:if test="${Admin ne null }">
				<li class="">
				<!-- First Tier Drop Down -->
				<label for="drop-2" class="toggle">管理者掲示板 <span class="fa fa-angle-down" aria-hidden="true"></span> </label>
				<a href="#">管理者掲示板 </a>
				<input type="checkbox" id="drop-2"/>
				<ul class="inner-ul">
					<c:set var="notice" value="お知らせ"/>
					<li><a href="BoardList?board_id=${notice }">お知らせ管理</a></li>
					<c:set var="report" value="申告管理"/>
					<li><a href="BoardList?board_id=${report }">申告管理</a></li>
					<c:set var="account" value="アカウント管理"/>
					<li><a href="BoardList?board_id=${account }">アカウント管理</a></li>
				</ul>
				</li>
			</c:if>
				
			<c:if test="${loginUser eq null and Admin eq null }">		
				<li class="">
				<a href="#popup3">ログイン </a>
				</li>
			</c:if>	
			<c:if test="${loginUser ne null or Admin ne null }">	
				<li class="">
				<c:if test="${Admin eq null }">
				<a href="GetProfile?num=${loginUser.num }">プロフィール </a>
				</c:if>
				</li>
				
				<li class="">
				<a href="Logout">ログアウト </a>
				</li>
			</c:if>
			
			</ul>
			</nav>
			</div>			
<script>
$(function(){
    $("#popbutton").click(function(){
        $('div.modal').modal({
                      remote : 'layer.html'
                });
    })
})

</script>

		<div class="clear"></div>
		<!-- //nav -->
	
</header>
<!-- //header -->


<!-- banner -->
<div class="banner" id="home">
	<div class="container">
		<div class="row banner-text">
			<div class="slider-info col-lg-6">
	
			</div>
			<div class="col-lg-6 col-md-8 mt-lg-0 mt-sm-5 mt-3 banner-image text-lg-center">
			
			</div>
		</div>
	</div>
</div>
<!-- //banner -->



</head>
<body>