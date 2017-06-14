
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
    <h1>用户登录</h1>
    <form action="api/login2" method="post">
        UserName:<input type="text" name="username"/><br/>
        Password:<input type="password" name="password"/><br/>
        <input type="submit" value="提交"/>
    </form>

    <font color="red">${error}</font>
</body>
</html>
