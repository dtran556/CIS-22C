/**
 * User.java
 * @author Kim Bui
 * @author Daniel Tran
 * @author Alex Benny
 * CIS 22C, Final Project
 */

public abstract class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    
    
    /**
     * Default constructor
     */
    public User() {
        firstName = "";
        lastName = "";
        username = "";
        password = "";
    }
    
    /**
     * Four-argument constructor
     * Assigns First Name, Last Name, username and password for user
     * @param fN         first name to set for user
     * @param lN         last name to set for user
     * @param userName   username of login account
     * @param passWord   password of login account
     */
    public User(String fN, String lN, String userName, String passWord) {
        firstName = fN;
        lastName = lN;
        username = userName;
        password = passWord;
    }
    
    /* Getters */
    
    /**
     * Gets user's first name 
     * 
     * @return user's first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Gets user's last name 
     * 
     * @return user's last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Gets user's username
     * 
     * @return user's username
     */
    public String getUserName() {
        return username;
    }

    /**
     * Gets user's password
     *
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /* Setters */
    
    /**
     * Sets user's first name
     * 
     * @param fN the first name to set
     */
    public void setFirstName(String fN) {
        firstName = fN;
    }
    
    /**
     * Sets user's last name
     * 
     * @param lN the last name to set
     */
    public void setLastName(String lN) {
        lastName = lN;
    }
    
    /**
     * Sets user's username
     * 
     * @param uName the username to set
     */
    public void setUserName(String uName) {
        username = uName;
    }
    
    /**
     * Sets user's password
     * 
     * @param pWord the password to set
     */
    public void setPassword(String pWord) {
        password = pWord;
    }
    
    
    public int hashCode()
    {
        String key = username + password;
        int sum = 0;
        for(int i = 0; i < key.length(); i++)
        {
            sum += key.charAt(i);
        }
        return sum;
    }
}
