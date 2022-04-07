
/**
 * @author Soham Manoli
 * @author Anna Dymchenko
 * Final Project
 */

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Order {
	class ComparePriority implements Comparator<Order> {
        public int compare(Order o1, Order o2) {
        	return Integer.compare(o1.getPriority(), o2.getPriority());
        }
    }
	class CompareCustomer implements Comparator<Order> {
        public int compare(Order o1, Order o2) {
        	return o1.getCustomer().getUserName().compareTo(o2.getCustomer().getUserName());
        }
    }
	class CompareOrderID implements Comparator<Order> {
        public int compare(Order o1, Order o2) {
        	return Integer.compare(o1.getOrderID(), o2.getOrderID());
        }
    }
	
    private int orderID;
    private Customer customer;
    private String date;
    private LinkedList<Game> orderContents;
    private int shippingSpeed; //or use enums
    private int priority;
    
   /**
     * Default constructor
     */
    public Order() {
    	orderID = -1;
    	customer = null;
    	date = "00-00-00";
    	orderContents = null;
    	shippingSpeed = 0;
    }
    
    
    /**
     * 5-argument constructor
     * Creates a new Order object and assigns:
     * 
     * @param orderID 		 order ID
     * @param customer		 customer placing the order
     * @param date			 the date when the order placed
     * @param orderContents  ordered product information
     * @param shippingSpeed  shipping speed
     */
    public Order(int orderID, Customer customer, String date, 
    				LinkedList<Game> orderContents, int shippingSpeed) {
		this.orderID = orderID;
		this.customer = customer;
		this.date = date;
		this.orderContents = orderContents;
		this.shippingSpeed = shippingSpeed;
	}
	
	
    /**Accessors*/
    
    /**
	 * Returns the order ID
	 * @return order ID
	 */
    public int getOrderID() {
    	return orderID;
    }
    
    /**
	 * Returns the order ID
	 * @return order ID
	 */
    public Customer getCustomer() {
    	return customer;
    }
    
    /**
	 * Returns the date of the order
	 * @return date of the order
	 */
    public String getDate() {
    	return date;
    }
    
    /**
	 * Returns the order contents
	 * @return order contents
	 */
    public LinkedList<Game> getOrderContents() {
    	return orderContents;
    }
    
    /**
	 * Returns the shipping speed
	 * @return shipping speed
	 */
    public int getShippingSpeed() {
    	return shippingSpeed;
    }
    
    /**
	 * Returns the priority of the order
	 * @return priority of the order
	 */
    public int getPriority() {
    	return priority;
    }

	    /* Setters */
    
    /**
     * Sets shipping speed
     * 
     * @param ss shipping speed customer selected
     * 			 1 - STANDARD SHIPPING
     * 			 2 - OVERNIGHT SHIPPING
     * 			 3 - RUSH SHIPPING
     */
    public void setShippingSpeed(int ss) {
    	shippingSpeed = ss;
    }
      
    /**
     * Sets a random order ID
     * @param orderID  order ID to set
     */
    public void setOrderID(int orderID) {
    	orderID = generateOrderID();
    }
    
    /**
     * Helper to generate a random integer for Order ID
     * 
     * @return a random orderID
     */
    private int generateOrderID() {
    	Random r = new Random();
    	return r.nextInt(99999);
    }
    
    /**
     * Adds customer order to LinkedList<Game>
     * 
     * @param g  the new Game object of the order
     */
    public void setOrderContents(Game g) {
    	if (orderContents == null) {
    		orderContents = new LinkedList<Game>();
    	}
    	orderContents.addLast(g);
    }
	
	/**
     * Places an customer order
     * 
     * @param bst 	         the BST containing Game product database
     * @param input	         Scanner object to read in customer input
     * @throws IOException   when Scanner object is in error
     * @return an Order object
     */
    public Order placeOrder(BST<Game> bst, Scanner input) throws IOException {
    	String orderInput = "";
    	Game orderObject = null;
    	
    	System.out.print("\nPlease enter name of game you want to purchase: ");
    	orderInput = input.nextLine();
    	orderObject = bst.search(new Game(orderInput, "", "", "", "", 0.0, 0), new ComparePrimaryKey());
		if (orderObject != null && orderObject.getNumInStock() > 0) {
	    	setOrderContents(orderObject);
	    	setOrderID(orderID);
	    	date = LocalDateTime.now().toString();
		
	    	System.out.print("Please select shipping speed:\n");
	    	System.out.println("\t1. Standard Shipping");
			System.out.println("\t2. Overnight Shipping");
			System.out.println("\t3. Rush Shipping");
	    	do {
	    		System.out.print("Enter your choice: ");
	    		orderInput = input.nextLine();
	    		if (orderInput.equalsIgnoreCase("1")) {
	    			setShippingSpeed(1);
	    		} else if (orderInput.equalsIgnoreCase("2")) {
	    			setShippingSpeed(2);
	    		} else {
	    			setShippingSpeed(3);
	    		}
	    	} while (!orderInput.equalsIgnoreCase("1") && 
    			!orderInput.equalsIgnoreCase("2") && 
    			!orderInput.equalsIgnoreCase("3"));
	    	    	
	    	return new Order(orderID, customer, date, orderContents, shippingSpeed);
		} else {
			System.out.println("\nThe game " + orderInput.toUpperCase() + " is not in stocks!");
			return null;
		}
    }
    
    /**
     * Adds customer orders according to priority in a priority queue 
     * that is based on the Heap data structure
     * 
     * @param heap 	Heap storing orders
     * @param order 	the order to insert to heap
     * @param c		Comparator object to compare orders based on their priority
     */
    public void addOrderToHeap(Heap<Order> heap, Order order, Order.ComparePriority c) {
    	try {
    		heap.insert(order, new Order.ComparePriority());
    		System.out.println("\nYour order has been placed! "
					+ "\nThank you for your purchase!");
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    	}
    }
}
