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
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="index.html">Quản lí nhan vien</a></li>
                </ol>
                <div class="card mb-4 container">
                    <form action="/nhan-vien/update" method="post">
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
                            <label class="form-label">Sdt</label>
                            <input type="text" class="form-control" name="sdt" value="${ms.sdt}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Gioi tinh</label>
                            <input type="radio" class="form-check-input" name="gioiTinh" value="Nam"
                                   <c:if test="${ms.gioiTinh == 'Nam'}">checked</c:if>
                            >
                            <label class="form-check-label">Nam</label>
                            <input type="radio" class="form-check-input" name="gioiTinh" value="Nữ"
                                   <c:if test="${ms.gioiTinh == 'Nữ'}">checked</c:if>
                            >
                            <label class="form-check-label">Nu</label>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Ngay sinh</label>
                            <input type="date" class="form-control" name="ngaySinh" value="${ms.ngaySinh}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Dia chi</label>
                            <input type="text" class="form-control" name="diaChi" value="${ms.diaChi}">
                        </div>
                        <div class="col-6">
                            <label class="form-label">Cua hang</label>
                            <select name="cuaHang">
                                <option readonly="">Chọn cua hang</option>
                                <c:forEach items="${listCH}" var="x">
                                    <option value="${x.id}"
                                            <c:if test="${ms.cuaHang.id == x.id}">selected</c:if>
                                    >${x.ten}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-6">
                            <label class="form-label">Chuc vu</label>
                            <select name="chucVu">
                                <option readonly="">Chọn chuc vu</option>
                                <c:forEach items="${listCV}" var="x">
                                    <option value="${x.id}"
                                            <c:if test="${ms.chucVu.id == x.id}">selected</c:if>
                                    >${x.ten}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Trạng thái</label>
                            <input type="radio" class="form-check-input" name="trangThai" value="0"
                                   <c:if test="${ms.trangThai == 0}">checked</c:if>
                            >
                            <label class="form-check-label">Active</label>
                            <input type="radio" class="form-check-input" name="trangThai" value="1"
                                   <c:if test="${ms.trangThai == 1}">checked</c:if>
                            >
                            <label class="form-check-label">Inactive</label>
                        </div>
                        <button class="btn btn-success mb-4">Update</button>
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
