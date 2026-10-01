<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Quản lý User</title>
<header class="toolbar">
    <div><p class="eyebrow">Quản trị tài khoản</p><h1>Quản lý User</h1><p>Danh sách người dùng và quyền truy cập hệ thống.</p></div>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/user/add">+ Thêm mới User</a>
</header>
<c:if test="${not empty param.message}"><div class="alert alert-success"><c:out value="${param.message}"/></div></c:if>
<div class="card table-card">
    <div class="table-responsive">
        <table class="table table-bordered table-hover table-striped align-middle">
            <thead><tr><th>ID</th><th>Username</th><th>Email</th><th>Họ tên</th><th>Phone</th><th>Role</th><th>Seller</th><th>Trạng thái</th><th>Thao tác</th></tr></thead>
            <tbody>
            <c:forEach items="${pageData.items}" var="u">
                <tr>
                    <td class="cell-id">#${u.userId}</td>
                    <td><strong><c:out value="${u.username}"/></strong></td>
                    <td><c:out value="${u.email}"/></td>
                    <td><c:out value="${u.fullname}"/></td>
                    <td><c:out value="${u.phone}"/></td>
                    <td><span class="badge badge-primary"><c:out value="${u.roleName}"/></span></td>
                    <td><c:out value="${u.sellerName}"/></td>
                    <td><c:choose><c:when test="${u.status}"><span class="badge badge-success">Hoạt động</span></c:when><c:otherwise><span class="badge badge-secondary">Đã khóa</span></c:otherwise></c:choose></td>
                    <td class="cell-actions"><div class="actions"><a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/admin/user/edit?id=${u.userId}">Sửa</a><form method="post" action="${pageContext.request.contextPath}/admin/user/delete" onsubmit="return confirm('Xóa user này?')"><input type="hidden" name="id" value="${u.userId}"><button class="btn btn-danger btn-sm" type="submit">Xóa</button></form></div></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<nav aria-label="Phân trang User">
    <ul class="pagination">
        <c:if test="${pageData.hasPrevious}"><li class="page-item"><a class="page-link" href="?page=${pageData.page-1}&size=${pageData.size}">‹ Trước</a></li></c:if>
        <c:forEach begin="1" end="${pageData.totalPages}" var="p"><li class="page-item ${p==pageData.page?'active':''}"><a class="page-link" href="?page=${p}&size=${pageData.size}">${p}</a></li></c:forEach>
        <c:if test="${pageData.hasNext}"><li class="page-item"><a class="page-link" href="?page=${pageData.page+1}&size=${pageData.size}">Sau ›</a></li></c:if>
    </ul>
</nav>
