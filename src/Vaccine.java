
public class Vaccine {
	
	public enum Brands {NONE, PFIZER, MODERNA, ASTRAZENICA};
	private Brands brand;
	private double dose;
	private String expiry_date;
	private long id;
	private double price_tag;
	private static int numberOfCreatedVaccine = 0;
	
	public Vaccine() {
		
		brand = Brands.NONE;
		dose = 0;
		expiry_date = "";
		id = 0;
		price_tag = 0;
		numberOfCreatedVaccine++;
	}
	
	public Vaccine(Brands brand, double dose, String expiry_date, double price_tag) {
		this.brand = brand;
		this.dose = dose;
		this.expiry_date = expiry_date;
		this.price_tag = price_tag;
		numberOfCreatedVaccine++;
	}
	
	public Vaccine(Vaccine v) {
		this.brand = v.brand;
		this.dose = v.dose;
		this.expiry_date = v.expiry_date;
		this.id = v.id;
		this.price_tag = v.price_tag;
		numberOfCreatedVaccine++;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public void findNumberOfCreatedVaccines() {
		System.out.println(numberOfCreatedVaccine);
	}
	
	public static int getNumberOfCreatedVaccine() {
		return numberOfCreatedVaccine;
	}
	
	@Override
    public String toString() {
        return "ID: " + this.id + "\nBrand: " + this.brand.toString() + "\nDose: " + this.dose + "\nExpiry: " + this.expiry_date + "\nPrice: $" + this.price_tag;
    }
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if (!(o instanceof Vaccine)) {
            return false;
        }
		
		Vaccine v = (Vaccine) o;
		
		return brand.equals(v.brand) && dose == v.dose; 
		
	}
}
