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
                        <li><a class="menu-top-active" href="/FashionShop/admin/users">Пользователи</a></li>
                        <li><a href="/FashionShop/admin/product">Добавить товар</a></li>
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
                <h4 class="page-head-line">Пользователи</h4>

            </div>

        </div>

        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <th>Логин</th>
                    <th>Доступен</th>
                    <th>Адрес</th>
                    <th>ФИО</th>
                    <th>Редактировать</th>
                    <th>Удалить</th>
                    <c:forEach items="${userList}" var="user">
                        <tr>
                            <td>${user.userName}</td>
                            <c:if test="${user.enabled}"><td>Да</td></c:if>
                            <c:if test="${!user.enabled}"><td>Нет</td></c:if>
                            <td>${user.address}</td>
                            <td>${user.contactName}</td>
                            <td><a href="/FashionShop/admin/users/update/${user.userName}">Редактировать</a> </td>
                            <td><a href="/FashionShop/admin/users/delete/${user.userName}">Удалить</a> </td>
                            <td><a href="/FashionShop/admin/users/available/${user.userName}">Поменять доступность</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>


        <h4 class="page-head-line">Редактирование пользователя</h4>
        <c:if test="${user ne null}">
        <form:form action="/FashionShop/admin/users/updateInfo" commandName="user">
            <input type="hidden" name="update" value="update" hidden="hidden">
            <form:label path="userName">
                <spring:message text="LOGIN"/>
            </form:label>
            <form:input path="userName" readonly="true" size="8" disabled="true"/>
            <form:hidden path="userName"/>

            <form:label path="password">
                <spring:message text="PASSWORD"/>
            </form:label>
            <form:input path="password" readonly="true" size="8" disabled="true"/>
            <form:hidden path="password"/>

            <form:checkbox path="enabled" checked="checked" hidden="hidden"/>

            <select name="role">
                <option value="ROLE_USER" selected="selected">ROLE_USER</option>
                <option value="ROLE_ADMIN">ROLE_ADMIN</option>
            </select>

            <span>Адрес</span>
            <form:label path="address" class="sr-only">
                <spring:message text="Адрес"/>
            </form:label>
            <form:input path="address" class="form-control" placeholder="Адрес" value="${user.address}"/>
            <form:errors path="address"/>

            <span>ФИО</span>
            <form:label path="contactName" class="sr-only">
                <spring:message text="ФИО"/>
            </form:label>
            <form:input path="contactName" class="form-control" placeholder="ФИО" value="${user.contactName}"/>
            <form:errors path="contactName"/>

            <input type="submit" value="Обновить">
        </form:form>
        </c:if>
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
