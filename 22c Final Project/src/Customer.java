
/**
 * Customer.java
 * @author Kim Bui
 * @author CIS 22C, Final Project
 */

import java.io.IOException;
import java.util.Scanner;

public class Customer extends User {
	private String address;
	private String city;
	private String state;
	private String zip;
	private LinkedList<Order> shippedOrders;
	private LinkedList<Order> unshippedOrders;

	/**
	 * Default constructor
	 */
	public Customer() {
		super();
		address = "";
		city = "";
		state = "";
		zip = "";
		shippedOrders = new LinkedList<>();
		unshippedOrders = new LinkedList<>();
	}

	/**
	 * 10-argument constructor Calls constructor of parent's class Assigns
	 * information for Customer Assigns customer's shipped and unshipped orders
	 *
	 * @param address   customer's address
	 * @param city      the city where customer lives in
	 * @param state     the state where customer lives in
	 * @param zip       customer's zipcode
	 * @param shipped   shipped orders
	 * @param unshipped unshipped orders
	 */
	public Customer(String userName, String passWord, String fN, String lN, String address, String city, String state, String zip) {
		super(userName, passWord, fN, lN);
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		shippedOrders = new LinkedList<>();
		unshippedOrders = new LinkedList<>();
	}


	/* Getters */

	/**
	 * Gets customer's first name
	 *
	 * @return customer's first name
	 */
	public String getFirstName() {
		return super.getFirstName();
	}

	/**
	 * Gets customer's last name
	 *
	 * @return customer's last name
	 */
	public String getLastName() {
		return super.getLastName();
	}

	/**
	 * Gets customer's username
	 *
	 * @return customer's username
	 */
	public String getUserName() {
		return super.getUserName();
	}

	/**
	 * Gets customer's password
	 *
	 * @return customer's password
	 */
	public String getPassword() {
		return super.getPassword();
	}

	/**
	 * Gets customer's shipped orders
	 *
	 * @return customer's shipped orders
	 */
	public LinkedList<Order> getShippedOrder() {
		return shippedOrders;
	}

	/**
	 * Gets customer's unshipped orders
	 *
	 * @return customer's unshipped orders
	 */
	public LinkedList<Order> getUnshippedOrder() {
		return unshippedOrders;
	}

	/**
	 * Gets customer's address
	 *
	 * @return customer's home address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Gets customer's city
	 *
	 * @return customer's city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Gets customer's state
	 *
	 * @return customer's state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Gets customer's zipcode
	 *
	 * @return customer's zipcode
	 */
	public String getZip() {
		return zip;
	}

	/* Setters */

	/**
	 * Sets customer's first name
	 *
	 * @param fN the first name to set
	 */
	public void setFirstName(String fN) {
		super.setFirstName(fN);
	}

	/**
	 * Sets customer's last name
	 *
	 * @param lN the last name to set
	 */
	public void setLastName(String lN) {
		super.setLastName(lN);
	}

	/**
	 * Sets customer's username
	 *
	 * @param uName the username to set
	 */
	public void setUserName(String uName) {
		super.setUserName(uName);
	}

	/**
	 * Sets customer's password
	 *
	 * @param pWord the password to set
	 */
	public void setPassWord(String pWord) {
		super.setPassword(pWord);
	}

	/**
	 * Sets customer's address
	 *
	 * @param addr the address to set
	 */
	public void setAddress(String addr) {
		address = addr;
	}

	/**
	 * Sets customer's city
	 *
	 * @param cT the city to set
	 */
	public void setCity(String cT) {
		city = cT;
	}

	/**
	 * Sets customer's state
	 *
	 * @param st the state to set
	 */
	public void setState(String st) {
		state = st;
	}

	/* Additional methods */

	/**
	 * Customer hashCode()
	 */
	@Override
    public int hashCode() {
    	String key = getUserName() + getPassword();
        int sum = 0;
        for(int i = 0; i < key.length(); i++) {
            sum += (int)key.charAt(i);
        }
        return sum;
    }

	/**
	 * Compares two Customer objects
	 *
	 * Two customers are equal if they have
	 * the same username and password
	 *
	 * @param o  another object to compare with
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Customer)) {
			return false;
		} else {
			Customer c = (Customer) o;
			return this.getUserName().equals(c.getUserName()) &&
					this.getPassword().equals(c.getPassword());
		}
	}

	/**
	 * Returns a String of the Customer information and their orders in the
	 * following format:
	 *
	 * Name: <first name> <last name>
	 * Address: <address>
	 *
	 * Your Orders:
	 * 		Shipped Orders: <Shipped Orders>
	 * 		Unshipped Orders: <Unshipped Orders>
	 *
	 * @return the Customer information and their ordering information
	 */
	@Override
	public String toString() {
		return "\n" + "Username: " + getUserName() + "\nPassword: " + getPassword() +
				"\nName: "
				+ getFirstName() + " " + getLastName() + "\nAddress: " + address + ", " + city
				+ ", " + zip + ", " + state
				+ "\nYour Orders: \n"
				+ "\tShipped Orders: " + (getShippedOrder().isEmpty() ? "No Records Found" : getShippedOrder().toString())
				+ "\n\tUnshipped Orders: "  + (getUnshippedOrder().isEmpty() ? "No Records Found" : getUnshippedOrder().toString());
	}

	/**
	 * Below are Helpers to show customer menus
	 * Helpers' parameter lists are the same
	 *
	 * @param primaryKeyBST		BST storing products sorted by Title
	 * @param secondaryKeyBST	BST storing products sorted by Developer
	 * @param order				Order object
	 * @param orderHeap			Heap storing order information
	 * @param input				Scanner object to read in user input
	 * @throws IOException		when Scanner object has error
	 *
	 *
	 * Outer Customer Menu:
	 * 	Please Select Below Option:
	 *		1. Search for a Product
	 *		2. List Database of Products
	 *		3. Place an Order
	 * 		4. View Purchases
	 *		0. Quit and Write Customer information to a File
	 *
	 * Customer Menu Item 1:
	 * 	Search for a Product:
	 * 		1. Find and Display One Record Using Title (Primary Key)
	 * 		2. Find and Display One Record Using Developer (Secondary Key)
	 *		0. Exit
	 *
	 * Customer Menu Item 2:
	 * 	List Database of Products:
	 * 		1. List Data Sorted By Primary Key
	 * 		2. List Data Sorted By Secondary Key
	 * 		0. Exit
	 *
	 * Customer Menu Item 3:
	 * 	Place an Order
	 *
	 * Customer Menu Item 4:
	 * 	View Purchases
	 */

	/**
	 * Shows the outer (the first level) customer menu
	 *
	 */
	private void showOuterMenu(BST<Game> primaryKeyBST, BST<Game> secondaryKeyBST,
								Order order, Heap<Order> orderHeap, Scanner input) throws IOException {
		String choice = "";
		System.out.println("\nPlease Select Below Option:"
				+ "\n\t1. Search for a Product"
				+ "\n\t2. List Database of Products"
				+ "\n\t3. Place an Order"
				+ "\n\t4. View Purchases"
				+ "\n\t0. Quit and Write Customer information to a File");

		System.out.print("Enter your choice: ");
		choice = input.nextLine();

		if (choice.equals("1")) {
			customerMenuItem1(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
		} else if (choice.equals("2")) {
			customerMenuItem2(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
		} else if (choice.equals("3")) {
			customerMenuItem3(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
		} else if (choice.equals("4")) {
			customerMenuItem4(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
		} else if (choice.equals("0")) {
			return;
		} else {
			System.out.println("\nInvalid entry! Please type 1 or 2 or 3 or 4 or O.\n");
			showOuterMenu(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
		}
	}

	/**
	 * Shows customer menu item 1
	 */
	private void customerMenuItem1(BST<Game> primaryKeyBST, BST<Game> secondaryKeyBST,
									Order order, Heap<Order> orderHeap, Scanner input) throws IOException {
		String choice = "";
		String keySearch = "";
		System.out.println("\nPlease Select Below Option:");
		System.out.println("\t1. Find and Display One Record Using Title (Primary Key)");
		System.out.println("\t2. Find and Display One Record Using Developer (Secondary Key)");
		System.out.println("\t0. Exit");
		System.out.print("Enter your choice: ");
		choice = input.nextLine();

		if (choice.equalsIgnoreCase("1")) {
			System.out.print("\nEnter the game title you want to search: ");
			keySearch = input.nextLine();
			Game result = primaryKeyBST.search(new Game(keySearch, "", "", "", "", 0.0, 0),
					new ComparePrimaryKey());
			if (result != null) {
				System.out.println();
				System.out.println("We found the product for you:\n" + result.toString());
			} else {
				System.out.println("\nSorry, the product " + keySearch.toUpperCase() + " is not in stocks!\n");
			}

		} else if (choice.equalsIgnoreCase("2")) {
			System.out.print("\nEnter the game deverloper that you want to search: ");
			keySearch = input.nextLine();
			Game result = secondaryKeyBST.search(new Game("", keySearch, "", "", "", 0.0, 0),
					new CompareSecondaryKey());
			if (result != null) {
				System.out.println();
				System.out.println("We found the product with Developer " + keySearch.toUpperCase() + " for you:\n"
									+ result.toString());
			} else {
				System.out.println("\nSorry, the product with Developer " + keySearch.toUpperCase() + " is not in stocks!\n");
			}
		} else if (choice.equalsIgnoreCase("0")) {
			;
		} else {
			System.out.println("\\nnInvalid entry! Please type 1 or 2 or O.");
		}
		showOuterMenu(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
	}

	/**
	 * Shows customer menu item 2
	 */
	private void customerMenuItem2(BST<Game> primaryKeyBST, BST<Game> secondaryKeyBST,
									Order order, Heap<Order> orderHeap, Scanner input) throws IOException {
		String choice = "";
		System.out.println("\nPlease Select Below Option:");
		System.out.println("\t1. List Data Sorted By Primary Key");
		System.out.println("\t2. List Data Sorted By Secondary Key");
		System.out.println("\t0. Exit");
		System.out.print("Enter your choice: ");
		choice = input.nextLine();
		if (choice.equalsIgnoreCase("1")) {
			System.out.println("\nList of Game Products Sorted By Title:\n" + primaryKeyBST.inOrderString());
		} else if (choice.equalsIgnoreCase("2")) {
			System.out.println("\nList of Game Products Sorted By Developer:\n" + secondaryKeyBST.inOrderString());
		} else if (choice.equalsIgnoreCase("0")) {
			;
		} else {
			System.out.println("\n\nInvalid entry! Please type 1 or 2 or O.");
		}
		showOuterMenu(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
	}

	/**
	 * Shows customer menu item 3
	 */
	private void customerMenuItem3(BST<Game> primaryKeyBST, BST<Game> secondaryKeyBST,
										Order order, Heap<Order> orderHeap, Scanner input) throws IOException {
		Order newOrder = null;
		newOrder = order.placeOrder(secondaryKeyBST, input);

		if (newOrder != null) {
			System.out.println("Order OK!");
			newOrder.addOrderToHeap(orderHeap, newOrder, order.new ComparePriority());
		} else {
			System.out.println("We are very sorry! \nWould you like to purchase another game?");
			System.out.print("\nPlease select Y (Yes) or N (No) or any key to exit: ");
			String choice = input.nextLine();
			if (choice.equalsIgnoreCase("Y")) {
				customerMenuItem3(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
			} else {
				showOuterMenu(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
			}
		}
	}

	/**
	 * Shows customer menu item 4
	 */
	private void customerMenuItem4(BST<Game> primaryKeyBST, BST<Game> secondaryKeyBST,
									Order order, Heap<Order> orderHeap, Scanner input) {
		System.out.println("\nYour PurChases at Our Store:\n");
		System.out.println("Shipped orders: \n" + "\t"
							+ (getShippedOrder().isEmpty() ? "No Records" : getShippedOrder().toString()));
		System.out.println("Unshipped orders: \n" + "\t"
							+ (getUnshippedOrder().isEmpty() ? "No Records" : getUnshippedOrder().toString()));
	}

	/**
	 * Displays the specific menu for customer Performs functionalities of customer
	 *
	 * @param primaryKeyBST   BST saving products which are sorted by primary key
	 *                        (title)
	 * @param secondaryKeyBST BST saving products which are sorted by secondary key
	 *                        (developer)
	 * @throws IOException if there is reading error
	 */
	public void customerMenu(BST<Game> primaryKeyBST, BST<Game> secondaryKeyBST,
							Order order, Heap<Order> orderHeap, HashTable<Customer> customerHashTable) throws IOException {
		Scanner input = new Scanner(System.in);
		String choice = "";

		System.out.println("Please Select Below Option:"
							+ "\n\t1. You are an existing Customer"
							+ "\n\t2. You are a new Customer and want to create an account"
							+ "\n\t3. Login as a Guest"
							+ "\n\t0. Exit");
		System.out.print("Enter your choice: ");
		choice = input.nextLine();
		do {
			if (choice.equals("1")) {
				customerLogin(customerHashTable, input);
				showOuterMenu(primaryKeyBST,secondaryKeyBST, order, orderHeap, input);
				continue;
			} else if (choice.equals("2")) {
				System.out.println("Creating account for new customer...");
				System.out.println("Assuming creating account ok...");
				showOuterMenu(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
				continue;
			} else if (choice.equals("3")) {
				System.out.println("\nYou are visiting as a Guest!");
				showOuterMenu(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
				continue;
			} else if (choice.equals("0")) {
				showOuterMenu(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
				continue;
			} else {
				System.out.println("\nInvalid entry! Please type 1 or 2 or 3 or O.\n");
				showOuterMenu(primaryKeyBST, secondaryKeyBST, order, orderHeap, input);
				continue;
			}
		} while (!choice.equalsIgnoreCase("0"));

		input.close();
	}

	/**
	 * Verifies customer login account
	 *
	 * @param input Scanner object to read user input
	 * @return whether customer successfully logged in
	 */
	public void customerLogin(HashTable<Customer> hashTable, Scanner input) {
		String userInput = "";

		System.out.print("\nPlease enter your unsername: ");
		userInput = input.nextLine();
		setUserName(userInput);
		System.out.print("Please enter your password: ");
		userInput = input.nextLine();
		setPassWord(userInput);

		Customer login = loginVerification(hashTable, new Customer(getUserName(), getPassword(),
											"", "", "", "", "", ""));
		if (login != null) {
			System.out.println("\nLogin Successfully!");
			System.out.println("\nWelcome back, " + login.getFirstName());
		} else {
			System.out.println("\nYour login information is not correct!");
			System.out.println("\nDo you want to try login again?");
			System.out.print("\nPlease select Y (Yes) or N (No) or any key to exit: ");
			userInput = input.nextLine();
			if (userInput.equalsIgnoreCase("Y")) {
				customerLogin(hashTable, input);
			} else {
				System.out.println("\nDo you want to create an account?");
				System.out.print("\nPlease select Y (Yes) or N (No) or any key to exit: ");
			}
		}
	}

	/**
	 * Determines whether a customer is in Customer database
	 *
	 * @param hashTable   HashTable storing Customer information
	 * @param customer	  customer trying to log in
	 *
	 * @return customer information of successful, otherwise null
	 */
	private Customer loginVerification(HashTable<Customer> hashTable, Customer customer) {
		return hashTable.find(customer);
	}
}
