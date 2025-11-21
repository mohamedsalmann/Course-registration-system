import java.sql.*;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        // ✅ Database connection details
        String url = "jdbc:mysql://localhost:3306/course_db"; // your DB name
        String user = "root";                                 // your MySQL username
        String password = "root123";               // 🔹 replace with your MySQL password

        try {
            // ✅ Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL database successfully!");

            // ✅ Create table if not exists
            String createTable = "CREATE TABLE IF NOT EXISTS registrations ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "student_name VARCHAR(100), "
                    + "course_name VARCHAR(100))";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createTable);
            System.out.println("📋 Table 'registrations' verified/created.");

            // ✅ Insert sample record
            String insertQuery = "INSERT INTO registrations (student_name, course_name) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, "Priya");
            pstmt.setString(2, "Java Basics");
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("🎉 Sample record inserted successfully!");
            }

            // ✅ Display all data
            String selectQuery = "SELECT * FROM registrations";
            ResultSet rs = stmt.executeQuery(selectQuery);

            System.out.println("\n📘 Current Registrations:");
            boolean empty = true;
            while (rs.next()) {
                empty = false;
                System.out.println("ID: " + rs.getInt("id")
                        + " | Student: " + rs.getString("student_name")
                        + " | Course: " + rs.getString("course_name"));
            }
            if (empty) {
                System.out.println("No records found.");
            }

            // ✅ Close connection
            conn.close();
            System.out.println("\n🔒 Connection closed successfully.");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
