<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<title>Thanh toán COD</title>

<header class="page-heading">
    <p class="eyebrow">Thanh toán</p>
    <h1>Thanh toán khi nhận hàng</h1>
    <p>Vui lòng kiểm tra đơn hàng và nhập chính xác thông tin người nhận.</p>
</header>

<c:if test="${not empty error}"><div class="alert alert-danger" role="alert"><c:out value="${error}"/></div></c:if>

<div class="checkout-layout">
    <section class="card checkout-form-card">
        <div class="cart-card-header"><h2>Thông tin giao hàng</h2><span class="badge badge-primary">COD</span></div>
        <form class="checkout-form" method="post" action="${pageContext.request.contextPath}/checkout">
            <div class="form-group">
                <label class="form-label" for="receiverName">Họ tên người nhận <span class="required">*</span></label>
                <input class="form-control" id="receiverName" name="receiverName" maxlength="160" value="<c:out value='${receiverName}'/>" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="receiverPhone">Số điện thoại <span class="required">*</span></label>
                <input class="form-control" id="receiverPhone" name="receiverPhone" maxlength="30" value="<c:out value='${receiverPhone}'/>" placeholder="0901234567" required>
            </div>
            <div class="form-group full">
                <label class="form-label" for="shippingAddress">Địa chỉ giao hàng <span class="required">*</span></label>
                <textarea class="form-control" id="shippingAddress" name="shippingAddress" maxlength="500" required><c:out value="${shippingAddress}"/></textarea>
            </div>
            <div class="form-group full">
                <label class="form-label" for="note">Ghi chú (không bắt buộc)</label>
                <textarea class="form-control" id="note" name="note" maxlength="500"><c:out value="${note}"/></textarea>
            </div>
            <div class="payment-method-box">
                <span class="payment-icon">COD</span>
                <div><strong>Thanh toán khi nhận hàng</strong><p>Thanh toán tiền mặt cho nhân viên giao hàng.</p></div>
            </div>
            <div class="form-actions">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/cart">Quay lại giỏ hàng</a>
                <button class="btn btn-primary" type="submit">Xác nhận đặt hàng</button>
            </div>
        </form>
    </section>

    <aside class="card checkout-order-card">
        <h2>Đơn hàng (${totalQuantity})</h2>
        <div class="checkout-products">
            <c:forEach items="${items}" var="item">
                <div class="checkout-product">
                    <img src="${pageContext.request.contextPath}/${item.productImage}" alt="Ảnh ${item.productName}">
                    <div><strong><c:out value="${item.productName}"/></strong><span>${item.quantity} × <fmt:formatNumber value="${item.unitPrice}" type="number"/> đ</span></div>
                    <b><fmt:formatNumber value="${item.subtotal}" type="number"/> đ</b>
                </div>
            </c:forEach>
        </div>
        <div class="summary-row summary-total"><span>Tổng thanh toán</span><strong><fmt:formatNumber value="${total}" type="number"/> đ</strong></div>
    </aside>
</div>
