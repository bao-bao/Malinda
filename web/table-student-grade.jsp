﻿<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Vo.DbTake" %>
<%@ page import="Model.Vo.DbUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <title>Course Registration</title>
    <meta name="description"
          content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="css/app.v2.css" type="text/css"/>
</head>
<body>
<section class="vbox">
    <header class="bg-dark dk header navbar navbar-fixed-top-xs">

        <!-- label -->
        <div class="navbar-header aside-md">
            <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen" data-target="#nav"><i
                    class="fa fa-bars"></i> </a>
            <a href="#" class="navbar-brand" data-toggle="fullscreen"><img src="images/logo.png" class="m-r-sm">Course
                Register</a>
            <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user"> <i class="fa fa-cog"></i>
            </a>
        </div>
        <!-- self info -->
        <ul class="nav navbar-nav navbar-right hidden-xs nav-user">
            <li class="dropdown hidden-xs"><a href="#" class="dropdown-toggle dker" data-toggle="dropdown"><i
                    class="fa fa-fw fa-search"></i></a>
                <section class="dropdown-menu aside-xl animated fadeInUp">
                    <section class="panel bg-white">
                        <form role="search">
                            <div class="form-group wrapper m-b-none">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search">
                                    <span class="input-group-btn">
                  <button type="submit" class="btn btn-info btn-icon"><i class="fa fa-search"></i></button>
                  </span></div>
                            </div>
                        </form>
                    </section>
                </section>
            </li>
            <% DbUser user = (DbUser)(request.getSession().getAttribute("loginuser")); %>
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span
                    class="thumb-sm avatar pull-left"> <img src="images/avatar.jpg"> </span>
                <%= user.getName() %><b
                        class="caret"></b> </a>
                <ul class="dropdown-menu animated fadeInRight">
                    <span class="arrow top"></span>
                    <li><a href="signin.jsp" data-toggle="ajaxModal">Logout</a></li>
                </ul>
            </li>
        </ul>
    </header>
    <section>
        <section class="hbox stretch"> <!-- .aside -->
            <aside class="bg-dark lter aside-md hidden-print" id="nav">
                <section class="vbox">
                    <header class="header bg-primary lter text-center clearfix">
                        <div class="btn-group">
                            <button onclick="location='signup.jsp'" type="button" class="btn btn-sm btn-dark btn-icon"
                                    title="New project" disabled=""><i
                                    class="fa fa-plus"></i></button>
                            <div class="btn-group hidden-nav-xs">
                                <button onclick="location='signup.jsp'" type="button" class="btn btn-sm btn-primary"
                                        disabled="">
                                    Maintain
                                </button>
                            </div>
                        </div>
                    </header>
                    <section class="w-f scrollable">
                        <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0"
                             data-size="5px" data-color="#333333"> <!-- nav -->
                            <nav class="nav-primary hidden-xs">
                                <ul class="nav">
                                    <li><a href="homepage" class="active"> <i
                                            class="fa fa-dashboard icon"> <b class="bg-danger"></b> </i>
                                        <span>Home Page</span> </a></li>
                                    <li><a href="#"> <i class="fa fa-columns icon"> <b class="bg-warning"></b>
                                    </i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i
                                            class="fa fa-angle-up text-active"></i> </span> <span>Admin</span> </a>
                                        <ul class="nav lt">
                                            <li><a href="table-admin.jsp"> <i class="fa fa-angle-right"></i> <span>Management</span>
                                            </a></li>
                                        </ul>
                                    </li>
                                    <li><a href="#"> <i class="fa fa-file-text icon"> <b class="bg-primary"></b>
                                    </i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i
                                            class="fa fa-angle-up text-active"></i> </span> <span>Professor</span> </a>
                                        <ul class="nav lt">
                                            <li><a href="mycourse"> <i class="fa fa-angle-right"></i>
                                                <span>My Courses</span> </a></li>
                                        </ul>
                                    </li>
                                    <li class="active"><a href="#"> <i class="fa fa-pencil icon"> <b class="bg-info"></b>
                                    </i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i
                                            class="fa fa-angle-up text-active"></i> </span> <span>Students</span> </a>
                                        <ul class="nav lt">
                                            <li><a href="mygrade"> <i class="fa fa-angle-right"></i>
                                                <span>My Courses</span> </a></li>
                                            <li><a href="register"> <i class="fa fa-angle-right"></i>
                                                <span>Registration</span> </a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </nav>
                            <!-- / nav --> </div>
                    </section>
                    <footer class="footer lt hidden-xs b-t b-dark">
                        <a href="#nav" data-toggle="class:nav-xs" class="pull-right btn btn-sm btn-dark btn-icon"> <i
                                class="fa fa-angle-left text"></i> <i class="fa fa-angle-right text-active"></i> </a>
                    </footer>
                </section>
            </aside>
            <!-- /.aside -->
            <section id="content">
                <section class="vbox">
                    <section class="scrollable padder">
                        <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
                            <li><a href="homepage.jsp"><i class="fa fa-home"></i> Home</a></li>
                            <li><a href="#"> Student</a></li>
                            <li class="active"> All Grades</li>
                        </ul>
                        <div class="m-b-md">
                            <h3 class="m-b-none"> All Grades</h3>
                        </div>
                            <div class="row">
                                <%
                                    ArrayList<DbTake> takes = (ArrayList<DbTake>)request.getAttribute("take");
                                %>
                                <div class="col-sm-6">
                                    <section class="panel panel-default">
                                        <table class="table table-striped m-b-none text-sm">
                                            <thead>
                                            <tr>
                                                <th>Course Name</th>
                                                <th>Grade</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                for(int i = 0; i < takes.size(); i++) {
                                                     if(i%2 == 0) {
                                            %>
                                            <tr>
                                                <td><%= takes.get(i).getCourse() %></td>
                                                <% if(takes.get(i).getGrade() == 0.0) {%>
                                                <td><%= '-' %></td>
                                                <% } else { %>
                                                <td><%= takes.get(i).getGrade() %></td>
                                                <% } %>
                                            </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </section>
                                </div>
                                <%
                                    if(takes.size() != 1)  {
                                %>
                                <div class="col-sm-6">
                                    <section class="panel panel-default">
                                        <table class="table table-striped m-b-none text-sm">
                                            <thead>
                                            <tr>
                                                <th>Course Name</th>
                                                <th>Grade</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                for(int j = 0; j < takes.size(); j++) {
                                                    if(j%2 == 1) {
                                            %>
                                            <tr>
                                                <td><%= takes.get(j).getCourse() %></td>
                                                <% if(takes.get(j).getGrade() == 0.0) {%>
                                                <td><%= '-' %></td>
                                                <% } else { %>
                                                <td><%= takes.get(j).getGrade() %></td>
                                                <% } %>
                                            </tr>
                                            <%      }
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </section>
                                </div>
                                <% } %>
                            </div>
                    </section>
                </section>
                <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
            </section>
            <aside class="bg-light lter b-l aside-md hide" id="notes">
                <div class="wrapper">Notification</div>
            </aside>
        </section>
    </section>
</section>
<script src="js/app.v2.js"></script> <!-- Bootstrap --> <!-- App -->
</body>
</html>