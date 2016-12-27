<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC 修改博客</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h1>SpringMVC 修改博客</h1>
    <hr/>
    <form:form action="${pageContext.request.contextPath}/admin/student/updateP" method="post" commandName="student" role="form">
        <div class="form-group">
            <label for="title">Name:</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Enter Name:" value="${stu.name}"/>
        </div>
      
        <div class="form-group">
            <label for="content">Age:</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="Enter Age:" value="${stu.age}"/>
        </div>
        <div class="form-group">
            <label for="birthday">Birth Date:</label>
            <input type="text" class="form-control" id="birthday" name="birthday"
                   value="<fmt:formatDate value="${stu.birthday}" pattern="yyyy-MM-dd"/>"/>
        </div>
        <!-- 把 id 一并写入 blogP 中 -->
        <input type="hidden" id="id" name="id" value="${stu.id}"/>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>