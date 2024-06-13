<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tables - SB Admin</title>
    <%@include file="../includes/header.jsp" %>
</head>
<style>
    span {
        color: red;
    }
</style>
<body class="sb-nav-fixed">
<%@include file="../includes/navbar.jsp" %>
<div id="layoutSidenav">
    <%@include file="../includes/aside.jsp" %>

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="index.html">Quản lí san pham chi tiet</a></li>
                </ol>
                <div class="card mb-4">
                    <form action="/san-pham-ct/add" method="post" class="p-3">
                        <div class="mb-3">
                            <label class="form-label">Nam BH</label>
                            <input type="number" class="form-control" name="namBH" value="${ms.namBH}">
                            <c:if test="${not empty errors}">
                                <span>${errors["namBH"]}</span>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mo ta</label>
                            <input type="text" class="form-control" name="moTa" value="${ms.moTa}">
                            <c:if test="${not empty errors}">
                                <span>${errors["moTa"]}</span>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">So Luong Ton</label>
                            <input type="text" class="form-control" name="soLuongTon" value="${ms.soLuongTon}">
                            <c:if test="${not empty errors}">
                                <span>${errors["soLuongTon"]}</span>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Gia Nhap</label>
                            <input type="text" class="form-control" name="giaNhap" value="${ms.giaNhap}">
                            <c:if test="${not empty errors}">
                                <span>${errors["giaNhap"]}</span>
                            </c:if>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Gia Ban</label>
                            <input type="text" class="form-control" name="giaBan" value="${ms.giaBan}">
                            <c:if test="${not empty errors}">
                                <span>${errors["giaBan"]}</span>
                            </c:if>
                        </div>
                        <div class="row p-4">
                            <div class="col-6">
                                <label class="form-label">Sản phẩm</label>
                                <select name="sanPham" class="p-1">
                                    <option value="" readonly="">Chọn sản phẩm</option>
                                    <c:forEach items="${listSp}" var="x">
                                        <option value="${x.id}">${x.ten}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Dòng sản phẩm</label>
                                <select name="dongSanPham" >
                                    <option readonly="">Chọn dòng sản phẩm</option>
                                    <c:forEach items="${listDsp}" var="x">
                                        <option value="${x.id}">${x.ten}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row p-4">
                            <div class="col-6">
                                <label class="form-label">Màu sắc</label>
                                <select name="mauSac" >
                                    <option readonly="">Chọn màu sắc</option>
                                    <c:forEach items="${listMS}" var="x">
                                        <option value="${x.id}">${x.ten}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Nhà sản xuất</label>
                                <select name="nhaSanXuat" >
                                    <option readonly="">Chọn nhà sản xuất</option>
                                    <c:forEach items="${listNsx}" var="x">
                                        <option value="${x.id}">${x.ten}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <button class="btn btn-success ml-2">Add</button>
                    </form>

                    <div class="card-body">

                    </div>
                </div>
            </div>
        </main>
        <%@include file="../includes/footer.jsp" %>
    </div>
</div>
<%@include file="../includes/under_footer.jsp" %>
</body>
</html>



