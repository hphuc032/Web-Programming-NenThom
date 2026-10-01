<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<title>Chi tiết ${product.productName}</title>
<div class="page-heading"><p class="eyebrow">Thông tin sản phẩm</p><h1>Chi tiết sản phẩm</h1></div>
<article class="product-detail">
    <div class="detail-grid">
        <div class="detail-media"><img src="${pageContext.request.contextPath}/${product.images}" alt="Ảnh ${product.productName}"></div>
        <div class="detail-content">
            <span class="badge badge-primary"><c:out value="${product.categoryName}"/></span>
            <h1 class="mt-3"><c:out value="${product.productName}"/></h1>
            <div class="detail-price"><fmt:formatNumber value="${product.price}" type="number"/> đ</div>
            <dl class="detail-list">
                <div class="detail-row"><dt>Mã sản phẩm</dt><dd><c:out value="${product.productCode}"/></dd></div>
                <div class="detail-row"><dt>Cửa hàng</dt><dd>#<c:out value="${product.sellerId}"/> - <c:out value="${product.sellerName}"/></dd></div>
                <div class="detail-row"><dt>Danh mục</dt><dd><c:out value="${product.categoryName}"/></dd></div>
                <div class="detail-row"><dt>Amount</dt><dd><c:out value="${product.amount}"/> sản phẩm</dd></div>
            </dl>
            <div class="detail-description"><h2>Mô tả sản phẩm</h2><p class="text-muted"><c:out value="${product.description}"/></p></div>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/products">← Quay lại danh sách</a>
        </div>
    </div>
</article>
