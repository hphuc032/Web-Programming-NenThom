<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<title>Sản phẩm theo cửa hàng</title>
<header class="page-heading">
    <p class="eyebrow">Danh mục sản phẩm</p>
    <h1>Sản phẩm theo cửa hàng</h1>
    <p>Các sản phẩm được phân nhóm theo đúng SellerID để bạn dễ dàng theo dõi.</p>
</header>
<c:if test="${empty groups}"><div class="card empty-state"><h2>Chưa có sản phẩm</h2><p class="text-muted mb-0">Danh sách sản phẩm hiện đang trống.</p></div></c:if>
<c:forEach items="${groups}" var="group">
    <section class="seller-group">
        <header class="seller-header">
            <h2>Cửa hàng #<c:out value="${group.key.sellerId}"/> - <c:out value="${group.key.sellerName}"/></h2>
            <span class="seller-count">${fn:length(group.value)} sản phẩm</span>
        </header>
        <div class="product-grid">
            <c:forEach items="${group.value}" var="product">
                <article class="product-card">
                    <a class="product-image-wrap" href="${pageContext.request.contextPath}/product/detail?id=${product.productId}">
                        <img src="${pageContext.request.contextPath}/${product.images}" alt="Ảnh ${product.productName}">
                    </a>
                    <div class="card-body">
                        <h3><a href="${pageContext.request.contextPath}/product/detail?id=${product.productId}"><c:out value="${product.productName}"/></a></h3>
                        <div class="product-code">Mã sản phẩm: <strong><c:out value="${product.productCode}"/></strong></div>
                        <ul class="product-meta">
                            <li><span>Danh mục</span><strong><c:out value="${product.categoryName}"/></strong></li>
                            <li><span>Số lượng</span><strong><c:out value="${product.amount}"/></strong></li>
                        </ul>
                        <div class="product-price"><fmt:formatNumber value="${product.price}" type="number"/> đ</div>
                        <a class="btn btn-outline-primary btn-sm mt-3" href="${pageContext.request.contextPath}/product/detail?id=${product.productId}">Xem chi tiết</a>
                    </div>
                </article>
            </c:forEach>
        </div>
    </section>
</c:forEach>
