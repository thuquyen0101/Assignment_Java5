<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <a class="nav-link" href="index.html">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Thống kê
                </a>
                <a class="nav-link collapsed" href="#">
                    <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                    Bán hàng tại quầy
                </a>
                <a class="nav-link collapsed" href="#">
                    <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                    Quản lý đơn hàng
                </a>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                    <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                    Quản lý sản phẩm
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                        <a class="nav-link collapsed" href="/mau-sac/hien-thi">
                            Màu sắc
                        </a>
                        <a class="nav-link collapsed" href="/dong-sp/hien-thi">
                            Dòng sản phẩm
                        </a>
                        <a class="nav-link collapsed" href="/nsx/hien-thi">
                            Nhà sản xuất
                        </a>
                    </nav>
                </div>
                <a class="nav-link " href="tables.html">
                    <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                    Tài khoản
                </a>
            </div>
        </div>
    </nav>
</div>