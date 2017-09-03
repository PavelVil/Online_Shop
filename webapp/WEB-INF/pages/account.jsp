<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Youth Fashion | Home</title>
    <link href="/resources/css/bootstrap-3.1.1.min.css" rel='stylesheet' type='text/css' />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/resources/js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <!--theme-style-->
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href='//fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/simpleCart.min.js"> </script>
    <!-- slide -->
    <script src="/resources/js/responsiveslides.min.js"></script>
    <script>
        $(function () {
            $("#slider").responsiveSlides({
                auto: false,
                speed: 500,
                namespace: "callbacks",
                pager: true,
            });
        });
    </script>

    <!-- animation-effect -->
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <script src="/resources/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!-- //animation-effect -->
</head>
<body>
<!--header-->
<div class="header">
    <div class="header-top">
        <div class="container">
            <div class="col-sm-4 logo animated wow fadeInLeft" data-wow-delay=".5s">
                <h1><a href="/FashionShop/main">Youth <span>Fashion</span></a></h1>
            </div>
            <div class="col-sm-4 world animated wow fadeInRight" data-wow-delay=".5s">
                <div class="cart box_1">
                    <a href="/FashionShop/shopCart">
                        <h3> <div class="total">
                            <span class="simpleCart_total"></span></div>
                            <img src="/resources/images/cart.png" alt=""/></h3>
                    </a>
                    <p><a href="/FashionShop/removeFromCart" class="simpleCart_empty">Очистить корзину</a></p>

                </div>
    </div>
    <div class="container">
        <div class="head-top">
            <div class="n-avigation">

                <nav class="navbar nav_bottom" role="navigation">

                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header nav_2">
                        <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse" data-target="#bs-megadropdown-tabs">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#"></a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <ul class="nav navbar-nav nav_1">
                            <li><a href="/FashionShop/main">На главную</a></li>
                            <li class="dropdown mega-dropdown active">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Женское<span class="caret"></span></a>
                                <div class="dropdown-menu mega-dropdown-menu">
                                    <div class="container-fluid">
                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="women">
                                                <ul class="nav-list ">
                                                    <c:forEach items="${categoryList}" var="category">
                                                        <li> <a href="/FashionShop/women/${category.categoryId}">${category.categoryName}</a></li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Nav tabs -->

                                </div>
                            </li>
                            <li class="dropdown mega-dropdown active">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Мужское<span class="caret"></span></a>
                                <div class="dropdown-menu mega-dropdown-menu">
                                    <div class="container-fluid">
                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="men">
                                                <ul class="nav-list ">
                                                    <c:forEach items="${categoryList}" var="category">
                                                        <li> <a href="/FashionShop/men/${category.categoryId}">${category.categoryName}</a></li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Nav tabs -->

                                </div>
                            </li>
                            <li><a href="/FashionShop/login">Войти</a></li>
                            <li class="last"><a href="/FashionShop/contact">Обратная связь</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->

                </nav>
            </div>


            <div class="clearfix"> </div>
            <!---pop-up-box---->
            <link href="/resources/css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
            <script src="/resources/js/jquery.magnific-popup.js" type="text/javascript"></script>
            <!---//pop-up-box---->
            <div id="small-dialog" class="mfp-hide">
                <div class="search-top">
                    <div class="login">
                        <form action="#" method="post">
                            <input type="submit" value="">
                            <input type="text" name="search" value="Type something..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">

                        </form>
                    </div>
                    <p>	Shopping</p>
                </div>
            </div>
            <script>
                $(document).ready(function() {
                    $('.popup-with-zoom-anim').magnificPopup({
                        type: 'inline',
                        fixedContentPos: false,
                        fixedBgPos: true,
                        overflowY: 'auto',
                        closeBtnInside: true,
                        preloader: false,
                        midClick: true,
                        removalDelay: 300,
                        mainClass: 'my-mfp-zoom-in'
                    });

                });
            </script>
            <!---->
        </div>
    </div>
</div>

<!--//header-->
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInLeft;">
            <li><a href="/FashionShop/main"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>На главную</a></li>
            <li class="active">Аккаунт</li>
        </ol>
    </div>
</div>
<div class="account">
    <div class="container">
        <h2>Аккаунт</h2>
        <div class="account_grid">
            <div class="col-md-6 login-right">

    <sec:authorize access="isAnonymous()">
                <form action="/j_spring_security_check" method="post">

                    <span>Email адрес</span>
                    <input type="text" name="j_username">

                    <span>Пароль</span>
                    <input type="password" name="j_password">

                        <label>
                            <input  id="remember_me"  name="remember-me-param" type="checkbox"/> Запомнить
                        </label>

                    <div class="word-in">
                        <input type="submit" value="Войти">
                    </div>
                </form>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <a href="/j_spring_security_logout" class="btn btn-danger">Выйти</a>
                </sec:authorize>

            </div>
            <div class="col-md-6 login-left">
                <h4>НОВЫЙ ПОЛЬЗОВАЕТЛЬ</h4>
                <p>Создавая аккаунт в нашем магазине, вы сможете быстрее заказывать наши товары.</p>
                <a class="acount-btn" href="/FashionShop/register">Создать аккаунт</a>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>

<!--footer-->
<div class="footer">
    <div class="container">
        <div class="footer-top">
            <div class="col-md-6 top-footer">
                <h3>Следите за нами в соц. сетях</h3>
                <div class="social-icons">
                    <ul class="social">
                        <li><a href="#"><i></i></a> </li>
                        <li><a href="#"><i class="facebook"></i></a></li>
                        <li><a href="#"><i class="google"></i> </a></li>
                        <li><a href="#"><i class="linked"></i> </a></li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="col-md-6 top-footer1">
                <h3>Newsletter</h3>
                <form action="#" method="post">
                    <input type="text" name="search" value="" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='';}">
                    <input type="submit" value="SUBSCRIBE">
                </form>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
    <div class="footer-bottom">
        <div class="container">
            <div class="col-md-3 footer-bottom-cate">
                <h6>Categories</h6>
                <ul>
                    <li><a href="products.html">Curabitur sapien</a></li>
                    <li><a href="single.html">Dignissim purus</a></li>
                    <li><a href="men.html">Tempus pretium</a></li>
                    <li><a href="products.html">Dignissim neque</a></li>
                    <li><a href="single.html">Ornared id aliquet</a></li>

                </ul>
            </div>
            <div class="col-md-3 footer-bottom-cate">
                <h6>Feature Projects</h6>
                <ul>
                    <li><a href="products.html">Dignissim purus</a></li>
                    <li><a href="men.html">Curabitur sapien</a></li>
                    <li><a href="single.html">Tempus pretium</a></li>
                    <li><a href="men.html">Dignissim neque</a></li>
                    <li><a href="products.html">Ornared id aliquet</a></li>
                </ul>
            </div>
            <div class="col-md-3 footer-bottom-cate">
                <h6>Top Brands</h6>
                <ul>
                    <li><a href="products.html">Tempus pretium</a></li>
                    <li><a href="single.html">Curabitur sapien</a></li>
                    <li><a href="men.html">Dignissim purus</a></li>
                    <li><a href="single.html">Dignissim neque</a></li>
                    <li><a href="men.html">Ornared id aliquet</a></li>


                </ul>
            </div>
            <div class="col-md-3 footer-bottom-cate cate-bottom">
                <h6>Our Address</h6>
                <ul>
                    <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>Address : 12th Avenue, 5th block, <span>Sydney.</span></li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>Email : <a href="mailto:info@example.com">info@example.com</a></li>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>Phone : +1234 567 567</li>
                </ul>
            </div>
            <div class="clearfix"> </div>
            <p class="footer-class"> © 2016 Youth Fashion . All Rights Reserved | Design by <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
        </div>
    </div>
</div>
<!--footer-->
</body>
</html>