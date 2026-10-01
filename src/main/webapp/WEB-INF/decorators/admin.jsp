<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin - <sitemesh:write property="title"/></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <sitemesh:write property="head"/>
</head>
<body class="admin-body">
<header class="admin-topbar">
    <div class="container admin-topbar-inner">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/home"><span class="brand-mark">A06</span><span>QUẢN TRỊ 24162095</span></a>
        <span class="admin-user">Quản trị viên: <strong><c:out value="${sessionScope.account.fullname}"/></strong></span>
    </div>
</header>
<div class="admin-shell">
    <aside class="admin-sidebar">
        <nav class="admin-nav" aria-label="Điều hướng quản trị">
            <div class="admin-nav-label">Menu quản trị</div>
            <a href="${pageContext.request.contextPath}/admin/home"><span class="nav-symbol">DB</span> Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin/users"><span class="nav-symbol">US</span> Quản lý User</a>
            <a href="${pageContext.request.contextPath}/admin/categories"><span class="nav-symbol">CT</span> Quản lý Category</a>
            <a href="${pageContext.request.contextPath}/home"><span class="nav-symbol">HM</span> Trang chủ</a>
            <a href="${pageContext.request.contextPath}/logout"><span class="nav-symbol">EX</span> Đăng xuất</a>
        </nav>
    </aside>
    <main class="admin-content"><div class="admin-content-inner"><sitemesh:write property="body"/></div></main>
    <footer class="admin-footer">Nguyễn Hoàng Phúc &nbsp;|&nbsp; MSSV: 24162095 &nbsp;|&nbsp; Mã đề: 06</footer>
</div>
</body>
</html>
