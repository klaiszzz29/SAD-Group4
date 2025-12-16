import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries{
    // SAMPLE QUERY
    public static boolean addPatient(String name, String diagnosis) {
        String sql = "INSERT INTO patients (name, diagnosis) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, diagnosis);
            int rows = pstmt.executeUpdate();

            System.out.println("✅ Patient added successfully!");
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error adding patient:");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addConsultationRecord(String symptoms, String findings, String diagnoses, String prescription, String doctorName) {
        String sql = "insert into consultationRecord(patientSymptoms, doctorFindings, diagnoses, prescriptions, doctorName) values(?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, symptoms);
                ps.setString(2, findings);
                ps.setString(3, diagnoses);
                ps.setString(4, prescription);
                ps.setString(5, doctorName);
                int rows = ps.executeUpdate();

                return rows > 0;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}