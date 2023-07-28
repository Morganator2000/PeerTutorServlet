package viewlayer;

import businesslayer.PeerTutorBusinessLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobject.PeerTutor;

/**
 * Not technically a main method, but it's pretty close. This is the code for the servlet execution.
 * It checks the data entered from the POST form.
 * @author morga
 */
public class PeerTutorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Peer Tutors Summary</title>");    
            out.println();
            out.println("</head>");
            out.println("<body bgcolor=\"#FDF5E6\">");
            out.println("<h1>Servlet PeerTutorServlet at " + request.getContextPath() + "</h1>");
            PeerTutorBusinessLogic logic = new PeerTutorBusinessLogic();
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            PeerTutor peerTutor = new PeerTutor();
            peerTutor.setFirstName(firstName);
            peerTutor.setLastName(lastName);
            String courseCode = request.getParameter("courseCode");
            String letterGrade = logic.getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
            
            if (!logic.isPeerTutorRegistered(peerTutor)){
                out.println("<ul>");
                out.println("<li>Last Name: " + peerTutor.getLastName() + "</li>");
                out.println("<li>First Name " + peerTutor.getFirstName() + "</li>");
                out.println("</ul>");
                out.println("<strong>Error: This person is not registered as a peer tutor.</strong>");
            } else if (!logic.isCourseValid(courseCode)){
                out.println("<ul>");
                out.println("<li>Course Code: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<strong>Error: The course is not valid.</strong>");
            } else if (!logic.hasPeerTutorTakenCourse(peerTutor, courseCode)) {
                out.println("<ul>");
                out.println("<li>Last Name: " + peerTutor.getLastName() + "</li>");
                out.println("<li>First Name " + peerTutor.getFirstName() + "</li>");
                out.println("<li>Course Code: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<strong>Error: The peer tutor has not taken the course.</strong>");
            } else if (!"A-".equals(letterGrade) && !"A".equals(letterGrade) && !"A+".equals(letterGrade) ) {
                out.println("<ul>");
                out.println("<li>Last Name: " + peerTutor.getLastName() + "</li>");
                out.println("<li>First Name " + peerTutor.getFirstName() + "</li>");
                out.println("<li>Course Code: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<strong>Error: This student received " + letterGrade + " which is not enough to be a peer tutor.</strong>");
            
            } else if (logic.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode)) {
                out.println("<ul>");
                out.println("<li>Last Name: " + peerTutor.getLastName() + "</li>");
                out.println("<li>First Name " + peerTutor.getFirstName() + "</li>");
                out.println("<li>Course Code: " + courseCode + "</li>");
                out.println("</ul>");
                out.println("<strong>Error: The peer tutor is already assigned to this course</strong>");
            } else {
            
                logic.assignCourseToPeerTutor(peerTutor, courseCode);
                List<PeerTutor> tutors = logic.getAllPeerTutorsForCourse(courseCode);
                out.println("<table border=\"1\">");
                out.println("<tr>");
                out.println("<td>Tutor ID</td>");
                out.println("<td>Last Name</td>");
                out.println("<td>First Name</td>");
                out.println("</tr>");
                for(PeerTutor tutor : tutors){
                    out.printf("<tr><td>%d</td><td>%s</td><td>%s</td></tr>",
                        tutor.getId(), tutor.getLastName(), tutor.getFirstName());
                }
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
