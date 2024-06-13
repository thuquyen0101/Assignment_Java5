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
                    <li class="breadcrumb-item"><a href="index.html">Quản lí sản phẩm chi tiet</a></li>
                </ol>
                <div class="card mb-4">
                    <div class="row p-4">
                        <div class="col-6">
                            <a class="btn btn-success ml-5" href="/san-pham-ct/form-add">Thêm mới </a>
                        </div>
                    </div>
                    <div class="card-body">
                        <%--                       table: id="datatablesSimple"--%>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Mã sp</th>
                                <th scope="col">Tên sp</th>
                                <th scope="col">Tên màu</th>
                                <th scope="col">Tên dòng sp</th>
                                <th scope="col">Tên nhà sx</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Gía nhập</th>
                                <th scope="col">Gía bán</th>
                                <th scope="col">Chức năng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listCtsp}" var="x" varStatus="i">
                                <tr>
                                    <th>${i.index+1}</th>
                                    <td>${x.sanPham.ma}</td>
                                    <td>${x.sanPham.ten}</td>
                                    <td>${x.mauSac.ten}</td>
                                    <td>${x.dongSanPham.ten}</td>
                                    <td>${x.nhaSanXuat.ten}</td>
                                    <td>${x.soLuongTon}</td>
                                    <td>${x.giaNhap}</td>
                                    <td>${x.giaBan}</td>
                                    <td>
                                        <a onclick="return confirm('Chắc chắn muốn xóa?')" class="btn btn-success"
                                           href="/san-pham-ct/remove/${x.id}">Remove </a>
                                        <a class="btn btn-success" href="/san-pham-ct/update?id=${x.id}">Update </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:if test="${totalPage > 0}">
                                <c:if test="${currentPage > 0}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="/san-pham-ct/hien-thi?pageNumber=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage == 0}">
                                    <li class="page-item disabled">
                                        <span class="page-link">Previous</span>
                                    </li>
                                </c:if>
                                <c:forEach begin="0" end="${totalPage -1}" var="x">
                                    <li class="page-item"><a class="page-link"
                                                             href="/san-pham-ct/hien-thi?pageNumber=${x}">${x+1}</a></li>
                                </c:forEach>
                                <c:if test="${currentPage < (totalPage -1)}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="/san-pham-ct/hien-thi?pageNumber=${currentPage + 1}">Next</a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage == (totalPage -1)}">
                                    <li class="page-item disabled">
                                        <span class="page-link">Next</span>
                                    </li>
                                </c:if>
                            </c:if>

                        </ul>
                    </nav>
                </div>
            </div>
        </main>
        <%@include file="../includes/footer.jsp" %>
    </div>
</div>
<%@include file="../includes/under_footer.jsp" %>
</body>
</html>



