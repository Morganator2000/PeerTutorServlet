package transferobject;

/**
 * The Peer Tutor data transfer object that was incorrectly named but I'm still not changing it.
 * @author Morgan Bakelmun
 */
public class PeerTutor {

    private int id;
    private String lastName;
    private String firstName;
    
    /**
     * The constructor for the PeerTutor
     * @param firstName the first name provided by the user
     * @param lastName the last name provided by the user
     */
    public PeerTutor(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
    
    /**
     * No-argument constructor
     */
    public PeerTutor(){};
    
    /**
    * Gets the ID of the peer tutor.
    * @return The ID of the peer tutor.
    */
   public int getId() {
       return id;
   }

   /**
    * Sets the ID of the peer tutor.
    * @param id The ID to set for the peer tutor.
    */
   public void setId(int id) {
       this.id = id;
   }

   /**
    * Gets the last name of the peer tutor.
    * @return The last name of the peer tutor.
    */
   public String getLastName() {
       return lastName;
   }

   /**
    * Sets the last name of the peer tutor.
    * @param lastName The last name to set for the peer tutor.
    */
   public void setLastName(String lastName) {
       this.lastName = lastName;
   }

   /**
    * Gets the first name of the peer tutor.
    * @return The first name of the peer tutor.
    */
   public String getFirstName() {
       return firstName;
   }

   /**
    * Sets the first name of the peer tutor.
    * @param firstName The first name to set for the peer tutor.
    */
   public void setFirstName(String firstName) {
       this.firstName = firstName;
   }
    
}
