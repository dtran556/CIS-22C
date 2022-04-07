/**
 * Game.java
 * @author Danh Nguyen
 * @author Kim Bui
 * CIS 22C, Final Project
 */

public class Game {
    private String title;
    private String developer;
    private String genre;
    private String ageRating;
    private String description;
    private double price;
    private int numInStock;
        
    
    /**
     * Default constructor
     */
    public Game() {
    	title = "";
    	developer = "";
    	genre = null;
    	ageRating = null;
    	price = 0.0;
    	numInStock = 0;
    }
    
    /**
     * 6-argument constructor
     * 
     * Assigns all variables as follows 
     * @param t    title of game
     * @param d    developer of game
     * @param g    game genre
     * @param a    age rating
     * @param desc game description
     * @param p    price of game
     * @param num  number of game
     */
    public Game(String t, String d, String g, 
    		String a, String desc, double p, int num) {
    	title = t;
    	developer = d;
    	genre = g;
    	ageRating = a;
    	description = desc;
    	price = p;
    	numInStock = num;
    }

  
    
	/* Getters */
	
	/**
	 * Gets game title
	 * 
	 * @return game title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets game's developer 
	 *
	 * @return game's developer
	 */
	public String getDeveloper() {
		return developer;
	}
	
	/**
	 * Gets game's genre 
	 *
	 * @return game's genre
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * Gets game's age rating 
	 *
	 * @return game's age rating
	 */
	public String getAgeRating() {
		return ageRating;
	}
	
	/**
	 * Gets game's description
	 *
	 * @return game's description
	 */
	public String getDescription() {
		return description;
	}
    
	/**
	 * Gets game's price
	 *
	 * @return game's price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Gets number of games in stock
	 * 
	 * @return number of available games in stock
	 */
	public int getNumInStock() {
		return numInStock;
	}
	
	
	/* Setters */
	
	/**
	 * Sets game title
	 * @param t  game title
	 */
	public void setTitle(String t) {
		title = t;
	}
	
	/**
	 * Sets game developer
	 * @param d  game title
	 */
	public void setDeveloper(String d) {
		developer = d;
	}
	
	/**
	 * Sets game genre
	 * @param g  game title
	 */
	public void setGenre(String g) {
		genre = g;
	}
	
	/**
	 * Sets game age rating
	 * @param a  game age rating
	 */
	public void setAgeRating(String a) {
		ageRating = a;
	}
	
	/**
	 * Sets game description 
	 * @param desc  game description
	 */
	public void setDescription(String desc) {
		description = desc;
	}
	
	/**
	 * Prints product database with the format:
	 * 
	 * <Title>
	 * <Developer>
	 * <Genre>
	 * <Age Rating>
	 * <Description>
	 * <Price>
	 * <Number of games in stocks>  
	 */
	@Override
	 public String toString() {
	        return  "\n" + title  + 
	        		"\n" + developer + 
	        		"\n" + genre + 
	        		"\n" + ageRating + 
	        		"\n" + description + 
	        		"\n" + price + 
	        		"\n" + numInStock + "\n";
	    }
	
}
