<%--
  Created by IntelliJ IDEA.
  User: hx
  Date: 2022/4/30
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>talk</title>
</head>
<body>

<div>
    <form action="${pageContext.request.contextPath}/Servlet" method="post">
        我说：<input type="text" name="my_word" value="" size="20">
        <input type="submit" name="submit" value="发送">

    </form>

</div>

<div>
    <%--机器人--%>
    机器人说：
    <%=request.getAttribute("Answer")%>

</div>

</body>
</html>
