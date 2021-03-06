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
        background: url("/static/img/bing.lylares.com-2019-10-10-1080p.jpg") no-repeat center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
        color: rgba(255, 255, 255, .95);
    }
    #accountlog {
        color: black;
    }

    #phonelog {
        color: black;
    }
</style>
<script>
    //账号登陆
    function accPress(){
        if ($("#userName").val() == "" || $("#userPass").val() == "") {
            $("#loginmes").css("color", "red");
            $("#loginmes").html("用户名和密码不能为空！！")
        } else {
            $.post("/checklog", {
                "userName": $("#userName").val(),
                "userPass": $("#userPass").val()
            }, function (data) {
                var obj = JSON.parse(data)
                if (obj.reMes == "success") {
                    $("#myloginform").submit();
                } else {
                    $("#loginmes").css("color", "red");
                    $("#loginmes").html("用户名密码不匹配！！")
                }
            })
        }
    }
    //手机号码登陆
    function phPress(){
        if ($("#userPhone").val() == "" || $("#verCode").val() == "") {
            $("#loginmes").css("color", "red");
            $("#loginmes").html("手机号或验证码不能为空！！")
        }else{
            $.post("/checklog2",{"userPhone":$("#userPhone").val(),verCode:$("#verCode").val()},
            function (data) {
              console.log(data)
                if(data=="success"){
                    $("#myloginform").submit();
                }else if (data=="NoneKey"){
                    $("#loginmes").css("color", "red");
                    $("#loginmes").html("验证码已失效，请重新获取验证码！")
                }else if(data=="VerErro"){
                    $("#loginmes").css("color", "red");
                    $("#loginmes").html("验证码错误，请重新填写验证码！")
                }else if(data=="PhoneErro"){
                    $("#loginmes").css("color", "red");
                    $("#loginmes").html("手机号码格式错误！")
                }
            })
        }
    }

    //获取验证码
    function getVerFidCode(){
        if($("#userPhone").val()==""){
            $("#loginmes").css("color", "red");
            $("#loginmes").html("手机号或验证码不能为空！！")
        }else{
            $.post("/sendVerCode",{"userPhone":$("#userPhone").val()},function (data) {
                console.log(data)
                if(data=="success"){
                    $("#loginmes").css("color", "green");
                    $("#loginmes").html("验证码发送成功！！")
                }else {
                    $("#loginmes").css("color", "red");
                    $("#loginmes").html("发送失败，手机号码有误！")
                }
            })
        }
    }

    $(function () {
        $("#accountlog").on("click", function () {
            $("#choicLogMethod").html("");
            $("#loginmes").css("color", "white");
            $("#choicLogBtn").html("");
            $("#loginmes").html("登录到后台首页")
            var mycontent = '<input id="userName" type="text" class="form-control uname" placeholder="手机号/邮箱/用户名" />'
                + '<input id="userPass" type="password" class="form-control pword m-b" placeholder="密码" />';
            var mycontent1 = '<input id="accSubbtn" class="btn btn-success btn-block" type="button" value="登陆" onclick="accPress()">';
            $("#choicLogMethod").html(mycontent);
            $("#choicLogBtn").html(mycontent1);
        })

        $("#phonelog").on("click", function () {
            $("#choicLogMethod").html("");
            $("#loginmes").css("color", "white");
            $("#choicLogBtn").html("");
            $("#loginmes").html("登录到后台首页")
            var mycontent = '<input id="userPhone" type="text" class="form-control uname" placeholder="请输入手机号" />\n' +
                '<input id="verCode" class="form-control" placeholder="6位数字验证码" >\n' +
                '<span class="input-group-btn">\n' +
                '<button class="btn btn-info" type="button" onclick="getVerFidCode()">获取验证码</button>\n' +
                '</span>';
            var mycontent1 = '<input id="phSubbtn" class="btn btn-success btn-block" type="button" value="登陆" onclick="phPress()">';
            $("#choicLogMethod").html(mycontent);
            $("#choicLogBtn").html(mycontent1);
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
                <ul class="nav nav-tabs">
                    <li id="accountli" role="presentation"><a id="accountlog">账号登陆</a></li>
                    <li id="phoneli" role="presentation"><a id="phonelog">免密登陆</a></li>
                </ul>
                <div id="choicLogMethod">
                    <input id="userName" type="text" class="form-control uname" placeholder="手机号/邮箱/用户名"/>
                    <input id="userPass" type="password" class="form-control pword m-b" placeholder="密码"/>
                </div>
                <br>
                <a href="">| 微信登陆 |</a><a href=""> APP扫码登陆 |</a><a href=""> 社交账号登陆 |</a>
                <div id="choicLogBtn">
                <input id="accSubbtn" class="btn btn-success btn-block" type="button" value="登陆">
                </div>
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
