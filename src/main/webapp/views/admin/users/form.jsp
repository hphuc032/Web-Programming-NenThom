<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>${empty user ? 'Thêm' : 'Sửa'} User</title>
<div class="card form-card">
    <header class="form-card-header"><p class="eyebrow">Quản lý User</p><h1>${empty user ? 'Thêm mới' : 'Cập nhật'} User</h1><p>Nhập đầy đủ các trường bắt buộc trước khi lưu.</p></header>
    <div class="form-card-body">
        <c:if test="${not empty error}"><div class="alert alert-danger"><c:out value="${error}"/></div></c:if>
        <form method="post">
            <c:if test="${not empty user}"><input type="hidden" name="id" value="${user.userId}"></c:if>
            <div class="form-grid">
                <div class="form-group"><label class="form-label" for="username">Username <span class="required">*</span></label><input class="form-control" id="username" name="username" value="<c:out value='${user.username}'/>" required></div>
                <div class="form-group"><label class="form-label" for="email">Email <span class="required">*</span></label><input class="form-control" id="email" type="email" name="email" value="<c:out value='${user.email}'/>" required></div>
                <div class="form-group"><label class="form-label" for="fullname">Họ tên <span class="required">*</span></label><input class="form-control" id="fullname" name="fullname" value="<c:out value='${user.fullname}'/>" required></div>
                <div class="form-group"><label class="form-label" for="phone">Điện thoại</label><input class="form-control" id="phone" name="phone" value="<c:out value='${user.phone}'/>"></div>
                <div class="form-group full"><label class="form-label" for="password">Mật khẩu ${empty user ? '*' : '(để trống nếu giữ nguyên)'}</label><input class="form-control" id="password" type="password" name="password" ${empty user?'required':''}></div>
                <div class="form-group full"><label class="form-label" for="images">Ảnh URL/path</label><input class="form-control" id="images" name="images" value="<c:out value='${user.images}'/>" placeholder="assets/images/... hoặc URL ảnh"></div>
                <div class="form-group"><label class="form-label" for="roleId">Vai trò</label><select class="form-select" id="roleId" name="roleId"><option value="2" ${user.roleId==2?'selected':''}>USER</option><option value="1" ${user.roleId==1?'selected':''}>ADMIN</option></select></div>
                <div class="form-group"><label class="form-label" for="sellerId">Seller (không bắt buộc)</label><select class="form-select" id="sellerId" name="sellerId"><option value="">-- Không liên kết --</option><c:forEach items="${sellers}" var="s"><option value="${s.sellerId}" ${user.sellerId==s.sellerId?'selected':''}>#${s.sellerId} - <c:out value="${s.sellerName}"/></option></c:forEach></select></div>
                <div class="form-group full"><div class="form-check"><input class="form-check-input" id="status" type="checkbox" name="status" value="true" ${empty user || user.status?'checked':''}><label class="form-check-label" for="status">Tài khoản đang hoạt động</label></div></div>
            </div>
            <div class="form-actions"><button class="btn btn-primary" type="submit">Lưu thông tin</button><a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/users">Hủy</a></div>
        </form>
    </div>
</div>
