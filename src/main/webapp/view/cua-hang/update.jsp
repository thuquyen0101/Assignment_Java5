<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tables - SB Admin</title>
    <%@include file="../includes/header.jsp" %>

</head>
<body class="sb-nav-fixed">
<%@include file="../includes/navbar.jsp" %>

<div id="layoutSidenav">
    <%@include file="../includes/aside.jsp" %>

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="index.html">Quản lí cua hang</a></li>
                </ol>
                <div class="card mb-4 container">
                    <form action="/cua-hang/update" method="post">
                        <input type="text" hidden="hidden" class="form-control" name="id" value="${ms.id}">
                        <div class="mb-3">
                            <label class="form-label">Mã</label>
                            <input type="text" class="form-control" name="ma" value="${ms.ma}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Tên</label>
                            <input type="text" class="form-control" name="ten" value="${ms.ten}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Dia chi</label>
                            <input type="text" class="form-control" name="diaChi" value="${ms.diaChi}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Thanh pho</label>
                            <input type="text" class="form-control" name="thanhPho" value="${ms.thanhPho}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Quoc gia</label>
                            <input type="text" class="form-control" name="quocGia" value="${ms.quocGia}">
                        </div>
                        <button class="btn btn-success mb-3">Update</button>
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
