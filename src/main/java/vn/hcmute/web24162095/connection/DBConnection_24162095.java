package vn.hcmute.web24162095.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DBConnection_24162095 {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = DBConnection_24162095.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input != null) PROPERTIES.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (IOException | ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private DBConnection_24162095() { }

    public static Connection getConnection() throws SQLException {
        String url = value("DB_URL", "db.url", "jdbc:mysql://localhost:3306/web24162095?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Ho_Chi_Minh&allowPublicKeyRetrieval=true&useSSL=false");
        String user = value("DB_USERNAME", "db.username", "root");
        String password = value("DB_PASSWORD", "db.password", "");
        return DriverManager.getConnection(url, user, password);
    }

    private static String value(String env, String key, String fallback) {
        String envValue = System.getenv(env);
        if (envValue != null && !envValue.isBlank()) return envValue;
        return PROPERTIES.getProperty(key, fallback);
    }
}
