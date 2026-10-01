<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>${empty category ? 'Thêm' : 'Sửa'} Category</title>
<div class="card form-card">
    <header class="form-card-header"><p class="eyebrow">Quản lý Category</p><h1>${empty category ? 'Thêm mới' : 'Cập nhật'} Category</h1><p>Cập nhật tên, hình ảnh và trạng thái hiển thị của danh mục.</p></header>
    <div class="form-card-body">
        <c:if test="${not empty error}"><div class="alert alert-danger"><c:out value="${error}"/></div></c:if>
        <form method="post">
            <c:if test="${not empty category}"><input type="hidden" name="id" value="${category.categoryId}"></c:if>
            <div class="form-grid">
                <div class="form-group full"><label class="form-label" for="categoryName">Tên danh mục <span class="required">*</span></label><input class="form-control" id="categoryName" name="categoryName" value="<c:out value='${category.categoryName}'/>" required placeholder="Ví dụ: Điện thoại"></div>
                <div class="form-group full"><label class="form-label" for="images">Ảnh URL/path</label><input class="form-control" id="images" name="images" value="<c:out value='${category.images}'/>" placeholder="assets/images/... hoặc URL ảnh"></div>
                <div class="form-group full"><div class="form-check"><input class="form-check-input" id="status" type="checkbox" name="status" value="true" ${empty category || category.status?'checked':''}><label class="form-check-label" for="status">Danh mục đang hoạt động</label></div></div>
            </div>
            <div class="form-actions"><button class="btn btn-primary" type="submit">Lưu thông tin</button><a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/categories">Hủy</a></div>
        </form>
    </div>
</div>
