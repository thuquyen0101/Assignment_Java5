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
                <h1 class="mt-4">Bán hàng tại quầy</h1>
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="row mt-5">
                            <div class="col-7">
                                <h2>Danh sách hoá đơn</h2>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <td>STT</td>
                                        <td>Ma</td>
                                        <td>Ten khach</td>
                                        <td>Ngay tao</td>
                                        <td>Tong tien</td>
                                        <td>Trang Thai</td>
                                        <td>Chuc nang</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listHD}" var="x" varStatus="i">
                                        <tr>
                                            <td>${i.index+1}</td>
                                            <td>${x.ma}</td>
                                            <td>${x.khachHang.ten}</td>
                                            <td>${x.ngayTao}</td>
                                            <td>0</td>
                                            <td>
                                                <c:if test="${x.tinhTrang == 0}">Chua thanh toan</c:if>
                                                <c:if test="${x.tinhTrang == 1}">Da thanh toan</c:if>
                                            </td>
                                            <td>
                                                <a href="/ban-hang/hoa-don?idHD=${x.id}"
                                                   class="btn btn-primary">Chon</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-5">
                                <h2>Tạo hoá đơn</h2>

                                <div class="row">
                                    <form action="/ban-hang/search-kh" method="post">
                                        <div>
                                            <div>
                                                <label class="mb-3 col-3">Sdt khách hàng</label>
                                                <input type="text" class="col-7 tenKH" name="sdt" value="${kh.sdt}">
                                            </div>
                                            <button class="btn btn-primary">Search</button>
                                            <div class="mb-3">
                                                <label class="col-3">Ten Khach hang</label>
                                                <input type="text" class="col-7" readonly
                                                       value="${kh.ho} ${kh.tenDem} ${kh.ten} ">
                                            </div>
                                        </div>
                                    </form>

                                    <form method="post" action="/ban-hang/create-hoa-don">
                                        <input hidden="hidden" value="${kh.sdt}" name="sdtKH">
                                        <input hidden="hidden" type="text" class="col-7" name="idKH" value="${kh.id}">
                                        <div class="mb-3">
                                            <label class="col-3">ID Hoa don</label>
                                            <input type="text" class="col-7" readonly
                                            <%--                                              idHoaDon     --%>
                                                   value="${hoaDon.id}">
                                        </div>
                                        <div class="mb-3">
                                            <label class="col-3">Mã khách hàng</label>
                                            <input type="text" class="col-7" readonly value="${hoaDon.khachHang.ma}">
                                        </div>
                                        <div class="mb-3">
                                            <label class="col-3">Tong tien</label>
                                            <input type="text" class="col-7" readonly name="tongTien" id="tongTien"
                                                   value="${tongTien}">
                                        </div>
                                        <div>
                                            <button class="btn btn-primary ">Tạo hoá đơn</button>
                                        </div>
                                        <a class="btn btn-primary mt-3" href="/ban-hang/thanh-toan?idHD=${hoaDon.id}"
                                           onclick="return confirm('Ban chac chan muon thanh toan?')"
                                        >Thanh toán
                                        </a>
                                    </form>

                                </div>

                            </div>
                        </div>
                        <div class="row mt-5">
                            <div class="row">
                                <h2 class="col-7">Danh sách hoá đơn chi tiết</h2>
                                <form action="/ban-hang/view-chon-spct">
                                    <input type="text" class="col-7" name="idHoaDon" hidden="hidden"
                                           value="${hoaDon.id}">
                                    <button class="btn btn-primary">Them san pham</button>
                                </form>
                            </div>
                            <table class="table">
                                <thead>
                                <tr>
                                    <td>STT</td>
                                    <td>ID HD</td>
                                    <td>Ten san pham</td>
                                    <td>So luong</td>
                                    <td>Gia ban</td>
                                    <td>Thành tien</td>
                                    <td>Chuc nang</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listHDCT}" var="x" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}</td>
                                        <td>${x.hoaDon.id}</td>
                                        <td>${x.id.spctId}</td>
                                        <td><input type="number" min="0" max="${soLuongTon}" id="quantity" onchange="changeQuantity()"
                                                   name="soLuongSP"
                                                   value="${x.soLuong}"></td>
                                        <td id="gia">${x.donGia}</td>
                                        <td id="kq">${x.getThanhTien()}</td>
                                        <td>
                                            <a class="btn btn-primary"
                                               href="/ban-hang/remove-from-hdct?idHD=${x.hoaDon.id}&idSPCT=${x.id.spctId}"
                                               onclick="return confirm('Ban chac chan muon bo sp nay?')">Chon
                                                xoa</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </main>
        <%@include file="../includes/footer.jsp" %>
    </div>
</div>
<%@include file="../includes/under_footer.jsp" %>
<script>
    function changeQuantity() {
        var quantity = document.getElementById('quantity');
        var kq = document.getElementById('kq')
        var gia = document.getElementById('gia').textContent;

        var abc = quantity.value * gia;
        kq.innerText = abc;

        var tongTien = document.getElementById('tongTien').value;
        tongTien += abc;
        console.log(quantity.value, kq, gia, abc, tongTien);
    }
</script>
</body>
</html>



