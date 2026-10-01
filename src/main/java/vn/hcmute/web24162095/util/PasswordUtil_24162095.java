package vn.hcmute.web24162095.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtil_24162095 {
    private PasswordUtil_24162095() { }
    public static String hash(String plain) { return BCrypt.hashpw(plain, BCrypt.gensalt(12)); }
    public static boolean matches(String plain, String hash) {
        try { return plain != null && hash != null && BCrypt.checkpw(plain, hash); }
        catch (IllegalArgumentException e) { return false; }
    }
}
