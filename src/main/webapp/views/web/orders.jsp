<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<title>Đơn hàng của tôi</title>

<header class="page-heading">
    <p class="eyebrow">Tài khoản</p>
    <h1>Đơn hàng của tôi</h1>
    <p>Theo dõi lịch sử mua hàng và trạng thái xử lý hiện tại.</p>
</header>

<nav class="order-filters" aria-label="Lọc trạng thái đơn hàng">
    <a class="filter-pill ${empty selectedStatus ? 'active' : ''}" href="${pageContext.request.contextPath}/orders">Tất cả</a>
    <c:forEach items="${statusOptions}" var="option">
        <a class="filter-pill ${selectedStatus eq option.code ? 'active' : ''}"
           href="${pageContext.request.contextPath}/orders?status=${option.code}"><c:out value="${option.label}"/></a>
    </c:forEach>
</nav>

<c:choose>
    <c:when test="${empty orders}">
        <section class="card empty-state">
            <h2>Không có đơn hàng phù hợp</h2>
            <p class="text-muted">Bạn chưa có đơn hàng ở trạng thái đang chọn.</p>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/products">Tiếp tục mua sắm</a>
        </section>
    </c:when>
    <c:otherwise>
        <section class="card table-card">
            <div class="table-responsive">
                <table class="table table-striped table-hover order-table">
                    <thead><tr><th>Mã đơn</th><th>Ngày đặt</th><th>Thanh toán</th><th>Tổng tiền</th><th>Trạng thái</th><th>Thao tác</th></tr></thead>
                    <tbody>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td><strong>#<c:out value="${order.cartId}"/></strong></td>
                            <td><c:out value="${order.formattedBuyDate}"/></td>
                            <td><span class="badge badge-secondary"><c:out value="${order.paymentMethod}"/></span></td>
                            <td class="order-total"><fmt:formatNumber value="${order.totalAmount}" type="number"/> đ</td>
                            <td><span class="badge badge-${order.statusBadgeClass}"><c:out value="${order.statusLabel}"/></span></td>
                            <td><a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/order/detail?id=${order.cartId}">Xem chi tiết</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
    </c:otherwise>
</c:choose>
