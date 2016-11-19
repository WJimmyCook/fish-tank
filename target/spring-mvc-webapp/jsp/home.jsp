<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Fish Tank App</title>
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
                            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/stats"><span class="glyphicon glyphicon-stats"></span> Stats</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
                <!--/.container-fluid -->
            </nav>
            <div class="row">
                <div class="col-md-6">
                    <h2>Fish List</h2>
                    <%@include file="fishSummaryTableFragment.jsp"%>
                </div>
                <div class="col-md-6">
                    <div class="col-md-offset-4 col-md-8">
                        <h2>Add New Fish</h2>
                    </div>
                    <form class="form-horizontal" action="fish" method="POST" id="newFishForm">
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label">
                                Fish Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" 
                                       id="add-name" placeholder="Fish Name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-scientific-name" class="col-md-4 control-label">
                                Scientific Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" 
                                       id="add-scientific-name" placeholder="Scientific Name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-picture-url" class="col-md-4 control-label">
                                Picture URL:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" 
                                       id="add-picture-url" placeholder="www.example.com" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-water-type" class="col-md-4 control-label">
                                Water Type:
                            </label>
                            <div class="col-md-8">
                                <label class="radio-inline">
                                    <input type="radio" name="waterRadioOptions" id="fresh-water-radio" value="Freshwater"> Freshwater
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="waterRadioOptions" id="salt-water-radio" value="Saltwater"> Saltwater
                                </label>
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="add-button" class="btn btn-default">
                                    Create Fish
                                </button>
                            </div>
                        </div>
                    </form>
                    <div id="validationErrors" class="alert alert-danger" style="display:none"/>
                </div>
            </div>
        </div>
        <%@include file="detailsEditModalFragment.jsp"%>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/fishList.js"></script>
    </body>
</html>

