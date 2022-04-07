import java.io.IOException;
import java.util.Scanner;

/**
 * Employee.java
 * @author Kim Bui
 * @author Soham Manoli
 * @author Anna Dymchenko
 * CIS 22C, Final Project
 */


// THERE MUST BE MINIMUM OF 3 EMPLOYEES


public class Employee extends User {
	private boolean isManager;
	
	/**
	 * Default constructor
	 */
	public Employee() {
		super();
		isManager = false;
	}
	
	/**
	 * Five-argument constructor
	 * Assigns First Name, Last Name, username and password for user
	 * Determine whether user is manager of regular employee
	 * 
	 * @param fN	     first name to set for user
	 * @param lN	     last name to set for user
	 * @param userName   username of login account
	 * @param passWord   password of login account
	 */
	public Employee(String fN, String lN, String userName, 
					String passWord, boolean isManager) {
		super(fN, lN, userName, passWord);
		this.isManager = isManager;
	}
	public Employee(String userName, String passWord) {
       		setUserName(userName);
       		setPassWord(passWord);
    }
	

	/* Getters */
	
	/**
	 * Gets employee's first name
	 * 
	 * @return employee's first name
	 */
	public String getFirstName() {
		return super.getFirstName();
	}
	
	/**
	 * Gets employee's last name
	 * 
	 * @return employee's last name
	 */
	public String getLastName() {
		return super.getLastName();
	}
	
	/**
	 * Gets employee's username 
	 * 
	 * @return employee's username
	 */
	public String getUserName() {
		return super.getUserName();
	}
	
	/**
	 * Checks if an employee is a manager
	 * 
	 * @return true if manager, false if employee
	 *  
	 */
	public boolean getIsManager() {
		return isManager;
	}
	
	/* Setters */
	
	/**
	 * Sets employee's first name
	 * 
	 * @param fN the first name to set
	 */
	public void setFirstName(String fN) {
		super.setFirstName(fN);
	}
	
	/**
	 * Sets employee's last name
	 * 
	 * @param lN the last name to set
	 */
	public void setLastName(String lN) {
		super.setLastName(lN);
	}
	
	/**
	 * Sets employee's username
	 * 
	 * @param uName the username to set
	 */
	public void setUserName(String uName) {
		super.setUserName(uName);
	}
	
	/**
	 * Sets employee's password
	 * 
	 * @param pWord the password to set
	 */
	public void setPassWord(String pWord) {
		super.setPassword(pWord);
	}
	
	/**
	 * Assigns an employee to be a manager or employee
	 * 
	 * @param isManager true if employee is manager,
	 *  				otherwise false 
	 */
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}

	public int hash()
	{
		String key = getUserName() + getPassword();
		int sum = 0;
		for(int i = 0; i < key.length(); i++)
		{
			sum += (int) key.charAt(i);
		}
		return sum;
	}

	/**
	 * Below are Helpers to show employees menus
	 * Helpers' parameter lists are the same
	 *
	 * @param primaryKeyBST		BST storing products sorted by Title
	 * @param secondaryKeyBST	BST storing products sorted by Developer
	 * @param order				Order object
	 * @param orderHeap			Heap storing order information
	 * @param input				Scanner object to read in user input
	 * @throws IOException        when Scanner object has error
	 *
	 *
	 * Outer Employee Menu:
	 * 	Please Select Below Option:
	 *		1. Search for an Order
	 *		2. View Order with Highest Priority
	 *		3. View All Orders Sorted by Priority
	 * 		4. Ship an Order
	 *		0. Quit and Write Employee Information to a File
	 *
	 * Employee Menu Item 1:
	 * 	Search for an Order:
	 * 		1. Search Using Order ID
	 * 		2. Search by Customer First and Last Name
	 *		0. Exit
	 *
	 * Customer Menu Item 2:
	 * 	View Order with the Highest Priority
	 *
	 * Customer Menu Item 3:
	 * 	View All Orders Sorted by Priority
	 *
	 * Customer Menu Item 4:
	 * 	Ship an Order
	 */

	/**
	 * Shows the outer (the first level) customer menu
	 *
	 */
	private void showOuterMenu(Heap<Order> orderHeap, Scanner input) throws IOException {
		String choice = "";
		System.out.println("\nPlease Select Below Option:"
				+ "\n\t1. Search for an Order"
				+ "\n\t2. View Order with Highest Priority"
				+ "\n\t3. View All Orders Sorted by Priority"
				+ "\n\t4. Ship an Order"
				+ "\n\t0. Quit and Write Employee Information to a File");

		System.out.print("Enter your choice: ");
		choice = input.nextLine();

		if (choice.equals("1")) {
			employeeMenuItem1(orderHeap, input);
		} else if (choice.equals("2")) {
			employeeMenuItem2(orderHeap);
		} else if (choice.equals("3")) {
			employeeMenuItem3(orderHeap);
		} else if (choice.equals("4")) {
			employeeMenuItem4(orderHeap, input);
		} else if (choice.equals("0")) {
			return;
		} else {
			System.out.println("\nInvalid entry! Please type 1 or 2 or 3 or 4 or O.\n");
			showOuterMenu(orderHeap, input);
		}
	}

	/**
	 * Shows customer menu item 1
	 */
	private void employeeMenuItem1(Heap<Order> orderHeap, Scanner input) throws IOException {
		String choice = "";
		System.out.println("\nPlease Select Below Option:");
		System.out.println("\t1. Search Using Order ID");
		System.out.println("\t2. Search by Customer First and Last Name");
		System.out.println("\t0. Exit");
		System.out.print("Enter your choice: ");
		choice = input.nextLine();

		if (choice.equalsIgnoreCase("1")) {
			System.out.print("\nEnter the ID of the Order you would like to search for: ");
			String id = input.nextLine();
			Order result = null;
			//TODO: search for order using id
			if (result != null) {
				System.out.println("\nWe found your order:\n" + result.toString());
			} else {
				System.out.println("No order found with id " + id);
			}

		} else if (choice.equalsIgnoreCase("2")) {
			System.out.print("\nEnter the Customer's First Name: ");
			String firstName = input.nextLine();
			System.out.print("\nEnter the Customer's Last Name: ");
			String lastName = input.nextLine();
			Order result = null;
			//TODO: search for order using first/last name
			if (result != null) {
				System.out.println("\nWe found your order:\n" + result.toString());
			} else {
				System.out.println("No order found with first name " + firstName + " and last name " + lastName);
			}
		} else if (choice.equalsIgnoreCase("0")) {
			;
		} else {
			System.out.println("\n\nInvalid entry! Please type 1 or 2 or O.");
		}
		showOuterMenu(orderHeap, input);
	}

	private void employeeMenuItem2(Heap<Order> orderHeap) throws IOException {
		Order result = null;
		//TODO: get order with highest priority
		System.out.println("\nThe order with the highest priority is:\n" + result.toString());
	}

	private void employeeMenuItem3(Heap<Order> orderHeap) throws IOException {
		//TODO: get list of orders with highest priority
		System.out.println("\nHere are all of the orders (sorted by priority):\n");
	}

	private void employeeMenuItem4(Heap<Order> orderHeap, Scanner input) throws IOException {
		employeeMenuItem3(orderHeap);

	}
	
	/**
	 * Displays the specific menu for employees & carries out employee options
	 *
	 * @param orderHeap heap containing all orders
	 * @throws IOException if there is reading error
	 */
	public void employeeMenu(Heap<Order> orderHeap, HashTable<Employee> employeeHashTable) {
		Scanner input = new Scanner(System.in);
		String choice = "";
		
		if(employeeLogin(employeeHashTable, input)) {
			showOuterMenu(orderHeap, input);
		}

		input.close();
	}
	
	public boolean employeeLogin(HashTable<Employee> hashTable, Scanner userInput) {
		String login, password, choice;
		System.out.println("Please enter your username: ");
		login = userInput.nextLine();
		System.out.println("Please enter your password: ");
		password = userInput.nextLine();
		Employee employee = new Employee(login, password);

		if (!hashTable.contains(employee)) {
			System.out.println("Account not found \n Would you like to try again, or quit?");
			System.out.println(", \'T\' to try again, or \'Q\' to quit: ");
			choice = userInput.nextLine();
			if (choice.equalsIgnoreCase("T")) {
				return employeeLogin(hashTable, userInput);
			} else if (choice.equalsIgnoreCase("Q")) {
				return false;
			} else {
				System.out.println("Invalid input, try again.");
				return employeeLogin(hashTable, userInput);
			}
		}
		else {
			employee = hashTable.get(employee);
			System.out.println("Welcome " + employee.getFirstName());
			return true;
		}
	}
}
