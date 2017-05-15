
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_CN">
    <head>
        <meta charset="UTF-8">
        <title>用户</title>
        <link rel="stylesheet" type="text/css" href="/static/css/customer/customer.css"/>
        <script type="text/javascript" src="/static/common/js/jquery-3.2.0.min.js"></script>
        <script type="text/javascript" src="/static/js/customer/customer.js"></script>
    </head>

    <body>
        ${name}：晚上好
        <a href="/customer/goTest">进入测试页面</a><br/>
        <a href="/customer/getCustomerList">进入用户页面</a><br/>
        <a href="/customer/goOther">进入其它页面</a>
        <button class="anniu" id="anniu">按钮</button>
    </body>
</html>
