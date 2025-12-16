import java.sql.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Queries {
    // SAMPLE QUERY

    public static boolean addConsultationRecord(String symptoms, String findings, String diagnoses, String prescription,
            String severity, String status, String doctorName) {
        String sql = "insert into consultationRecord(patientSymptoms, doctorFindings, diagnoses, prescriptions, severity, status, doctorName) values(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, symptoms);
            ps.setString(2, findings);
            ps.setString(3, diagnoses);
            ps.setString(4, prescription);
            ps.setString(5, severity);
            ps.setString(6, status);
            ps.setString(7, doctorName);
            int rows = ps.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("add consult error");
            return false;
        }
    }

    public static void displayConsultationRecord(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        String sql = "select dateOfVisit, diagnoses, severity, status, totalVisit from consultationRecord";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Date date = rs.getDate("dateOfVisit");
                String diagnoses = rs.getString("diagnoses");
                String severity = rs.getString("severity");
                String status = rs.getString("status");
                int visits = rs.getInt("totalVisit");

                model.addRow(new Object[] { date, diagnoses, severity, status, visits });
            }

        } catch (SQLException e) {
            System.out.println("display consult error");
            e.printStackTrace();
        }
    }
}