<%--
  Created by IntelliJ IDEA.
  User: haoooo
  Date: 2020/2/5
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>欢迎登陆</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
    <link href="static/css/login.css" rel="stylesheet">
    <script src="static/js/jquery-3.4.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>
</head>
<style>
    #signin {
        background: #18c8f6;
        height: auto;
        background:url("/static/img/bing.lylares.com-2019-10-10-1080p.jpg") no-repeat center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
        color: rgba(255,255,255,.95);
    }
</style>
<script>

    $(function () {
        $("#subbtn").on("click",function(){
            if($("#userName").val()==""||$("#userPass").val()==""){
                $("#loginmes").css("color","red");
                $("#loginmes").html("用户名和密码不能为空！！")
            }else{
               $.post("/checklog",{"userName":$("#userName").val(),"userPass":$("#userPass").val()},function (data) {
                   var obj = JSON.parse(data)
                       if(obj.reMes=="success"){
                           $("#myloginform").submit();
                       }else{
                       $("#loginmes").css("color","red");
                       $("#loginmes").html("用户名密码不匹配！！")
                   }
               })
            }
        })

    })

</script>

<body id="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-12">
            <form id="myloginform" method="post" action="/home">
                <h4 class="no-margins">登录：</h4>
                <p id="loginmes" class="m-t-md">登录到后台首页</p>
                <input id="userName" type="text" class="form-control uname" placeholder="用户名" />
                <input id="userPass" type="password" class="form-control pword m-b" placeholder="密码" />
                <a href="">忘记密码了？</a>
                <input id="subbtn" class="btn btn-success btn-block" type="button" value="登陆" >
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; AwslCRM
        </div>
    </div>
</div>
</body>
</html>
