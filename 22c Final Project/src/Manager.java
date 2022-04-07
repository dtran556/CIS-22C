package com.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Manager extends Employee{
	
	private BST<Game> product;
	
	private Scanner scan;
	
	public Manager(Employee employee, BST<Game> product){
		super(employee.getFirstName(), employee.getLastName(),
				employee.getUserName(), employee.getPassWord(), employee.getIsManager());
		if (product == null) {
			return;
		}
		this.product = product;
		scan = new Scanner(System.in);
	}
	
	public void managerMenu() {
		Scanner aScanner = null;
		try  {
			aScanner = new Scanner(System.in);
			
			String choiceString;
			
			while (true) {
				
				System.out.println("Enter \'1\' to add new game");
				System.out.println("Enter \'2\' to update game infomation");
				System.out.println("Enter \'3\' to remove product");
				
				choiceString = aScanner.nextLine();
				
				if (choiceString.equalsIgnoreCase("quit")) {
					System.out.println("Log out succesfully");
					break;
				}
				
				
				if (choiceString.equalsIgnoreCase("1")) {
					addProduct();
				}else if (choiceString.equalsIgnoreCase("2")) {
					updateInfo();
				}
				else if (choiceString.equalsIgnoreCase("3")) {
					removeItem();
				}else {
					System.out.println("Invalid entry");
				}
				writeToFile();
				
			}
	
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	private void addProduct() {
		Game game = new Game();
		try  {
			
			System.out.print("Enter game title: ");
			game.setTitle(scan.nextLine());
			
			System.out.print("Enter game developer: ");
			game.setDeveloper(scan.nextLine());
			
			System.out.print("Enter game genre: ");
			game.setGenre(scan.nextLine());
			
			System.out.print("Enter game age rating: ");
			game.setAgeRating(scan.nextLine());
			
			System.out.print("Enter game description: ");
			game.setDescription(scan.nextLine());
			
			System.out.print("Enter game price: ");
			game.setPrice(scan.nextDouble());
			
			System.out.print("Enter game amount of product: ");
			game.setNumInStock(scan.nextInt());
		}catch (Exception e) {
			e.getStackTrace();
		}
		product.insert(game, new TitleGame());
	}
	
	private void updateInfo() {
		System.out.println("Enter \'1\' to update price");
		System.out.println("Enter \'2\' to update description");
		System.out.println("Enter \'3\' to add more stock: ");
		String choiceUpdate = scan.nextLine();
		if (choiceUpdate.equalsIgnoreCase("1")) {
			updatePrice();
		}else if (choiceUpdate.equalsIgnoreCase("2")) {
			updateDescription();
		}else if (choiceUpdate.equalsIgnoreCase("3")) {
			addStock();
		}else {
			System.out.println("Invalid entry");
			updateInfo();
		}
	}
	private void updatePrice() {

		try  {
			
			System.out.println("Enter game title: ");
			String name = scan.nextLine();
			
			System.out.println("Enter new price: ");
			Double price = scan.nextDouble();
			
			Game game = new Game(name, null);
			Game tempGame = product.search(game, new TitleGame());
			if (tempGame != null) {
				tempGame.setPrice(price);
			}else {
				System.out.format("\nThe game \'%s\' is not in stock\n", name);
				updatePrice();
			}
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	
	private void addStock() {

		try {
			System.out.println("Enter game title: ");
			String name = scan.nextLine();
			
			System.out.println("Enter number of stock to add: ");
			int numAdd = scan.nextInt();
			
			Game game = new Game(name, null);
			Game tempGame = product.search(game, new TitleGame());
			if (tempGame != null) {
				tempGame.setNumInStock(tempGame.getNumInStock() + numAdd);
			}else {
				System.out.format("\nThe game \'%s\' is not in stock\n", name);
				addStock();
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	private void updateDescription() {

		try {
			System.out.println("Enter game title: ");
			String name = scan.nextLine();
			
			System.out.println("Enter new description: ");	
			String description = scan.nextLine();
			
			Game game = new Game(name, null);
			Game tempGame = product.search(game, new TitleGame());
			if (tempGame != null) {
				tempGame.setDescription(description);
			}else {
				System.out.format("\nThe game \'%s\' is not in stock\n", name);
				updateDescription();
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	private void removeItem() {
		try  {
			System.out.println("Enter game title: ");
			String name = scan.nextLine();
	
			Game game = new Game(name, null);
			product.remove(game, new TitleGame());
		}catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void writeToFile()  {
		BufferedWriter aBufferedWriter  = null;
		try {
			aBufferedWriter  = new BufferedWriter(new FileWriter("/Users/yushen/Downloads/Stocks.txt"));
			while (!product.isEmpty()) {
				aBufferedWriter.write(product.getRoot().getTitle() + "\n");
				aBufferedWriter.write(product.getRoot().getDeveloper() + "\n");
				aBufferedWriter.write(product.getRoot().getGenre() + "\n");
				aBufferedWriter.write(product.getRoot().getDescription() + "\n");
				aBufferedWriter.write(product.getRoot().getAgeRating()+ "\n");
				aBufferedWriter.write(product.getRoot().getPrice() + "\n");
				aBufferedWriter.write(product.getRoot().getNumInStock() + "\n\n");
				product.remove(product.getRoot(), new TitleGame());
				
			}
		
			aBufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	
	}


}












