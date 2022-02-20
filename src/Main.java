import java.util.Scanner;

public class Main {
	
	private static final String password = "password";
	private static int wrongPasswordCounter = 0;
	static Vaccine[] inventory;
	
	public static boolean checkPassword(boolean isTerminate) {
		System.out.println("Please enter your password");
		Scanner scanner = new Scanner(System.in);
		String pass = scanner.nextLine();
		boolean output = false;
		
		if(pass.equals(password)) {
			output = true;
		}else {
			wrongPasswordCounter++;
			if(wrongPasswordCounter == 12 && isTerminate) {
				System.out.println("Program  detected  suspicious  activities  and  will  terminate  immediately!");
			   System.exit(0);
			}else if(wrongPasswordCounter == 3 || wrongPasswordCounter == 6 || wrongPasswordCounter == 9) {
				output = false;
			}else {
				if(checkPassword(isTerminate)) {
					output = true;
				};
			}
		}
		return output;
	}
	
	public static void findVaccineBy(Vaccine.Brands brand) {
		for(int i=0;i <Vaccine.getNumberOfCreatedVaccine(); i++) {
			if(inventory[i].getBrand() == brand) {
				System.out.println("Vaccine: #" + i);
				System.out.println(inventory[i]);
			}
		}
	}
	
	public static void findCheaperThan(double price) {
		for(int i=0;i <Vaccine.getNumberOfCreatedVaccine(); i++) {
			if(inventory[i].getPrice_tag() < price) {
				System.out.println("Vaccine: #" + i);
				System.out.println(inventory[i]);
			}
		}
	}

	public static void main(String[] args) {		
		
		System.out.println("Welcome User");
		System.out.println("How many vaccines your store will contain?");
		Scanner scanner = new Scanner(System.in);
		
		inventory = new Vaccine[Integer.parseInt(scanner.nextLine())];
		
		while(true) {
			System.out.println("What do you want to do?");
			System.out.println("1. Enter new vaccines (password required)");
			System.out.println("2. Change information of a vaccine (password required)");
			System.out.println("3. Display all vaccines by a specific brand");
			System.out.println("4. Display all vaccines under a certain price");
			System.out.println("5. Quit");
			System.out.println("Please enter your choice >");
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch(choice) {
				case 1:
					
					if(checkPassword(true)) {
						wrongPasswordCounter = 0;
						System.out.println("How many vaccines do you want to add?");
						int numOfVaccine = Integer.parseInt(scanner.nextLine());
						
						if(numOfVaccine <= (inventory.length - Vaccine.getNumberOfCreatedVaccine())) {
							
							for(int i=0; i<numOfVaccine; i++) {
								Vaccine v = new Vaccine();
								System.out.println("Enter vaccine ID");
								v.setId(Integer.parseInt(scanner.nextLine()));
								
								System.out.println("Enter choose vaccine Brand from below: ");
								System.out.println("1. PFIZER \n2. MODERNA \n3.ASTRAZENICA ");
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
								
								System.out.println("Enter Expirty Date");
								v.setExpiry_date(scanner.nextLine());
								
								System.out.println("Enter price");
								v.setPrice_tag(Double.parseDouble(scanner.nextLine()));
								
								inventory[Vaccine.getNumberOfCreatedVaccine() - 1] = v;
								
								System.out.println(inventory[Vaccine.getNumberOfCreatedVaccine() - 1]);
							}
	
							
							
						}else {
							System.out.println("Not enough storage. You can only add " + (inventory.length - Vaccine.getNumberOfCreatedVaccine()) + " Vaccine");
							break;
						}
					}else {
						break;
					}
					
					
					break;
				case 2:
					System.out.println("change");
					break;
				case 3:
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
				case 4:
					System.out.println("Please enter price");
					findCheaperThan(Double.parseDouble(scanner.nextLine()));
					break;
				case 5:
					System.out.println("Thanks for using the system.");
					System.exit(0);
					break;
				default:
					System.out.println("Please enter between 1-5");
			}
		}
		
	}

}
