<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<title>Giỏ hàng</title>

<header class="page-heading">
    <p class="eyebrow">Mua sắm</p>
    <h1>Giỏ hàng của bạn</h1>
    <p>Kiểm tra sản phẩm và số lượng trước khi thanh toán COD.</p>
</header>

<c:if test="${not empty cartFlashMessage}">
    <div class="alert alert-${cartFlashType}" role="alert"><c:out value="${cartFlashMessage}"/></div>
</c:if>

<c:choose>
    <c:when test="${empty items}">
        <section class="card empty-state">
            <h2>Giỏ hàng đang trống</h2>
            <p class="text-muted">Hãy chọn một sản phẩm phù hợp để bắt đầu mua sắm.</p>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/products">Xem sản phẩm</a>
        </section>
    </c:when>
    <c:otherwise>
        <div class="cart-layout">
            <section class="card cart-items-card">
                <div class="cart-card-header">
                    <h2>Sản phẩm (${totalQuantity})</h2>
                    <form method="post" action="${pageContext.request.contextPath}/cart/clear" onsubmit="return confirm('Xóa toàn bộ giỏ hàng?')">
                        <button class="btn btn-outline-danger btn-sm" type="submit">Xóa tất cả</button>
                    </form>
                </div>
                <div class="cart-item-list">
                    <c:forEach items="${items}" var="item">
                        <article class="cart-item">
                            <a class="cart-item-image" href="${pageContext.request.contextPath}/product/detail?id=${item.productId}">
                                <img src="${pageContext.request.contextPath}/${item.productImage}" alt="Ảnh ${item.productName}">
                            </a>
                            <div class="cart-item-info">
                                <h3><a href="${pageContext.request.contextPath}/product/detail?id=${item.productId}"><c:out value="${item.productName}"/></a></h3>
                                <div class="text-muted">Còn <c:out value="${item.availableStock}"/> sản phẩm</div>
                                <div class="cart-unit-price"><fmt:formatNumber value="${item.unitPrice}" type="number"/> đ</div>
                            </div>
                            <div class="cart-quantity-block">
                                <div class="quantity-stepper">
                                    <form method="post" action="${pageContext.request.contextPath}/cart/update">
                                        <input type="hidden" name="productId" value="${item.productId}">
                                        <input type="hidden" name="action" value="decrease">
                                        <button type="submit" aria-label="Giảm số lượng">−</button>
                                    </form>
                                    <form class="quantity-direct" method="post" action="${pageContext.request.contextPath}/cart/update">
                                        <input type="hidden" name="productId" value="${item.productId}">
                                        <input type="number" name="quantity" value="${item.quantity}" min="1" max="${item.availableStock}" aria-label="Số lượng">
                                        <button class="btn btn-outline-primary btn-sm" type="submit">Cập nhật</button>
                                    </form>
                                    <form method="post" action="${pageContext.request.contextPath}/cart/update">
                                        <input type="hidden" name="productId" value="${item.productId}">
                                        <input type="hidden" name="action" value="increase">
                                        <button type="submit" aria-label="Tăng số lượng">+</button>
                                    </form>
                                </div>
                            </div>
                            <div class="cart-subtotal">
                                <span>Thành tiền</span>
                                <strong><fmt:formatNumber value="${item.subtotal}" type="number"/> đ</strong>
                            </div>
                            <form class="cart-remove" method="post" action="${pageContext.request.contextPath}/cart/remove" onsubmit="return confirm('Xóa sản phẩm này khỏi giỏ?')">
                                <input type="hidden" name="productId" value="${item.productId}">
                                <button class="btn btn-danger btn-sm" type="submit">Xóa</button>
                            </form>
                        </article>
                    </c:forEach>
                </div>
            </section>

            <aside class="card cart-summary">
                <h2>Tóm tắt đơn hàng</h2>
                <div class="summary-row"><span>Tổng số lượng</span><strong>${totalQuantity}</strong></div>
                <div class="summary-row summary-total"><span>Tổng cộng</span><strong><fmt:formatNumber value="${total}" type="number"/> đ</strong></div>
                <button class="btn btn-primary btn-block" type="button" disabled>Thanh toán COD</button>
                <p class="checkout-note">Chức năng thanh toán được triển khai ở bước COD tiếp theo.</p>
                <a class="btn btn-outline-primary btn-block" href="${pageContext.request.contextPath}/products">Tiếp tục mua hàng</a>
            </aside>
        </div>
    </c:otherwise>
</c:choose>
