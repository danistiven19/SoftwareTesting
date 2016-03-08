package co.edu.udea.program1.Common.Models;

import java.io.File;
import java.util.List;

public class Program {

	public String Name;
	public int TotalSize;
	public List<Part> Parts;
	public File Directory;
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getTotalSize() {
		return TotalSize;
	}
	public void setTotalSize(int totalSize) {
		TotalSize = totalSize;
	}
	public List<Part> getParts() {
		return Parts;
	}
	public void setParts(List<Part> parts) {
		Parts = parts;
	}
	public File getDirectory() {
		return Directory;
	}
	public void setDirectory(File directory) {
		Directory = directory;
	}
}
