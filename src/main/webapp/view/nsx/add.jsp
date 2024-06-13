<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tables - SB Admin</title>
    <%@include file="../includes/header.jsp" %>
    <style>
        span {
            color: red;
        }
    </style>
</head>
<body class="sb-nav-fixed">
<%@include file="../includes/navbar.jsp" %>

<div id="layoutSidenav">
    <%@include file="../includes/aside.jsp" %>

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Nhà sản xuất</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="index.html">Quản lí sản phẩm</a></li>
                    <li class="breadcrumb-item active">Nhà sản xuất </li>
                </ol>
                <div class="card mb-4 container">
                    <form action="/nsx/add" method="post">
                        <div class="mb-3">
                            <label class="form-label">Mã</label>
                            <input type="text" class="form-control" name="ma" value="${ms.ma}">
                            <c:if test="${not empty errors}">
                                <span>${errors["ma"]}</span>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Tên</label>
                            <input type="text" class="form-control" name="ten" value="${ms.ten}">
                            <c:if test="${not empty errors}">
                                <span>${errors["ten"]}</span>
                            </c:if>
                        </div>
                        <button class="btn btn-success mb-4">Add</button>
                    </form>
                </div>
            </div>
        </main>
        <%@include file="../includes/footer.jsp" %>

    </div>
</div>
<%@include file="../includes/under_footer.jsp" %>
</body>
</html>
