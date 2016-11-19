<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Stats</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/layout.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" 
                                data-target="#navbar1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="${pageContext.request.contextPath}/home" class="navbar-left">
                            <img src="${pageContext.request.contextPath}/img/icon.png" class="img-logo"></a>
                    </div>
                    <div id="navbar1" class="navbar-collapse collapse navbar-right">
                        <ul class="nav navbar-nav">
                            <li role="presentation"><a 
                                    href="${pageContext.request.contextPath}/home">
                                    <span class="glyphicon glyphicon-home"></span> Home</a></li>
                            <li role="presentation" class="active"><a 
                                    href="${pageContext.request.contextPath}/stats">
                                    <span class="glyphicon glyphicon-stats"></span> Stats</a></li>
                            <li role="presentation"><a 
                                    href="${pageContext.request.contextPath}/search">
                                    <span class="glyphicon glyphicon-search"></span> Search</a></li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
                <!--/.container-fluid -->
            </nav>
            <div class="row">
                <div class="col-md-12">
                    <h2>Statistics</h2>
                    <div id="chart_div"></div>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
        <script>
            google.load('visualization', '1.0', {'packages': ['corechart']});
        </script> 
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/stats.js"></script> 
    </body>
</html>

