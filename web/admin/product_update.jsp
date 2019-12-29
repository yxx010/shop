<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<!-- Mirrored from admindesigns.com/demos/absolute/1.1/admin_forms-validation.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 06 Aug 2015 02:56:15 GMT -->
<head>
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">

<title>油画商城--修改商品</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/skin/default_skin/css/theme.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/admin-tools/admin-forms/css/admin-forms.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/assets/img/favicon.ico">
</head>

<body class="admin-validation-page" data-spy="scroll" data-target="#nav-spy" data-offset="200">
<div id="main">
    <%@ include file="header.jsp" %>

     <%@ include file="left.jsp" %>

    <section id="content_wrapper">
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 编辑商品信息 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <form action="${pageContext.request.contextPath}/ProductServlet?method=update" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="pid" value="${product.pid}">
                    <input type="hidden" name="path" value="${product.path}">
                    <input type="hidden" name="filename" value="${product.filename}">
                    <div class="panel-body bg-light">
                        <div class="section-divider mt20 mb40">
                            <span> 基本信息 </span>
                        </div>
                        <div class="section row">
							<div class="col-md-2"></div>
							<div class="col-md-1">
                                <label for="pname" class="field prepend-icon">
                                    <label for="pname" class="field-icon">
										名称
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="pname" class="field">
                                    <input id="pname" name="pname" class="gui-input" placeholder="请输入商品名称" type="text" value="${product.pname}"/>
                                </label>
                            </div>
                        </div>
						<div class="section row">
							<div class="col-md-2"></div>
							<div class="col-md-1">
                                <label for="price" class="field prepend-icon">
                                    <label for="price" class="field-icon">
										价格
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="price" class="field">
                                    <input id="price" name="price" class="gui-input" placeholder="请输入商品价格" type="text" value="${product.price}"/>

                                </label>
                            </div>
                        </div>
                        <div class="section row">
                            <div class="col-md-2"></div>
                            <div class="col-md-1">
                                <label for="author" class="field prepend-icon">
                                    <label for="author" class="field-icon">
                                        作者
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="author" class="field">
                                    <input id="author" name="author" class="gui-input" placeholder="请输入作者名字" type="text" value="${product.author}"/>

                                </label>
                            </div>
                        </div>
                        <div class="section row">
							<div class="col-md-2"></div>
							<div class="col-md-1">
                                <label for="cid" class="field prepend-icon">
                                    <label for="cid" class="field-icon">
										分类
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="cid" class="field select">
                                    <select id="cid" name="cid" class="gui-input" placeholder="分类...">
                                        <c:if test="${c.cid==product.category.cid}">selected</c:if>
                                        <c:forEach var="c" items="${categoryList}">
                                            <option value="${c.cid}" <c:if test="${c.cid==product.category.cid}">selected</c:if>>${c.cname}</option>
                                        </c:forEach>
                                    </select>
                                    <i class="arrow double"></i>
                                </label>
                            </div>
                        </div>
						 <div class="section row">
							<div class="col-md-2"></div>
							<div class="col-md-1">
                                <label for="sn" class="field prepend-icon">
                                    <label for="sn" class="field-icon">
										图片
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="name" class="field">
                                    <input id="name" name="name" class="gui-input" placeholder="价格" type="file" value="上传图片"/>
                                </label>
                            </div>
                        </div>
						<div class="section row">
							<div class="col-md-2"></div>
							<div class="col-md-1">
                                <label for="description" class="field prepend-icon">
                                    <label for="description" class="field-icon">
										描述
                                    </label>
                                </label>
                            </div>
							<div class="col-md-6">
								<label for="description" class="field">
									<input id="description" name="description" class="gui-input" placeholder="请输入商品描述" type="text" value="${product.description}"/>
								</label>
							</div>
                        </div>
                        <div class="panel-footer text-center">
                            <button type="submit" class="button"> 保存 </button>
                            <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

</section>
</div>
<style>
    /* demo page styles */
    body { min-height: 2300px; }

    .content-header b,
    .admin-form .panel.heading-border:before,
    .admin-form .panel .heading-border:before {
        transition: all 0.7s ease;
    }
    /* responsive demo styles */
    @media (max-width: 800px) {
        .admin-form .panel-body { padding: 18px 12px; }
    }
</style>

<style>
    .ui-datepicker select.ui-datepicker-month,
    .ui-datepicker select.ui-datepicker-year {
        width: 48%;
        margin-top: 0;
        margin-bottom: 0;

        line-height: 25px;
        text-indent: 3px;

        color: #888;
        border-color: #DDD;
        background-color: #FDFDFD;

        -webkit-appearance: none; /*Optionally disable dropdown arrow*/
    }
</style>
<script src="vendor/jquery/jquery-1.11.1.min.js"></script>
<script src="vendor/jquery/jquery_ui/jquery-ui.min.js"></script>
<script src="assets/admin-tools/admin-forms/js/jquery.validate.min.js"></script>
<script src="assets/admin-tools/admin-forms/js/additional-methods.min.js"></script>
<script src="assets/admin-tools/admin-forms/js/jquery-ui-datepicker.min.js"></script>
<script src="assets/js/utility/utility.js"></script>
<script src="assets/js/demo/demo.js"></script>
<script src="assets/js/main.js"></script>
<script type="text/javascript" src="js/pages.js"></script>
<script type="text/javascript" src="js/items.js"></script>
</body>
</html>
