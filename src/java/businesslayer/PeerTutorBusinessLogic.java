package businesslayer;

import dataaccesslayer.*;
import java.util.List;
import transferobject.*;

/**
 * The Business logic for peer tutors
 * @author Morgan Bakelmun
 */
public class PeerTutorBusinessLogic {
    private PeerTutorDAO peerTutorDAO;

    /**
     * Constructor for the logic.
     */
    public PeerTutorBusinessLogic() {
        peerTutorDAO = new PeerTutorDAOImpl();
    }
    
    /**
     * Check if the person is registered as a peer tutor
     * @param peerTutor the peer tutor object
     * @return true if the peer tutor is registered.
     */
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {
        return peerTutorDAO.isPeerTutorRegistered(peerTutor);
    }
    
    /**
     * Check if the course exists
     * @param courseCode The course code
     * @return true if the course exists
     */
    public boolean isCourseValid(String courseCode) {
        return peerTutorDAO.isCourseValid(courseCode);
    }
    
    /**
     * Check to make sure this person has taken the course
     * @param peerTutor the peer tutor
     * @param courseCode the course code
     * @return true if this peer tutor has taken the course
     */
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.hasPeerTutorTakenCourse(peerTutor, courseCode);
    }
    
    /**
     * Get the grade this person earned.
     * @param peerTutor the peer tutor
     * @param courseCode the course code
     * @return The grade in string form
     */
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
    }
    
    /**
     * Check if this person is already a tutor for this course
     * @param peerTutor the peer tutor object
     * @param courseCode the course code
     * @return true if they have taken the course
     */
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        return peerTutorDAO.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode);
    }
    
    /**
     * The insert statement to add this person as a tutor for the course
     * @param peerTutor the peer tutor
     * @param courseCode the course code
     */
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        peerTutorDAO.assignCourseToPeerTutor(peerTutor, courseCode);
    }
    
    /**
     * Return a list of all the tutors for the given course
     * @param courseCode the course code
     * @return a list of peer tutors
     */
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        return peerTutorDAO.getAllPeerTutorsForCourse(courseCode);
    }
}
