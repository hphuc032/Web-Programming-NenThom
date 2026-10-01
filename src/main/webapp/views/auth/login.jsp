<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Đăng nhập</title>
<section class="auth-shell">
    <div class="card auth-card shadow-sm">
        <header class="auth-header"><p class="eyebrow">Chào mừng trở lại</p><h1>Đăng nhập</h1><p>Nhập thông tin tài khoản để tiếp tục.</p></header>
        <div class="auth-body">
            <c:if test="${not empty sessionScope.flash}"><div class="alert alert-success"><c:out value="${sessionScope.flash}"/></div><c:remove var="flash" scope="session"/></c:if>
            <c:if test="${not empty error}"><div class="alert alert-danger"><c:out value="${error}"/></div></c:if>
            <form method="post">
                <div class="form-group"><label class="form-label" for="username">Tên đăng nhập</label><input class="form-control" id="username" name="username" required autocomplete="username" placeholder="Nhập username"></div>
                <div class="form-group mt-3"><label class="form-label" for="password">Mật khẩu</label><input class="form-control" id="password" type="password" name="password" required autocomplete="current-password" placeholder="Nhập mật khẩu"></div>
                <button class="btn btn-primary btn-lg mt-4" type="submit">Đăng nhập</button>
            </form>
            <div class="auth-footer">Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register"><strong>Đăng ký ngay</strong></a></div>
        </div>
    </div>
</section>
