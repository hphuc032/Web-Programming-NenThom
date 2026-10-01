package vn.hcmute.web24162095.util;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class MailUtil_24162095 {
    private MailUtil_24162095() { }

    public static void sendOtp(String recipient, String otp) throws MessagingException, IOException {
        Properties config = new Properties();
        try (InputStream input = MailUtil_24162095.class.getClassLoader().getResourceAsStream("mail.properties")) {
            if (input == null) throw new IllegalStateException("Thiếu mail.properties. Hãy sao chép từ mail.properties.example và nhập SMTP App Password.");
            config.load(input);
        }
        String username = config.getProperty("mail.username");
        String password = config.getProperty("mail.password");
        Properties props = new Properties();
        props.put("mail.smtp.host", config.getProperty("mail.host", "smtp.gmail.com"));
        props.put("mail.smtp.port", config.getProperty("mail.port", "587"));
        props.put("mail.smtp.auth", config.getProperty("mail.auth", "true"));
        props.put("mail.smtp.starttls.enable", config.getProperty("mail.starttls.enable", "true"));
        Session session = Session.getInstance(props, new Authenticator() {
            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(config.getProperty("mail.from", username), "Web 24162095", StandardCharsets.UTF_8.name()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("Mã OTP kích hoạt tài khoản", StandardCharsets.UTF_8.name());
        message.setText("Mã OTP của bạn là: " + otp + "\nMã có hiệu lực trong 5 phút.", StandardCharsets.UTF_8.name());
        Transport.send(message);
    }
}
