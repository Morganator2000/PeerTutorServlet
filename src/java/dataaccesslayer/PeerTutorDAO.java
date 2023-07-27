package dataaccesslayer;

import java.util.List;
import transferobject.PeerTutor;

/**
 * The interface for the peer tutor DAO.
 * @author Morgan Bakelmun
 */
public interface PeerTutorDAO {
    /**
     * Check if the person is registered as a peer tutor
     * @param peerTutor the peer tutor object
     * @return true if the peer tutor is registered.
     */
    boolean isPeerTutorRegistered(PeerTutor peerTutor);
    /**
     * Check if the course exists
     * @param courseCode The course code
     * @return true if the course exists
     */
    boolean isCourseValid(String courseCode);
    /**
     * Check to make sure this person has taken the course
     * @param peerTutor the peer tutor
     * @param courseCode the course code
     * @return true if this peer tutor has taken the course
     */
    boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode);
    /**
     * Get the grade this person earned.
     * @param peerTutor the peer tutor
     * @param courseCode the course code
     * @return The grade in string form
     */
    String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode);
    /**
     * Check if this person is already a tutor for this course
     * @param peerTutor the peer tutor object
     * @param courseCode the course code
     * @return true if they have taken the course
     */
    boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode);
    /**
     * The insert statement to add this person as a tutor for the course
     * @param peerTutor the peer tutor
     * @param courseCode the course code
     */
    void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode);
    /**
     * Return a list of all the tutors for the given course
     * @param courseCode the course code
     * @return a list of peer tutors
     */
    List<PeerTutor> getAllPeerTutorsForCourse(String courseCode);
}

