<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Quản lý Category</title>
<header class="toolbar">
    <div><p class="eyebrow">Quản trị danh mục</p><h1>Quản lý Category</h1><p>Tổ chức và cập nhật các nhóm sản phẩm trong hệ thống.</p></div>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/category/add">+ Thêm mới Category</a>
</header>
<c:if test="${not empty param.message}"><div class="alert alert-success"><c:out value="${param.message}"/></div></c:if>
<div class="card table-card">
    <div class="table-responsive">
        <table class="table table-bordered table-hover table-striped align-middle">
            <thead><tr><th>ID</th><th>Tên danh mục</th><th>Ảnh</th><th>Đường dẫn ảnh</th><th>Trạng thái</th><th>Thao tác</th></tr></thead>
            <tbody>
            <c:forEach items="${pageData.items}" var="x">
                <tr>
                    <td class="cell-id">#${x.categoryId}</td>
                    <td><strong><c:out value="${x.categoryName}"/></strong></td>
                    <td><c:choose><c:when test="${not empty x.images}"><img class="table-image category-image" src="${pageContext.request.contextPath}/${x.images}" alt="Ảnh ${x.categoryName}"></c:when><c:otherwise><span class="badge badge-secondary">Không có ảnh</span></c:otherwise></c:choose></td>
                    <td class="text-muted"><c:out value="${x.images}"/></td>
                    <td><c:choose><c:when test="${x.status}"><span class="badge badge-success">Hoạt động</span></c:when><c:otherwise><span class="badge badge-secondary">Đang ẩn</span></c:otherwise></c:choose></td>
                    <td class="cell-actions"><div class="actions"><a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/admin/category/edit?id=${x.categoryId}">Sửa</a><form method="post" action="${pageContext.request.contextPath}/admin/category/delete" onsubmit="return confirm('Xóa category này?')"><input type="hidden" name="id" value="${x.categoryId}"><button class="btn btn-danger btn-sm" type="submit">Xóa</button></form></div></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<nav aria-label="Phân trang Category">
    <ul class="pagination">
        <c:if test="${pageData.hasPrevious}"><li class="page-item"><a class="page-link" href="?page=${pageData.page-1}&size=${pageData.size}">‹ Trước</a></li></c:if>
        <c:forEach begin="1" end="${pageData.totalPages}" var="p"><li class="page-item ${p==pageData.page?'active':''}"><a class="page-link" href="?page=${p}&size=${pageData.size}">${p}</a></li></c:forEach>
        <c:if test="${pageData.hasNext}"><li class="page-item"><a class="page-link" href="?page=${pageData.page+1}&size=${pageData.size}">Sau ›</a></li></c:if>
    </ul>
</nav>
