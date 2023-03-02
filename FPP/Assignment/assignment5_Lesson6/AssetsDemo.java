package assignment5_Lesson6;

//Answer===============
//An abstract superclass Asset
//four subclasses Appliance, Automobile, Furniture, and CompactDisc is a good design. 
//Any number of different asset types may be added in the future by subclassing Asset and
//implementing any abstract methods it declares.

//Assets Class ========================================

// this haven't been taught

abstract class Asset {
	private String serialNumber;
	private String category;
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
}

// Automobile Class
class Automobile extends Asset{
	private final String category = "Automobile";
	
	Automobile(String serial) {
		setCategory(category);
		setSerialNumber(serial);
	}
}

// Compact Disk Class ==============================================
class CompactDisk extends Asset{
	private final String category = "Compact Disk";
	
	public CompactDisk(String serial) {
		setCategory(category);
		setSerialNumber(serial);
	}
}

//Electronic Appliances  class===========================================
class ElecAppliance extends Asset{
	private final String category = "Electronic Appliance";
	
	public ElecAppliance(String serial) {
		setSerialNumber(serial);
		setCategory(category);
	}
}

//furniture class ================================================
class Furniture extends Asset{
	private final String category = "Furniture";
	
	Furniture(String serial) {
		setCategory(category);
		setSerialNumber(serial);
	}
}

// AssetsDemo class ===========================================================
public class AssetsDemo {

	public static void main(String[] args) {
		

	}

}
