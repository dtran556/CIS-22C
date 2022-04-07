
/** 
 * UserInterface.java
 * 
 * @author Kim Bui
 * @author Daniel Tran
 * @author Alex Benny
 * @author Soham Manoli
 * @author Anna Dymchenko
 * 
 * CIS 22C, Final Project
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
	private static final String StocksFile = "Stocks.txt";
	private static final String CustomersFile = "Customers.txt";
	private static final String EmployeesFile = "Employee.txt";

	private static BST<Game> primaryKeyBST = new BST<>();
	private static BST<Game> secondaryKeyBST = new BST<>();

	private static HashTable<Customer> customersHashTable = new HashTable<>(10);;
	private static HashTable<Employee> employeesHashTable = new HashTable<>(10);

	private static Heap<Order> priorityQueue = new Heap<>(new ArrayList<>(15), new Order().new ComparePriority());;

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Our Online Game Store!\n");

		UserInterface UI = new UserInterface();
		Customer customer = new Customer();
		Employee employee = new Employee();
		Order order = new Order();
		Scanner input = null;

		// Populates data
		UI.populateGame();
		UI.populateCustomer();
		UI.populateEmployee();

		String choice = "";

		System.out.println("Are you a Customer or a Employee?" 
							+ "\n\t1. Customer" 
							+ "\n\t2. Employee" 
							+ "\n\t0. Exit.");
		input = new Scanner(System.in);
		do {
			System.out.print("Enter your choice: ");
			choice = input.nextLine();
			if (choice.equalsIgnoreCase("1")) {
				System.out.println("\nWelcome Customer to Our Store!");
				customer.customerMenu(primaryKeyBST, secondaryKeyBST, order, priorityQueue, customersHashTable);
				break;
			} else if (choice.equalsIgnoreCase("2")) {
				System.out.println("\nWelcome, store Employee!");
				employee.employeeMenu(priorityQueue, employeesHashTable);
				break;
			} else if (choice.equalsIgnoreCase("0")) {
				break;
			} else {
				System.out.println("\nInvalid entry! Please type 1 or 2 or O.\n");
			}
		} while (!choice.equalsIgnoreCase("0"));
		
		System.out.println("\nThank you for visitting! "
							+ "\nSee you again! "
							+ "\nHave a good day!");
		input.close();
	}

	/**
	 * Reads game products from external *.txt file and stores them into two BSTs -
	 * primaryKeyBST stores products that are sorted by primary key which is title -
	 * secondaryKeyBST stores products that are sorted by secondary key which is
	 * developer
	 * 
	 * @throws IOException when input file cannot be read
	 */
	public void populateGame() {
		File gameData = new File(StocksFile);
		Scanner input = null;
		try {
			input = new Scanner(gameData);
			while (input.hasNextLine()) {
				Game g = new Game(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(),
						input.nextLine(), Double.parseDouble(input.nextLine()), Integer.parseInt(input.nextLine()));
				primaryKeyBST.insert(g, new ComparePrimaryKey());
				secondaryKeyBST.insert(g, new CompareSecondaryKey());

				if (input.hasNextLine()) {
					input.nextLine();
				}
			}
		} catch (IOException E) {
			E.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	/**
	 * Reads customer information from *.txt file and stores all the info to Hash
	 * Table
	 * 
	 * @throws IOException when input file cannot be read
	 */
	public void populateCustomer() {
		File customerData = new File(CustomersFile);
		Scanner input = null;
		try {
			input = new Scanner(customerData);
			while (input.hasNextLine()) {
				Customer c = new Customer(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(),
						input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine());
				customersHashTable.add(c);
				if (input.hasNextLine()) {
					input.nextLine();
				}
			}
		} catch (IOException E) {
			E.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
	
	/**
	 * Reads employee  information from *.txt file and stores all the info to Hash
	 * Table
	 * 
	 * @throws IOException when input file cannot be read
	 */
	public void populateEmployee() {
		File employeeData = new File(EmployeesFile);
		Scanner input = null;
		try {
			input = new Scanner(employeeData);
			while (input.hasNextLine()) {
				Employee e = new Employee(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), Boolean.parseBoolean(input.nextLine()));
				employeesHashTable.add(e);
				if (input.hasNextLine()) {
					input.nextLine();
				}
			}
		} catch (IOException E) {
			E.printStackTrace();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	// creating customer account
	public Customer createCustomerAccount(Scanner userInput) {
		String firstName, lastName, login, password, address, city, state, zip;
		Customer customer;

		System.out.print("\nPlease enter your first name: ");
		firstName = userInput.nextLine();
		System.out.print("Please enter your last name: ");
		lastName = userInput.nextLine();
		System.out.print("Please enter your login: ");
		login = userInput.nextLine();
		System.out.print("Please enter your password: ");
		password = userInput.nextLine();
		// add address city state zip
		System.out.print("Please enter your address: ");
		address = userInput.nextLine();
		System.out.print("Please enter your city: ");
		city = userInput.nextLine();
		System.out.print("Please enter your state: ");
		state = userInput.nextLine();
		System.out.print("Please enter your zip: ");
		zip = userInput.nextLine();

		customer = new Customer(login, password, firstName, lastName, address, city, state, zip);
		// missing shipped/unshipped lists
		customersHashTable.add(customer);

		System.out.println("\nWelcome " + customer.getFirstName() + "!");
		return customer;
		// after create new customer obj add to file
	}

	// if customer wants to log in as a guest
	public Customer guestLogin() {
		Customer customer = new Customer();
		customer.setFirstName("Guest");
		customer.setLastName("Guest");
		System.out.println("\nWelcome Guest!");
		return customer;
	}

	// customer login
	public Customer customerLogin(Scanner userInput) {
		System.out
				.println("\nWould you like to login as a guest, create an account, or sign into an existing account?");
		System.out.print("Enter \'G\' to login as a guest, \'C\' to create an account, or \'S\' to sign in: ");

		String choice = userInput.nextLine();
		if (choice.equals("G")) {
			return guestLogin();
		} else if (choice.equalsIgnoreCase("C")) {
			return createCustomerAccount();
		} else if (choice.equalsIgnoreCase("S")) {
			// customer has an account already
			return existingLogin();
		} else {
			System.out.println("Invalid input. Please enter \'G\', \'C\', or \'S\'");
			return customerLogin();
		}

	}

	// if the customer has an existing account
	public Customer existingLogin(Scanner userInput) {
		String login, password, choice;
		System.out.print("Please enter your username: ");
		String username = userInput.nextLine();
		System.out.println("Please enter your password: ");
		password = userInput.nextLine();
		Customer customer = new Customer(username, password);

		if (!customersHashTable.contains(customer)) {
			System.out.println("Account not found. \n Would you like to make a new account, try again, or quit?");
			System.out.println("Enter \'N\' to make a new account, \'T\' to try again, or \'Q\' to quit: ");
			choice = userInput.nextLine();
			if (choice.equalsIgnoreCase("N")) {
				return createCustomerAccount();
			} else if (choice.equalsIgnoreCase("T")) {
				return existingLogin();
			} else if (choice.equalsIgnoreCase("Q")) {
				return null;
			} else {
				System.out.println("Invalid input");
			}

		}
		customer = customersHashTable.get(customer);
		System.out.println("Welcome " + customer.getFirstName());
		return customer;
	}

	// employee login - moved to Employee class
	/*
	public Employee employeeLogin(Scanner userInput) {
		String login, password, choice;
		System.out.println("Please enter your username: ");
		login = userInput.nextLine();
		System.out.println("Please enter your password: ");
		password = userInput.nextLine();
		Employee employee = new Employee(login, password);

		if (!employeesHashTable.contains(employee)) {
			System.out.println("Account not found \n Would you like to try again, or quit?");
			System.out.println(", \'T\' to try again, or \'Q\' to quit: ");
			choice = userInput.nextLine();
			if (choice.equalsIgnoreCase("T")) {
				return employeeLogin(userInput);
			} else if (choice.equalsIgnoreCase("Q")) {
				return null;
			} else {
				System.out.println("Invalid input");
			}
		}
		employee = employeesHashTable.get(employee);
		System.out.println("Welcome " + employee.getFirstName());
		return employee;
	}
	*/

}
