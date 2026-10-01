<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><sitemesh:write property="title"/> - Shop 24162095</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <sitemesh:write property="head"/>
</head>
<body>
<header class="site-header">
    <nav class="navbar navbar-expand-lg navbar-dark" aria-label="Điều hướng chính">
        <div class="container navbar-inner">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home"><span class="brand-mark">S06</span><span>SHOP 24162095</span></a>
            <ul class="navbar-nav">
                <li><a class="nav-link" href="${pageContext.request.contextPath}/home">Trang Chủ</a></li>
                <li><a class="nav-link" href="${pageContext.request.contextPath}/products">Sản phẩm</a></li>
                <c:choose>
                    <c:when test="${not empty sessionScope.account}">
                        <c:if test="${sessionScope.account.roleName eq 'USER'}">
                            <li><a class="nav-link" href="${pageContext.request.contextPath}/cart">Giỏ hàng</a></li>
                            <li><a class="nav-link" href="${pageContext.request.contextPath}/orders">Đơn hàng của tôi</a></li>
                        </c:if>
                        <li><span class="user-greeting">Xin chào, <c:out value="${sessionScope.account.fullname}"/></span></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                    </c:when>
                    <c:otherwise><li><a class="nav-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a></li></c:otherwise>
                </c:choose>
                <c:if test="${sessionScope.account.roleName eq 'ADMIN'}"><li><a class="nav-link nav-admin" href="${pageContext.request.contextPath}/admin/home">Trang quản trị</a></li></c:if>
            </ul>
        </div>
    </nav>
</header>
<main class="container main-content"><sitemesh:write property="body"/></main>
<footer class="site-footer"><div class="container"><strong>Nguyễn Hoàng Phúc</strong> &nbsp;|&nbsp; MSSV: 24162095 &nbsp;|&nbsp; Mã đề: 06</div></footer>
</body>
</html>
