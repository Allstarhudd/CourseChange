import java.sql.*;
import java.sql.*;     // Import JDBC classes

public class StudentDBApp {

    // JDBC URL constructed from:
    // Host = localhost
    // Port = 3306
    // Database name = LabAdvanced
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/LabAdvanced";

    // MySQL username
    private static final String USER = "root";

    // MySQL password
    private static final String PASSWORD = "setufree";

    public static void main(String[] args) {

        // Try-with-resources automatically closes the connection
        try {
            // 1. Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish a connection to the database
            Connection conn = DriverManager.getConnection(
                    DB_URL, USER, PASSWORD);

            // 3. Confirmation message
            System.out.println("Database Connected Successfully");

            // 4. Retrieve and display student records
            displayStudents(conn);

            // 5. Update marks using student ID
            updateStudentMarks(conn, 2, 85);

            // 6. Delete a student using email
            deleteStudent(conn, "eva@jkuat.ac.ke");

            // Close connection explicitly (optional here)
            conn.close();

        } catch (ClassNotFoundException e) {
            // Thrown if JDBC driver is missing
            System.out.println("JDBC Driver not found");
            e.printStackTrace();

        } catch (SQLException e) {
            // Handles all SQL-related errors
            System.out.println("Database error occurred");
            e.printStackTrace();
        }
    }

    // Method to retrieve and display all students
    private static void displayStudents(Connection conn)
            throws SQLException {

        String sql = "SELECT * FROM student";

        // Create statement object
        Statement stmt = conn.createStatement();

        // Execute query and store result
        ResultSet rs = stmt.executeQuery(sql);

        // Print table header
        System.out.printf("%-5s %-20s %-25s %-10s %-5s%n",
                "ID", "NAME", "EMAIL", "COURSE", "MARKS");

        // Loop through result set
        while (rs.next()) {
            System.out.printf("%-5d %-20s %-25s %-10s %-5d%n",
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getInt("marks"));
        }
    }

    // Method to update student marks using ID
    private static void updateStudentMarks(
            Connection conn, int id, int newMarks)
            throws SQLException {

        String sql =
                "UPDATE student SET marks = ? WHERE student_id = ?";

        // PreparedStatement prevents SQL injection
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, newMarks); // New marks
        ps.setInt(2, id);       // Student ID

        ps.executeUpdate();

        System.out.println(
                "Student marks updated successfully");
    }

    // Method to delete student using email
    private static void deleteStudent(
            Connection conn, String email)
            throws SQLException {

        String sql =
                "DELETE FROM student WHERE email = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);

        ps.executeUpdate();

        System.out.println(
                "Student deleted successfully");
    }
}
