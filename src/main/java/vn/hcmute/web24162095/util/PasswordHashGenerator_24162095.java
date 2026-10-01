package vn.hcmute.web24162095.util;

public final class PasswordHashGenerator_24162095 {
    private PasswordHashGenerator_24162095() { }
    public static void main(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("Truyền đúng một mật khẩu cần băm.");
        System.out.println(PasswordUtil_24162095.hash(args[0]));
    }
}
