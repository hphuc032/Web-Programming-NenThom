<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Trang Seller</title>
<section class="card dashboard-welcome">
    <p class="eyebrow">Khu vực cửa hàng</p>
    <h1>Xin chào Seller</h1>
    <p>Đăng nhập thành công với tài khoản liên kết cửa hàng ID <strong>#<c:out value="${sessionScope.account.sellerId}"/></strong>.</p>
    <div class="button-row mt-4"><a class="btn btn-primary" href="${pageContext.request.contextPath}/products">Xem danh sách sản phẩm</a><a class="btn btn-secondary" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></div>
</section>
