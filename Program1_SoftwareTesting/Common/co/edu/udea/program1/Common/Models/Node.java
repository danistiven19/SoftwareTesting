package co.edu.udea.program1.Common.Models;

public class Node {
	
	private int id;
	private Node next;
	private Node previous;
	private double data;
	
	public Node(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node getPrevious() {
		return previous;
	}
	public void setPrevious(Node previous) {
		this.previous = previous;
	}
	public double getData() {
		return data;
	}
	public void setData(double data) {
		this.data = data;
	}

}
