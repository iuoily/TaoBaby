<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/5/12
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="icon" type="image/x-icon" href="${ctx }/static/img/title-icon.jpg"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    <%request.getRequestDispatcher("/admin/login/login.page").forward(request, response);%>--%>
    <%request.getRequestDispatcher("/front/index").forward(request, response);%>
</body>
</html>
