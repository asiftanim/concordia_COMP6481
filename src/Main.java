//.......................................
// Assignment 1
// © Kazi Asif Tanim
// Written by: Kazi Asif Tanim, 40216618
// Type: Driver class
// Description: Vaccine inventory program. Add/Update/Search/View Vaccine records.
//.......................................

import java.util.Scanner;

public class Main {
	
	private static final String password = "password"; //static password
	private static int wrongPasswordCounter = 0; //wrong password counter
	static Vaccine[] inventory; // Vaccine of Vaccine which will be used as inventory
	
	//method to handle password. isTerminate will decide if it should consider 12 failed attempt or not
	public static boolean checkPassword(boolean isTerminate) {
		System.out.println("\nPlease enter your password");
		Scanner scanner = new Scanner(System.in);
		String pass = scanner.nextLine();
		boolean output = false;
		
		if(pass.equals(password)) {
			output = true;
		}else {
			System.out.println("Wrong password");
			wrongPasswordCounter++; //wrong password counter
			if(wrongPasswordCounter == 12 && isTerminate) { // check if user input 12 wrong password.
				System.out.println("Program  detected  suspicious  activities  and  will  terminate  immediately!");
			   System.exit(0); //terminate the program as 12 failed attempt
			}else if(wrongPasswordCounter % 3 == 0) { //This is for redisplay main menu for if 3 password attemps failed
				output = false;
			}else {
				if(checkPassword(isTerminate)) { //recursion to keep asking for password if password is wrong
					output = true;
				};
			}
		}
		return output;
	}
	
	//Method to find Vaccine by brand name. User a loop and match given brand. if exist print vaccine info
	public static void findVaccineBy(Vaccine.Brands brand) {
		for(int i=0;i <Vaccine.getNumberOfCreatedVaccine(); i++) {
			if(inventory[i].getBrand() == brand) {
				System.out.println("\nVaccine: #" + i);
				System.out.println(inventory[i]);
			}
		}
	}
	
	//Method to find Vaccine cheaper than give price. User a loop and match given price. if exist print vaccine info
	public static void findCheaperThan(double price) {
		for(int i=0;i <Vaccine.getNumberOfCreatedVaccine(); i++) {
			if(inventory[i].getPrice_tag() < price) {
				System.out.println("\nVaccine: #" + i);
				System.out.println(inventory[i]);
			}
		}
	}

	//Entry point of the program.
	public static void main(String[] args) {		
		
		System.out.println("Welcome User \n");
		System.out.println("How many vaccines your store will contain?");
		Scanner scanner = new Scanner(System.in);
		
		inventory = new Vaccine[Integer.parseInt(scanner.nextLine())]; //Init Vaccine array size by user given size.
		
		//infite loop to keep keep the program running unit user Quit.
		while(true) {
			System.out.println("\nWhat do you want to do?");
			System.out.println("1. Enter new vaccines (password required)");
			System.out.println("2. Change information of a vaccine (password required)");
			System.out.println("3. Display all vaccines by a specific brand");
			System.out.println("4. Display all vaccines under a certain price");
			System.out.println("5. Quit");
			System.out.println("Please enter your choice >");
			
			int choice = Integer.parseInt(scanner.nextLine()); //get menu choice from user
			
			//switch cases from user input.
			switch(choice) {
				case 1:
					if(checkPassword(true)) { //check password. continue if password is right. all password situation is handled in checkPassword method
						wrongPasswordCounter = 0; // wrong pass counter to 0 as password is correct.
						System.out.println("How many vaccines do you want to add?");
						int numOfVaccine = Integer.parseInt(scanner.nextLine());
						
						//check if there is enough storage. new vaccine count should be less than or equal (inventory size - number of existng vaccine)
						if(numOfVaccine <= (inventory.length - Vaccine.getNumberOfCreatedVaccine())) {
							
							for(int i=0; i<numOfVaccine; i++) { //loop to add all the vaccines
								
								System.out.println("\nEnter vaccine ID");
								Vaccine v = new Vaccine(Integer.parseInt(scanner.nextLine())); //assign id when creating object
								
								System.out.println("Enter choose vaccine Brand from below: ");
								System.out.println("1. PFIZER \n2. MODERNA \n3. ASTRAZENICA ");
								//set brand name accourding to user choice
								int c = Integer.parseInt(scanner.nextLine());
								switch(c) {
									case 1:
										v.setBrand(Vaccine.Brands.PFIZER);
										break;
									case 2:
										v.setBrand(Vaccine.Brands.MODERNA);
										break;
									case 3:
										v.setBrand(Vaccine.Brands.ASTRAZENICA);
										break;
								}
								
								System.out.println("Enter Dose");
								v.setDose(Double.parseDouble(scanner.nextLine()));
								
								System.out.println("Enter Expiry Date");
								v.setExpiry_date(scanner.nextLine());
								
								System.out.println("Enter price");
								v.setPrice_tag(Double.parseDouble(scanner.nextLine()));
								
								inventory[Vaccine.getNumberOfCreatedVaccine() - 1] = v; // Assign  Vaccine object v to the inventory. index will be (exsting vaccine - 1)
								
								System.out.println("\nVaccine: #" + (Vaccine.getNumberOfCreatedVaccine() - 1)); //print vaccine index number
								System.out.println(inventory[Vaccine.getNumberOfCreatedVaccine() - 1]); // print whole vaccine object
							}
	
							
							
						}else { //If no storage show a message and show how many space left.
							System.out.println("Not enough storage. You can only add " + (inventory.length - Vaccine.getNumberOfCreatedVaccine()) + " Vaccine");
							break;
						}
					}else {
						break;
					}
					
					break;
				case 2:
					if(checkPassword(false)) { //check password. but this time 12 failed attampt condition will not be checked.
						wrongPasswordCounter = 0; //success login so counter to 0;
						System.out.println("\nPlease enter vaccine index number which you want to update");
						int vaccIndex = Integer.parseInt(scanner.nextLine());
						
						if(vaccIndex >= inventory.length) { //check to avoid array out of index exception
							System.out.println("Please input index between 0-" + (inventory.length-1));
						}
						
						//if selected index is empty then ask to enter new vaccine or go to main menu.
						else if(inventory[vaccIndex] == null) {
							System.out.println("\nNo vaccine in this index.");
							System.out.println("1. Add a new vaccine");
							System.out.println("2. Go to main menu");
						    int emptySlotChoice = Integer.parseInt(scanner.nextLine());
						    switch(emptySlotChoice) {
							    case 1: //Add new vaccine selected.
							    	
							    	//same procedure as New vaccine add as above
									System.out.println("\nEnter vaccine ID");
									Vaccine v = new Vaccine(Integer.parseInt(scanner.nextLine())); //assign id when creating object
									
									System.out.println("Enter choose vaccine Brand from below: ");
									System.out.println("1. PFIZER \n2. MODERNA \n3. ASTRAZENICA ");
									int c = Integer.parseInt(scanner.nextLine());
									switch(c) {
										case 1:
											v.setBrand(Vaccine.Brands.PFIZER);
											break;
										case 2:
											v.setBrand(Vaccine.Brands.MODERNA);
											break;
										case 3:
											v.setBrand(Vaccine.Brands.ASTRAZENICA);
											break;
									}
									
									System.out.println("Enter Dose");
									v.setDose(Double.parseDouble(scanner.nextLine()));
									
									System.out.println("Enter Expiry Date");
									v.setExpiry_date(scanner.nextLine());
									
									System.out.println("Enter price");
									v.setPrice_tag(Double.parseDouble(scanner.nextLine()));
									
									inventory[Vaccine.getNumberOfCreatedVaccine() - 1] = v;
									
									System.out.println("\nVaccine: #" + (Vaccine.getNumberOfCreatedVaccine() - 1));
									System.out.println(inventory[Vaccine.getNumberOfCreatedVaccine() - 1]);
									
							    	break;
							    default: //go to main menu
							    	break;
						    }
						}else { //Vaccine exist in the give slot. Promt user to update.
							System.out.println("\nVaccine: #" + vaccIndex);
							System.out.println(inventory[vaccIndex]); //print current vaccine info
							
							boolean isLoop = true; // loop flag to exit to main menu as we will enter a infinite loop.
							
							while(isLoop) { //keep promting to update from 1-4 until user choose option 5.
								System.out.println("\nWhat information  would you like to change");
								System.out.println("1. Brand");
								System.out.println("2. Dose");
								System.out.println("3. Expiry");
								System.out.println("4. price");
								System.out.println("5. Quit");
								System.out.println("Enter your choice >");
								int c = Integer.parseInt(scanner.nextLine());
								
								switch(c) { //switch case according to user choice
									case 1: //update brand name
										System.out.println("\nEnter choose vaccine Brand from below: ");
										System.out.println("1. PFIZER \n2. MODERNA \n3.ASTRAZENICA ");
										int updateChoice = Integer.parseInt(scanner.nextLine());
										switch(updateChoice) {
											case 1:
												inventory[vaccIndex].setBrand(Vaccine.Brands.PFIZER);
												break;
											case 2:
												inventory[vaccIndex].setBrand(Vaccine.Brands.MODERNA);
												break;
											case 3:
												inventory[vaccIndex].setBrand(Vaccine.Brands.ASTRAZENICA);
												break;
										}
										System.out.println("\nVaccine: #" + vaccIndex);
										System.out.println(inventory[vaccIndex]);
										break;
									case 2: //update dose
										System.out.println("\nEnter Dose");
										inventory[vaccIndex].setDose(Double.parseDouble(scanner.nextLine()));
										System.out.println("Vaccine: #" + vaccIndex);
										System.out.println(inventory[vaccIndex]);
										break;
									case 3: //update expiry date
										System.out.println("\nEnter Expiry Date");
										inventory[vaccIndex].setExpiry_date(scanner.nextLine());
										System.out.println("Vaccine: #" + vaccIndex);
										System.out.println(inventory[vaccIndex]);
										break;
									case 4: //update price
										System.out.println("\nEnter price");
										inventory[vaccIndex].setPrice_tag(Double.parseDouble(scanner.nextLine()));
										System.out.println("Vaccine: #" + vaccIndex);
										System.out.println(inventory[vaccIndex]);
										break;
									case 5: //exit infinite loop and go back to main menu.
										isLoop = false;
										break;
									default: //if input is out of range 1-5
										System.out.println("\nPLease choose between 1-5");
										break;
								}
							}
						}
					}
					break;
				case 3: //find vaccine by brand name
					System.out.println("Enter Brand name");
					System.out.println("1. PFIZER \n2. MODERNA \n3.ASTRAZENICA ");
					int c = Integer.parseInt(scanner.nextLine());
					switch(c) {
						case 1:
							findVaccineBy(Vaccine.Brands.PFIZER);
							break;
						case 2:
							findVaccineBy(Vaccine.Brands.MODERNA);
							break;
						case 3:
							findVaccineBy(Vaccine.Brands.ASTRAZENICA);
							break;
					}
					
					break;
				case 4: //find cheap vaccine by given price 
					System.out.println("Please enter price");
					findCheaperThan(Double.parseDouble(scanner.nextLine()));
					break;
				case 5: //exit program
					System.out.println("\nProgram terminated. Thanks for using the system.");
					System.exit(0);
					break;
				default: //if input is out of 1-5
					System.out.println("\nPlease enter between 1-5");
			}
		}
		
	}

}
