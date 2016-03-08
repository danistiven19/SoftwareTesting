package co.edu.udea.program1.Common.Models;

public class Part {

	public  Part() {
		this.NumberOfItems = 0;
	}
	public String Name;
	public int NumberOfItems;
	public int PartSize;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getNumberOfItems() {
		return NumberOfItems;
	}
	public void setNumberOfItems(int numberOfItems) {
		NumberOfItems = numberOfItems;
	}
	public int getPartSize() {
		return PartSize;
	}
	public void setPartSize(int partSize) {
		PartSize = partSize;
	}
	
	
}
