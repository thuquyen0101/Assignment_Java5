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
                    <li class="breadcrumb-item"><a href="index.html">Quản lí hóa đơn</a></li>
                </ol>
                <div class="card mb-4">
                    <div class="row p-4">
                        <div class="col-6">
                            <form action="/hoa-don/search">
                                <%--                                <input placeholder="Nhập mã hoặc tên" name="keyword">--%>
                                <button class="btn btn-success">Search</button>
                            </form>
                        </div>
                    </div>
                    <div class="card-body">
                        <%--                       table: id="datatablesSimple"--%>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">ID HD</th>
                                <th scope="col">Ma San Pham</th>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">So luong mua</th>
                                <th scope="col">Màu</th>
                                <th scope="col">Nhà sản xuất</th>
                                <th scope="col">Dòng sản phẩm</th>
                                <th scope="col">Giá</th>
                                <th scope="col">Chức năng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listHDCT}" var="x" varStatus="i">
                                <tr>
                                    <th>${i.index+1}</th>
                                    <td>${x.id.hdId}</td>
                                    <td>${x.chiTietSanPham.id}</td>
                                    <td>${x.chiTietSanPham.sanPham.ten}</td>
                                    <td>${x.soLuong}</td>
                                    <td>${x.chiTietSanPham.mauSac.ten}</td>
                                    <td>${x.chiTietSanPham.nhaSanXuat.ten}</td>
                                    <td>${x.chiTietSanPham.dongSanPham.ten}</td>
                                    <td>${x.donGia}</td>
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
                                           href="/nsx/hien-thi?pageNumber=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage == 0}">
                                    <li class="page-item disabled">
                                        <span class="page-link">Previous</span>
                                    </li>
                                </c:if>
                                <c:forEach begin="0" end="${totalPage -1}" var="x">
                                    <li class="page-item"><a class="page-link"
                                                             href="/nsx/hien-thi?pageNumber=${x}">${x+1}</a></li>
                                </c:forEach>
                                <c:if test="${currentPage < (totalPage -1)}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="/nsx/hien-thi?pageNumber=${currentPage + 1}">Next</a>
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



