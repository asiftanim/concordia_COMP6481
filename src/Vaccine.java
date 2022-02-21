//.......................................
// Assignment 1
// © Kazi Asif Tanim
// Written by: Kazi Asif Tanim, 40216618
// Type: Vaccine class
//.......................................

public class Vaccine {
	
	public enum Brands {NONE, PFIZER, MODERNA, ASTRAZENICA}; //Enum of vaccine names
	
	private Brands brand;
	private double dose;
	private String expiry_date;
	private long id;
	private double price_tag;
	private static int numberOfCreatedVaccine = 0;
	
	//Default conostructor
	public Vaccine() {
		
		//Initialize to default vaule
		brand = Brands.NONE;
		dose = 0;
		expiry_date = "";
		id = 0;
		price_tag = 0;
		numberOfCreatedVaccine++; //increment to keep track of number of vaccine object created.
	}
	
	//Parametarized constructor
	public Vaccine(long id, Brands brand, double dose, String expiry_date, double price_tag) {
		
		//Init parameter values
		this.id = id;
		this.brand = brand;
		this.dose = dose;
		this.expiry_date = expiry_date;
		this.price_tag = price_tag;
		numberOfCreatedVaccine++; //increment to keep track of number of vaccine object created.
	}
	
	//Init id at the time of object creation. Not allowed to change after assignment
	public Vaccine(long id) {
		this.id = id;
		numberOfCreatedVaccine++; //increment to keep track of number of vaccine object created.
	}
	
	//Copy constructor
	public Vaccine(Vaccine v) {
		this.brand = v.brand;
		this.dose = v.dose;
		this.expiry_date = v.expiry_date;
		this.id = v.id;
		this.price_tag = v.price_tag;
		numberOfCreatedVaccine++; //increment to keep track of number of vaccine object created.
	}
	
	//Getters & Setters start
	//............................
	public long getId() {
		return id;
	}
	
	public Brands getBrand() {
		return brand;
	}

	public void setBrand(Brands brand) {
		this.brand = brand;
	}
	
	public double getDose() {
		return dose;
	}

	public void setDose(double dose) {
		this.dose = dose;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public double getPrice_tag() {
		return price_tag;
	}

	public void setPrice_tag(double price_tag) {
		this.price_tag = price_tag;
	}
	//Getter and Setter end
	//.......................
	
	//method to print vaccine object count
	public void findNumberOfCreatedVaccines() {
		System.out.println(numberOfCreatedVaccine);
	}
	
	//method to get vaccine object count
	public static int getNumberOfCreatedVaccine() {
		return numberOfCreatedVaccine;
	}
	
	//Override toString to print customize Vaccine object
	@Override
    public String toString() {
        return "ID: " + this.id + "\nBrand: " + this.brand.toString() + "\nDose: " + this.dose + "\nExpiry: " + this.expiry_date + "\nPrice: $" + this.price_tag;
    }
	
	//Override equals to compare between 2 Vaccine object
	@Override
	public boolean equals(Object o) {
		
		//if compared with same vaccine object
		if(o == this) {
			return true;
		}
		
		//Check if vaccine object or not. If different object then return false
		if (!(o instanceof Vaccine)) {
            return false;
        }
		
		Vaccine v = (Vaccine) o; //type casting Object to Vaccine to compare
		
		return brand.equals(v.brand) && dose == v.dose; //compare then return result
		
	}
}
