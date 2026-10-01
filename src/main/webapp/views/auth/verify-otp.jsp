<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<title>Xác minh OTP</title>
<section class="auth-shell">
    <div class="card auth-card shadow-sm">
        <header class="auth-header text-center"><p class="eyebrow">Bước xác minh</p><h1>Xác minh OTP</h1><p>Nhập mã gồm 6 chữ số đã được gửi tới email của bạn.</p></header>
        <div class="auth-body">
            <div class="alert">Mã OTP có hiệu lực trong <strong>5 phút</strong>. Vui lòng không chia sẻ mã này.</div>
            <c:if test="${not empty error}"><div class="alert alert-danger"><c:out value="${error}"/></div></c:if>
            <form method="post"><div class="form-group"><label class="form-label text-center" for="otp">Mã xác minh</label><input class="form-control otp-input" id="otp" name="otp" inputmode="numeric" pattern="[0-9]{6}" maxlength="6" required autocomplete="one-time-code" placeholder="000000"></div><button class="btn btn-primary btn-lg btn-block mt-4" type="submit">Kích hoạt tài khoản</button></form>
        </div>
    </div>
</section>
