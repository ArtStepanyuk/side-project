<%--
  Created by IntelliJ IDEA.
  User: Shishambe
  Date: 8/25/15
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html ng-app="app">
<head>
    <style>
        /* This helps the ng-show/ng-hide animations start at the right place. */
        /* Since Angular has this but needs to load, this gives us the class early. */
        .ng-hide { display: none!important; }
    </style>
    <base href="/" />
    <title ng-bind="title">Благодійний портал</title>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
    <base href="/">

    <link rel="stylesheet" href="resources/styles/lib-9667fbc443.css">

    <link rel="stylesheet" href="resources/styles/app-b2bf057b75.css">
</head>
<body>
    <header>
        <nav class="navbar navbar-default navbar-fixed-top fw700">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" ng-click="isCollapsed = !isCollapsed" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">
                        <img alt="SoftServe" class="logo" src="/resources/images/logo.png">
                    </a>
                </div>
                <div class="collapse navbar-collapse" collapse="isCollapsed" id="navbar">
                    <ul class="nav navbar-nav">
                        <li><a href="/needs">Потреби</a></li>
                        <li><a href="/offers">Пропозиції</a></li>
                        <li><a href="/needs/newneedstep1">Додати Потребу</a></li>
                        <li><a href="/offers/newofferstep1">Додати Пропозицію</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <hr class="divider hidden-lg hidden-md hidden-sm">
                        </li>
                        <li><a href="/about">Про нас</a></li>
                        <li><a href="/profile">Мій кабінет</a></li>
                        <li><a href="#">Вийти</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div ui-view></div>
    <footer>
        <nav id="footer" class="navbar navbar-default navbar-fixed-bottom">
            <div class="container">
                <p class="navbar-text text-center">© SoftServe LTD, 2010-2014, 0 800 60 3000 (10.00 – 19.00 Пн.-Пт.)
                    <a href="/contacts">Контакти</a>
                    <a href="/sitemap">Site Map</a>
                    <a href="/privacy">Privacy</a>
                    <a href="/terms">Terms Of Use</a>
                </p>
            </div>
        </nav>
    </footer>

    <script src="resources/js/lib-03b044dcbb.js"></script>
    <!-- added uk_ua localization manually for datepicker -->
    <!--<script src="/bower_components/angular-i18n/angular-locale_uk-ua.js"></script>-->
    <script src="resources/js/app-dd0d0e651c.js"></script>
</body>
</html>

