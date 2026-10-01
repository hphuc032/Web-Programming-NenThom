<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<title>Đặt hàng thành công</title>

<section class="card checkout-success">
    <div class="success-check">✓</div>
    <p class="eyebrow">Hoàn tất</p>
    <h1>Đặt hàng thành công</h1>
    <p class="text-muted">Cảm ơn bạn. Đơn hàng đã được ghi nhận và đang chờ xác nhận.</p>
    <dl class="success-details">
        <div><dt>Mã đơn</dt><dd>#<c:out value="${order.cartId}"/></dd></div>
        <div><dt>Thanh toán</dt><dd>COD</dd></div>
        <div><dt>Tổng tiền</dt><dd><fmt:formatNumber value="${order.totalAmount}" type="number"/> đ</dd></div>
        <div><dt>Trạng thái</dt><dd><span class="badge badge-primary">Đơn hàng mới</span></dd></div>
    </dl>
    <div class="button-row checkout-success-actions">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/orders">Xem đơn hàng</a>
        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/products">Tiếp tục mua</a>
    </div>
</section>
