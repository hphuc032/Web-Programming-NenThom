<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<title>Chi tiết đơn hàng #${order.cartId}</title>

<div class="order-detail-heading">
    <div>
        <p class="eyebrow">Chi tiết đơn hàng</p>
        <h1>Đơn hàng #<c:out value="${order.cartId}"/></h1>
        <p class="text-muted">Đặt lúc <c:out value="${order.formattedBuyDate}"/></p>
    </div>
    <span class="badge badge-${order.statusBadgeClass} order-status-badge"><c:out value="${order.statusLabel}"/></span>
</div>

<div class="order-detail-layout">
    <section class="card order-products-card">
        <div class="cart-card-header"><h2>Sản phẩm</h2><span>${fn:length(items)} mặt hàng</span></div>
        <div class="order-product-list">
            <c:forEach items="${items}" var="item">
                <article class="order-product">
                    <img src="${pageContext.request.contextPath}/${item.productImage}" alt="Ảnh ${item.productName}">
                    <div><h3><c:out value="${item.productName}"/></h3><span><fmt:formatNumber value="${item.unitPrice}" type="number"/> đ × ${item.quantity}</span></div>
                    <strong><fmt:formatNumber value="${item.subtotal}" type="number"/> đ</strong>
                </article>
            </c:forEach>
        </div>
        <div class="order-grand-total"><span>Tổng thanh toán</span><strong><fmt:formatNumber value="${order.totalAmount}" type="number"/> đ</strong></div>
    </section>

    <aside class="card delivery-card">
        <h2>Thông tin nhận hàng</h2>
        <dl>
            <div><dt>Người nhận</dt><dd><c:out value="${order.receiverName}"/></dd></div>
            <div><dt>Điện thoại</dt><dd><c:out value="${order.receiverPhone}"/></dd></div>
            <div><dt>Địa chỉ</dt><dd><c:out value="${order.shippingAddress}"/></dd></div>
            <div><dt>Thanh toán</dt><dd>COD</dd></div>
            <c:if test="${not empty order.note}"><div><dt>Ghi chú</dt><dd><c:out value="${order.note}"/></dd></div></c:if>
        </dl>
        <a class="btn btn-secondary btn-block" href="${pageContext.request.contextPath}/orders">Quay lại lịch sử</a>
    </aside>
</div>
