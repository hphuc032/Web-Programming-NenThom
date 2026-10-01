<%@ page contentType="text/html;charset=UTF-8" %>
<title>Trang chủ</title>
<section class="hero">
    <div class="hero-content">
        <p class="eyebrow">Đề thi 06 · Lập trình Web</p>
        <h1>Website quản lý sản phẩm theo cửa hàng</h1>
        <p class="lead">Quản lý tài khoản, danh mục và khám phá sản phẩm của từng cửa hàng trên một giao diện rõ ràng, thuận tiện.</p>
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/products">Xem tất cả sản phẩm →</a>
    </div>
</section>
<section class="section-block" aria-labelledby="features-title">
    <div class="section-title"><p class="eyebrow">Chức năng hệ thống</p><h2 id="features-title">Quản lý đơn giản, đầy đủ nghiệp vụ</h2></div>
    <div class="feature-grid">
        <article class="card feature-card"><span class="feature-icon">TK</span><h3>Tài khoản</h3><p>Đăng ký xác minh OTP qua email, đăng nhập và phân quyền an toàn bằng Session.</p></article>
        <article class="card feature-card"><span class="feature-icon">SP</span><h3>Sản phẩm</h3><p>Xem sản phẩm được gom đúng theo SellerID cùng trang thông tin chi tiết dễ theo dõi.</p></article>
        <article class="card feature-card"><span class="feature-icon">QT</span><h3>Quản trị</h3><p>Thêm, sửa, xóa User và Category với phân trang được xử lý trực tiếp tại database.</p></article>
    </div>
</section>
