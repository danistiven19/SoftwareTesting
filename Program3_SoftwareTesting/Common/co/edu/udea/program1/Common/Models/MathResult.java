package co.edu.udea.program1.Common.Models;

public class MathResult  {
	public Correlation resultCorrelation;
	public Regression resultRegression;
	public String[] xArray;
	public String[] yArray;
	
	
	public Correlation getResultCorrelation() {
		return resultCorrelation;
	}
	public void setResultCorrelation(Correlation resultCorrelation) {
		this.resultCorrelation = resultCorrelation;
	}
	public Regression getResultRegression() {
		return resultRegression;
	}
	public void setResultRegression(Regression resultRegression) {
		this.resultRegression = resultRegression;
	}
	public String[] getxArray() {
		return xArray;
	}
	public void setxArray(String[] xArray) {
		this.xArray = xArray;
	}
	public String[] getyArray() {
		return yArray;
	}
	public void setyArray(String[] yArray) {
		this.yArray = yArray;
	}
	
}
