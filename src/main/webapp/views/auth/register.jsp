<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Đăng ký tài khoản</title>
<section class="auth-shell">
    <div class="card auth-card auth-card-wide shadow-sm">
        <header class="auth-header"><p class="eyebrow">Tài khoản mới</p><h1>Đăng ký tài khoản</h1><p>Hoàn thành thông tin bên dưới để nhận mã xác minh OTP qua email.</p></header>
        <div class="auth-body">
            <c:if test="${not empty error}"><div class="alert alert-danger"><c:out value="${error}"/></div></c:if>
            <form method="post">
                <div class="form-grid">
                    <div class="form-group"><label class="form-label" for="username">Username <span class="required">*</span></label><input class="form-control" id="username" name="username" value="<c:out value='${param.username}'/>" required autocomplete="username"></div>
                    <div class="form-group"><label class="form-label" for="email">Email <span class="required">*</span></label><input class="form-control" id="email" type="email" name="email" value="<c:out value='${param.email}'/>" required autocomplete="email"></div>
                    <div class="form-group"><label class="form-label" for="fullname">Họ và tên <span class="required">*</span></label><input class="form-control" id="fullname" name="fullname" value="<c:out value='${param.fullname}'/>" required autocomplete="name"></div>
                    <div class="form-group"><label class="form-label" for="phone">Điện thoại</label><input class="form-control" id="phone" name="phone" value="<c:out value='${param.phone}'/>" autocomplete="tel"></div>
                    <div class="form-group"><label class="form-label" for="password">Mật khẩu <span class="required">*</span></label><input class="form-control" id="password" type="password" name="password" minlength="6" required autocomplete="new-password"></div>
                    <div class="form-group"><label class="form-label" for="confirmPassword">Xác nhận mật khẩu <span class="required">*</span></label><input class="form-control" id="confirmPassword" type="password" name="confirmPassword" minlength="6" required autocomplete="new-password"></div>
                </div>
                <div class="form-actions"><button class="btn btn-primary" type="submit">Gửi mã OTP</button><a class="btn btn-secondary" href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a></div>
            </form>
        </div>
    </div>
</section>
