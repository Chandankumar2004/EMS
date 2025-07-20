package ems.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;

public class DBConnection {

    private static String URL = "jdbc:mysql://localhost:3306/ems_db?allowPublicKeyRetrieval=true&useSSL=false";
    private static String USER = "root";
    private static String PASSWORD = "root";

    static {
        try {
            File configFile = new File("config.properties");
            if (configFile.exists()) {
                Properties props = new Properties();
                props.load(new FileInputStream(configFile));

                String dbUrl = props.getProperty("db.url");
                String dbUser = props.getProperty("db.user");
                String dbPass = props.getProperty("db.password");

                if (dbUrl != null) URL = dbUrl.trim();
                if (dbUser != null) USER = dbUser.trim();
                if (dbPass != null) PASSWORD = dbPass.trim();

                System.out.println("✅ Loaded DB credentials from config.properties");
            } else {
                System.out.println("⚠ config.properties not found, using default DB credentials.");
            }

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Failed to load DB configuration.");
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 🚀 Utility to renumber employee IDs sequentially
    public static void renumberEmployeeIds() {
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();

            stmt.executeUpdate("SET @count = 0");
            stmt.executeUpdate("UPDATE employees SET id = (@count := @count + 1) ORDER BY id");
            stmt.executeUpdate("ALTER TABLE employees AUTO_INCREMENT = 1");

            System.out.println("✅ Employee IDs renumbered successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Failed to renumber employee IDs.");
        }
    }
}
