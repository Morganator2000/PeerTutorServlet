package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobject.PeerTutor;

/**
 * The implementation for the PeerTutor
 * @author Morgan Bakelmun
 */
public class PeerTutorDAOImpl implements PeerTutorDAO {
            DataSource ds = new DataSource();

    @Override
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        try {Connection con = ds.createConnection();
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM PeerTutor WHERE LastName = ? AND FirstName = ?");
            pstmt.setString(1, peerTutor.getLastName());
            pstmt.setString(2,peerTutor.getFirstName());
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isCourseValid(String courseCode) {
        try {Connection con = ds.createConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM course WHERE CourseCode = ?");
            pstmt.setString(1, courseCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        try {Connection con = ds.createConnection();
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM student st\n" +
                "JOIN studentcourse sc ON  st.StudentID = sc.Student_StudentID\n" +
                "JOIN course c ON sc.Course_CourseCode = c.CourseCode\n" +
                "WHERE LastName = ?\n" +
                "AND FirstName = ?\n" +
                "AND CourseCode = ?");
            pstmt.setString(1, peerTutor.getLastName());
            pstmt.setString(2, peerTutor.getFirstName());
            pstmt.setString(3, courseCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        try {Connection con = ds.createConnection();
                PreparedStatement pstmt = con.prepareStatement("SELECT GradeCode FROM student s\n" +
                "JOIN grade g ON s.StudentID = g.Student_StudentID\n" +
                "JOIN course c ON g.Course_CourseCode = c.CourseCode\n" +
                "WHERE LastName = ?\n" +
                "AND FirstName = ?\n" +
                "AND CourseCode = ?\n");
            pstmt.setString(1, peerTutor.getLastName());
            pstmt.setString(2, peerTutor.getFirstName());
            pstmt.setString(3, courseCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("GradeCode");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        try {Connection con = ds.createConnection();
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM PeerTutor PT \n" +
                "JOIN PeerTutorCourse PC ON PT.PeerTutorID = PC.PeerTutor_PeerTutorID\n" +
                "JOIN Course c ON pc.Course_CourseCode = c.CourseCode\n" +
                "WHERE LastName = ?\n" +
                "AND FirstName = ?\n" +
                "AND c.CourseCode = ?");
            pstmt.setString(1, peerTutor.getLastName());
            pstmt.setString(2, peerTutor.getFirstName());
            pstmt.setString(3, courseCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        try {Connection con = ds.createConnection();
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO peertutorcourse (peertutor_peertutorId, course_coursecode)\n" +
                    "SELECT peertutorId, ? FROM peertutor WHERE LastName = ? AND FirstName = ?");
            pstmt.setString(1, courseCode);
            pstmt.setString(2, peerTutor.getLastName());
            pstmt.setString(3, peerTutor.getFirstName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        List<PeerTutor> peerTutors = new ArrayList<>();
        try {Connection con = ds.createConnection();
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM PeerTutor PT "
                        + "JOIN PeerTutorCourse PC ON PT.PeerTutorID = PC.PeerTutor_PeerTutorID "
                        + "WHERE PC.Course_CourseCode = ?");
            pstmt.setString(1, courseCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PeerTutor peerTutor = new PeerTutor();
                    peerTutor.setId(rs.getInt("PeerTutorID"));
                    peerTutor.setFirstName(rs.getString("FirstName"));
                    peerTutor.setLastName(rs.getString("LastName"));
                    peerTutors.add(peerTutor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peerTutors;
    }
}