package vn.hcmute.web24162095.model;

import java.io.Serializable;

public class PendingRegistration_24162095 implements Serializable {
    private final User_24162095 user;
    private final String otp;
    private final long expiresAt;
    public PendingRegistration_24162095(User_24162095 user, String otp, long expiresAt) {
        this.user = user; this.otp = otp; this.expiresAt = expiresAt;
    }
    public User_24162095 getUser() { return user; }
    public boolean verify(String submittedOtp) { return System.currentTimeMillis() <= expiresAt && otp.equals(submittedOtp); }
    public boolean isExpired() { return System.currentTimeMillis() > expiresAt; }
}
