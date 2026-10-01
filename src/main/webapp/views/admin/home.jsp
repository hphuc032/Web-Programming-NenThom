<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Dashboard</title>
<section class="card dashboard-welcome">
    <p class="eyebrow">Tổng quan hệ thống</p>
    <h1>Dashboard quản trị</h1>
    <p>Xin chào <strong><c:out value="${sessionScope.account.fullname}"/></strong>. Khu vực được bảo vệ bởi AuthFilter và AdminFilter.</p>
</section>
<div class="dashboard-grid">
    <a class="card dashboard-card" href="${pageContext.request.contextPath}/admin/users"><span class="feature-icon">US</span><h2>Quản lý User</h2><p>Create, Read, Update, Delete và Pagination cho tài khoản người dùng.</p><strong>Đi tới quản lý →</strong></a>
    <a class="card dashboard-card" href="${pageContext.request.contextPath}/admin/categories"><span class="feature-icon">CT</span><h2>Quản lý Category</h2><p>Create, Read, Update, Delete và Pagination cho danh mục sản phẩm.</p><strong>Đi tới quản lý →</strong></a>
</div>
