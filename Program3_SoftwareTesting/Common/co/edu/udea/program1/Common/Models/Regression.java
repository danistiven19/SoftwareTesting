package co.edu.udea.program1.Common.Models;

public class Regression {
	public double b1;
	public double b0;
	public double yValue;
	public double xValue;
	
	public double getB1() {
		return b1;
	}
	public void setB1(double b1) {
		this.b1 = b1;
	}
	public double getB0() {
		return b0;
	}
	public void setB0(double b0) {
		this.b0 = b0;
	}
	public double getyValue() {
		return yValue;
	}
	public void setyValue(double yValue) {
		this.yValue = yValue;
	}
	public double getxValue() {
		return xValue;
	}
	public void setxValue(double xValue) {
		this.xValue = xValue;
	}
	
	@Override
	public String toString(){
		return "y = "+this.getB0()+" + "+this.getB1()+"X";
	}
	
}
