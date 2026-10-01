package vn.hcmute.web24162095.util;

import java.security.SecureRandom;

public final class OtpUtil_24162095 {
    private static final SecureRandom RANDOM = new SecureRandom();
    private OtpUtil_24162095() { }
    public static String generate() { return String.format("%06d", RANDOM.nextInt(1_000_000)); }
}
