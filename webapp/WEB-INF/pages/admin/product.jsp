<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <![endif]-->
    <title>Админ. панель</title>
    <!-- BOOTSTRAP CORE STYLE  -->
    <link href="/resources/css/admin/bootstrap.css" rel="stylesheet" />
    <!-- FONT AWESOME ICONS  -->
    <link href="/resources/css/admin/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE  -->
    <link href="/resources/css/admin/style.css" rel="stylesheet" />
    <!-- HTML5 Shiv and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!--<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>-->
    <!--<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
    <%--<![endif]-->--%>
</head>
<body>
<header>
</header>
<!-- HEADER END-->
<div class="navbar navbar-inverse set-radius-zero">

</div>
<!-- LOGO HEADER END-->
<section class="menu-section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="navbar-collapse collapse ">
                    <ul id="menu-top" class="nav navbar-nav navbar-right">
                        <li><a href="/FashionShop/admin/main">Заказы</a></li>
                        <li><a href="/FashionShop/admin/users">Пользователи</a></li>
                        <li><a class="menu-top-active" href="/FashionShop/admin/product">Добавить товар</a></li>
                    </ul>
                </div>
            </div>

        </div>
    </div>
</section>
<!-- MENU SECTION END-->
<div class="content-wrapper">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h4 class="page-head-line">Добавить товар</h4>

                <form:form action="/FashionShop/admin/product/addProduct" commandName="product" enctype="multipart/form-data">
                    <c:if test="${!empty product.name}">
                        <div class="form-group">
                            <form:label path="productId">
                                <spring:message text="ID"/>
                            </form:label>
                            <form:input path="productId" readonly="true" size="8" disabled="true"/>
                            <form:hidden path="productId"/>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <form:label path="name">
                            <spring:message text="Название"/>
                        </form:label>
                        <form:input path="name"/>
                        <form:errors path="name"/>
                    </div>
                    <div class="form-group">
                        <form:label path="description">
                            <spring:message text="Описание"/>
                        </form:label>
                        <form:textarea path="description"/>
                        <form:errors path="description"/>
                    </div>
                    <div class="form-group">
                        <form:label path="price">
                            <spring:message text="Цена"/>
                        </form:label>
                        <form:input  path="price" />
                        <form:errors path="price"/>
                    </div>
                    <div class="form-group">
                        <form:label path="category">
                            <spring:message text="Категория"/>
                        </form:label>
                        <select name="categoryOnForm">
                            <c:if test="${!empty product.name}">
                                <option selected = "true">${product.category.categoryName}</option>
                            </c:if>
                            <c:if test="${empty product.name}">
                                <c:forEach items="${categoryList}" var="category">
                                    <option>${category.categoryName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                    <div class="form-group">
                        <form:label path="gender">
                            <spring:message text="Пол"/>
                        </form:label>
                        <select name="genderOnForm">
                            <c:if test="${!empty product.name}">
                                <option selected = "true">${product.gender.genderName}</option>
                            </c:if>
                            <c:if test="${empty product.name}">
                                <c:forEach items="${genderList}" var="gender">
                                    <option>${gender.genderName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>

                    <c:if test="${empty product.name}">
                        <div class="form-group">
                            <input type="file" name="file" id="post-photo">
                            <label for="post-photo" class="btn">Выбрать фото</label>
                        </div>
                    </c:if>

                    <c:if test="${!empty product.name}">
                        <input type="file" name="file" id="post-photo-not-null">
                        <label for="post-photo-not-null" class="btn">Обновить фото</label>
                    </c:if>
                    <c:if test="${empty product.name}">
                        <input type="submit"
                               value="<spring:message text="Добавить товар"/>" class="btn"/>
                    </c:if>
                    <c:if test="${!empty product.name}">
                        <input type="submit"
                               value="<spring:message text="Редактировать товар"/>" class="btn"/>
                    </c:if>
                    <c:if test="${empty product.name}">
                        <input type="reset" value="Отмена" class="btn">
                    </c:if>
                    <c:if test="${!empty product.name}">
                        <a href="/" class="btn">Отмена</a>
                    </c:if>
                </form:form>


            </div>

        </div>


    </div>
</div>
<!-- CONTENT-WRAPPER SECTION END-->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                &copy; 2015 YourCompany | By : <a href="http://www.designbootstrap.com/" target="_blank">DesignBootstrap</a>
            </div>

        </div>
    </div>
</footer>
<!-- FOOTER SECTION END-->
<!-- JAVASCRIPT AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
<!-- CORE JQUERY SCRIPTS -->
<script src="/resources/js/admin/jquery-1.11.1.js"></script>
<!-- BOOTSTRAP SCRIPTS  -->
<script src="/resources/js/admin/bootstrap.js"></script>

</body>
</html>
